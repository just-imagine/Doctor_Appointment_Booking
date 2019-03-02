package com.example.a1611821.doctor_appointment_booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
    }

    public void close(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}

