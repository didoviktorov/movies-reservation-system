package org.movies.system.models.view;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserEditDto {

    @NotNull
    @Size(min = 1, max = 15, message = "Invalid username - maximum size 15 symbols")
    private String username;

    @NotEmpty(message = "Invalid Email!")
    @Email(message = "Invalid email")
    private String email;

    private Set<String> roles;

    public UserEditDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
