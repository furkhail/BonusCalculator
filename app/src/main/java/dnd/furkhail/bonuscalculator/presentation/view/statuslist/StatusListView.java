package dnd.furkhail.bonuscalculator.presentation.view.statuslist;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.presentation.view.LoadDataView;

public interface StatusListView extends LoadDataView {

    void showAddStatusDialog();

    void renderStatusList(List<Status> statusListt);

    void viewStatus(Status status);
}
