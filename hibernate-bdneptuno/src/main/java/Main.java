
import DTO.CategoriasDTO;
import DTO.ClientesDTO;
import DTO.ProductosDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class Main {

    private static Session session;

    public static void main(String[] args) {

        // Abro sesión
        session = HibernateUtil.getSession();

        try {
            // Inicio transacción
            session.getTransaction().begin();

            consultaTodosLosProductos();

            consultaTodosLosProductosYCategorias();

            consultaTodosLosProductosYCategorias("Pescado/Marisco");

            consultaContactoYPedidos("Maria Anders");

            consultaContactoYPedidos(20);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /**
     * EJEMPLO - Consulta de todos los Productos
     */
    static void consultaTodosLosProductos() {
        System.out.println("");
        System.out.println("EJEMPLO Consulta de todos los Productos:");

        Query<ProductosDTO> query = session.createQuery("FROM ProductosDTO", ProductosDTO.class);

        List<ProductosDTO> lista = query.list();

        for (ProductosDTO prod : lista) {
            System.out.println(prod.getProducto());
        }
    }

    /**
     * EJEMPLO - Consulta de todos los Productos y Categoria
     */
    static void consultaTodosLosProductosYCategorias() {
        System.out.println("");
        System.out.println("EJEMPLO Consulta de todos los Productos y Categoria:");

        Query<Object[]> query = session.createQuery("SELECT p, p.categoria FROM ProductosDTO p", Object[].class);

        List<Object[]> lista1 = query.list();

        for (Object[] elem : lista1) {
            ProductosDTO p = (ProductosDTO) elem[0];
            System.out.println("PRODUCTO: " + p.getProducto());

            CategoriasDTO cat = (CategoriasDTO) elem[1];
            System.out.println("CATEGORIA DESCRIPCION: " + cat.getDescripcion());

            System.out.println("----------------------------------------------------------------");
        }
    }

    /**
     * EJEMPLO - Consulta de todos los Productos y categoría, con Categoría como parámetro
     */
    static void consultaTodosLosProductosYCategorias(String categoria) {
        System.out.println("");
        System.out.println("----- EJEMPLO Consulta de todos los Productos y Categoria: -----");

        Query<Object[]> query = session.createQuery(
                "SELECT p, p.categoria, p.categoria.categoria " +
                "FROM ProductosDTO p " +
                "WHERE p.categoria.categoria = :categoria",
                Object[].class);

        query.setParameter("categoria", categoria);

        List<Object[]> lista = query.list();

        for (Object[] elem : lista) {
            ProductosDTO p = (ProductosDTO) elem[0];
            System.out.println("PRODUCTO: " + p.getProducto());

            CategoriasDTO cat = (CategoriasDTO) elem[1];
            System.out.println("CATEGORIA DESCRIPCION: " + cat.getDescripcion());

            System.out.println("CATEGORIA: " + elem[2]);

            System.out.println("----------------------------------------------------------------");
        }

        System.out.println("----------------------------------------------------------------");
    }

    /**
     * EJEMPLO - los datos del contacto Maria Anders y cuántos pedidos
     */
    static void consultaContactoYPedidos(String contacto) {
        System.out.println("");
        System.out.println("EJEMPLO Consulta los datos del contacto Maria Anders y cuántos pedido tiene:");

        Query<Object[]> query = session.createQuery(
                "SELECT c, SIZE(c.pedidos) FROM ClientesDTO c WHERE c.contacto=:contacto", Object[].class);

        query.setParameter("contacto", contacto);

        Object[] elem = query.uniqueResult();

        ClientesDTO c = (ClientesDTO) elem[0];
        int numPedidos = (Integer) elem[1];
        System.out.println(c.getContacto() + "  " + numPedidos);
    }

    /**
     * EJEMPLO - Consulta los clientes con más de 20 pedidos
     */
    static void consultaContactoYPedidos(int numMinPedidos) {
        System.out.println("");
        System.out.println("EJEMPLO Consulta los clientes con más de 20 pedidos:");

        Query<ClientesDTO> query = session.createQuery(
                "SELECT c FROM ClientesDTO c WHERE SIZE(c.pedidos) > :numPedidos", ClientesDTO.class);

        query.setParameter("numPedidos", numMinPedidos);

        List<ClientesDTO> lista = query.list();

        lista.forEach(System.out::println);
    }
}