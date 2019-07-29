package mikaeldionitoharbigantara.gmail.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class HalamanCamera extends AppCompatActivity {
    Button btnCamera;
    ImageView imageView;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_camera);

        cekTulis();
        cekBaca();
        btnCamera = findViewById(R.id.btnCamera);
        imageView = findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            imageBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(imageBitmap);
            SimpanGambar(imageBitmap);
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        }
    }private void SimpanGambar(Bitmap finalBitmap) {
        File mediaStorageDir= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        Toast.makeText(this, mediaStorageDir.toString(), Toast.LENGTH_LONG).show();

        Random generator = new Random();
        int m = 10000;
        m = generator.nextInt(m);
        File fileMik = new File (mediaStorageDir,  "IMG_"+ m +".jpg");
        if (fileMik.exists ())
            fileMik.delete ();

        try {
            FileOutputStream out = new FileOutputStream(fileMik);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cekTulis() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(this,"CAMERA READY!",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"FAILED!",Toast.LENGTH_LONG).show();
    }
    public void cekBaca() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Toast.makeText(this,"OK!",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"FAILURE!",Toast.LENGTH_LONG).show();

        // TODO ALERT!!!!! : Terimakasih Tidak Copy langsung RUN - Mikael Dionito.H!!!
    }
}
