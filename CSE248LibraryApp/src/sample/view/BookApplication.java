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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Book;
import sample.Patron;
import sample.controller.Controller;

public class BookApplication extends Application {
	 Controller controller = new Controller();

	    Patron patron;

	    public BookApplication(Patron patron) {
	        this.patron = patron;
	    }

	    public ObservableList<Book> getBooks() {
	        Controller controller = new Controller();
	        List<Book> books = controller.getBooks();
	        ObservableList<Book> list = FXCollections.observableArrayList();
	        for (Book book : books) {
	            list.add(book);
	        }
	        return list;
	    }

	    
	    public TableView<Book> createTableByObject() {
	        TableView<Book> tableView = new TableView<Book>();
	        tableView.setEditable(true);
	        addBookColumns(tableView);


	        ObservableList<Book> books = getBooks();
	        tableView.setItems(books);
	        return tableView;
	    }


	    private void addBookColumns(TableView<Book> tableView) {
	        TableColumn<Book, String> titleColumn = new TableColumn<>("title");
	        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
	        titleColumn.setMaxWidth(600);
	        titleColumn.setMinWidth(50);
	        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        tableView.getColumns().add(titleColumn);

	        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
	        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
	        isbnColumn.setMinWidth(50);
	        isbnColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        tableView.getColumns().add(isbnColumn);

	        TableColumn<Book, String> authorColumn = new TableColumn<>("author");
	        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
	        authorColumn.setMinWidth(50);
	        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	        tableView.getColumns().add(authorColumn);

	        TableColumn<Book, String> priceColumn = new TableColumn<>("price");
	        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));
	        priceColumn.setMinWidth(50);
	        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());

	        tableView.getColumns().add(priceColumn);

	        // my logic is a little mess here but it works
	        TableColumn<Book, String> isReturnedColumn = new TableColumn<>("Borrowed");
	        isReturnedColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Returned"));
	        isReturnedColumn.setMinWidth(50);
	        tableView.getColumns().add(isReturnedColumn);

	        TableColumn<Book, String> subscribeColumn = new TableColumn<>("subscribe");
	        subscribeColumn.setCellFactory(col -> {
	            TableCell<Book, String> cell = new TableCell<Book, String>() {
	                Button button = new Button("subscribe");

	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (!empty) {
	                        TableView tableView = getTableView();
	                        Book book = (Book) tableView.getItems().get(getIndex());
	                        button.setOnAction(event -> {
	                            
	                            controller.bookingBook(book, patron);
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
	        tableView.getColumns().add(subscribeColumn);
	    }

	    @Override
	    public void start(Stage mainStage) throws Exception {
	        Scene scene = new Scene(new Group());
	        mainStage.setTitle("Books");
	        mainStage.setWidth(1200);
	        mainStage.setHeight(500);
	        TableView<Book> table = createTableByObject();

	        TableColumn<Book, String> borrowColumn = new TableColumn<>("borrow");
	        borrowColumn.setCellFactory(col -> {
	            TableCell<Book, String> cell = new TableCell<Book, String>() {
	                Button button = new Button("borrow");

	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (!empty) {
	                        TableView tableView = getTableView();
	                        Book book = (Book) tableView.getItems().get(getIndex());
	                        button.setOnAction(event -> {
	                            
	                            controller.borrowBook(book, patron);
	                            TableView<Book> tableByObject = createTableByObject();
	                            table.setItems(tableByObject.getItems());
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
	        table.getColumns().add(borrowColumn);

	        final Label label = new Label("Address Book");
	        label.setFont(new Font("Arial", 20));
	        Button addBtn = new Button("add book");
	        addBtn.setOnMouseClicked(e -> {
	            AddBookApplication addBookApplication = new AddBookApplication(patron);
	            try {
	                addBookApplication.start(mainStage);
	            } catch (Exception exception) {
	                exception.printStackTrace();
	            }
	        });
	        Button delBtn = new Button("del book");
	        delBtn.setOnMouseClicked(e -> {
	            DelBookApplication delBookApplication = new DelBookApplication(patron);
	            try {
	                delBookApplication.start(mainStage);
	            } catch (Exception exception) {
	                exception.printStackTrace();
	            }
	        });
	        TextField search = new TextField();
	        search.setMinWidth(50);
	        Button button3 = new Button("search");
	        button3.setOnMouseClicked(e -> {
	            String isbn = search.getText();
	            if (null != isbn && isbn.length() > 0) {
	                Book bookByISBN;
	                ObservableList<Book> newBooks = FXCollections.observableArrayList();
	                try {
	                    bookByISBN = controller.getBookByISBN(isbn);
	                    newBooks.add(bookByISBN);
	                    table.setItems(newBooks);
	                } catch (FileNotFoundException fileNotFoundException) {
	                    fileNotFoundException.printStackTrace();
	                    table.getItems().clear();
	                }
	            } else {
	                TableView<Book> tableByObject = createTableByObject();
	                table.setItems(tableByObject.getItems());
	            }
	        });

	        table.setEditable(false);
	        table.setMinWidth(1150);
	        table.setMinHeight(300);
	        HBox hBox = new HBox();
	        hBox.getChildren().addAll(addBtn, delBtn, search, button3);

	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(hBox, table);

	        ((Group) scene.getRoot()).getChildren().addAll(vbox);

	        mainStage.setScene(scene);
	        mainStage.show();

	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
}
