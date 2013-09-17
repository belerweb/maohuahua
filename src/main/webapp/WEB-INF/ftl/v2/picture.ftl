<#assign Title="${img.title!} -- ${site.title!'猫画画'}">
<#include 'head.ftl'>
<div class="container-fluid">
	<div class="row-fluid spacetop">
		<div class="span6">
			<img src="http://maohuahua.u.qiniudn.com/${img.qiniuKey}" alt="${img.title!}">
		</div>
		<div class="span6">
			<h4>${img.title!}</h4>
			<p>
				<b>日期</b> : ${img.date?string('yyyy-MM-dd')} | <b>用户</b> : ${owner.nickname!}
			</p>
			<p>
				${img.description!?html}
			</p>
		</div>
	</div>
</div>
<script type="text/javascript">
var Page = {
	init : function() {
	}
};
</script>
<#include 'foot.ftl'>