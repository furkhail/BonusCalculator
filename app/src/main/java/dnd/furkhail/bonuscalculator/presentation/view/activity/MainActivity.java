package dnd.furkhail.bonuscalculator.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import dnd.furkhail.bonuscalculator.MyApp;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.presentation.base.BaseActivity;
import dnd.furkhail.bonuscalculator.presentation.view.playerCharacter.PlayerCharacterFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, PlayerCharacterFragment.newInstance());
        }
    }

    private void initializeInjector() {
        ((MyApp) getApplication()).getApplicationComponent().inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
