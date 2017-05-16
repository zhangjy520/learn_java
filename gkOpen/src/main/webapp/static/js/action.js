

$(function(){
    $('.document-grade-1>li>a').click(function(){
        if($(this).next().css('display')=='block'){
            $(this).next().slideUp(150);
        }else{
            $(this).next().slideDown(150);
        }
    });
    if($('.document-grade-2>li>a').className=='active'){
        //$('.document-grade-2').show();
    }
    //打开登录界面
    $('span.login').click(function(){
        $('.mask-bg').fadeIn(300);
        $('.mask-content input[type=text]').focus();
    })
    //关闭登陆界面
    $('.closeWindow').click(function(){
        $('.mask-bg').fadeOut(300);
    })


//    记住我选框
    $('.checkbox').click(function(){
        var checkbox=document.getElementById('rememberme');
        if(checkbox.checked){
            $(this).addClass('checked');
        }else{
            $(this).removeClass('checked');
        }
    })



    //首页动画效果
    //$('.pro-1').animate({marginLeft:'-80px',opacity:'.5'},500)
})












