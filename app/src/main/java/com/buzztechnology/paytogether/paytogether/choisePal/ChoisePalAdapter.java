package com.buzztechnology.paytogether.paytogether.choisePal;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;

import com.bejibx.android.recyclerview.selection.HolderClickObserver;
import com.bejibx.android.recyclerview.selection.SelectionHelper;
import com.bejibx.android.recyclerview.selection.SelectionObserver;
import com.buzztechnology.paytogether.paytogether.R;

import java.util.List;

public class ChoisePalAdapter extends RecyclerView.Adapter<ChoisePalAdapter.ViewHolder>
        implements HolderClickObserver, SelectionObserver {
    private ChoisePalActivity activity;
    private List<PalModel> pals;
    private SelectionHelper mSelectionHelper;

    public ChoisePalAdapter(ChoisePalActivity activity, List<PalModel> pals) {
        this.activity = activity;
        this.pals = pals;
        mSelectionHelper = new SelectionHelper();
        mSelectionHelper.registerSelectionObserver(this);
    }

    @Override
    public ChoisePalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pal_view, parent, false);
        return mSelectionHelper.wrapSelectable(new ViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PalModel pal = pals.get(position);
        holder.nameTextView.setText(pal.getName());
        holder.isPalView.setChecked(pal.isPal());

        Checkable view = (Checkable) holder.itemView;
        view.setChecked(mSelectionHelper.isItemSelected(position));
        mSelectionHelper.bindHolder(holder, position);
    }

    public SelectionHelper getSelectionHelper() {
        return mSelectionHelper;
    }

    @Override
    public int getItemCount() {
        return pals.size();
    }

    public void addItems(PalModel pal) {
        this.pals.add(pal);
        notifyDataSetChanged();
    }

    public void clearItems() {
        pals.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onHolderClick(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public boolean onHolderLongClick(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder holder, boolean isSelected) {
        ((Checkable) holder.itemView).setChecked(isSelected);
    }

    @Override
    public void onSelectableChanged(boolean isSelectable) {
        if (isSelectable) {
            activity.startActionMode();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private CheckBox isPalView;
        private TextView nameTextView;

        public ViewHolder(View v) {
            super(v);
            isPalView = v.findViewById(R.id.is_pal_list_item);
            nameTextView = v.findViewById(R.id.name_list_item_pal);
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.setBackgroundColor(Color.RED);
                    Log.i("!!!!!!!!!!!!!", "onLongClick: " + nameTextView.getText());
                    return false;
                }
            });
        }
    }
}
