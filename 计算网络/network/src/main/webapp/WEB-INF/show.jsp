<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>抓包界面</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css" >
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css" >
    <link rel="stylesheet" href="/css/community.css" >
    <link rel="stylesheet" href="/css/editormd.preview.min.css" >

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <!--
            <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
        -->
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->

    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>


<body>

<nav class="navbar navbar-default navbar-fixed-top" >
    <div class="container" >
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="javascript:void(0)" class="navbar-brand" onclick="changeStart()">开始捕获</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li ><a href="javascript:void(0)" onclick="changeEnd()" class="active">暂停捕获<span class="sr-only">(current)</span></a></li>
                    <li ><a href="#">保存</a></li>
                    <li class="dropdown">
                        <select onchange="changeStatus()" class="form-control" style="width: 160px;margin-top: 8px;">
                            <option value="0">请选择捕获协议</option>
                            <option value="1">IP</option>
                            <option value="2">TCP</option>
                            <option value="3">UDP</option>
                            <option value="4">ICMP</option>
                        </select>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div>
</nav>


<dev>
    <div style="width: 1000px;height: 100%; margin-left: 60px;">
        <span id="my-span" style="width: 1000px;">
        <!-- 展示WebSocket消息 -->
        </span>
    </div>
</dev>
<script type="text/javascript">

    let websocket;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://127.0.0.1:8080/network/network-data");
    }else {
        alert('该浏览器不支持WebSocket')
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    };

    websocket.onclose = function (event) {
        console.log('关闭连接');
    };

    websocket.onerror = function (event) {
        alert('websocket发生错误');
    };

    window.onbeforeunload = function () {
        websocket.close();
    };

    function changeStatus() {
        var vs = $('select  option:selected').val();
        if (vs == 1) {
            //              1、我们首先要创建XMLHttpRequest
            var xmlhttprequest = new XMLHttpRequest();
//              2、调用open方法设置请求参数，（true代表异步请求，false代表同步请求）
            xmlhttprequest.open("GET","http://localhost:8080/network/catchIP" , true);

//              3、调用send方法发送请求z
            xmlhttprequest.send();
            alert("IP");
        }
        if (vs == 2) {
            //              1、我们首先要创建XMLHttpRequest
            var xmlhttprequest = new XMLHttpRequest();
//              2、调用open方法设置请求参数，（true代表异步请求，false代表同步请求）
            xmlhttprequest.open("GET","http://localhost:8080/network/catchTCP" , true);

//              3、调用send方法发送请求z
            xmlhttprequest.send();
            alert("TCP");
        }
        if (vs == 3) {
            //              1、我们首先要创建XMLHttpRequest
            var xmlhttprequest = new XMLHttpRequest();
//              2、调用open方法设置请求参数，（true代表异步请求，false代表同步请求）
            xmlhttprequest.open("GET","http://localhost:8080/network/catchUDP" , true);

//              3、调用send方法发送请求z
            xmlhttprequest.send();
            alert("UDP");
        }
        if (vs == 4) {
            //              1、我们首先要创建XMLHttpRequest
            var xmlhttprequest = new XMLHttpRequest();
//              2、调用open方法设置请求参数，（true代表异步请求，false代表同步请求）
            xmlhttprequest.open("GET","http://localhost:8080/network/catchICMP" , true);

//              3、调用send方法发送请求z
            xmlhttprequest.send();
            alert("ICMP");
        }
    };

    var flag = true;
    function changeStart() {
        flag = true;
        //              1、我们首先要创建XMLHttpRequest
        var xmlhttprequest = new XMLHttpRequest();
//              2、调用open方法设置请求参数，（true代表异步请求，false代表同步请求）
        xmlhttprequest.open("GET","http://localhost:8080/network/startCatch" , true);

//              3、调用send方法发送请求z
        xmlhttprequest.send();
        alert("开始捕获");
    };
    function changeEnd() {
        flag = false;
        //              1、我们首先要创建XMLHttpRequest
        var xmlhttprequest = new XMLHttpRequest();
//              2、调用open方法设置请求参数，（true代表异步请求，false代表同步请求）
        xmlhttprequest.open("GET","http://localhost:8080/network/pauseCatch" , true);

//              3、调用send方法发送请求z
        xmlhttprequest.send();
    };

    let mySpan = document.getElementById("my-span");
    websocket.onmessage = function (event) {
        console.log('收到消息' + event.data);
        if (flag) {
            // 展示数据
            mySpan.innerText = event.data
        }
    };


</script>
</body>
</html>