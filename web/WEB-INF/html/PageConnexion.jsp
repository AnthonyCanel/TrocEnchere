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

    <title>Connexion</title>
</head>
<body class="container-fluid">
<div class="row">
    <div class="d-flex justify-content-around">
        <div class="d-flex mb-5">
            <h1>ENI-Encheres</h1>
        </div>
    </div>
</div>
<div class="mx-auto" style="width: 400px;">
    <form action="PageConnexion" method="post">
        <div class="shadow p-5">
            <div class="row">
                <div class="col-5 mb-3">
                    Identifiant :
                </div>
                <div class="col-7 mb-3">
                    <input type="text" name="Identifiant" placeholder="Identifiant" required>
                </div>
            </div>

            <div class="row">
                <div class="col-5 mb-3">
                    Mot de passe :
                </div>
                <div class="col-7 mb-3">
                    <input type="password" name="MotDePasse" placeholder="Mot de passe" required>
                </div>
            </div>

            <div class="row">
                <div class="col-5 mb-3">
                    <button type="submit" name="Connexion?Connexion=true" class="btn btn-outline-dark mb-3 mb-5">Connexion</button>
                </div>

                <div class="col-7 mb-3">
                    <div class="row form-check">
                        <div class="col-2 ">
                            <input class="form-check-input" type="checkbox" id="flexCheckDefault"/>
                        </div>
                        <div class="col-10 ms-2 ps-1 pe-0">
                            <label class="form-check-label" for="flexCheckDefault"> Se souvenir de moi</label>
                        </div>
                    </div>
                    <div class="row">
                        <a class="nav-link" href="">Mot de passe oublié</a>
                    </div>
                </div>
            </div>
            <div class="row mb-2">
                <div class="d-flex justify-content-center">
                    <button type="button" name="CreerUnCompte?CreerUnCompte=true" class="btn btn-outline-dark btn-lg "
                            style="width: 400px">Créer un compte</button>
                </div>
            </div>

        </div>
    </form>
</div>
</body>
</html>