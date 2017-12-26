	<div class="container_12 footer">	
		<div class="gird_12 " >
			<h4><?php _e('友情链接:'); ?></h4>
			<ul class="link">
				<?php wp_list_bookmarks('orderby=id&categorize=0&category=&title_li='); ?>
			</ul>			
		</div>
		<div class="clear"></div>
		<div class="grid_12 copyright">			
			
			Copyright &copy; 2011-<?php echo date('Y'); ?> <a href="<?php bloginfo('url'); ?>" title="<?php bloginfo('name'); ?>"><?php bloginfo('name'); ?></a>.Theme By DanXs
			<a href="<?php echo get_option('xs_map'); ?>" title="网站地图" target="_blank">网站地图</a>
			<?php echo stripslashes(get_option('xs_track_code')); ?>
			<div class="copyright">声明：本站所有内容均来源于互联网，本站只做演示，请支持正版！</div>
		</div>
		<div class="clear"></div>			
		<?php wp_footer(); ?>
		</div>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" ></script>	
	<?php if ( is_singular() ){ ?>	

	<script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/comments-ajax.js"></script>
	<?php } ?>
	<!-- 返回顶部 -->
	<script type="text/javascript">
	(function() {
		var $backToTopTxt = "返回顶部", $backToTopEle = $('<a class="backToTop"><span></span></a>').appendTo($("body"))
			.attr("title", $backToTopTxt).click(function() {
				$("html, body").animate({ scrollTop: 0 }, 120);
		}), $backToTopFun = function() {
			var st = $(document).scrollTop(), winh = $(window).height();
			(st > 0)? $backToTopEle.show(): $backToTopEle.hide();	
			//IE6下的定位
			if (!window.XMLHttpRequest) {
				$backToTopEle.css("top", st + winh - 166);	
			}
		};
		$(window).bind("scroll", $backToTopFun);
		$(function() { $backToTopFun(); });
	})();
	</script>

	<script type="text/javascript">
	/*
	 对页面上的字体增大、缩小、恢复原始大小
	 需要在html页面中定义三个元素
	 元素的class分别为 resetFont、increaseFont、decreaseFont
	 在本文件的JQuery事件中分别定义了三个元素的click事件来实现增大、缩小、恢复原始大小
	 */
	$(function () {
	    //取得字体大小，在html标记下定义了font-size
	    var originalFontSize = $(".entry p").css("font-size");
	    //恢复默认字体大小
	    $(".resetFont").click(function () {
	        $(".entry p").css("font-size", originalFontSize);
	        //JavaScript不向下执行
	        return false;
	    });
	 
	    //加大字体,某个元素的class定义为increaseFont
	    $(".increaseFont").click(function () {
	        //取得当前字体大小 后缀px,pt,pc
	        var currentFontSize = $(".entry p").css("font-size");
	        //取得当前字体大小，parseFloat()转为float类型去除后缀
	        var currentFontSizeNumber = parseFloat(currentFontSize);
	        //新定义的字体大小
	        var newFontSize = currentFontSizeNumber * 1.2;
	        //重写样式表
	        $(".entry p").css("font-size", newFontSize);
	        //JavaScript不向下执行
	        return false;
	    });
	 
	    //减小字体，某个元素的class定义为decreaseFont
	    $(".decreaseFont").click(function () {
	        //取得当前字体大小 后缀px,pt,pc
	        var currentFontSize = $(".entry p").css("font-size");
	        //取得当前字体大小，parseFloat()转为float类型去除后缀
	        var currentFontSizeNumber = parseFloat(currentFontSize);
	        //重新定义字体大小
	        var newFontSize = currentFontSizeNumber * 0.8;
	        //重写样式表
	        $(".entry p").css("font-size", newFontSize);
	        //JavaScript不向下执行
	        return false;
	    });
	});
	</script>
</body>
</html>