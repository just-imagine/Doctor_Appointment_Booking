package com.example.a1611821.doctor_appointment_booking;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class User {

    private String Password;
    private String Identity;
    private String Name;
    private String Surname;
    private String Email;
    private String Gender;
    private String Contact;
    private String confirmPassword;

    //aall getter methods to retieve data


    public String getPassword() {
        return Password;
    }

    public String getIdentity() {
        return Identity;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return Gender;
    }

    public String getContact() {
        return Contact;
    }

    //all setter methods



    public void setPassword(String password) {
        Password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    //the user email is a valid one
    public boolean validEmail(){
        if(Email==null || Email.equals("") || Email.length()<2 || !isAlphaNum(Email.charAt(0))){
            return  false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.Email).matches();
    }

    //emails can never start withspecial characters so check char at 0
    public boolean isAlphaNum(char c){
        if(Character.isAlphabetic(c) || Character.isDigit(c)){
            return true;
        }
        return  false;
    }

    //no one has a  name with special characters check this
    boolean validName(){
        if(Name==null || Name.equals("")){
            return  false;
        }

        for(int i=0;i<Name.length();++i){
            char c=Name.charAt(i);
            if(Character.isAlphabetic(c)  || Character.isSpace(c)){
                continue;
            }

            else{
                return  false;
            }
        }

        return  true;
    }

    boolean validIdentity(){
        if(Identity==null || Identity.equals("") || Identity.length()<13 || Identity.length()>13){
            return  false;
        }
        for(int i=0;i<Identity.length();++i){
            char c=Identity.charAt(i);
            if(!Character.isDigit(c)){
                return false;
            }
        }

        return  true;
    }

    boolean validSurname(){
        if(Surname==null || Surname.equals("")){
            return  false;
        }

        for(int i=0;i<Surname.length();++i){
            char c=Surname.charAt(i);
            if(Character.isAlphabetic(c) || Character.isSpace(c)){
                continue;
            }
            else{
                return  false;
            }
        }
        return  true;
    }

    //check for password field
    public  boolean validPassword(){
        if(Password==null || Password.equals("") || Password.length()<6){
            return  false;
        }

        return  true;
    }

    //check phone number
    public  boolean validContact(){
        if(Contact==null || Contact.length()<10 || Contact.charAt(0)!='0'){
            return  false;
        }

        else{
            for(int i=0;i<Contact.length();++i){
                char c=Contact.charAt(i);
                if(!Character.isDigit(c)){
                    return false;
                }
            }
        }

        return  true;

    }

    //method used at sign up to check that the passwords corresponds to entered one
    public boolean confirmPassword(){
        return  Password.equals(confirmPassword);
    }

    //check for gender that it is set

    public boolean validGender(){
        if(Gender==null || Gender.equals("")){
            return  false;
        }

        else if(Gender.equals("Male" ) || Gender.equals("Female")){
            return  true;
        }

        return  false;
    }

    public boolean validUser(){
        if(validEmail() && validName() && validSurname() && validPassword() && confirmPassword() && validIdentity() && validGender()){
            return  true;
        }

        return  false;
    }

    //when trying to login if db user info matches current user info then login

    @Override
    public boolean equals(Object obj){
        if(this==obj)
            return  true;

        else{
            User other=(User)obj;
            if(this.validEmail() && this.validPassword() && this.Email.equals(other.Email) && other.Password.equals(this.Password) ){
                return  true;
            }
            return  false;
        }
    }

    //gets all necessary information
    public ArrayList<String> getData(){
        ArrayList<String>patientData=new ArrayList<>();
        Collections.addAll(patientData,Identity,Email,Name,Surname);
        return patientData;
    }


    }
