<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="page-header position-relative">
		<h1>
			我的图片
		</h1>
	</div>
	<div class="row-fluid">
		<ul class="thumbnails">
		<#list imgs as img>
			<#if (img_index+1)%4==1>
		</ul>
	</div>
	<div class="row-fluid">
		<ul class="thumbnails">
			</#if>
			<li class="span3">
				<div class="thumbnail">
					<h4 data-pk="${img.id}" data-name="title" data-type="text">${img.title!?html}</h4>
					<span class="label label-warning date">${img.date?string('yyyy-MM-dd')}</span>
					<img src="http://${qiniuBk}.u.qiniudn.com/${img.qiniuKey}">
					<p data-pk="${img.id}" data-name="description" data-type="textarea">${img.description!?html}</p>
				</div>
			</li>
		</#list>
		</ul>
	</div>
</div>
<script type="text/javascript">
$('h4,p', '#page-content .thumbnail').editable({
	url:'${ContextPath}/picture/update',
	emptytext:'未设置',
	send:'always'
});
</script>