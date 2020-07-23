package com.example.noteorplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new MainFragment(MainActivity.this));
    }

    //设置Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //给Menu添加行为
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                replaceFragment(new AddFragment(MainActivity.this));
                break;
            case R.id.renew:
                replaceFragment(new MainFragment(MainActivity.this));
                Toast.makeText(MainActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Fragment help=getSupportFragmentManager().findFragmentById(R.id.fragment_main);
                getSupportFragmentManager().beginTransaction().hide(help).
                        add(R.id.fragment_main,new HelpFragment()).addToBackStack(null).commitAllowingStateLoss();
                break;
            case R.id.about:
                Fragment about=getSupportFragmentManager().findFragmentById(R.id.fragment_main);
                getSupportFragmentManager().beginTransaction().hide(about).
                        add(R.id.fragment_main,new AboutFragment()).addToBackStack(null).commitAllowingStateLoss();
                break;
                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }

    //用于动态调用fragment
    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_main,fragment).commitAllowingStateLoss();
    }
}
