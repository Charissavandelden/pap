let pokedex = document.getElementById('pokedex');
let welcomePage = document.getElementById("welcomepage");
let namePage = document.getElementById("namepage");
let typePage = document.getElementById("typepage");
let movePage = document.getElementById("movepage");
let pokedexNumberPage = document.getElementById("pokedexnumberpage");

// Volg de huidige pagina
let currentPage = 'welcome';

// Hulpfunctie om een specifieke pagina te tonen
function showPage(pageName) {
    pokedex.style.display = 'none';
    welcomePage.style.display = 'none';
    namePage.style.display = 'none';
    typePage.style.display = 'none';
    movePage.style.display = 'none';
    pokedexNumberPage.style.display = 'none';

    switch(pageName) {
        case 'welcome':
            welcomePage.style.display = 'block';
            pokedex.style.display = 'block';
            break;
        case 'name':
            namePage.style.display = 'block';
            break;
        case 'type':
            typePage.style.display = 'block';
            break;
        case 'move':
            movePage.style.display = 'block';
            break;
        case 'pokedexNumber':
            pokedexNumberPage.style.display = 'block';
            break;
    }
    currentPage = pageName;
}

// Navigeer naar een nieuwe pagina en zet deze in de geschiedenis via de History API.
function navigateTo(pageName) {
    showPage(pageName);
    history.pushState({ page: pageName }, '', '#' + pageName);
}

// Terugknop van de browser
window.addEventListener('popstate', function(event) {
    if (event.state && event.state.page) {
        showPage(event.state.page);
    } else {
        showPage('welcome');
    }
});

// Initialize
history.replaceState({ page: 'welcome' }, '', '');

document.getElementById('registratie-form').addEventListener('submit', function (event) {
    event.preventDefault();

    fetch("/pokemon/name", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            navigateTo('name');
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
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            navigateTo('type');
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
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            navigateTo('move');
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
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            navigateTo('pokedexNumber');
        })
        .catch(error => {
            console.error('AJAX error:', error);
        });
});

document.getElementById('pokedex-form').addEventListener('submit', function (event) {
    event.preventDefault();
    document.location.href = "/pap";
});

document.addEventListener('DOMContentLoaded', function() {
    const backButtons = document.querySelectorAll('[id^="back-button-"]');
    backButtons.forEach(button => {
        button.addEventListener('click', goBack);
    });
});

function goBack() {
    history.back();
}

// place holder voor dropdown
function selectPokemon(pokemonValue) {
    console.log('Selected Pokemon:', pokemonValue);

    if (pokemonValue === 'pikachu') {
        console.log('Displaying Pikachu');
    } else if (pokemonValue.startsWith('placeholder')) {
        console.log('Empty slot selected');
    } else {
        console.log('Displaying other Pokemon:', pokemonValue);
    }
}
