package dnd.furkhail.bonuscalculator.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import dnd.furkhail.bonuscalculator.dagger.modules.ApplicationModule;
import dnd.furkhail.bonuscalculator.dagger.modules.ProjectModule;
import dnd.furkhail.bonuscalculator.presentation.view.activity.MainActivity;
import dnd.furkhail.bonuscalculator.presentation.view.playerCharacter.PlayerCharacterFragment;

@Singleton
@Component(modules = {ApplicationModule.class, ProjectModule.class})
public interface ApplicationComponent {
    void inject(MainActivity baseActivity);

    void inject(PlayerCharacterFragment baseFragment);
}