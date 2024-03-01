package com.doremi.booking.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.doremi.booking.entity.Usuario;
import com.doremi.booking.entity.UsuarioRole;
import com.doremi.booking.repository.UsuarioRepository;

@Component
public class DatosInicialesUsuarios implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passCinfrar= "digital";
        String passCifrado= cifrador.encode(passCinfrar);
        System.out.println("Clave cifrada: "+passCifrado);
        //crear un usuario
        Usuario usuario1= new Usuario("Marcela","Administrador","marcela.hermosa@gmail.com",passCifrado, UsuarioRole.ROLE_ADMIN);
        Usuario usuario2= new Usuario("Jorge","Jorge24","jorge24@gmail.com",passCifrado,UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
    }
}
