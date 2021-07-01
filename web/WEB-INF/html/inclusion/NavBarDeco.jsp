<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-5">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/PageAccueilEnchere">ENI Enchère</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/PageConnexion">S'inscrire ou connecter</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="row text-center text-danger my-3">
    <h5>${messageErreur}</h5>
</div>