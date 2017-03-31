package dnd.furkhail.bonuscalculator.presentation.view.playercharacter;

import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.presentation.view.LoadDataView;

public interface PlayerCharacterView extends LoadDataView{

    void renderPlayerCharacter(PlayerCharacter playerCharacter);

//    void showChangeAbilityDialog(Ability ability);

//    void showChangeStatDialog(Stat stat);

}
