package com.example.noteorplan;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {

    private Context context;
    private EditText mEtTitle,mEtContent;
    private Button mBtnAdded;
    public AddFragment(Context context){
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_add_fragment,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEtTitle=view.findViewById(R.id.et_title);
        mEtContent=view.findViewById(R.id.et_content);
        mBtnAdded=view.findViewById(R.id.btn_add);

        mBtnAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper=new DBHelper(context);
                SQLiteDatabase db=helper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("title",mEtTitle.getText().toString());
                values.put("content",mEtContent.getText().toString());
                db.insert("information",null,values);
                db.close();

                //返回显示标题数据的页面
                Toast.makeText(context,"信息添加成功，请 刷新查看 或 继续添加",Toast.LENGTH_SHORT).show();
                //将内容清空以便用户继续添加新内容
                mEtTitle.setText("");
                mEtContent.setText("");
            }
        });
    }
}
