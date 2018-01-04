package utp.edu.pe.mypetsfriend.models;

import java.util.Date;

/**
 * Created by Marco on 17/07/2016.
 */
public class Pet {
    private String pet_id;
    private String dni;
    private String pet_name;
    private String breed;
    private String hair_color;
    private String birth_date;
    private boolean status;
    private String pictureUri;

    private String age_year;
    private String age_month;
    private String age_day;


    public Pet(String pet_id,
               String dni,
               String pet_name,
               String breed,
               String hair_color,
               String birth_date,
               boolean status,
               String pictureUri,
               String age_year,
               String age_month,
               String age_day) {
        this.pet_id = pet_id;
        this.dni = dni;
        this.pet_name = pet_name;
        this.breed = breed;
        this.hair_color = hair_color;
        this.birth_date = birth_date;
        this.status = status;
        this.pictureUri = pictureUri;
        this.age_year = age_year;
        this.age_month = age_month;
        this.age_day = age_day;
    }
/*
    public Pet(String pet_id,
               String pet_name,
               String breed,
               String pictureUri,
               String age_year )
    {
        this.pet_id = pet_id;
        this.pet_name = pet_name;
        this.breed = breed;
        this.pictureUri = pictureUri;
        this.age_year = age_year;
    }
*/

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAge_year() {
        return age_year;
    }

    public void setAge_year(String age_year) {
        this.age_year = age_year;
    }

    public String getAge_month() {
        return age_month;
    }

    public void setAge_month(String age_month) {
        this.age_month = age_month;
    }

    public String getAge_day() {
        return age_day;
    }

    public void setAge_day(String age_day) {
        this.age_day = age_day;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }
}
