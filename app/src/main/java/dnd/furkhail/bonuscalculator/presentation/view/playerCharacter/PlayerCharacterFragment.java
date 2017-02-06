package dnd.furkhail.bonuscalculator.presentation.view.playerCharacter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dnd.furkhail.bonuscalculator.MyApp;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.dagger.components.ApplicationComponent;
import dnd.furkhail.bonuscalculator.domain.business.Ability;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.presentation.base.BaseFragment;
import dnd.furkhail.bonuscalculator.presentation.view.adapter.AbilityAdapter;

public class PlayerCharacterFragment extends BaseFragment implements PlayerCharacterView {

    private static final String TAG = "PlayerCharacterFragment";

    @BindView(R.id.ability_scores_recycler)
    RecyclerView mAbilityScoresRecycler;
    AbilityAdapter mAbilityAdapter;

    @Inject
    PlayerCharacterPresenter mPresenter;

    PlayerCharacter mPlayerCharacter;

    public PlayerCharacterFragment() {
    }

    public static PlayerCharacterFragment newInstance() {
        Bundle args = new Bundle();

        PlayerCharacterFragment fragment = new PlayerCharacterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(MyApp.get(getActivity()).getApplicationComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            ButterKnife.bind(this, view);
            Log.i(TAG, "onCreateView: new layoutmanager");
            mAbilityScoresRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_playable_character_rv;
    }

    @Override
    protected void injectComponent(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        if (savedInstanceState == null) {
            Log.i(TAG, "onViewCreated: no saveinstanceState");
            mPresenter.initialize();
        }
    }

    @Override
    public void renderPlayerCharacter(PlayerCharacter playerCharacter) {
        Log.d(TAG, "renderPlayerCharacter() called with: playerCharacter = [" + playerCharacter + "]");
        mPlayerCharacter = playerCharacter;
        if (mAbilityAdapter == null) {
            Log.i(TAG, "renderPlayerCharacter: new abilityadapter");
            mAbilityAdapter = new AbilityAdapter(mPlayerCharacter.getAbilities());
            mAbilityScoresRecycler.setAdapter(mAbilityAdapter);
        } else {
            Log.i(TAG, "renderPlayerCharacter: old abilityadapter");
            mAbilityAdapter.setAbilities(mPlayerCharacter.getAbilities());
        }
        mAbilityAdapter.getPositionClicks().doOnNext(ability -> {
            Log.d(TAG, "accept() called with: ability = [" + ability + "]");
            showChangeAbilityDialog(ability);
        }).forEach(ability -> Log.d(TAG, "accept() called with: ability = [" + ability + "]"));
    }

    @Override
    public void showChangeAbilityDialog(Ability ability) {
        Log.i(TAG, "showChangeAbilityDialog: ");
        if(mPlayerCharacter.getAbilities().contains(ability)) {
            new MaterialDialog.Builder(getContext())
                    .title(ability.getName())
                    .autoDismiss(false)
                    .cancelable(true)
                    .input("actualiza la " + ability.getName(), ability.getAmount() + "", false, (dialog, input) -> {
                        if(TextUtils.isDigitsOnly(input)){
                            mPlayerCharacter.getAbilities()
                                    .get(mPlayerCharacter.getAbilities().indexOf(ability))
                                    .setAmount(Integer.parseInt(input.toString()));
                            mPresenter.reloadPlayerCharacter(mPlayerCharacter);
                            dialog.dismiss();
                        }
                        else{
                            Log.i(TAG, "onInput: not number");
                        }
                    }).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        mPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
        mPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
        mPresenter.destroy();
    }
}
