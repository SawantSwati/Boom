package com.example.boom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    TextInputEditText meetingIDInput, nameInput;
    MaterialButton joinBtn, createBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meetingIDInput = findViewById(R.id.meeting_id_input);
        nameInput = findViewById(R.id.name_input);
        joinBtn = findViewById(R.id.join_btn);
        createBtn = findViewById(R.id.create_btn);

        joinBtn.setOnClickListener((v) -> {
            String meetingID = meetingIDInput.getText().toString();
            if (meetingID.length() != 10) {
                meetingIDInput.setError("Invalid Meeting ID");
                meetingIDInput.requestFocus();
                return;
            }

            String name = nameInput.getText().toString();
            if (name.isEmpty()) {
                nameInput.setError("Name is required to join the meeting");
                nameInput.requestFocus();
                return;
            }
            startMeeting(meetingID, name);

        });


        createBtn.setOnClickListener(v->{
            String name = nameInput.getText().toString();
            if (name.isEmpty()) {
                nameInput.setError("Name is required to create the meeting");
                nameInput.requestFocus();
                return;
            }
            startMeeting(getRandomMeetingID(),name);


        });

    }
        void startMeeting (String meetingID, String name){
            String userID = UUID.randomUUID().toString();

            Intent intent = new Intent(MainActivity.this,ConferenceActivity.class);
            intent.putExtra("meeting_ID",meetingID);
            intent.putExtra("name",name);
            intent.putExtra("user_id",userID);

            startActivity(intent);

        }
        String getRandomMeetingID(){
        StringBuilder id = new StringBuilder();
        while (id.length() != 10){
            int random = new Random().nextInt(10);
            id.append(random);
        }
        return id.toString();
        }

    }