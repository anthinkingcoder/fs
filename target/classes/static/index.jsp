<%@ page contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <%@ include file="/v3/pmodule/eta/etaH5/common/head.jsp" %>
    <link rel="stylesheet" href="<hh:css value='/info/version3/eta/etaH5/styles/swiper.min.css'/>"/>
    <link rel="stylesheet" href="<hh:css value='/info/version3/eta/etaH5/styles/etaswipe.css'/>"/>
    <link rel="stylesheet" href="<hh:css value='/info/version3/eta/etaH5/styles/pages/eta_home.css'/>"/>
    <link rel="stylesheet" href="<hh:css value='/info/version3/eta/etaH5/styles/up_down_load.css'/>"/>
</head>

<script type="text/javascript">

    shareUrl="<hh:url value='/OPTSMKM4/WAP4021401.dow'/>?viewCode=json"; //分享签名
    var title="中国移动${CMPAY_TICKET}商城";
    var content="逢7就有优惠，快来围观吧！";
    var img_share="${DOMAIN}/info/version3/eta/etaH5/images/share_home.jpg";
    var new_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc3b7846562a4325b&redirect_uri=${DOMAIN}/weixin/cm_wallet_web/web/wx/cb/pres/OutterAuthRedirect?outterRedirectUrl=${DOMAIN}/mkmweb/produce_wap_home.xhtml?channel_flag=wetchat&response_type=code&scope=snsapi_userinfo&state=hebaoState#wechat_redirect";

    window.urls={
        querySale:"<hh:url value='/OMKMWEB3/MWB0103004.dow'/>?viewCode=html",// 进营销活动页
        ticket_query:"<hh:url value='/OMKMWEB1/MWB0101000.dow'/>?viewCode=json",//查余额
        sendchekno:"<hh:url value='/OPTSURM1/PTS0011803.dow'/>?viewCode=json",// 获取短信验证码
        mblmessagelog:"<hh:url value='/OPTSURM1/PTS0011804.dow'/>?viewCode=json",// 登录交易
        produce_wap_qry:"<hh:url value='/OMKMWEB1/MWB0101002.dow'/>?viewCode=json",// 查商品
        produce_city_choose:"<hh:url value='/OMKMWEB1/MWB0101004.dow'/>?viewCode=html",// 切换城市
        wap_produce_dis:"<hh:url value='/OMKMWEB1/MWB0101050.dow'/>?viewCode=json",// 写入定位
        produce_login:"<hh:url value='/OMKMWEB1/MWB0101007.dow'/>?viewCode=html",// 登录页
        produce_user_detail:"<hh:url value='/OMKMWEB1/MWB0101005.dow'/>?viewCode=html",// 账户页
        wap_produce_detail:"<hh:url value='/OMKMWEB1/MWB0101020.dow'/>?viewCode=html",// 商城页
        etacounpon_detail:"<hh:url value='/OMKMWEB1/MWB0101009.dow'/>?viewCode=html",// 明细页
        eta_exchange:"<hh:url value='/OMKMWEB1/MWB0101022.dow'/>?viewCode=html", // 积分兑换页
        wap_produce_reqData:"<hh:url value='/OMKMWEB1/MWB0101026.dow'/>?viewCode=json",// 单点登录
        query_nearmerc:"<hh:url value='/OMKMWEB1/MWB0101056.dow'/>", // 查询附近商家数量
        query_nearmerc_type:"<hh:url value='/OMKMWEB1/MWB0101071.dow'/>", // 附近商家页
        merc_market:"<hh:url value='/OMKMWEB1/MWB9011006.dow'/>", // 单点登陆京东商城
        dollar_index:"<hh:url value='/OMKMWEB3/MWB0103000.dow'/>?viewCode=html", // 一元购页
        prev_pre:"<hh:url value='/OMKMWEB1/MWB0101082.dow'/>?viewCode=json" // 分省广告位
    };


    var DIS_CITY = '${ETF.DIS_CITY}',  // 定位城市
        CHO_CITY = '${ETF.CHO_CITY}';  // 选择城市

</script>


<body>
<div class="outer1">
    <!-- header -->
    <header class="header_home" id="homeheader">
        <div class="areacity">
            <button class="selectcity"  id="selectCity"  onclick="selectcity()">
                <c:choose>
                    <c:when test="${empty ETF.DIS_CITY}">
                        <input type="hidden" value="" id="cityNum">
                    </c:when>
                    <c:when test="${empty ETF.CHO_CITY}">
                        <input type="hidden" value="" id="cityNum">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" value="${ETF.DIS_CITY}" id="cityNum">
                    </c:otherwise>
                </c:choose>
                <span class="cityshow" id="cityShow">未定位</span>
                <i class="bw-ico city_img"></i>
            </button>
        </div>
        <h4 class="page-title">${BRAND_KEYWORD}爱客联盟</h4>

        <div class="logined">
            <div  id="logining" >
                <i class="bw-ico packet-ico packet_icon"></i>
                <button class="loginbtn" id="loginBtn"> 登录 </button>
            </div>
            <div id='loginResult' class="hide">
                <a  href="https://uat.cmpay.com/gmeweb/hbtree_index.xhtml">
                    <i class="bw-ico person-ico person_icon"></i>
                    <span class='moneyfont'>我的</span>
                </a>

            </div>

        </div>
        <!-- <a class="mine-font">我的</a> -->
    </header>
    <!-- header end -->


    <div class="main">
        <!-- login status  -->
        <div id="mcaLogin" class="bgorg hide" style="height: 0.8rem;line-height: 0.8rem;">
            <c:if test="${ETF.LOG_FLG == '0'}">
                <i class="bw-ico packet-ico packet_icon"></i>
                <button class="loginbtn" id="loginBtn"> 登录 </button>
            </c:if>
            <c:if test="${ETF.LOG_FLG == '1'}">
                <a href="https://uat.cmpay.com/gmeweb/hbtree_index.xhtml" >
                    <i class="bw-ico person-ico person_icon"></i>
                    <span class='moneyfont'>我的</span>
                </a>
            </c:if>
        </div>
        <!-- login status end  -->

        <c:if test="${ETF.PRE_REC_NUM != ''}" >
            <c:forEach items="${ETF.PRE_REC}" var="PRE_REC" varStatus="status" begin="0" end="0" step="1">
                <div class="shadow-box special clearfix">
                    <p class="special-title hide" style="color: ${PRE_REC.PRE_COL}"><i class="bw-ico special-ico" style="background-image: url(/info/rrsfiles/imgs/${PRE_REC.PRE_LOGO})"></i> ${PRE_REC.PRE_TIL} <c:if test="${PRE_REC.URL_FLG  == '1'}"><a href="${PRE_REC.PRE_URL}" class="special-more">更多</a></c:if></p>
                    <a class="big-img" data-val="${status.index}0" onclick="mercLogin(this)" data-loginflg="${PRE_REC.MAIN_LOG}" data-mecrid="${PRE_REC.MAIN_MERC}"  data-url="${PRE_REC.MAIN_URL}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.MAIN_PIC})"></a>
                    <a class="small-img" data-val="${status.index}1" onclick="mercLogin(this)" data-loginflg="${PRE_REC.AUX_LOG1}" data-mecrid="${PRE_REC.AUX_MERC1}"  data-url="${PRE_REC.AUX_URL1}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.AUX_PIC1})"></a>
                    <a class="small-img" data-val="${status.index}2" onclick="mercLogin(this)" data-loginflg="${PRE_REC.AUX_LOG2}" data-mecrid="${PRE_REC.AUX_MERC2}"  data-url="${PRE_REC.AUX_URL2}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.AUX_PIC2})"></a>
                    <a class="small-img" data-val="${status.index}3" onclick="mercLogin(this)" data-loginflg="${PRE_REC.AUX_LOG3}" data-mecrid="${PRE_REC.AUX_MERC3}"  data-url="${PRE_REC.AUX_URL3}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.AUX_PIC3})"></a>
                </div>

            </c:forEach>

        </c:if>

        <!-- active broadcast adv  -->
        <section id="broadcastAct" class="hide" >
            <div class="screenMask" style="display:block;"></div>
            <div class="broadcast">
                <img class="broadcast-img" id="brdcastImg" >
                <i class="close-ico" id="brdcastClose"></i>
            </div>
        </section>

        <!-- banner -->
        <!-- <div class="banner">
            <div class="swiper-container">
                <div class="swiper-wrapper" data-recnum="${ETF.PIC_REC_NUM}" >

                    <c:if test="${empty ETF.PIC_REC_NUM}" >
                        #include virtual='/info/wap/dzqh5/navimages.html'
                    </c:if>
                    <c:if test="${ETF.PIC_REC_NUM != ''}" >
                        <c:forEach items="${ETF.PIC_REC}" var="PIC_REC" varStatus="status">
                            <div class="swiper-slide">
                                <a href="${PIC_REC.ACT_URL}">
                                   <img class="swiper-lazy" data-src='/info/rrsfiles/imgs/${PIC_REC.PIC_URL}'>
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>


                </div>
                <div class="swiper-pagination"></div>
            </div>
        </div> -->

        <!--  小图标导航 -->
        <c:if test="${ETF.NAV_REC_NUM != ''}" >
            <div id="navContent" class="shadow-box sel_nav clearfix">
                <c:forEach items="${ETF.NAV_REC}" var="NAV_REC" varStatus="status">
                    <a onclick="mercLogin2(this)" data-url="${NAV_REC.NAV_URL}" data-mecrid="${NAV_REC.NAV_URL}" data-loginflg="${NAV_REC.LOGIN_FLG}"  data-val="${status.count}" style="background-image:url(/info/rrsfiles/imgs/${NAV_REC.NAV_PIC})"></a>

                </c:forEach>
            </div>
        </c:if>

        <!-- 附近商家   -->
        <section class="arrond-mc-box hide" id="arroundJump">
            <p class="amc-txt">附近商家</p>
            <p class="amc-entry">搜罗周边商户挑好货，和包券为你买单</p>
            <i class="bw-ico around-ad-ico"></i>
        </section>

        <c:if test="${ETF.PRE_REC_NUM != ''}" >
            <c:forEach items="${ETF.PRE_REC}" var="PRE_REC" varStatus="status"  begin="1" end="${ETF.PRE_REC_NUM}" step="1">
                <div class="shadow-box special clearfix">
                    <p class="special-title" style="color: ${PRE_REC.PRE_COL}"><i class="bw-ico special-ico" style="background-image: url(/info/rrsfiles/imgs/${PRE_REC.PRE_LOGO})"></i> ${PRE_REC.PRE_TIL} <c:if test="${PRE_REC.URL_FLG  == '1'}"><a href="${PRE_REC.PRE_URL}" class="special-more">更多</a></c:if></p>
                    <a class="big-img" data-val="${status.index}0" onclick="mercLogin(this)" data-loginflg="${PRE_REC.MAIN_LOG}" data-mecrid="${PRE_REC.MAIN_MERC}"  data-url="${PRE_REC.MAIN_URL}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.MAIN_PIC})"></a>
                    <a class="small-img" data-val="${status.index}1" onclick="mercLogin(this)" data-loginflg="${PRE_REC.AUX_LOG1}" data-mecrid="${PRE_REC.AUX_MERC1}"  data-url="${PRE_REC.AUX_URL1}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.AUX_PIC1})"></a>
                    <a class="small-img" data-val="${status.index}2" onclick="mercLogin(this)" data-loginflg="${PRE_REC.AUX_LOG2}" data-mecrid="${PRE_REC.AUX_MERC2}"  data-url="${PRE_REC.AUX_URL2}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.AUX_PIC2})"></a>
                    <a class="small-img" data-val="${status.index}3" onclick="mercLogin(this)" data-loginflg="${PRE_REC.AUX_LOG3}" data-mecrid="${PRE_REC.AUX_MERC3}"  data-url="${PRE_REC.AUX_URL3}" style="background-image:url(/info/rrsfiles/imgs/${PRE_REC.AUX_PIC3})"></a>
                </div>

            </c:forEach>

        </c:if>



        <!-- 商品类型切换 -->
        <nav class="nav-type " id="navType">
            <i class="bw-ico handpick-ico"></i>
            <a name="featured" class="nav-type-txt">精选商品</a>
            <!-- <a name="cash" class="fonthui">优惠专区</a>
            <i class="bw-ico nav-notices" id="navNoticesBtn"></i> -->

        </nav>

        <!-- return top -->
        <a href="#" class="top-return hide" id="returnTop">
            <i class="bw-ico retrun-top-ico"></i>
        </a>

        <!-- prod list -->
        <article class="inner">
            <div class="prod-list clearfix" id="foodsList">

            </div>
        </article>

    </div>
</div>

<input type="hidden" value="${ETF.DIS_CITY}" id="DIS_CITY">
<input type="hidden" value="${ETF.CHO_CITY}" id="CHO_CITY">
<input type="hidden" value="${ETF.LOG_FLG}" id="LOG_FLG">
<input type="hidden" value="${ETF.EXG_FLG}" id="exg_flg">
<input type="hidden" value="${ETF.PIC_URL}" id="PIC_URL"> <!-- 宣传图 -->
<input type="hidden" value="${ETF.ACT_URL}" id="ACT_URL"> <!-- 宣传活动地址 -->
<input type="hidden" value="${ETF.PIC_NM}" id="PIC_NM"> <!-- 宣传活动主题 -->
<input type="hidden" value="${ETF.CHANNEL_FLAG}" id="channelFlag">
<input type="hidden" value="${ETF.TOKEN}" id="TOKEN">
<input type="hidden" value="${ETF.MERC_ID}" id="MERC_ID">

<script src="/info/version3/eta/etaH5/scripts/common/city.js"></script>
<%@ include file="/v3/pmodule/eta/etaH5/common/footer.jsp" %>
<script src="/info/version3/eta/etaH5/scripts/common/head.load.min.js"></script>
<script src="/info/version3/eta/etaH5/scripts/libs/juicer-min.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="/info/version3/eta/etaH5/scripts/wxPermissionsHome.js"></script>
<script src="/info/version3/eta/etaH5/scripts/libs/swiper.min.js"></script>
<script src="/info/version3/eta/etaH5/scripts/libs/dropload.min.js"></script>
<script src="/info/version3/eta/etaH5/scripts/common/singlelogin.js"></script>
<script src="/info/version3/eta/etaH5/scripts/common/getPositionInfo.js"></script>
<script src="/info/version3/eta/etaH5/scripts/getGoodsList.js"></script>
<script src="/info/version3/eta/etaH5/scripts/pages/eta_home.js"></script>
<script>

    $(function() {
        if (HEADER_FLG1 || HEADER_FLG2) {
            $('#mcaLogin').removeClass('hide');
        }
        if (!DIS_CITY && !CHO_CITY) {
            Geolocal.PointsGet().always(function() {
                CITY_CD = getcityId(Geolocal.getCityfun());
                if (Geolocal.getCityfun()){
                    $('#cityShow').html(Geolocal.getCityfun());
                } else {
                    $('#cityShow').html('未定位');
                }
                store.session('LONGITUDE', Geolocal.longitudefun());
                store.session('LATITUDE', Geolocal.latitudefun());
                store.session('CITYCD', CITY_CD);
                store.session('PROVCD', getprodId(CITY_CD));
                $('#cityNum').val(CITY_CD);
                wirte_city(CITY_CD);
                dropLoadInit();
                gerArroundMecr();
            });
        } else {
            CITY_CD = CHO_CITY ? CHO_CITY : DIS_CITY;
            store.session('CITY_CD', CITY_CD);
            store.session('PROVCD', getprodId(CITY_CD));
            $('#cityNum').val(CITY_CD);
            $('#cityShow').html(getcityName(CITY_CD));
            dropLoadInit();
            gerArroundMecr();
        }
    });
    juicer.set({
        'tag::interpolateOpen': '$[',
        'tag::interpolateClose': ']',
        'tag::noneencodeOpen': '$$[',
        'tag::noneencodeClose': ']'
    });
</script>



<script id="foods-list" type="text/template">
    {@each REC as list}
    <div class="pro">
        <a data-onlcnl="$[list.ONL_CNL]" data-disprice="$[list.DISCOUNT_PRICE]" data-fixedamt="$[list.FIXED_AMT]" data-flg="$[list.DISCOUNT_FLG]" data-disimg="$[list.PRE_PIC]" data-provno="$[list.PROD_NO]" data-goodname="$[list.MERC_TYP]" data-mercname="$[list.MERC_NM]" data-typenum="$[list.TYPE_NUM]" data-istomerc="$[list.IS_TO_MERC]" data-mercprodurl="$[list.MERC_PROD_URL]" onclick="onlcnlclick(this)">
            <div class="project">
                <img class="pro-img" src="/info/rrsfiles/imgs/$[list.PROD_PIC]"/>
                {@if list.PRE_PIC}
                <img class="sale_img" src="/info/rrsfiles/imgs/$[list.PRE_PIC]">
                {@/if}

            </div>
            <p class="pro-title">
                {@if list.MERC_TYP.length > 13}
                $[list.MERC_TYP.substring(0, 13)]...
                {@else}
                $[list.MERC_TYP]
                {@/if}
            </p>
            <p class="pro-money">
                <span class="money-sign">￥</span>$[list.FIXED_AMT]
                {@if list.TYPE_NUM !== '1'} 起 {@/if}
            </p>

            <p class="pro-money mh40">
                {@if list.DISCOUNT_FLG === '0'}
                <span class="prod-discount"></span>
                {@else}
                <span class="prod-discount">￥$[list.PROD_PRICE]</span>
                {@/if}
            </p>


        </a>

    </div>
    {@/each}
</script>


</body>
</html>