$(function(){
    //�����˵��л�
    $('nav .container>ul>li').hover(function(){
        $(this).children('ul').show();
    },function(){
        $(this).children('ul').slideUp(200);
    })
    //�ֻ�����tab��ǩ�л�
    $('.sh-tabs a').click(function(){
        $('.sh-tabs a').removeClass('active');
        $(this).addClass('active');
        var tabItem=$('#sh-lose div[id*=sh-lose]');
        //console.log(tabItem)
        var data=$(this).attr('data');
        for(var i=0;i<tabItem.length;i++){
            if(tabItem[i].id==data){
                $(tabItem[i]).show();
                $(tabItem[i]).siblings('.sh-tab-item').hide();
            }
        }
    })


    //���ֻ�ѡ��
    $('.sh-type label').click(function(){
        $(this).addClass('checked');
        //console.log($(this).children('input')[0].checked)
        //$(this).children('input')[0].checked=true;
        $(this).siblings('label').removeClass('checked');
    })

    //  ��Ŀѡ��
  /*  $('.select-items li').click(function(){
        $(this).addClass('active');
        $(this).siblings().removeClass('active')
    })*/

    //������
    $('input.search').focus(function () {
        console.log(11)
        $(this).next().removeClass('search-item');
        $(this).next().addClass('search-cancel')
        $('i.search-cancel').click(function(){
            $(this).siblings('input.search').val('');
            $(this).addClass('search-item');
            $(this).removeClass('search-cancel');
        })
    })

})
























