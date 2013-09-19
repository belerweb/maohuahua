<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="page-header position-relative">
		<h1>
			图片管理
			<small>
				<i class="icon-double-angle-right"></i>
				上传图片
			</small>
		</h1>
	</div>
	<div class="row-fluid">
		<div id="picture-upload-wizard" class="row-fluid hidden">
			<ul class="wizard-steps">
				<li data-target="#picture-upload－step1" class="active">
					<span class="step">1</span>
					<span class="title">选择日期</span>
				</li>
				<li data-target="#picture-upload－step2">
					<span class="step">2</span>
					<span class="title">上传图片</span>
				</li>
				<li data-target="#picture-upload－step3">
					<span class="step">3</span>
					<span class="title">补充图片信息</span>
				</li>
			</ul>
		</div>
		<hr />
		<div class="step-content row-fluid position-relative">
			<div class="step-pane active" id="picture-upload－step1">
				<div class="span3"> </div>
				<div class="span6 text-center">
					<div class="row-fluid">
						<div class="alert alert-success">
							<strong>为图片选择一个准确的日期，让您的画画历程更加真实！</strong>
						</div>
					</div>
					<div class="row-fluid input-append">
						<input name="date" type="text" class="span10 input-mask-date date-picker" placeholder="xxxx年xx月xx日">
						<span class="btn btn-small">
							<i class="icon-calendar bigger-110"></i>
							选择日期
						</span>
					</div>
				</div>
				<div class="span3"> </div>
			</div>
			<div class="step-pane" id="picture-upload－step2">
				<div id="picture-uploader"> </div>
			</div>
			<div class="step-pane" id="picture-upload－step3">
				<div class="alert alert-info text-center">
					<strong>图片上方的文字是图片名称，图片下方的文字是图片详细说明，点击一下就可以编辑修改。修改完成后自动保存。</strong>
				</div>
			</div>
		</div>
		<hr />
		<div class="row-fluid wizard-actions">
			<button type="button" class="btn btn-success btn-next" data-last="完成">下一步</button>
		</div>
	</div>
</div>
<script type="text/javascript">
(function(){
	var uploaded = false;
	var files = [];
	var uploaderInit = function() {
		$('#picture-uploader').pluploadQueue({
			runtimes:'html5,flash,html4',
			flash_swf_url : '${ContextPath}/assets/v1/img/plupload.flash.swf',
			multi_selection:true,
			filters:[{title : '图片', extensions : 'jpg,gif,png'}],
			url:'http://up.qiniu.com/',
			preinit:{
				Init: function(up, info) {
				},
				UploadFile: function(up, file) {
					var param;
					$.ajax({
						async:false,
						url:'${ContextPath}/picture/upload/token',
						data:{
							date: $('#page-content .date-picker').datepicker('getDate').format('yyyyMMdd'),
							name: file.name
						}
					}).done(function(data, status, xhr){
						param = data;
					}).fail(function(xhr, status, data){
						bootbox.alert('<div class="alert alert-error">' + data + '</div>');
						throw data;
					});
					up.settings.multipart_params = param;
				}
			},
			init:{
				FilesAdded: function(up, files) {
				},
				FilesRemoved: function(up, files) {
				},
				QueueChanged: function(up) {
				},
				Refresh: function(up) {
				},
				StateChanged: function(up) {
					if (up.state==plupload.STARTED) {
						uploaded = true;
					}
				},
				UploadProgress: function(up, file) {
				},
				FileUploaded: function(up, file, info) {
					files.push($.parseJSON(info.response));
				},
				ChunkUploaded: function(up, file, info) {
				},
				Error: function(up, args) {
				}
			}
		});
	};

	$('#picture-upload-wizard').ace_wizard().on('change' , function(e, info){
		if(info.step == 1) {
			var dateText = $('#page-content .date-picker').val();
			if (dateText.length == 0) {
				bootbox.alert('<div class="alert alert-error">亲，你得为你得图片选择一个日期。</div>');
				return false;
			}
			var date = $('#page-content .date-picker').datepicker('getDate');
			if (date.format('yyyyMMdd') > ${.now?string('yyyyMMdd')}) {
				bootbox.alert('<div class="alert alert-error">亲，你选择了'+dateText+'，你未来人吗？。</div>');
				return false;
			}
			uploaderInit();
		}
		if(info.step == 2) {
			var uploader = $('#picture-uploader').pluploadQueue();
			if (uploader.state==plupload.STARTED) { // uploading
				bootbox.alert('<div class="alert alert-error">亲，文件正在上传中，请耐心等待！</div>');
				return false;
			}
			if (uploader.state==plupload.STOPPED && !uploaded) {
				bootbox.alert('<div class="alert alert-error">亲，你还没有上传任何文件！</div>');
				return false;
			}
			if (files.length==0) { // all files failed
				bootbox.alert('<div class="alert alert-error">亲，所有文件都传输失败，重新选择文件并上传吧！</div>', function(){
					uploader.destroy();
					$('#picture-uploader').empty();
					uploaded = false;
					uploaderInit();
				});
				return false;
			}
			$.each(files, function(i, img) {
				if(i%4==0) {
					$('#picture-upload－step3').append('<div class="row-fluid"><ul class="thumbnails"></ul></div>');
				}
				var li = $('<li class="span3"><div class="thumbnail"></div></li>');
				var thumbnail = li.find('.thumbnail');
				var title = $('<h4></h4>');
				title.text(img.title);
				title.attr('data-pk', img.id);
				title.attr('data-name', 'title');
				title.attr('data-type', 'text');
				thumbnail.append(title);
				thumbnail.append('<img src="http://${qiniuBk}.u.qiniudn.com/'+img.qiniuKey+'">');
				var description = $('<p></p>');
				description.attr('data-pk', img.id);
				description.attr('data-name', 'description');
				description.attr('data-type', 'textarea');
				thumbnail.append(description);
				$('#picture-upload－step3 .thumbnails:last').append(li);
			});
			$('h4,p', '#picture-upload－step3 .thumbnail').editable({
				url:'${ContextPath}/picture/update',
				emptytext:'未设置',
				send:'always'
			});
		}
	}).on('changed', function(e) {
	}).on('finished', function(e) {
		bootbox.alert('<div class="alert alert-success">成功上传'+files.length+'张图片。</div>', function(){
			var date = $('#page-content .date-picker').datepicker('getDate');
			App.go('#main-content', '${ContextPath}/picture?date=' + date.format('yyyy-MM-dd'));
		});
	});

	$('#page-content .date-picker').mask("9999年99月99日").datepicker({
		language:'zh-CN',
		autoclose:true
	});
	$('#page-content .date-picker').next().click(function(){
		$(this).prev().datepicker('show');
	});
})();
</script>