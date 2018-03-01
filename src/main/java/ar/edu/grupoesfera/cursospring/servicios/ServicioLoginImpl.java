package ar.edu.grupoesfera.cursospring.servicios;

import javax.inject.Inject;

import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.UsuarioExistente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	@Inject
	private UsuarioDao usuarioDao;

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return usuarioDao.consultarUsuario(usuario);
	}

	@Override
	public void registrar(Usuario usuario) throws UsuarioExistente {
		Usuario usuarioEncontrado = usuarioDao.buscarPor(usuario.getEmail());
		if(usuarioEncontrado != null){
			throw new UsuarioExistente();
		}
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
