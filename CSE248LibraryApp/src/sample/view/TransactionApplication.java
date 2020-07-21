package sample.view;

import java.io.FileNotFoundException;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.TransactionRecord;
import sample.controller.Controller;


public class TransactionApplication extends Application {


  List<TransactionRecord> transactionRecords;

  public TransactionApplication(List<TransactionRecord> transactionRecords) {
      this.transactionRecords = transactionRecords;
//      this.patron = patron;
  }

  Controller controller =new Controller();

  public ObservableList<TransactionRecord> getPatrons() {
      ObservableList<TransactionRecord> list = FXCollections.observableArrayList();
      for (TransactionRecord transactionRecord : transactionRecords) {
          list.add(transactionRecord);
      }
      return list;
  }


  public TableView<TransactionRecord> createTableByObject() {
      TableView<TransactionRecord> tableView = new TableView<TransactionRecord>();
      addTransactionRecordColumns(tableView);
      ObservableList<TransactionRecord> transactionRecords = getPatrons();
      tableView.setItems(transactionRecords);
      return tableView;
  }


  private void addTransactionRecordColumns(TableView<TransactionRecord> tableView) {
      TableColumn<TransactionRecord, Integer> idColumn = new TableColumn<>("id");
      idColumn.setCellValueFactory(new PropertyValueFactory<TransactionRecord, Integer>("id"));
      idColumn.setMinWidth(50);
      tableView.getColumns().add(idColumn);

      TableColumn<TransactionRecord, String> patronIdColumn = new TableColumn<>("patronId");
      patronIdColumn.setCellValueFactory(new PropertyValueFactory<TransactionRecord, String>("patronId"));
      patronIdColumn.setMinWidth(50);
      tableView.getColumns().add(patronIdColumn);

      TableColumn<TransactionRecord, String> isbnColumn = new TableColumn<>("isbn");
      isbnColumn.setCellValueFactory(new PropertyValueFactory<TransactionRecord, String>("isbn"));
      isbnColumn.setMinWidth(50);
      tableView.getColumns().add(isbnColumn);

//      TableColumn<TransactionRecord, String> ReturnColumn = new TableColumn<>("Return");
//      ReturnColumn.setCellValueFactory(new PropertyValueFactory<TransactionRecord, String>("Return"));
//      ReturnColumn.setMinWidth(50);
//      tableView.getColumns().add(ReturnColumn);

      TableColumn<TransactionRecord, String> createDateColumn = new TableColumn<>("createDate");
      createDateColumn.setCellValueFactory(new PropertyValueFactory<TransactionRecord, String>("createDate"));
      createDateColumn.setMinWidth(50);
      tableView.getColumns().add(createDateColumn);

      TableColumn<TransactionRecord, String> doneDateColumn = new TableColumn<>("doneDate");
      doneDateColumn.setCellValueFactory(new PropertyValueFactory<TransactionRecord, String>("doneDate"));
      doneDateColumn.setMinWidth(50);
      tableView.getColumns().add(doneDateColumn);
  }

  @Override
  public void start(Stage transactionRecordStage) throws Exception {
      Scene scene = new Scene(new Group());
      transactionRecordStage.setTitle("Transaction Records");
      transactionRecordStage.setWidth(1000);
      transactionRecordStage.setHeight(500);
      TableView<TransactionRecord> table = createTableByObject();


      table.setEditable(false);
      table.setMinWidth(800);
      table.setMinHeight(300);
      Button button = new Button("<Back");
      button.setOnMouseClicked(e -> {
          transactionRecordStage.close();
          PatronApplication patronApplication = new PatronApplication();
          try {
              patronApplication.start(transactionRecordStage);
          } catch (Exception exception) {
              exception.printStackTrace();
          }
      });

      TableColumn<TransactionRecord, String> returnColumn = new TableColumn<>("return");
      returnColumn.setCellFactory(col -> {
          TableCell<TransactionRecord, String> cell = new TableCell<TransactionRecord, String>() {
              Button button = new Button("return");

              @Override
              protected void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (!empty) {
                      TableView tableView = getTableView();
                      TransactionRecord transactionRecord = (TransactionRecord) tableView.getItems().get(getIndex());
                      button.setOnAction(event -> {
                          
                          try {
                              controller.returnBook(transactionRecord);
                          } catch (FileNotFoundException e) {
                              e.printStackTrace();
                          }
                      });
                  }
                  if (empty) {
                      
                      setText(null);
                      setGraphic(null);
                  } else {
                      this.setGraphic(button);
                  }
              }
          };
          return cell;
      });
      table.getColumns().add(returnColumn);

      final VBox vbox = new VBox();
      vbox.setSpacing(5);
      vbox.setPadding(new Insets(10, 0, 0, 10));
      vbox.getChildren().addAll(button,table);

      ((Group) scene.getRoot()).getChildren().addAll(vbox);

      transactionRecordStage.setScene(scene);
      transactionRecordStage.show();
  }

  public static void main(String[] args) {
      launch(args);
  }
}
