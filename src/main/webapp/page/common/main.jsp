<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>后台管理-同城</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!-- <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" /> -->
    <link rel="shortcut icon" href="/logos.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <style type="text/css">
    #bg{ 
	display: none; 
	position: absolute; 
	top: 0%; 
	left: 0%; 
	width: 100%; 
	height: 100%; 
	background-color: black; 
	z-index:1001; 
	-moz-opacity: 0.3; 
	opacity:.30; 
	filter: alpha(opacity=30);
}
 
#show{ 
	display: none; 
	position: absolute; 
	top: 30%; 
	left: 40%; 
	width: 26.5%; 
	height: auto; 
	padding: 8px;
	border: 8px solid #E8E9F7; 
	background-color: white; 
	z-index:1002; 
	overflow: auto;
}
    </style>
</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="index.html">同城</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <!--         <ul class="layui-nav left fast-add" lay-filter=""> -->
    <!--           <li class="layui-nav-item"> -->
    <!--             <a href="javascript:;">+新增</a> -->
    <!--             <dl class="layui-nav-child"> 二级菜单 -->
    <!--               <dd><a onclick="x_admin_show('资讯','http://www.baidu.com')"><i class="iconfont">&#xe6a2;</i>资讯</a></dd> -->
    <!--               <dd><a onclick="x_admin_show('图片','http://www.baidu.com')"><i class="iconfont">&#xe6a8;</i>图片</a></dd> -->
    <!--                <dd><a onclick="x_admin_show('用户','http://www.baidu.com')"><i class="iconfont">&#xe6b8;</i>用户</a></dd> -->
    <!--             </dl> -->
    <!--           </li> -->
    <!--         </ul> -->
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;"><span id="span">${phone}</span></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a href="javascript:editphone()">修改手机号</a></dd>
                <dd><a href="/login_out">退出</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;"><span id="span">手动更新</span></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a href="javascript:TaskGood()">行情商城</a></dd>
                <dd><a href="javascript:TaskData()">监测机数据</a></dd>
            </dl>
        </li>
    </ul>
    <input id="codeSuccess" name="codeSuccess" type="hidden">
 <!-- 添加测试数据等待 -->
<div id="bg"></div>
<div id="show">
  	<img src='/images/zhuanquan.gif' />
</div>
<script type="text/javascript">
//手动更新商品
function TaskGood() {
	layer.msg('正在统计,请耐心等待', {icon: 6, time: 2000});
	$.ajax({
         url :"/count/doCount",
         type:'post',
         timeout:60000,
         beforeSend:function(XMLHttpRequest){
       	 showDiv();
        }, 
        success:function(data,textStatus){ 
       	if(data){
          	layer.msg('更新行情商城成功', {icon: 6,time :2000});
      	}else{
      		layer.msg('更新行情商城失败,请重试!', {icon: 5,time :2000});
      	} 
       }, 
         complete:function(XMLHttpRequest,textStatus){ 
       	 hideDiv();
          }, 
          error:function(XMLHttpRequest,textStatus,errorThrown){ 
       	  hideDiv();
         } 
      }); 
}

function TaskData() {
	layer.msg('正在获取数据,请耐心等待', {icon: 6, time: 2000});
	$.ajax({ 
        url :"/doUpdate/doUpdateData", 
        type:'post', 
        timeout:60000, 
        beforeSend:function(XMLHttpRequest){ 
      	 showDiv();
       }, 
       success:function(data,textStatus){ 
      	if(data){
         	layer.msg('更新测试数据成功', {icon: 6,time :2000});
     	}el

           function editphone(){
               layer.open({
                   id:1,
                   type: 1,
                   title:'修改手机号',
                   skin:'layui-layer-rim',
                   area:['450px', 'auto'],
                   closeBtn : 1,
                   content: '<form class="layui-form" action=se{
     		layer.msg('更新测试数据失败,请重试!', {icon: 5,time :2000});
     	} 
      }, 
        complete:function(XMLHttpRequest,textStatus){ 
      	 hideDiv();
         }, 
         error:function(XMLHttpRequest,textStatus,errorThrown){ 
      	  hideDiv();
        } 
     });
}"">'
	            +'<div class="layui-form-item">'
	            +'<label class="layui-form-label">新手机号</label>'
	            +'<div class="layui-input-block">'
	            +'<input id="phone" name="phone" type="text"  autocomplete="off" placeholder="" class="layui-input">'
	            +'</div>'
	            +'</div>'
	              +'<div class="layui-form-item">'
	              +'<label class="layui-form-label">验证码</label>'
	              +'<div class="layui-input-block">'
	              +'<input id="code" name="code"  type="text"  class="layui-input" placeholder="请输入验证码">'
	              +'<input class="layui-btn" type="button" value="获取验证码"  autocomplete="off" id="btnSendCode" onclick="sendMessage()" style="width: 140px;height: 50px;" />'
                  +'<span id="co" style="display: none;"></span>'
	              +'</div>'
	              +'</div>'
	              +'</form>'
	        ,
	        btn:['确认修改'],
	        btn1: function (index,layero) {
	        	var phone = $("#phone").val();
	        	var codeSuccess = $("#codeSuccess").val();
	        	var code = $("#code").val();
	        	if(phone!=null && phone!='' && code!=null && code != ''){
	        	$.post("/user/editphone",{"phone":phone,"code":code,"codeSuccess":codeSuccess},function(data){
	        		if(data>0){
	        			layer.open({
	        					id:2,
	        			        type: 1,
	        			        title:'修改成功',
	        			        skin:'layui-layer-rim',
	        			        area:['450px', 'auto'],
	        			        closeBtn : 0
	        			        ,content: ' <div class="row" style="width: 420px;  margin-left:7px; margin-top:10px;">修改成功,点击确定按钮,重新登录</div>'
	        			        ,
	        			        btn:['确定'],
	        			        btn1: function (index,layero) {
	        			        	location.href="/login_out";
	        			        }
	        			});
	        		}else{
	        			layer.msg('修改失败,请重试', {icon: 5, time: 2000});
	        		}
	        		if(data==0){
	        			layer.msg('验证码输入不一致', {icon: 5, time: 2000});
	        		}
	        	});
	    	    }else{
	    	    	layer.msg('请输入手机号或验证码', {icon: 5, time: 2000});
	    	    }
			}
	 	});
}
layui.use(['form','layer'], function(){
    $ = layui.jquery;
  var form = layui.form
  ,layer = layui.layer
  ,upload = layui.upload;

  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
  
});

function showDiv() { 
	parent.document.getElementById("bg").style.display ="block";
	parent.document.getElementById("show").style.display ="block";
}
 
function hideDiv(){
	parent.document.getElementById("bg").style.display ="none";
	parent.document.getElementById("show").style.display ="none";	
}
</script>
<script type="text/javascript">
var InterValObj; //timer变量，控制时间 
var count = 60; //间隔函数，1秒执行 
var curCount;//当前剩余秒数 
  
function sendMessage() { 
 　	
　　  
    var phone = $("#phone").val();
 	//发异步，把数据提交给controller
 	if(phone!=null&&phone!=''){
	curCount = count; 
　　  //设置button效果，开始计时 
     $("#btnSendCode").attr("disabled", "true"); 
 	 $("#btnSendCode").val(curCount + "秒后可重新发送"); 
     InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次 
 	//获取用户输入的手机号
 	$.post("/user/ObtainCode",{"phone":phone},function(data){
 		if(data>0){
 			layer.msg('发送成功', {icon: 6, time: 1000});
 			$("#codeSuccess").val(data);
 		}else{
 			layer.msg('发送失败,请重试!', {icon: 5, time: 2000});
 		}
 	});
	}else{
	      layer.msg('请输入手机号', {icon: 5, time: 2000});
	} 
} 
//timer处理函数 
function SetRemainTime() { 
      if (curCount == 0) {         
        window.clearInterval(InterValObj);//停止计时器 
        $("#btnSendCode").removeAttr("disabled");//启用按钮 
        $("#btnSendCode").val("重新发送验证码"); 
      } 
      else { 
        curCount--; 
        $("#btnSendCode").val(curCount + "秒后可重新发送"); 
      } 
    } 
</script>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav" style="width: 240px;">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>用户中心</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/user/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户列表</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>内容管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                	<li>
                        <a _href="/review/web/authenticationtoexaminelist">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户说说</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/review/web/administratorslist">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>资讯管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>积分商城</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                	<li>
                        <a _href="/enterprise/web/comapply-overview">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>商品管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/enterprise/web/comapply-overview">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>订单管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/enterprise/web/comapply-overview">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>配置管理</cite>
                        </a>
                    </li>
                </ul>
            </li>


            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b4;</i>
                    <cite>内容管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/config/Web/Config">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>基础配置</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/config/Web/findconfigYsxyAndFwxy">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>轮播管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/config/Web/appcofig">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>分类管理</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/config/Web/appcofig">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>首页说说</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/config/Web/appcofig">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>资讯管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b4;</i>
                    <cite>类目管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/page/category/category-list.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>类目列表</cite>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='/enterprise/web/comapply-overview' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2018 同城 All Rights Reserved</div>
</div>
<!-- 底部结束 -->
</body>
</html>