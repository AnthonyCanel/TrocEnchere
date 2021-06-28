<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Création Compte</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="container-fluid">
<header class=" row">
    <div class="col-4">
        <a class="btn btn-white m-5 mt-3" href="${pageContext.request.contextPath}/PageAccueilEnchere">
            <h1>ENI Enchère</h1>
        </a>
    </div>
    <div class="col-4">
        <h3 class="m-5 ml-1 font-weight-bold">Vous avez remporté la vente</h3>
    </div>
    <div class="col-4">
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
<div class=" row m-2 p-3 ">
    <div class="col-4">
        <div class="w-100 bg-secondary shadow"><img src="" alt=""></div>
    </div>
    <div class="col-8 shadow p-3">

        <div class="row m-2">
            <h5>PC Gamer pour travailler</h5>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <p>Description</p>
            </div>
            <div class="col-8">
                <p>C'est un PC qui fonctionne comme un malade vous serrez pas déçu</p>
            </div>
        </div>

        <div class=" row m-2 m-2">
            <div class="col-4">
                <p>Meilleur offre :</p>
            </div>
            <div class="col-8">
                <p>210 pts par Bob</p>
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <p>Mise à prix :</p>
            </div>
            <div class="col-8">
                <p>185 points</p>
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <p>Retrait</p>
            </div>
            <div class="col-8">
                <p>10 allée des Alouettes</p>
                <p>44800 Saint Herblain</p>
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <p>Vendeur : </p>
            </div>
            <div class="col-8">
                <p>jojo44</p>
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <p>Mail : </p>
            </div>
            <div class="col-8">
                <p>(Mon mail)</p>
            </div>
        </div>

        <div class="m-4">
            <a href="${pageContext.request.contextPath}/PageAccueilEnchere"><input class="p-2" type="button" value="Back"></a>
        </div>
    </div>
</div>
</div>
</div>
</div>
</body>
</html>
