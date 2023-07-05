package com.example.cardimate;    //insert record

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cardimate.Class.Cardmodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * This Activity Inserts Data which will be stored in the firebae
 */
public class MainActivity4 extends AppCompatActivity {

    EditText systolic_pressure,diastolic_pressure,heart_rate,comment,date,time;
    private Button save_btn;


    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private String key = null;

    /**
     * This is the oncreate method
     * @param savedInstanceState   bunch of arguments
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        systolic_pressure=findViewById(R.id.sp);
        diastolic_pressure= findViewById(R.id.dp);
        heart_rate=findViewById(R.id.hr);
        comment= findViewById(R.id.c);
        date=findViewById(R.id.d);
        time=findViewById(R.id.t);
        save_btn=findViewById(R.id.btn_6);



        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            Cardmodel cardmodel = (Cardmodel) bundle.getSerializable("model");
            systolic_pressure.setText(cardmodel.getSystolicPressure());
            diastolic_pressure.setText(cardmodel.getDiastolicPressure());
            heart_rate.setText(cardmodel.getHeartRate());
            comment.setText(cardmodel.getComment());
            date.setText(cardmodel.getDate());
            time.setText(cardmodel.getTime());
            key = cardmodel.getKey();

            save_btn.setText(R.string.update);

            TextView tv = findViewById(R.id.create_record);
            tv.setText(R.string.update_record);
        }
        /**
         * User users timepicker to select time the widget
         */
        time.setOnClickListener(v->select_time());

        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dp=new DatePickerDialog.OnDateSetListener() { //setting calender

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day_of_month) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day_of_month);
                updatecalender();

            }



            public void updatecalender(){
                String format="dd/MM/YYYY";
                SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
                date.setText(sdf.format(calendar.getTime()));

            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity4.this, dp, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

//        back_btn.setOnClickListener(new View.OnClickListener() { //back to interface acitvity
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity4.this,Interface.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Cards");

        String data = (key == null) ? databaseRef.push().getKey() : key;
        /**
         * Clicking on this button, the inserted data will be saved to the firebase
         */
        save_btn.setOnClickListener(new View.OnClickListener() { //insert data to firebase for different user and then back to interface activity
            @Override
            public void onClick(View view) {

                final String sp=systolic_pressure.getText().toString();
                final String dp=diastolic_pressure.getText().toString();
                final String hr=heart_rate.getText().toString();
                final String d=date.getText().toString();
                final String t=time.getText().toString();
                final String c=comment.getText().toString();

                if(sp.isEmpty() || dp.isEmpty() || hr.isEmpty() || d.isEmpty() || t.isEmpty() || c.isEmpty()){
                    Toast.makeText(MainActivity4.this,"Please  Fill  All The Required Fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity4.this,"Record Is Saved",Toast.LENGTH_SHORT).show();

                    HashMap<String,Object> m=new HashMap<String,Object>();
                    m.put("systolicPressure",sp);
                    m.put("diastolicPressure",dp);
                    m.put("heartRate",hr);
                    m.put("date",d);
                    m.put("time",t);

                    if(!c.isEmpty()){
                        m.put("comment",c);
                    }
                   databaseRef.child(userId).child(data).setValue(m);

                    Intent intent=new Intent(MainActivity4.this,Interface.class);
                    startActivity(intent);
                    finish();
                }
            }
        });





    }

    public void select_time(){ //time setting
        Calendar currenttime=Calendar.getInstance();
        int hour=currenttime.get(Calendar.HOUR_OF_DAY);
        int minute=currenttime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog=new TimePickerDialog(MainActivity4.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                currenttime.set(Calendar.HOUR_OF_DAY,h);
                currenttime.set(Calendar.MINUTE,m);

                String myformat="HH:mm";
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat(myformat,Locale.US);
                time.setText(simpleDateFormat.format(currenttime.getTime()));

            }
        },hour,minute,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}