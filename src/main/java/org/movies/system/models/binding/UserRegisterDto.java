package org.movies.system.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDto {

    @NotNull
    @Size(min = 1, max = 15, message = "Invalid username - maximum size 15 symbols")
    private String username;

    @NotNull
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @Size(min = 1, max = 15, message = "Invalid password")
    private String password;

    @NotNull
    @Size(min = 1, max = 15, message = "Invalid password")
    private String confirmPassword;

    public UserRegisterDto() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean passwordsMatch() {
        return this.password.equals(this.confirmPassword);
    }
}
