<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			用户列表
		</h3>
		<table id="main-list-table" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>昵称</th>
					<th>电子邮件</th>
					<th class="center">手机号</th>
					<th class="center">注册时间</th>
					<th class="center">管理员？</th>
					<th class="center" style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as user>
				<tr>
					<td class="center"><input type="checkbox" value="${user.id}"><span class="lbl"></span></td>
					<td>${user.nickname!}</td>
					<td>${user.email!}</td>
					<td class="center">${user.mobile!}</td>
					<td class="center">${user.created?string('yyyy-MM-dd HH:mm:ss')}</td>
					<td class="center">
						<label>
							<input name="admin" type="checkbox" <#if user.roles?seq_contains('ROLE_ADMIN')>checked="checked"</#if> class="ace-switch ace-switch-5"
								data-id="${user.id}" data-action="toggle-property">
							<span class="lbl"></span>
						</label>
					</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" class="btn btn-mini btn-info" title="编辑"
								data-action="edit" data-id="${user.id!}">
								<i class="icon-edit bigger-120"></i>
							</button>
							<button type="button" class="btn btn-mini btn-danger" title="删除"
								data-action="delete" data-id="${user.id!}" data-name="${user.nickname!}">
								<i class="icon-trash bigger-120"></i>
							</button>
						</div>
					</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
$('#main-list-table').dataTable({
	iDeferLoading: ${result.total},
	iDisplayStart: ${result.start},
	iDisplayLength: ${result.pageSize},
	bLengthChange: false,
	bFilter: false,
	bServerSide: true,
	fnServerData: function (sSource, aoData, fnCallback, oSettings) {
		var p = {};
		$.each(aoData, function(i, obj){
			p[obj.name] = obj.value;
		});
		var q = {};
		q.page = p.iDisplayStart / p.iDisplayLength;
		App.go('#main-content', '${ContextPath}/admin/user/list?' + $.param(q));
	}
});
</script>