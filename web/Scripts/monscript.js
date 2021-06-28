function GestionCheckBoxAchats(IdRadioButton,ChecBox1,ChecBox2,ChecBox3,ChecBox4,ChecBox5,ChecBox6){
    if (IdRadioButton.checked){
        document.getElementById(ChecBox1).disabled = false;
        document.getElementById(ChecBox1).checked = true;
        document.getElementById(ChecBox2).disabled = false;
        document.getElementById(ChecBox3).disabled = false;
        document.getElementById(ChecBox4).disabled = true;
        document.getElementById(ChecBox5).disabled = true;
        document.getElementById(ChecBox6).disabled = true;
        document.getElementById(ChecBox4).checked = false;
        document.getElementById(ChecBox5).checked = false;
        document.getElementById(ChecBox6).checked = false;
    }
}
function GestionCheckBoxVentes(IdRadioButton,ChecBox1,ChecBox2,ChecBox3,ChecBox4,ChecBox5,ChecBox6){
    if (IdRadioButton.checked){
        document.getElementById(ChecBox1).disabled = true;
        document.getElementById(ChecBox2).disabled = true;
        document.getElementById(ChecBox3).disabled = true;
        document.getElementById(ChecBox1).checked = false;
        document.getElementById(ChecBox2).checked = false;
        document.getElementById(ChecBox3).checked = false;
        document.getElementById(ChecBox4).disabled = false;
        document.getElementById(ChecBox4).checked = true;
        document.getElementById(ChecBox5).disabled = false;
        document.getElementById(ChecBox6).disabled = false;
    }
}
