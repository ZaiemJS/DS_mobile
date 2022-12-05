package com.example.assurance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList client_id, client_name, client_lastname, client_age;

    Adapter(Activity activity, Context context, ArrayList client_id, ArrayList client_name, ArrayList client_lastname,
                  ArrayList client_age ){
        this.activity = activity;
        this.context = context;
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_lastname = client_lastname;
        this.client_age = client_age;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.client_id_txt.setText(String.valueOf(client_id.get(position)));
        holder.client_name_txt.setText(String.valueOf(client_name.get(position)));
        holder.client_lastname_txt.setText(String.valueOf(client_lastname.get(position)));
        holder.client_age_txt.setText(String.valueOf(client_age.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(client_id.get(position)));
                intent.putExtra("name", String.valueOf(client_name.get(position)));
                intent.putExtra("lastname", String.valueOf(client_lastname.get(position)));
                intent.putExtra("age", String.valueOf(client_age.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return client_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView client_id_txt, client_name_txt, client_lastname_txt, client_age_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            client_id_txt = itemView.findViewById(R.id.client_id_txt);
            client_name_txt = itemView.findViewById(R.id.client_name_txt);
            client_lastname_txt = itemView.findViewById(R.id.client_lastname_txt);
            client_age_txt = itemView.findViewById(R.id.client_age_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
