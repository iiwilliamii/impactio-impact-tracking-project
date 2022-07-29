package com.example.impactioproject.Funding;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.impactioproject.PostModels.PostAdapter;
import com.example.impactioproject.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FundingAdapter extends RecyclerView.Adapter<FundingAdapter.FundingViewHolder> {

    List<Fundings> mData;
    RecyclerViewListener mListener;

    public FundingAdapter(RecyclerViewListener listener, List<Fundings> mData) {
        this.mData = mData;
        this.mListener = listener;
    }

    public class FundingViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mDescription, mAmount, mTime;

        public FundingViewHolder (View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_funding_row_title);
            mDescription = itemView.findViewById(R.id.tv_funding_row_description);
            mAmount = itemView.findViewById(R.id.tv_funding_row_amount);
            mTime = itemView.findViewById(R.id.tv_funding_row_time);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull FundingAdapter.FundingViewHolder holder, int position) {

        String stringDate = mData.get(position).getTimeStamp().toString();
        Long Date = Long.parseLong(stringDate);

        holder.mTitle.setText(mData.get(position).getTitle());
        holder.mDescription.setText(mData.get(position).getDescription());
        holder.mAmount.setText(mData.get(position).getDescription());
        holder.mTime.setText(getDate(Date).toString());
    }

    @NonNull
    @Override
    public FundingAdapter.FundingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_funding_adapter, parent, false);
        return new FundingAdapter.FundingViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface RecyclerViewListener {
        void onClick(View view, String Symbol);
    }

    private Object getDate(Long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

}
