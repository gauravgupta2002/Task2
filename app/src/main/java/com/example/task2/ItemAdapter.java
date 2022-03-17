package com.example.task2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private ArrayList<ModalClass> courseModalArrayList;
    private Context context;

    public ItemAdapter(ArrayList<ModalClass> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }


   @SuppressLint("NotifyDataSetChanged")
   public void filterList(ArrayList<ModalClass> filterllist) {
        courseModalArrayList = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_items, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ModalClass modal = courseModalArrayList.get(position);
        holder.courseName.setText(modal.getName());
        holder.courseDesc.setText(modal.getColor());
        holder.coursePrice.setText(modal.getPrice());
    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName, courseDesc, coursePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.fruitName);
            courseDesc = itemView.findViewById(R.id.color);
            coursePrice = itemView.findViewById(R.id.rate);
        }
    }
}
