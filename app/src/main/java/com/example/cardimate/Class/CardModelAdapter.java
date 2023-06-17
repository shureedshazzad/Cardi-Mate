package com.example.cardimate.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardimate.R;

import java.util.ArrayList;
import java.util.List;

public class CardModelAdapter extends RecyclerView.Adapter<CardModelAdapter.CardModelViewHolder> {
    private Context context;
    private ArrayList<Cardmodel> cardModelList;

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

        holder.dateMeasuredTextView.setText(cardModel.getDate());
        holder.systolicDataTextView.setText(cardModel.getSystolicPressure()+" mmHg");
        holder.diastolicDataTextView.setText(cardModel.getDiastolicPressure()+" mmHg");
        holder.heartRateDataTextView.setText(cardModel.getHeartRate()+" bits/min");
        holder.statusDataTextView.setText(cardModel.getComment());
        holder.TimeDataTextView.setText(cardModel.getTime());

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

        public CardModelViewHolder(@NonNull View itemView) {
            super(itemView);
            dateMeasuredTextView = itemView.findViewById(R.id.r_r_v_i_dateMeasured);
            systolicDataTextView = itemView.findViewById(R.id.r_r_v_i_systolic_data);
            diastolicDataTextView = itemView.findViewById(R.id.r_r_v_i_diastolic_data);
            heartRateDataTextView = itemView.findViewById(R.id.r_r_v_i_heart_rate_data);
            statusDataTextView = itemView.findViewById(R.id.r_r_v_i_status_data);
            TimeDataTextView=itemView.findViewById(R.id.r_r_v_i_timestampdata);
            editButton = itemView.findViewById(R.id.r_r_v_i_edit_button);
            deleteButton = itemView.findViewById(R.id.r_r_v_i_delete_button);
            cardView = itemView.findViewById(R.id.reccard);
        }
    }
}
