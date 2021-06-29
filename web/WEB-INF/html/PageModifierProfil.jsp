<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!--BOOTSTRAP CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Modifier Profil</title>
</head>
<body>
<header>
    <div class="row">
        <div class="col">
            <h1 class="m-5 mt-3">ENI-Enchères</h1>
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
</header>
<div class="container shadow p-4">
    <h1 class="row d-flex justify-content-center mb-5 p-2">Mon Profil</h1>
    <form action="PageMonProfil" method="post">
        <div class="p-3">
            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Pseudo : </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="pseudo" id="pseudo" value="${pseudo}" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Nom :</label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="nom" id="nom" value="${nom}" required
                                       pattern="[A-Za-z]{1,30}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Prénom :</label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="prenom" id="prenom" value="${prenom}" required
                                       pattern="[A-Za-z]{1,30}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Email : </label>
                        </div>
                        <div class="col-8">
                            <div>

                            </div>
                            <div class="input-group input-group-md mb-3">
                                <!--Vérification Mail abc@example.com # Minimum three characters
                                    ABC.xyz@example.com # Accepts Caps as well.
                                    abce.xyz@example.co.in # Accepts . before @
                                    pattern="[A-Za-z0-9._%+-]{3,}@[a-zA-Z]{3,}([.]{1}[a-zA-Z]{2,}|[.]{1}[a-zA-Z]{2,}[.]{1}[a-zA-Z]{2,})"-->
                                <input class="w-100" type="email" name="email" id="email" value="${email}" required>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Téléphone : </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="telephone" id="telephone" value="${telephone}"
                                       required pattern="^[0-9-.// |]{1,15}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Rue </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="rue" id="rue" value="${rue}" required>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Code postal </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="CP" id="codePostal" value="${CP}" required
                                       pattern="[0-9]{1,5}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Ville </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="text" name="ville" id="ville" value="${ville}" required
                                       pattern="^[A-Za-z'- ]{1,30}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Mot de passe actuel : </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="password" name="mdpa" id="motPasseActuel" value="${mdpa}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label></label>
                        </div>
                        <div class="col-8">
                            <label></label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Nouveau mot de passe : </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="password" name="nmdp" id="nouveauMotPasse">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Confirmation : </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input class="w-100" type="password" name="cmdp" id="confirmationMotPasse">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-2">
                            <label>Crédit : </label>
                        </div>
                        <div class="col-1">
                            <div class="input-group input-group-sm mb-3">
                                <label id="credit" name="credit">${credit}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col  d-flex justify-content-center">
                <input type="hidden" name="action" value="valider"/>
                <button type="submit" name="button" class="btn btn-outline-dark" value="valider">Enregistrer</button>
            </div>
        </div>
    </form>

        <form method="get" action="${pageContext.request.contextPath}/SupprimerCompte">
            <div class="col  d-flex justify-content-center">
                <input type="hidden" name="action" value="supprimer"/>
                    <button type="submit" name="button" class="btn btn-outline-dark" value="supprimer">Supprimer mon compte</button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
