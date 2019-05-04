package com.example.recyclerview.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.recyclerview.CustomAdapter.UserHistoryCustomAdapter;
import com.example.recyclerview.R;
import com.example.recyclerview.UserHistory;

import java.util.ArrayList;
import java.util.List;

public class HistoryPageFragment extends Fragment {
    private ListView lv_user_history;
    View view;
    private List<UserHistory> userHistories;
    private UserHistoryCustomAdapter userHistoryCustomAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.history_page_fragment, parent, false);
        bind_id();
        return view ;
    }

    private void bind_id(){
        lv_user_history = view.findViewById(R.id.lv_user_history);
        userHistories = new ArrayList<>();
        userHistories.add(new UserHistory("1","A","1000","B","10/10/10"));
        userHistories.add(new UserHistory("1","A","1000","B","10/10/10"));
        userHistories.add(new UserHistory("1","A","1000","B","10/10/10"));
        userHistories.add(new UserHistory("1","A","1000","B","10/10/10"));
        userHistories.add(new UserHistory("1","A","1000","B","10/10/10"));
        userHistories.add(new UserHistory("1","A","1000","B","10/10/10"));
        userHistories.add(new UserHistory("1","A","1000","B","10/10/10"));

        userHistoryCustomAdapter = new UserHistoryCustomAdapter(getActivity(),1,userHistories);
        lv_user_history.setAdapter(userHistoryCustomAdapter);
    }

}