package com.niazi.taskks;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

/** @noinspection ALL*/
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    EditText edname,edemail,edadress,edphone;
    TextView textView;
    CircleImageView imageView;
    ImageView uploadView;

    Button upload,show;
    int rollid = 0;

    private int Gallery_Code;

    Uri uri;
    String ImageUrl;

    ProgressDialog progressDialog;


    FirebaseDatabase mdatabase;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edname=findViewById(R.id.editTextname);
        edemail=findViewById(R.id.classed);
        edadress=findViewById(R.id.subject);
        edphone=findViewById(R.id.editTextPhone2);
        imageView=findViewById(R.id.profile_imag);
        uploadView=findViewById(R.id.uploadphotob);
        show=findViewById(R.id.seedeatail);
        upload=findViewById(R.id.uploadbn);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Student Detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rollid = (int) snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mdatabase = FirebaseDatabase.getInstance();
        reference=mdatabase.getReference().child("Student Detail");
        firebaseStorage= FirebaseStorage.getInstance();


        uploadView.setOnClickListener(view -> {
            Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, Gallery_Code);
        });
        progressDialog = new ProgressDialog(this);

        //                             .child(String.valueOf(rollid+1)).on                                                          .putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                             /*   @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                                    while (!uriTask.isCanceled());

                                            imageUrl = uriTask.getResult().toString();

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("url", imageUrl);
                                    map.put("name", binding.editTextname.getText().toString());
                                    map.put("class", binding.classed.getText().toString());
                                    map.put("subject", binding.subject.getText().toString());
                                    map.put("phoneno", binding.editTextPhone2.getText().toString());

                                    FirebaseDatabase.getInstance().getReference().child("Student Details")
                                            .child(String.valueOf(rollid+1)).setValue(map);


                                }

                            });


        });
*/



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && resultCode == RESULT_OK) {

            uri = data.getData();
            imageView.setImageURI(uri);
        }
        upload.setOnClickListener(view -> {


            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Attention Please");
            alert.setMessage("Are You Sure");
            alert.setInverseBackgroundForced(true);
            alert.setCancelable(false);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
              /*   progressDialog.setTitle("Uploading...");
                 progressDialog.show();


*/
                    String editTextnam = edname.getText().toString().trim();
                    String classe =  edemail.getText().toString().trim();
                    String subjec = edadress.getText().toString().trim();
                    String editTextPhoe2 = edphone.getText().toString().trim();


                    if (!(editTextnam.isEmpty() && classe.isEmpty() && subjec.isEmpty() && editTextPhoe2.isEmpty() && uri != null)) {
                        progressDialog.setTitle("Uploading...");
                        progressDialog.show();

                        StorageReference filepath = firebaseStorage.getReference().child("Student Profile")
                                .child(uri.getLastPathSegment());

                        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {


                                        String imageUrl = task.getResult().toString();

                                        DatabaseReference map = reference.push();
                                        map.child("image").setValue(task.getResult().toString());
                                        map.child("name").setValue(editTextnam);
                                        map.child("email").setValue(classe);
                                        map.child("address").setValue(subjec);
                                        map.child("phoneno").setValue(editTextPhoe2);

                                        progressDialog.dismiss();
                                /*     FirebaseDatabase.getInstance().getReference().child("Student Details")
                                             .child(String.valueOf(rollid + 1)).setValue(map);
                                */ }
                                });
                            }

                        });
                    }
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();

                }
            });
            alert.show();
/*
                 FirebaseStorage.getInstance().getReference().child("Student").child(String.valueOf(rollid + 1)).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                         Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                         while (!uriTask.isComplete());
                             Uri ImageUri = uriTask.getResult();
                             ImageUrl= ImageUri.toString();


                         HashMap<String, String> map = new HashMap<>();
                         map.put("url", ImageUrl);
                         map.put("name", binding.editTextname.getText().toString());
                         map.put("email", binding.classed.getText().toString());
                         map.put("address", binding.subject.getText().toString());
                         map.put("phoneno", binding.editTextPhone2.getText().toString());

                         FirebaseDatabase.getInstance().getReference().child("Student")
                                 .child(String.valueOf(rollid + 1)).setValue(map);

                         progressDialog.dismiss();


                     }
                 });
             }
             }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                 }
             });
         alert.show();


         });*/


        });

    }
}