package dnd.furkhail.bonuscalculator.presentation.view.playerCharacter;

import dnd.furkhail.bonuscalculator.domain.business.Ability;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.presentation.view.LoadDataView;

public interface PlayerCharacterView extends LoadDataView{

    void renderPlayerCharacter(PlayerCharacter playerCharacter);

    void showChangeAbilityDialog(Ability ability);

}
