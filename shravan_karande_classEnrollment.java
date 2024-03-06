// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Shravan Karande
//    StudentID: 1225888172
//      Lecture: TTH 10:30 AM
//  Description: This the main class of this assignment. It includes the Start method, Stage and Scene objects to display the assignment. 

//Note: when you submit on gradescope, you need to comment out the package line
//package yourPackageName;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Assignment6 extends Application
{
    public static final int WIDTH = 600, HEIGHT = 400;

    public void start(Stage stage)
    {
        StackPane root = new StackPane();
        CoursePane coursePane = new CoursePane();
        root.getChildren().add(coursePane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("ASU Course Enrollment System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}