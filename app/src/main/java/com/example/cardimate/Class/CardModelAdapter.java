package com.example.cardimate.Class;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardimate.MainActivity6;
import com.example.cardimate.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Adapter class for the CardModel RecyclerView.
 * Handles binding data to the views and defining click listeners for each item.
 */
public class CardModelAdapter extends RecyclerView.Adapter<CardModelAdapter.CardModelViewHolder> {
    private Context context;
    private ArrayList<Cardmodel> cardModelList;
    /**
     * Constructor for the CardModelAdapter class.
     * @param context The context of the calling activity or fragment.
     * @param cardModelList The list of Cardmodel objects to be displayed in the RecyclerView.
     */
    public CardModelAdapter(Context context, ArrayList<Cardmodel> cardModelList) {
        this.context = context;
        this.cardModelList = cardModelList;
    }

    @NonNull
    @Override
    public CardModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        return new CardModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardModelViewHolder holder, int position) {
        Cardmodel cardModel = cardModelList.get(position);

        // Bind data to the views
        holder.dateMeasuredTextView.setText(cardModel.getDate());
        holder.systolicDataTextView.setText(cardModel.getSystolicPressure()+" mmHg");
        holder.diastolicDataTextView.setText(cardModel.getDiastolicPressure()+" mmHg");
        holder.heartRateDataTextView.setText(cardModel.getHeartRate()+" bits/min");
        holder.statusDataTextView.setText(cardModel.getComment());
        holder.TimeDataTextView.setText(cardModel.getTime());

        //holder.clStatus.setBackgroundColor(cardModel.getBackColor());
        holder.clStatus.setBackgroundColor(context.getResources().getColor(cardModel.getBackColor()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainActivity6.class);
                intent.putExtra("model",cardModelList.get(holder.getAdapterPosition()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        // Set click listeners or any other logic for buttons (edit and delete)

    }

    @Override
    public int getItemCount() {
        return cardModelList.size();
    }

    public static class CardModelViewHolder extends RecyclerView.ViewHolder {
        private TextView dateMeasuredTextView;
        private TextView systolicDataTextView;
        private TextView diastolicDataTextView;
        private TextView heartRateDataTextView;
        private TextView statusDataTextView;
        private TextView TimeDataTextView;
        private CardView cardView;
        Button editButton,deleteButton;
        private ConstraintLayout clStatus;

        public CardModelViewHolder(@NonNull View itemView) {
            super(itemView);
            clStatus = itemView.findViewById(R.id.r_r_v_i_status_background);
            dateMeasuredTextView = itemView.findViewById(R.id.r_r_v_i_dateMeasured);
            systolicDataTextView = itemView.findViewById(R.id.r_r_v_i_systolic_data);
            diastolicDataTextView = itemView.findViewById(R.id.r_r_v_i_diastolic_data);
            heartRateDataTextView = itemView.findViewById(R.id.r_r_v_i_heart_rate_data);
            statusDataTextView = itemView.findViewById(R.id.r_r_v_i_status_data);
            TimeDataTextView=itemView.findViewById(R.id.r_r_v_i_timestampdata);

            cardView = itemView.findViewById(R.id.reccard);
        }
    }
}
