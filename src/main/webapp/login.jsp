<%--
  Created by IntelliJ IDEA.
  User: 隔壁小王
  Date: 2017/12/27
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <%@include file="/WEB-INF/views/common.jsp"%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="static/css/reset.css">
    <link rel="stylesheet" href="static/css/supersized.css">
    <link rel="stylesheet" href="static/css/style.css">

    <script type="text/javascript">
        function login() {
            //获取输入框中的用户名和密码
            var userName = $("#userName").val()
            var password = $("#password").val()
            var formData = {
                userName:userName,
                password:password
            };
            $.ajax({
                url:path+"/user/login",
                method:"post",
                data:formData,
                success:function (res) {
                    if (res.msg){
                        location=path+"/main";
                    }else {
                        $.messager.alert("错误提示","登录失败!","error");
                    }
                }
            })
        }
    </script>
</head>
<body>

<div class="page-container">
    <h1>Login</h1>

        <input type="text" name="username" id="userName" placeholder="Username"><br/>
        <input type="password" name="password" id="password" placeholder="Password"><br/>
        <button type="submit" onclick="login()">Sign me in</button>
        <div class="error"><span>+</span></div>

</div>

<!-- Javascript -->
<script src="static/js/supersized.3.2.7.min.js"></script>
<script src="static/js/supersized-init.js"></script>
<script src="static/js/scripts.js"></script>

</body>


</html>
