package com.example.cardimate;  //RECYCLER VIEW to show the fetched data

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardimate.Class.CardModelAdapter;
import com.example.cardimate.Class.Cardmodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Cardmodel> list;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    CardModelAdapter adapter; // Declare the adapter as a class member

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity5.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();

        adapter = new CardModelAdapter(getApplicationContext(), list); // Initialize the adapter

        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cards");



        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //list.clear();
                for (DataSnapshot itemsnapshot : snapshot.getChildren()) {
                    Cardmodel cardmodel = itemsnapshot.getValue(Cardmodel.class);
                    cardmodel.setKey(itemsnapshot.getKey());
                    list.add(cardmodel);
                }
                adapter.notifyDataSetChanged(); // Notify the adapter of the data change

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed

            }
        });


    }
}
