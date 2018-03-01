package ar.edu.grupoesfera.cursospring.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.servicios.ServicioLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorLogin {

	@Inject
	private ServicioLogin servicioLogin;

	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			return new ModelAndView("redirect:/home");
		} else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/registrarme", method = RequestMethod.POST)
	public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario) {
		ModelMap model = new ModelMap();

		if (!servicioLogin.existeUsuario(usuario.getEmail())) {
			try{
				servicioLogin.registrar(usuario);
			} catch (Exception e){
				model.put("error", "Error al registrar el nuevo usuario");
				return new ModelAndView("nuevo-usuario", model);
			}
			return new ModelAndView("redirect:/login");
		} else {
			model.put("error", "Usuario ya existe");
			return new ModelAndView("nuevo-usuario", model);
		}
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(path = "/nuevo-usuario", method = RequestMethod.GET)
	public ModelAndView nuevoUsuario() {
		ModelMap model = new ModelMap();
		model.put("usuario", new Usuario());
		return new ModelAndView("nuevo-usuario", model);
	}


	public void setServicioLogin(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}
}
