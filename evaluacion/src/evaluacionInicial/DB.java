package evaluacionInicial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/evaluacion_inicial";
    private static final String USER = "root";
    private static final String PASS = "m1sql";
    
    private Connection conexion;
    
    public DB() {
        conexion = null;
    }
    
    public void openDB() {
	    if (conexion != null) return;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(URL,USER,PASS);
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        }
    }
    
    public boolean findUser(String username, String pass) throws SQLException {
    	
	    PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios where username=? and password=?");
	    ps.setString(1, username);
	    ps.setString(2, pass);
	    ResultSet rs = ps.executeQuery();
	    	
	    return rs.isBeforeFirst();  
    }
    
    public boolean findUser(String username) throws SQLException {
    	
	    PreparedStatement ps = conexion.prepareStatement("SELECT * FROM usuarios where username=?");
	    ps.setString(1, username);
	    ResultSet rs = ps.executeQuery();
	    	
	    return rs.isBeforeFirst();  
    }
    
    public boolean createUser(String username, String pass) {
		try {
			if (findUser(username)) return false;
			
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios VALUES(id, ?, ?)");
			ps.setString(1, username);
		    ps.setString(2, pass);
		    ps.executeUpdate();
		    
		    return true;  
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}  
    }
    
    public void closeDB(){
        if (conexion == null) return;

        try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			conexion = null;
		}
    }
}
