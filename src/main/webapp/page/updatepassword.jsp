<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员修改密码-同城</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="css/font.css"/>
<!--     <link rel="stylesheet" href="/css/xadmin.css}"/> -->
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
    <link rel="shortcut icon" href="/logos.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/font.css"/>
    <link rel="stylesheet" href="/css/xadmin.css"/>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="this is my page"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">微链云修改管理员密码</div>
    <div id="darkbannerwrap"></div>

    <form id="forms" method="post" action="/user/web/updateuserpassword" class="layui-form">

        <input id="phone" name="phone" placeholder="手机号" type="text" lay-verify="required" class="layui-input"
               required="required" value="${phone}" />
        <hr class="hr15"/>
        <input name="password" lay-verify="required" placeholder="新密码" type="password" class="layui-input"
               required="required"/>
        <hr class="hr15"/>
        <input  lay-verify="required" placeholder="请输入验证码" type="text" id="code" name="code" style="width: 200px;">
        <input class="layui-btn" type="button" id="btn" value="获取验证码" style="width: 120px;height: 50px;" />
<%--         <input type="hidden" id="getcode" name="getcode" value="${code}" /> --%>
		<span id="co" style="display: none;"></span>
        <hr class="hr15"/>
    <p style="color: red;">${error}</p>
    <hr class="hr15"/>
    <input value="修改" lay-filter="login" style="width: 100%;" type="submit"/>
    <hr class="hr20"/>
    </form>
</div>

<script>
    $(function () {
        layui.use('form', function () {
            var form = layui.form;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function (data) {
                // alert(888)
                layer.msg(JSON.stringify(data.field), function () {
                    location.href = 'index.html'
                });
                return false;
            });
        });
    })
</script>
<script type="text/javascript">
	//输入电话号码获取验证码
	$("#btn").click(function(){
		var phone = document.getElementById("phone").value;
		if(phone != ""){
			var phones = $("#phone").val();
			var num={"num":phones};
			var url="/user/web/getCode";
			$.post(url,num,function(backData,status,ajax){
				$("#co").text(backData);
			});
			
			times=60,
			timer=null;
			// 计时开始
			var that = this;
			this.disabled=true;
			timer = setInterval(function(){
				times --;
				that.value = times + "秒后重试";
				if(times <= 0){
					that.disabled =false;
					that.value = "发送验证码";
					clearInterval(timer);
					times = 60;
				}
			       //console.log(times);
			},1000); 
		}else{
			alert("请输入您的电话号码");
		}
	});
</script>

<!-- 底部结束 -->
<script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>