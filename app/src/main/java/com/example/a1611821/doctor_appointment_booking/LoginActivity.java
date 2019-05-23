package com.example.a1611821.doctor_appointment_booking;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button Login;
    EditText Username;
    EditText Password;
    User Patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        Login=(Button)findViewById(R.id.login);
    }

    public void launchRegistration(View view){
        startActivity(new Intent(getApplicationContext(), WelcomeActivity2.class));
    }

    public void doLogin(View v){
        Patient=new User();
        Patient.setEmail(Username.getText().toString().trim());
        Patient.setPassword(Password.getText().toString().trim());
        //we want to launch an async and check that the account exist;
        //the parameters passed are the username and password
        ContentValues Params=new ContentValues();
        Params.put("USERNAME",Patient.getEmail());
        Params.put("PASSWORD",Patient.getPassword());
      
       //still need to figure out a way to handle the threading in some other class this is a naive way of doing it
       AsyncHTTPPost getAccount=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/newLogin.php",Params){
            @Override
            protected void onPostExecute(String output) {


                try {
                    //only ever returns one user which we need to compare with the user we have to see if we can login
                    JSONArray dbInformation=new JSONArray(output);
                    JSONObject obj=dbInformation.getJSONObject(0);
                    User dbUser=new User();
                    dbUser.setEmail(obj.getString("EMAIL_ADDRESS"));
                    dbUser.setPassword(obj.getString("PASSWORD"));
                    if(Patient.equals(dbUser)){
                        //take all the patient data and pass it to the next Intent
                        Intent HomeScreen=new Intent(getApplicationContext(),HomeScreen.class);
                        HomeScreen.putExtra("USERNAME",Patient.getEmail());
                        HomeScreen.putExtra("IDENTITY",obj.getString("ID_NUMBER"));
                        HomeScreen.putExtra("NAME",obj.getString("NAME"));
                        HomeScreen.putExtra("SURNAME",obj.getString("SURNAME"));
                        //start the next intent
                        startActivity(HomeScreen);
                    }


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Check your username and password",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        };
        getAccount.execute();
    }
  
    }

