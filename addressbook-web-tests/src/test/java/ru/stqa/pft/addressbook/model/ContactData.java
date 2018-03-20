package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstname;
    private final String midname;
    private final String lastname;
    private final String nickname;
    private final String mobphone;
    private final String email;
    private final String address;
    private final String group;

    public void setId(int id) {
        this.id = id;
    }

    public ContactData(String firstname, String midname, String lastname, String nickname, String mobphone, String email, String address, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.midname = midname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.mobphone = mobphone;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public ContactData(int id, String firstname, String midname, String lastname, String nickname, String mobphone, String email, String address, String group) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData contactData = (ContactData) o;
        return Objects.equals(firstname, contactData.firstname) &&
                Objects.equals(lastname, contactData.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstname,lastname);
    }
    public int getId() {
        return id;
    }
}
