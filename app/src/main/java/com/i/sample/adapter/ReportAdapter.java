package com.i.sample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i.sample.R;
import com.i.sample.database.models.Results;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Elangovan - 08-10-18
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private List<Results> myResultsAL;

    private Context myContext;

    public ReportAdapter(FragmentActivity aContext, List<Results> aResultsAL) {
        this.myResultsAL = aResultsAL;
        this.myContext = aContext;
    }

    @NonNull
    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_report_adapter, parent, false);

        return new ReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Results aResults = myResultsAL.get(position);
        holder.myTitleTXT.setText(aResults.categoryname + "--" + aResults.noofCorrectAnswer + " / " + aResults.noofquestions);
       /*   holder.myDescriptionTXT.setText(String.format("%s, %s, %s",
                myResultsAL.get(position).name, myResultsAL.get(position).city, myResultsAL.get(position).countryName));*/

    }


    @Override
    public int getItemCount() {
        return myResultsAL.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myTitleTXT, myDescriptionTXT;
        View myRootView;

        MyViewHolder(View view) {
            super(view);
            myRootView = view;
            myTitleTXT = view.findViewById(R.id.txt_title);
            myDescriptionTXT = view.findViewById(R.id.txt_description);

        }


    }

    public void update(ArrayList<Results> aResultsAL) {
        this.myResultsAL = aResultsAL;
        notifyDataSetChanged();
    }


}

