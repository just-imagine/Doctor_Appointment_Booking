package com.example.a1611821.doctor_appointment_booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81

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
<<<<<<< HEAD

        Intent Bookings=(new Intent(getApplicationContext(), Viewings.class));
        String username=getIntent().getStringExtra("Username");
        String Identity=getIntent().getStringExtra("Identity");
        Bookings.putExtra("Username",username);
        Bookings.putExtra("Identity",Identity);
        startActivity(Bookings);
=======
        startActivity(new Intent(getApplicationContext(), Viewings.class));
>>>>>>> 18523281a9e135acc8ec2d4b45cf212b34d74a81

    }

    public void startPrescriptions(View view){
        startActivity(new Intent(getApplicationContext(), PrescriptionActivity.class));

    }

    public void startChat(View view){
        startActivity(new Intent(getApplicationContext(), ChatActivity.class));

    }


}
