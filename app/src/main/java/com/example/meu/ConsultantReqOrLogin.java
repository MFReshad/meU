package com.example.meu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ConsultantReqOrLogin extends AppCompatActivity {

    Button b1,b2,bl;
    TextView tw,ps;
    View l;
    int vis=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_req_or_login);

        l = findViewById(R.id.layout);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        bl = findViewById(R.id.confirm_button);
        tw = findViewById(R.id.admin);
        ps = findViewById(R.id.passText);
        tw.setPaintFlags(tw.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ConsultantReqOrLogin.this, LoginConsultant.class);
                ConsultantReqOrLogin.this.startActivity(myIntent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });

        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLay();

            }
        });

        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = ps.getText().toString();
                String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(pass);
                if(sha256hex.equals("fd86714886e982d78522af58038a1237a4bf7b77533465c8211c462781b304f3"))
                {
                    Intent myIntent = new Intent(ConsultantReqOrLogin.this, AddCons.class);
                    ConsultantReqOrLogin.this.startActivity(myIntent);
                }
                else
                    Toast.makeText(ConsultantReqOrLogin.this, "Wrong pass", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onBackPressed() {
        if (vis == 1) {
            hideLay();

        } else
            super.onBackPressed();

    }


    private void showLay() {
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        tw.setVisibility(View.INVISIBLE);
        l.setVisibility(View.VISIBLE);
        vis =1;

    }
    private void hideLay() {
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        tw.setVisibility(View.VISIBLE);
        l.setVisibility(View.INVISIBLE);
        vis=0;
    }



    public void alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wanna be a Consultant!");
        builder.setMessage("You will need to submit your CV on your consulting experience by mail to us.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // LogIn.isLogin = 0;
                // saveData();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"180104040@aust.edu"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Wanna be a consultant #meU");
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                // stop chronometer here

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}