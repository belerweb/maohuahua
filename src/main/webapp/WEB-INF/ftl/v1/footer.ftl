		</div><!--/.fluid-container#main-container-->
		<script type="text/javascript" src="${ContextPath}/assets/v1/js/jquery.js"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v1/js/bootstrap.js"></script>
		<script type="text/javascript">
		$(function(){
			document.title = '${Title!''}';
			var page = window.PageContext||{};
			if (page.init && $.isFunction(page.init)) {
				page.init();
			}
		});
		</script>
	</body>
</html>