package dnd.furkhail.bonuscalculator;

import android.app.Application;

import dnd.furkhail.bonuscalculator.dagger.components.ApplicationComponent;
import dnd.furkhail.bonuscalculator.dagger.components.DaggerApplicationComponent;

public class MyApp extends Application {

    private static MyApp instance;

    private ApplicationComponent applicationComponent;

    public static MyApp getInstance() {
        return instance;
    }

    public static void setInstance(MyApp instance) {
        MyApp.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

        applicationComponent = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}