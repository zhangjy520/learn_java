<div class="footer_content">
	&#169; <?php print(date(Y)); ?> <?php bloginfo('name'); ?>&nbsp;&nbsp;<?php wp_loginout(); wp_register('  ', ''); ?><br/>
	<p id="back-top"><a href="#top"><span>&and;</span></a></p>
</div> 
<?php wp_footer(); ?>
<script type="text/javascript">
$(document).ready(function(){
	$("#back-top").hide();
	$(function () {
		$(window).scroll(function () {
			if ($(this).scrollTop() > 100) {
				$('#back-top').fadeIn();
			} else {
				$('#back-top').fadeOut();
			}
		});
		$('#back-top a').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 800);
			return false;
		});
	});
});
</script>
</body>
</html>