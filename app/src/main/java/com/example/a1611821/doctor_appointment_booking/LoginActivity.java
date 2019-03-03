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

public class LoginActivity extends AppCompatActivity {

    Button Login;
    EditText Username;
    EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);

        Login=(Button)findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=Username.getText().toString();
                String password=Password.getText().toString();
                ContentValues Params=new ContentValues();
                Params.put("USERNAME",username);
                Params.put("PASSWORD",password);

                AsyncHTTPPost SignIn=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/Login.php",Params) {
                    @Override
                    protected void onPostExecute(String output) {
                        if(output.equals("success")){
                            Intent HomeActivity=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(HomeActivity);
                        }

                        else if(output.equals("unsuccessful")){
                            Toast.makeText(getApplicationContext(),"Check your username and password",Toast.LENGTH_SHORT).show();
                        }

                    }
                };
                SignIn.execute();
            }
        });
    }

    public void _register(View view){
        startActivity(new Intent(getApplicationContext(), WelcomeActivity2.class));

    }
   
}
