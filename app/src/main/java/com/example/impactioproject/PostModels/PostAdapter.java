package com.example.impactioproject.PostModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.impactioproject.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

//    Context mContext;
    List<Post> mData;
    RecyclerViewListener mListener;

    public PostAdapter(RecyclerViewListener listener, List<Post> mData) {
//        this.mContext = mContext;
        this.mData = mData;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post_adapter, parent, false);
        return new PostAdapter.PostViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        holder.mTitle.setText(mData.get(position).getTitle());
        holder.mDescription.setText(mData.get(position).getDescription());
        holder.mTime.setText(mData.get(position).getTimeStamp().toString());


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mDescription, mTime;

        public PostViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_post_title);
            mTime = itemView.findViewById(R.id.tv_post_time);
            mDescription = itemView.findViewById(R.id.tv_post_description);
        }
    }

    public interface RecyclerViewListener {
        void onClick(View view, String Symbol);
    }
}
