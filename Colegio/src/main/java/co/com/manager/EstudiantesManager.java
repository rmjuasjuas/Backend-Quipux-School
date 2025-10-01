package co.com.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.bd.BaseDatos;
import co.com.model.Estudiante;
import co.com.model.EstudianteMateria;

public class EstudiantesManager {
    
    // Método para obtener todos los estudiantes
    public List<Estudiante> getTodosLosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        
        try {
            conn = BaseDatos.getConnection();
            ps = conn.prepareStatement("SELECT * FROM Estudiantes");
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Estudiante est = new Estudiante();
                est.setIdEstudiante(rs.getInt("idEstudiante"));
                est.setIdentificacion(rs.getString("identificacion"));
                est.setNombre(rs.getString("nombre"));
                estudiantes.add(est);
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
        return estudiantes;
    }
    
    // Método para insertar un estudiante
    public boolean insertarEstudiante(Estudiante estudiante) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean exito = false;
        
        try {
            conn = BaseDatos.getConnection();
            ps = conn.prepareStatement(
                "INSERT INTO Estudiantes (identificacion, nombre) VALUES (?, ?)"
            );
            ps.setString(1, estudiante.getIdentificacion());
            ps.setString(2, estudiante.getNombre());
            
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
    
    // Método ESTRELLA: Obtener estudiantes con sus materias (JOIN)
    public List<EstudianteMateria> getEstudiantesConMaterias() {
        List<EstudianteMateria> resultado = new ArrayList<EstudianteMateria>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        
        try {
            conn = BaseDatos.getConnection();
            // JOIN entre Estudiantes, EstudiantesMateria y Materias
            String sql = "SELECT e.idEstudiante, e.identificacion, e.nombre AS nombreEstudiante, " +
                        "m.idMateria, m.nombre AS nombreMateria " +
                        "FROM Estudiantes e " +
                        "INNER JOIN EstudiantesMateria em ON e.idEstudiante = em.idEstudiante " +
                        "INNER JOIN Materias m ON em.idMateria = m.idMateria " +
                        "ORDER BY e.nombre, m.nombre";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                EstudianteMateria em = new EstudianteMateria();
                em.setIdEstudiante(rs.getInt("idEstudiante"));
                em.setIdentificacion(rs.getString("identificacion"));
                em.setNombreEstudiante(rs.getString("nombreEstudiante"));
                em.setIdMateria(rs.getInt("idMateria"));
                em.setNombreMateria(rs.getString("nombreMateria"));
                resultado.add(em);
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
        return resultado;
    }
    
    // Método para inscribir un estudiante en una materia
    public boolean inscribirEstudianteEnMateria(int idEstudiante, int idMateria) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean exito = false;
        
        try {
            conn = BaseDatos.getConnection();
            ps = conn.prepareStatement(
                "INSERT INTO EstudiantesMateria (idEstudiante, idMateria) VALUES (?, ?)"
            );
            ps.setInt(1, idEstudiante);
            ps.setInt(2, idMateria);
            
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
}