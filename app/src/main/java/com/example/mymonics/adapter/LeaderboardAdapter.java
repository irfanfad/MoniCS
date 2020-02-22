package com.example.mymonics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.model.User;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {

    private List<User> listUser;
    public Context context;

    public LeaderboardAdapter(List<User> listUser, Context context) {
        this.listUser = listUser;
        this.context = context;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_leaderboard, parent, false);
        return new LeaderboardAdapter.LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {

        final User user = listUser.get(position);

        holder.tvNamaLead.setText(user.getNama());
        holder.tvPointLead.setText(user.getTotalPoint().toString());
        holder.tvIndex.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaLead, tvPointLead,tvIndex;
        public CardView cvLeaderboard;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaLead = itemView.findViewById(R.id.tv_nama_lead);
            tvPointLead = itemView.findViewById(R.id.tv_point_lead);
            tvIndex = itemView.findViewById(R.id.tv_index);
            cvLeaderboard = itemView.findViewById(R.id.cv_leaderboard);
        }
    }
}
