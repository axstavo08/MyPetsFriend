package utp.edu.pe.mypetsfriend.models;

import java.util.Date;

/**
 * Created by Marco on 18/11/2016.
 */

public class Person {

    private String dni;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String phone_number;
    private String birth_date;
    private boolean status;

    public  Person(){}

    public Person(String address ,String birth_date, String dni, String email, String first_name, String last_name, String phone_number, boolean status) {
        this.address = address;
       this.birth_date = birth_date;
        this.dni = dni;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
