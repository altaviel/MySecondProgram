package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String midname;
    private final String lastname;
    private final String nickname;
    private final String mobphone;
    private final String email;
    private final String address;
    private String group;

    public ContactData(String firstname, String midname, String lastname, String nickname, String mobphone, String email, String address, String group) {
        this.firstname = firstname;
        this.midname = midname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.mobphone = mobphone;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMidname() {
        return midname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobphone() {
        return mobphone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }
}
