package ar.edu.itba.paw.webapp.form;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

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

    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 100)
    private String surname;

    //TODO: poner las otras validaciones

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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
