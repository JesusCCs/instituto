let divs = document.getElementsByTagName("div");
let links = document.getElementsByTagName("a");


for (const link of links) {
    link.onmouseover = function () { seleccion(this) }
    link.onmouseout = function () { deseleccion() }
}

function seleccion(elemento) {
    let clase = elemento.textContent.toLowerCase()
    console.log(clase)

    for (const div of divs) {
        if (div.classList.contains(clase) && clase == "sol") div.classList.add("resaltar-sol")
        else if (div.classList.contains(clase)) div.classList.add("resaltar")
    }
}

function deseleccion() {
    for (const div of divs) {
        div.classList.remove("resaltar")
        div.classList.remove("resaltar-sol")
    }
}


document.getElementById("venus").onclick = function () { activar_venus() }

function activar_venus() {
    let fondo = document.getElementById("fondo")
    fondo.classList.add('fadeIn')
    fondo.classList.remove('escondido')

    let contenido = document.getElementById("venus-contenido")
    contenido.classList.add('slideInUp')
    contenido.classList.remove('escondido')
}

document.getElementById("x").onclick = function () { desactivar_venus() }

function desactivar_venus() {
    let fondo = document.getElementById("fondo")
    fondo.classList.add('fadeOut')
    fondo.classList.remove('fadeIn')
    setTimeout(al_inicio, 880, fondo)

    let contenido = document.getElementById("venus-contenido")
    contenido.classList.add('slideOutDown')
    contenido.classList.remove('slideInUp')
    setTimeout(al_inicio, 880, contenido)
}

function al_inicio(elemento) {
    elemento.classList.remove('fadeOut')
    elemento.classList.remove('slideOutDown')
    elemento.classList.add('escondido')
}