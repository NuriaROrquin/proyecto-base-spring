package ar.edu.grupoesfera.cursospring.servicios;


import ar.edu.grupoesfera.cursospring.modelo.Usuario;

public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);

    Boolean existeUsuario(String email);

    void registrar(Usuario usuario);
}
