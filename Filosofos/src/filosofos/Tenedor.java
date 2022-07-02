package filosofos;

public class Tenedor {

    private boolean disponible;
    private final String id;

    public Tenedor(String id) {
        this.id = id;
        this.disponible = true;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }
}
