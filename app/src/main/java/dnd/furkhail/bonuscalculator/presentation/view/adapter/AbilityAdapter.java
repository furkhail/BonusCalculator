package dnd.furkhail.bonuscalculator.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.domain.business.Ability;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AbilityAdapter extends RecyclerView.Adapter<AbilityAdapter.ViewHolder> {

    private static final String TAG = "AbilityAdapter";

    private List<Ability> mAbilities;

    private final PublishSubject<Ability> onClickSubject = PublishSubject.create();

    public AbilityAdapter(List<Ability> abilities) {
        mAbilities = abilities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ability_recycler, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ability ability = mAbilities.get(position);
        holder.name.setText(ability.getName());
        holder.score.setText(ability.getAmount()+"");
        holder.score.setOnClickListener(v -> {
            onClickSubject.onNext(ability);
        });
    }

    @Override
    public int getItemCount() {
        return mAbilities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView score;

        ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.item_ability_name_lbl);
            score = (TextView) view.findViewById(R.id.item_ability_score_lbl);
        }

    }

    public Observable<Ability> getPositionClicks(){
        return onClickSubject;
    }

    public void setAbilities(List<Ability> abilities) {
        mAbilities = abilities;
        notifyDataSetChanged();
    }
}
