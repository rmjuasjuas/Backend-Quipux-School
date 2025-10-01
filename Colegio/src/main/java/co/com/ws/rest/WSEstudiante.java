package co.com.ws.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import co.com.manager.EstudiantesManager;
import co.com.manager.MateriasManager;
import co.com.model.Estudiante;
import co.com.model.EstudianteMateria;
import co.com.model.Materia;

@Path("WSEstudiante")
public class WSEstudiante {
	
	// ========== SERVICIOS DE ESTUDIANTES ==========
	
	@GET
	@Path("/getEstudiantes")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEstudiantes() {
		Gson gson = new Gson();
		EstudiantesManager manager = new EstudiantesManager();
		List<Estudiante> estudiantes = manager.getTodosLosEstudiantes();
		return gson.toJson(estudiantes);
	}
	
	@POST
	@Path("/insertarEstudiante")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertarEstudiante(String estudianteJson) {
		Gson gson = new Gson();
		Estudiante estudiante = gson.fromJson(estudianteJson, Estudiante.class);
		EstudiantesManager manager = new EstudiantesManager();
		boolean exito = manager.insertarEstudiante(estudiante);
		
		JsonObject respuesta = new JsonObject();
		respuesta.addProperty("exito", exito);
		respuesta.addProperty("mensaje", exito ? "Estudiante registrado correctamente" : "Error al registrar estudiante");
		
		return respuesta.toString();
	}
	
	// ========== SERVICIO ESTRELLA: ESTUDIANTES CON MATERIAS (PUNTOS EXTRAS) ==========
	
	@GET
	@Path("/getEstudiantesConMaterias")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEstudiantesConMaterias() {
		Gson gson = new Gson();
		EstudiantesManager manager = new EstudiantesManager();
		List<EstudianteMateria> resultado = manager.getEstudiantesConMaterias();
		return gson.toJson(resultado);
	}
	
	// ========== SERVICIO PARA INSCRIBIR ESTUDIANTE EN MATERIA ==========
	
	@POST
	@Path("/inscribirEstudianteMateria")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String inscribirEstudianteMateria(String jsonData) {
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(jsonData, JsonObject.class);
		int idEstudiante = json.get("idEstudiante").getAsInt();
		int idMateria = json.get("idMateria").getAsInt();
		
		EstudiantesManager manager = new EstudiantesManager();
		boolean exito = manager.inscribirEstudianteEnMateria(idEstudiante, idMateria);
		
		JsonObject respuesta = new JsonObject();
		respuesta.addProperty("exito", exito);
		respuesta.addProperty("mensaje", exito ? "Estudiante inscrito en materia correctamente" : "Error al inscribir");
		
		return respuesta.toString();
	}
	
	// ========== SERVICIO PARA INSERTAR MATERIA ==========
	
	@POST
	@Path("/insertarMateria")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertarMateria(String materiaJson) {
		Gson gson = new Gson();
		Materia materia = gson.fromJson(materiaJson, Materia.class);
		MateriasManager manager = new MateriasManager();
		boolean exito = manager.insertarMateria(materia);
		
		JsonObject respuesta = new JsonObject();
		respuesta.addProperty("exito", exito);
		respuesta.addProperty("mensaje", exito ? "Materia registrada correctamente" : "Error al registrar materia");
		
		return respuesta.toString();
	}
}