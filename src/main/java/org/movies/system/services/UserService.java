package org.movies.system.services;

import org.movies.system.models.binding.UserRegisterDto;
import org.movies.system.models.view.UserEditDto;
import org.movies.system.models.entities.User;

public interface UserService {

    Long userCount();

    User findFirstByUsername(String username);

    User findFirstById(String id);

    void register(UserRegisterDto userRegisterDto);

    void edit(UserEditDto userEditDto);

    void seedUser();
}
