<%@ page import="edu.bjut.search.entity.DocAttribute" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>搜索一下</title>
</head>
<body>
<%
    List<DocAttribute> docs = (List<DocAttribute>) request.getAttribute("docs");
    if (docs == null || docs.size() <= 0)
        out.println("搜索结果为空。");
    else {
        out.println("为您找到大约" + docs.size() + "条结果。<br>");
        for (DocAttribute doc : docs) {
            out.println("<a href=\"" + doc.getLink() + "\" target=\"_blank\">"+doc.getTitle()+"</a><br>");
            out.println(doc.getView()+"<br>");
        }
    }

%>

</body>

</html>