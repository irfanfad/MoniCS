package com.example.mymonics.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymonics.DetailRewardActivity;
import com.example.mymonics.R;
import com.example.mymonics.model.Reward;

import java.util.List;

import static com.example.mymonics.DetailLaporanActivity.url;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.RewardViewHolder> {

    private List<Reward> listReward;
    public Context context;

    public RewardAdapter(List<Reward> listReward, Context context) {
        this.listReward = listReward;
        this.context = context;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_reward, parent, false);
        return new RewardAdapter.RewardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {

        final Reward reward = listReward.get(position);

        holder.tvNamaReward.setText(reward.getNamaReward());
        Glide.with(context).load(url + reward.getGambarReward()).into(holder.imgReward);

        holder.cvReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailRewardActivity.class);
                intent.putExtra("detailReward", reward);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listReward.size();
    }

    public class RewardViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaReward;
        public ImageView imgReward;
        public CardView cvReward;

        public RewardViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaReward = itemView.findViewById(R.id.tv_nama_reward);
            imgReward = itemView.findViewById(R.id.img_reward);
            cvReward = itemView.findViewById(R.id.cv_reward);



        }
    }
}
