
function clearForm(){
    $("#authWindow").window("close");
}
function submitForm() {
    //Ajax提交表单
    //1.准备请求参数
    var formData =  $("#ff").form().serialize();
    //2.发起请求参数
    $.ajax({
        url:path+'/auth/update',//请求地址
        method:'post',//请求方式
        data:formData,//请求数据
        success:function (res) {
            if (res.msg){
                //成功
                $("#authWindow").window("close");
                $("#auth-treegrid").treegrid("reload");
            }
        }
    })
}
function addoreditAuthForm(action){
    //获取treegrid选中的行
    var row =$("#auth-treegrid").treegrid("getSelected");
    var parent = $("#auth-treegrid").treegrid("getParent",row.dbid);
    if (action == '添加'){
        $("#parentName").textbox("setValue",row.authName);
        $("#layer").textbox("setValue",parseInt(row.layer)+1);
        $("#parentId").val(row.dbid);
    }else{
        $("#parentName").textbox("setValue",parent.authName);
        $("#layer").textbox("setValue",row.layer);
        $("#authName").textbox("setValue",row.authName);
        $("#authCode").textbox("setValue",row.authCode);
        $("#authURL").textbox("setValue",row.authURL);
        $("#orders").textbox("setValue",row.orders);
        $("#type").combobox("setValue",row.type);
        $("#valid").combobox("setValue",row.valid);
        $("#dbid").val(row.dbid);
        $("#parentId").val(row.parentId);
    }
    $("#authWindow").window({
        modal: true,
        closed: true,
        iconCls: "icon-edit",
        width: 400,
        height: 400,
        title: action+"权限",
        collapsible: false,
        minimizable: false,
        maximizable: false,
        resizable:false,
        onBeforeClose:function () {
            $("#ff").form("reset");
        }
    }).window("open")
}
function refreshAuth(){
    $("#auth-treegrid").treegrid("reload");
}
function onContextMenu(e,row) {
    if(row){
        //响应右击事件
        e.preventDefault();//组织浏览器的右击菜单弹出
        //选中指定的事件
        $(this).treegrid("select",row.dbid);
        //显示右键菜单
        $("#mm").menu("show",{
            left:e.pageX,
            top:e.pageY
        })

    }
}
$(function () {
    $("#auth-treegrid").treegrid({
        url:path+"/auth/toAuth",
        idField:"dbid",//唯一字段
        method:"get",//请求方式
        fit:true,//填充
        rownumbers:true,//显示行号
        treeField:"authName",//需要显示为tree的字段
        onContextMenu:onContextMenu,
        columns:[[{title:"权限名称",field:"authName",width:"200px"},
            {title:"权限编码",field:"authCode",width:"80px"},
            {title:"URL",field:"authURL",width:"80px"},
            {title:"类型",field:"type",width:"80px",formatter:function (value, row, index) {
                if (value=="1"){
                    return "模块";
                }else{
                    return "资源";
                }
            }},
            {title:"排序",field:"orders",width:"80px"},
            {title:"是否有效",field:"valid",width:"80px",formatter:function (value,row,index) {
                if (value=="1"){
                    return "显示";
                }else {
                    return "<span style='color: red'>不显示</span>";
                }
            }},
            {title:"层级",field:"layer",width:"80px"},
        ]]
    })
})