package mikaeldionitoharbigantara.gmail.com;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private CardView inputanMik, mapsMik, compassMik, cameraMik, jsonMik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputanMik = (CardView) findViewById(R.id.inputan_mik);
        mapsMik = (CardView) findViewById(R.id.maps_mik);
        compassMik = (CardView) findViewById(R.id.compass_mik);
        cameraMik = (CardView) findViewById(R.id.camera_mik);
        jsonMik = (CardView) findViewById(R.id.json_mik);

        inputanMik.setOnClickListener(this);
        mapsMik.setOnClickListener(this);
        compassMik.setOnClickListener(this);
        cameraMik.setOnClickListener(this);
        jsonMik.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mik ;
        switch (v.getId()){
            case R.id.inputan_mik: mik = new Intent(this,HalamanInputan.class); startActivity(mik); break;
            case R.id.maps_mik : mik = new Intent(this,HalamanMaps.class); startActivity(mik); break;
            case R.id.compass_mik : mik = new Intent(this,HalamanCompass.class); startActivity(mik); break;
            case R.id.camera_mik : mik = new Intent(this, HalamanCamera.class); startActivity(mik); break;
            case R.id.json_mik : mik = new Intent(this, HalamanJson.class); startActivity(mik); break;
            default: break;
        }

    }
}
