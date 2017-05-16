<%@ include file="../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctxStatic}/css/pop.css">
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/global.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
    <style>
        .container>p{padding:0 0 15px 10px;border-bottom:1px solid #ddd;}
        .container>p span{color:#666;margin-right:15px;}
        .container>p input{width:257px;}
        .sh-type label { padding-left: 20px;cursor: pointer;position: relative;margin-right: 18px;
        }
        .sh-type label:before{content:'';position:absolute;width:13px;height:13px;
            left:0;top:2px;background:url(${ctxStatic}/images/icon.png) no-repeat -15px -34px;}
        .sh-type .checked:before{background-position: 0 -34px;}
        section{padding-left:10px;border-bottom:1px solid #ddd;}
        section input[type=checkbox]{visibility: hidden;}
        section input[type=checkbox]:checked+label:before{background-position:0 0;}
        section label{display:inline-block;cursor: pointer;position:relative;padding-bottom:12px;color:#555;padding-left:7px;}
        section label:before{content:'';position:absolute;width:12px;height:12px;background:url(${ctxStatic}/images/pop-icon.png) no-repeat -28px 0;top:3px;left:-13px;}
        h3{font-size:15px;color:#19BE9D;margin:15px 0;font-weight:500;}
        footer{padding-left:10px;}
    </style>
</head>
<body>
<main class="container">
    <p>
        <input id="id" value="${role.id}" hidden>
        <span>角色名称</span>
        <input id="name" type="text" placeholder="输入角色名称，角色名称不能重复" value="${role.name}">
    </p>
    <section>
        <h3>操作权限</h3>
        <p>
            <input type="checkbox" id="selectAll">
            <label for="selectAll">全选</label>
        </p>
        <c:forEach items="${menuList}" var="menu" varStatus="i">
            <p>
                <input type="checkbox" id="c${menu.id}" class="menuid" value="${menu.id}" flag="c${menu.parentId}"
                       <c:if test="${gukeer:containsString(menuId,menu.id)}">checked</c:if>>
                <label for="c${menu.id}" class="checked">
                    ${menu.remarks}
                </label>
            </p>
        </c:forEach>
    </section>
    <footer>
        <h3>数据范围</h3>
        <div class="sh-type">
            <label for="sh-1" <c:if test="${role.permissionRange == 0}">class="checked"</c:if>>
                <input type="radio" class="range" id="sh-1" hidden="hidden" value="0" <c:if test="${role.permissionRange == 0}">checked</c:if> />
                <span>全校学生</span>
            </label>
            <label for="sh-2" <c:if test="${role.permissionRange == 1}">class="checked"</c:if>>
                <input type="radio" class="range" id="sh-2" value="1" hidden="hidden" <c:if test="${role.permissionRange == 1}">checked</c:if>  />
                <span>本年级学生</span>
            </label>
            <label for="sh-3" <c:if test="${role.permissionRange == 2}">class="checked"</c:if>>
                <input type="radio" class="range" id="sh-3" value="2" hidden="hidden" <c:if test="${role.permissionRange == 2}">checked</c:if>  />
                <span>本班学生</span>
            </label>
        </div>
    </footer>
</main>
<script>
//    $('input[type=checkbox]').click(function(){
//        console.log(this.checked)
//    })


var flag = true;
    function doSubmit(){
//        if(flag == false){
//            return true;
//        }
//        flag = false;
//        var index = layer.load();
        //-------------以上原先有--------------
        var name = $('#name').val();
        if(name == null || name == ''){
            layer.msg('请填写角色名称');
//            return false;
            return ;
        }
        var menuTemp = "";
        $('input:checkbox[class=menuid]:checked').each(function (i) {
            if (0 == i) {
                menuTemp = $(this).val();
            } else {
                menuTemp += ("," + $(this).val());
            }
        });
        if(menuTemp == null || menuTemp == ''){
            layer.msg('请选择操作权限');
//            return false;
            return;
        }
        var range = $('label[class=checked] input').val();
        var roleId = $('#id').val();
        $.post('${ctx}/manage/role/save',{
            roleName:name,
            menuId:menuTemp,
            range:range,
            roleId:roleId
        },function (res) {
//            layer.close(index);
//            flag = true;
            layer.msg(res.msg);
            setTimeout(function () {
                window.parent.location.reload(true);
            },1000);
        })
    }

    $('#selectAll').click(function(){
        var checkBox=$('input[id^=c]');
//        console.log(checkBox)
        if(this.checked){
            for(var i=0;i<checkBox.length;i++){
                checkBox[i].checked=true;
            }
        }else{
            for(var i=0;i<checkBox.length;i++){
                checkBox[i].checked=false;
            }
        }
    })

    $(".menuid").click(function () {
        var check = $(this).attr('flag');
        if( check != 'c0'){
//            console.log($("input[id="+check+"]"))
            $("input[id="+check+"]")[0].checked=true;
        } else {
            var _flag = $(this).attr('id');
            var che = $("input[flag="+_flag+"]");
            for( var i=0;i<che.length;i++) {
                che[i].checked=false;
            }

        }
    })
</script>
</body>
</html>