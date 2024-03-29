package ar.edu.itba.paw2019a2.webapp.form;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@PasswordMatch
public class RegisterForm {
    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 100)
    private String surname;

    @AvailableUsername
    @Size(min = 1, max = 15)
    private String username;

    @Size(min = 6, max = 100)
    private String password;

    @Size(min = 6, max = 100)
    private String repeatPassword;

    @Size(min = 6, max = 100)
    @Email
    private String email;

    private String gender;
    private boolean sendMail;

    //TODO: poner las otras validaciones

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public boolean getSendMail() {
        return sendMail;
    }

    public void setSendMail(boolean sendMail) {
        this.sendMail = sendMail;
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
