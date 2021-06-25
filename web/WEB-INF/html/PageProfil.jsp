<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Création Compte</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="container-fluid">
<header>
    <div class="col-6">
        <a class="btn btn-white m-5 mt-3" href="${pageContext.request.contextPath}/PageAccueilEnchere">
            <h1>ENI Enchère</h1>
        </a>
    </div>
    <div class="col-6">
        <c:choose>
            <c:when test="${sessionScope.utilisateur != null}">
                <jsp:include page="inclusion/NavBarCo.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="inclusion/NavBarDeco.jsp"></jsp:include>
            </c:otherwise>
        </c:choose>
    </div>
</header>
<div class="container shadow p-4 d-flex justify-content-end w-50">
    <div class="container">
        <div class="row">
            <div class="col-4">
                <label>Pseudo</label>
            </div>
            <div class="col-8">
                <label class="fw-bold" id="pseudo">vbiqvuvhivhiov</label>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <label>Nom</label>
            </div>
            <div class="col-8">
                <label id="nom">vbiqvuvhivhiov</label>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <label>Prenom</label>
            </div>
            <div class="col-8">
                <label id="prenom">vbiqvuvhivhiov</label>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <label>Email</label>
            </div>
            <div class="col-8">
                <label id="email">vbiqvuvhivhiov</label>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <label>Code Postal</label>
            </div>
            <div class="col-8">
                <label id="CP">vbiqvuvhivhiov</label>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <label>Ville</label>
            </div>
            <div class="col-8">
                <label id="ville">vbiqvuvhivhiov</label>
            </div>
        </div>
        <div class="row d-flex justify-content-center">
            <a href="${pageContext.request.contextPath}/PageAccueilEnchere">
                <button>Retour</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>