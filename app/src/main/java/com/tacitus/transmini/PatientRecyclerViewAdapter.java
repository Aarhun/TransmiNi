package com.tacitus.transmini;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tacitus.transmini.helper.ItemTouchHelperAdapter;
import com.tacitus.transmini.helper.ItemTouchHelperViewHolder;

import java.util.ArrayList;

public class PatientRecyclerViewAdapter extends RecyclerView
        .Adapter<PatientRecyclerViewAdapter
        .PatientDataHolder> implements ItemTouchHelperAdapter
{
    private static String LOG_TAG = "PatientRecyclerViewAdapter";
    private ArrayList<PatientData> mDataset;

    public static class PatientDataHolder extends RecyclerView.ViewHolder
            implements ItemTouchHelperViewHolder {
        TextView label;
        TextView dateTime;

        public PatientDataHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.textView);
            dateTime = itemView.findViewById(R.id.textView2);
        }


        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);

        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);

        }
    }

    public PatientRecyclerViewAdapter(ArrayList<PatientData> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public PatientDataHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_row, parent, false);

        PatientDataHolder dataObjectHolder = new PatientDataHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(PatientDataHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getmText1());
        holder.dateTime.setText(mDataset.get(position).getmText2());
    }

    public void addItem(PatientData dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        PatientData patient = mDataset.remove(fromPosition);
        mDataset.add(toPosition > fromPosition ? toPosition - 1 : toPosition, patient);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
