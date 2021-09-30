package com.example.meu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.meu.LogIn.TEXT_NAME;

public class ConsConfirm extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_confirm);

       // final TypeWriter tw = (TypeWriter) findViewById(R.id.tw);
       // final TypeWriter tw1 = (TypeWriter) findViewById(R.id.tw1);

        btn = findViewById(R.id.confirm_button);


        String last = " ";

/*
        tw1.setText("");
        tw1.setCharacterDelay(250);
        tw1.animateText(Html.fromHtml(last));


 */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });

    }

    public void alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation message of payment!");
        builder.setMessage("Are you sure to payment 1800/- tk to get access?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(ConsConfirm.this, "Payment Successful.", Toast.LENGTH_SHORT).show();
                // LogIn.isLogin = 0;
                // saveData();
                Intent myIntent1 = new Intent(ConsConfirm.this, ChatWithCons.class);
                startActivity(myIntent1);
                dialog.dismiss();
                // stop chronometer here

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}