package ar.edu.grupoesfera.cursospring.servicios;

import javax.inject.Inject;

import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	@Inject
	private UsuarioDao servicioLoginDao;

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}

	@Override
	public Boolean existeUsuario(String email) {
		return null;
	}

	@Override
	public void registrar(Usuario usuario) {

	}

}
