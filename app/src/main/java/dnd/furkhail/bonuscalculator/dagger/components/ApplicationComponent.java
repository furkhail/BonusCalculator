package dnd.furkhail.bonuscalculator.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import dnd.furkhail.bonuscalculator.dagger.modules.ApplicationModule;
import dnd.furkhail.bonuscalculator.presentation.view.activity.BaseActivity;
import dnd.furkhail.bonuscalculator.presentation.view.fragment.MainFragment;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainFragment mainFragment);
}