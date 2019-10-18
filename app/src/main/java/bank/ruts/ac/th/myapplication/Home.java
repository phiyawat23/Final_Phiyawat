package bank.ruts.ac.th.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Home extends AppCompatActivity {
    TextView txtname;
    Button red,blue,sensor;
    MediaPlayer sRed,sBlue,sSensor;
    public FirebaseDatabase database1,database2,database3;
    public DatabaseReference led_red,led_blue,getsensor;
    public Integer value,value_refer,value2,value_refer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Button
        red = (Button)findViewById(R.id.btnRed);
        blue = (Button)findViewById(R.id.btnBlue);
        sensor = (Button)findViewById(R.id.btnSensor);
        //TextView
        txtname = (TextView)findViewById(R.id.txtuname);
        //Sound
        sRed = MediaPlayer.create(Home.this, R.raw.effect_btn_shut);
        sBlue = MediaPlayer.create(Home.this, R.raw.effect_btn_shut);
        sSensor = MediaPlayer.create(Home.this, R.raw.effect_btn_shut);
        //Txtname
        txtname.setText(getIntent().getStringExtra("name"));
        //LED RED
        database1 = FirebaseDatabase.getInstance();
        led_red = database1.getReference("Final/LED/red");
        led_red.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(Integer.class);
                if(value == 1){
                    red.setText("RED ON");
                    value_refer = 0;
                }
                else {
                    red.setText("RED OFF");
                    value_refer = 1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                led_red.setValue(value_refer);
                sRed.start();
            }
        });

        //LED_BLUE
        database2 = FirebaseDatabase.getInstance();
        led_blue = database2.getReference("Final/LED/blue");
        led_blue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                value2 = dataSnapshot2.getValue(Integer.class);
                if(value2 == 1){
                    blue.setText("BLUE ON");
                    value_refer2 = 0;
                }
                else {
                    blue.setText("BLUE OFF");
                    value_refer2 = 1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError2) {

            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                led_blue.setValue(value_refer2);
                sBlue.start();
            }
        });

        //Sensor
        database3 = FirebaseDatabase.getInstance();
        getsensor = database3.getReference("Final/Sensor");
        getsensor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
                Map map = (Map)dataSnapshot3.getValue();
                String data = String.valueOf(map.get("lightSensor"));
                sensor.setText(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError3) {

            }
        });
        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sSensor.start();
            }
        });
    }
}
