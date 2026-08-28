package co.edu.cesde.ga.aplicacion.service; // OJO: Sin el .Impl al final

import co.edu.cesde.ga.aplicacion.models.Users;
import java.util.List;

public interface UserService {
    void registerUser(Users user);
    Users getUserById(Long id);
    Users getUserByEmail(String email);
    List<Users> getAllUsers();
    void updateUserInfo(Users user);
    void removeUser(Long id);
}
