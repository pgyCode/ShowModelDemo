package com.danale.shixisheng2018.shower.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.TextView;

import com.danale.shixisheng2018.shower.R;
import com.danale.shixisheng2018.shower.all.AllFragment;
import com.danale.shixisheng2018.shower.mine.MineFragment;
import com.danale.shixisheng2018.shower.model.AccountModel;
import com.danale.shixisheng2018.shower.model.AllModel;
import com.danale.shixisheng2018.shower.parent.OnWeakTaskListener;
import com.danale.shixisheng2018.shower.parent.WeakTask;
import com.danale.shixisheng2018.shower.tool.App;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TextView txtAll;
    private View labelAll;
    private TextView txtMine;
    private View labelMine;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initLayout();

        App.postRunnable(new FlushRunnable());
    }

    private void initLayout(){
        viewPager = findViewById(R.id.viewpager);
        txtAll = findViewById(R.id.txt_all);
        labelAll = findViewById(R.id.label_all);
        txtMine = findViewById(R.id.txt_mine);
        labelMine = findViewById(R.id.label_mine);

        fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new MineFragment());

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                replaceFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        findViewById(R.id.btn_all).setOnClickListener(this);
        findViewById(R.id.btn_mine).setOnClickListener(this);

        replaceFragment(0);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_all){
            viewPager.setCurrentItem(0);
        } else if (view.getId() == R.id.btn_mine){
            viewPager.setCurrentItem(1);
        }
    }

    private void replaceFragment(int sign) {
        switch (sign) {
            case 0:
                labelAll.setBackgroundResource(R.color.T_main);
                txtAll.setTextColor(ContextCompat.getColor(this, R.color.T_main));
                labelMine.setBackgroundResource(R.color.T_touming);
                txtMine.setTextColor(ContextCompat.getColor(this, R.color.T_black));
                break;
            case 1:
                labelAll.setBackgroundResource(R.color.T_touming);
                txtAll.setTextColor(ContextCompat.getColor(this, R.color.T_black));
                labelMine.setBackgroundResource(R.color.T_main);
                txtMine.setTextColor(ContextCompat.getColor(this, R.color.T_main));
                break;
        }
    }

    private static class FlushRunnable implements Runnable{

        @Override
        public void run() {
            AllModel.getInstance().netFlush();
        }
    }
}
