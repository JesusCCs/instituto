let sol = document.getElementById("fondo-sol");
let eclipse = document.getElementById("fondo-eclipse");

links = document.getElementsByClassName("eclipse");

for (const link of links) {
    link.onclick = function () { cambiar() };
}


function cambiar() {
    if (!sol.classList.contains("fadeOut")) {
        sol.classList.remove("fadeIn");
        sol.classList.add("fadeOut");
        eclipse.classList.remove("fadeOut");
        eclipse.classList.add("fadeIn");
    } else {
        eclipse.classList.remove("fadeIn");
        eclipse.classList.add("fadeOut");
        sol.classList.remove("fadeOut");
        sol.classList.add("fadeIn");
    }
}