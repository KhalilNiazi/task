package com.niazi.taskks.SqL_DataBase;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.niazi.taskks.R;

import java.util.ArrayList;

public class Sql_adobtor extends RecyclerView.Adapter<Sql_adobtor.ViewHolder> {
    ArrayList<Sql_ModelClass> modelClassList;
    Databasehelper databasehelper;

    public Sql_adobtor(ArrayList<Sql_ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detali_itemview,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nam.setText(modelClassList.get(position).getName());
        holder.surnamee.setText(modelClassList.get(position).getSurname());
        holder.mark.setText(modelClassList.get(position).getMarks());
        holder.id.setText(modelClassList.get(position).getId());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),Sql_Database_Activity.class);
            intent.putExtra("id",modelClassList.get(position).getId());
            intent.putExtra("name",modelClassList.get(position).getName());
            intent.putExtra("surname",modelClassList.get(position).getSurname());
            intent.putExtra("mark",modelClassList.get(position).getMarks());
            view.getContext().startActivity(intent);
            ((Activity)view.getContext()).finish();

        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                {


                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                    alert.setTitle("delete this item");
                    alert.setMessage("Are you sure ");

                    alert.setCancelable(false);

                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            databasehelper = new Databasehelper(view.getContext());

                            databasehelper.getWritableDatabase();

                            Integer deletedRow =  databasehelper.deletedata(modelClassList.get(position).getId());

                            if(deletedRow>0)
                            {
                                Toast.makeText(view.getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(view.getContext(),Sql_Database_Activity.class);
                                view.getContext().startActivity(intent);
                                ((Activity)view.getContext()).finish();

                            }
                            else
                            {
                                Toast.makeText(view.getContext(), "Data Not Deleted", Toast.LENGTH_SHORT).show();
                            }




                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(view.getContext(), "Cancel", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {

        return modelClassList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nam,surnamee,mark,id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nam = itemView.findViewById(R.id.name);
            surnamee = itemView.findViewById(R.id.Surname);
            mark = itemView.findViewById(R.id.MARKS);
            id = itemView.findViewById(R.id.id);

        }

    }
}
