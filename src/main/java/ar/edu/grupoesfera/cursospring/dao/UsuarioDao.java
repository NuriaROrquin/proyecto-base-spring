package ar.edu.grupoesfera.cursospring.dao;


import ar.edu.grupoesfera.cursospring.modelo.Usuario;

public interface UsuarioDao {
	
	Usuario consultarUsuario(Usuario usuario);

    Usuario buscarPor(String email);

    void guardar(Usuario usuario);
}
