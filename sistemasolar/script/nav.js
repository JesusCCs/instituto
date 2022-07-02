navContenedor = document.getElementById("nav-contenedor")
navs = document.getElementsByClassName("navegacion")
navPrincipal = document.getElementById("nav-principal")
navControl = document.getElementById("nav-control")
navControlClick = document.querySelector("#nav-control span")

navControlClick.onclick = function () { animarNavegacion() }

function animarNavegacion() {
    if (navPrincipal.classList.contains("escondido")) {
        navControl.classList.add('nav-mover')
        navControl.style.marginRight = "1.4%"
        setTimeout(quitar_mover, 300, navControl)
        setTimeout(traer_navs, 300);
    } else {
        quitar_navs()
        setTimeout(add_mover, 500, navControl)
        setTimeout(inicio, 1000, navControl)
    }
}

function quitar_mover(elemento) {
    navPrincipal.classList.remove("escondido")
    elemento.classList.remove('nav-mover')
}

function traer_navs() {
    for (const elemento of navs) {
        elemento.classList.add('animated', 'bounceInRight')
    }
}


function quitar_navs() {
    for (const elemento of navs) {
        navPrincipal.classList.remove("bounceInRight")
        elemento.classList.add('bounceOutRight')
    }
}

function add_mover(elemento) {
    navControl.style.marginRight = "8%"
    elemento.classList.add('nav-llevar')
}

function inicio(elemento) {
    for (const elemento of navs) {
        elemento.classList.remove("animated", "bounceOutRight")
    }
    navPrincipal.classList.add("escondido")
    elemento.classList.remove('nav-llevar')
}