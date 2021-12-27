
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>欢迎使用协议分析软件</title>

    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <style type="text/css">
      .myForm {
        position: absolute; /*绝对定位*/
        width: 300px;
        height: 200px;

        text-align: center; /*(让div中的内容居中)*/
        top: 50%;
        left: 50%;
        margin-top: 10px;
        margin-left: -150px;
      }
    </style>

  </head>
  <body>


  <h1 style=" text-align: center;margin-top: 100px;font-family: 宋体; font-size: 80px">欢迎使用协议分析软件</h1>

  <p  style="text-align: center; margin-top: 40px;font-size: 30px;">请选择网卡</p>

  <div class="myForm">
    <form action="capture-tcp" method="get">
      <select name="netId" class="form-control" style="width: 200px;margin: 0 auto;">
        <option value="0">Microsoft</option>
        <option value="1">Intel(R) Ethernet Connection (10) I219-V</option>
        <option value="2">Microsoft2</option>
        <option value="3">VMware Virtual Ethernet Adapter1</option>
        <option value="4">Microsoft3</option>
        <option value="5">VMware Virtual Ethernet Adapter</option>
      </select>
      <div style="height: 30px"></div>
      <button type="submit" class="btn btn-primary" style="width: 199px;">开始捕获</button>
    </form>
  </div>
  </body>
</html>
