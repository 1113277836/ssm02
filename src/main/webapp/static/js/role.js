$(function () {
    initDataGrid();
})
function submitRole() {
    //获取tree中选中的节点
    var nodes=$("#grantrole2auth").tree("getChecked");
    var authIds=[];
    for (var i =0;i<nodes.length;i++){
        authIds.push(nodes[i].id);
    }
    var roleId =$("#roleId").val();
    console.log(roleId);
    var formData={
        roleId:roleId,
        authIds:authIds
    };
    $.ajax({
        url:path+"/role/grantAuth",
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
    $("#roleWindow").window("close");
}
function searchForm() {
    //两个输入框的内容，下拉列表的内容
    var formData = {
        roleName:$("#name").textbox("getValue"),
        roleCode:$("#code").textbox("getValue"),
        valid:$("#vali").combobox("getValue")
    };
    console.log(formData);
    $("#roleDatagrid").datagrid("load",formData);
}
    function initDataGrid() {
        $("#roleDatagrid").datagrid({
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
            url:path+'/role/queryRole',
            columns:[[{field:'roleName',title:'roleName'},
                {field:'roleCode',title:'roleCode'},
                {field:'valid',title:'valid',formatter:function(value,row,index) {
            if (value == '1'){
                return"有效"
            }else {
                return"<span style='color: red'>无效</span>"
            }
        }},
                {field:'orders',title:'orders'},
                {field:'dbid',title:'授权',width:"200",
                    formatter:function (value,row,index) {
                        return '<a href="javascript:void(0)"  onclick="showGrantAuth2Role('+value+')">授权</a>'
                    }}
            ]]
        });
    }
function showGrantAuth2Role(roleId) {
    $("#roleId").val(roleId);
    $("#grantrole2auth").tree({
        url:path+'/role/getAllAuth?roleId='+roleId,
        method:'get',
        animate:true,
        checkbox:true,
        cascadeCheck:false
    })
    // console.log(roleId)
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
        //关闭窗口时重置表单
        onBeforeClose:function () {
            $("#grantForm").form("reset");
        }

    }).window("open")

}
function submitForm() {
    //Ajax提交表单
    //1.准备请求参数
    var formData =  $("#ff").form().serialize();
    //2.发起请求参数
    $.ajax({
        url:path+'/role/doInsertRole',//请求地址
        method:'post',//请求方式
        data:formData,//请求数据
        success:function (res) {
            if (res.msg){
                //成功
                $("#roleWindow").window("close");
                $("#roleDatagrid").datagrid("reload");
            }
        }
    })
}
function addForm(action){
     var row =$("#roleDatagrid").datagrid("getSelected");
     if (action=='编辑'){
                $("#dbid").val(row.dbid);
              $("#roleName").textbox("setValue",row.roleName);
             $("#roleCode").textbox("setValue",row.roleCode);
             $("#valid").combobox("setValue",row.valid);
             $("#orders").textbox("setValue",row.orders);
     }
    $("#roleWindow").window({
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





