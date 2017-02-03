package dnd.furkhail.bonuscalculator.presentation.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dnd.furkhail.bonuscalculator.dagger.components.ApplicationComponent;

public abstract class BaseFragment extends Fragment {
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    protected abstract int getLayoutResource();

    protected abstract void injectComponent(ApplicationComponent component);
}
