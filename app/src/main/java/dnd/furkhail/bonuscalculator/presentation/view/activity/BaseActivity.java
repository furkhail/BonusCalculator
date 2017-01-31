package dnd.furkhail.bonuscalculator.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dnd.furkhail.bonuscalculator.MyApp;

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getApplication()).getApplicationComponent().inject(this);
    }
}
