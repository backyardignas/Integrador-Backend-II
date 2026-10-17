package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.UserService;
import co.edu.cesde.ga.aplicacion.domain.Users;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users create(Users user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceConflictException("Ya existe un usuario con el correo: " + user.getEmail());
        }
        if (userRepository.existsByIdentification(user.getIdentification())) {
            throw new ResourceConflictException("Ya existe un usuario con la identificación: " + user.getIdentification());
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email.trim().toLowerCase());
    }

    @Override
    public Optional<Users> update(Users user) {
        Optional<Users> existing = userRepository.findById(user.getUserId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (userRepository.existsByEmailAndUserIdNot(user.getEmail(), user.getUserId())) {
            throw new ResourceConflictException("Ya existe un usuario con el correo: " + user.getEmail());
        }
        if (userRepository.existsByIdentificationAndUserIdNot(user.getIdentification(), user.getUserId())) {
            throw new ResourceConflictException("Ya existe un usuario con la identificación: " + user.getIdentification());
        }

        Users current = existing.get();
        current.setIdentification(user.getIdentification());
        current.setName(user.getName());
        current.setLastName(user.getLastName());
        current.setEmail(user.getEmail());
        current.setPassword(user.getPassword());

        return Optional.of(userRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("un usuario", id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
