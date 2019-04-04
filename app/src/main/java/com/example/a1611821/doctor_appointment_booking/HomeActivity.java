package com.example.a1611821.doctor_appointment_booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startProfile(View view){
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

    }

    public void startCalendar(View view){

        Intent Bookings=(new Intent(getApplicationContext(), Viewings.class));
        String username=getIntent().getStringExtra("Username");
        String Identity=getIntent().getStringExtra("Identity");
        Bookings.putExtra("Username",username);
        Bookings.putExtra("Identity",Identity);
        startActivity(Bookings);

    }

    public void startPrescriptions(View view){
        startActivity(new Intent(getApplicationContext(), PrescriptionActivity.class));

    }

    public void startChat(View view){
        startActivity(new Intent(getApplicationContext(), ChatActivity.class));

    }


}
