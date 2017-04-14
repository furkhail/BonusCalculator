package dnd.furkhail.bonuscalculator.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.domain.business.Status;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{

    private static final String TAG = "StatusAdapter";

    List<Status> mStatusList;

    final PublishSubject<Status> onClickSubject = PublishSubject.create();

    public StatusAdapter(List<Status> statusList) {
        mStatusList = statusList;
        Log.i(TAG, "StatusAdapter: constructor");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_status, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Status status = mStatusList.get(position);
        holder.name.setText(status.getName());
        holder.active.setChecked(status.isActive());
        holder.statusContainer.setOnClickListener(v -> onClickSubject.onNext(status));

        /*
        holder.amount.setText(status.getAmount()+"");
        holder.amount.setOnClickListener(v -> {
            Log.i(TAG, "onBindViewHolder: onclicklistener fired");
            onClickSubject.onNext(status);
        });
        */
    }

    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_status_name)
        protected TextView name;
        @BindView(R.id.item_status_active)
        protected ToggleButton active;
        @BindView(R.id.status_container)
        protected View statusContainer;

        ViewHolder(View view){
            super(view);

            ButterKnife.bind(this, view);

        }

    }

    public Observable<Status> getPositionClicks(){
        return onClickSubject;
    }

    public void setStatusList(List<Status> statusList) {
        mStatusList = statusList;
        notifyDataSetChanged();
    }
}
