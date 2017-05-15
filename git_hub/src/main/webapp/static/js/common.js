$(function () {
    $('.nav-left li').on('click', function () {
        $('.nav-left li').removeClass('active');
        localStorage.index_li = $('.nav-left li').index($(this));
        $('.nav-left li').eq(localStorage.index_li).addClass('active');
    });
    $('.nav-left li').eq(localStorage.index_li).addClass('active');
});