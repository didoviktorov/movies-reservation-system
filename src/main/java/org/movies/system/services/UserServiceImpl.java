package org.movies.system.services;

import org.movies.system.models.binding.UserRegisterDto;
import org.movies.system.models.view.UserEditDto;
import org.movies.system.models.entities.Role;
import org.movies.system.models.entities.User;
import org.movies.system.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public User findFirstByUsername(String username) {
        return this.userRepository.findFirstByUsername(username);
    }

    @Override
    public User findFirstById(String id) {
        return this.userRepository.findFirstById(id);
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        User user = this.modelMapper.map(userRegisterDto, User.class);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = this.roleService.findFirstByName("USER");
        role.getUsers().add(user);
        user.getRoles().add(role);

        this.roleService.save(role);

        this.userRepository.save(user);
    }

    @Override
    public void edit(UserEditDto userEditDto) {
        User user = new User();
        this.userRepository.save(user);
    }

    @Override
    public void seedUser() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@admin.bg");
        admin.setPassword(this.passwordEncoder.encode("admin"));

        admin.getRoles().add(this.roleService.findFirstByName("ADMIN"));
        admin.getRoles().add(this.roleService.findFirstByName("USER"));

        this.userRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findFirstByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No Found User");
        }

        Set<SimpleGrantedAuthority> roles = user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toSet());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
        return userDetails;
    }
}
