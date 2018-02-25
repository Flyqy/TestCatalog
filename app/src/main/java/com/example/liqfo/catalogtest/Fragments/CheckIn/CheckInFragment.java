package com.example.liqfo.catalogtest.Fragments.CheckIn;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.liqfo.catalogtest.BaseFragment;
import com.example.liqfo.catalogtest.CheckListener;
import com.example.liqfo.catalogtest.R;

public class CheckInFragment extends BaseFragment {

    private EditText et;
    private TextInputLayout til;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setTitle(R.string.auth);
        setSubtitle("");
    }

    private CheckListener checkListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            checkListener = (CheckListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.check_in_fragment, container, false);

        til = view.findViewById(R.id.text_input_layout);
        et = til.findViewById(R.id.user_name);
        til.setHint(getString(R.string.hint_check));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.check_in_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_confirm:
                checkListener.onRegClick(et.getText().toString());
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
