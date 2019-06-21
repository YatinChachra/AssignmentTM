package com.example.android.assignment.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.assignment.Model.Response;
import com.example.android.assignment.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 300 on 6/21/2019.
 */

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.NumberViewHolder> {

    private static final String TAG = ResponseAdapter.class.getSimpleName();

    private int mNumberItems;

    private ArrayList<Response> list;

    private Response ads;
    private Context context;

    public ResponseAdapter(Context context,ArrayList<Response> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.vertical_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        Response ad=list.get(position);

        //holder.ID.setText(ad.getId());
        holder.QUESTION.setText(ad.getQuestion());
        //holder.TICK.setText(ad.getTick());
        //holder.CROSS.setText(ad.getCross());


        Picasso.with(context).load(ad.getImage()).fit().into(holder.IMAGE);


        // Picasso.with(this).load(ad.getPetImage()).into(holder.IMAGE);

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {


        TextView ID,QUESTION,TICK,CROSS;
        ImageView IMAGE;

        public NumberViewHolder(View itemView) {
            super(itemView);


       //     ID = (TextView) itemView.findViewById(R.id.id);
            QUESTION = (TextView) itemView.findViewById(R.id.question);
         //   TICK = (TextView) itemView.findViewById(R.id.tick);
          //  CROSS = (TextView) itemView.findViewById(R.id.cross);

            IMAGE = (ImageView) itemView.findViewById(R.id.displayimage);


        }
    }
}
