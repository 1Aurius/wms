package com.example.wms.Services;

import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Models.utilizador.UtilizadorRepository;
import com.example.wms.Requests.RegistoUtilizadorRequest;
import com.example.wms.Responses.UtilizadorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UtilizadorService implements UserDetailsService {

    private final UtilizadorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UtilizadorService(UtilizadorRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilizador loadUserByUsername(String nome) throws UsernameNotFoundException {
        return repository.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizador não encontrado: " + nome));
    }

    @Transactional
    public Utilizador register(RegistoUtilizadorRequest request) {
        String normalizedNome = request.getNome() == null ? "" : request.getNome().trim();
        if (normalizedNome.isBlank()) {
            throw new RuntimeException("INVALID NAME: Username is required");
        }

        if (repository.findByNome(normalizedNome).isPresent()) {
            throw new RuntimeException("INVALID NAME: Name already exists");
        }

        Utilizador utilizador = new Utilizador(
                normalizedNome,
                passwordEncoder.encode(request.getPassword()),
                request.getDn(),
                request.isAdmin(),
                request.isGestorRotas(),
                request.isGestor(),
                request.isLoja(),
                request.isArmazem()
        );

        try {
            return repository.save(utilizador);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("INVALID NAME: Name already exists");
        }
    }

    public List<UtilizadorResponse> getAll(String query) {
        return repository.findAll().stream()
                .filter(u -> query == null || u.getNome().toLowerCase().contains(query.toLowerCase()))
                .map(UtilizadorResponse::from)
                .toList();
    }

    public UtilizadorResponse getById(Long id) {
        return repository.findById(id)
                .map(UtilizadorResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UtilizadorResponse update(Long id, RegistoUtilizadorRequest request) {
        Utilizador utilizador = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        utilizador.setNome(request.getNome());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            utilizador.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        utilizador.setDn(request.getDn());
        utilizador.setAdmin(request.isAdmin());
        utilizador.setGestorRotas(request.isGestorRotas());
        utilizador.setGestor(request.isGestor());
        utilizador.setLoja(request.isLoja());
        utilizador.setArmazem(request.isArmazem());
        return UtilizadorResponse.from(repository.save(utilizador));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}