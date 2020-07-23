package com.example.noteorplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HelpFragment extends Fragment {
    TextView mTvView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_help,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvView=view.findViewById(R.id.tv_help);
        String help="每次添加后，需要刷新；查看具体信息内容后，如有进行修改操作，回退后，需要刷新才能查看最新的样子；需要点击“修改”才能进行内容的修改和删除操作";
        mTvView.setText(help);
    }
}
