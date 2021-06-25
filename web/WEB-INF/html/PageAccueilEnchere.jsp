<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Accueil</title>
</head>
<body>
<div class="row">
    <div class="row d-flex justify-content-around">
        <div class="col d-flex">
            <h1 class="m-5 mt-3">ENI-Enchères</h1>
        </div>
        <div class="col d-flex m-5 mt-3">
            <c:choose>
                <c:when test="${sessionScope.utilisateur != null}">
                    <jsp:include page="inclusion/NavBarCo.jsp"></jsp:include>
                </c:when>
                <c:otherwise>
                    <jsp:include page="inclusion/NavBarDeco.jsp"></jsp:include>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div class="d-flex justify-content-center">
    <div class="shadow p-5 w-75 ">
        <div class="row">
            <div>
                <h2 class="text-center mb-4">Liste des Enchères</h2>
            </div>
        </div>

        <div class="row">
            <label class="text-start mb-3">Filtres :</label>
        </div>


        <div class="row mb-5">
            <div class="col-5">
                <div class="input-group mb-3">
                    <span class="input-group-text">Search</span>
                    <input type="text" class="form-control"/>
                </div>
            </div>
            <div class="col-5">
                <div class="row">
                    <div class="col-4 text-center">
                        <label>Catégorie :</label>
                    </div>
                    <div class="col-8">
                        <select class="form-select form-select-sm" aria-label=".form-select-sm example">
                            <option selected>Open this select menu</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-2">
                <a href="${pageContext.request.contextPath}/PageAccueilEnchere">
                    <button type="button" class="btn btn-outline-dark btn mb-3">Rechercher</button>
                </a>
            </div>
        </div>
        <form action="/PageAccueilEnchere" method="post">
        <div class="row">
                <c:if test="${sessionScope.utilisateur != null}">
                    <jsp:include page="inclusion/CheckBoxAchats.jsp"></jsp:include>
                </c:if>
            <div class="col-4"></div>
        <div class="col-4"></div>
        </div>
        </form>
        <div class="row ">
            <!-- la boucle est a mettre ici-->
            <div class="col bg-light shadow-sm p-3 m-3 d-flex align-items-center">
                <div class="w-100 row">
                    <div class="col-3">
                        <img src="img/fromage.jpg"></img>
                    </div>
                    <div class="col-9">
                        <div>
                            <div class="row">
                                <h5 class="font-weight-bold">Saucisson</h5>
                            </div>
                            <div class="row">
                                <label>Prix : 654 banane</label>
                            </div>
                            <div class="row">
                                <label>Fin de l'enchère : 654 banane</label>
                            </div>
                            <div class="row">
                                <label>Vendeur : 654 banane</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- fin de la boucle-->
            <!-- article complementaire a enlever une fois la boucle faite-->
            <div class="col bg-light shadow-sm p-3 m-3 d-flex align-items-center">
                <div class="w-100 row">
                    <div class="col-3">
                        <img src="img/fromage.jpg"></img>
                    </div>
                    <div class="col-9">
                        <div>
                            <div class="row">
                                <h5 class="font-weight-bold">Comté</h5>
                            </div>
                            <div class="row">
                                <label>Prix : 654 banane</label>
                            </div>
                            <div class="row">
                                <label>Fin de l'enchère : 654 banane</label>
                            </div>
                            <div class="row">
                                <label>Vendeur : 654 banane</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- fin de l'article complementaire a enlever une fois la boucle faite-->
        </div>
    </div>
</div>

</body>
</html>
