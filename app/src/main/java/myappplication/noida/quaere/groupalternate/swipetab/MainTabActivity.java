package myappplication.noida.quaere.groupalternate.swipetab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import myappplication.noida.quaere.groupalternate.R;

public class MainTabActivity extends AppCompatActivity {
    TabLayout.Tab tab1, tab2, tab3;
    // private Toolbar toolbar;
    public TabLayout tabLayout;
    private ViewPager viewPager;
    private static int[] tabIcons = {
            R.mipmap.ic_tab_favourite,
            R.mipmap.ic_tab_call, R.mipmap.ic_tab_contacts

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main_activity);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);
        // setUpTabIcons();

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.iv_tab);


            tabTextView.setText(tab.getText());
            imageView.setImageResource(tabIcons[i]);
            tab.setCustomView(relativeLayout);

        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "Favorites");
        adapter.addFrag(new ContactUsFragment(), "ContactUs");
        adapter.addFrag(new LoginFragment(), "Profile");
        // adapter.
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        //    private BaseFragment mFragmentAtPos2; // Fragment at index 2
        private final FragmentManager mFragmentManager = getSupportFragmentManager();
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            //   mFragmentManager = manager;

        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeFragment();
            }
            if (position == 1) {
                return new ContactUsFragment();
            }
            if (position == 2)
                return new RootFragment();
            else
                return new LoginFragment();

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
