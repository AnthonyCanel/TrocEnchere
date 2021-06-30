<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-4">
    <div>
        <input class="form-check-input" type="radio" name="AchatsVentes" id="achats" value="a" >
        <label class="form-check-label" for="achats">Achats</label>
    </div>
    <div class="btn-group-vertical mt-2 mb-3" id="groupAchats" name="AchatsVentes" role="group" aria-label="Basic radio toggle button group">
        <div class="form-check form-switch">
            <input name="achatsH" type="hidden" value ="encheresOuvertes">
            <input class="form-check-input" name="achats" type="checkbox" id="EncheresOuvertes">
            <label class="form-check-label" for="EncheresOuvertes">Enchères ouvertes</label>
        </div>
        <div class="form-check form-switch">
            <input name="achatsH" type="hidden" value ="mesEncheresEnCours">
            <input class="form-check-input" name="achats" type="checkbox" id="EncheresEnCours">
            <label class="form-check-label" for="EncheresEnCours">Mes enchères en cours</label>
        </div>
        <div class="form-check form-switch">
            <input name="achatsH" type="hidden" value ="mesEncheresRemportees">
            <input class="form-check-input" name="achats" type="checkbox" id="EncheresRemportes">
            <label class="form-check-label" for="EncheresRemportes">Mes enchères remportées</label>
        </div>
    </div>
</div>
<div class="col-4">
    <div class="form-check">
        <input class="form-check-input" type="radio" name="AchatsVentes" id="mesVentes" value="v">
        <label class="form-check-label" for="mesVentes">Mes ventes</label>
    </div>
    <div class="btn-group-vertical mt-2 mb-3" id="groupVentes" name="AchatsVentes" role="group" aria-label="Basic radio toggle button group">
        <div class="form-check form-switch">
            <input name="ventesH" type="hidden" value ="mesVentesEnCours">
            <input class="form-check-input" name="ventes" type="checkbox" id="VentesEnCours">
            <label class="form-check-label" for="VentesEnCours">Mes ventes en cours</label>
        </div>
        <div class="form-check form-switch">
            <input name="ventesH" type="hidden" value ="ventesNonDebutees">
            <input class="form-check-input" name="ventes" type="checkbox" id="VentesNonDebutes">
            <label class="form-check-label" for="VentesNonDebutes">Ventes non débutées</label>
        </div>
        <div class="form-check form-switch">
            <input name="ventesH" type="hidden" value ="ventesTerminees">
            <input class="form-check-input" name="ventes" type="checkbox" id="VentesTerminees">
            <label class="form-check-label" for="VentesTerminees">Ventes terminées</label>
        </div>
    </div>
    <script language="JavaScript" type="text/javascript" src="Scripts\monscript.js"></script>
</div>
