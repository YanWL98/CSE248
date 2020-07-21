package sample.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Patron;


public class AdminApplication extends Application {


    Patron patron;

    public AdminApplication(Patron patron){
        this.patron = patron;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Choice");
        primaryStage.setWidth(235);
        primaryStage.setHeight(170);

        Button patronPutton = new Button("Patron Manager");
        patronPutton.setPrefWidth(200);
        patronPutton.setPrefHeight(50);
        patronPutton.setOnMouseClicked(e -> {
            primaryStage.close();
            PatronApplication patronApplication = new PatronApplication();
            try {
                patronApplication.start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        Button bookPutton = new Button("Book Manager");
        bookPutton.setPrefWidth(200);
        bookPutton.setPrefHeight(50);
        bookPutton.setOnMouseClicked(e -> {
            primaryStage.close();
            BookApplication bookApplication = new BookApplication(patron);
            try {
                bookApplication.start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(patronPutton, bookPutton);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
