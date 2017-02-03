package dnd.furkhail.bonuscalculator;

import android.app.Application;
import android.content.Context;

import dnd.furkhail.bonuscalculator.dagger.components.ApplicationComponent;
import dnd.furkhail.bonuscalculator.dagger.components.DaggerApplicationComponent;
import dnd.furkhail.bonuscalculator.dagger.modules.ApplicationModule;

public class MyApp extends Application {

    private ApplicationComponent applicationComponent;
    private static MyApp instance;

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        instance = this;
    }

    public void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public static MyApp getContext(){
        return instance;
    }
}