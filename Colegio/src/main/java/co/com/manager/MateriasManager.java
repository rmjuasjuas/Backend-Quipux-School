package co.com.manager;

import java.util.ArrayList;
import java.util.List;
import co.com.model.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import co.com.bd.BaseDatos;
public class MateriasManager {
	
	public List<Materia> getTodasLasMateriasSinBd() {
		List<Materia> materias = new ArrayList<Materia>();

		for (int i = 0; i < 3 ; i++) {
			
			Materia mat = new Materia();
			mat.setIdMateria(i);
			mat.setNombre("Materia " + i);
			
			materias.add(mat);
		}
		
		return materias;
	}

	// Método para insertar materias
    public boolean insertarMateria(Materia materia) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean exito = false;
        
        try {
            conn = BaseDatos.getConnection();
            ps = conn.prepareStatement(
                "INSERT INTO Materias (nombre) VALUES (?)"
            );
            ps.setString(1, materia.getNombre());
            
            int filasAfectadas = ps.executeUpdate();
            exito = filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exito;
    }
    
    // Método para obtener todas las materias desde BD
    public List<Materia> getTodasLasMaterias() {
        List<Materia> materias = new ArrayList<Materia>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        
        try {
            conn = BaseDatos.getConnection();
            ps = conn.prepareStatement("SELECT * FROM Materias");
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Materia mat = new Materia();
                mat.setIdMateria(rs.getInt("idMateria"));
                mat.setNombre(rs.getString("nombre"));
                materias.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return materias;
    }
}