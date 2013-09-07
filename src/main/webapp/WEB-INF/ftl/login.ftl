<#assign ContextPath=springMacroRequestContext.getContextPath() />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>猫画画</title>

		<meta name="description" content="纪录你画画的每一天" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link href="${ContextPath}/assets/v1/css/bootstrap.css" rel="stylesheet" />
		<link href="${ContextPath}/assets/v1/css/font-awesome.css" rel="stylesheet" />

		<!--[if IE 7]>
		<link href="${ContextPath}/assets/v1/css/font-awesome-ie7.css" rel="stylesheet" />
		<![endif]-->

		<!--fonts-->
		<link href="${ContextPath}/assets/v1/css/google-fonts-opensans.css" rel="stylesheet" />

		<!--ace styles-->
		<link href="${ContextPath}/assets/v1/css/ace.css" rel="stylesheet" />
		<link href="${ContextPath}/assets/v1/css/ace-responsive.css" rel="stylesheet" />

		<!--[if lt IE 9]>
		<link href="${ContextPath}/assets/v1/css/ace-ie.css" rel="stylesheet" />
		<![endif]-->

		<link href="${ContextPath}/assets/v1/css/application.css" rel="stylesheet" />
	</head>

	<body class="login-layout">
		<div class="container-fluid" id="main-container">
			<div id="main-content">
				<div class="row-fluid">
					<div class="span12">
						<div class="login-container">
							<div class="row-fluid">
								<div class="center">
									<h1>
										<i class="icon-github green"></i>
										<span class="red">猫</span>
										<span class="white">画画</span>
									</h1>
									<h4 class="blue">纪录你画画的每一天</h4>
								</div>
							</div>

							<div class="space-6"></div>

							<div class="row-fluid">
								<div class="position-relative">
									<div id="login-box" class="visible widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header blue lighter bigger">
													<i class="icon-github-alt green"></i>
													帐号信息
												</h4>

												<div class="space-6"></div>

												<form method="post" action="${ContextPath}/login">
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input name="j_username" type="text" required="true" class="span12" placeholder="用户名/邮箱/手机号" />
																<i class="icon-user"></i>
															</span>
														</label>

														<label>
															<span class="block input-icon input-icon-right">
																<input name="j_password" type="password" required="true" class="span12" placeholder="密码" />
																<i class="icon-lock"></i>
															</span>
														</label>

														<div class="space"></div>

														<div class="row-fluid">
															<div class="span8"></div>
															<button type="submit" class="span4 btn btn-small btn-primary">
																<i class="icon-key"></i>
																登录
															</button>
														</div>
													</fieldset>
												</form>
											</div><!--/widget-main-->

											<div class="toolbar clearfix">
												<div>
													<a class="forgot-password-link" onclick="show_box('forgot-box'); return false;" href="#">
														<i class="icon-arrow-left"></i>
														忘记密码
													</a>
												</div>

												<div>
													<a class="user-signup-link" onclick="show_box('signup-box'); return false;" href="#">
														我要注册
														<i class="icon-arrow-right"></i>
													</a>
												</div>
											</div>
										</div><!--/widget-body-->
									</div><!--/login-box-->

									<div id="forgot-box" class="widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header red lighter bigger">
													<i class="icon-key"></i>
													找回密码
												</h4>

												<div class="space-6"></div>
												<p>
													输入你的邮箱或手机号
												</p>

												<form method="post" action="${ContextPath}/forgot_password">
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input name="id" type="text" required="true" class="span12" placeholder="邮箱或手机号" />
																<i class="icon-github-sign"></i>
															</span>
														</label>

														<div class="row-fluid">
															<button type="submit" class="span5 offset7 btn btn-small btn-danger" data-loading-text="请等待...">
																<i class="icon-lightbulb"></i>
																确认找回密码
															</button>
														</div>
													</fieldset>
												</form>
											</div><!--/widget-main-->

											<div class="toolbar center">
												<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
													返回登录
													<i class="icon-arrow-right"></i>
												</a>
											</div>
										</div><!--/widget-body-->
									</div><!--/forgot-box-->

									<div id="signup-box" class="widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header green lighter bigger">
													<i class="icon-group blue"></i>
													新用户注册
												</h4>

												<div class="space-6"></div>
												<p>
													输入你的邮件或手机号
												</p>

												<form method="post" action="${ContextPath}/signup">
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input name="id" type="text" required="true" class="span12" placeholder="邮件或手机号" />
																<i class="icon-github-sign"></i>
															</span>
														</label>

														<div class="space-24"></div>

														<div class="row-fluid">
															<button type="submit" class="span6 offset6 btn btn-small btn-success" data-loading-text="注册中...">
																确认注册
																<i class="icon-arrow-right icon-on-right"></i>
															</button>
														</div>
													</fieldset>
												</form>
											</div>

											<div class="toolbar center">
												<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
													<i class="icon-arrow-left"></i>
													返回登录
												</a>
											</div>
										</div><!--/widget-body-->
									</div><!--/signup-box-->
								</div><!--/position-relative-->
							</div>
						</div>
					</div><!--/span-->
				</div><!--/row-->
			</div>
		</div><!--/.fluid-container-->

		<script src="${ContextPath}/assets/v1/js/jquery.js" type="text/javascript"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.form.js" type="text/javascript"></script>
		<script src="${ContextPath}/assets/v1/js/bootstrap.js" type="text/javascript"></script>
		<script src="${ContextPath}/assets/v1/js/bootbox.js" type="text/javascript"></script>

		<script type="text/javascript">
			function show_box(id) {
			 $('.widget-box.visible').removeClass('visible');
			 $('#'+id).addClass('visible');
			}
			$('#forgot-box form, #signup-box form').submit(function() {
				$('button[type=submit]', this).button('loading');
				$(this).ajaxSubmit({
					success: function(response, statusText, xhr, form) {
						bootbox.alert('<div class="alert alert-success">' + response + '</div>', function() {
							form.resetForm();
							$('button[type=submit]', form).button('reset');
							show_box('login-box');
						});
					},
					error: function(xhr, status, error, form) {
						bootbox.alert('<div class="alert alert-error">' + xhr.responseText + '</div>', function() {
							$('button[type=submit]', form).button('reset');
						});
					}
				}); 
				return false; 
			});
		</script>
	</body>
</html>
