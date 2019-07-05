package com.example.newsfeed;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsfeed.Models.Article;
import com.example.newsfeed.Models.News;
import com.example.newsfeed.Models.Source;
import com.example.newsfeed.api.ApiClient;
import com.example.newsfeed.api.ApiInterface;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    public static final String API_KEY = "3dd7f060f1ab4df88d13dfb7e5863ea2";
    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;

    RequestQueue mRequestQueue;

    private String TAG = MainActivity.class.getSimpleName();
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private List<livemodel> live = new ArrayList<>();
    RequestQueue mRequestQueue1;
    ViewPagerAdapter viewPagerAdapter;
    private AdView mAdView;
    ImageView imag;



    @SuppressLint( "ResourceAsColor" )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imag=findViewById(R.id.image1);
        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com/search?q=soccer+images&tbm=isch&source=iu&ictx=1&fir=Gs1ggQdmr33TVM%253A%252CVpkwToMN1qMyeM%252C_&vet=1&usg=AI4_-kRv1W6nLyf1Qtagk8LcJdn9cPZ6_g&sa=X&ved=2ahUKEwjj3fWi8_fiAhWLXSsKHdBUCmoQ9QEwAHoECAYQBA#imgrc=Gs1ggQdmr33TVM:";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

        mAdView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);




        onClick1();


    }

    public void onClick1() {
        String username = "";
        String password = "";
        mRequestQueue = Volley.newRequestQueue(this);
          final String url = "https://newsapi.org/v2/everything?q=football&from=2019-06-24&sortBy=publishedAt&apiKey=3dd7f060f1ab4df88d13dfb7e5863ea2";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray arti = response.getJSONArray("articles");
                    for (int i = 0; i <arti.length(); i++) {
                        JSONObject ar = arti.getJSONObject(i);

                         Article article = new Article(ar.getString("author"), ar.getString("title"), ar.getString("description"), ar.getString("url"), ar.getString("urlToImage"), ar.getString("publishedAt"),ar.getString("content"));
                        articles.add(article);
                    }
                    adapter = new Adapter(articles, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
    }
  /*  private void initListener(){
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onitemClick(View view, int position) {
                Intent intent=new Intent(MainActivity.this,Description.class);
                //Source source=articles.get(position);
                Article article=articles.get(position);
                intent.putExtra("url",article.getUrl());
                intent.putExtra("author",article.getAuthor());
                intent.putExtra("description",article.getDescription());
                intent.putExtra("title",article.getTitle());
                intent.putExtra("date",article.getPublishedAt());
                intent.putExtra("image",article.getUrlToImage());
                startActivity(intent);
            }
        });
    }*/


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
           //  onClick();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            // into onPostExecute() but that is upto you
            onClick1();


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {


        }
    }



     }




