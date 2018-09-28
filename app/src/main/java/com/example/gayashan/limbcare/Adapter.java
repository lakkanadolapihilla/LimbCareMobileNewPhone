package com.example.gayashan.limbcare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<TeamCard> cardItems;
    private Context context;

    public Adapter(List<TeamCard> cardItems, Context context) {
        this.cardItems = cardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teamcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TeamCard teamCard = cardItems.get(position);
        holder.id.setText(teamCard.getId());
        holder.fname.setText(teamCard.getFname());
        holder.lname.setText(teamCard.getLname());
        holder.nic.setText(teamCard.getNic());
        holder.job.setText(teamCard.getJob());
        holder.email.setText(teamCard.getEmail());
        holder.birthday.setText(teamCard.getBirthday());
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView fname;
        public TextView lname;
        public TextView nic;
        public TextView job;
        public TextView email;
        public TextView birthday;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            nic = itemView.findViewById(R.id.nic);
            job = itemView.findViewById(R.id.job);
            email = itemView.findViewById(R.id.email);
            birthday = itemView.findViewById(R.id.birthday);


        }
    }


}
