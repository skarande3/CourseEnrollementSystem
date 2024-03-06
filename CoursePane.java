// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Shravan Karande
//    StudentID: 1225888172
//      Lecture: TTH 10:30 AM
//  Description: This is the class in which we import statements to use certain classes and interfaces. 
//  It has significant attributes such as courseList, checkboxContainer and other GUI components. 
// In this class we create the layout of the assignment, update the check box handler (the main VBox), 
// provide exceptions, and Handlers.  




//Note: when you submit on gradescope, you need to comment out the package line
//package yourPackageName;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Collection;

public class CoursePane extends HBox
{
    //GUI components
    private ArrayList<Course> courseList;
    private VBox checkboxContainer;
    private ArrayList<CheckBox> CkeckBoxList; 

    //Step 1.1: declare any necessary instance variables here
    //----
    private Label labelsubject;
    private Label lablecourseno;
    private Label lableinstructor; 
    private Label lableinfo;
    private Label lableinfo2;
 
    private Button buttonAdd;
    private Button buttonDrop;
 
    private TextField textfieldcourseno;
    private TextField textfieldinstructor; 
    
    ComboBox<String> combosubject; 
    
    
    //constructor
    public CoursePane()
    {
        //step 1.2: initialize instance variables
         labelsubject = new Label("Subject");
         lablecourseno = new Label("Course Num"); 
         lableinstructor = new Label("Instructor");
         lableinfo = new Label("No course entered");
         lableinfo2 = new Label("Total course enrolled: 0"); 
        
        checkboxContainer = new VBox();
        courseList = new ArrayList<Course>();
         buttonAdd = new Button("Add =>");
         buttonDrop = new Button("Drop <="); 
        
         CkeckBoxList = new ArrayList<CheckBox>(); 
         
         textfieldcourseno = new TextField();
         textfieldinstructor = new TextField();
        
        combosubject = new ComboBox<String>();
        combosubject.getItems().addAll("ACC", "AME", "BME", "CHM", "CSE", "DAT", "EEE"); 
        combosubject.setValue("CSE"); 

        Label labelLeft = new Label("Add Course(s)");
        labelLeft.setTextFill(Color.BLUE);
        labelLeft.setFont(Font.font(null, 14));

        Label labelRight = new Label("Course(s) Enrolled");
        labelRight.setTextFill(Color.BLUE);
        labelRight.setFont(Font.font(null, 14));

        //set up the layout. Note: CoursePane is a HBox and contains
        //leftPane, centerPane and rightPane. Pick proper layout class
        //and use nested sub-pane if necessary, then add each GUI components inside.

        //step 1.3: create and set up the layout of the leftPane, leftPane contains a top label, the center sub-pane
        //and a label show at the bottom
        
          
        GridPane leftcenterPane = new GridPane();
        leftcenterPane.setAlignment(Pos.CENTER);
        leftcenterPane.add(labelsubject, 0, 0); 
        leftcenterPane.add(combosubject, 1, 0);
        leftcenterPane.add(lablecourseno, 0, 1); 
        leftcenterPane.add(textfieldcourseno, 1, 1);
        leftcenterPane.add(lableinstructor, 0, 2);
        leftcenterPane.add(textfieldinstructor, 1, 2);
        
        BorderPane leftpane = new BorderPane();
        leftpane.setTop(labelLeft);
        leftpane.setBottom(lableinfo);
        
        leftpane.setCenter(leftcenterPane); 
        
        //step 1.4: create and set up the layout of the centerPane which holds the two buttons
        GridPane centerPane = new GridPane();
        centerPane.setAlignment(Pos.CENTER);
        
        centerPane.add(buttonAdd, 0, 0);
        centerPane.add(buttonDrop, 0, 1);

        //step 1.5: create and set up the layout of the rightPane, rightPane contains a top label,
        //checkboxContainer and a label show at the bottom
        BorderPane rightPane = new BorderPane();
        
        rightPane.setTop(labelRight);
        rightPane.setBottom(lableinfo2);
        rightPane.setCenter(checkboxContainer);

        //CoursePane is a HBox. Add leftPane, centerPane and rightPane inside
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(leftpane, centerPane, rightPane); 
        //This is for the borders 
        leftpane.setStyle("-fx-border-color: Black;");
        centerPane.setStyle("-fx-border-color: Black;");
        rightPane.setStyle("-fx-border-color: Black;");        

        //Step 3: Register the GUI component with its handler class
        ButtonHandler btnHandler = new ButtonHandler();
        buttonAdd.setOnAction(btnHandler); 
        buttonDrop.setOnAction(btnHandler);
        Course course = new Course();
		combosubject.setOnAction(new CheckBoxHandler(course));
        
    } //end of constructor

    //step 2.1: Whenever a new Course is added or one or several courses are dropped/removed, this method will
   
    public void updateCheckBoxContainer()
    {
    //1) clear the original checkboxContainer;
        checkboxContainer.getChildren().clear();
        CkeckBoxList.clear();
    //2) create a CheckBox for each Course object inside the courseList, and also add it inside the checkboxContainer;
        for (int i =0; i < courseList.size(); i++) {
        	CheckBox check = new CheckBox(courseList.get(i).toString());
        	checkboxContainer.getChildren().addAll(check); 
        	CkeckBoxList.add(check);
    //3) register the CheckBox with the CheckBoxHandler.
        	check.setOnAction(new CheckBoxHandler(courseList.get(i)));
            
        
        }
        
    }

    //Step 2.2: Create a ButtonHandler class
    private class ButtonHandler implements EventHandler<ActionEvent>
    {  
//This determines what to do when the Button "buttonAdd" and "buttonDrop" is clicked on
		public void handle(ActionEvent e)
        {

			try {
	 int MIN_VALUE = -2147483648; 
	 boolean isCourseNew = true;
	// everything is entered correctly and the "Add =>" button is pressed
	 if (e.getSource() == buttonAdd && textfieldcourseno.getText().length() > 0 && (Integer.parseInt(textfieldcourseno.getText()) >= MIN_VALUE) && textfieldinstructor.getText().length() > 0) //everything is entered correctly and the "Ad) //everything is entered correctly and the "Add =>" button is pressed
		{
		 // This was done to check where the code was at.       
		 //lableinfo.setText("It's in the 1st if statement"); //need to check whether the course already exist inside the courseList or not;
		 //lableinfo.setText("Course added successfully");
		 
		 //need to check whether the course already exist inside the courseList or not
   	 for(int i =0; i < courseList.size(); i++) {
   		 if (((courseList.get(i).getCourseNum()) == Integer.parseInt(textfieldcourseno.getText())) && ((courseList.get(i).getSubject()) == combosubject.getValue())) {
   			 isCourseNew = false;
   			//This was done to check where the code was at. 
   			 //lableinfo.setText("It's in the for loop");
   		 }
   	 }	 
		
	
   	   if (isCourseNew == true )
   	   {
         	//This was done to check where the code was at. 
   		   //lableinfo2.setText("It's in the new course");
   		
   		   //it's a new course   
   		   Course course = new Course (combosubject.getValue(), Integer.parseInt(textfieldcourseno.getText()), textfieldinstructor.getText()); 
			   courseList.add(course); 
			   lableinfo.setText("Course added successfully" );
			   lableinfo.setTextFill(Color.BLACK);
			   lableinfo2.setText("Total course enrolled: " + (courseList.size()));
			   lableinfo2.setTextFill(Color.BLACK);
			   updateCheckBoxContainer();
			  
			   
		   
   	   }
		   else //a duplicated one
		   {
			 //show error message
			   lableinfo.setText("Dublicated course - Not added");
			   lableinfo.setTextFill(Color.RED);
			   
			  
		   }
   	//Clear all the text fields and prepare for the next entry;
   	   textfieldcourseno.clear(); 
	      textfieldinstructor.clear();
       }
	       
      
      

     else if (e.getSource() == buttonAdd && (textfieldcourseno.getText().length() == 0 || textfieldinstructor.getText().length() == 0))
   	   //">=" button is pressed, but one of the text field is empty
     {
    	 lableinfo.setText("At least one feild is empty. Fill all feilds"); 
    	 lableinfo.setTextFill(Color.RED);

     }

   else if (e.getSource() == buttonDrop)//when "Drop Course" button is pushed.)
   {
	   
	   ArrayList<Course> CopyCourseList = new ArrayList<Course>();
	   CopyCourseList = (ArrayList)courseList.clone();
	   
	   for(int iterateI = 0; iterateI < CopyCourseList.size(); iterateI++) {
		 
		   if(CkeckBoxList.get(iterateI).isSelected()) {
			  
	  for(int iterateJ = 0; iterateJ < courseList.size(); iterateJ++) {
		   
		  if(CopyCourseList.get(iterateI) == courseList.get(iterateJ)) {
			  courseList.remove(iterateJ); 
			//This was done to see where the code is
			  //  lableinfo.setText(lableinfo.getText()+"\ndeleted obj: courseList:"+courseList.size());
			  lableinfo2.setText("Total course enrolled: " + (courseList.size()));
			  break;
			  /*
			   *this code is triggered when a "Drop Course" button is pushed. The code creates a copy of an ArrayList called "courseList" using the clone() 
			   *method and stores it in a new ArrayList called "CopyCourseList". It then iterates through each element of the "CopyCourseList" ArrayList using a for loop.
Within the for loop, the code checks whether a corresponding checkbox in a list of checkboxes (called "CkeckBoxList") is selected using the isSelected() method. If the 
checkbox is selected, the code enters a nested for loop that iterates through the elements of the original "courseList" ArrayList using another for loop.
Within the nested for loop, the code compares the element in "CopyCourseList" that corresponds to the selected checkbox with each element in the original 
"courseList" using the "==" operator. If the elements are equal, the code removes the element from "courseList" using the remove() method, which modifies the original "courseList" ArrayList.


			   */
		  }
	  }
		   }
		   
	   }
	   updateCheckBoxContainer();
	   
	   
		   
   }
   else  //  for all other invalid actions, thrown an exception and it will be caught
   {
   
/*          	
        Not using this statement because exceptions are already dealt with.  
*/		}

} //end of try

catch(NumberFormatException ex)
{
	lableinfo.setText("Error! Course number must be a integer");
	lableinfo.setTextFill(Color.RED);
}
catch(Exception exception)
{
	
}

            
        } //end of handle() method
     }//end of ButtonHandler class

    //Step 2.3: A ComboBoxHandler
    private class ComboBoxHandler implements EventHandler<ActionEvent>
    {
    	 public void handle(ActionEvent e)
         {
    		 
         }  

    }//end of ComboBoxHandler

    //Step 2.4: A CheckBoxHandler
    private class CheckBoxHandler implements EventHandler<ActionEvent>
    {
        // Pass in a Course object so that we know which course is associated with which CheckBox
        private Course oneCourse;

        
        //constructor
        public CheckBoxHandler(Course oneCourse)
        {
           this.oneCourse = oneCourse;//----
        }
        public void handle(ActionEvent e)
        {
        /*	
        		
        		//----
        	}
        }*/
    }//end of CheckBoxHandler*/
    }
} //end of CoursePane class