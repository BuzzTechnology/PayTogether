package com.buzztechnology.paytogether.paytogether.choisePal;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bejibx.android.recyclerview.selection.SelectionHelper;
import com.bejibx.android.recyclerview.selection.SelectionObserver;
import com.buzztechnology.paytogether.paytogether.R;

import java.util.ArrayList;
import java.util.List;

public class ChoisePalActivity extends AppCompatActivity implements AddPalDialogFragment.AddPalListener {

    private final ActionModeCallback mActionModeCallback = new ActionModeCallback();
    private RecyclerView mRecyclerView;
    private ChoisePalAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DialogFragment addPalDialog;

    private List<PalModel> pals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise_pal);

        mRecyclerView = findViewById(R.id.choise_pal_list);
        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.attachToRecyclerView(mRecyclerView);

        addPalDialog = new AddPalDialogFragment();

        //for performance
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        pals = getAllPals();

        mAdapter = new ChoisePalAdapter(this, pals);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onClickPlus(View view){
        addPalDialog.show(getSupportFragmentManager(), "pisos");
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText input = dialog.getDialog().findViewById(R.id.input_name_pal);
        mAdapter.addItems(new PalModel(input.getText().toString(), true));

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public void startActionMode() {
        startActionMode(mActionModeCallback);
    }

    private List<PalModel> getAllPals() {
        // TODO initial DB
        List<PalModel> pals = new ArrayList<>();
        pals.add(new PalModel("Лиза", false));
        pals.add(new PalModel("Коля", true));
        pals.add(new PalModel("Женя", false));
        pals.add(new PalModel("Леха ебать", true));
        pals.add(new PalModel("первый нах", true));
        pals.add(new PalModel("второй нах", true));
        pals.add(new PalModel("третий нах", true));
        pals.add(new PalModel("четвертый нах", true));
        pals.add(new PalModel("пятый нах", true));
        pals.add(new PalModel("шестой нах", true));
        pals.add(new PalModel("седьмой нах", true));
        pals.add(new PalModel("восьмой нах", true));
        pals.add(new PalModel("девятый нах", true));
        pals.add(new PalModel("десятый нах", true));
        return pals;
    }

    private class ActionModeCallback implements ActionMode.Callback, SelectionObserver {
        private ActionMode mActionMode;

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            SelectionHelper selectionHelper = mAdapter.getSelectionHelper();
            selectionHelper.unregisterSelectionObserver(this);
            mActionMode = null;
            selectionHelper.setSelectable(false);
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            mActionMode = actionMode;
            mActionMode.getMenuInflater().inflate(R.menu.gallery_selection, menu);
            mAdapter.getSelectionHelper().registerSelectionObserver(this);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_toast:
                    Toast.makeText(ChoisePalActivity.this,
                            R.string.text_simple_toast, Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder holder, boolean isSelected) {
            if (mActionMode != null) {
                int checkedImagesCount = mAdapter.getSelectionHelper().getSelectedItemsCount();
                mActionMode.setTitle(String.valueOf(checkedImagesCount));
            }
        }

        @Override
        public void onSelectableChanged(boolean isSelectable) {
            if (!isSelectable) {
                mActionMode.finish();
            }
        }
    }
}
