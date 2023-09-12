package com.niazi.taskks.Adoptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.niazi.taskks.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Std_Detail_Adb extends RecyclerView.Adapter<Std_Detail_Adb.ViewHolder> {


    List<Std_Detail_Model> list;

    public Std_Detail_Adb(List<Std_Detail_Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detali_itemview,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nam.setText(list.get(position).getName());
        holder.pho.setText(list.get(position).getPhoneno());
//        holder.circleImageView.setImageResource(list.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nam,pho;
        CircleImageView circleImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nam = itemView.findViewById(R.id.name);
     //       pho = itemView.findViewById(R.id.phoneno);
          //  circleImageView = itemView.findViewById(R.id.profile_image);

        }

    }
}
