
const info1 = document.getElementById("info-1");
const info2 = document.getElementById("info-2");
document.getElementsByClassName("flecha")[0].onclick = function () { animar() };

function animar() {
    if (info1.classList.contains('escondido')) {
        add_animacion(info2, 'fadeOut')
        setTimeout(aparecer_info, 880, info1);
        setTimeout(eliminarAnimacion_info, 880, info2);
    } else {
        add_animacion(info1, 'fadeOut')
        setTimeout(aparecer_info, 880, info2);
        setTimeout(eliminarAnimacion_info, 880, info1);
    }
}

function add_animacion(elemento, tipo) {
    elemento.classList.add('animated', tipo)
}

function aparecer_info(elemento) {
    elemento.classList.add('animated', 'fadeIn');
    elemento.classList.remove('escondido');
}

function eliminarAnimacion_info(elemento) {
    elemento.classList.add('escondido');
    elemento.classList.remove('animated');
    elemento.classList.remove('fadeIn');
    elemento.classList.remove('fadeOut');
}

const infoPhobos = document.getElementById("info-phobos");
const infoDeimos = document.getElementById("info-deimos");
document.getElementById("deimos").onclick = function () { animarDesplegable(this) };
document.getElementById("phobos").onclick = function () { animarDesplegable(this) };

function animarDesplegable(boton) {
    if (boton.id == "deimos") {
        if (!infoPhobos.classList.contains('escondido')) {
            add_animacion(infoPhobos, 'bounceOutLeft')
            setTimeout(aparecerDesplegable, 240, infoDeimos, 'bounceInDown');
            setTimeout(eliminarAnimacion_desplegable, 880, infoPhobos);
        } else if (!infoDeimos.classList.contains('escondido')) {
            add_animacion(infoDeimos, 'bounceOutUp')
            setTimeout(eliminarAnimacion_desplegable, 880, infoDeimos);
        } else {
            aparecerDesplegable(infoDeimos, 'bounceInDown');
        }
    } else {
        if (!infoDeimos.classList.contains('escondido')) {
            add_animacion(infoDeimos, 'bounceOutUp')
            setTimeout(aparecerDesplegable, 350, infoPhobos, 'bounceInLeft');
            setTimeout(eliminarAnimacion_desplegable, 880, infoDeimos);
        } else if (!infoPhobos.classList.contains('escondido')) {
            add_animacion(infoPhobos, 'bounceOutLeft')
            setTimeout(eliminarAnimacion_desplegable, 880, infoPhobos);
        } else {
            aparecerDesplegable(infoPhobos, 'bounceInLeft');
        }
    }
}

function aparecerDesplegable(elemento, tipo) {
    elemento.classList.add('animated', tipo);
    elemento.classList.remove('escondido');
}

function eliminarAnimacion_desplegable(elemento) {
    elemento.classList.add('escondido');
    elemento.classList.remove('animated');
    elemento.classList.remove('bounceInLeft');
    elemento.classList.remove('bounceOutLeft');
    elemento.classList.remove('bounceInDown');
    elemento.classList.remove('bounceOutUp');
}