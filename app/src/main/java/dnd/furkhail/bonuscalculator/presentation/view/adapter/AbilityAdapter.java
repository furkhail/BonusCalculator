package dnd.furkhail.bonuscalculator.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.domain.business.Ability;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AbilityAdapter extends RecyclerView.Adapter<AbilityAdapter.ViewHolder>{

    private static final String TAG = "AbilityAdapter";

    List<Ability> mAbilitiesScores;

    final PublishSubject<Ability> onClickSubject = PublishSubject.create();

    public AbilityAdapter(List<Ability> abilitiesScores) {
        mAbilitiesScores = abilitiesScores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ability ability = mAbilitiesScores.get(position);
        holder.name.setText(ability.getName());
        holder.amount.setText(ability.getAmount()+"");
        holder.amount.setOnClickListener(v -> {
            Log.i(TAG, "onBindViewHolder: onclicklistener fired");
            onClickSubject.onNext(ability);
        });
    }

    @Override
    public int getItemCount() {
        return mAbilitiesScores.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView name;
        TextView amount;

        ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.item_score_name_lbl);
            amount = (TextView) view.findViewById(R.id.item_score_amount_lbl);
        }

    }

    public Observable<Ability> getPositionClicks(){
        return onClickSubject;
    }

    public void setAbilitiesScores(List<Ability> abilitiesScores) {
        mAbilitiesScores = abilitiesScores;
        notifyDataSetChanged();
    }
}
