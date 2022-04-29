package com.example.souq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Users_adaptor extends RecyclerView.Adapter<Users_adaptor.Viewholder> {

    ArrayList<String> users;
    ArrayList<String> types;
    Context context;
    Show_user_info show_user_info;

    public Users_adaptor(ArrayList<String> users, ArrayList<String> types, Context context, Show_user_info show_user_info) {
        this.users = users;
        this.types = types;
        this.context = context;
        this.show_user_info = show_user_info;
    }

    @NonNull
    @Override
    public Users_adaptor.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.users_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Users_adaptor.Viewholder holder, int position) {
        String s =users.get(position);
        holder.name.setText(s);
        String t=types.get(position);
        holder.type.setText(t);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView name;
        TextView type;
        Button button;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.user_name);
            type=itemView.findViewById(R.id.user_type);
            button =itemView.findViewById(R.id.user_info);
            button.setOnClickListener(view -> {
                show_user_info.show_user_info(getAdapterPosition());
            });
        }
    }
}
