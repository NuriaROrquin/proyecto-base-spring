package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorBasico {

    @RequestMapping("/saludito")
    public ModelAndView escucharSaludo(){
        ModelMap model = new ModelMap();

        model.put("saludoParaMostrar", "HOLA CAPO!!!!");
        return new ModelAndView("saludo", model);
    }

    @RequestMapping("/hi/amigo")
    public ModelAndView helloWorld(@RequestParam("nombre") String nombre) {

        String message = "Hola, " + nombre + " !";
        ModelMap model = new ModelMap();
        model.put("message", message);
        return new ModelAndView("mensaje", model);
    }

    @RequestMapping("/hola/{nombre}/ademas/{apellido}")
    public ModelAndView helloWorld2(@PathVariable String nombre, @PathVariable String apellido) {
        String message = "Hola, " + nombre + "!";
        ModelMap model = new ModelMap();
        model.put("message", message);
        return new ModelAndView("mensaje", model);
    }

    @RequestMapping(path="/saludo", method = RequestMethod.POST)
    public ModelAndView addContact(@ModelAttribute("persona") Persona persona) {
        ModelMap model = new ModelMap();
        model.put("nombre", persona.getNombre());
        model.put("apellido", persona.getApellido());
        model.put("mail", persona.getEmail());
        return new ModelAndView("confirmacion", model);
    }

    @RequestMapping("/formulario")
    public ModelAndView irAForm(){
        ModelMap model = new ModelMap();
        Persona persona = new Persona();
        persona.setNombre("Juan Carlos");
        persona.setApellido("Calabro");
        model.put("persona", persona);
        return new ModelAndView("formulario", model);
    }
}
