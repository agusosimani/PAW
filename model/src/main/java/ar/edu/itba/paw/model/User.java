package ar.edu.itba.paw.model;

import javafx.util.Builder;

/**
 * Hello world!
 *
 */
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String username;
    private int gender;
    private int status;

    private User(int id, String email, String password, String name, String surname, String username, int gender, int status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.gender = gender;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder{

        private int id = 0;
        private String email;
        private String password;
        private String name = "";
        private String surname  = "";
        private String username;
        private int gender = 0;
        private int status = 0;

        public Builder(int id, String username, String password, String email) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.username = username;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder gender(int gender) {
            this.gender = gender;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public User build() {
            return new User(id, email, password, name, surname, username, gender, status);
        }
    }
}
