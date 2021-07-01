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
<form action="PageAccueilEnchere" method="post">
    <div class="row">
        <div class="row p-0 d-flex justify-content-around">
            <div class="col d-flex">
                <a class="btn btn-white mt-3" href="${pageContext.request.contextPath}/PageAccueilEnchere">
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
                        <div class="col-5 text-center">
                            <label>Catégorie :</label>
                        </div>
                        <div class="col-7">
                            <select class="form-select form-select-sm" aria-label=".form-select-sm example"
                                    name="combo">

                                <option name="cat" value="0">Toutes</option>
                                <c:forEach items="${listeCategories}" var="chaqueCategorie">
                                    <option
                                            value="${chaqueCategorie.noCategorie}"
                                            <c:if test="${categorie == chaqueCategorie.noCategorie }">
                                                selected
                                            </c:if>
                                    >
                                            ${chaqueCategorie.libelle}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-2">
                    <button name="recherche" class="btn btn-outline-dark btn mb-3">Rechercher</button>
                </div>
            </div>
            <div class="row">
                <c:if test="${sessionScope.utilisateur != null}">
                    <jsp:include page="inclusion/CheckBoxAchatsVentes.jsp"></jsp:include>
                </c:if>
            </div>

            <div class="row ">
                <%--                <c:if test="${not empty rechercheParDefaut}">--%>
                <%--                    <h3 class="row text-center">--%>
                <%--&lt;%&ndash;                        Mes ventes en cours&ndash;%&gt;--%>
                <%--                    </h3>--%>
                <%--                </c:if>--%>
                <c:forEach items="${rechercheParDefaut}" var="unElement">
                    <div class="col-6 p-3 align-items-center">
                        <div class="bg-light shadow-sm p-3">
                            <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir"
                               name="nomArticle" value="${unElement.idArticle}">
                                <div class="w-100 row">
                                    <div class="col-3">
                                    </div>
                                    <div class="col-9">
                                        <div>
                                            <div class="row">
                                                <h5 class="font-weight-bold">${unElement.nomArticle}</h5>
                                            </div>
                                            <div class="row">
                                                <label>Prix : ${unElement.prixArticle} banane</label>
                                            </div>
                                            <div class="row">
                                                <label>Fin de l'enchère : ${unElement.finEnchere}</label>
                                            </div>
                                            <div class="row">
                                                <label>Vendeur : ${unElement.vendeur}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${not empty mesVentesEnCours}">
                    <div class="row text-center">
                        <h3>
                            Mes ventes en cours
                        </h3>
                    </div>
                </c:if>
                <c:forEach items="${mesVentesEnCours}" var="maVenteEnCours">
                    <div class="col-6 p-3 align-items-center">
                        <div class="bg-light shadow-sm p-3">
                            <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir"
                               name="nomArticle" value="${maVenteEnCours.idArticle}">
                                <div class="w-100 row">
                                    <div class="col-3">
                                    </div>
                                    <div class="col-9">
                                        <div>
                                            <div class="row">
                                                <h5 class="font-weight-bold">${maVenteEnCours.nomArticle}</h5>
                                            </div>
                                            <div class="row">
                                                <label>Prix : ${maVenteEnCours.prixArticle} banane</label>
                                            </div>
                                            <div class="row">
                                                <label>Fin de l'enchère : ${maVenteEnCours.finEnchere}</label>
                                            </div>
                                            <div class="row">
                                                <label>Vendeur : ${maVenteEnCours.vendeur}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${not empty mesVentesNonDebutees}">
                    <div class="row text-center">
                        <h3>
                            Mes ventes non débutées
                        </h3>
                    </div>
                </c:if>
                <c:forEach items="${mesVentesNonDebutees}" var="maVenteNonDebutee">
                    <div class="col-6 p-3 align-items-center">
                        <div class="bg-light shadow-sm p-3">
                            <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir"
                               name="nomArticle" value="${maVenteNonDebutee.idArticle}">
                                <div class="w-100 row">
                                    <div class="col-3">
                                    </div>
                                    <div class="col-9">
                                        <div>
                                            <div class="row">
                                                <h5 class="font-weight-bold">${maVenteNonDebutee.nomArticle}</h5>
                                            </div>
                                            <div class="row">
                                                <label>Prix : ${maVenteNonDebutee.prixArticle} banane</label>
                                            </div>
                                            <div class="row">
                                                <label>Fin de l'enchère : ${maVenteNonDebutee.finEnchere}</label>
                                            </div>
                                            <div class="row">
                                                <label>Vendeur : ${maVenteNonDebutee.vendeur}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${not empty mesVentesEnCours}">
                    <div class="row text-center">
                        <h3>
                            Mes ventes terminées
                        </h3>
                    </div>
                </c:if>
                <c:forEach items="${mesVentesTerminees}" var="maVenteTerminee">
                    <div class="col-6 p-3 align-items-center">
                        <div class="bg-light shadow-sm p-3">
                            <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir"
                               name="nomArticle" value="${maVenteTerminee.idArticle}">
                                <div class="w-100 row">
                                    <div class="col-3">
                                    </div>
                                    <div class="col-9">
                                        <div>
                                            <div class="row">
                                                <h5 class="font-weight-bold">${maVenteTerminee.nomArticle}</h5>
                                            </div>
                                            <div class="row">
                                                <label>Prix : ${maVenteTerminee.prixArticle} banane</label>
                                            </div>
                                            <div class="row">
                                                <label>Fin de l'enchère : ${maVenteTerminee.finEnchere}</label>
                                            </div>
                                            <div class="row">
                                                <label>Vendeur : ${maVenteTerminee.vendeur}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${not empty encheresOuvertes}">
                    <div class="row text-center">
                        <h3>
                            Les enchères ouvertes
                        </h3>
                    </div>
                </c:if>
                <c:forEach items="${encheresOuvertes}" var="enchereOuverte">
                    <div class="col-6 p-3 align-items-center">
                        <div class="bg-light shadow-sm p-3">
                            <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir"
                               name="nomArticle" value="${enchereOuverte.idArticle}">
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
                <c:forEach items="${mesEncheresEnCours}" var="enchereEnCours">
                    <div class="col-6 p-3 align-items-center">
                        <div class="bg-light shadow-sm p-3">
                            <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir"
                               name="nomArticle" value="${enchereEnCours.idArticle}">
                                <div class="w-100 row">
                                    <div class="col-3">
                                    </div>
                                    <div class="col-9">
                                        <div>
                                            <div class="row">
                                                <h5 class="font-weight-bold">${enchereEnCours.nomArticle}</h5>
                                            </div>
                                            <div class="row">
                                                <label>Prix : ${enchereEnCours.prixArticle} banane</label>
                                            </div>
                                            <div class="row">
                                                <label>Fin de l'enchère : ${enchereEnCours.finEnchere}</label>
                                            </div>
                                            <div class="row">
                                                <label>Vendeur : ${enchereEnCours.vendeur}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${not empty mesEncheresRemportees}">
                    <div class="row text-center">
                        <h3>
                            Mes enchères remportées
                        </h3>
                    </div>
                </c:if>
                <c:forEach items="${mesEncheresRemportees}" var="enchereRemportee">
                    <div class="col-6 p-3 align-items-center">
                        <div class="bg-light shadow-sm p-3">
                            <a class="p-0 btn btn-white" href="${pageContext.request.contextPath}/PageEncherir"
                               name="nomArticle" value="${enchereRemportee.idArticle}">
                                <div class="w-100 row">
                                    <div class="col-3">
                                    </div>
                                    <div class="col-9">
                                        <div>
                                            <div class="row">
                                                <h5 class="font-weight-bold">${enchereRemportee.nomArticle}</h5>
                                            </div>
                                            <div class="row">
                                                <label>Prix : ${enchereRemportee.prixArticle} banane</label>
                                            </div>
                                            <div class="row">
                                                <label>Fin de l'enchère
                                                    : ${enchereRemportee.finEnchere}</label>
                                            </div>
                                            <div class="row">
                                                <label>Vendeur : ${enchereRemportee.vendeur}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>


                <!-- fin de la boucle-->
                <!-- article complementaire a enlever une fois la boucle faite-->

                <%--            <!-- fin de l'article complementaire a enlever une fois la boucle faite-->--%>
            </div>
        </div>
    </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
