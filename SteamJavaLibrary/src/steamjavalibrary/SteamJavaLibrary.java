package steamjavalibrary;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Steam Sales Simulator main class.
 * @author joona, jonnie, joni
 */
public class SteamJavaLibrary extends Application{
    public static Data data;
    /**
     * Runs the loading screen until Data has loaded. Loads main UI afterwards.
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/resources/steamlogo-white-s.png")));
        stage.setTitle("Steam Sales Simulator");
        
        Parent loadroot = FXMLLoader.load(getClass().getResource("loading.fxml"));
        Scene loadingscene = new Scene(loadroot);
        
        stage.centerOnScreen();
        stage.setScene(loadingscene);
        stage.show();
        
        Task<Integer> task = new Task<Integer>() {
            @Override
            public Integer call() {
                data = new Data();
                return 1;
            }
        };
        task.setOnSucceeded(e -> { 
            try{
                Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
                Scene mainscene = new Scene(root);
                stage.setScene(mainscene);
                stage.show();
            }catch(Exception a){
                System.out.println(a);
            }
        });
        new Thread(task).start();
    }
    
    /**
     * The main for the project.
     * Launches the UI.
     * @param args 
     */
    public static void main(String[] args){
        launch(args);
    }
}
