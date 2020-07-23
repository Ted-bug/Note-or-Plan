package com.example.noteorplan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener{
    Button mBtnChange,mBtnChanged,mBtnDelete;
    EditText mEtTitle,mEtContent;
    private String key;
    private DBHelper helper;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        helper=new DBHelper(MessageActivity.this);

        findView();
        setListener();
        setMessages(getIntent());

        mEtTitle.setEnabled(false);
        mEtContent.setEnabled(false);
        mBtnChanged.setBackground(getResources().getDrawable(R.color.colorDark));
        mBtnChanged.setEnabled(false);
        mBtnDelete.setBackground(getResources().getDrawable(R.color.colorDark));
        mBtnDelete.setEnabled(false);
    }

    //绑定组件
    public void findView(){
        mBtnChange=findViewById(R.id.btn_change);
        mBtnChanged=findViewById(R.id.btn_changed);
        mBtnDelete=findViewById(R.id.btn_delete);
        mEtContent=findViewById(R.id.et_content);
        mEtTitle=findViewById(R.id.et_title);

    }

    //为按钮添加监听器
    public void setListener(){
        mBtnChange.setOnClickListener(this);
        mBtnChanged.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    //设置信息内容
    public void setMessages(Intent intent){
        String title,content;
        title=intent.getStringExtra("title");
        content=intent.getStringExtra("content");
        mEtTitle.setText(title);
        mEtContent.setText(content);
    }

    //设置按钮监听器
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        SQLiteDatabase db=helper.getWritableDatabase();
        switch (v.getId()){
            case R.id.btn_change:

                mEtTitle.setEnabled(true);
                mEtContent.setEnabled(true);
                mBtnDelete.setBackground(getResources().getDrawable(R.drawable.seleted_button));
                mBtnDelete.setEnabled(true);
                mBtnChanged.setBackground(getResources().getDrawable(R.drawable.seleted_button));
                mBtnChanged.setEnabled(true);
                mBtnChange.setBackground(getResources().getDrawable(R.color.colorDark));
                mBtnChange.setEnabled(false);

                key=mEtTitle.getText().toString();
                break;
            case R.id.btn_changed:
                ContentValues values=new ContentValues();
                values.put("title",mEtTitle.getText().toString());
                values.put("content",mEtContent.getText().toString());
                db.update("information",values,"title=?",new String[]{key});

                mEtTitle.setEnabled(false);
                mEtContent.setEnabled(false);
                mBtnChanged.setBackground(getResources().getDrawable(R.color.colorDark));
                mBtnChanged.setEnabled(false);
                mBtnDelete.setBackground(getResources().getDrawable(R.color.colorDark));
                mBtnDelete.setEnabled(false);
                mBtnChange.setEnabled(true);
                mBtnChange.setBackground(getResources().getDrawable(R.drawable.seleted_button));

                Toast.makeText(MessageActivity.this,"修改完成",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                db.delete("information","title=?",new String[]{key});

                mEtTitle.setEnabled(false);
                mEtContent.setEnabled(false);
                mBtnChanged.setBackground(getResources().getDrawable(R.color.colorDark));
                mBtnChanged.setEnabled(false);
                mBtnDelete.setBackground(getResources().getDrawable(R.color.colorDark));
                mBtnDelete.setEnabled(false);
                mBtnChange.setEnabled(true);
                mBtnChange.setBackground(getResources().getDrawable(R.drawable.seleted_button));

                Toast.makeText(MessageActivity.this,"删除成功，请刷新一下哦",Toast.LENGTH_SHORT).show();
                //让其删除后，销毁当前activity,直接跳转回上一个activity
                finish();
                break;
                default:
                    break;
        }
        db.close();
    }
}
