package sample.welcome;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Administration;
import sample.Patron;
import sample.controller.Controller;
import sample.save.AdministrationSave;
import sample.save.AlertBox;
import sample.save.PatronSave;
import sample.view.AdminApplication;
import sample.view.BookApplication;

public class WelcomeApplication extends Application {

    Controller controller = new Controller();

    List<Patron> patronList;
    List<Administration> administrations;

    public WelcomeApplication() {
        try {
            this.patronList = PatronSave.getAllPartons();
            this.administrations = AdministrationSave.getAllAdministrations();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        btn.setOnMouseClicked(e -> {
            
            String name = userTextField.getText();
            String password = pwBox.getText();
            String id = password.substring(name.length());
            boolean flag = controller.checkUser(name, password);
            if (flag) {
                Patron patron = new Patron();
                patron.setId(id);
                patron.setName(name);
                
                AlertBox.display("Tips", "SUCCEED");
                
                primaryStage.close();
                
                for (Administration administration : administrations) {
                    if (id.equals(administration.getId()) && name.equals(administration.getName())) {
                        AdminApplication adminApplication = new AdminApplication(patron);
                        
                        try {
                            adminApplication.start(primaryStage);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    } else {
                        BookApplication ac = new BookApplication(patron);
                        
                        try {
                            ac.start(primaryStage);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    }
                }
            } else {
                AlertBox.display("Tips", "FAILED");
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
//        String name = "Khalil Kirkpatrick";
//        String pwd = "Khalil Kirkpatrick3";
//
//        int i = pwd.lastIndexOf(name);
//        String id = pwd.substring(name.length());
//        System.out.println(id);


    }
}
