package co.edu.cesde.ga.aplicacion.service.Impl;

import co.edu.cesde.ga.aplicacion.exceptions.ObjetoNoEncontradoException;
import co.edu.cesde.ga.aplicacion.models.Roles;
import co.edu.cesde.ga.aplicacion.models.UserRoles;
import co.edu.cesde.ga.aplicacion.models.Users;
import co.edu.cesde.ga.aplicacion.repository.RolesRepository;
import co.edu.cesde.ga.aplicacion.repository.UserRepository;
import co.edu.cesde.ga.aplicacion.repository.UserRolesRepository;
import co.edu.cesde.ga.aplicacion.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserRolesServiceImpl(UserRolesRepository userRolesRepository, UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRolesRepository = userRolesRepository;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    @Transactional
    public void assignRole(Long userId, Long roleId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjetoNoEncontradoException("No se encontró un usuario con el ID: " + userId));
        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() -> new ObjetoNoEncontradoException("No se encontró un rol con el ID: " + roleId));

        UserRoles userRole = new UserRoles(user, role);
        userRolesRepository.save(userRole);
    }

    @Override
    public List<UserRoles> getRolesByUserId(Long userId) {
        return userRolesRepository.findByUser_UserId(userId);
    }

    @Override
    public List<UserRoles> getAllAssignments() {
        return userRolesRepository.findAll();
    }

    @Override
    @Transactional
    public void removeRoleFromUser(Long userId, Long roleId) {

        List<UserRoles> roles = userRolesRepository.findByUser_UserId(userId);
        for (UserRoles ur : roles) {
            if (ur.getRole().getRoleId().equals(roleId)) {
                userRolesRepository.delete(ur);
                break;
            }
        }
    }
}
