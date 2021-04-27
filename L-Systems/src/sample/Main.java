package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Button btnRun = new Button();
        btnRun.setText("Clique para gerar a imagem");
        btnRun.setPrefSize(300, 30);
        btnRun.setLayoutX(75);
        btnRun.setLayoutY(30);

        TextArea expInput = new TextArea();
        expInput.setPrefSize(440, 100);
        expInput.setLayoutX(5);
        expInput.setLayoutY(90);
        expInput.setText("Raphael Konichi de Moraes\nRA: 082180028\nEdite o Arquivo 'Input.txt' localizado na pasta do programa.\nNão altere o tipo da letra, apenas quantidade"
                + " e ordem das regras.");
        expInput.setEditable(false);

        Pane root = new Pane();
        root.getChildren().add(btnRun);
        root.getChildren().add(expInput);

        Scene scene = new Scene(root, 450, 200);

        primaryStage.setTitle("Fractal");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnRun.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                ImageProccessing.start();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}