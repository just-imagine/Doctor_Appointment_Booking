package com.example.a1611821.doctor_appointment_booking;

import android.widget.TextView;

/**
 * Created by 1613720 on 2019/03/11.
 */

public class AdvancedSlots {

    TextView []Days;
    TextView []Slots;
    public AdvancedSlots(TextView[] days,TextView[] slots){
        this.Days=days;
        this.Slots=slots;
    }

    public  void  Assign(){
        //first 11 in slots should have same date different times
        //letters used
        int time=8;
        for(int i=0;i<Slots.length;++i){
            if(i<11){
                Slots[i].setText("A"+(i+1)+","+Days[0].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==10){
                    time=8;
                }
            }

            else if(i>=11 && i<22){
                Slots[i].setText("B"+(i+1-11)+","+Days[1].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==21){
                    time=8;
                }
            }
            else if(i>=22 && i<33){
                Slots[i].setText("C"+(i+1-22)+","+Days[2].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==32){
                    time=8;
                }
            }
            else if(i>=33 && i<44){
                Slots[i].setText("D"+(i+1-33)+","+Days[3].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==43){
                    time=8;
                }
            }
            else if(i>=44 && i<55){
                Slots[i].setText("E"+(i+1-44)+","+Days[4].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==54){
                    time=8;
                }
            }
            else if(i>=55 && i<66){
                Slots[i].setText("F"+(i+1-55)+","+Days[5].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==65){
                    time=8;
                }
            }
            else if(i>=66 && i<77){
                Slots[i].setText("G"+(i+1-66)+","+Days[6].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==76){
                    time=8;
                }
            }
            else if(i>=77 && i<88){
                Slots[i].setText("H"+(i+1-77)+","+Days[7].getText().toString().split("\\s")[1]+time);
                ++time;
                if(i==87){
                    time=8;
                }
            }
        }
    }
}
