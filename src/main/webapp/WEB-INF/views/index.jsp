<%@ page pageEncoding="utf-8" %>
<html>
<head>
	<title>文件上传测试</title>
	<link rel="stylesheet" type="text/css" href="http://localhost/static/css/plugins/webuploader/webuploader.css">
	<script type="text/javascript" src="http://localhost/static/js/plugins/jquery/jquery.js"></script>
	<script type="text/javascript" src="http://localhost/static/js/plugins/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="http://localhost/static/js/plugins/webuploader/upload.js"></script>
	<script type="text/javascript">
		$(function(){
			var json = {
				
				// 其他参数
				auto:true,
				
				// pick参数
				pickId:"#filePicker",
				pickName:"请选择图片",
				
				// 上传数据（非文件）
				data:{specs:"1x430x430,1x240x240,1x320x320"},
				server:"/zhiguw-file/upload",
				
				// accept参数
				title:"选择你要上传的图片",
				extensions: 'gif,jpg,jpeg,bmp,png',
			    mimeTypes: 'image/*',
			    
			    // 文件大小参数
			    fileSingleSizeLimit:1024*1024,
			    minWidth:430,
			    minHeight:430,
			    
			    
			    // 事件
			    dialogOpen:dialogOpen,
				
			};
			function dialogOpen(){
				console.log(566666);
			}
			
			$("#test_btn").zhiguFileUploader(json);
		})
	</script>
</head>
<body>

<!-- <div id="test">
	<fieldset>
		<legend>文件上传测试</legend>
		
		<input id="test_btn" type="button" value="CLICK ME!"/>
		<label id="filePicker"></label>
	</fieldset>
</div> -->


























<div style="">
	<fieldset>
	<p>测试(返回json)</p>
	<form method="POST" enctype="multipart/form-data" action="/zhiguw-file/upload">
			<label for="file">File</label>
			文件：<input id="file" type="file" name="file" />
			类型：<input name="type" />
			规格：<input name="specs" />
			<p> <input type="submit" value="submit" /></p>		
	</form>
	</fieldset>
</div>		
	<div style="display:none">
		
	 <p>*            图片规格裁切说明： </p>
	 <p>*            
	 <p>*            1x320x320 --> 等比压缩图片，可能不符合规格，图片不变行  --压缩</p>
	 <p>*            2x320x320 --> 非等比压缩图片，完全符合规格，图片可能变形  --压缩</p>
	 <p>*            3x540x540x320x320 --> 先压缩（540x540）后裁剪 (320x320) 等比操作   --先压缩后裁切</p>
	 <p>*            4x540x540x320x320 --> 先裁切（540x540） 后压缩（320x320）等比操作 --先裁切后压缩</p>
	 <p>*            5x100x100x400x400 --> 根据图片坐标裁切图片 起点坐标：（100x100） 终点坐标 ：（400x400）--裁切</p>
	 <p>*            6x100x100x540x540 --> 根据图片坐标和指定宽高裁切图片  起点坐标：（100x100） 裁切宽高（540x540）--裁切</p>
	 <p>*            7x0x0x540x540x300x300 --> 先根据坐标裁切,再压缩,再裁切  起点坐标:（0x0）裁切宽高：（300x300）压缩宽高：（540x540）--先裁切后压缩</p>
	 <p>*            8x0x0x500x500x200x200 --> 先根据坐标裁切,再压缩 起点坐标：（100x100） 终点坐标 ：（500x500）压缩宽高（200x200） --先裁切后压缩</p>
	 <p>* 			 9x100x100 --> 头像简单处理</p>
	 <p>*            10x540x540x320x320 --> 先压缩（540x540）后裁剪 (320x320) 完全符合规格 --先压缩后裁切</p>
	 <p>*            11x540x540x320x320 --> 先裁切（540x540） 后压缩（320x320）完全符合规格 --先裁切后压缩</p>
	
	</div>
</body>
</html>
