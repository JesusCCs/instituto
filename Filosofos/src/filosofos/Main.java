package filosofos;

import filosofos.hilo.Filosofo;
import filosofos.seccioncritica.Mesa;

public class Main {

    public static void main(String[] args) {

        Mesa mesa = new Mesa();

        Tenedor rojo = new Tenedor("rojo");
        Tenedor amarillo = new Tenedor("amarillo");
        Tenedor blanco = new Tenedor("blanco");
        Tenedor negro = new Tenedor("negro");
        Tenedor azul = new Tenedor("azul");

        Filosofo aristoteles = new Filosofo("Aristóteles",mesa);
        aristoteles.setTenedores(rojo, amarillo);

        Filosofo democrito = new Filosofo("Demócrito",mesa);
        democrito.setTenedores(amarillo, blanco);

        Filosofo platon = new Filosofo("Platón",mesa);
        platon.setTenedores(blanco,negro);

        Filosofo pitagoras = new Filosofo("Pitágoras",mesa);
        pitagoras.setTenedores(negro,azul);

        Filosofo tales = new Filosofo("Tales",mesa);
        tales.setTenedores(azul,rojo);

        aristoteles.start();
        democrito.start();
        platon.start();
        pitagoras.start();
        tales.start();
    }
}
