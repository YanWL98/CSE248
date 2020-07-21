package sample.view;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Patron;
import sample.ReservedRecord;
import sample.TransactionRecord;
import sample.controller.Controller;

public class PatronApplication extends Application {

    Controller controller =new Controller();

    public ObservableList<Patron> getPatrons() {
        Controller controller = new Controller();
        List<Patron> patrons = controller.qryAllPatrons();
        ObservableList<Patron> list = FXCollections.observableArrayList();
        for (Patron patron : patrons) {
            list.add(patron);
        }
        return list;
    }

    
    public TableView<Patron> createTableByObject() {
        TableView<Patron> tableView = new TableView<Patron>();
        addBookColumns(tableView);
        ObservableList<Patron> patrons = getPatrons();
        tableView.setItems(patrons);
        return tableView;
    }


    private void addBookColumns(TableView<Patron> tableView) {
        TableColumn<Patron, Integer> titleColumn = new TableColumn<>("name");
        titleColumn.setCellValueFactory(new PropertyValueFactory<Patron, Integer>("name"));
        titleColumn.setMaxWidth(200);
        titleColumn.setMinWidth(150);
        tableView.getColumns().add(titleColumn);

        TableColumn<Patron, String> isbnColumn = new TableColumn<>("id");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Patron, String>("id"));
        isbnColumn.setMinWidth(50);
        tableView.getColumns().add(isbnColumn);

        TableColumn<Patron, String> authorColumn = new TableColumn<>("phone");
        authorColumn.setCellValueFactory(new PropertyValueFactory<Patron, String>("phone"));
        authorColumn.setMinWidth(50);
        tableView.getColumns().add(authorColumn);


    }

    @Override
    public void start(Stage patronStage) throws Exception {
        Scene scene = new Scene(new Group());
        patronStage.setTitle("All Patrons");
        patronStage.setWidth(550);
        patronStage.setHeight(500);
        TableView<Patron> table = createTableByObject();

        table.setEditable(false);
        table.setMinWidth(500);
        table.setMinHeight(300);
        HBox hBox = new HBox();


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(hBox, table);

        TableColumn<Patron, String> transactionColumn = new TableColumn<>("transaction");
        transactionColumn.setCellFactory(col -> {
            TableCell<Patron, String> cell = new TableCell<Patron, String>() {
                Button button = new Button("transaction");
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        Patron patron = getTableView().getItems().get(getIndex());
                        button.setOnMouseClicked(e -> {
                            patronStage.close();
                            List<TransactionRecord> allTransactions = controller.getAllTransactions(patron);
                            TransactionApplication transactionApplication = new TransactionApplication(allTransactions);
                            try {
                                transactionApplication.start(patronStage);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });
                        if (empty) {
                            
                            setText(null);
                            setGraphic(null);
                        } else {
                            this.setGraphic(button);
                        }
                    }
                }
            };
            return cell;
        });
        TableColumn<Patron, String> reservedColumn = new TableColumn<>("reserved");
        reservedColumn.setCellFactory(col -> {
            TableCell<Patron, String> cell = new TableCell<Patron, String>() {
                Button button = new Button("reserved");
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        Patron patron = getTableView().getItems().get(getIndex());
                        button.setOnMouseClicked(e -> {
                            patronStage.close();
                            List<ReservedRecord> allReserveds = controller.getAllReserveds(patron);
                            ReservedApplication reservedApplication = new ReservedApplication(allReserveds);
                            try {
                                reservedApplication.start(patronStage);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        });
                        if (empty) {
                            
                            setText(null);
                            setGraphic(null);
                        } else {
                            this.setGraphic(button);
                        }
                    }
                }
            };
            return cell;
        });
        table.getColumns().addAll(transactionColumn,reservedColumn);


        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        patronStage.setScene(scene);
        patronStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
