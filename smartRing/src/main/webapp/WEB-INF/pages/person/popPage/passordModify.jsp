<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        *{box-sizing: border-box}
        .container{padding:20px;width:350px;margin:0 auto;height:100%;}
        .container>div{position:relative;margin:20px 0;text-align:center;}
        .container>div span{text-align:left;display:inline-block;width:80px;font-size:12px;color:#676767;}
        .container>div input{border:1px solid #ddd;outline: none;padding-left:5px;width:205px;height:27px;color:#999;}
        .container i{background:url(${ctxStatic}/images/hide-pwd.png) no-repeat center center;cursor:pointer;display:inline-block;position:absolute;right:19px;top:13px;width:12px;height:8px;}
        /*.container i.hide{background:url(images/hide-pwd.png) no-repeat center center;}*/
        .container i.show{background: url(${ctxStatic}/images/show-pwd.png) no-repeat center center;}
    </style>
</head>
<body>
<div class="container">
    <div>
        <span>原密码</span>
        <input type="password"/>
        <i></i>
    </div>
    <div>
        <span>新密码</span>
        <input type="password"/>
        <i></i>
    </div>
    <div>
        <span>确认新密码</span>
        <input type="password"/>
        <i></i>
    </div>
</div>
<script>
    var eyes=document.getElementsByTagName('i');
    for(var i=0;i<eyes.length;i++){
        eyes[i].addEventListener('click',function(){
            var myClass=this.className;

            if(!myClass){
                this.previousElementSibling.setAttribute('type','text');
                this.className='show';
            }else{
                this.previousElementSibling.setAttribute('type','password');
                this.className='';
            }

        })
    }
</script>
</body>
</html>