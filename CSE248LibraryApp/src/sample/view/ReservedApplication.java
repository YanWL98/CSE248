package sample.view;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.ReservedRecord;
import sample.controller.Controller;


public class ReservedApplication extends Application {
    List<ReservedRecord> reservedRecords;

    public ReservedApplication(List<ReservedRecord> reservedRecords) {
        this.reservedRecords = reservedRecords;
    }

    Controller controller =new Controller();

    public ObservableList<ReservedRecord> getPatrons() {
        ObservableList<ReservedRecord> list = FXCollections.observableArrayList();
        for (ReservedRecord reservedRecord : reservedRecords) {
            list.add(reservedRecord);
        }
        return list;
    }

    
    public TableView<ReservedRecord> createTableByObject() {
        TableView<ReservedRecord> tableView = new TableView<ReservedRecord>();
        addTransactionRecordColumns(tableView);
        ObservableList<ReservedRecord> transactionRecords = getPatrons();
        tableView.setItems(transactionRecords);
        return tableView;
    }


    private void addTransactionRecordColumns(TableView<ReservedRecord> tableView) {
        TableColumn<ReservedRecord, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<ReservedRecord, Integer>("id"));
        idColumn.setMinWidth(50);
        tableView.getColumns().add(idColumn);

        TableColumn<ReservedRecord, String> patronIdColumn = new TableColumn<>("patronId");
        patronIdColumn.setCellValueFactory(new PropertyValueFactory<ReservedRecord, String>("patronId"));
        patronIdColumn.setMinWidth(50);
        tableView.getColumns().add(patronIdColumn);

        TableColumn<ReservedRecord, String> isbnColumn = new TableColumn<>("isbn");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<ReservedRecord, String>("isbn"));
        isbnColumn.setMinWidth(50);
        tableView.getColumns().add(isbnColumn);

    }


    @Override
    public void start(Stage rservedstage) throws Exception {
        Scene scene = new Scene(new Group());
        rservedstage.setTitle("Reserved Records");
        rservedstage.setWidth(1000);
        rservedstage.setHeight(500);
        TableView<ReservedRecord> table = createTableByObject();


        table.setEditable(false);
        table.setMinWidth(800);
        table.setMinHeight(300);
        Button button = new Button("<Back");
        button.setOnMouseClicked(e -> {
            rservedstage.close();
            PatronApplication patronApplication = new PatronApplication();
            try {
                patronApplication.start(rservedstage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(button,table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        rservedstage.setScene(scene);
        rservedstage.show();
    }
}
