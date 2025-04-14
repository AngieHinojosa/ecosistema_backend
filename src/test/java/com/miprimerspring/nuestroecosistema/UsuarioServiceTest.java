package com.miprimerspring.nuestroecosistema;

import com.miprimerspring.nuestroecosistema.api.UsuarioRestController;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

public class UsuarioServiceTest {

    @Test
    public void probandoCreacionUsuario() {

        //Arrange
        Usuario usuario = new Usuario();
        //Act
        usuario.setUsuarioId(1L);
        usuario.setUsuarioNombres("Juan");
        usuario.setUsuarioApellidos("Perez");
        usuario.setUsuarioCorreo("juan.perez@correo.com");
        usuario.setUsuarioNumeroDocumento("123456789");
        usuario.setUsuarioEstado("Activo");

    }
    @Test
    public void obtenerUsuarioPorId() {
        Usuario usuarioPrueba2 = new Usuario();
        usuarioPrueba2.getUsuarioId();
        usuarioPrueba2.getUsuarioNombres();
        usuarioPrueba2.getUsuarioApellidos();
        usuarioPrueba2.getUsuarioCorreo();
        usuarioPrueba2.getUsuarioNumeroDocumento();
        usuarioPrueba2.getUsuarioEstado();
    }


}
