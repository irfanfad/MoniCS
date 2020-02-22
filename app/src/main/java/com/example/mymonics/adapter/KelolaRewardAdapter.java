package com.example.mymonics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymonics.R;
import com.example.mymonics.model.Reward;

import java.util.List;

public class KelolaRewardAdapter extends RecyclerView.Adapter<KelolaRewardAdapter.ViewHolder> {

    private List<Reward> listReward;
    private Context context;

    public KelolaRewardAdapter(List<Reward> listReward, Context context) {
        this.listReward = listReward;
        this.context = context;
    }

    @NonNull
    @Override
    public KelolaRewardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_kelola_reward, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KelolaRewardAdapter.ViewHolder holder, int position) {

        Reward reward = listReward.get(position);
        holder.tvPoint.setText(String.valueOf(reward.getPointReward()));
        holder.tvNama.setText(reward.getNamaReward());

    }

    @Override
    public int getItemCount() {
        return listReward.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNama;
        TextView tvPoint;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvPoint = itemView.findViewById(R.id.tv_point);

        }
    }

}