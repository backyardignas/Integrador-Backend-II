package co.edu.cesde.ga.aplicacion.application.service;

import co.edu.cesde.ga.aplicacion.domain.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Users create(Users user);
    List<Users> findAll();
    Optional<Users> findById(Long id);
    Optional<Users> findByEmail(String email);
    Optional<Users> update(Users user);
    void deleteById(Long id);
    boolean existsById(Long id);
}
