package com.julu.config.security;
import com.julu.handler.CustomUserDetailsService;
import com.julu.handler.LoginFailureHandler;
import com.julu.handler.LoginSuccessHandler;

import com.julu.utils.MD5;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 安全策略配置
 * @author mhs123
 *
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableConfigurationProperties(SecuritySettings.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected Log log = LogFactory.getLog(getClass());
    @Autowired
    private AuthenticationManager authenticationManager;
    //配置文件的setting
    @Autowired
    private SecuritySettings settings;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    //用户身份验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5.getMD5_32_xx((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5.getMD5_32_xx((String)rawPassword));
            }
        });
        //remember me
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler()).failureHandler(loginFailureHandler())//指定登陆页面url
                .and().authorizeRequests()
               // .antMatchers("/*/app/**","/doc.html","/","/*.html","/images/**", "/checkcode", "/lib/**","/fonts/**","/js/**", "/css/**").permitAll() //完全允许访问的url
                // swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                // swagger end
                .antMatchers(settings.getPermitall().split(",")).permitAll()
                .anyRequest().authenticated()
                .and().csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())//防止跨站请求伪造攻击
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().logout().logoutSuccessUrl(settings.getLogoutsuccssurl())//设置使用默认的退出
                .and().exceptionHandling().accessDeniedPage(settings.getDeniedpage())
                .and().rememberMe().tokenValiditySeconds(86400).tokenRepository(tokenRepository());//记录登陆状态指定记录的时间为86400秒，即为1天
    }
    //官方推荐的加密算法比md5更安全
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //指定保存登录用户token的数据源
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
        jtr.setDataSource(dataSource);
        return jtr;
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }


    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }
    /**
     * 权限管理设置
     * 过滤器在系统启动时开始工作，并同时导入资源管理器和权限决断器，对用户访问资源进行管理
     * @return
     * @throws Exception
     */
    @Bean
    public CustomFilterSecurityInterceptor customFilter() throws Exception{
        CustomFilterSecurityInterceptor customFilter = new CustomFilterSecurityInterceptor();
        customFilter.setSecurityMetadataSource(securityMetadataSource());
        customFilter.setAccessDecisionManager(accessDecisionManager());//设置一个拒绝访问的请求链接
        customFilter.setAuthenticationManager(authenticationManager);
        return customFilter;
    }

    @Bean
    public CustomAccessDecisionManager accessDecisionManager() {
        return new CustomAccessDecisionManager();
    }

    @Bean
    public CustomSecurityMetadataSource securityMetadataSource() {
        return new CustomSecurityMetadataSource(settings.getUrlroles());
    }

   //加入需要排除csrf保护的列表
    private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher(){
        CsrfSecurityRequestMatcher csrfSecurityRequestMatcher = new CsrfSecurityRequestMatcher();
        List<String> list = new ArrayList<String>();
        list.add("/rest/");
        csrfSecurityRequestMatcher.setExecludeUrls(list);
        return csrfSecurityRequestMatcher;
    }
}
