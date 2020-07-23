package com.example.noteorplan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragmentAdapter extends RecyclerView.Adapter <MainFragmentAdapter.Holder>{
    private Context context;
    private String[] strings;

    //构造函数，初始化数据源
    public MainFragmentAdapter(Context context,String[] strings){
        this.context=context;
        this.strings=strings;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //参数这样写，那么我们自己对组件的设置（基于parent计算的）才能生效，不然到recycle里面时就作废了
        Holder holder=new Holder(LayoutInflater.from(context).inflate(R.layout.adapter_item,parent,false));
        return holder;
    }

    //设置每一行所显示的内容
    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.textView.setText(strings[position]);

        //这里还要设置一下监听器，以便跳转到另一个activity,并显示详细内容
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper=new DBHelper(context);
                SQLiteDatabase db=helper.getWritableDatabase();
                Cursor cursor=db.query("information",new String[]{"title","content"},
                        "title=?",new String[]{strings[position]},
                        null,null,null);
                if (cursor.moveToFirst()){
                    String title,content;
                    title=cursor.getString(cursor.getColumnIndex("title"));
                    content=cursor.getString(cursor.getColumnIndex("content"));
                    Intent intent=new Intent(context,MessageActivity.class);
                    intent.putExtra("title",title);
                    intent.putExtra("content",content);
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context,"此标题已被删除/无内容，请刷新",Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }

    //设置列表长度
    @Override
    public int getItemCount() {
        int count;
        count=strings.length;
        return count;
    }
    //创建自己的holder
    class Holder extends RecyclerView.ViewHolder{
        private TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
