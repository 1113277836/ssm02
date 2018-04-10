<%--
  Created by IntelliJ IDEA.
  User: 隔壁小王
  Date: 2017/12/20
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@include file="common.jsp"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${path}/static/js/auth.js"></script>
</head>
<body>
<table id="auth-treegrid"></table>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="addoreditAuthForm('添加')" data-options="iconCls:'icon-add'">增加子节点</div>
    <div onclick="addoreditAuthForm('编辑')" data-options="iconCls:'icon-edit'">编辑子节点</div>
    <div class="menu-sep"></div>
    <div onclick="refreshAuth()">刷新</div>
</div>
<div id="authWindow" style="padding:10px;">
    <form id="ff" method="post">
        <input type="hidden" id="d bid" name="dbid">
        <input type="hidden" id="parentId" name="parentId">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="parentName" name="parentName" style="width:100%" data-options="label:'上级节点:',readonly:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="layer" name="layer" style="width:50%" data-options="label:'当前层级:',readonly:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="authName" name="authName" style="width:100%" data-options="label:'权限名称:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="authCode" name="authCode" style="width:100%" data-options="label:'权限编码:'">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="authURL" name="authURL" style="width:100%;height:60px" data-options="label:'URL:',multiline:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="orders" name="orders" style="width:100%" data-options="label:'排序:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <select class="easyui-combobox" id="type" name="type" label="类型" style="width:100%" data-options="editable:false,panelHeight:'50px'"><option value="1">模块</option><option value="2">资源</option></select>
        </div>
        <div style="margin-bottom:20px">
            <select class="easyui-combobox" id="valid" name="valid" label="是否有效" style="width:100%" data-options="editable:false,panelHeight:'50px'"><option value="1">有效</option><option value="0">无效</option></select>
        </div>
    </form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px" data-options="iconCls:'icon-ok'">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px" data-options="iconCls:'icon-cancel'">关闭</a>
    </div>
</div>

</body>
</html>
