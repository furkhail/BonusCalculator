package dnd.furkhail.bonuscalculator.presentation.view.fragment;

import android.app.Fragment;
import android.content.Context;

public abstract class BaseFragment extends Fragment {

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
