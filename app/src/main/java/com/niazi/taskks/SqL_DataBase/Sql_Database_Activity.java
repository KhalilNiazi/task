package com.niazi.taskks.SqL_DataBase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.niazi.taskks.Adoptor.Std_Detail_Adb;
import com.niazi.taskks.Adoptor.Std_Detail_Model;
import com.niazi.taskks.MainActivity;
import com.niazi.taskks.MainActivity2;
import com.niazi.taskks.R;

import java.util.ArrayList;

public class Sql_Database_Activity extends AppCompatActivity {
    RecyclerView recyclerView;

    Databasehelper databasehelper;

    Sql_ModelClass modelClass;
    String getid, getname, getsurnamew, getmark;
    Sql_adobtor adobtor;
    ArrayList<Sql_ModelClass> list;


    EditText edname, edsurenm, edmarksd, rdid;
    Button uploadbtu, Updatabtn;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_database);


        list = new ArrayList<>();
        ViewData();

        edname = findViewById(R.id.sname);
        edsurenm = findViewById(R.id.surna);
        edmarksd = findViewById(R.id.mardsds);
        rdid = findViewById(R.id.sids);
        uploadbtu = findViewById(R.id.uplbn);
        Updatabtn = findViewById(R.id.UPDATEbtn);
        recyclerView = findViewById(R.id.sqlrec);

        getid = getIntent().getStringExtra("id");
        getname = getIntent().getStringExtra("name");
        getmark = getIntent().getStringExtra("mark");
        getsurnamew = getIntent().getStringExtra("surname");

        rdid.setText(getid);
        rdid.setEnabled(false);
        edname.setText(getname);
        edmarksd.setText(getmark);
        edsurenm.setText(getsurnamew);


        adobtor.notifyDataSetChanged();
        recyclerView.setLayoutManager((new GridLayoutManager(this, 2)));

        databasehelper = new Databasehelper(this);
        databasehelper.getWritableDatabase();


        uploadbtu.setOnClickListener(view -> {

            String name = "";
            String surname = "";
            String mark = "";

            if (edname.getText().toString().equals("")) {
                Toast.makeText(this, "Fill Name", Toast.LENGTH_SHORT).show();
            } else {
                name = edname.getText().toString();
            }
            if (edsurenm.getText().toString().equals("")) {
                Toast.makeText(this, "Fill Surname", Toast.LENGTH_SHORT).show();
            } else {
                surname = edsurenm.getText().toString();
            }
            if (edmarksd.getText().toString().equals("")) {
                Toast.makeText(this, "Fill Mark", Toast.LENGTH_SHORT).show();
            } else {
                mark = edsurenm.getText().toString();
            }
            boolean isInsurted = databasehelper.insertData(edname.getText().toString(), edsurenm.getText().toString(), edmarksd.getText().toString());

            if (isInsurted == true) {
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                ViewData();
                recyclerView.scrollToPosition(list.size() - 1);


            } else {
                Toast.makeText(this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

            }
        });


        Updatabtn.setOnClickListener(view -> {

            boolean isupdate = databasehelper.updatedata(rdid.getText().toString(), edname.getText().toString(),
                    edsurenm.getText().toString(), edmarksd.getText().toString());


            if (isupdate == true) {
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
                edname.setText("");
                edsurenm.setText("");
                edmarksd.setText("");
                ViewData();
                recyclerView.scrollToPosition(list.size() - 1);

            } else {
                Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show();

            }
        });
     /*   Delletessbtn.setOnClickListener(view -> {


            Integer deleteRow = databasehelper.deletedata(rdid.getText().toString());

            if (deleteRow>0)
            {
                showMessage("Attention","Data Deleted");
                edname.setText("");
                edsurenm.setText("");
                edmarksd.setText("");
                recview();

            }else {
                showMessage("Error","Data Not Deleted");

            }
        });*/
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    //ViewData ma Cursor ka error a raha ha
    public void ViewData()
    {


        Cursor cursor = databasehelper.getAllData();
        list.clear();
        if(cursor.getCount() == 0 )
        {
            showMessage("Error","No Data Found");

            return;
        }
        while (cursor.moveToNext())
        {

            modelClass= new Sql_ModelClass(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));


            list.add(modelClass);
            adobtor = new Sql_adobtor(list);
            recyclerView.setAdapter(adobtor);

        }
    }

}