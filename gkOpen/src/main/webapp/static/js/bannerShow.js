(function($) {
    $.fn.bannerShow = function(options) {
        return this.each(function() {
            var _lubo = jQuery('.bs-content');

            var _box = jQuery('.bs-box');

            var _this = jQuery(this);
            //




            var luboHei = _box.height();

            var Over = 'mouseover';

            var Out = 'mouseout';

            var Click = 'click';

            var Li = "li";

            var _cirBox = '.cir_box';

            var cirOn = 'cir_on';

            var _cirOn = '.cir_on';

            var cirlen = _box.children(Li).length;
            //圆点的数量  图片的数量




            var luboTime = 3000;
            //轮播时间




            var switchTime = 800;
            //图片切换时间




            cir();

            //根据图片的数量来生成圆点




            function cir() {

                _lubo.append('<ul class="cir_box"></ul>');

                var cir_box = jQuery('.cir_box');

                for (var i = 0; i < cirlen; i++) {

                    cir_box.append('<li style="" value="' + i + '"></li>');

                }

                var cir_dss = cir_box.width();

                cir_box.css({

                    left: '50%',

                    marginLeft: -cir_dss / 2,

                    bottom: '4%'


                });

                cir_box.children(Li).eq(0).addClass(cirOn);


            }

            //定时器




            int = setInterval(clock, luboTime);

            function clock() {

                var cir_box = jQuery(_cirBox);

                var onLen = jQuery(_cirOn).val();

                _box.children(Li).eq(onLen).stop(false, false).animate({

                        opacity: 0


                    },
                    switchTime);

                if (onLen == cirlen - 1) {

                    onLen = -1;


                }

                _box.children(Li).eq(onLen + 1).stop(false, false).animate({

                        opacity: 1


                    },
                    switchTime);

                cir_box.children(Li).eq(onLen + 1).addClass(cirOn).siblings().removeClass(cirOn);


            }

            // 鼠标在图片上 关闭定时器




            _lubo.bind(Over,
                function() {

                    clearTimeout(int);


                });

            _lubo.bind(Out,
                function() {

                    int = setInterval(clock, luboTime);


                });

            //鼠标划过圆点 切换图片




            jQuery(_cirBox).children(Li).bind(Over,
                function() {

                    var inde = jQuery(this).index();

                    jQuery(this).addClass(cirOn).siblings().removeClass(cirOn);

                    _box.children(Li).stop(false, false).animate({

                            opacity: 0


                        },
                        switchTime);

                    _box.children(Li).eq(inde).stop(false, false).animate({

                            opacity: 1


                        },
                        switchTime);


                });



        });


    }


})(jQuery);
