package org.movies.system.services;

import org.movies.system.models.binding.UserRegisterDto;
import org.movies.system.models.view.UserEditDto;
import org.movies.system.models.entities.User;

import java.util.List;

public interface UserService {

    Long userCount();

    List<User> findAllUsers();

    User findFirstByUsername(String username);

    User findFirstById(String id);

    void register(UserRegisterDto userRegisterDto);

    void edit(UserEditDto userEditDto);

    void seedUser();

    UserEditDto findEditUser(String id);

    void editUser(String id, UserEditDto userEditDto);
}
