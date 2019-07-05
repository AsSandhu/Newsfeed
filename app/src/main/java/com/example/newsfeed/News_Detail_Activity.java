package com.example.newsfeed;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class News_Detail_Activity extends AppCompatActivity {

   private static final String TAG="News_Detail_Activity";
    String imageurl,imagename,description,title,time,urltoimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__detail_);
        Log.d(TAG,"OnCreate:Started");
//        getIncomingIntent();
    }




    public void getIncomingIntent(){
        Log.d(TAG,"Get Incoming Intent:Checking For Incoming Intent");
        if(getIntent().hasExtra("imgurl") && getIntent().hasExtra("Name") && getIntent().hasExtra("description") && getIntent().hasExtra("title") && getIntent().hasExtra("time") && getIntent().hasExtra("urltoimage")){
            Log.d(TAG,"get Incoming Intent:Found intent extra");

            imageurl=getIntent().getStringExtra("imgurl");
            imagename=getIntent().getStringExtra("Name");
            description=getIntent().getStringExtra("description");
            title=getIntent().getStringExtra("title");
            time=getIntent().getStringExtra("time");
            urltoimage=getIntent().getStringExtra("urltoimage");

        }
    }

    public void setImage(String imageurl,String imagename,String description,String title,String time,String urltoimage){
        Log.d(TAG,"setImage:setting the image and name to widget");


        TextView textView= findViewById(R.id.author);
        textView.setText(imagename);

        TextView textView1=findViewById(R.id.publishedAt);

        TextView textView2=findViewById(R.id.title);
        textView2.setText(title);

        TextView textView3=findViewById(R.id.desc);
        textView3.setText(description);

        TextView textView4=findViewById(R.id.source);
        textView4.setText(urltoimage);





        ImageView imageView = findViewById(R.id.img);

       Glide.with(this)
               .asBitmap()
                .load(urltoimage)
               .into(imageView);

    }
}
