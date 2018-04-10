<%--
  Created by IntelliJ IDEA.
  User: 隔壁小王
  Date: 2017/12/22
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@include file="common.jsp"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${path}/static/js/role.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:50px;background:white;padding:10px;font-size: 20px">
    <input id="name" class="easyui-textbox" style="width: 20%" data-options="prompt:'输入权限名称'">
    <input  id="code" class="easyui-textbox" style="width: 20%" data-options="prompt:'输入权限编码'">
    <select id="vali" class="easyui-combobox" name="state" style="width: 20%">
        <option value="1">有效</option>
        <option value="0">无效</option>
    </select>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchForm()" style="width:80px" data-options="iconCls:'icon-search'">搜索</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addForm('添加')" style="width:80px" data-options="iconCls:'icon-add'">添加</a>
</div>

<div data-options="region:'center',title:'Center'">
    <table id="roleDatagrid">

    </table>
    <div id="roleWindow" style="padding:10px;">
        <form id="ff" method="post">
            <input type="hidden" id="dbid" name="dbid">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" id="roleName" name="roleName" style="width:100%" data-options="label:'角色名称:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" id="roleCode" name="roleCode" style="width:100%" data-options="label:'角色编码:'">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" id="valid" name="valid" label="是否有效" style="width:100%" data-options="editable:false,panelHeight:'50px'"><option value="1">有效</option><option value="0">无效</option></select>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" id="orders" name="orders" style="width:100%" data-options="label:'排序:',required:true">
            </div>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px" data-options="iconCls:'icon-ok'">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px" data-options="iconCls:'icon-cancel'">关闭</a>
        </div>
    </div>
    <div id="grantWindow"  style="padding:10px;">
        <div class="easyui-panel" style="padding:5px">
            <input type="hidden" id="roleId"/>
            <ul id="grantrole2auth" class="easyui-tree" ></ul>
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRole()" style="width:80px" data-options="iconCls:'icon-ok'">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px" data-options="iconCls:'icon-cancel'">关闭</a>
        </div>
    </div>

</div>

</body>
</html>
