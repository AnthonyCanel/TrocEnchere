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
    <div class="row p-0 d-flex justify-content-around">
        <div class="col d-flex">
            <a class="btn btn-white m-5 mt-3" href="${pageContext.request.contextPath}/PageAccueilEnchere">
                <h1>ENI Enchère</h1>
            </a>
        </div>
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
<div class="d-flex justify-content-center">
    <div class="shadow p-5 w-75 ">
        <div class="row">
            <div>
                <h2 class="text-center mb-4">Liste des Enchères</h2>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/PageAccueilEnchere" method="post">
            <div class="row">
                <label class="text-start mb-3">Filtres :</label>
            </div>
            <div class="row mb-5">
                <div class="col-5">
                    <div class="input-group mb-3">
                        <span class="input-group-text">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                            </svg>
                        </span>
                        <input type="text" name="filtreSaisi" value="${saisie}" class="form-control"/>
                    </div>
                </div>
                <div class="col-5">
                    <div class="row">
                        <div class="col-4 text-center">
                            <label>Catégorie :</label>
                        </div>
                        <div class="col-8">
                            <select class="form-select form-select-sm" aria-label=".form-select-sm example"
                                    name="combo" >

                                <option name="cat" value="0" >Toutes</option>
                                <c:forEach items="${listeCategories}" var="chaqueCategorie">
                                    <option name="cat"
                                            value="${chaqueCategorie.noCategorie}"
                                            <c:if test="${categorie == chaqueCategorie.noCategorie }">
                                             selected
                                            </c:if>
                                    >${chaqueCategorie.libelle}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-2">
                    <%--                <a href="${pageContext.request.contextPath}/PageAccueilEnchere">--%>
                    <button name="recherche" class="btn btn-outline-dark btn mb-3">Rechercher</button>
                    <%--                </a>--%>
                </div>
            </div>
            <%--        <form action="/PageAccueilEnchere" method="post">--%>
            <div class="row">
                <c:if test="${sessionScope.utilisateur != null}">
                    <jsp:include page="inclusion/CheckBoxAchats.jsp"></jsp:include>
                </c:if>
                <div class="col-4"></div>
                <div class="col-4"></div>
            </div>
        </form>
        <div class="row ">
            <c:choose>
                <c:when test="${AchatsVentes eq 'v'}">
                    <c:if test="${not empty mesVentesEnCours}">
                        <h3 class="row text-center">
                            Mes ventes en cours
                        </h3>
                    </c:if>
                    <c:forEach items="${mesVentesEnCours}" var="maVenteEnCours">
                        <div class="col-6 p-3 align-items-center">
                            <div class="bg-light shadow-sm p-3">
                                <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir" name="nomArticle" value="${mesVentesEnCours.idArticle}">
                                    <div class="w-100 row">
                                        <div class="col-3">
                                        </div>
                                        <div class="col-9">
                                            <div>
                                                <div class="row">
                                                    <h5 class="font-weight-bold">${mesVentesEnCours.nomArticle}</h5>
                                                </div>
                                                <div class="row">
                                                    <label>Prix : ${mesVentesEnCours.prixArticle} banane</label>
                                                </div>
                                                <div class="row">
                                                    <label>Fin de l'enchère : ${mesVentesEnCours.finEnchere}</label>
                                                </div>
                                                <div class="row">
                                                    <label>Vendeur : ${mesVentesEnCours.vendeur}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${not empty mesVentesNonDebutees}">
                        <h3 class="row text-center">
                            Mes ventes non débutées
                        </h3>
                    </c:if>
                    <c:forEach items="${mesVentesNonDebutees}" var="maVenteNonDebutee">
                        <div class="col-6 p-3 align-items-center">
                            <div class="bg-light shadow-sm p-3">
                                <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir" name="nomArticle" value="${mesVentesNonDebutees.idArticle}">
                                    <div class="w-100 row">
                                        <div class="col-3">
                                        </div>
                                        <div class="col-9">
                                            <div>
                                                <div class="row">
                                                    <h5 class="font-weight-bold">${mesVentesNonDebutees.nomArticle}</h5>
                                                </div>
                                                <div class="row">
                                                    <label>Prix : ${mesVentesNonDebutees.prixArticle} banane</label>
                                                </div>
                                                <div class="row">
                                                    <label>Fin de l'enchère : ${mesVentesNonDebutees.finEnchere}</label>
                                                </div>
                                                <div class="row">
                                                    <label>Vendeur : ${mesVentesNonDebutees.vendeur}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${not empty mesVentesEnCours}">
                        <h3 class="row text-center">
                            Mes ventes terminées
                        </h3>
                    </c:if>
                    <c:forEach items="${mesVentesTerminees}" var="maVenteTerminee">
                        <div class="col-6 p-3 align-items-center">
                            <div class="bg-light shadow-sm p-3">
                                <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir" name="nomArticle" value="${mesVentesTerminees.idArticle}">
                                    <div class="w-100 row">
                                        <div class="col-3">
                                        </div>
                                        <div class="col-9">
                                            <div>
                                                <div class="row">
                                                    <h5 class="font-weight-bold">${mesVentesTerminees.nomArticle}</h5>
                                                </div>
                                                <div class="row">
                                                    <label>Prix : ${mesVentesTerminees.prixArticle} banane</label>
                                                </div>
                                                <div class="row">
                                                    <label>Fin de l'enchère : ${mesVentesTerminees.finEnchere}</label>
                                                </div>
                                                <div class="row">
                                                    <label>Vendeur : ${mesVentesTerminees.vendeur}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty encheresOuvertes}">
                        <h3 class="row text-center">
                            Les enchères ouvertes
                        </h3>
                    </c:if>
                    <c:forEach items="${encheresOuvertes}" var="enchereOuverte">
                        <div class="col-6 p-3 align-items-center">
                            <div class="bg-light shadow-sm p-3">
                                <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir" name="nomArticle" value="${enchereOuverte.idArticle}">
                                    <div class="w-100 row">
                                        <div class="col-3">
                                        </div>
                                        <div class="col-9">
                                            <div>
                                                <div class="row">
                                                    <h5 class="font-weight-bold">${enchereOuverte.nomArticle}</h5>
                                                </div>
                                                <div class="row">
                                                    <label>Prix : ${enchereOuverte.prixArticle} banane</label>
                                                </div>
                                                <div class="row">
                                                    <label>Fin de l'enchère : ${enchereOuverte.finEnchere}</label>
                                                </div>
                                                <div class="row">
                                                    <label>Vendeur : ${enchereOuverte.vendeur}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${not empty mesEncheresEnCours}">
                        <div class="row text-center">
                            <h3>
                                Mes enchères en cours
                            </h3>
                        </div>
                    </c:if>
                    <c:forEach items="${mesEncheresEnCours}" var="enchereEnCours">MES ENCHERES EN COURS
                        <div class="col-6 p-3 align-items-center">
                            <div class="bg-light shadow-sm p-3">
                                <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir" name="nomArticle" value="${mesEncheresEnCours.idArticle}">
                                    <div class="w-100 row">
                                        <div class="col-3">
                                        </div>
                                        <div class="col-9">
                                            <div>
                                                <div class="row">
                                                    <h5 class="font-weight-bold">${mesEncheresEnCours.nomArticle}</h5>
                                                </div>
                                                <div class="row">
                                                    <label>Prix : ${mesEncheresEnCours.prixArticle} banane</label>
                                                </div>
                                                <div class="row">
                                                    <label>Fin de l'enchère : ${mesEncheresEnCours.finEnchere}</label>
                                                </div>
                                                <div class="row">
                                                    <label>Vendeur : ${mesEncheresEnCours.vendeur}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${not empty mesEncheresRemportees}">
                        <h3 class="row text-center">
                            Mes enchères remportées
                        </h3>
                    </c:if>
                    <c:forEach items="${mesEncheresRemportees}" var="enchereRemportee">
                        <div class="col-6 p-3 align-items-center">
                            <div class="bg-light shadow-sm p-3">
                                <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir" name="nomArticle" value="${mesEncheresRemportees.idArticle}">
                                    <div class="w-100 row">
                                        <div class="col-3">
                                        </div>
                                        <div class="col-9">
                                            <div>
                                                <div class="row">
                                                    <h5 class="font-weight-bold">${mesEncheresRemportees.nomArticle}</h5>
                                                </div>
                                                <div class="row">
                                                    <label>Prix : ${mesEncheresRemportees.prixArticle} banane</label>
                                                </div>
                                                <div class="row">
                                                    <label>Fin de l'enchère
                                                        : ${mesEncheresRemportees.finEnchere}</label>
                                                </div>
                                                <div class="row">
                                                    <label>Vendeur : ${mesEncheresRemportees.vendeur}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


            <!-- fin de la boucle-->
            <!-- article complementaire a enlever une fois la boucle faite-->

            <%--            <!-- fin de l'article complementaire a enlever une fois la boucle faite-->--%>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
