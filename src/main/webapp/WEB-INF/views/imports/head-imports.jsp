<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags/" %>

<head>
    <meta charset="utf-8">
    <link rel="icon" href="#">
    <fmt:setBundle basename="messages"/>
    <title>
        <fmt:message key="app.name"/>
    </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
    <script type="module" src="<c:url value="/static/js/main.js"/>"></script>
</head>
