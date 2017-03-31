package dnd.furkhail.bonuscalculator.presentation.view.playercharacter;

import android.util.Log;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.domain.interactor.base.DefaultMaybeObserver;
import dnd.furkhail.bonuscalculator.domain.interactor.base.DefaultObserver;
import dnd.furkhail.bonuscalculator.domain.interactor.playercharacter.GetPlayerCharacterUseCase;
import dnd.furkhail.bonuscalculator.domain.interactor.playercharacter.UpdatePlayerCharacterUseCase;
import dnd.furkhail.bonuscalculator.presentation.base.Presenter;

class PlayerCharacterPresenter implements Presenter<PlayerCharacterView> {

    private static final String TAG = "PlayerCharacterPresente";

    private PlayerCharacterView mPlayerCharacterView;

    private GetPlayerCharacterUseCase mGetPlayerCharacterUseCase;
    private UpdatePlayerCharacterUseCase mUpdatePlayerCharacterUseCase;

    private boolean initializing = false;

    @Inject
    PlayerCharacterPresenter(GetPlayerCharacterUseCase getPlayerCharacterUseCase,
                             UpdatePlayerCharacterUseCase updatePlayerCharacterUseCase
                             ) {
        mGetPlayerCharacterUseCase = getPlayerCharacterUseCase;
        mUpdatePlayerCharacterUseCase = updatePlayerCharacterUseCase;
    }

    @Override
    public void resume() {
        if(!initializing){
            initialize();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mPlayerCharacterView = null;
        mGetPlayerCharacterUseCase.dispose();
        mUpdatePlayerCharacterUseCase.dispose();
    }

    @Override
    public void setView(PlayerCharacterView view) {
        mPlayerCharacterView = view;
    }

    @Override
    public void initialize() {
        Log.d(TAG, "initialize() called");
        initializing = true;
        loadPlayerCharacter();
    }

    void reloadPlayerCharacter(PlayerCharacter playerCharacter){
        hideViewRetry();
        showViewLoading();
        updatePlayerCharacter(playerCharacter);
    }

    private void loadPlayerCharacter(){
        hideViewRetry();
        showViewLoading();
        getPlayerCharacter();
    }

    private void showPlayerCharacter(PlayerCharacter playerCharacter) {
        mPlayerCharacterView.renderPlayerCharacter(playerCharacter);
    }

    private void getPlayerCharacter() {
        mGetPlayerCharacterUseCase.execute(new GetPlayerCharacterObserver());
    }

    void updatePlayerCharacter(PlayerCharacter playerCharacter){
        mUpdatePlayerCharacterUseCase.execute(new UpdatePlayerCharacterObserver(), playerCharacter);
    }

    private final class GetPlayerCharacterObserver extends DefaultObserver<PlayerCharacter>{

        @Override
        public void onNext(PlayerCharacter playerCharacter) {
            Log.d(TAG, "onNext() called with: playerCharacter = [" + playerCharacter + "]");
            showPlayerCharacter(playerCharacter);
            initializing = false;
        }

        @Override
        public void onComplete() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable exception) {
            Log.d(TAG, "onError() called with: exception = [" + exception + "]");
            hideViewLoading();
            showViewRetry();
        }

    }

    private final class UpdatePlayerCharacterObserver extends DefaultMaybeObserver<PlayerCharacter> {

        @Override
        public void onSuccess(PlayerCharacter playerCharacter) {
            Log.d(TAG, "onNext() called with: playerCharacter = [" + playerCharacter + "]");
            hideViewLoading();
            showPlayerCharacter(playerCharacter);
            initializing = false;
        }

        @Override
        public void onComplete() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable exception) {
            Log.d(TAG, "onError() called with: exception = [" + exception + "]");
            hideViewLoading();
            showViewRetry();
        }

    }

    private void showViewLoading() {
        mPlayerCharacterView.showLoading();
    }

    private void hideViewLoading() {
        mPlayerCharacterView.hideLoading();
    }

    private void showViewRetry() {
        mPlayerCharacterView.showRetry();
    }

    private void hideViewRetry() {
        mPlayerCharacterView.hideRetry();
    }
}
