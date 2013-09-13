<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div class="clearfix" id="page-content">
	<div class="page-header position-relative">
		<h1>
			个人设置
			<small>
				<i class="icon-double-angle-right"></i>
				我的资料
			</small>
		</h1>
	</div>
	<div class="row-fluid form-horizontal">
			<div class="control-group">
				<label class="control-label">手机号:</label>
				<div class="controls">
					<a class="editable" data-name="mobile" data-type="text">${(user.mobile!'')?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">电子邮件:</label>
				<div class="controls">
					<a class="editable" data-name="email" data-type="text">${(user.email!'')?html}</a>
				</div>
			</div>
			<div class="form-actions">
				<button type="button" class="btn btn-info" data-action="toggle">
					<i class="icon-edit bigger-110"></i> 修改
				</button>
			</div>
	</div>
</div>
<script type="text/javascript">
$('#page-content .editable').editable({
	disabled : true,
	emptytext : '还没有设置哦！',
	send : 'always',
	url : '${ContextPath}/user/settings'
});
$('#page-content button[data-action=toggle]').click(function(){
	$('#page-content .editable').editable('toggleDisabled');
	var clicked = $(this).data('clicked')||0;
	if (clicked%2==0) {
		$(this).removeClass('btn-info').addClass('btn-success').html('<i class="icon-ok bigger-110"></i> 完成');
	} else {
		$(this).removeClass('btn-success').addClass('btn-info').html('<i class="icon-edit bigger-110"></i> 修改');
	}
	$(this).data('clicked', clicked+1);
});
</script>
