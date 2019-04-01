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

<<<<<<< HEAD
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

=======
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81
public class LoginActivity extends AppCompatActivity {

    Button Login;
    EditText Username;
    EditText Password;
<<<<<<< HEAD
=======
    String h="";
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81
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
<<<<<<< HEAD
                final String username=Username.getText().toString();
=======
                String username=Username.getText().toString();
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81
                String password=Password.getText().toString();
                ContentValues Params=new ContentValues();
                Params.put("USERNAME",username);
                Params.put("PASSWORD",password);

                AsyncHTTPPost SignIn=new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1611821/Login.php",Params) {
                    @Override
                    protected void onPostExecute(String output) {
<<<<<<< HEAD
                        String form[]=output.split(",");
                        String identity="";
                        try {
                            JSONArray result=new JSONArray(output);
                            for(int i=0;i<result.length();++i){
                                JSONObject obj=result.getJSONObject(i);
                                identity=obj.getString("ID_NUMBER");
                            }

                            if(!identity.equals("")){
                                Intent HomeActivity=new Intent(getApplicationContext(),HomeActivity.class);
                                HomeActivity.putExtra("Username",username);
                                HomeActivity.putExtra("Identity",identity);
                                startActivity(HomeActivity);
                            }
                        } catch (JSONException e) {
                             if(output.equals("unsuccessful")){
                                Toast.makeText(getApplicationContext(),"Check your username and password",Toast.LENGTH_SHORT).show();}

                              else if(output.equals("")){
                                     Toast.makeText(getApplicationContext(),"connection error, check your internet connection",Toast.LENGTH_SHORT).show();
                                 }
                                  e.printStackTrace();



=======
                        if(output.equals("success")){
                            Intent HomeActivity=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(HomeActivity);
                        }

                        else if(output.equals("unsuccessful")){
                            Toast.makeText(getApplicationContext(),"Check your username and password",Toast.LENGTH_SHORT).show();
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81
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
