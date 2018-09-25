package jobs.vmware.com.remote_trigger.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import jobs.vmware.com.remote_trigger.DAO.InstanceActivity;
import jobs.vmware.com.remote_trigger.DAO.WorkflowActivity;
import jobs.vmware.com.remote_trigger.R;

/**
 * Created by amedishetti on 28-05-2018.
 */

public class WorkflowAdapter extends RecyclerView.Adapter<WorkflowAdapter.ViewHolder> {

    private List<WorkflowInfo> workflows;
    Context mcontext;
    String folder_name;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView workflow_textview;
        LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            workflow_textview = (TextView) v.findViewById(R.id.workflow_textview);
            linearLayout = v.findViewById(R.id.workflow_linear_layout);
        }
        public TextView getTextView() {
            return workflow_textview;
        }
    }

    public WorkflowAdapter(List<WorkflowInfo> myDataset, Context context,String folder_name) {
        workflows = myDataset;
        mcontext = context;
        this.folder_name = folder_name;
    }


    @Override
    public WorkflowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workflow_text_layout, parent, false);

        WorkflowAdapter.ViewHolder vh = new WorkflowAdapter.ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(WorkflowAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.getTextView().setText(workflows.get(position).getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, InstanceActivity.class);
                intent.putExtra("workflown_name",workflows.get(position).getName());
                //intent.putExtra("isValid",workflows.get(position).getIsValid());
                intent.putExtra("folder_name",folder_name);
                mcontext.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return workflows.size();
    }


}
