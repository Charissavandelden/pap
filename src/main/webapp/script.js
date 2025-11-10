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

    fetch("/pokemon/name", {
        method: 'GET',
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

    fetch("/pokemon/move", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'  // JSON!
        },
    })
        .then(response => {


            pokedex.style.display = 'none';
            stap1.style.display = 'none';
            stap2.style.display = 'none';
            stap3.style.display = 'block';

            console.log("2");


            // this.hideLoading();
            // if (onSuccess) onSuccess(data);
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
            stap1.style.display = 'none';
            stap2.style.display = 'none';
            stap3.style.display = 'none';
            stap4.style.display = 'block';

            console.log("3");


            // this.hideLoading();
            // if (onSuccess) onSuccess(data);
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
            stap1.style.display = 'none';
            stap2.style.display = 'none';
            stap3.style.display = 'none';
            stap4.style.display = 'none';
            stap5.style.display = 'block';

            console.log("4");
        })
        .catch(error => {
            console.error('AJAX error:', error);
        });
});

document.getElementById('pokedex-form').addEventListener('submit', function (event) {
    event.preventDefault();
    document.location.href = "/welcome";
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
