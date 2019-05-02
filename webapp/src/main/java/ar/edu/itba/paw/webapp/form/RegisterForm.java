package ar.edu.itba.paw.webapp.form;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterForm {
    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 100)
    private String surname;

    @NotNull
    @Size(min = 1, max = 15)
    private String username;

    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    @Size(min = 6, max = 100)
    private String repeatPassword;

    @NotNull
    @Size(min = 10, max = 100)
    @Email
    private String email;


    private int gender;

    private int status;

    //TODO: poner las otras validaciones

    public String getRepeatPassword() {
            return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


//             __
//            /
//         .-/-.
//         |'-'|
//         |   |
//         |   |   .-""""-.
//         \___/  /' .  '. \   \|/\//
//               (`-..:...-')  |`""`|
//                ;-......-;   |    |
//                 '------'    \____/
//
