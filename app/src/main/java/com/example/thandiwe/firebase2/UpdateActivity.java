package com.example.thandiwe.firebase2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {

    EditText etName, etSurname;
    Button btnUpdate;
    private DatabaseReference databaseReference;
    private DatabaseReference dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etName=(EditText)findViewById(R.id.editText3);
        etSurname=(EditText)findViewById(R.id.editText4);
        btnUpdate = (Button)findViewById(R.id.update);

        Intent intent = getIntent();
        String key = intent.getStringExtra("name");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Person").child(key);
        dbWrite = FirebaseDatabase.getInstance().getReference().child("Person").child(key);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                final Person person = dataSnapshot.getValue(Person.class);

                etName.setText(person.getName());
                etSurname.setText(person.getSurname());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        person.setName(etName.getText().toString());
                        person.setName(etSurname.getText().toString());
                        dbWrite.setValue(person);

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
