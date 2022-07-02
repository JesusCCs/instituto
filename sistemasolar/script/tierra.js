

window.addEventListener('wheel', function (event) {
    // zona 1
    let tierraInfo = document.getElementById("tierra-info")
    let tierraContenido = this.document.getElementById("tierra-contenido")
    let tierraTitulo = this.document.getElementById("tierra-titulo")


    // zona 2
    let datos = this.document.getElementsByClassName("dato")




    let main = this.document.getElementsByTagName("main")[0];
    let posicion_pagina = main.scrollLeft;

    if (event.deltaY < 0) {
        // zona 1
        if (!tierraInfo.classList.contains("animacion-info-a")) tierraInfo.classList.add("animacion-info-a")
        tierraInfo.classList.remove("animacion-info-r")
        tierraContenido.classList.add("fadeIn")
        tierraContenido.classList.remove("fadeOut")
        tierraTitulo.classList.remove("fadeIn")
        tierraTitulo.classList.add("fadeOut", "faster")

        //zona 2
        // for (const dato of datos) {
        //     dato.classList.add("fadeOutDown")
        //     dato.classList.remove("fadeInUp")
        // }

    } else {
        // zona 1
        if (!tierraInfo.classList.contains("animacion-info-r")) tierraInfo.classList.add("animacion-info-r")
        tierraInfo.classList.remove("animacion-info-a")
        tierraContenido.classList.add("fadeOut", "faster")
        tierraContenido.classList.remove("fadeIn")
        tierraTitulo.classList.remove("fadeOut")
        tierraTitulo.classList.add("fadeIn")
        tierraTitulo.classList.remove("escondido")

        //zona 2
        for (const dato of datos) {
            dato.classList.add("fadeInDown")
            dato.classList.remove("escondido")
            dato.classList.remove("fadeOutDown")
        }

    }

})

window.addEventListener('mousemove', function (event) {


})

