package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Usuario;

public interface ServicioMail {

    void enviarMailDeBienvenida(Usuario usuario);
}
