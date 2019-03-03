package com.example.a1611821.doctor_appointment_booking;

import android.content.ContentValues;
import android.content.Intent;
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

public class        WelcomeActivity2 extends AppCompatActivity  implements View.OnClickListener{
    TextView CreateAccount;
    EditText Firstname;
    EditText Surname;
    EditText Email;
    EditText Contact;
    EditText Identity;
    EditText Password,ConfirmPassword;
    RadioButton male,female,other;
    String gender="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        //edittext to collect information from
        Firstname=(EditText)findViewById(R.id.fullnames);
        Surname=(EditText)findViewById(R.id.Surname);
        Email=(EditText)findViewById(R.id.email);
        Contact=(EditText)findViewById(R.id.mobile);
        Identity=(EditText)findViewById(R.id.identity_no);
        Password=(EditText)findViewById(R.id.password);
        ConfirmPassword=(EditText)findViewById(R.id.confirmation);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        other=(RadioButton)findViewById(R.id.other);
        female.setOnClickListener(this);
        male.setOnClickListener(this);
        other.setOnClickListener(this);

        CreateAccount=(TextView) findViewById(R.id.createAccount);
        CreateAccount.setOnClickListener(this);
    }

    public void close(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void onClick(View v) {

        if(v.equals(female)){
            gender="Female";
            if(male.isChecked()){
                male.setChecked(false);
            }
        }

        if(v.equals(male)){
            gender="Male";
            if(female.isChecked()){
                female.setChecked(false);
            }
        }

        if(v.equals(other)) {
            gender="Other";
            if (female.isChecked()) {
                female.setChecked(false);
            }

            if(male.isChecked()){
                male.setChecked(false);
            }
        }

        if(v.equals(CreateAccount)){
            String name,surname,emailaddress,password,confirmpassword,identity,contact;
            name=Firstname.getText().toString();
            surname=Surname.getText().toString();
            emailaddress=Email.getText().toString();
            password=Password.getText().toString();
            confirmpassword=ConfirmPassword.getText().toString();
            identity=Identity.getText().toString();
            contact=Contact.getText().toString();

            String emailPattern = "^[_A-Za-z0-9-]+" +
                    "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            boolean format=true;

            if(name.equals("")){
                format=false;
                Firstname.setError("This field cannot be empty");
            }

            if(!name.equals("") && name.length()<3){
                format=false;
                Firstname.setError("Enter a valid first name");
            }

            if(surname.equals("")){
                format=false;
                Surname.setError("This field cannot be empty");
            }

            if(surname.length()<3 && !surname.equals("")){
                format=false;
                Surname.setError("Enter a valid surname");
            }

            if(emailaddress.equals("")){
                format=false;
                Email.setError("This field cannot be empty");
            }

            if(!emailaddress.trim().matches(emailPattern) && !emailaddress.equals("")){
                format=false;
                Email.setError("Enter a valid email");
            }

            if(password.equals("")){
                format=false;
                Password.setError("This field cannot be empty");
            }

            if(password.length()<6 && !password.equals("")){
                format=false;
                Password.setError("Password must at least be 6 characters");
            }

            if(!confirmpassword.equals(password)){
                format=false;
                ConfirmPassword.setError("Passwords do not match");
            }

            if(identity.equals("")){
                format=false;
                Identity.setError("This field cannot be empty");
            }
            if(!identity.equals("")){
                if(identity.length()==13){
                    String data=identity.substring(0,2);
                    if(data.charAt(0)=='0'){
                        data="20"+data;
                    }

                    else{
                        data="19"+data;
                    }

                    if(2019-Integer.parseInt(data)<18){
                        format=false;
                        Toast.makeText(getApplicationContext(),"You must be 18 years or older to register",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            if(!TextUtils.isDigitsOnly(identity) || identity.length()!=13 && !identity.equals("")){
                format=false;
                Identity.setError("Enter a valid identity number");
            }

            if(!contact.equals("")){
                if(!TextUtils.isDigitsOnly(contact) || contact.charAt(0)!='0' ){
                    format=false;
                    Contact.setError("Enter a valid phone number");}
            }

            if(contact.equals("")){
                format=false;
                Contact.setError("This field cannot be empty");
            }

            if(gender.equals("")){
                format=false;
                Toast.makeText(getApplicationContext(), "Please select gender", Toast.LENGTH_LONG).show();
            }


            if(format){
                ContentValues Params;
                Params =new ContentValues();
                Params.put("ID_NUMBER", identity);
                Params.put("EMAIL_ADDRESS", emailaddress);
                Params.put("NAME", name);
                Params.put("SURNAME", surname);
                Params.put("CONTACT_NO",Integer.parseInt(contact));
                Params.put("GENDER","male");
                Params.put("PASSWORD",password);
                AsyncHTTPPost register=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/CreateAccount.php",Params) {
                    @Override
                    protected void onPostExecute(String output) {
                        String d="";
                        if(output.equals("success")){
                            finish();
                        }
                        else if(output.equals("Account already exist")){
                            Toast.makeText(getApplicationContext(), "Account already exist", Toast.LENGTH_SHORT).show();
                        }

                    }
                };

                register.execute();
            }

        }
    }
}

