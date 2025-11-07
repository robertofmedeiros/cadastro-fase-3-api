package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.UsuariosRequestDTO;
import br.com.senac.api.controllers.dtos.UsuariosResponseDTO;
import br.com.senac.api.jwt.TokenService;
import br.com.senac.api.modelos.Usuarios;
import br.com.senac.api.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UsuariosResponseDTO cadastrarUsuario(UsuariosRequestDTO usuario) {
        Optional<Usuarios> usuarioResult =
                usuariosRepositorio.findByEmail(usuario.getEmail());
        if(usuarioResult.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado!");
        }

        Usuarios usuarioPersist = new Usuarios();
        usuarioPersist.setEmail(usuario.getEmail());
        usuarioPersist.setNome(usuario.getNome());
        usuarioPersist.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuarios usuarioFinal = usuariosRepositorio.save(usuarioPersist);

        UsuariosResponseDTO retorno = new UsuariosResponseDTO();
        retorno.setEmail(usuarioFinal.getEmail());
        retorno.setNome(usuarioFinal.getNome());
        retorno.setToken(tokenService.gerarToken(usuarioFinal));

        return retorno;

    }

    public UsuariosResponseDTO loginUsuario(UsuariosRequestDTO usuario) {
        Optional<Usuarios> resultUsuario =
                usuariosRepositorio.findByEmail(usuario.getEmail());
        if(resultUsuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuarios usuarioLogin = resultUsuario.get();

        if(passwordEncoder.matches(usuario.getSenha(), usuarioLogin.getSenha())) {
            UsuariosResponseDTO response = new UsuariosResponseDTO();
            response.setEmail(usuarioLogin.getEmail());
            response.setNome(usuarioLogin.getNome());
            response.setToken(tokenService.gerarToken(usuarioLogin));

            return response;
        }

        throw new RuntimeException("Senha invalida!");
    }
}
