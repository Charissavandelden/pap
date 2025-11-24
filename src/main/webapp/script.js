let pokedex = document.getElementById('pokedex');
let stap1 = document.getElementById("stap1");
let stap2 = document.getElementById("stap2");
let stap3 = document.getElementById("stap3");
let stap4 = document.getElementById("stap4");
let stap5 = document.getElementById("stap5");
let stap6 = document.getElementById("stap6");


document.getElementById('registratie-form').addEventListener('submit', function (event) {
    console.log('Registratie');

    event.preventDefault();

    fetch("/pap", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'  // JSON!
        },
    })
        .then(response => {
            pokedex.style.display = 'none';
            stap1.style.display = 'none';
            stap2.style.display = 'block';

            console.log("1");

            // this.hideLoading();
            // if (onSuccess) onSuccess(data);
        })
        .catch(error => {
            console.error('AJAX error:', error);
        });


});

document.getElementById('name-form').addEventListener('submit', function (event) {
    event.preventDefault();

    const name= sessionStorage.getItem("name")
    pokedex.style.display = 'none';
    stap1.style.display = 'none';
    stap2.style.display = 'none';
    stap3.style.display = 'block';

    console.log("2");

    if (name == null || name.length === 0) {
        const errorText = "naam mag niet leeg zijn";
        showErrorPopup(errorText);
    }
});

document.getElementById('type-form').addEventListener('submit', function (event) {
    event.preventDefault();


    const type= sessionStorage.getItem("type")
    const types = ["Normal", "Fire", "Water", "Grass", "Electric", "Ice", "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy"];

    pokedex.style.display = 'none';
    stap1.style.display = 'none';
    stap2.style.display = 'none';
    stap3.style.display = 'none';
    stap4.style.display = 'block';

    console.log("hallotest");

    if (type == null) {
        const errorText = "type mag niet leeg zijn";
        showErrorPopup(errorText);
    }

    if (type != null && types.includes(type)) {
        const errorText = "type is invalid: " + type;
        showErrorPopup(errorText);
    }
});

document.getElementById('move-form').addEventListener('submit', function (event) {
    event.preventDefault();

    pokedex.style.display = 'none';
    stap1.style.display = 'none';
    stap2.style.display = 'none';
    stap3.style.display = 'none';
    stap4.style.display = 'none';
    stap5.style.display = 'block';

    const move= sessionStorage.getItem("move")


    if (move == null || move.length === 0) {
        const errorText = "naam mag niet leeg zijn";
        showErrorPopup(errorText);
    }

    console.log("4");
});

document.getElementById('pokedex-form').addEventListener('submit', function (event) {
    event.preventDefault();
    document.location.href = "/pap";
});

function displayValidationErrors(errors) {
    errors.forEach(error => {

    })
}

function showErrorPopup(s) {
    // The alert() function displays a dialog box with a specified message
    alert(s);
}

