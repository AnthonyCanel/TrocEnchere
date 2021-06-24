<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Accueil</title>
</head>
<body class="container-fluid">
    <div class="row">
        <div class="d-flex justify-content-around">
            <div class="d-flex">
                <h1>ENI-Encheres</h1>
            </div>
        </div>
    </div>
    <div class="mx-auto" style="width: 400px;">
        <div class="row">
            <form action="PageConnexion" method="post">
                <div class="col">
                    Identifiant :
                </div>
                <div class="col">
                    <input type="text" name="Identifiant" placeholder="Identifiant">
                </div>

                <div class="row">
                    <div class="col">
                        Mot de passe :
                    </div>
                </div>
                <div>
                    <div class="col">
                        <input type="password" name="MotDePasse" placeholder="Mot de passe">
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <a href="${pageContext.request.contextPath}/PagesListeEncheresConnecte"><input type="submit" name="Connexion?Connexion=true" value="Connexion"/></a>

        <%--                <button type="button" name="Connexion?Connexion=true" class="btn btn-outline-secondary btn-lg mb-3">--%>
        <%--                    Connexion</button>--%>
                    </div>
                    <div class="col">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"/>
                            <label class="form-check-label" for="flexCheckDefault"> Se souvenir de moi</label>
                            <a href="">Mot de passe oublié</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <a href="${pageContext.request.contextPath}/PageCreerCompte"><button type="button" name="CreerUnCompte?CreerUnCompte=true" class="btn btn-outline-secondary btn-lg mb-3" style="width: 400px">Créer un compte</button></a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>