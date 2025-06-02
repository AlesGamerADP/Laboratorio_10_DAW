package com.tecsup.laboratorio_10.servicios;
import com.tecsup.laboratorio_10.modelo.documents.Alumno;
import com.tecsup.laboratorio_10.modelo.daos.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository dao;

    @Override
    public void grabar(Alumno alumno) {
        dao.save(alumno);
    }

    @Override
    public void eliminar(String id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public List<Alumno> buscar(String nombre) {
        return dao.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    @Transactional
    public void editar(Alumno alumno) {
        dao.save(alumno);
    }
    @Override
    @Transactional
    public Alumno buscarPorId(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listar() {
        return dao.findAll();
    }
}