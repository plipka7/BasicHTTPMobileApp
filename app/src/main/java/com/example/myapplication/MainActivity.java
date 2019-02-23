package com.example.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {
    Button userButton;
    EditText userURL;
    EditText userPort;
    EditText userMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Allow networking to occur on the main thread*/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*Set controls*/
        userURL = findViewById(R.id.userURL);
        userPort = findViewById(R.id.userPort);
        userButton = findViewById(R.id.sendRequest);
        userMessage = findViewById(R.id.userMessage);

        /*Set click listener*/
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("here");
                //String url = userURL.getText().toString();
                try {
                    String message = userMessage.getText().toString();
                    String url = userURL.getText().toString();
                    int port = Integer.parseInt(userPort.getText().toString());

                    Socket socket = new Socket(url, port);
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(message);
                    socket.close();
                } catch(Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: Message failed to send.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
