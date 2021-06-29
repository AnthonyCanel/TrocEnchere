<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page créer compte </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="container-fluid">
<header class="row">
    <div class="col">
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
</header>
<div class="container shadow p-4">
    <h1 class="row d-flex justify-content-center mb-5 p-2">Mon Profil</h1>
    <form action="PageCreerCompte" method="post">
        <div class="p-3">
            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="pseudo">Pseudo :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="pseudo" id="pseudo">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="nom">Nom : </label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="nom" id="nom">
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="prenom">Prenom :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="prenom" id="prenom">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="email">Email :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="email" id="email">
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="tel">Téléhone :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="tel" id="tel">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="rue">Rue :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="rue" id="rue">
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="CP">Code postal :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="CP" id="CP">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="ville">Ville :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="ville" id="ville">
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="mdp">Mot de passe :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="mdp" id="mdp">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="mdpC">Confirmation :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" name="mdpC" id="mdpC">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-5">
                <h4 class="text-align-center mb-5 text-uppercase text-danger fw-bold d-flex justify-content-center"
                    id="msg" name="msgError">${message}</h4>
                <div class="col mr-5 d-flex flex-row-reverse">
                    <a href="${pageContext.request.contextPath}/PageAccueilEnchere">
                        <button class="btn btn-outline-dark" name="Creer?Creer=creer">Créer</button>
                    </a>
                </div>
                <div class="col ml-5 ">
                    <a href="${pageContext.request.contextPath}/PageAccueilEnchere">
                        <button class="btn btn-outline-dark" name="Annuler?Annuler=annuler">Annuler</button>
                    </a>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>