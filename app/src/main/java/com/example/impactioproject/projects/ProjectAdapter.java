package com.example.impactioproject.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.impactioproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> implements Filterable {
    private List<Projects> mProject, mProjectsFiltered;
    private RecyclerViewClickListener mListener;
    public static final int SORT_METHOD_NAME = 1;
    public static final int SORT_METHOD_VALUE = 2;

    public ProjectAdapter(List<Projects> projects, RecyclerViewClickListener listener) {
        mProject = projects;
        mProjectsFiltered = projects;
        mListener = listener;
    }

    @NonNull
    @Override
    public ProjectAdapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_menu_adapter, parent, false);
        return new ProjectViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ProjectViewHolder holder, int position) {
        Projects project = mProjectsFiltered.get(position);
        holder.mName.setText(project.getName());
        holder.mDescription.setText(project.getDescription());
        holder.itemView.setTag(project.getSymbol());

    }

    public interface RecyclerViewClickListener {
        public void onClick(View view, String projectSymbol);
    }

    @Override
    public int getItemCount() {
        return mProjectsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mProjectsFiltered = mProject;
                } else {
                    ArrayList<Projects> filteredList = new ArrayList<>();
                    for (Projects projects : mProject) {
                        if (projects.getName().toLowerCase(Locale.ROOT).contains(charString.toLowerCase(Locale.ROOT))) {
                            mProjectsFiltered.add(projects);
                        }
                    }
                    mProjectsFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mProjectsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mProjectsFiltered = (ArrayList<Projects>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImage;
        private TextView mName, mDescription;
        private RecyclerViewClickListener mListener;

        public ProjectViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mImage = view.findViewById(R.id.iv_shop_main);
            mName = view.findViewById(R.id.tv_funding_row_title);
            mDescription = view.findViewById(R.id.tv_funding_row_description);
            mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, (String) view.getTag());
        }
    }

    public void setData(List<Projects> projects) {
        mProject.clear();
        mProject.addAll(projects);
        notifyDataSetChanged();
    }
}
