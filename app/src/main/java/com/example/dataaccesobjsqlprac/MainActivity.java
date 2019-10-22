package com.example.dataaccesobjsqlprac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FriendsDAO friendsDAO;
    TextView textV;
    EditText first, last, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        friendsDAO = new FriendsDAO(this);
        friendsDAO.open();
        first = (EditText)findViewById(R.id.first);
        last = (EditText)findViewById(R.id.last);
        email = (EditText)findViewById(R.id.email);
        textV = (TextView)findViewById(R.id.textV);
        friendsDAO.close();
    }

    @Override protected void onResume() {
        friendsDAO.open();
        super.onResume();
    }

    @Override protected void onPause() {
        friendsDAO.close();
        super.onPause();
    }

    public void showRecords(View v) {
        List<Friend> friends = friendsDAO.getAllFriends();
        String str = "";
        for (Friend f : friends) {
            String row = f.getId() + ": First Name: " +
                    f.getFirstName() + ", Last Name: " +
                    f.getLastName() + ", Email: " + f.getEmail();
            str += row + "\n";
        }
        textV.setText(str);
    }

    public void addRecord(View v) {
        String fn = first.getText().toString();
        String ln = last.getText().toString();
        String em = email.getText().toString();
        Friend f = new Friend(0, fn, ln, em);
        friendsDAO.addFriend(f);
    }

    public void deleteRecords(View v) {
        textV.setText("");
        friendsDAO.deleteFriends();
    }
}
