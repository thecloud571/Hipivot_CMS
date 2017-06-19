<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../static/jquery-easyui-1.5.2/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../static/jquery-easyui-1.5.2/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="../static/jquery-easyui-1.5.2/themes/icon.css">
<script type="text/javascript"
	src="../static/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="../static/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../static/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
	<table title="视频列表" class="easyui-datagrid" pagination="true"
		url="../CMS/video/list.do"
		toolbar="#tb" fitColumns="true" fit="true" rownumbers="true">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="detail_CN.name" width="20%" align="center" formatter="name_CN">视频名称</th>
				<th field="detail_CN" width="10%" align="center">视频类型</th>
				<th field="sort.pubDate" width="10%" align="center" formatter="pubDate">发布时间</th>
				<th field="sort.playTime" width="10%" align="center" formatter="playTime">播放次数</th>
				<th field="sort.downloadTime" width="5%" align="center" formatter="downloadTime">下载次数</th>
				<th field="detail_CN.desc" width="30%" align="center" formatter="desc_CN">视频描叙</th>
			</tr>
		</thead>
	</table>
	<div id="tb" class="easyui-panel" style="padding:5px;">
		<div>
			
			<a href="javascript:openVideoAddDialog()" class="easyui-linkbutton"
				iconCLS="icon-add" plain="true"> 添加 </a>
			
			<a href="#" class="easyui-linkbutton"
				iconCLS="icon-edit" plain="true"> 编辑 </a>
			
			<a href="#" class="easyui-linkbutton"
				iconCLS="icon-remove" plain="true"> 删除 </a>
			
			<a href="#" class="easyui-linkbutton"
				iconCLS="icon-tip" plain="true"> 查看 </a>
			
		</div>
		<div>
			留空
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 700px; height: 75%; padding: 0px 30px" closed="true"
		buttons="#dlg-buttons" closable="false">
		<form id="fm" method="post">
			<div style="margin: 6px;">
				<input class="easyui-textbox" label="视频名称：" style="width: 60%"
					id="videoName" name="videoDetail.name" missingMessage="请填写视频中文名称"
					required="true">
			</div>
			<div style="margin: 6px;">
				<input class="easyui-combobox" label="视频类型：" style="width: 45%"
					id="videoType" name="videoType.uuid" missingMessage="请选择视频类型"
					required="true"
					data-options="panelHeight:'auto',editable:false,valueField:'uuid',textField:'name',url:'../CMS/video/getVideoType.do'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="easyui-timespinner" label="视频时长：" style="width: 45%"
					id="videoLength" name="lengthStr" missingMessage="请填写视频时长"
					required="true" showSeconds="true" value="00:00:00" min="00:00:00"
					,max="24:00:00" />
			</div>
			<div style="margin: 6px;">
				<input class="easyui-numberbox" label="视频评分：" style="width: 45%"
				id="videoScore" name="videoSort.score" missingMessage="请填写视频评分"
				required="true" showSeconds="true" precision="1" value="0" min="0"
				max="10" />
				&nbsp;&nbsp;&nbsp;&nbsp; 
				<input class="easyui-datetimebox" label="发布时间：" style="width: 45%" id="videoPubDate" name="pubDateStr"
					 missingMessage="请选择发布时间" required="true" />
			</div>
			<div style="margin: 6px;">
				<input class="easyui-textbox" label="视频描叙："
					style="width: 90%; height: 250px;" id="videoDesc"
					name="videoDetail.desc" missingMessage="请填写视频描叙" multiline="true"
					required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveVideo()" class="easyui-linkbutton"
			iconCls="icon-save" style="width: 80px;">保存</a> <a href="javascript:closeVideoDialog()"
			class="easyui-linkbutton" iconCls="icon-undo" style="width: 80px;">取消</a>
	</div>
	
	
	<script type="text/javascript" src="./js/videoList.js"></script>
</body>
</html>
