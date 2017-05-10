package com.zachary.reddit.ui.AddTopic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zachary.reddit.R;
import com.zachary.reddit.base.BaseFragment;

/**
 * Created by user on 10/5/2017.
 */

public class AddTopicFragment extends BaseFragment implements AddTopicContract.View {
    private AddTopicContract.Presenter mPresenter;

    private EditText etTopic;
    private TextView tvWordCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPresenter(AddTopicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_topic,container,false);

        etTopic = getViewByView(rootView,R.id.etTopic);
        tvWordCount = getViewByView(rootView,R.id.tvWordCount);

        etTopic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.onTextChanged(s.toString());
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setWordCount(int count) {
        tvWordCount.setText(String.format(getString(R.string.word_count_format),count));
    }

    @Override
    public void showEmptyTextToast() {
        Toast.makeText(getContext(), R.string.error_topic_empty, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addTopicSuccessful(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void showErrorToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
