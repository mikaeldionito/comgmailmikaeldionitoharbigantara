package mikaeldionitoharbigantara.gmail.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.os.Handler;

public class HalamanSplash extends AppCompatActivity {
    public ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_splash);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(HalamanSplash.this, MainActivity.class));
                finish();
            }
        },3000L);
    }
}
