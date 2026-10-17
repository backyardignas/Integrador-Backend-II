package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.RolesService;
import co.edu.cesde.ga.aplicacion.domain.Roles;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.RolesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {

    private final RolesRepository roleRepository;

    public RolesServiceImpl(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Roles create(Roles role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new ResourceConflictException("Ya existe un rol con el nombre: " + role.getName());
        }
        return roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Roles> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Roles> findByName(String name) {
        return roleRepository.findByName(name.trim().toUpperCase());
    }

    @Override
    public Optional<Roles> update(Roles role) {
        Optional<Roles> existing = roleRepository.findById(role.getRoleId());
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (roleRepository.existsByNameAndRoleIdNot(role.getName(), role.getRoleId())) {
            throw new ResourceConflictException("Ya existe un rol con el nombre: " + role.getName());
        }

        Roles current = existing.get();
        current.setName(role.getName());
        current.setDescription(role.getDescription());

        return Optional.of(roleRepository.save(current));
    }

    @Override
    public void deleteById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("un rol", id);
        }
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }
}
