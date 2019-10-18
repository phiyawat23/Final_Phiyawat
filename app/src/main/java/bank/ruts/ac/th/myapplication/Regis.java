package bank.ruts.ac.th.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Regis extends AppCompatActivity {
    MediaPlayer sReris;
    Button btnRegis;
    EditText username,password;
    User userpush;
    public DatabaseReference user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        //Edittext
        username = (EditText)findViewById(R.id.etUname);
        password = (EditText)findViewById(R.id.etPass);
        //Button
        btnRegis = (Button)findViewById(R.id.btnRegister);
        //Soundbtn
        sReris = MediaPlayer.create(Regis.this, R.raw.effect_btn_shut);
        //Firebase
        user = FirebaseDatabase.getInstance().getReference("Final/User");
        //
        userpush = new User();
        //Onclick
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sReris.start();
                userpush.setName(username.getText().toString());
                userpush.setPass(password.getText().toString());
                user.push().setValue(userpush);
                Intent login = new Intent(Regis.this, MainActivity.class);
                startActivity(login);
            }
        });
    }
}
