package dnd.furkhail.bonuscalculator.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dnd.furkhail.bonuscalculator.R;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class ScoreAdapter<T> extends RecyclerView.Adapter<ScoreAdapter.ViewHolder>{

    private static final String TAG = "AbilityAdapter";

    List<T> mScores;

    final PublishSubject<T> onClickSubject = PublishSubject.create();

    ScoreAdapter(List<T> scores) {
        mScores = scores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_score, null);
        return new ViewHolder(view);
    }

    @Override
    public abstract void onBindViewHolder(ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mScores.size();
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

    public Observable<T> getPositionClicks(){
        return onClickSubject;
    }

    public void setScores(List<T> scores) {
        mScores = scores;
        notifyDataSetChanged();
    }
}
