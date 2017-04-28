package dnd.furkhail.bonuscalculator.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.domain.business.Stat;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder>{

    private List<Stat> mStats;

    private final PublishSubject<Stat> onClickSubject = PublishSubject.create();

    public StatAdapter(List<Stat> stats) {
        mStats = stats;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stat stat = mStats.get(position);
        holder.name.setText(stat.getName());
        String amount = stat.getAmount() + "";
        holder.amount.setText(amount);
        holder.amount.setOnClickListener(v -> onClickSubject.onNext(stat));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mStats.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_score_name_lbl)
        protected TextView name;

        @BindView(R.id.item_score_amount_lbl)
        TextView amount;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public Observable<Stat> getPositionClicks(){
        return onClickSubject;
    }

    public void setStats(List<Stat> stats) {
        mStats = stats;
        notifyDataSetChanged();
    }
}
