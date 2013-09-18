<#include 'head.ftl'>
<div class="container-fluid">
	<div id="content">
	<#list imgs as img>
		<div class="boxportfolio4">
			<div class="boxcontainer">
				<a href="${ContextPath}/p/${img.id}.html" target="_blank"><img src="http://${qiniuBk}.u.qiniudn.com/${img.qiniuKey}" alt="${img.title!}"></a>
				<h1><a href="javascript:void(0);">${img.title!}</a></h1>
				<p>
				</p>
			</div>
		</div>
	</#list>
	</div>
</div>
<script type="text/javascript">
var Page = {
	init : function() {
		$('#content').imagesLoaded(function() {
			$(this).isotope({
				filter : '*',
				animationOptions : {
					duration : 750,
					easing : 'linear',
					queue : false,
				}
			})
		});
	}
};
</script>
<#include 'foot.ftl'>