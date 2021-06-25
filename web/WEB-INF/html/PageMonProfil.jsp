<%--
  Created by IntelliJ IDEA.
  User: ajouhanneau2021
  Date: 24/06/2021
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <!--BOOTSTRAP CSS-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <title>Profil</title>
</head>
<body>
<header>
  <h1 class="m-5 mt-3">ENI-Enchères</h1>
</header>
<div class="container-xl d-flex justify-content-center">
  <div class="shadow p-5 w-50 d-flex justify-content-center">
    <div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Pseudo : </label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="pseudo">${pseudo}</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Nom :</label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="nom">${nom}</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Prénom :</label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="prenom">${prenom}</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Email : </label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="email">${email}</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Téléphone : </label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="telephone">${telephone}</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Rue </label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="rue">${rue}</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Code postal </label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="CP">${CP}</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Ville </label>
        </div>
        <div class="col mt-2 mb-2">
          <label name="ville">${ville}</label>
        </div>
      </div>
      <div class="row m-5 mt-3">
        <div class="col mt-2 mb-2">
          <a href="${pageContext.request.contextPath}/PageModifierProfil"><button type="button" class="btn btn-outline-dark">Modifier</button></a>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
