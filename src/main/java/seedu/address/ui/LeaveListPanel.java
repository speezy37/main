package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.LeavePanelSelectionChangedEvent;
import seedu.address.model.leave.Leave;

/**
 * Panel containing the list of leaves.
 */
public class LeaveListPanel extends UiPart<Region> {
    private static final String FXML = "LeaveListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Leave> leaveListView;

    public LeaveListPanel(ObservableList<Leave> leaveList) {
        super(FXML);
        setConnections(leaveList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Leave> leaveList) {
        leaveListView.setItems(leaveList);
        leaveListView.setCellFactory(listView -> new LeaveListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        leaveListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in leave list panel changed to : '" + newValue + "'");
                        raise(new LeavePanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code LeaveCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            leaveListView.scrollTo(index);
            leaveListView.getSelectionModel().clearAndSelect(index);
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code LeaveCard}.
     */
    class LeaveListViewCell extends ListCell<Leave> {
        @Override
        protected void updateItem(Leave leave, boolean empty) {
            super.updateItem(leave, empty);

            if (empty || leave == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LeaveCard(leave, getIndex() + 1).getRoot());
            }
        }
    }

}
