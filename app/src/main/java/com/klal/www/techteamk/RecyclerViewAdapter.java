package com.klal.www.techteamk;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private static final String TAG = "RecyclerViewAdapter";
    ArrayList<String> notification_text =new ArrayList<String>();
    private Context context;

    public RecyclerViewAdapter(Context context,ArrayList<String> notification_text) {

        this.notification_text = notification_text;

        this.context = context;

    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        Log.d(TAG,"OnBindViewHolder : called");

        holder.textView.setText(notification_text.get(i));

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"OnClick : Item clicked");

                Toast.makeText(context,notification_text.get(i),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,NotificationDetails.class);

                intent.putExtra("Massage","Sallery For Your Work In December");
                intent.putExtra("Date","11/11/1999");
                intent.putExtra("Notification","Your have recieved your salary of Sum Rs. 15000 ");

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notification_text.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView textView;
        TextView date;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.notification_text);
            date =itemView.findViewById(R.id.date);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
