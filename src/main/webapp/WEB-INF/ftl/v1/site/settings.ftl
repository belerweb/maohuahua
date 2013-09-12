<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div class="clearfix" id="page-content">
	<div class="page-header position-relative">
		<h1>
			我的网站
			<small>
				<i class="icon-double-angle-right"></i>
				网站设置
			</small>
		</h1>
	</div>
	<div class="row-fluid">
		<form method="post" action="${ContextPath}/site/settings" class="form-horizontal">
			<div class="control-group">
				<label class="control-label">网站名称:</label>
				<div class="controls">
					<input name="name" type="text" placeholder="网站名称" class="input-xlarge">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">网站描述:</label>
				<div class="controls">
					<textarea name="description" placeholder="网站描述" class="input-xlarge"></textarea>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-info">
					<i class="icon-ok bigger-110"></i> 确定
				</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
$('#page-content form').submit(function(){
	$(this).ajaxSubmit({
		success: function(response, status, xhr, form) {
			bootbox.alert('<div class="alert alert-success">保存成功！</div>');
		},
		error: function(xhr, status, response, form) {
			bootbox.alert('<div class="alert alert-error">保存失败！</div>');
		}
	});
	return false;
});
</script>
