package dnd.furkhail.bonuscalculator.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dnd.furkhail.bonuscalculator.MyApp;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.presentation.base.BaseActivity;
import dnd.furkhail.bonuscalculator.presentation.view.playercharacter.PlayerCharacterFragment;
import dnd.furkhail.bonuscalculator.presentation.view.statuslist.StatusListFragment;


public class MainActivity extends BaseActivity {

    @BindView(R.id.view_pager_main)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (mViewPager != null) {
            setupViewPager();
            mTabLayout.setupWithViewPager(mViewPager);
        }

        this.initializeInjector();
//        if (savedInstanceState == null) {
//            addFragment(R.id.fragment_container, PlayerCharacterFragment.newInstance());
//        }
    }

    private void initializeInjector() {
        ((MyApp) getApplication()).getApplicationComponent().inject(this);

    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(PlayerCharacterFragment.newInstance(), "Player Character");
        adapter.addFragment(StatusListFragment.newInstance(), "Status List");
//        adapter.addFragment(PlayerCharacterFragment.newInstance(), "Category 3");
        mViewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        Adapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
