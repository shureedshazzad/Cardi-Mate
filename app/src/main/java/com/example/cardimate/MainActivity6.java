package com.example.cardimate;// Data Update and Delete

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardimate.Class.Cardmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This activity contains details , and update and delete option
 */

public class MainActivity6 extends AppCompatActivity {
    TextView sys,dia,date,comment,time,heart_rate;
    FloatingActionButton delete,edit;
    String key="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        sys=findViewById(R.id.syspressure2);
        dia=findViewById(R.id.diapressure2);
        date=findViewById(R.id.date2);
        time=findViewById(R.id.time2);
        heart_rate=findViewById(R.id.heart_rate2);
        comment=findViewById(R.id.status2);
        delete=findViewById(R.id.delete);
        edit=findViewById(R.id.edit);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            Cardmodel model = (Cardmodel)bundle.getSerializable("model");

            sys.setText(model.getFormattedSys());
            dia.setText(model.getFormattedDys());
            date.setText(model.getDate());
            time.setText(model.getTime());
            heart_rate.setText(model.getFormattedHeartRate());
            comment.setText(model.getComment());
            key = model.getKey();

        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cards").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                reference.child(key).removeValue();
                Toast.makeText(MainActivity6.this, "DELETED !!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity5.class));
                finish();
            }
        });
        edit.setOnClickListener(v -> {
            if(bundle == null) return;

            Intent intent=new Intent(MainActivity6.this,MainActivity4.class);

            String sys = bundle.getString("systolicPressure");
            String dys = bundle.getString("diastolicPressure");
            String date = bundle.getString("date");
            String time = bundle.getString("time");
            String heart_rate = bundle.getString("heartRate");
            String comment = bundle.getString("comment");
            Cardmodel cardmodel = new Cardmodel(date,dys,heart_rate,sys,time,comment);
            cardmodel.setKey(key);
            intent.putExtra("model",cardmodel);
            intent.putExtra("fromEdit",true);

            startActivity(intent);
        });


    }
}

