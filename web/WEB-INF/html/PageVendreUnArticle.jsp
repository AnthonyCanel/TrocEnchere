<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Création Compte</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="container-fluid">
<header class=" row m-2">
    <div class="col-4">
        <h1 class="m-5 mt-3">ENI Enchère</h1>
    </div>
    <div class="col-8">
        <h3 class="m-5 ml-1">Nouvelle vente</h1>
    </div>
</header>
<div class=" row m-2 p-3 ">
    <div class="col-4">
        <div class="w-100 bg-secondary shadow"><img src="" alt=""></div>
    </div>
    <div class="col-8 shadow p-3">

        <div class=" row m-2">
            <div class="col-4">
                <label for="article">Article :</label>
            </div>
            <div class="col-8">
                <input type="text" id="article">
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <label for="description">Description :</label>
            </div>
            <div class="col-8">
                <input type="area" id="description"  row m-2s="5" cols="33">
            </div>
        </div>

        <div class=" row m-2 m-2">
            <div class="col-4">
                <label for="catégorie">Catégorie</label>
            </div>
            <div class="col-8">
                <select id="catégorie">
                    <option value=""> -- Choisir une catégorie --</option>

                </select>
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <label for="photo">Photo de l'article</label>
            </div>
            <div class="col-8">
                <input type="button" id="photo" value="UPLOADER">
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <label for="prix">Mise à prix :</label>
            </div>
            <div class="col-8">
                <input type="number" min="0" max="150" id="prix">
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <label for="début">Début de l'enchère</label>
            </div>
            <div class="col-8">
                <input type="date" id="début">
            </div>
        </div>

        <div class=" row m-2">
            <div class="col-4">
                <label for="fin">Fin de l'enchère</label>
            </div>
            <div class="col-8">
                <input type="date" id="fin">
            </div>
        </div>

        <div class="border border-dark m-4">
            <div class=" row m-2">
                <h3>Retrait</h3>
                <div class=" row m-2">
                    <div class="col-4">
                        <label for="rue">Rue :</label>
                    </div>
                    <div class="col-8">
                        <input type="text" id="rue">
                    </div>
                </div>

                <div class=" row m-2">
                    <div class="col-4">
                        <label for="CP">Code postal :</label>
                    </div>
                    <div class="col-8">
                        <input type="text"   id="CP">
                    </div>
                </div>

                <div class=" row m-2">
                    <div class="col-4">
                        <label for="ville">Ville</label>
                    </div>
                    <div class="col-8">
                        <input type="text"   id="ville">
                    </div>
                </div>
            </div>
        </div>

        <div class=" row m-2">
            <div class="col">
                <input type="button" value="Enregistrer">
            </div>
            <div class="col">
                <input type="button" value="Annuler">
            </div>
            <div class="col">
                <input type="button" value="Annuler la vente">
            </div>
        </div>
    </div>
</div>
</body>
</html>