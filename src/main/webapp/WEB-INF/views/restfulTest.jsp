<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>restful Test</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div
        style="width:800px;margin-top:10px;margin-left: auto;margin-right: auto;text-align: center;">
    <h2>restful Test</h2>
</div>
<div style="width:800px;margin-left: auto;margin-right: auto;">
    <label>path：<%=path%>
    </label><br>

    <label>basePath: <%=basePath%>
    </label><br>
    
    <fieldset class="uk-form">
        <legend>基于Restful架构风格的资源请求测试</legend>
        <button class="btn btn-primary" id="btnGet">获取人员GET</button>
        <button class="btn btn-primary" id="btnAdd">添加人员POST</button>
        <button class="btn btn-primary" id="btnUpdate">更新人员PUT</button>
        <button class="btn btn-warning" id="btnDel">删除人员DELETE</button>
        <button class="btn btn-primary" id="btnList">查询列表PATCH</button>
    </fieldset>
    <textarea id="abc" cols="10" rows="4" style="width: 100%;">

    </textarea>

    <script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous">
    </script>

    <script type="text/javascript">
        (function(window,$){
            var dekota={
                url:'',
                init:function(){
                    dekota.url='<%=basePath%>';
                    $("#btnGet").click(dekota.getPerson);
                    $("#btnAdd").click(dekota.addPerson);
                    $("#btnDel").click(dekota.delPerson);
                    $("#btnUpdate").click(dekota.updatePerson);
                    $("#btnList").click(dekota.listPerson);
                },
                getPerson:function(){
                    $.ajax({
                        url: dekota.url + 'person/33',
                        type: 'GET',
                        dataType: 'json',
                        success:(function(data, status, xhr) {
                            $("#abc").val(JSON.stringify(data));
                        }),error:(function(xhr, status, error) {
                            alert("请求失败");
                        })
                    });
                },
                addPerson:function(){
                    $.ajax({
                        url: dekota.url + 'person',
                        type: 'POST',
                        dataType: 'json',
                        data: {name:'张三',sex:'男',age:23},
                        success:(function(data, status, xhr) {
                            $("#abc").val(JSON.stringify(data));
                        }),error:(function(xhr, status, error) {
                            alert("请求失败");
                        })
                    });
                },
                delPerson:function(){
                    $.ajax({
                        url: dekota.url + 'person/109',
                        type: 'DELETE',
                        dataType: 'json',
                        success:(function(data, status, xhr) {
                            $("#abc").val(JSON.stringify(data));
                        }),error:(function(xhr, status, error) {
                            alert("请求失败");
                        })
                    });
                },
                updatePerson:function(){
                    $.ajax({
                        url: dekota.url + 'person',
                        type: 'POST',//注意在传参数时，加：_method:'PUT'　将对应后台的PUT请求方法
                        dataType: 'json',
                        data: {_method:'PUT',name:'王五',sex:'男',age:23},
                        success:(function(data, status, xhr) {
                            $("#abc").val(JSON.stringify(data));
                        }),error:(function(xhr, status, error) {
                            alert("请求失败");
                        })
                    });
                },
                listPerson:function(){
                    $.ajax({
                        url: dekota.url + 'person',
                        type: 'POST',//注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                        dataType: 'json',
                        data: {_method:'PATCH',name: '张三'},
                        success:(function(data, status, xhr) {
                            $("#abc").val(JSON.stringify(data));
                        }),error:(function(xhr, status, error) {
                            alert("请求失败");
                        })
                    });
                }
            };
            window.dekota=(window.dekota)?window.dekota:dekota;
            $(function(){
                dekota.init();
            });
        })(window,jQuery);

    </script>
</div>
</body>


