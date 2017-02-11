package dnd.furkhail.bonuscalculator.presentation.view.adapter;

import android.util.Log;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Stat;

public class StatAdapter extends ScoreAdapter<Stat>{

    private static final String TAG = "AbilityAdapterH";

    public StatAdapter(List<Stat> scores) {
        super(scores);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stat stat = mScores.get(position);
        holder.name.setText(stat.getName());
        holder.amount.setText(stat.getAmount()+"");
        holder.amount.setOnClickListener(v -> {
            Log.i(TAG, "onBindViewHolder: onclicklistener fired");
            onClickSubject.onNext(stat);
        });
    }
}
