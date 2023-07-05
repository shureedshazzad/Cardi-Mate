package com.example.cardimate;  //RECYCLER VIEW to show the fetched data

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.example.cardimate.Class.CardModelAdapter;
import com.example.cardimate.Class.Cardmodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This the activity where the Inserted data, that is stored in the firebase, will be shown using recycler view
 * Each set of data will be situated in a Card , which is a predesigned layout
 */
public class MainActivity5 extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Cardmodel> list;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    CardModelAdapter adapter; // Declare the adapter as a class member

    /**
     * This is the oncreate method
     * @param savedInstanceState bunch of arguments
     */
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



        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            /**
             * This method here fetches data from firebase by matching Uid
             * @param snapshot  the instance of fetched data from firebase
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //list.clear();
                for (DataSnapshot itemsnapshot : snapshot.getChildren()) {
                    Cardmodel cardmodel = itemsnapshot.getValue(Cardmodel.class);
                    cardmodel.setKey(itemsnapshot.getKey());
                    list.add(cardmodel);
                }
                adapter.notifyDataSetChanged(); // Notify the adapter of the data change
                dialog.dismiss();
            }

            @Override
            /**
             * Handles error if needed
             */
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed

            }
        });

        dialog.show();
    }
}
