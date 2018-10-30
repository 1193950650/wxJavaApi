 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微链云管理员登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/font.css"/>
    <link rel="stylesheet" href="/css/xadmin.css}"/>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/font.css"/>
    <link rel="stylesheet" href="/css/xadmin.css"/>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="this is my page" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">微链云管理登录</div>
    <div id="darkbannerwrap"></div>

    <form id="forms" method="post" action="/user/web/UsersLogin" class="layui-form">

        <input name="phone" placeholder="手机号" type="text" lay-verify="required" class="layui-input" required="required" />
        <hr class="hr15"/>
        <input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input" required="required" />
        <hr class="hr15"/>
<!--         <div> -->
<!--         	<div style="wid 167px;float: left;"><input name="code" lay-verify="required" placeholder="验证码" type="code" class="layui-input" style="wid 167px;height: 45px;" /></div> -->
<!--         	&nbsp;&nbsp;&nbsp;<div style="wid 167px;height: 45px;float: left;"> -->
<!--         		<img style="wid 167px;height: 45px;" id="codeImg" onclick="this.src='verifycode?'+Math.random()" alt="点击更换" title="点击更换" src="verifycode" /> -->
<!--         	</div> -->
<!-- 		</div> -->
<!--         <hr class="hr15"/> -->
        <p style="color: red;" text="${error}"></p>
        <hr class="hr15"/>
        <input value="登录" lay-filter="login" style="wid100%;" type="submit"/>
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
<script type="text/javascript">
// function test(){
//     var form = new FormData(document.getElementById("forms"));
// //     var req = new XMLHttpRequest();
// //     req.open("post", "${pageContext.request.contextPath}/public/testupload", false);
// //     req.send(form);
//     $.ajax({
//         url:"/user/web/UsersLogin",
//         type:"post",
//         data:form,
//         processData:false,
//         contentType:false,
//         success:function(data){
//             window.clearInterval(timer);
//             console.log("over..");
//         },
//         error:function(e){
//             alert("错误！！");
//             window.clearInterval(timer);
//         }
//     });        
//     get();//此处为上传文件的进度条
// }
</script>
</body>
</html>