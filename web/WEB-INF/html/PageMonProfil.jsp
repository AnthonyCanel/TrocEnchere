<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <!--BOOTSTRAP CSS-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <title>Profil</title>
</head>
<body>
<header>
  <div class="col-6">
    <a class="btn btn-white m-5 mt-3" href="${pageContext.request.contextPath}/PageAccueilEnchere">
      <h1>ENI Enchère</h1>
    </a>
  </div>
  <div class="col-6">
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
<div class="container-xl d-flex justify-content-center">
  <div class="shadow p-5 w-50 d-flex justify-content-center">
    <div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Pseudo : </label>
        </div>
        <div class="col mt-2 mb-2">
          <label></label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Nom :</label>
        </div>
        <div class="col mt-2 mb-2">
          <label></label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Prénom :</label>
        </div>
        <div class="col mt-2 mb-2">
          <label></label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Email : </label>
        </div>
        <div class="col mt-2 mb-2">
          <label></label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Téléphone : </label>
        </div>
        <div class="col mt-2 mb-2">
          <label></label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Rue </label>
        </div>
        <div class="col mt-2 mb-2">
          <label></label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Code postal </label>
        </div>
        <div class="col mt-2 mb-2">
          <label>dgwdghwdgh</label>
        </div>
      </div>
      <div class="row">
        <div class="col mt-2 mb-2">
          <label>Ville </label>
        </div>
        <div class="col mt-2 mb-2">
          <label></label>
        </div>
      </div>
      <div class="row m-5 mt-3">
        <div class="col mt-2 mb-2">
          <button type="button" class="btn btn-outline-dark">Modifier</button>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
