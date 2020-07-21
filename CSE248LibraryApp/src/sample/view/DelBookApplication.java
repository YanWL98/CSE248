package sample.view;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Patron;
import sample.controller.Controller;


public class DelBookApplication extends Application {

    Controller controller = new Controller();
    Patron patron;
    public DelBookApplication(Patron patron) {
        this.patron = patron;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Del Book");
        primaryStage.setWidth(600);
        primaryStage.setHeight(350);

        TextArea textArea = new TextArea();


        Button button = new Button("submit");
        button.setOnMouseClicked(e -> {
            try {
                controller.delBook(textArea.getText(), patron);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            primaryStage.close();
            BookApplication bookApplication = new BookApplication(patron);
            try {
                bookApplication.start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        final VBox vbox = new VBox();
        vbox.getChildren().addAll(textArea);

        final VBox vboxChildren = new VBox();
        vboxChildren.getChildren().add(button);

        vbox.getChildren().add(vboxChildren);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}