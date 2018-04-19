package org.movies.system.areas.users.services;

import org.movies.system.exceptions.BadRequestException;
import org.movies.system.areas.users.models.binding.UserRegisterDto;
import org.movies.system.areas.users.models.binding.UserEditDto;
import org.movies.system.areas.roles.entities.Role;
import org.movies.system.areas.users.entities.User;
import org.movies.system.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.movies.system.areas.roles.services.RoleService;
import org.movies.system.utils.EscapeCharacters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long userCount() {
        return this.userRepository.count();
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findFirstByUsername(String username) {
        User user = this.userRepository.findFirstByUsername(username);
        this.checkUser(user);
        return user;
    }

    @Override
    public User findFirstById(String id) {
        User user = this.userRepository.findFirstById(id);
        this.checkUser(user);
        return user;
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        User user = this.modelMapper.map(userRegisterDto, User.class);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = this.roleService.findFirstByName("USER");
        role.getUsers().add(user);
        user.addRole(role);

        this.roleService.save(role);

        this.userRepository.save(user);
    }

    @Override
    public void seedUser() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@admin.bg");
        admin.setPassword(this.passwordEncoder.encode("admin"));

        admin.addRole(this.roleService.findFirstByName("ADMIN"));
        admin.addRole(this.roleService.findFirstByName("USER"));

        this.userRepository.save(admin);
    }

    @Override
    public UserEditDto findEditUser(String id) {
        UserEditDto userEditDto = new UserEditDto();
        User user = this.findFirstById(id);

        this.checkUser(user);

        this.modelMapper.map(user, userEditDto);

        Set<String> editUserRoles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        userEditDto.setRoles(editUserRoles);

        return userEditDto;
    }

    @Override
    public void editUser(String id, UserEditDto userEditDto) {
        User user = this.findFirstById(id);
        this.checkUser(user);

        user.setEmail(userEditDto.getEmail());
        user.setUsername(userEditDto.getUsername());

//        user.setRoles(new HashSet<>());
        for (String roleName : userEditDto.getRoles()) {
            Role role = this.roleService.findFirstByName(roleName);

            role.getUsers().add(user);
            user.addRole(role);

            this.roleService.save(role);
        }

        this.userRepository.save(user);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public boolean validateRegisterUser(UserRegisterDto userRegisterDto) {
        return this.userRepository.findFirstByUsername(userRegisterDto.getUsername()) == null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findFirstByUsername(EscapeCharacters.escape(username));
        if (user == null) {
            throw new UsernameNotFoundException("No Found User");
        }

        Set<SimpleGrantedAuthority> roles = user.getAuthorities()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getAuthority()))
                .collect(Collectors.toSet());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
        return userDetails;
    }

    private void checkUser(User user) {
        if (user == null) {
            throw new BadRequestException();
        }
    }
}
