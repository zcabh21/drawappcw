package DrawApptesting;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class Main extends Application
{ 
   
    private HBox hb;
    private Scene scene;
    private TextArea messageView;
    private Canvas image;
    private ScrollPane newcanvas;
    private BorderPane borderPane;
    private GraphicsContext gc;
    private Stage primaryStage; 
    private Reader reader;
    private Parser parser;
    private int WIDTH=500;
    private int HEIGHT=300;
    private Turtle turtle;
           
    private void init(Stage primaryStage ){
        primaryStage.setResizable(true);
        borderPane = new BorderPane();
        scene = new Scene(borderPane, WIDTH+300, HEIGHT+300);          
       
        image=new Canvas(WIDTH,HEIGHT);
        gc =image.getGraphicsContext2D(); 
        turtle=new Turtle(gc);
        
        newcanvas = new ScrollPane();
        newcanvas.setContent(image);
        newcanvas.setFitToHeight(true);
        newcanvas.setFitToWidth(true);
       
        borderPane.setCenter(newcanvas);
        BorderPane.setMargin(newcanvas,new Insets(10));     
                
        Button quitButton = new Button("Close Window");
        Button stepButton = new Button("Next Step");
        Button allButton = new Button("Show all");
        Button save=new Button("Save");
        
        messageView = new TextArea();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(messageView);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
        hb= new HBox(15);
        hb.getChildren().addAll(stepButton,allButton,save,quitButton);
        hb.setAlignment(Pos.CENTER);
        
        FlowPane fp=new FlowPane();
        fp.setVgap(10);
        fp.setHgap(5);
        fp.setMaxHeight(210);
        fp.setOrientation(Orientation.VERTICAL);
        fp.getChildren().addAll(messageView,hb);
        
        borderPane.setBottom(fp);
        fp.setAlignment(Pos.CENTER);   
        
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
               postMessage("Image is saved in the CCode folder.");
            }
            });
         
        primaryStage.setScene(scene);
        primaryStage.setTitle("DrawApp");
    }
    
    public void postMessage(final String s)
    {
              messageView.setText(s);          
    }
    
   public Turtle getTurtle(){
       return turtle;
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
         postMessage("The colour changed to:"+colour);
    }
    
    public void setGradient(Color colour1,Color colour2){
     RadialGradient rg = new RadialGradient(0,0,0,0,1,true, CycleMethod.NO_CYCLE, new Stop[]{
             new Stop(0,colour1),
             new Stop(1,colour2)
         });
     gc.setFill(rg);
     postMessage("The gradient changed to: "+colour1+" with "+colour2);
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
      image.setHeight(height);
      image.setWidth(width);
    }
    
  public void saveDrawing() throws IOException{
    try{    
        ImageIO.write(SwingFXUtils.fromFXImage(image.snapshot(null, null), null),"png",new File("Image"+System.currentTimeMillis()));
        }
    catch(IOException e){
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
    
    @Override
    public void start(Stage primaryStage) throws Exception {
         init(primaryStage); 
         reader = new InputStreamReader(System.in);
         parser = new Parser(reader,this);
         primaryStage.show();
    } 
}
    
    
