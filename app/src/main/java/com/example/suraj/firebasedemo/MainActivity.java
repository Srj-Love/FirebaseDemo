package com.example.suraj.firebasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //First Step
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //we can do it this way also
       //DatabaseReference referenceFromUrl = FirebaseDatabase.getInstance().getReferenceFromUrl        ("https://fir-demo-970f4.firebaseio.com/");


        // get Reference where to write or store your data
        final DatabaseReference reference = database.getReferenceFromUrl("https://fir-demo-970f4.firebaseio.com/");


        final EditText id = (EditText) findViewById(R.id.bxrId);
        final EditText name = (EditText) findViewById(R.id.bxrName);
        final EditText power = (EditText) findViewById(R.id.bxrPunchPower);
        final EditText speed = (EditText) findViewById(R.id.bxrPunchSpeed);
        final EditText stamina = (EditText) findViewById(R.id.bxrStamina);

        Button sendData = (Button) findViewById(R.id.sendData);

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //                      category          key               value
                reference.child(id.getText().toString()).child("Name").setValue(name.getText().toString());
                reference.child(id.getText().toString()).child("Boxer Power").setValue(power.getText().toString());
                reference.child(id.getText().toString()).child("Speed").setValue(speed.getText().toString());
                reference.child(id.getText().toString()).child("Stamina").setValue(stamina.getText().toString());
            }
        });

    }

    public void readData(View view) {
        startActivity(new Intent(MainActivity.this,ReadDataActivity.class));
    }
}
