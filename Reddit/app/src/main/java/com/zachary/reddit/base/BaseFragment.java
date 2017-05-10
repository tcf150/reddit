package com.zachary.reddit.base;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by user on 10/5/2017.
 */

public class BaseFragment extends Fragment {
    protected void hideKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
