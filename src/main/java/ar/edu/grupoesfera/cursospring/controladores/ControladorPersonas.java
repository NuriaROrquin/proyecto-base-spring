package ar.edu.grupoesfera.cursospring.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.grupoesfera.cursospring.modelo.Persona;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.servicios.PersonaService;

import java.util.List;

@Controller
public class ControladorPersonas {
	
	@Inject
	private PersonaService personaService;

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
	
	@RequestMapping(path="/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		
		Usuario usuarioValidado = personaService.validarUsuario(usuario.getUsuario(), usuario.getPassword());
		if(usuarioValidado != null){
			request.getSession().setAttribute("ROL",usuarioValidado.getRol());
			return new ModelAndView("home");
		} else {
			ModelMap model = new ModelMap();
			model.put("error", "usuaio-invalido");
			return new ModelAndView("login", model);
		}
    }

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}
	
	
	
	

}
