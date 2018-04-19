package org.movies.system.areas.users.services;

import org.movies.system.areas.users.models.binding.UserRegisterDto;
import org.movies.system.areas.users.models.binding.UserEditDto;
import org.movies.system.areas.users.entities.User;

import java.util.List;

public interface UserService {

    Long userCount();

    List<User> findAllUsers();

    User findFirstByUsername(String username);

    User findFirstById(String id);

    void register(UserRegisterDto userRegisterDto);

    void seedUser();

    UserEditDto findEditUser(String id);

    void editUser(String id, UserEditDto userEditDto);

    void save(User user);

    boolean validateRegisterUser(UserRegisterDto userRegisterDto);
}
