package dnd.furkhail.bonuscalculator.presentation.view.playercharacter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dnd.furkhail.bonuscalculator.MyApp;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.dagger.components.ApplicationComponent;
import dnd.furkhail.bonuscalculator.domain.business.Ability;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.domain.business.Stat;
import dnd.furkhail.bonuscalculator.presentation.base.BaseFragment;
import dnd.furkhail.bonuscalculator.presentation.view.adapter.AbilityAdapter;
import dnd.furkhail.bonuscalculator.presentation.view.adapter.StatAdapter;

public class PlayerCharacterFragment extends BaseFragment implements PlayerCharacterView {

    private static final String TAG = "PlayerCharacterFragment";

    @BindView(R.id.ability_scores_recycler)
    RecyclerView mAbilityScoresRecycler;
    AbilityAdapter mAbilityAdapter;

    @BindView(R.id.pc_stats_recycler)
    RecyclerView mStatScoresRecycler;
    StatAdapter mStatAdapter;

    @BindView(R.id.name_lbl)
    TextView mNameTextView;

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
            mStatScoresRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
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
//            Log.i(TAG, "onViewCreated: no saveinstanceState");
            mPresenter.initialize();
        }
    }

    @Override
    public void renderPlayerCharacter(@NonNull PlayerCharacter playerCharacter) {
        mPlayerCharacter = playerCharacter;
        setNamePlayerCharacter();
        setAbilityAdapter();
        setStatAdapter();
    }

    private void setStatAdapter() {
        if (mStatAdapter == null) {
            mStatAdapter = new StatAdapter(mPlayerCharacter.getStats());
            mStatScoresRecycler.setAdapter(mStatAdapter);
            mStatAdapter.getPositionClicks()
                    .subscribe(this::showStatAmountDialog, Throwable::printStackTrace);
        } else {
            mStatAdapter.setScores(mPlayerCharacter.getStats());
        }
    }

    private void setAbilityAdapter() {
        if (mAbilityAdapter == null) {
            mAbilityAdapter = new AbilityAdapter(mPlayerCharacter.getAbilities());
            mAbilityScoresRecycler.setAdapter(mAbilityAdapter);
            mAbilityAdapter.getPositionClicks()
                    .subscribe(this::showChangeAbilityDialog, Throwable::printStackTrace);
        } else {
            mAbilityAdapter.setScores(mPlayerCharacter.getAbilities());
        }
    }

    private void setNamePlayerCharacter(){
        mNameTextView.setText(mPlayerCharacter.getName());
    }

    private void showChangeAbilityDialog(Ability ability) {
        Log.i(TAG, "showChangeAbilityDialog: ");
        if(mPlayerCharacter.getAbilities().contains(ability)) {
            new MaterialDialog.Builder(getContext())
                    .title(ability.getName())
                    .autoDismiss(false)
                    .cancelable(true)
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input("actualiza la " + ability.getName(), ability.getAmount() + "", false, (dialog, input) -> {
                        if(TextUtils.isDigitsOnly(input)){
                            mPlayerCharacter.getAbilities()
                                    .get(mPlayerCharacter.getAbilities().indexOf(ability))
                                    .setAmount(Integer.parseInt(input.toString()));
                            mPresenter.reloadPlayerCharacter(mPlayerCharacter);
                            dialog.dismiss();
                        }
                    }).show();
        }
    }

    private void showNewStatDialog() {
        new MaterialDialog.Builder(getContext())
                .title("Nuevo stat")
                .autoDismiss(false)
                .cancelable(true)
                .input(getString(R.string.pc_name), "", false, (dialog, input) -> {
                    if(input.length()>0){
                        boolean exists = false;
                        for(Stat s: mPlayerCharacter.getStats()) {
                            if(input.toString().equals(s.getName())) {
                                exists = true;
                                break;
                            }
                        }
                        if(!exists){
                            Stat stat = new Stat(input.toString(), 0);
                            mPlayerCharacter.getStats().add(stat);
                            showStatAmountDialog(stat);
                            mPresenter.reloadPlayerCharacter(mPlayerCharacter);
                            dialog.dismiss();
                        }
                    }
                }).show();
    }

    private void showStatAmountDialog(Stat stat){
        new MaterialDialog.Builder(getContext())
                .title(stat.getName())
                .autoDismiss(false)
                .cancelable(true)
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input("cambia la cifra", stat.getAmount()+"", false, (dialog, input) -> {
                    if(TextUtils.isDigitsOnly(input)){
                        mPlayerCharacter.getStats()
                                .get(mPlayerCharacter.getStats().indexOf(stat))
                                .setAmount(Integer.parseInt(input.toString()));
                        mPresenter.reloadPlayerCharacter(mPlayerCharacter);
                        dialog.dismiss();
                    }
                }).show();
    }


    @OnClick(R.id.name_lbl)
    public void clickNameLbl(){
        new MaterialDialog.Builder(getContext())
                .title(R.string.pc_name)
                .cancelable(true)
                .autoDismiss(false)
                .input(getString(R.string.pc_name), mPlayerCharacter.getName(), false, (dialog, input) -> {
                    if (input.length() > 0) {
                        mPlayerCharacter.setName(input.toString());
                        mPresenter.reloadPlayerCharacter(mPlayerCharacter);
                        dialog.dismiss();
                    }
                }).show();
    }

    @OnClick(R.id.fab)
    public void clickFab(){
        showNewStatDialog();
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
