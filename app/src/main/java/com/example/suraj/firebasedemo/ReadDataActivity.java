package com.example.suraj.firebasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);


       // final EditText id = (EditText) findViewById(R.id.readbxrId);
        final EditText name = (EditText) findViewById(R.id.readbxrName);
        Button sendData = (Button) findViewById(R.id.read);
        final TextView value = (TextView) findViewById(R.id.value);
        Button readValues = (Button) findViewById(R.id.readValues);
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // this will create a unique fitbase key for us
                String key = reference.push().getKey();

                //this will add a oot children key and value
                reference.child(key).setValue(name.getText().toString());

                //this will create a category with unique firebase key
                // and put the child with (key-name,value-Suraj Gupta);
                reference.push().child("name").setValue("Suraj Gupta");

            }
        });


        readValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will reead the values
                FirebaseDatabase.getInstance().getReference("-KovOHdNCtkc8xgT1sfc/name")
                        .addValueEventListener(new ValueEventListener() {
                            // this method will change the text automatically after changing the value in firebase also
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                               String v =  dataSnapshot.getValue(String.class);//if the values is Integer than put = dataSnapshot.getValue(Integer.class);
                                value.setText(v);


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });
    }
}
