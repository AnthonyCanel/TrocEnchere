<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-4">
    <div>
        <input class="form-check-input" type="radio" name="AchatsVentes" id="achats" value="a" checked>
        <label class="form-check-label" for="achats">Achats</label>
    </div>
    <div class="btn-group-vertical mt-2 mb-3" name="AchatsVentes" role="group" aria-label="Basic radio toggle button group" disabled>
        <div class="form-check form-switch">
            <input class="form-check-input" name="achat" type="radio" id="EncheresOuvertes">
            <label class="form-check-label" for="EncheresOuvertes">Enchères ouvertes</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" name="achat" type="radio" id="EncheresEnCours">
            <label class="form-check-label" for="EncheresEnCours">Mes enchères en cours</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" name="achat" type="radio" id="EncheresRemportes">
            <label class="form-check-label" for="EncheresRemportes">Mes enchères remportées</label>
        </div>
    </div>
</div>
<div class="col-4">
    <div class="form-check">
        <input class="form-check-input" type="radio" name="AchatsVentes" id="mesVentes" value="v">
        <label class="form-check-label" for="mesVentes">Mes ventes</label>
    </div>
    <div class="btn-group-vertical mt-2 mb-3" name="AchatsVentes" role="group" aria-label="Basic radio toggle button group">
        <div class="form-check form-switch">
            <input class="form-check-input" name="ventes" type="radio" id="VentesEnCours">
            <label class="form-check-label" for="VentesEnCours">Mes ventes en cours</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" name="ventes" type="radio" id="VentesNonDebutes">
            <label class="form-check-label" for="VentesNonDebutes">Ventes non débutées</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" name="ventes" type="radio" id="VentesTerminees">
            <label class="form-check-label" for="VentesTerminees">Ventes terminés</label>
        </div>
    </div>
</div>
