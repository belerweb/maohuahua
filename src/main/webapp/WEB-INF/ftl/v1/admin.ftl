<#include "header.ftl">
<#assign Title='猫画画' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'用户管理',
	'url':'/admin/user/list'
}] />
<#include "sidebar_admin.ftl">
<div id="main-content" class="clearfix">
	<div id="page-content" class="clearfix">
		<div class="alert alert-success text-center" style="padding:100px 10px;">
		亲爱的${User.nickname!}，欢迎来到猫画画。猫画画，用心纪录你画画的每一天！
		</div>
	</div>
</div>
<script type="text/javascript">
var PageContext = {
	init: function(){
	}
};
</script>
<#include "footer.ftl">