package com.example.caro_matic.caro_matic;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MenuHolder>{

    private static ArrayList<Disease> mDiseases;

    public static class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView;
        private TextView percentTextView;

        public MenuHolder(View v) {
            super(v);

            nameTextView = v.findViewById(R.id.disease_name);
            percentTextView = v.findViewById(R.id.disease_percent);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
//            builder1.setMessage(description);
//            builder1.setCancelable(true);
//
//            AlertDialog alert11 = builder1.create();
//            alert11.show();
        }

        public void bindIndustry(String name, String percent) {
            SpannableString content = new SpannableString(name);
            content.setSpan(new UnderlineSpan(), 0, name.length(), 0);
            nameTextView.setText(content);
            percentTextView.setText(percent);
//            this.description = description;
        }
    }

    public ResultsAdapter(ArrayList<Disease> disease) {
        mDiseases = disease;
    }

    @Override
    public ResultsAdapter.MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        return new MenuHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ResultsAdapter.MenuHolder holder, int position) {
        String name = mDiseases.get(position).getName();
        String percent = mDiseases.get(position).getPercent();
//        String description = mDiseases.get(position).getDescription();
        holder.bindIndustry(name, percent);
    }

    @Override
    public int getItemCount() {
        return mDiseases.size();
    }
}
