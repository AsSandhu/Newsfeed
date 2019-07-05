package com.example.newsfeed;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Visibility;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.newsfeed.Models.Article;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.app.Application;

import retrofit2.http.GET;

import static com.android.volley.VolleyLog.TAG;
import static com.example.newsfeed.Utils.getCountry;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    private List<url> item;
    private List<Article> articles;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public Adapter(List<url> list, List<Article> articles, Context context, OnItemClickListener onItemClickListener) {
        this.item = list;
        this.articles = articles;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, final int position) {
       String url1="https://backendlessappcontent.com/34B2442F-B27C-1850-FF10-7F34BBA40000/26345FD4-A509-74C3-FF97-592B3F439200/files/image/back.jpg";
        String url2="https://backendlessappcontent.com/34B2442F-B27C-1850-FF10-7F34BBA40000/26345FD4-A509-74C3-FF97-592B3F439200/files/image/images.jpg";
       String url3="https://backendlessappcontent.com/34B2442F-B27C-1850-FF10-7F34BBA40000/26345FD4-A509-74C3-FF97-592B3F439200/files/image/preminds.png";
       String url4="https://backendlessappcontent.com/34B2442F-B27C-1850-FF10-7F34BBA40000/26345FD4-A509-74C3-FF97-592B3F439200/files/image/vivek_sir.jpg";
        final MyViewHolder holder=holders;
        final Article model=articles.get(position);

       articles.get(2).setUrlToImage(url1);
        articles.get(5).setUrlToImage(url2);
      articles.get(8).setUrlToImage(url3);
        articles.get(11).setUrlToImage(url4);




        RequestOptions requestOptions= new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        requestOptions.centerCrop();



        Glide.with(context)
        .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
       // try {
            //holder.time.setText(model.getPublishedAt());
        //} catch (Exception e) {
        //    e.printStackTrace();
       // }
       // holder.published_ad.setText(Utils.DateFormat(model.getPublishedAt()));
       // holder.author.setText(model.getAuthor());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                     final String url1 = "https://www.amazon.in/?ie=UTF8&tag=googinabkvernac-21&ascsubtag=_k_CjwKCAjwr8zoBRA0EiwANmvpYGcsWnJZw8-_Dnn70zp3Tf0gviJVAWIOveTERX2P4wQAPw49PQznQhoCiAcQAvD_BwE_k_&ext_vrnc=hi&gclid=CjwKCAjwr8zoBRA0EiwANmvpYGcsWnJZw8-_Dnn70zp3Tf0gviJVAWIOveTERX2P4wQAPw49PQznQhoCiAcQAvD_BwE";

                     Log.d(TAG, "onclicked: clickedOn:" + articles.get(position));
                     Intent intent1 = new Intent(Intent.ACTION_VIEW);
                     intent1.setData(Uri.parse(url1));
                     context.startActivity(intent1);



                     Log.d(TAG, "onclicked: clickedOn:" + articles.get(position));
                    //   Intent intent = new Intent(context, Description.class);
                     Article article = articles.get(position);

                   /*  intent.putExtra("url", article.getUrl());
                     intent.putExtra("author", article.getAuthor());
                     intent.putExtra("description", article.getDescription());
                     intent.putExtra("title", article.getTitle());
                     intent.putExtra("date", article.getPublishedAt());
                     intent.putExtra("image", article.getUrlToImage());
                     context.startActivity(intent);*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onitemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, desc, author, published_ad, source, time;
       final ImageView imageView;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);

            this.onItemClickListener = onItemClickListener;
            imageView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            


        }
    }
    public int datetime(String date) throws ParseException {
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH);
        Date date1 = sdf.parse(date);
        long time= date1.getTime();
        long timed=cal.getTimeInMillis();
        //isTime = p.format(date);
        long difference = Math.abs(cal.getTimeInMillis()-date1.getTime());
        int hours = (int) (difference/(1000 * 60 * 60));
        int isTime=hours;
        return isTime;
    }


    public static String DateFormat(String oldstringDate){
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }

}
