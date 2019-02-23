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

import java.util.List;


/**
 * Created by Elangovan - 08-10-18
 */
public class AdminReportAdapter extends RecyclerView.Adapter<AdminReportAdapter.MyViewHolder> {

    private List<Results> myResultsAL;

    private Context myContext;

    public AdminReportAdapter(FragmentActivity aContext, List<Results> aResultsAL) {
        this.myResultsAL = aResultsAL;
        this.myContext = aContext;
    }

    @NonNull
    @Override
    public AdminReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_report_admin_adapter, parent, false);

        return new AdminReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Results aResults = myResultsAL.get(position);
        holder.myUsername.setText("User name : " + aResults.userName);
        holder.myCategoryTXT.setText("Category name : " + aResults.categoryname);
        holder.myDescriptionTXT.setText("Scored : " + aResults.noofCorrectAnswer + " / " + aResults.noofquestions);
       /*   holder.myDescriptionTXT.setText(String.format("%s, %s, %s",
                myResultsAL.get(position).name, myResultsAL.get(position).city, myResultsAL.get(position).countryName));*/

    }


    @Override
    public int getItemCount() {
        return myResultsAL.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myUsername, myCategoryTXT, myDescriptionTXT;
        View myRootView;

        MyViewHolder(View view) {
            super(view);
            myRootView = view;
            myCategoryTXT = view.findViewById(R.id.name);
            myDescriptionTXT = view.findViewById(R.id.txt_description);
            myUsername = view.findViewById(R.id.username);

        }


    }

    public void update(List<Results> aResultsAL) {
        this.myResultsAL = aResultsAL;
        notifyDataSetChanged();
    }


}

