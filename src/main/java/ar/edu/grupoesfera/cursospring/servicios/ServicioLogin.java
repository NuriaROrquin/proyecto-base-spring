package ar.edu.grupoesfera.cursospring.servicios;


import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.UsuarioExistente;

public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);

    void registrar(Usuario usuario) throws UsuarioExistente;
}
