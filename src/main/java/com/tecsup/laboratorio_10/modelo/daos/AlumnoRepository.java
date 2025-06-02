package com.tecsup.laboratorio_10.modelo.daos;
import com.tecsup.laboratorio_10.modelo.documents.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);
}