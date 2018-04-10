$(function () {
    initDataGrid();
})

function submitRole() {
    //获取选中的节点
    var nodes=$("#grantrole2auth").datagrid("getSelections");
    var roleIds=[];
    for (var i =0;i<nodes.length;i++){
        roleIds.push(nodes[i].dbid);
    }
    console.log(roleIds);
    var userId =$("#userId").val();
    console.log(userId);
    var formData={
        userId:userId,
        roleIds:roleIds
    };
    $.ajax({
        url:path+"/user/grantRole",
        method:"post",
        data:formData,
        traditional:true,//解决传递参数时出现的问题
        success:function (res) {
            if (res.msg){
                $("#grantWindow").window("close")
            }
        }

    })
}


function clearForm(){
    $("#grantWindow").window("close");
}
//搜索内容
function searchForm() {
    //两个输入框的内容，下拉列表的内容
    var formData = {
        userName:$("#name").textbox("getValue"),
        realName:$("#rname").textbox("getValue"),
        valid:$("#vali").combobox("getValue")
    };
    console.log(formData);
    $("#userDatagrid").datagrid("load",formData);
}
function initDataGrid() {
    $("#userDatagrid").datagrid({
        rownumbers:true,//显示行号
        singleSelect:true,//单选
        autoRowHeight:false,//自动行高
        pagination:true,//是否分页
        pageSize:10, //一页显示多少条数
        method:'post',
        fit:true,
        fitColumns:true,
        onDblClickRow:function (row,index) {
            addForm('编辑')
        },
        url:path+'/user/queryUser',
        columns:[[{field:'userName',title:'userName'},
            {field:'realName',title:'realName'},
            {field:'valid',title:'valid',formatter:function(value,row,index) {
                if (value == '1'){
                    return"正常"
                }else {
                    return"<span style='color: red'>冻结</span>"
                }
            }},
             {field:'dbid',title:'授予角色',width:"200",
                formatter:function (value,row,index) {
                   return '<a href="javascript:void(0)"  onclick="showGrantAuth2Role('+value+')">授予角色</a>'
             }}
        ]]
    });
}
function showGrantAuth2Role(userId) {
    $("#userId").val(userId)
    $("#grantrole2auth").datagrid({
        url:path+'/user/getAllRole?userId='+userId,
        method:'get',
        rownumbers:true,
        columns:[[{field:"dbid",checkbox:true},
            {field:"roleName",title:"角色名称",width:100},
            {field:"roleCode",title:"角色编码",width:100}]],
        onLoadSuccess:function (data) {//数据加载完成时执行
            var rows =data.rows;
            for (var i=0;i<rows.length;i++){
                if (rows[i].checked){
                    //此行应该被选中
                    $(this).datagrid("selectRow",i);
                }
            }
        }
    });
    // console.log(userId)
    $("#grantWindow").window({
        modal: true,//灰色的隔层
        close: true,//是否可以关闭
        iconCls: 'icon-save',//图标
        title: "授权窗口",
        height:560,
        width: 500,
        collapsible: false,//折叠
        minimizable: false,//最小化
        maxmizable: false,//最大化
        resizable: false,//拉伸
        footer:"#footer",
        //关闭窗口时重置表单
        onBeforeClose:function () {
            $("#grantForm").form("reset");
        }
    }).window("open")

}
//添加增加提价按钮
function submitForm() {
    //Ajax提交表单
    //1.准备请求参数
    var formData =  $("#ff").form().serialize();
    var password =$("#password").val()
    var passwordd=$("#passwordd").val()

    //2.发起请求参数
    $.ajax({
        url:path+'/user/update',//请求地址
        method:'post',//请求方式
        data:formData,//请求数据
        success:function (res) {
            if (res.msg&&password==passwordd){
                //成功
                $("#userWindow").window("close");
                $("#userDatagrid").datagrid("reload");
            }else {
                $.messager.alert("错误提示","密码不一致","error");
            }
        }
    })
}
//添加编辑界面
function addForm(action){
    var row =$("#userDatagrid").datagrid("getSelected");
    if (action=='编辑'){
        $("#dbid").val(row.dbid);
        $("#userName").textbox("setValue",row.userName);
        $("#realName").textbox("setValue",row.realName);
        $("#password").textbox("setValue",row.password);
        $("#passwordd").textbox("setValue",row.password);
        $("#valid").combobox("setValue",row.valid);
    }
    $("#userWindow").window({
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





