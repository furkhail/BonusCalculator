package dnd.furkhail.bonuscalculator.presentation.view;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;

public interface StatusListView extends LoadDataView{

    void showAddStatusDialog();

    void renderStatusList(List<Status> statusListt);

    void viewStatus(Status status);
}
