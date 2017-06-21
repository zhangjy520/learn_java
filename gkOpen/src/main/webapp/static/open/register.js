/**
 * Created by LL on 2016/12/7.
 */

var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

function toEmailCheck() {
    // $("#nextStop").style.cssText='background:darkgray;border:1px solid #54ab37';
    // var div = document.getElementById("nextStop");
    // div.style.setProperty('background:darkgray');

    var userType = $('.gkradio input[name="loginUser.userType"]:checked ').val();
    var regist_email = document.getElementById("regist_email").value;
    var regist_password = document.getElementById("regist_password").value;
    //是否同意开发者协议
    if (!$("#agreement")[0].checked) {
        webToast("请先同意《教育云开放平台开放平台开发者协议》后再注册", "top", 2300);
        return false;
    }
    //邮箱格式验证
    if (regist_email == "") {
        webToast("电子邮件不能为空", "top", 2300);
        // $("#nextStop").attr("disabled", false);
        return false;
    } else {
        reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
        if (!reg.test(regist_email)) {
            webToast("电子邮件格式不正确", "top", 2300);
            // $("#nextStop").attr("disabled", false);
            return false;
        }
    }
    if(regist_password == ""){
        webToast("密码不能为空", "top", 2300);
        // $("#nextStop").attr("disabled", false);
        return false;
    }else{
        if (regist_password.length<6) {
            webToast("请输入六位以上密码", "top", 2300);
            // $("#nextStop").attr("disabled", false);
            return false;
        }
    }

    // $("#nextStop").attr("disabled", true);
    webToast("正在发送邮件到您的邮箱中，请稍后", "top", 2300);
    $.post(postPath + "/register/doregister", {
        userType: userType,
        username: regist_email,
        password: regist_password
    }, function (data) {
        if (data.code == 0) {
            /*alert(data.msg);*/
            webToast(data.msg, "top", 2000);
            window.location.replace(postPath + data.data);
        } else {
            // $("#nextStop").attr("disabled", false);
            webToast(data.msg, "top", 2300);
            window.location.replace(postPath + data.data);
        }
    });
}

function sendCode(sendCodeBtn) {
    var wait = 60;
    if ($('#phone').val() != '') {
        var regBox = {
            regMobile: /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
        }
        var mobile = document.getElementById("phone").value;
        var username = document.getElementById("usernameEmail").innerHTML;
        var mflag = regBox.regMobile.test(mobile);
        if (!mflag) {
            wait = 0;
            webToast("请输入正确的手机号", "top", 2300);
            return false;
        } else {
            $.post(postPath + "/shortMessage", {
                mobile: mobile,
                username: username
            }, function (data) {
                if (data.code == 0) {
                } else if (data.code == 1) {
                    alert(data.msg);
                }
            });
        }
    } else {
        wait = 0;
        webToast("请先输入手机号", "top", 2300);
        return false;
    }
    time(sendCodeBtn);
    function time(o) {
        var t = 0;
        if (wait == 0) {
            o.style.border = '1px solid #54ab37';
            o.style.color = '#54ab37';
            o.style.background = '#fff';
            o.removeAttribute("disabled");
            if (t == 0) {
                o.value = "免费获证码";
            }
            o.value = "重新发送";
            wait = 60;
            t++;
        } else {
            o.setAttribute("disabled", "disabled");
            o.value = "验证码...(" + wait + ")";
            o.style.border = 'none';
            o.style.color = '#fff';
            o.style.background = '#ddd';
            wait--;
            setTimeout(function () {
                    time(o)
                },
                1000)
        }
    };
}
//保存详细信息
function saveinfo() {
    var code = $("#codeInput").val();
    var username = document.getElementById("usernameEmail").innerHTML;
    var licence_num = $("#licence_num").val();
    var userType = $(".userType").val();
    var cmbAdress = $("#address").val();

    //详细地址
    if (cmbAdress == "" || cmbAdress == null) {
        webToast("详细地址不能为空", "top", 2300);
        return false;
    }

    //验证码
    var codeInput = $("#codeInput").val();
    if (codeInput == ""){
        webToast("验证码不能为空", "top", 2300);
        $("#nextStop").attr("disabled", false);
        return false;
    }
    if (userType == 0) {
        var name = $("#name").val();
        if (name == "") {
            webToast("姓名不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }

        var identityCard = $("#identityCard").val();
        if (identityCard == "") {
            webToast("身份证不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }else{
            var identity = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
            identity.test(identityCard);
        }

        var wm3 = $("#tosql3").val();
        if (wm3 == "") {
            webToast("身份证照片或扫描件不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }
        var companyName = $("#companyName").val();
        if (companyName == "") {
            webToast("公司名称不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }
        var wm4 = $("#tosql4").val();
        if (wm4 == "") {
            webToast("作品展示图片不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }
        var wm5 = $("#tosql5").val();
        if (wm5 == "") {
            webToast("开发者资质证书不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }


    } else {
        var company_phoneNumber = $("#company_phoneNumber").val();
        var r = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
        alert(r.test(company_phoneNumber));
        r.test(company_phoneNumber);
        var licence_num = $("#licence_num").val();


        if (licence_num == "") {
            webToast("营业执照号码不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }else{
            isValidBusCode(licence_num);
        }
        var licence_site = $("#licence_site").val();
        if (licence_site == ""){
            webToast("营业执照地址不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }
        var wm1 = $("#wm1").attr("src");
        if (wm1 == ""){
            webToast("营业执照副本不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }

        var corporate_name = $("#corporate_name").val();
        if (corporate_name == ""){
            webToast("法人姓名不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }

        var corporate_identity = $("#corporate_identity").val();
        if (corporate_identity == ""){
            webToast("法人身份证号码不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }

        var wm2 = $("#wm2").attr("src");
        if (wm2 == ""){
            webToast("身份证照片或扫描件不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }

        var name = $("#name").val();
        if (name == ""){
            webToast("联系人姓名不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }
        var phone = $("#phone").val();
        if (phone == ""){
            webToast("联系人手机号码不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }

        var codeInput = $("#codeInput").val();
        if (codeInput == ""){
            webToast("验证码不能为空", "top", 2300);
            $("#nextStop").attr("disabled", false);
            return false;
        }

    }
    //营业执照js验证


    function isValidBusCode(busCode) {
        return true;
        var ret = false;
        if (busCode.length == 15) {
            var sum = 0;
            var s = [];
            var p = [];
            var a = [];
            var m = 10;
            p[0] = m;
            for (var i = 0; i < busCode.length; i++) {
                a[i] = parseInt(busCode.substring(i, i + 1), m);
                s[i] = (p[i] % (m + 1)) + a[i];
                if (0 == s[i] % m) {
                    p[i + 1] = 10 * 2;
                } else {
                    p[i + 1] = (s[i] % m) * 2;
                }
            }
            if (1 == (s[14] % m)) {
                //营业执照编号正确!
                //alert("营业执照编号正确!");
                ret = true;
            } else {
                //营业执照编号错误!
                ret = false;
                //alert("营业执照编号错误!");
            }
        } else if ("" == busCode) {
            ret = true;
        }
        return ret;
    }


    $.post(postPath + "/register/judgeCode", {
            code: code,
            username: username
        }, function (data) {
            if (data.code == 0) {
                /*webToast("提交成功请耐心等待审核", "top", 2300);*/
                webToast("提交成功请耐心等待审核", "top", 2300);
                setTimeout(function () {
                        $("#save-detail").submit();
                        // if (data == "false") {
                        //     webToast("提交成功请耐心等待审核", "top", 2300);
                        // }
                    },
                    2300)
            } else {
                webToast("验证码不正确", "top", 2300);
            }
        }
    );
}
















