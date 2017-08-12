package com.example.sherif.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private static int IMG_RESUlT = 1;
    String imageDecode;
    ImageView imageViewLoad;
    Button looadImage;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewLoad = (ImageView) findViewById(R.id.image);
        looadImage = (Button) findViewById(R.id.btn);

        looadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent,IMG_RESUlT);


            }
        });


    }

//    @Override
//    protected void onActivityResult (int requestCode, int resultCode, Intent data){
//
//        super.onActivityResult(requestCode, resultCode,data);
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    try {

        if (requestCode == IMG_RESUlT && resultCode == RESULT_OK
                && null != data) {


            Uri URI = data.getData();
            String[] FILE = {MediaStore.Images.Media.DATA};


            Cursor cursor = getContentResolver().query(URI,
                    FILE, null, null, null);

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(FILE[0]);
            imageDecode = cursor.getString(columnIndex);
            cursor.close();

            imageViewLoad.setImageBitmap(BitmapFactory
                    .decodeFile(imageDecode));

        }
    } catch (Exception e) {
        Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                .show();
    }
}


}
