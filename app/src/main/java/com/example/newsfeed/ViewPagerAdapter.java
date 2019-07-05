package com.example.newsfeed;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    List<livemodel> articles;
    public ViewPagerAdapter(Context context, List<livemodel> articles) {
        this.context = context;
        this.articles=articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        TextView league=(TextView)view.findViewById(R.id.leag);
        TextView home=(TextView)view.findViewById(R.id.hometeam);
        TextView homescore=(TextView)view.findViewById(R.id.homescore);
        TextView awayscore=(TextView)view.findViewById(R.id.awayscore);
        TextView away=(TextView)view.findViewById(R.id.awayteam);
        TextView time=(TextView)view.findViewById(R.id.time11);
        TextView status=(TextView)view.findViewById(R.id.status);
        livemodel livemodel=articles.get(position);
        String score =livemodel.getScore();
        String[] scores=new String[2];
        scores=score.split("-");
        league.setText(livemodel.getLeaguename());
        home.setText(livemodel.getHome());
        homescore.setText(scores[0]);
        awayscore.setText(scores[1]);
        away.setText(livemodel.getAway());
        time.setText(livemodel.getTime());
        status.setText(livemodel.getStatus());
        //

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}

