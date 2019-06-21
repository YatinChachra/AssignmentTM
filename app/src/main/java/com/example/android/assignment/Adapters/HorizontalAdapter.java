package com.example.android.assignment.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.assignment.Model.Response;
import com.example.android.assignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 300 on 6/21/2019.
 */

public class HorizontalAdapter extends PagerAdapter {

    private List<Response> model;
    private LayoutInflater layoutInflater;
    private Context context;

    public HorizontalAdapter(List<Response> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.horizontal_list,container,false);

        ImageView imageView=(ImageView)view.findViewById(R.id.image);
        TextView textView=(TextView)view.findViewById(R.id.title);

        Picasso.with(context).load(model.get(position).getImage()).fit().into(imageView);

        textView.setText(/*model.get(position).getId()+"\n"+*/model.get(position).getQuestion()/*+"\n"+model.get(position).getTick()+"\n"+
                model.get(position).getCross()*/);

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);
    }
}
