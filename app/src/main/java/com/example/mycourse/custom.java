package com.example.mycourse;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class custom extends RecyclerView.Adapter<custom.MyViewHolder> {

    List<course> mylist;
    Context context;

    public  custom(List<course> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final course course=mylist.get(position);
        holder.t1.setText(course.getTitle());
        holder.t2.setText(course.getDescription());
        holder.t3.setText(course.getDuration());
        holder.t4.setText(course.getVenue());
        holder.t5.setText(course.getDate());

    }

    @Override
    public int getItemCount() {

        return mylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4,t5;

        public MyViewHolder(View itemView){
            super(itemView);
            t1=itemView.findViewById(R.id.tv1);
            t2=itemView.findViewById(R.id.tv2);
            t3=itemView.findViewById(R.id.tv3);
            t4=itemView.findViewById(R.id.tv4);
            t5=itemView.findViewById(R.id.tv5);
        }

    }
}
