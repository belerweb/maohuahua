<div id="sidebar" class="fixed">
	<div id="sidebar-shortcuts">
		<div id="sidebar-shortcuts-large">
			<#if User.roles?seq_contains('ROLE_ADMIN')>
			<a class="btn btn-small btn-danger" href="${ContextPath}/admin" title="后台管理">
				<i class="icon-cogs"></i>
			</a>
			</#if>
			<a class="btn btn-small btn-warning" href="http://${site.domains[0]}" title="我的网站">
				<i class="icon-globe"></i>
			</a>
		</div>
	</div>
	<#include "sidebar_common.ftl">
</div>