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
    <form id="search-form" action="api/search" method="get">
        <input type="text"/>
        <button type="submit">搜索一下</button>
    </form>
    
    <br>
    
    <form action="api/index/offline" method="post">
        <input type="file"/>
        <button type="submit">选择一个文件</button>
    </form>
    <br>

    <form action="api/index/offline/batch" method="post">
        <input type="file"/>
        <button type="submit">选择一个文件夹</button>
    </form>

    <br>

    <form action="api/index/online" method="post">
        <input type="text"/>
        <button type="submit">输入一个URL地址</button>
    </form>
</div>
</body>

</html>