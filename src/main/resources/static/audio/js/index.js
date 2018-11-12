/*
    写在前面：
    为了保持在功能演示方面的简洁， demo不会做任何合法性校验
*/

// 本demo用到的唯一一个CGI，获取usersig （什么是usersig? 请看 https://www.qcloudtrtc.com/webrtcapi/ )
// 如果您不了解非对称加密，可以这样简单理解：
// 你有公钥 和 私钥 两把钥匙，腾讯云有一把钥匙（公玥）
// 你把数据丢盒子里，并且用私钥上锁，然后把上了锁的盒子给到腾讯云
// 腾讯云可以用公钥这把钥匙来解开这把锁，拿到里面的数据。
// 所以你需要的是
// 去控制台把私钥下载下来，用TLS工具算一个签名（usersig)

//不要把您的sdkappid填进来就用这个cgi去测，测试demo的cgi没有您的私钥，臣妾做不到啊
var FetchSigCgi = 'https://www.qcloudtrtc.com/sxb_dev/?svc=account&cmd=authPrivMap';
var sdkappid,
    accountType = 14418, // accounttype 还是在文档中会找到
    userSig,
    username;


function onKickout() {
    alert("on kick out!");
}

function onRelayTimeout(msg) {
    alert("onRelayTimeout!" + (msg ? JSON.stringify(msg) : ""));
}


function createAudioElement( id, isLocal ){
    var audioNode=document.createElement("audio");
    audioNode.id = id;
    audioNode.autoplay = 'true';
    if( isLocal ){
        audioNode.muted = 'true';
    }
    audioNode.controls = 'true';
    document.querySelector("#remote-video-wrap").appendChild(audioNode);
    return audioNode;
}

var context = new AudioContext();



function addBGM() {
    document.getElementById("bgm").play();
    RTC.stopRTC({},function(){
        gotStream(function(stream){
            var microphone = context.createMediaStreamSource(stream);
            var backgroundMusic = context.createMediaElementSource(document.getElementById("bgm"));
            var analyser = context.createAnalyser();
            var mixedOutput = context.createMediaStreamDestination();
            microphone.connect(analyser);
            analyser.connect(mixedOutput);
            backgroundMusic.connect(mixedOutput);
            RTC.startRTC({
                stream: mixedOutput.stream,
                role: 'user'
            });
        })
    })

}

function getComputerAudio( callback ){
    // funciont 1
    // 枚举音频输入设备 -> 先获取PC的音频混流设备
    // 具体是哪个 device，这里每台电脑恐怕都不一样。
    // 可能是最后一个？
    // device 的item会像这样：{"label":"扬声器 (Realtek High Definition Audio)","deviceId":"95a3e76b348d181fca042eada7428dcf98df9d0129fb9a5c7a54619d3428387c"}
   /* 
     RTC.getAudioDevices(function(devices) {
       
        var device = devices[ devices.length - 1];
        console.error( devices[0])
        // 这个音频流包含麦克风和PC电脑音频
        RTC.getLocalStream({
            video:false,
            audio:true,
            audioDevice:device
        },function( info ){
            callback( info.stream )
        });
    });
   */

    // funciont 2
    // 目前测试 ，使用PC的默认输出是可以的。
    // audioDevice:{
    //     deviceId:"default"
    // }
    RTC.getLocalStream({
        video:false,
        audio:true,
        audioDevice:{
            deviceId:"default"
        }
    },function( info ){
        callback( info.stream )
    });
}
function addComputer() {
    RTC.stopRTC({},function(){
        getComputerAudio( function( pc_stream){
            RTC.startRTC({
                stream: pc_stream,
                role: 'user'
            });
        })
    })
}
function soundMeter( info ){
    
    console.debug(' meter start')
    // 分析音频流
    var meter = WebRTCAPI.SoundMeter({
        stream: info.stream,
        onprocess: function( data ){
            $("#volume").val( data.volume)
            $("#volume_str").text( "volume: "+ data.volume)
            $("#status").text( data.status )
        }
    })
    setTimeout( function(){
        console.debug(' meter stop')
        meter.stop();
     },10000);
}
function onLocalStreamAdd(info) {
    if (info.stream && info.stream.active === true)
    {
        var id = "local";
        var audio = document.getElementById(id);
        if(!audio){
            audio = createAudioElement(id, true);
        }
        audio.srcObject = info.stream;
    }
    soundMeter( info );
}


function onRemoteStreamUpdate( info ) {
    if (info.stream && info.stream.active === true)
    {
        var id = info.videoId;
        var audio = document.getElementById(id);
        if(!audio){
            audio = createAudioElement(id);
        }
        audio.srcObject = info.stream;
    } else{
        console.log('欢迎用户'+ info.userId+ '加入房间');
    }
}


function onRemoteStreamRemove( info ) {
    console.log( info.userId+ ' 断开了连接');
    var audioNode = document.getElementById( info.videoId );
    if( audioNode ){
        audioNode.srcObject = null;
        document.querySelector("#remote-video-wrap").removeChild(audioNode);
    }
}

function onWebSocketClose() {
    RTC.quit();
}


function gotStream( succ ){
    RTC.getLocalStream({
        video:false, //不采集视频
        audio:true
    },function(info){
        var stream = info.stream;
        succ ( stream )
    });
}

function initRTC(opts){

      // 初始化
    window.RTC = new WebRTCAPI({
        "debug":{
            log:true
        },
        "userId": opts.userId,
        "userSig": opts.userSig,
        "sdkAppId": opts.sdkappid
    });
    
    
    RTC.createRoom({
        roomid : opts.roomid * 1,
        privMap: 255,
        recordId: $("#recordId").val() || null,
        pureAudioPushMod: $("#pureAudioPushMod").val(),
    },function(){
        gotStream(function(stream){
            RTC.startRTC({
                stream: stream,
                role: 'user'
            });
        })
    });

    // 远端流新增/更新
    RTC.on("onRemoteStreamUpdate",onRemoteStreamUpdate)
    // 本地流新增
    RTC.on("onLocalStreamAdd",onLocalStreamAdd)
    // 远端流断开
    RTC.on("onRemoteStreamRemove",onRemoteStreamRemove)
    // 重复登录被T
    RTC.on("onKickout",onKickout)
    RTC.on("onErrorNotify",function(e){ console.error(e); });
    // 服务器超时
    RTC.on("onRelayTimeout",onRelayTimeout)
    // just for debugging
    RTC.on("*",function(e){ console.debug(e); });
}
$("#username").val("audio_"+ parseInt(Math.random()*100000000));
function login(){
    sdkappid = $("#sdkappid").val();
    username = $("#username").val();
    //请使用英文半角/数字作为用户名
    $.ajax({
        type: "POST",
        url: FetchSigCgi,
        dataType: 'json',
        data:JSON.stringify({
            pwd: "12345678",
            appid: parseInt(sdkappid),
            roomnum:parseInt($("#roomid").val()),
            privMap:255,
            identifier : username,
            accounttype: accountType
        }),
        success: function (json) {
            if(json && json.errorCode === 0 ){
                //一会儿进入房间要用到
                userSig = json.data.userSig;
                var privateMapKey = json.data.privMapEncrypt;
                // 页面处理，显示视频流页面
                $("#video-section").show();
                $("#input-container").hide();

                initRTC({
                    "userId": username,
                    "userSig": userSig,
                    "privMapEncrypt": privateMapKey,
                    "sdkappid": sdkappid,
                    "accountType": accountType,
                    "roomid": $("#roomid").val()
                });
            }else{
                console.error(json);
            }
        },
        error: function (err){
            console.error(err);
        }
    })
}

