package jobs.vmware.com.remote_trigger.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jobs.vmware.com.remote_trigger.DAO.FolderActivity;
import jobs.vmware.com.remote_trigger.DAO.WorkflowActivity;
import jobs.vmware.com.remote_trigger.R;

/**
 * Created by amedishetti on 23-05-2018.
 */
public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
   private List<FolderInfo> folders;
   private boolean isRefreshing = false;
    Context mcontext;
    public FolderAdapter(List<FolderInfo> myDataset,Context context) {
        folders = myDataset;
        mcontext = context;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView mTextView;
        LinearLayout linearLayout;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textview);
            linearLayout = v.findViewById(R.id.folder_parent_layout);
        }
        public TextView getTextView() {
            return mTextView;
        }


    }

    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.folder_text_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       holder.getTextView().setText(folders.get(position).getName());
       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(mcontext, WorkflowActivity.class);
               intent.putExtra("foldername",folders.get(position).getName());
               mcontext.startActivity(intent);
           }
       });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return folders.size();
    }
/*
    public void refresh() {
        if (isRefreshing) return;
        isRefreshing = true;
        FolderActivity.execute(new Runnable() {
            @Override
            public void run() {
                final List<FolderInfo> notes = DataStore.getNotes().getAll();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        NotesAdapter.this.notes = notes;
                        notifyDataSetChanged();
                        isRefreshing = false;
                    }
                });
            }
        });
    }*/



}


