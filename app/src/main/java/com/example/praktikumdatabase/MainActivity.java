package com.example.praktikumdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nama, nrp, jurusan;
    Button create, read, update, delete;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = findViewById(R.id.nama);
        nrp = findViewById(R.id.nrp);
        jurusan = findViewById(R.id.jurusan);
        create = findViewById(R.id.buttonCreate);
        read = findViewById(R.id.buttonRead);
        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);
        db = new DBHelper(this);

//      Button Input Data
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String namatxt = nama.getText().toString();
                String nrptxt = nrp.getText().toString();
                String jurusantxt = jurusan.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(namatxt,nrptxt,jurusantxt);
                if (checkinsertdata)
                    Toast.makeText(MainActivity.this, "Input Data Berhasil", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Input Data Gagal", Toast.LENGTH_SHORT).show();
            }
        });

//      Button Update Data
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namatxt = nama.getText().toString();
                String nrptxt = nrp.getText().toString();
                String jurusantxt = jurusan.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(namatxt,nrptxt,jurusantxt);
                if(checkupdatedata)
                    Toast.makeText(MainActivity.this, "Update Data Berhasil", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Update Data Gagal", Toast.LENGTH_SHORT).show();
            }
        });

//      Button Read Data
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getdata();
                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Tidak Ada Data Mahasiswa", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nama : " + res.getString(0) + "\n");
                    buffer.append("NRP : " + res.getString(1) + "\n");
                    buffer.append("Jurusan : " + res.getString(2) + "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Data Mahasiswa");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
//      Button Delete Data
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namatxt = nama.getText().toString();
                Boolean deletedata = db.deletedata(namatxt);
                if (deletedata)
                    Toast.makeText(MainActivity.this, "Hapus Data Berhasil", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Hapus Data Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}