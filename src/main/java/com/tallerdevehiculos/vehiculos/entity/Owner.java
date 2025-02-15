package com.tallerdevehiculos.vehiculos.entity;



import java.io.Serializable;


public class Owner implements Serializable {


    private String uId;

    private int id;

    private String name;

    private String lastName;

    private int telephone;

    private String email;

    private String creationDate;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String toJson(){
        String json="{"+
                '\u201C'+ "uId" + '\u201C' +":"+'\u201C'+getuId()+'\u201C'+","+
                '\u201C'+ "id" + '\u201C' +":"+'\u201C'+getId()+'\u201C'+","+
                '\u201C'+ "name" + '\u201C' +":"+'\u201C'+getName()+'\u201C'+","+
                '\u201C'+ "lastName" + '\u201C' +":"+'\u201C'+getLastName()+'\u201C'+","+
                '\u201C'+ "telephone" + '\u201C' +":"+'\u201C'+getTelephone()+'\u201C'+","+
                '\u201C'+ "email" + '\u201C' +":"+'\u201C'+getEmail()+'\u201C'+","+
                '\u201C'+ "creationDate" + '\u201C' +":"+'\u201C'+getCreationDate()+'\u201C'+"}";
        return json;

    }
}
