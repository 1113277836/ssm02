<%@ page import="com.hwua.ssm.po.Auth" %>
<%@ page import="com.alibaba.fastjson.JSON" %><%--
  Created by IntelliJ IDEA.
  User: 隔壁小王
  Date: 2017/12/19
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <%@include file="common.jsp"%>
    <script type="text/javascript">
        function exit() {
            $.ajax({
                url:path+"/user/exit",
                method:"post",
                success:function (res) {
                    if (res.msg){
                        location=path+"/login.jsp";
                    }
                }
            })
        }
    </script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height:50px;background:white;padding:10px;font-size: 20px">PACTERA 人力资源管理系统
        <div style="margin-left: 1100px;margin-top: -20px">欢迎${sessionScope.user.userName} <a href="javascript:void(0)" onclick="exit()">退出</a></div>
    </div>
    <div data-options="region:'west',split:false,title:'功能模块'" style="width:150px;padding:0px;">
        <div class="easyui-accordion" data-options="fit:true">
            <ul id="my-tree" class="easyui-tree"></ul>
            <c:forEach items="${sessionScope.auths}" var="auth">
                <c:set value="${auth}" var="auth"/>
                <div title="${auth.authName}">
                    <ul id="tree-${auth.dbid}"></ul>
                    <script type="text/javascript">
                        <%
                            Auth auth = (Auth) pageContext.getAttribute("auth");

                            String authJSON = JSON.toJSONString(auth.getChildren());
                            pageContext.setAttribute("authJSON",authJSON);
                        %>
                        var treeData='${authJSON}';
                        treeData = JSON.parse(treeData);
                        console.log(treeData);
                        $("#tree-${auth.dbid}").tree({
                            data:treeData,
                            onClick:function (node) {
                                if(!node.children.length) {
                                    if ($("#main-tab").tabs("exists",node.text)) {
                                        $("#main-tab").tabs("select", node.text);
                                    } else {
                                        $("#main-tab").tabs('add', {
                                            title: node.text,
                                            content: "<iframe height='100%' width='100%'  frameborder='0'"+"src=" + path + node.authURL + "/>",
                                            closable: true
                                        });
                                    }
                                }
                            }
                        })
                    </script>
                </div>
            </c:forEach>
        </div>
    </div>
    <div data-options="region:'center',title:'Center'">
        <div id="main-tab" class="easyui-tabs" data-options="fit:true"></div>
        <span style="font-size: 30px">这里是首页</span>
    </div>
</body>
</html>
