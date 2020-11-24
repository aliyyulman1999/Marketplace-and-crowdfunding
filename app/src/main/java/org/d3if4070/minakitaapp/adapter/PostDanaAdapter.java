package org.d3if4070.minakitaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.d3if4070.minakitaapp.R;
import org.d3if4070.minakitaapp.activities.PostDetailActivity;
import org.d3if4070.minakitaapp.model.Post;
import org.d3if4070.minakitaapp.model.PostDana;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostDanaAdapter extends RecyclerView.Adapter<PostDanaAdapter.MyViewHolder> {

    Context mContext;
    List<PostDana> mData ;


    public PostDanaAdapter(Context mContext, List<PostDana> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_post_item,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(mData.get(position).getTitleDana());
        holder.tvDana.setText(mData.get(position).getDanaDana());
        Glide.with(mContext).load(mData.get(position).getPictureDana()).into(holder.imgPost);
        Glide.with(mContext).load(mData.get(position).getUserPhotoDana()).into(holder.imgPostProfile);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDana;
        ImageView imgPost;
        ImageView imgPostProfile;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.row_post_title);
            tvDana = itemView.findViewById(R.id.row_post_dana);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_post_profile_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent postDetailActivity = new Intent(mContext, PostDetailActivity.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("titleDana",mData.get(position).getTitleDana());
                    postDetailActivity.putExtra("postImageDana",mData.get(position).getPictureDana());
                    postDetailActivity.putExtra("descriptionDana",mData.get(position).getDescriptionDana());
                    postDetailActivity.putExtra("danaDana",mData.get(position).getDanaDana());
                    postDetailActivity.putExtra("postKeyDana",mData.get(position).getPostKeyDana());
                    postDetailActivity.putExtra("userPhotoDana",mData.get(position).getUserPhotoDana());
                    // will fix this later i forgot to add user name to post object
                    //postDetailActivity.putExtra("userName",mData.get(position).getUsername);
                    long timestamp  = (long) mData.get(position).getTimeStampDana();
                    postDetailActivity.putExtra("postDateDana",timestamp) ;
                    mContext.startActivity(postDetailActivity);


                }
            });

        }


    }
}

