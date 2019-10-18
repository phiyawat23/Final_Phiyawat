package bank.ruts.ac.th.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer bg,sLogin,sRegis1;
    Button Login,Regis;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Edittext
        username = (EditText)findViewById(R.id.uname);
        // Sound BG
        bg = MediaPlayer.create(MainActivity.this, R.raw.soundbg);
        sLogin = MediaPlayer.create(MainActivity.this, R.raw.effect_btn_shut);
        sRegis1 = MediaPlayer.create(MainActivity.this, R.raw.effect_btn_shut);
        bg.start();
        //Button
        Login = (Button)findViewById(R.id.btnLogin);
        Regis = (Button)findViewById(R.id.btnRegis);
        //LoginOnclick
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sLogin.start();
                Intent home = new Intent(MainActivity.this, Home.class);
                home.putExtra("name",username.getText().toString());
                startActivity(home);
            }
        });
        //RegisOnclick
        Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sRegis1.start();
                Intent regis = new Intent(MainActivity.this, Regis.class);
                startActivity(regis);
                bg.stop();
            }
        });
    }
}
