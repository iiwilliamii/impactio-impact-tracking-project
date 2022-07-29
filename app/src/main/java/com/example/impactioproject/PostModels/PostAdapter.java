package com.example.impactioproject.PostModels;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.impactioproject.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

//    Context mContext;
    List<Posts> mData;
    RecyclerViewListener mListener;

    public PostAdapter(RecyclerViewListener listener, List<Posts> mData) {
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

        String stringDate = mData.get(position).getTimeStamp().toString();
        Long Date = Long.parseLong(stringDate);

        holder.mTitle.setText(mData.get(position).getTitle());
        holder.mDescription.setText(mData.get(position).getDescription());
        holder.mTime.setText(getDate(Date).toString());

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

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
}
