let pokedex = document.getElementById('pokedex');
let welcomePage = document.getElementById("welcomepage");
let namePage = document.getElementById("namepage");
let typePage = document.getElementById("typepage");
let movePage = document.getElementById("movepage");
let pokedexNumberPage = document.getElementById("pokedexnumberpage");

document.getElementById('registratie-form').addEventListener('submit', function (event) {
    event.preventDefault();

    fetch("/pokemon/name", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'  // JSON!
        },
    })
        .then(response => {
            pokedex.style.display = 'none';
            welcomePage.style.display = 'none';
            namePage.style.display = 'block';
        })
        .catch(error => {
            console.error('AJAX error:', error);
        });
});

document.getElementById('name-form').addEventListener('submit', function (event) {
    event.preventDefault();

    fetch("/pokemon/move", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'  // JSON!
        },
    })
        .then(response => {
            pokedex.style.display = 'none';
            welcomePage.style.display = 'none';
            namePage.style.display = 'none';
            typePage.style.display = 'block';
        })
        .catch(error => {
            console.error('AJAX error:', error);
        });
});

document.getElementById('type-form').addEventListener('submit', function (event) {
    event.preventDefault();

    fetch("/pokemon/move", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'  // JSON!
        },
    })
        .then(response => {
            pokedex.style.display = 'none';
            welcomePage.style.display = 'none';
            namePage.style.display = 'none';
            typePage.style.display = 'none';
            movePage.style.display = 'block';
        })
        .catch(error => {
            console.error('AJAX error:', error);
        });
});

document.getElementById('move-form').addEventListener('submit', function (event) {
    event.preventDefault();

    fetch("/pokemon/move", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'  // JSON!
        },
    })
        .then(response => {
            pokedex.style.display = 'none';
            welcomePage.style.display = 'none';
            namePage.style.display = 'none';
            typePage.style.display = 'none';
            movePage.style.display = 'none';
            pokedexNumberPage.style.display = 'block';
        })
        .catch(error => {
            console.error('AJAX error:', error);
        });
});

document.getElementById('pokedex-form').addEventListener('submit', function (event) {
    event.preventDefault();
    document.location.href = "/pap";
});

// document.getElementById('pokedex-form').addEventListener('submit', function (event) {
//     console.log('Registratie');
//
//     event.preventDefault();
//     fetch("/pokemon/move", {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'  // JSON!
//         },
//     })
//         .then(response => {
//             pokedex.style.display = 'block';
//             console.log("6");
//         })
//         .catch(error => {
//             console.error('AJAX error:', error);
//         });
// });
