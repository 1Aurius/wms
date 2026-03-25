package com.example.wms.Services;

import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Models.utilizador.UtilizadorRepository;
import com.example.wms.Requests.RegistoUtilizadorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UtilizadorService implements UserDetailsService {

    private final UtilizadorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UtilizadorService(UtilizadorRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        return repository.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizador não encontrado: " + nome));
    }

    public Utilizador register(RegistoUtilizadorRequest request) {
        if (repository.findByNome(request.getNome()).isPresent()) {
            throw new RuntimeException("INVALID NAME: Name already exists");
        }

        Utilizador utilizador = new Utilizador(
                request.getNome(),
                passwordEncoder.encode(request.getPassword()),
                request.getDn(),
                request.isAdmin(),
                request.isGestorRotas(),
                request.isGestor(),
                request.isLoja(),
                request.isArmazem()
        );

        return repository.save(utilizador);
    }
}