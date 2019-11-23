import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application 
{

	@Override
	public void start(Stage primaryStage) 
	{
		Button btn = new Button();
		btn.setText("Sample Button");
		btn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Some Calculation");
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(btn);

		// Dimensions Width by Height
		Scene scene = new Scene(root, 1200, 600);

		primaryStage.setTitle("Grade Analytics");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}