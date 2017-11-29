<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>搜索一下</title>
</head>
<body>
<div id="search-field">
    <br><br><br><br><br><br><br><br>
    <div id="cen" style="text-align:center">
        <form id="search-form" action="<%=request.getContextPath()%>/api/search" method="get">
            <input type="text" name="keyword" id="keyword" style="height:20px;weight:100px"/>
            <button type="submit">搜索一下</button>
        </form>
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br>

    <form action="<%=request.getContextPath()%>/api/index/offline" method="post">
        <input type="text" name="source" placeholder="输入文件路径"/>
        <button type="submit">提交</button>
    </form>
    <br>

    <form action="<%=request.getContextPath()%>/api/index/offline/batch" method="post">
        <input type="text" name="source" placeholder="输入文件夹路径"/>
        <button type="submit">提交</button>
    </form>

    <br>

    <form action="<%=request.getContextPath()%>/api/index/online" method="post">
        <input type="text" name="source" placeholder="输入URL路径"/>
        <button type="submit">提交</button>
    </form>

</div>
</body>

</html>