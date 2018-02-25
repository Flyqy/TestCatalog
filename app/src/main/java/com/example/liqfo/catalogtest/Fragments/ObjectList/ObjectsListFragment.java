package com.example.liqfo.catalogtest.Fragments.ObjectList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.liqfo.catalogtest.AddNew.AddNewActivity;
import com.example.liqfo.catalogtest.BaseFragment;
import com.example.liqfo.catalogtest.CheckListener;
import com.example.liqfo.catalogtest.DataObjects;
import com.example.liqfo.catalogtest.Detalization.DetalizationActivity;
import com.example.liqfo.catalogtest.R;

import java.util.List;

public class ObjectsListFragment extends BaseFragment implements ObjectsView {

    private CheckListener checkListener;
    private List<DataObjects> dataObjects;

    private ObjectsPresenter objectsPresenter = new ObjectsPresenter();

    private RecyclerView rv;
    private ObjectAdapter objectAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        objectsPresenter.setContext(getContext().getApplicationContext());
        initSubtitle();
        addNewItems();
        objectsPresenter.getObjectList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initSubtitle() {
        objectsPresenter.getUserName();
    }

    private void addNewItems() {
        objectsPresenter.addNewItems();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.objects_list_fragment, container, false);

        rv = view.findViewById(R.id.recycler_list_of_objects);
        rv.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        objectAdapter = new ObjectAdapter();
        rv.setAdapter(objectAdapter);

        rv.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                objectsPresenter.clickOnItem(dataObjects.get(position).getId());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.objects_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_logout:
                onLogoutClick();
            case R.id.menu_item_add:
                onAddClick();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        objectsPresenter.attachView(this);
        try {
            checkListener = (CheckListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentListener");
        }
    }

    @Override
    public void showSubtitle(String username) {
        setTitle(username);
    }

    @Override
    public void showDialogExit() {
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext())
                .setMessage(R.string.logout)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        objectsPresenter.positiveLogout();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setCancelable(true);
        ad.show();
    }

    @Override
    public void logout() {
        checkListener.onLogout();
    }

    @Override
    public void showDataItems(List<DataObjects> dataObjects) {
        this.dataObjects = dataObjects;
        objectAdapter.setDataObjects(dataObjects);
        objectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDetail(String id) {
        Intent intent = new Intent(getContext(), DetalizationActivity.class);
        intent.putExtra(DetalizationActivity.PAR_ID, id);
        startActivity(intent);
    }

    @Override
    public void showAddNew() {
        Intent intent = new Intent(getContext(), AddNewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        objectsPresenter.getObjectList();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        objectsPresenter.onDestroy();
    }

    public void onLogoutClick() {
        objectsPresenter.onLogoutClick();
    }

    public void onAddClick() {
        objectsPresenter.onAddClick();
    }
}
