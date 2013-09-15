<#assign ContextPath=springMacroRequestContext.getContextPath() />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${site.title!'猫画画'}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="${site.description!?html}">
<meta name="keywords" content="${(site.keywords![])?join('')}">
<meta name="author" content="${user.nickname!'猫画画'}">
<link href="${ContextPath}/assets/v2/css/bootstrap.css" rel="stylesheet">
<link href="${ContextPath}/assets/v2/css/font-awesome.css" rel="stylesheet">
<link href="${ContextPath}/assets/v2/css/application.css" rel="stylesheet">
<!--[if IE 7]>
	<link href="${ContextPath}/assets/v2/css/font-awesome-ie7.min.css" rel="stylesheet">
<![endif]-->
<!--[if lt IE 9]>
	<script src="${ContextPath}/assets/v2/js/html5shiv.js"></script>
<![endif]-->
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="brand" href="${ContextPath}/">${site.name!'猫画画'}</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href="${ContextPath}/">首页</a></li>
					<li><a href="${ContextPath}/login.html">注册/登录</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div id="content">
	<#list imgs as img>
		<div class="boxportfolio4">
			<div class="boxcontainer">
				<img src="http://maohuahua.u.qiniudn.com/${img.qiniuKey}" alt="${img.title!}">
				<div class="roll">
					<div class="wrapcaption">
						<a href="singleproject.html"><i class="icon-link captionicons"></i></a>
					</div>
				</div>
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
<script src="${ContextPath}/assets/v2/js/jquery.js" type="text/javascript"></script>
<script src="${ContextPath}/assets/v2/js/bootstrap.js" type="text/javascript"></script>
<script src="${ContextPath}/assets/v2/js/twitter-bootstrap-hover-dropdown.js" type="text/javascript"></script>
<script src="${ContextPath}/assets/v2/js/jquery.isotope.js" type="text/javascript"></script>
<script src="${ContextPath}/assets/v2/js/application.js" type="text/javascript"></script>
</body>
</html>