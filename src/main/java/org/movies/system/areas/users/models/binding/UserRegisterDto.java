package org.movies.system.areas.users.models.binding;

import javax.validation.constraints.*;

public class UserRegisterDto {

    @NotNull
    @Size(min = 1, max = 50, message = "Invalid username - maximum size 15 symbols")
    private String username;

    @NotEmpty(message = "Please enter email")
    @Pattern(regexp = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email")
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
