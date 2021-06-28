<%@ page import="fr.eni.bo.Categorie" %>
<%@ page import="java.util.List" %>
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
    <%
        List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("categorie");
    %>
    <title>Accueil</title>
</head>
<body>
<div class="row">
    <div class="row d-flex justify-content-around">
        <div class="col d-flex">
            <a class="btn btn-white m-5 mt-3" href="${pageContext.request.contextPath}/PageAccueilEnchere">
                <h1>ENI Enchère</h1>
            </a>
        </div>
        <div class="col m-5 mt-3">
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
                    <span class="input-group-text">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
</svg>
                    </span>
                    <input type="text" class="form-control"/>
                </div>
            </div>
            <div class="col-5">
                <div class="row">
                    <div class="col-4 text-center">
                        <label>Catégorie :</label>
                    </div>
                    <div class="col-8">
                        <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="combo">
                            <option selected>Toutes</option>
                            <% //plus simple de construire la comboBox
                                if (listeCategories != null) {
                                    for(Categorie chaqueCategorie : listeCategories){
                                        out.print("<option value=" + chaqueCategorie.getLibelle() + ">" + chaqueCategorie.getLibelle() + "</option>");
                                    }
                                }
                            %>
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
                        <img src="img/saucisson.jpg"></img>
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
