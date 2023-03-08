package com.simonkijo.login;

public class personSearchModel {
    String first_name,last_name,user_name,pass_word,description_;

    public personSearchModel(String first_name, String last_name, String user_name, String pass_word, String description_) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.description_ = description_;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }
}
