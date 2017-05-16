<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>开发者注册</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/less.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
    <script src="${ctx}/static/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/js/respond.min.js"></script>
</head>
<style>
    h4,h5{
        font-weight: bold;
    }
    h4{
        margin-top: 30px !important;
    }
    p{
        margin: 20px 0 !important;
    }
</style>
<body>
<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp"%>
        <section class="col-xs-9">
            <h1>开发者协议</h1>
            <div>
                <h4>一、重要须知</h4>
                <p>本协议是合作方（以下称合作方或您）与教育云开放平台之间关于您提交的相关应用与教育云开放平台（以下统称为开放平台）连接并使用开放平台提供的各种服务的法律协议。</p>
                <p>开放平台在此特别提醒，您欲使用开放平台服务，必须事先认真审阅本服务条款，包括免除或者限制开放平台责任的免责条款及对您权利限制的条款。请您审阅并决定接受或不接受本服务条款（未成年人审阅时应得到法定监护人的陪同）。如您不同意本服务条款及/或开放平台随时对本服务条款的修改，您应不使用或主动取消开放平台提供的服务。否则，您的任何对开放平台的相关服务的使用行为将被视为您对本服务条款的完全接受，包括接受开放平台对服务条款随时做出的任何修改。</p>
                <p>如果您选择提交开发者账号申请，即表示您同意接受本协议各项服务条款的约束。如果您不同意本服务条款，则不能获得使用本服务的权利。您若违反本服务条款规定，开放平台有权随时中止或终止您开放平台服务的使用资格并保留追究相关法律责任的权利。</p>
            </div>
            <div>
                <h4>二、术语</h4>
                <h5>如无特别说明，下列术语在本协议中的含义为：</h5>
                <p>1、开放平台：指由开放平台开发并开放的API接口，合作方通过该接口，将其应用接入开放平台。通过开放平台服务将实现用户在合作方应用的信息、操作同步显示到开放平台上，从而实现信息分享同步。</p>
                <p>2、合作方：参与“应用接入”合作的主体，包括但不限于法人。</p>
                <p>3、教育云平台用户：指所有在合作方应用登录的用户，以下简称平台用户或用户。</p>
                <p>4、第三方：指开放平台、合作方以外的公民、法人。</p>
            </div>
            <div>
                <h4>三、合作方的权利和义务</h4>
                <p>1、合作方在接入开放平台之前，确保合作应用已取得了运营所需的相关法规规定的全部资质文件（包括但不限于《ICP运营许可证》、《营业执照》、《网络文化经营许可证》、《BBS许可证》等），且已按政府主管部门的规定完成了相应的备案手续，且应在申请书中附加加盖公司公章的全部资质文件及法人身份证复印件的电子扫描版，交与开放平台留存；合作方需签署本协议书允许与开放平台在开放平台服务上进行合作，合作方需提供负责开放平台的负责人的个人资料，包括但不限于姓名、身份证号码、联系方式（如通信地址、电子邮件等），是真实的、完整的、合法有效的。如该负责人变更的，合作方应当在变更前15日致电通知开放平台，合作方未能提前15日致电通知开放平台的，由此产生的任何损失均由合作方承担，开放平台有权中止或终止本协议。</p>
                <p>2、合作方确保：在开放平台的使用过程中的内容真实、准确、完整、安全；不含有任何欺诈成份；其应用发布的内容不侵犯任何第三者对该应用享有的合法权益，包括但不限于著作权、商标权、专利权等；不会违反任何法律、法规、条例或规章。对于任何因使用合作方应用造成的法律责任，由合作方单独承担，与开放平台无关。</p>
                <p>3、合作方应向开放平台提供合作方与用户的权利义务协议，由开放平台在连接授权页面中提示用户。</p>
                <p>4、合作方保证在其应用上公布的内容不得含有任何违反国家法律法规政策的信息，包括但不限于：</p>
                <ul>
                    <li>a)反对宪法所确定的基本原则的；</li>
                    <li>b)危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；</li>
                    <li>c)损害国家荣誉和利益的；</li>
                    <li>d)煽动民族仇恨、民族歧视，破坏民族团结的；</li>
                    <li>e)破坏国家宗教政策，宣扬邪教和封建迷信的；</li>
                    <li>f)散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；</li>
                    <li>g)侮辱或者诽谤他人，侵害他人合法权益的；</li>
                    <li>h)含有法律、行政法规禁止的其他内容的。</li>
                </ul>
                <p>5、合作方在与教育云平台用户达成的交易及相关协议时，应自行独立解决两者间发生的纠纷，与开放平台无关，开放平台无需承担任何责任。</p>
                <p>6、用户可授权合作方将其在合作方应用的动态信息同步到开放平台，同步内容同时可在开放平台以及开放平台的其它产品中进行传播，如果用户在合作方应用的动态信息非首先或非原始产生于合作方应用时，合作方不得将此类信息同步到开放平台。</p>
                <p>7、合作方如向开放平台进行通知，应当通过开放平台对外正式公布的通信地址、电子邮件地址等联系信息进行送达。</p>
                <p>8、在合作过程中，合作方应当安排专门人员负责有关合作工作，以保证约定的合作工作不间断地进行。</p>
                <p>9、开放平台提供的公开开放平台API接口为开放平台版权所有，合作方不得存在对其进行盗用、反编译、恶意攻击及其它任何危害开放平台及其用户安全与权益的行为。</p>
                <p>10、开放平台过程中，版权归开放平台所有。合作方收到kpi后，应妥善保存并注意防止泄漏；禁止合作方公开开放平台向其提供的kpi信息，禁止合作方将kpi泄漏给非放平台外的第三方，任何第三方也不能存储和调用非经开放平台合法授权的开放平台接口。</p>
                <p>11、合作方自行承担开放平台合作业务相关的服务器及带宽费用，并提供支持，以保证用户的通过开放平台合作方应用进行正常访问。</p>
                <p>12、合作方不得以任何方式收集、索取或以其他方式获取用户的开放平台帐号、密码、好友关系链或其他身份验证凭据等相关信息。</p>
                <p>13、对于由于合作方违反相关法律法规或侵犯任何人的权益造成的损失，均与开放平台无关，若造成开放平台或他人损失的，合作方应当承担全部责任。</p>
            </div>
            <div>
                <h4>四、开放平台的权利和义务</h4>
                <p>1、开放平台享有对以下内容完全的、不可分割的所有权及知识产权：</p>
                <ul>
                    <li>a)开放平台及其所有元素和组件，包括但不限于所有内容、数据、技术、软件、代码、用户界面以及与其相关的任何衍生作品；</li>
                    <li>b)合作方向开放平台提供的开放平台服务相关的任何信息及反馈；</li>
                    <li>c)其它开放平台的应用信息；</li>
                    <li>d)其他依法应该由开放平台享有权益的内容。</li>
                </ul>
                <p>2、在合作方提交申请之后，开放平台有权对该合作方予以审核，开放平台有权依据其自身判断决定是否同意与其进行开放平台连接。</p>
                <p>3、开放平台有权不定时对合作方进行审核，如发现合作方违反本协议的约定、违反任何法律法规或开放平台根据自己的独立判断认为合作方不符合开放平台要求或平台用户的需求的，开放平台有权在不通知的情况下对其予以删除或屏蔽，但开放平台的审核，并不减轻合作方应该承担的任何责任，由于合作方违反相关法规造成开放平台或他人损失的，合作方应当承担全部责任。</p>
                <p>4、开放平台有权知悉合作方和平台用户的注册数据及交易信息，如发现注册数据或交易行为中存在任何问题或怀疑，可要求合作方和平台用户改正，或者直接做出删除、屏蔽等处理。</p>
                <p>5、开放平台有权对用户从合作方应用同步到开放平台的内容进行审核，并选择是否允许其显示在开放平台。</p>
                <p>6、经过用户授权且通过开放平台审核同步到开放平台的内容可在开放平台以及开放平台的其它产品中传播。</p>
                <p>7、由于合作方应用存在海量信息等特殊性，开放平台并不实际控制合作方提供的链接，故对于任何因直接或间接使用合作方应用信息而造成的损失，开放平台均不承担责任。</p>
                <p>8、开放平台不能控制网络信息的真实性、安全性或合法性，以及达成交易各方履行其义务的能力，合作方和教育云平台用户应自行谨慎判断信息的真实性、安全性和合法性。</p>
                <p>9、如发生下列任一情形，开放平台有权以普通或非专业人员的知识水平标准对合作方或教育云平台用户提供的相关内容或实施的行为进行判别，如认为这些内容或行为违法或不合理，开放平台有权删除相关内容，或终止或暂停对该合作方或平台用户提供服务：</p>
                <ul>
                    <li>a)他方对某个合作方的内容或行为持有异议并通知开放平台；</li>
                    <li>b)他方向开放平台告知开放平台用户有违法或不合理的内容。</li>
                </ul>
                <p>10、如发现合作方或教育云平台用户违约，开放平台可不经过事先通知合作方或教育云平台用户而直接删除相关信息，或终止、暂停为合作方或教育云平台用户提供服务。</p>
                <p>11、开放平台与合作方之间若有联合运营或推广的需要，具体授权将另行单独协商确定。</p>
                <p>12、对开放平台的合作不收取任何费用，但开放平台保留今后收取费用的权利。</p>
                <p>对开放平台的合作不收取任何费用，但教育云平台保留今后收取费用的权利。</p>
            </div>
            <div>
                <h4>五、本协议下服务的暂停或终止</h4>
                <p>1、合作方应按照教育云平台的要求提交真实准确的相关资料，如教育云平台发现合作方的注册信息不真实或不准确的，教育云平台有权暂停或终止向其提供本协议下服务,由此产生的一切法律责任由合作方自行承担。</p>
                <p>2、如合作方书面通知教育云平台不接受经教育云平台新的服务条款的，教育云平台有权随时暂停或终止向其提供本协议下服务。</p>
                <p>3、在合作方违反本协议规定或任何法规时，教育云平台有权随时暂停或终止向该合作方提供服务。如该合作方后续再直接或间接或以他人名义注册登录开放平台并申请使用开放平台服务的，教育云平台有权直接单方面暂停或终止提供本协议下服务。</p>
                <p>4、如本协议服务终止，教育云平台有权选择是否为合作方保留合作方账号中与开放平台服务相关的任何信息，也可选择是否将信息转发给合作方，也有权选择是否保存合作方的数据及以前的记录。</p>
                <p>5、如本协议服务终止的，教育云平台有权删除在服务终止前合作方尚未完成的信息。</p>
            </div>
            <div>
                <h4>六、隐私相关条款</h4>
                <p>1、教育云平台重视对合作方及教育云平台用户隐私的保护，保护隐私是教育云平台的一项基本政策。您提供的登记资料及教育云平台保留的有关的若干其他个人资料将受到中国有关隐私的法律和教育云平台《隐私保护声明》之规范。您应当遵守中国有关隐私的法律和
                    教育云平台的隐私权政策。您在使用本服务的同时即是对《隐私保护声明》及各种规范、规则等所有条款的接受和遵守，这些服务条款可由教育云平台随时更新，且毋须另行通知。服务条款一旦发生变更,教育云平台将在网页上公布修改内容。修改后的服务条款一旦在网页上公布即有效代替原来的服务条款。</p>
                <p>2、在合作方使用开放平台时，合作方允许教育云平台可自动接收并记录合作方浏览器上的服务器数值，包括但不限于IP地址等数据、合作方和教育云平台用户要求取用的网页记录及各种记录等。</p>
                <p>3、教育云平台不允许任何人以任何手段收集、编辑、出售或者无偿传播合作方和教育云平台用户的个人信息。一经发现合作方实施上述行为，教育云平台有权立即终止与该合作方的服务协议，终止为其提供任何服务。</p>
                <p>4、为服务合作方和教育云平台用户的目的，教育云平台可使用合作方的信息，包括但不限于向合作方和教育云平台用户发出产品、服务或商业信息等，或者与教育云平台合作伙伴共享信息以便他们向合作方和教育云平台用户发送有关其产品、服务或商业信息等。</p>
                <p>5、如发生下列任一情况，教育云平台有权对合作方的信息予以披露：</p>
                <ul>
                    <li>a)经相关合作方同意披露的；</li>
                    <li>b)根据法律的有关规定，或行政、司法机构的要求，向第三方或者行政、司法机构披露；</li>
                    <li>c)如果合作方出现违反中国有关法律、法规、规章、政策的，需要向第三方披露；</li>
                    <li>d)其它平台为合作方和教育云平台用户服务目的而认为合适披露的。</li>
                </ul>
            </div>
            <div>
                <h4>七、知识产权条款</h4>
                <p>1、尊重知识产权是合作方和教育云平台用户应尽的义务，如有违反，合作方和教育云平台用户应承担法律责任。</p>
                <p>2、用户在合作方应用发表的被同步到教育云平台的所有内容，包括但不限于文字、图片、应用架构、应用画面的安排、网页设计，合作方应自行负责其内容的合法性、准确性等责任。</p>
            </div>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>


</body>
</html>