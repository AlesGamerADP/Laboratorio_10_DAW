package com.tecsup.laboratorio_10.controladores;

import com.tecsup.laboratorio_10.modelo.documents.Alumno;
import com.tecsup.laboratorio_10.servicios.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService servicio;

    @RequestMapping(value = "/alumno/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        List<Alumno> alumnos = servicio.listar();
        model.addAttribute("alumno", alumnos); // si prefieres, usa "alumnos" (plural)
        return "alumnosView";
    }

    @RequestMapping(value = "/alumno/form", method = RequestMethod.GET)
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario Alumno");
        return "alumnoForm";
    }

    @RequestMapping(value = "/alumno/form/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") String id, Map<String, Object> model) {
        Alumno alumno = servicio.buscarPorId(id);
        if (alumno == null) {
            return "redirect:/alumno/listar";
        }
        model.put("alumno", alumno);
        model.put("titulo", "Editar Alumno");
        return "alumnoForm";
    }

    @RequestMapping(value = "/alumno/form", method = RequestMethod.POST)
    public String guardar(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario Alumno");
            return "alumnoForm";
        }
        if (alumno.getId() != null && alumno.getId().trim().isEmpty()) {
            alumno.setId(null);  // Así Mongo genera el id automáticamente
        }
        servicio.grabar(alumno);
        status.setComplete();
        return "redirect:/alumno/listar";
    }

    @RequestMapping(value = "/alumno/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable("id") String id) {
        Alumno alumno = servicio.buscarPorId(id);
        if (alumno != null) {
            servicio.eliminar(id);
        }
        return "redirect:/alumno/listar";
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public String buscarPorNombre(String nombre, Model model) {
        List<Alumno> resultados = servicio.buscar(nombre);
        model.addAttribute("alumno", resultados); // considera usar "alumnos"
        model.addAttribute("titulo", "Resultados de búsqueda para: " + nombre);
        return "alumnosView";
    }
}