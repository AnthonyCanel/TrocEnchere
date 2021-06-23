<!doctype html>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Accueil</title>
</head>
<body class="container-fluid">
<div class="row">
    <div class="d-flex justify-content-around">
        <div class="d-flex">
            <h1>ENI-Encheres</h1>
        </div>
        <div class="d-flex">
            <a href="PageConnexion.jsp">S'inscrire - Se connecter</a>
        </div>
    </div>
</div>
<div class="row">
    <div class="columns">
        <h2 class="text-center">Liste des Enchères</h2>
    </div>
</div>
<div class="row">
    <label class="text-start mb-3">Filtres :</label>
</div>

<div class="row align-items-center">

    <div class="col col-6">
        <div class="input-group mb-3">
            <span class="input-group-text">Search</span>
            <input type="text" class="form-control" />
        </div>
        <div class="row">
            <div class="col">
                <label>Catégorie :</label>
            </div>
            <div class="col">
                <select class="form-select form-select-sm" aria-label=".form-select-sm example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>
        </div>
    </div>

    <div class="col col-6">
        <button type="button" class="btn btn-outline-secondary btn-lg mb-3">Rechercher</button>
    </div>

</div>
<div class="row ">
    <!-- la boucle est a mettre ici-->
    <div class="col">
            <span class="border border-primary">
                <div class="row ">
                    <div class="col-3">
                        <img src="https://www.google.com/url?sa=i&url=https%3A%2F%2Flaboutiquedessaucissons.fr%2Fles-saucissons-de-nos-regions%2F31-saucisson-sec-pur-porc-des-cevennes.html&psig=AOvVaw3fWGcYdpgsTgriXSNamHBK&ust=1624528671658000&source=images&cd=vfe&ved=2ahUKEwiZ4L2Ev63xAhXODuwKHVzzCL4Qr4kDegUIARDhAQ"></img>

                    </div>
                    <div class="col">
                        <label>nom de l'article</label>
                        <label>Prix : 654 banane</label>
                        <label>Fin de l'enchère : 654 banane</label>
                        <label>Vendeur : 654 banane</label>
                    </div>
                </div>

            </span>
    </div>
    <!-- fin de la boucle-->
    <!-- article complementaire a enlever une fois la boucle faite-->
    <div class="col">
            <span class="border border-primary">
                <div class="row ">
                    <div class="col-3">
                        <img src="https://www.google.com/url?sa=i&url=https%3A%2F%2Flaboutiquedessaucissons.fr%2Fles-saucissons-de-nos-regions%2F31-saucisson-sec-pur-porc-des-cevennes.html&psig=AOvVaw3fWGcYdpgsTgriXSNamHBK&ust=1624528671658000&source=images&cd=vfe&ved=2ahUKEwiZ4L2Ev63xAhXODuwKHVzzCL4Qr4kDegUIARDhAQ"></img>

                    </div>
                    <div class="col">
                        <label>nom de l'article</label>
                        <label>Prix : 654 banane</label>
                        <label>Fin de l'enchère : 654 banane</label>
                        <label>Vendeur : 654 banane</label>
                    </div>
                </div>

            </span>
    </div>
    <!-- fin de l'article complementaire a enlever une fois la boucle faite-->
</div>
</body>
</html>
