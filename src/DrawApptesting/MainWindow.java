package DrawApptesting;

import java.io.InputStreamReader;
import java.io.Reader;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MainWindow extends Application
{ 
    
Group drawing=new Group();   

   private Scene scene;
    private TextArea messageView;
  
    private Canvas image;
    private GraphicsContext gc;
    Stage primarStage; 
    Reader reader;
    Parser parser;
  
    
     private void init(Stage primaryStage ){

        primaryStage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        scene = new Scene(borderPane, 800, 600);
        
        borderPane.setTop(drawing);
        
        messageView = new TextArea();     
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(messageView);
        Button quitButton = new Button("Close Window");
        Button stepButton = new Button("Next Step");
        Button allButton = new Button("Show all");
        borderPane.setCenter(scrollPane);
        borderPane.setLeft(stepButton);
        borderPane.setBottom(allButton);
        borderPane.setRight(quitButton);
        BorderPane.setAlignment(stepButton, Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(allButton, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(quitButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(drawing, new Insets(10));
        
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent e) {
            Platform.exit();
            }
            });
        
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent e) {
          parser.step_by_step();
            }
            });
        
        allButton.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent e) {
                          
           parser.parse();
            }
            });
        
         
      image=new Canvas(500,300);
        drawing.getChildren().add(image);
         gc =image.getGraphicsContext2D();
    
       //  gc.setFill(Color.GREEN);
      //  gc.setStroke(Color.BLUE);
    
        primaryStage.setScene(scene);
        primaryStage.setTitle("DrawApp");
      
    }
       
        
    
    
    public void postMessage(final String s)
    {
        messageView.setText(s);
    }
    
    protected void paintComponent(GraphicsContext gc)
    {
        gc.fillRect(0, 0, image.getWidth(), image.getHeight());   
    }
    
    public void setBackgroundColour(Color colour)
    {
       gc.fillRect(0, 0, image.getWidth(), image.getHeight());   
    }
    
    public void clear(Color colour)
    {
        setBackgroundColour(colour);
    }
    
    public void setColour(Color colour)
    {
       
        gc.setFill(colour);
    }
    public void setGradient(Color colour1,Color colour2){
     RadialGradient rg = new RadialGradient(0,0,0,0,1,true, CycleMethod.NO_CYCLE, new Stop[]{
             new Stop(0,colour1),
             new Stop(1,colour2)
         });
     gc.setFill(rg);
    }
    
    public void displayImage(String url,int x, int y,int width,int height){
        Image img=new Image(url);
        gc.drawImage(img, x, y, width, height);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2)
    {
        gc.strokeLine(x1, y1, x2, y2);
    }
    
    public void drawRect(int x1, int y1, int x2, int y2)
    {
        gc.strokeRect(x1, y1, x2, y2);
    }
    
    public void fillRect(int x1, int y1, int x2, int y2)
    {
        gc.fillRect(x1, y1, x2, y2);
    }
    
     public void fillOval(double x, double y, double w,double h)
    {
        gc.fillOval(x, y, w, h);
       
    }
   
    
    
    public void strokeText(int x, int y, String s)
    {
        gc.strokeText(s, x, y);
    }
    
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
    {
        gc.arc(x, y, width, height, startAngle, arcAngle);
    }
    
    public void drawOval(int x, int y, int width, int height)
    {
        gc.strokeOval(x, y, width, height);
    }
     
     public static void main(String[] args) {
		launch(args);
	}
    
 
	public void start(Stage primaryStage) throws Exception {
       init(primaryStage);  
        reader = new InputStreamReader(System.in);
        parser = new Parser(reader,this);
        primaryStage.show();
           
	}
    
   
}
    
    
