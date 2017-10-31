package com.berdnamu.docentree;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;
    private final int FRAGMENT4 = 4;
    public ImageButton btn_find;
    public ImageButton btn_help;
    public ImageButton btn_display;
    public ImageButton btn_group;
    public ImageButton write_find_btn;
    public ImageButton write_help_btn;
    public LinearLayout write_find_layout;
    public LinearLayout write_help_layout;
    public Button find_submit;
    public Button help_submit;
    public String find_name;
    public String find_pw;
    public String find_content;
    public String help_name;
    public String help_pw;
    public String help_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_find = (ImageButton) findViewById(R.id.btn_find);
        btn_help = (ImageButton) findViewById(R.id.btn_help);
        btn_display = (ImageButton) findViewById(R.id.btn_display);
        btn_group = (ImageButton) findViewById(R.id.btn_group);

        btn_find.setOnClickListener(this);
        btn_help.setOnClickListener(this);
        btn_display.setOnClickListener(this);
        btn_group.setOnClickListener(this);

        write_find_btn = (ImageButton) findViewById(R.id.write_find_btn);
        write_help_btn = (ImageButton) findViewById(R.id.write_help_btn);

        write_find_btn.setOnClickListener(this);
        write_help_btn.setOnClickListener(this);

        write_find_layout = (LinearLayout) findViewById(R.id.write_find_layout);
        write_help_layout = (LinearLayout) findViewById(R.id.write_help_layout);

        find_submit = (Button) findViewById(R.id.find_submit);
        help_submit = (Button) findViewById(R.id.help_submit);

        find_submit.setOnClickListener(this);
        help_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_find :
                callFragment(FRAGMENT1);
                break;
            case R.id.btn_help :
                callFragment(FRAGMENT2);
                break;
            case R.id.btn_display :
                callFragment(FRAGMENT3);
                break;
            case R.id.btn_group :
                callFragment(FRAGMENT4);
                break;
            case R.id.write_find_btn :
                write_find_layout.setVisibility(LinearLayout.VISIBLE);
                write_help_layout.setVisibility(LinearLayout.GONE);
                break;
            case R.id.write_help_btn :
                write_help_layout.setVisibility(LinearLayout.VISIBLE);
                write_find_layout.setVisibility(LinearLayout.GONE);
                break;
            case R.id.find_submit :
                findSubmitData();
                break;
            case R.id.help_submit :
                helpSubmitData();
                break;
        }
    }

    private void callFragment(int frament_no){

        LinearLayout write_layout = (LinearLayout) findViewById(R.id.write_layout);
        write_layout.setVisibility(LinearLayout.GONE);

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no){
            case 1:
                btn_find.setImageResource(R.drawable.btn_bottom_find_on);
                btn_help.setImageResource(R.drawable.btn_bottom_help_off);
                btn_display.setImageResource(R.drawable.btn_bottom_display_off);
                btn_group.setImageResource(R.drawable.btn_bottom_grouproom_off);

                // '프래그먼트1' 호출

                FindActivity fragment1 = new FindActivity();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                btn_find.setImageResource(R.drawable.btn_bottom_find_off);
                btn_help.setImageResource(R.drawable.btn_bottom_help_on);
                btn_display.setImageResource(R.drawable.btn_bottom_display_off);
                btn_group.setImageResource(R.drawable.btn_bottom_grouproom_off);

                // '프래그먼트2' 호출
                HelpActivity fragment2 = new HelpActivity();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;

            case 3:
                btn_find.setImageResource(R.drawable.btn_bottom_find_off);
                btn_help.setImageResource(R.drawable.btn_bottom_help_off);
                btn_display.setImageResource(R.drawable.btn_bottom_display_on);
                btn_group.setImageResource(R.drawable.btn_bottom_grouproom_off);

                // '프래그먼트3' 호출
                DisplayActivity fragment3 = new DisplayActivity();
                transaction.replace(R.id.fragment_container, fragment3);
                transaction.commit();
                break;

            case 4:
                btn_find.setImageResource(R.drawable.btn_bottom_find_off);
                btn_help.setImageResource(R.drawable.btn_bottom_help_off);
                btn_display.setImageResource(R.drawable.btn_bottom_display_off);
                btn_group.setImageResource(R.drawable.btn_bottom_grouproom_on);

                // '프래그먼트4' 호출
                GroupActivity fragment4 = new GroupActivity();
                transaction.replace(R.id.fragment_container, fragment4);
                transaction.commit();
                break;

        }

    }

    private void findSubmitData() {
        find_name = ((EditText)(findViewById(R.id.find_name))).getText().toString();
        find_pw = ((EditText)(findViewById(R.id.find_pw))).getText().toString();
        find_content = ((EditText)(findViewById(R.id.find_content))).getText().toString();

        Toast.makeText(MainActivity.this, "전송하였습니다.", Toast.LENGTH_SHORT).show();
    }

    private void helpSubmitData() {
        help_name = ((EditText)(findViewById(R.id.help_name))).getText().toString();
        help_pw = ((EditText)(findViewById(R.id.help_pw))).getText().toString();
        help_content = ((EditText)(findViewById(R.id.help_content))).getText().toString();

        Toast.makeText(MainActivity.this, "전송하였습니다", Toast.LENGTH_SHORT).show();
    }


}
