(window.webpackJsonpsyzs=window.webpackJsonpsyzs||[]).push([["component-api"],{"3HBR":function(e,n,t){"use strict";t("Hd7B"),t("wz3H"),t("q1tI")},"4rqw":function(e,n,t){"use strict";t.d(n,"a",(function(){return p}));var o=t("/UZO"),i=t("Uh3X"),r=t("ohty"),c={head:{cmd:"syzssource",userInfo:{guid:"",qimei:"",imei:"",uuid:"",evilScore:0},authInfo:{businessId:"AuthName",signature:"",appId:"0",A2:[],uin:0,qqOpenId:"",weixinAccessToken:"",weixinOpenId:"",nonce:94166378,UserIpv4:""},hostAppInfo:{pkgName:"",versionName:"",versionCode:0,appKey:"",userId:"",channel:"",appChannelId:"",scene:""},deviceInfo:{mid:"",imei1:"",imsi1:"",imei2:"",imsi2:"",macAddr:"",xResolution:0,yResolution:0,brand:"",mode:"",manufacturer:"",product:"",platform:0,phone:""},netInfo:{netType:0,ipv4:"",ipv6:"",wifiSsid:"",wifiBssid:""},httpHeadInfo:{},protocol:0,milliSecond:0,lbsInfo:{latitude:0,longitude:0,accuracy:0,cityCode:0,province:"",city:"",district:"",street:""},qua:"",whiteBoard:0},body:{Bid:"syzs",num:0,param:'{"sourceid":5}',listS:{keyword:[""],pkgname:[""],supplyId:[""]},listI:{pagesize:[15]}}},a=t("ve/k"),u=function(e,n,t,o){return new(t||(t=Promise))((function(i,r){function c(e){try{u(o.next(e))}catch(e){r(e)}}function a(e){try{u(o.throw(e))}catch(e){r(e)}}function u(e){var n;e.done?i(e.value):(n=e.value,n instanceof t?n:new t((function(e){e(n)}))).then(c,a)}u((o=o.apply(e,n||[])).next())}))},s=function(e,n){var t,o,i,r,c={label:0,sent:function(){if(1&i[0])throw i[1];return i[1]},trys:[],ops:[]};return r={next:a(0),throw:a(1),return:a(2)},"function"===typeof Symbol&&(r[Symbol.iterator]=function(){return this}),r;function a(r){return function(a){return function(r){if(t)throw new TypeError("Generator is already executing.");for(;c;)try{if(t=1,o&&(i=2&r[0]?o.return:r[0]?o.throw||((i=o.return)&&i.call(o),0):o.next)&&!(i=i.call(o,r[1])).done)return i;switch(o=0,i&&(r=[2&r[0],i.value]),r[0]){case 0:case 1:i=r;break;case 4:return c.label++,{value:r[1],done:!1};case 5:c.label++,o=r[1],r=[0];continue;case 7:r=c.ops.pop(),c.trys.pop();continue;default:if(!(i=(i=c.trys).length>0&&i[i.length-1])&&(6===r[0]||2===r[0])){c=0;continue}if(3===r[0]&&(!i||r[1]>i[0]&&r[1]<i[3])){c.label=r[1];break}if(6===r[0]&&c.label<i[1]){c.label=i[1],i=r;break}if(i&&c.label<i[2]){c.label=i[2],c.ops.push(r);break}i[2]&&c.ops.pop(),c.trys.pop();continue}r=n.call(e,c)}catch(e){r=[6,e],o=0}finally{t=i=0}if(5&r[0])throw r[1];return{value:r[0]?r[1]:void 0,done:!0}}([r,a])}}},d="200207677";u(void 0,void 0,void 0,(function(){var e;return s(this,(function(n){switch(n.label){case 0:return[4,o.Market.QueryClientBaseInfo()];case 1:return e=n.sent(),d=e.supplyid,[2]}}))}));var l=Object(a.g)()+"/v2/syzssource",p=function(e,n){return u(void 0,void 0,void 0,(function(){var t,o,a;return s(this,(function(u){switch(u.label){case 0:return t=Array.isArray(e)?e:[e],0===e.length?[2,{}]:(o={},a=r._.merge({},c,{body:{listS:{pkgname:t,supplyId:[(n||d).toString()]},param:'{"sourceid":110}'}}),[4,Object(i.ajax)({url:l+"?params="+encodeURIComponent(a.body.param),method:"post",dataJSON:a})]);case 1:return u.sent().Queue[0].Item.forEach((function(e){o[e.Id]=JSON.parse(e.Material.Value)})),[2,o]}}))}))}},"O53+":function(e,n,t){"use strict";t.d(n,"b",(function(){return a})),t.d(n,"c",(function(){return u})),t.d(n,"a",(function(){return s}));var o=t("Uh3X"),i=t("ve/k"),r=function(){return(r=Object.assign||function(e){for(var n,t=1,o=arguments.length;t<o;t++)for(var i in n=arguments[t])Object.prototype.hasOwnProperty.call(n,i)&&(e[i]=n[i]);return e}).apply(this,arguments)},c=i.s?"apitest.livelink.qq.com":"welive.qq.com";function a(e){var n=e.loginType,t=e.openid,i=e.accessToken,r="//"+c+"/api/live/getUserInfo";return Object(o.ajax)({url:r,method:"post",dataJSON:{channelCode:"tgb",cookieString:"acctype="+n.toLowerCase()+";openid="+t+";access_token="+i+";appid=101549767"}})}function u(e){var n="//"+c+"/api/user/sub";return Object(o.ajax)({url:n,method:"post",dataJSON:r(r({},e),{updateTime:~~(Date.now()/1e3)})})}function s(e){var n="//"+c+"/api/user/subStatus";return Object(o.ajax)({url:n,method:"post",dataJSON:e})}},W0uf:function(e,n,t){"use strict";t.d(n,"a",(function(){return o})),t.d(n,"d",(function(){return f})),t.d(n,"f",(function(){return y})),t.d(n,"c",(function(){return h})),t.d(n,"e",(function(){return b})),t.d(n,"b",(function(){return v}));var o,i=t("ld/X"),r=t("/UZO"),c=t("WwPU"),a=t("wz3H"),u=t("Uh3X"),s=t("ohty"),d=t("Km+A"),l=t("ve/k"),p=function(e,n,t,o){return new(t||(t=Promise))((function(i,r){function c(e){try{u(o.next(e))}catch(e){r(e)}}function a(e){try{u(o.throw(e))}catch(e){r(e)}}function u(e){var n;e.done?i(e.value):(n=e.value,n instanceof t?n:new t((function(e){e(n)}))).then(c,a)}u((o=o.apply(e,n||[])).next())}))},m=function(e,n){var t,o,i,r,c={label:0,sent:function(){if(1&i[0])throw i[1];return i[1]},trys:[],ops:[]};return r={next:a(0),throw:a(1),return:a(2)},"function"===typeof Symbol&&(r[Symbol.iterator]=function(){return this}),r;function a(r){return function(a){return function(r){if(t)throw new TypeError("Generator is already executing.");for(;c;)try{if(t=1,o&&(i=2&r[0]?o.return:r[0]?o.throw||((i=o.return)&&i.call(o),0):o.next)&&!(i=i.call(o,r[1])).done)return i;switch(o=0,i&&(r=[2&r[0],i.value]),r[0]){case 0:case 1:i=r;break;case 4:return c.label++,{value:r[1],done:!1};case 5:c.label++,o=r[1],r=[0];continue;case 7:r=c.ops.pop(),c.trys.pop();continue;default:if(!(i=(i=c.trys).length>0&&i[i.length-1])&&(6===r[0]||2===r[0])){c=0;continue}if(3===r[0]&&(!i||r[1]>i[0]&&r[1]<i[3])){c.label=r[1];break}if(6===r[0]&&c.label<i[1]){c.label=i[1],i=r;break}if(i&&c.label<i[2]){c.label=i[2],c.ops.push(r);break}i[2]&&c.ops.pop(),c.trys.pop();continue}r=n.call(e,c)}catch(e){r=[6,e],o=0}finally{t=i=0}if(5&r[0])throw r[1];return{value:r[0]?r[1]:void 0,done:!0}}([r,a])}}};!function(e){e.Login="login",e.Logout="logout"}(o||(o={}));function f(e){if(e)!function(){var e=document.getElementById("login");e||((e=document.createElement("div")).id="login",document.body.appendChild(e)),e.style.display="block";var n=new c.a({root:"login",type:"qq",onSucess:function(){return p(this,void 0,void 0,(function(){var e,t,o,c;return m(this,(function(a){switch(a.label){case 0:e=Object(s.getCookie)("openid"),t=Object(s.getCookie)("accesstoken"),o=Object(s.getCookie)("logintype"),a.label=1;case 1:return a.trys.push([1,5,,6]),[4,r.Window.GetOtherWindowInfo({name:"market"})];case 2:return(c=a.sent())&&c.browserId?[4,r.Ipc.SendMessage(c.browserId,JSON.stringify({type:"login",data:{openid:e,accesstoken:t,logintype:o}}))]:[3,4];case 3:a.sent(),a.label=4;case 4:return Object(i.f)("loginInSucc"),[3,6];case 5:return a.sent(),[3,6];case 6:return n.destroy(),document.getElementById("login").style.display="none",[2]}}))}))},onCancel:function(){document.getElementById("login").style.display="none"}});n.show()}();else{"https://s.syzs.qq.com/syzs/webapp_oversea/login2.html";r.Window.OpenPopupBrowserWindow({url:"https://s.syzs.qq.com/syzs/webapp_oversea/login2.html",width:584,height:370,modalMode:!0,resizable:!1,showTitlebar:!1,disableZoom:!0})}}function y(){return Object(a.jsonp)(Object(l.t)()+"/api/auth/openid-trans").then((function(e){return 0===e.code&&e.data&&e.data.yopenid?(Object(s.setCookie)("yopenid",e.data.yopenid,"qq.com","/",8760),Object(s.setCookie)("seckey",e.data.seckey,"qq.com","/",8760),e.data):(window.aegis.report("\u8f6c\u6362yopenid\u63a5\u53e3 error : data: "+JSON.stringify(e)),e)}))}function h(e){void 0===e&&(e=!1);var n=Object(s.getCookie)("nickname"),t=Object(s.getCookie)("headimgurl");return n&&t&&!e?(n=decodeURIComponent(n),t=decodeURIComponent(t),Promise.resolve({nickname:n,headimgurl:t})):Object(a.jsonp)(Object(l.t)()+"/api/auth/userinfo").then((function(e){var n;return 0===e.code&&e.data?(r.Ipc.BroadCastMessage(JSON.stringify({type:o.Login})),Object(s.setCookie)("nickname",encodeURIComponent(e.data.nickname),"qq.com","/",8760),Object(s.setCookie)("headimgurl",encodeURIComponent(e.data.headimgurl),"qq.com","/",8760),e.data.headimgurl||(null===(n=window.aegis)||void 0===n||n.report("get userinfo callback headimgurl is undefined, res.data: "+e.data+", Hence use default userInfo img"),Object(s.setCookie)("headimgurl",encodeURIComponent("https://i.gtimg.cn/open/appstore/imgupload/202009/2031556104_1600054054268067.png"),"qq.com","/",8760)),e.data):e}))}function b(e){void 0===e&&(e=!1),r.Market.Logout(0),r.tgbEventBus.$emit("Market.UserLogout"),r.Ipc.BroadCastMessage(JSON.stringify({type:o.Logout})),Object(d.delCookie)("nickname","qq.com","/"),Object(d.delCookie)("uin","qq.com","/"),Object(d.delCookie)("logintype","qq.com","/"),Object(d.delCookie)("skey","qq.com","/"),Object(d.delCookie)("gender","qq.com","/"),Object(d.delCookie)("headimgurl","qq.com","/"),Object(d.delCookie)("openid","qq.com","/"),Object(d.delCookie)("accesstoken","qq.com","/"),Object(d.delCookie)("refreshtoken","qq.com","/"),Object(d.delCookie)("wx_gender","qq.com","/"),Object(d.delCookie)("yopenid","qq.com","/"),Object(d.delCookie)("seckey","qq.com","/"),Object(d.delCookie)("yy_openid","qq.com","/"),Object(d.delCookie)("yy_logintype","qq.com","/"),Object(d.delCookie)("yy_token","qq.com","/"),Object(d.delCookie)("over_sea_nickname","qq.com","/"),Object(d.delCookie)("livelink_access_token","qq.com","/"),Object(d.delCookie)("livelink_acctype","qq.com","/"),Object(d.delCookie)("livelink_appid","qq.com","/"),Object(d.delCookie)("livelink_channel_code","qq.com","/"),Object(d.delCookie)("livelink_openid","qq.com","/"),Object(d.delCookie)("livelink_uid","qq.com","/"),Object(d.delCookie)("livelink_user_name","qq.com","/"),e&&Object(i.f)("loginOutSucc")}function v(){return p(this,void 0,void 0,(function(){var e,n,t;return m(this,(function(o){switch(o.label){case 0:return(e=Object(s.getCookie)("logintype"))&&["fb","gg"].includes(e)?[3,2]:[4,Object(a.jsonp)(Object(l.t)()+"/bin/syzs/checkLogin.php")];case 1:return n=o.sent(),[3,4];case 2:return t="yybstyle.sparta.html5.qq.com"===window.location.hostname?"syzslogin.sparta.html5.qq.com":"syzslogin.open.qq.com",[4,Object(u.ajax)({url:"https://"+t+"/"+{fb:"facebook",gg:"google"}[e]+"/check_login",method:"POST"})];case 3:n=o.sent(),o.label=4;case 4:return 0!==n.code&&b(),[2,n]}}))}))}},"ve/k":function(e,n,t){"use strict";t.d(n,"s",(function(){return i})),t.d(n,"g",(function(){return c})),t.d(n,"m",(function(){return a})),t.d(n,"o",(function(){return u})),t.d(n,"a",(function(){return d})),t.d(n,"p",(function(){return l})),t.d(n,"f",(function(){return p})),t.d(n,"d",(function(){return m})),t.d(n,"e",(function(){return f})),t.d(n,"q",(function(){return y})),t.d(n,"r",(function(){return h})),t.d(n,"k",(function(){return b})),t.d(n,"i",(function(){return v})),t.d(n,"c",(function(){return q})),t.d(n,"h",(function(){return g})),t.d(n,"n",(function(){return k})),t.d(n,"j",(function(){return w})),t.d(n,"l",(function(){return O})),t.d(n,"b",(function(){return j})),t.d(n,"t",(function(){return C}));var o=t("uR+Q"),i=["imgcache.qq.com","qzs.qq.com"].indexOf(window.location.host)<0;function r(e){return Object.keys(e).map((function(n){return n+"="+encodeURIComponent(e[n])})).join("&")}function c(){return i?"//yybadaccess.sparta.html5.qq.com":"//yybadaccess.3g.qq.com"}function a(){return i?"//apitest.livelink.qq.com":"//welive.qq.com"}function u(){return i?"//sy-guanjia.sparta.html5.qq.com":"//sy.guanjia.qq.com"}function s(e){return e?(i?"https://halodev.sparta.html5.qq.com":"https://imgcache.qq.com")+"/syzs/syzsweb/main.html#":""}function d(e,n){return void 0===n&&(n=!1),s(n)+"/app?pkgname="+encodeURIComponent(e)}function l(e,n){return void 0===n&&(n=!1),s(n)+"/topic?topic_mid="+encodeURIComponent(e)}function p(e,n){return void 0===n&&(n=!1),s(n)+"/discovery/"+o.t.discovery_information.name+"?type="+encodeURIComponent(e)}function m(e,n){return void 0===n&&(n=!1),s(n)+"/game-information?pkgname="+encodeURIComponent(e)}function f(e,n,t){switch(void 0===t&&(t=!1),e){case"topic":return l(n,t);case"news":return function(e,n){return void 0===n&&(n=!1),s(n)+"/news?cmsid="+encodeURIComponent(e)}(n,t);default:return"/"}}function y(e,n,t){return void 0===t&&(t=!1),s(t)+"/video?cmsid="+encodeURIComponent(n)+"&vid="+encodeURIComponent(e)}function h(e,n,t){return void 0===e&&(e=""),void 0===n&&(n=""),void 0===t&&(t=!1),s(t)+"/video-list?"+r({tagid:e,pkgname:n})}function b(e,n,t){return void 0===e&&(e=""),void 0===n&&(n=""),void 0===t&&(t=!1),s(t)+"/live-list?"+r({gameId:e,pkgname:n})}function v(e,n){return void 0===e&&(e=""),void 0===n&&(n=!1),s(n)+"/gamelibrary/"+e}function q(e,n){return void 0===e&&(e=""),void 0===n&&(n=!1),s(n)+"/discovery"+(e?"/"+e:"")}function g(e,n){return void 0===e&&(e=""),void 0===n&&(n=!1),s(n)+"/gamelibrary/"+o.t.library_all.name+(e?"?"+e:"")}function k(e){return void 0===e&&(e=!1),s(e)+"/gamelibrary/"+encodeURIComponent(o.t.library_rank.name)}function w(e,n){return void 0===e&&(e=""),void 0===n&&(n=!1),s(n)+"/webview?src="+encodeURIComponent(e)}function O(e,n){return void 0===n&&(n=!1),s(n)+"/live-detail?anchorId="+e.anchorId+"&platId="+e.livePlatId}function j(e,n){return void 0===e&&(e=""),void 0===n&&(n=!1),s(n)+"/AuthorDetails?authorId="+e}function C(){var e="https://sy.guanjia.qq.com";return["yybstyle.sparta.html5.qq.com","halodev.sparta.html5.qq.com"].includes(window.location.hostname)?e="https://sy-guanjia.sparta.html5.qq.com":"syzsstyle.sparta.html5.qq.com"===window.location.hostname&&(e="https://sy-gray.noscan.sparta.html5.qq.com"),e}}}]);