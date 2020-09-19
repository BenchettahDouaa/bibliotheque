package com.pharmaservice.bibliotheque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {
    DBHelper myDb;
    EditText et1,et2,et3;
    Button btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        myDb = new DBHelper(this);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        btn5=(Button)findViewById(R.id.button);
        AddData();;

    }
    public void AddData(){
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isInserted = myDb.insertBooks(et1.getText().toString(),et2.getText().toString(),et3.getText().toString());
              if(isInserted==true)
                  Toast.makeText(AddBook.this,"le livre est inserer",Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(AddBook.this,"le livre n'est pas inserer",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void retour(View view){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
