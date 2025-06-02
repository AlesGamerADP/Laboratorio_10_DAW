package com.tecsup.laboratorio_10.servicios;
import com.tecsup.laboratorio_10.modelo.documents.Alumno;
import java.util.List;

public interface AlumnoService {
    public List<Alumno> listar();
    public void grabar(Alumno alumno);
    List<Alumno> buscar(String nombre);
    public void eliminar(String id);
    public void editar(Alumno alumno);
    Alumno buscarPorId(String id);
}