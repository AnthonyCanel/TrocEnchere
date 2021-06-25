<%--
  Created by IntelliJ IDEA.
  User: ajouhanneau2021
  Date: 24/06/2021
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--BOOTSTRAP CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Modifier Profil</title>
</head>
<body>
<header>
    <h1 class="m-5 mt-3">ENI-Enchères</h1>
</header>
<div class="container shadow p-4">
    <h1 class="row d-flex justify-content-center mb-5 p-2">Mon Profil</h1>
    <form action="">
        <div class="p-3">
            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Pseudo : </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <input type="text" class="form-control" id="pseudo">
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
                                <input type="text" class="form-control" id="nom">
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
                                <input type="text" class="form-control" id="prenom">
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
                            <div class="input-group input-group-sm mb-3">
                                <input type="text" class="form-control" id="email">
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
                                <input type="text" class="form-control" id="telephone">
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
                                <input type="text" class="form-control" id="rue">
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
                                <input type="text" class="form-control" id="codePostal">
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
                                <input type="text" class="form-control" id="ville">
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
                                <input type="text" class="form-control" id="motPasseActuel">
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
                                <input type="text" class="form-control" id="nouveauMotPasse">
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
                                <input type="text" class="form-control" id="confirmationMotPasse">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label>Crédit : </label>
                        </div>
                        <div class="col-8">
                            <div class="input-group input-group-sm mb-3">
                                <label></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <label></label>
                        </div>
                        <div class="col-8">
                            <button type="button" class="btn btn-outline-dark">Enregistrer</button>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col-4">
                            <button type="button" class="btn btn-outline-dark">Supprimer mon compte</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
