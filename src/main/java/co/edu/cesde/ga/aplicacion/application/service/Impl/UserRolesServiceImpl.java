package co.edu.cesde.ga.aplicacion.application.service.Impl;

import co.edu.cesde.ga.aplicacion.application.service.UserRolesService;
import co.edu.cesde.ga.aplicacion.domain.Roles;
import co.edu.cesde.ga.aplicacion.domain.UserRoles;
import co.edu.cesde.ga.aplicacion.domain.Users;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.aplicacion.domain.exceptions.ResourceNotFoundException;
import co.edu.cesde.ga.aplicacion.domain.repository.RolesRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.UserRepository;
import co.edu.cesde.ga.aplicacion.domain.repository.UserRolesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public UserRolesServiceImpl(UserRolesRepository userRolesRepository,
                                UserRepository userRepository,
                                RolesRepository rolesRepository) {
        this.userRolesRepository = userRolesRepository;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserRoles assignRole(Long userId, Long roleId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("un usuario", userId));
        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("un rol", roleId));

        if (userRolesRepository.existsByUser_UserIdAndRole_RoleId(userId, roleId)) {
            throw new ResourceConflictException("El usuario " + userId + " ya tiene asignado el rol " + role.getName());
        }

        return userRolesRepository.save(new UserRoles(user, role));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoles> findAll() {
        return userRolesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRoles> findById(Long id) {
        return userRolesRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoles> findByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("un usuario", userId);
        }
        return userRolesRepository.findByUser_UserId(userId);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRolesRepository.existsById(id)) {
            throw new ResourceNotFoundException("una asignación de rol", id);
        }
        userRolesRepository.deleteById(id);
    }
}
