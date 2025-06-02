package com.tecsup.laboratorio_10.modelo.daos;
import com.tecsup.laboratorio_10.modelo.documents.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursoRepository extends MongoRepository<Curso, String> {
}