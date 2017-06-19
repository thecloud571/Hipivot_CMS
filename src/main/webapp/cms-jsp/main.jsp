<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内容管理界面-玩它 -深圳卓梦科技有限公司</title>
<link rel="stylesheet" type="text/css" href="../static/jquery-easyui-1.5.2/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="../static/jquery-easyui-1.5.2/themes/icon.css">
<script type="text/javascript" src="../static/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="../static/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../static/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
			$("#tabs").tabs("reload");
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/cms-jsp/"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content,
				pill:true
			});
		}
	}
	
	//关闭Tabs
    function closeTab(menu, type) {
        var allTabs = $("#tabs").tabs('tabs');
        var allTabtitle = [];
        $.each(allTabs, function (i, n) {
            var opt = $(n).panel('options');
            if (opt.closable)
                allTabtitle.push(opt.title);
        });
        var curTabTitle = $(menu).data("tabTitle");
        var curTabIndex = $("#tabs").tabs("getTabIndex", $("#tabs").tabs("getTab", curTabTitle));
        switch (type) {
            case 1:
                $("#tabs").tabs("close", curTabIndex);
                return false;
                break;
            case 2:
                for (var i = 0; i < allTabtitle.length; i++) {
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
                break;
            case 3:
                for (var i = 0; i < allTabtitle.length; i++) {
                    if (curTabTitle != allTabtitle[i])
                        $('#tabs').tabs('close', allTabtitle[i]);
                }
                $('#tabs').tabs('select', curTabTitle);
                break;
            case 4:
                for (var i = curTabIndex; i < allTabtitle.length; i++) {
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
                $('#tabs').tabs('select', curTabTitle);
                break;
            case 5:
                for (var i = 0; i < curTabIndex-1; i++) {
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
                $('#tabs').tabs('select', curTabTitle);
                break;
        }

    }
	
	 $(document).ready(function () {
         //监听右键事件，创建右键菜单
         $('#tabs').tabs({
             onContextMenu:function(e, title,index){
                 e.preventDefault();
                 if(index>0){
                     $('#mm').menu('show', {
                         left: e.pageX,
                         top: e.pageY
                     }).data("tabTitle", title);
                 }
             }
         });
         //右键菜单click
         $("#mm").menu({
             onClick : function (item) {
                 closeTab(this, item.name);
             }
         });
	 });
	
	function openPasswordModifyDialog(){
		$("#dlg").dialog("open").dialog("setTitle","修改密码");
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		url="${pageContext.request.contextPath}/cms/manager/modifyPassword.do?userName="+${currentUser.userName}+"&oldPassword="+oldPassword+"&newPassword="+newPassword;
	}
	
	function modifyPassword(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				var newPassword=$("#newPassword").val();
				var newPassword2=$("#newPassword2").val();
				if(!$(this).form("validate")){
					return false;
				}
				if(newPassword!=newPassword2){
					$.messager.alert("系统提示","确认密码输入错误！");
					return false;
				}
				return true;
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.failed){
					$.messager.alert("系统提示","旧密码错误，请重新输入");
					resetValue();
					$("#dlg").dialog("reload");
				}
				if(result.success){
					$.messager.alert("系统提示","密码修改成功，重新登录时生效！");
					resetValue();
					$("#dlg").dialog("close");
				}else{
					$.messager.alert("系统提示","密码修改失败！");
					return;
				}
			}
		 });
	}
	
	function closePasswordModifyDialog(){
		resetValue();
		$("#dlg").dialog("close");
	}
	
	function resetValue(){
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#newPassword2").val("");
	}
	
	function logout(){
		$.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
			if(r){
				window.location.href='${pageContext.request.contextPath}/cms/manager/logout.do';
			} 
		 });
	}
	
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 15%;">
	<table style="padding: 0px" width="100%">
		<tr>
			<td width="30%">
				留空
			</td>
			<td valign="top" align="right" width="40%">
				<font>用户:</font><font color="blue">[${currentUser.userName }]</font>-<font>【${currentUser.role.name }】</font>
				<a href="javascript:logout()" class="easyui-linkbutton" data-options="border:true,plain:true," style="margin-top: -4px;"><font color="red">安全退出</font></a>
			</td>
		</tr>
	</table>
</div>

<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs" pill=true>
		<div title="首页" data-options="iconCls:'icon-man'">
			<div align="center" style="padding-top: 150px;">
				留空
			</div>
		</div>
	</div>
</div>

<div region="west" style="width: 260px;padding: 5px;" title="导航菜单">
	<div class="easyui-accordion" data-options="fit:true">
			
			<div title="上线管理" style="padding:10px;height: 10px;" data-options="selected:true">
			</div>
			
			<div title="视频管理" style="padding:10px;height: 10px" data-options="">
				<a href="javascript:openTab('视频列表管理','videoList.jsp')" class="easyui-linkbutton" data-options="plain:true" style="width: 225px">视频列表管理</a>
				<a href="javascript:openTab('本地资源列表','videoLocalList.jsp')" class="easyui-linkbutton" data-options="plain:true" style="width: 225px">本地资源列表</a>
				<a href="javascript:openTab('网络资源列表','videoNetList.jsp')" class="easyui-linkbutton" data-options="plain:true" style="width: 225px">网络资源列表</a>
			</div>
			
			<div title="游戏管理" style="padding:10px;height: 10px" data-options="">
			</div>
			
			<div title="横幅管理" style="padding:10px;height: 10px" data-options=""">
			</div>
			
			<div title="标签管理" style="padding:10px;height: 10px" data-options="">
			</div>
			
			<div title="类型管理" style="padding:10px;height: 10px" data-options="">
			</div>
			
			<div title="推广管理" style="padding:10px;height: 10px" data-options="">
			</div>
			
			<div title="MAC管理" style="padding:10px;height: 10px" data-options="">
			</div>
			
			<div title="用户管理" style="padding:10px;height: 10px" data-options="">
			</div>
			
			<div title="系统管理" style="padding:10px;height: 10px" data-options="">
			</div>
			
	</div>
</div>
<div region="south" style="height: 25px;padding: 5px" align="center">
	Copyright © 2015-2017 深圳卓梦科技有限公司  版权所有
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding: 10px 20px" 
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>用户名：</td>
   			<td><input type="text" id="userName" name="userName" readonly="readonly" value="${currentUser.userName }" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>旧密码：</td>
   			<td><input type="password" id="oldPassword" name="oldPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>新密码：</td>
   			<td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>确认新密码：</td>
   			<td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   	</table>
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
 
 <div id="mm" class="easyui-menu" style="width:160px;">
	<div id="mm-tabclose" data-options="name:1">关闭当前</div>
	<div id="mm-tabcloseall" data-options="name:2">全部关闭</div>
	<div id="mm-tabcloseother" data-options="name:3">关闭其它</div>
	<div class="menu-sep"></div>
	<div id="mm-tabcloseright" data-options="name:4">当前右侧全部关闭</div>
	<div id="mm-tabcloseleft" data-options="name:5">当前左侧全部关闭</div>
 </div>
 
</body>
</html>