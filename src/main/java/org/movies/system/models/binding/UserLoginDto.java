package org.movies.system.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDto {

    @NotNull
    @Size(min = 1, max = 15, message = "Invalid username")
    private String username;

    @NotNull
    @Size(min = 1, max = 15, message = "Invalid password")
    private String password;

    public UserLoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
