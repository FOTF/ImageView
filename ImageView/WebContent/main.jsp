<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>照片墙</title>

<link rel="stylesheet" href="css/lrtk.css" />
<link rel="stylesheet" href="css/uploadify.css" />
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/scripts.js"></script>
<script src="js/jquery.uploadify.js"></script>
<%-- <script src="js/select.js"></script> --%>
<%-- <script src="js/select.js"></script> --%>

<style type="text/css">
img:hover {
	-moz-transition-duration: 0.32s;
	box-shadow: 0px 0px 4px 4px rgba(100, 220, 250, 1)
	!important;
}

ul{
	border:solid 3px #ccc;
	box-shadow: 0px 0px 4px 4px rgba(100, 220, 250, 1)
	!important;
}

</style>

</head>
<body>
	<!-- 代码 开始 -->
	<div id="gallery-container">
		<ul class="items--small">
			<s:iterator value="images" id="id" status="status">
				<li class="item" style="align:left">
					<a href="#">
						<%-- <img src="readimage?imagePath=${path }"alt="" width="250" height="166"/></a> --%>
						<img src="readimage?imageType=1&imagePath=${path }"alt=""/></a>
				<input type="checkbox" value="name" id="${realName }"/><label for="${realName }">${name }</label>				
				</li>
			</s:iterator>
		</ul>

		<div class="imagesControls">
				<form>
				<div id="fileQueue"></div>
				<input type="file" name="files" id="files" /> 
				<p>  
                	<a href="javascript:$('#files').uploadify('upload')">开始上传</a>   
                	<a href="javascript:$('#files').uplaodify('cancel','*')">取消上传</a>  
            	</p> 
            	</form>
			<span class="imagesControl icon-arrow-left" id="previous"></span> 
			<span class="imagesControl icon-arrow-right" id="next"></span> 
			<span class="fs-toggle icon-fullscreen"></span>
		</div>

		<ul class="items--big">
			<s:iterator value="images" id="id" status="status">
				<li class="item--big"><a href="#"> <figure> <img
							<%-- src="readimage?imagePath=${path }" alt="" width="736" height="490"/> <figcaption class="img-caption"> --%>
							src="readimage?imageType=2&imagePath=${path }" alt=""/> <figcaption class="img-caption">
						${name } </figcaption> </figure>
				</a></li>
			</s:iterator>
		</ul>
		<div class="controls">
			<span class="control icon-arrow-left" data-direction="previous"></span>
			<span class="control icon-arrow-right" data-direction="next"></span>
			<span class="grid icon-grid"></span>
			<span class="fs-toggle icon-fullscreen"></span>
		</div>
	</div>

	<script>
	$(function() {
		$("#files").uploadify({
			'multi': false,
            'auto':false,
       	    'fileObjName' : 'files',
            'swf': 'images/uploadify.swf',
            'uploader': '<%=request.getContextPath()%>/upload/uploadify.action',
            'multi': false,
            'overrideEvents' : ['onUploadProgress'],  
            'fileTypeDesc':'支持的格式：',
            'formData': {'ctrlid' : 1},
            //允许上传的文件后缀
            'fileTypeExts':'*.xml, *.gz, *.zip',
            //上传文件的大小限制
            'fileSizeLimit':'1MB',
            //  'buttonImage':'upbutton.gif',
            //浏览按钮的宽度
            'width':'100',
            //浏览按钮的高度
            'height':'32',
            //上传数量 
            'queueSizeLimit' : 1,
            'buttonText'     : '上传文件'
	    });
     $('#gallery-container').sGallery({
        fullScreenEnabled: true
      });
	 
	 $("#next").click(function(){
		 window.location.href='imageview?number=1&currentPage=<%=request.getAttribute("currentPage")%>';
	 });
	 
	 $("#previous").click(function(){
		 window.location.href='imageview?number=-1&currentPage=<%=request.getAttribute("currentPage")%>';
		});
});
	</script>
</body>
</html>