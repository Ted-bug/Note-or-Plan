package com.example.noteorplan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {
    RecyclerView mRecycle;
    Context context;
    String[] strings;

    //重写此方法参数，以传入数据源，以及context
    public MainFragment(Context context){
        this.context=context;
        getTitleData(this.context);
    }

    //绑定layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_main_fragment,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecycle=view.findViewById(R.id.recycle);
        mRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycle.setAdapter(new MainFragmentAdapter(context,strings));
    }

    //获取title的数据
    public void getTitleData(Context context){

        DBHelper helper=new DBHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.query("information",new String[]{"title"},null,
                null,null,null,null);

        int i=0;
        String[] string=new String[cursor.getCount()];
        while (cursor.moveToNext()){
            string[i++]=cursor.getString(cursor.getColumnIndex("title"));
        }
        db.close();
        strings=string;
    }
}
