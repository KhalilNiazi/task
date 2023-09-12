package com.niazi.taskks;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.niazi.taskks.Adoptor.Std_Detail_Adb;
import com.niazi.taskks.Adoptor.Std_Detail_Model;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButto;


    ArrayList<Std_Detail_Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        floatingActionButto= findViewById(R.id.floatingActionButton);


        recyclerView=findViewById(R.id.detailrec);


        list = new ArrayList<>();

        list.add(new Std_Detail_Model("Imran","034333232", R.drawable.manpink));
        list.add(new Std_Detail_Model("Aslam","036332223", R.drawable.images));
        list.add(new Std_Detail_Model("Zohaib","03247319", R.drawable.man));
        list.add(new Std_Detail_Model("Arif","53232233", R.drawable.manwhite));
        list.add(new Std_Detail_Model("Ayan","73794324", R.drawable.mangry));


        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);

        Std_Detail_Adb stdDetailAdb1 = new Std_Detail_Adb(list);

        recyclerView.setAdapter(stdDetailAdb1);
        recyclerView.setLayoutManager(linearLayoutManager);



        floatingActionButto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity2.this);
                dialog.setContentView(R.layout.dialog);

                Button button = dialog.findViewById(R.id.uploasd);
                EditText editname = dialog.findViewById(R.id.namde);
                EditText editphone = dialog.findViewById(R.id.phodne);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        String number = "";

                        if (editname.getText().toString().equals("")) {
                            Toast.makeText(MainActivity2.this, "Fill Name", Toast.LENGTH_SHORT).show();
                        } else {
                            name = editname.getText().toString();
                        }
                        if (editphone.getText().toString().equals("")) {
                            Toast.makeText(MainActivity2.this, "Fill Phone Number", Toast.LENGTH_SHORT).show();
                        } else {
                            number = editphone.getText().toString();
                        }


                        list.add(new Std_Detail_Model(name,number, R.drawable.sd));

                        stdDetailAdb1.notifyItemInserted(list.size()-1);
                        recyclerView.scrollToPosition(list.size()-1);

                        dialog.dismiss();

                    }
                });
                dialog.show();

            }



        });


    }}