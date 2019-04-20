package com.example.a1611821.doctor_appointment_booking;


import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.linkedin.android.testbutler.TestButler;

public class TestRunner extends AndroidJUnitRunner {

    @Override
    public void onStart() {
        TestButler.setup(getTargetContext());
        super.onStart();
    }

    @Override
    public void finish(int resultCode, Bundle results) {
        TestButler.teardown(getTargetContext());
        super.finish(resultCode, results);
    }
}