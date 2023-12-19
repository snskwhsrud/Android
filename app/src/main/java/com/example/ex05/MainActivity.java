package com.example.ex05;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    LinearLayout drawerView;
    TabLayout tab;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("카카오검색");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_splitscreen_24);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerView = findViewById(R.id.drawerView);

        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);

        tab.addTab(tab.newTab().setText("블로그"));
        tab.getTabAt(0).setIcon(R.drawable.baseline_menu_book_24);

        tab.addTab(tab.newTab().setText("도서"));
        tab.getTabAt(1).setIcon(R.drawable.baseline_book_24);

        tab.addTab(tab.newTab().setText("지역"));
        tab.getTabAt(2).setIcon(R.drawable.baseline_home_24);

        PageAdapter adapter =new PageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if(item.getItemId()  == android.R.id.home){
            if(drawerLayout.isDrawerOpen(drawerView)){
                drawerLayout.close();
            }else {
                drawerLayout.openDrawer(drawerView);
            }
        }
        return super.onOptionsItemSelected(item);
    }

 class PageAdapter extends FragmentPagerAdapter {

     public PageAdapter(@NonNull FragmentManager fm) {
         super(fm);
     }

     @NonNull
     @Override
     public Fragment getItem(int position) {
         switch (position) {
             case 0:
                 return new BlogFragment();
             case 1:
                 return new BookFragment();
             case 2:
                 return new LocalFragment();
         }
         return null;
     }

     @Override
     public int getCount() {
         return 3;
     }
 }
}