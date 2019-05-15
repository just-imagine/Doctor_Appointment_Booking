package com.example.a1611821.doctor_appointment_booking;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class  WelcomeActivity2 extends AppCompatActivity{
    TextView CreateAccount;
    EditText Name;
    EditText Surname;
    EditText Email;
    EditText Contact;
    EditText Identity;
    EditText Password,ConfirmPassword;
    RadioButton Male,Female;
    RadioGroup Sex;
    //local sqlite database to be used for testing
    DatabaseHelper accountDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountDB=new DatabaseHelper(this, "app");
        setContentView(R.layout.activity_welcome2);
      
        //Edittexts to collect information from
        Name=(EditText)findViewById(R.id.fullnames);
        Surname=(EditText)findViewById(R.id.Surname);
        Email=(EditText)findViewById(R.id.email);
        Contact=(EditText)findViewById(R.id.mobile);
        Identity=(EditText)findViewById(R.id.identity_no);
        Password=(EditText)findViewById(R.id.password);
        ConfirmPassword=(EditText)findViewById(R.id.confirmation);
        Male=(RadioButton)findViewById(R.id.male);
        Female=(RadioButton)findViewById(R.id.female);

        Sex=(RadioGroup)findViewById(R.id.sex);

        // CreateAccount will create a new User object and initialize all its fields when its clicked
        CreateAccount=(TextView) findViewById(R.id.createAccount);

        selectFromAccountDb();
    }

    public void doRegistration(View v){
        //get id of clicked radio button so we can initialize the gender accordingly
        int Id=Sex.getCheckedRadioButtonId();

        //at least one radio button in radio group must have been checked

        final User user=new User();

        if(Id!=-1){
            //the radio button that has the gender
            RadioButton checkedGender=(RadioButton)findViewById(Sex.getCheckedRadioButtonId());
            user.setGender(checkedGender.getText().toString().trim());}

        //create new user and set relevent details and validate then register
        user.setName(Name.getText().toString().trim());
        user.setSurname(Surname.getText().toString().trim());
        user.setEmail(Email.getText().toString().trim());
        user.setPassword(Password.getText().toString().trim());
        user.setContact(Contact.getText().toString().trim());
        user.setIdentity(Identity.getText().toString().trim());
        user.setConfirmPassword(ConfirmPassword.getText().toString().trim());

        //now check if the user entered details in expected format
        if(user.validUser()){
         //first insert the user into local database;
            insertToAccountDB(user);
         //launch async here that will add the user to the remote  database
            ContentValues Params=new ContentValues();
            Params.put("ID_NUMBER", user.getIdentity());
            Params.put("EMAIL_ADDRESS", user.getEmail());
            Params.put("NAME", user.getName());
            Params.put("SURNAME", user.getSurname());
            Params.put("CONTACT_NO",Integer.parseInt(user.getContact()));
            Params.put("GENDER",user.getGender());
            Params.put("PASSWORD",user.getPassword());

            AsyncHTTPPost addUser=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/Account.php",Params) {
             @Override
             protected void onPostExecute(String output) {
                Intent HomeScreen=new Intent(getApplicationContext(),HomeScreen.class);
                 HomeScreen.putExtra("USERNAME",user.getEmail());
                 HomeScreen.putExtra("IDENTITY",user.getIdentity());
                 HomeScreen.putExtra("NAME",user.getName());
                 HomeScreen.putExtra("SURNAME",user.getSurname());
                 startActivity(HomeScreen);
                 Toast.makeText(getApplicationContext(),"you have been registered",Toast.LENGTH_SHORT).show();
                 finish();
             }
            };

            addUser.execute();
        }

        //if not valid user then use verify methods to set  relevent errors
        else{
            if(!user.validIdentity()){
                Identity.setError("Enter a valid Identity");
            }

            if(!user.validEmail()){
                Email.setError("This filed cannot be empty and it must be a valid email address");
            }

            if(!user.validName()){
                Name.setError("This field cannot be empty and must not have special characters or numbers");
            }

            if(!user.validSurname()){
                Surname.setError("This field cannot be empty and must and cannot not have special characters or numbers");
            }

            if(!user.validPassword()){
                Password.setError("This field cannot be empty and must be of 6 characters or more");
            }

            if(!user.confirmPassword()){
                ConfirmPassword.setError("Passwords do not match");
            }

            if(!user.validGender()){
                Toast.makeText(getApplicationContext(),"Pick a gender please",Toast.LENGTH_SHORT).show();
            }

            if(!user.validContact()){
                Contact.setError("This field cannot be empty and must of the form 082....");
            }
                }

        }

        //remove someone from the local accounts database to be used by testing team
        public void deleteFromAccountDb(User user){
            String vals[]={user.getIdentity()};
            accountDB.doUpdate("delete from ACCOUNTS where ID_NUMBER=?;", vals);
        }

        public void insertToAccountDB(User user){
            String[] vals = {user.getIdentity(),user.getEmail(),user.getName(),
            user.getSurname(),user.getContact(),user.getGender(),user.getPassword()};
            accountDB.doUpdate("Insert into ACCOUNTS(ID_NUMBER,EMAIL_ADDRESS,NAME,SURNAME,CONTACT_NO,GENDER,PASSWORD) values (?,?,?,?,?,?,?);", vals);
        }

        //gets stuff from local database and put it into a json array to be used by testing team
        public  JSONArray selectFromAccountDb() {
            Cursor c = accountDB.doQuery("SELECT* from ACCOUNTS");
            JSONArray data=new JSONArray();
            while (c.moveToNext()) {
                JSONObject obj=new JSONObject();
                data.put(obj);
                try {
                    obj.put("ID_NUMBER",c.getString(c.getColumnIndex("ID_NUMBER")));
                    obj.put("EMAIL_ADDRESS",c.getString(c.getColumnIndex("EMAIL_ADDRESS")));
                    obj.put("NAME",c.getString(c.getColumnIndex("NAME")));
                    obj.put("SURNAME",c.getString(c.getColumnIndex("SURNAME")));
                    obj.put("CONTACT_NO",c.getString(c.getColumnIndex("CONTACT_NO")));
                    obj.put("GENDER",c.getString(c.getColumnIndex("GENDER")));
                    obj.put("PASSWORD",c.getString(c.getColumnIndex("PASSWORD")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return  data;
        }
    }
