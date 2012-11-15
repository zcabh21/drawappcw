package DrawApptesting;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
import javax.imageio.ImageIO;

public class Main extends Application
{ 
    Group drawing; 
    private HBox hb;
    private Scene scene;
    private TextArea messageView;
    private Canvas image;
    private GraphicsContext gc;
    Stage primaryStage; 
    Reader reader;
    Parser parser;
    private int WIDTH=500;
    private int HEIGHT=300;
    
         private void init(Stage primaryStage ){

             drawing=new Group();
        primaryStage.setResizable(true);
        BorderPane borderPane = new BorderPane();
        scene = new Scene(borderPane, WIDTH+300, HEIGHT+300);
        
        borderPane.setTop(drawing);
        
        messageView = new TextArea();     
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(messageView);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        BorderPane.setMargin(messageView,new Insets(0));
         
        Button quitButton = new Button("Close Window");
        Button stepButton = new Button("Next Step");
        Button allButton = new Button("Show all");
        Button save=new Button("Save");
        borderPane.setCenter(scrollPane);
        BorderPane.setMargin(drawing, new Insets(100));
       hb= new HBox(15);
        hb.getChildren().addAll(stepButton,allButton,save,quitButton);
        borderPane.setBottom(hb);
        hb.setAlignment(Pos.CENTER);
        
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
        
        save.setOnAction(new EventHandler<ActionEvent>() {
   
            @Override
            public void handle(ActionEvent e) {
                try {
                    saveDrawing();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
               postMessage("Image is saved.");
            }
            });
        
         
      image=new Canvas(WIDTH,HEIGHT);
        drawing.getChildren().add(image);
         gc =image.getGraphicsContext2D();
  
        primaryStage.setScene(scene);
        primaryStage.setTitle("DrawApp");
      
    }
       
        
    
    
    public void postMessage(final String s)
    {
              messageView.setText(s);
                  
    }
    
  /*  public void turtle(int x,int y, double angle){
    startAt(x,y,angle);
    forward(angle);
    left(angle);
    right(angle);
    pen_up();
    pen_down();
    }
    */
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
     
  public void changeDimensions(double width, double height){

      gc.scale(width, height);
      //image.setHeight(height);
      //image.setWidth(width);
     //drawing.prefHeight(height);
      //drawing.prefWidth(width);
     // drawing.resize(width, height);
    //  drawing.maxHeight(height);
    //  drawing.maxWidth(width);
//drawing.setScaleX(width);  
//drawing.setScaleY(height);
messageView.setPrefHeight(height-100);
messageView.setPrefWidth(width);
hb.setPrefHeight(height-50);
hb.setPrefWidth(width);

   }
    

public void saveDrawing() throws IOException{
try{
ImageIO.write(SwingFXUtils.fromFXImage(image.snapshot(null, null), null),"jpg",new File("Image"));
        }
catch(IOException e){
    e.printStackTrace();
}
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
       parser = new Parser(reader,this,primaryStage);
        primaryStage.show();
           
	}
    
   
}
    
    
