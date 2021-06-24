<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page créer compte </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="container-fluid">
<header>
    <h1 class="m-5 mt-3">ENI Enchère</h1>
</header>
<div class="container shadow p-4">
    <h1 class="row d-flex justify-content-center mb-5 p-2">Mon Profil</h1>
    <form action="">
        <div class="p-3">
            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="pseudo">Pseudo :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" id="pseudo">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="nom">Nom : </label>
                        </div>
                        <div class="col-8">
                            <input type="text" id="nom">
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
                            <input type="text" id="prenom">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="email">Email :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" id="email">
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
                            <input type="text" id="tel">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="rue">Rue :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" id="rue">
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
                            <input type="text" id="CP">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="ville">Ville :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" id="ville">
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
                            <input type="text" id="mdp">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label for="mdpC">Confirmation :</label>
                        </div>
                        <div class="col-8">
                            <input type="text" id="mdpC">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-5">
                <h4 class="text-align-center mb-5 text-uppercase text-danger fw-bold d-flex justify-content-center" id="msg" name="msgError">Coucou</h4>
                <div class="col mr-5 d-flex flex-row-reverse">
                    <button class="btn btn-outline-secondary">Créer</button>
                </div>
                <div class="col ml-5 ">
                    <button class="btn btn-outline-secondary">Annuler</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>