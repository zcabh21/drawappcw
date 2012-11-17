package DrawApptesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Parser 
{
    private BufferedReader reader; 
   
    
    private Main frame;
 
    
    public Parser(Reader reader, Main frame)
    {
        this.reader = new BufferedReader(reader);
 
        this.frame = frame;
        
       
    }
    
    public void parse()
    {
        try
        {
            String line = reader.readLine();
            while(line!=null)
            {
                parseLine(line);
                line = reader.readLine();
            }
 
        }
 
        catch(IOException e)
        {
            frame.postMessage("Parse failed.");
            return;
        }
        
       catch(ParseException e)
        {
            frame.postMessage("Parse Exception: " + e.getMessage());
            return;
        } 
        frame.postMessage("Drawing is completed.");
    }
    
    
    public int step_by_step(){
      try
        {
            String line = reader.readLine();
           if(line!=null)
            {
                frame.postMessage("More lines to come!");
           parseLine(line);
            }
           else{
               frame.postMessage("End.");
                return -1;   
           }
        }
        catch(IOException e)
        {
            frame.postMessage("Parse failed.");
            return 0;
        }
        
       catch(ParseException e)
        {
            frame.postMessage("Parse Exception: " + e.getMessage());
            return 0;
        } 
       frame.postMessage("This step is completed.");
       return 0;
    
    
}
    
    private void parseLine(String line) throws ParseException
    {
        if (line.length() < 2) return;
        
        String command = line.substring(0, 2);
        
        if(command.equals("SP")){ //turtle
          
            setPosition(line.substring(2,line.length())); 
            return;
        }
        
          if(command.equals("MF")){ //turtle
          
            moveForward(line.substring(2,line.length())); 
            return;
        }
            if(command.equals("TL")){ //turtle
          
            turnLeft(line.substring(2,line.length())); 
            return;
        }
              if(command.equals("TR")){ //turtle
          
            turnRight(line.substring(2,line.length())); 
            return;
        }
          
        if (command.equals("DL"))
        {
            drawLine(line.substring(2,line.length())); 
            return; 
        }
        
        if (command.equals("DR"))
        {
            drawRect(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("FR"))
        {
            fillRect(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("SC"))
        {
               
            setColour(line.substring(3, line.length()));
            return;
        }
        
        if (command.equals("DS"))
        {
            drawString(line.substring(3, line.length()));
            return;
        }
        
        if (command.equals("DA"))
        {
            drawArc(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("DO"))
        {
            drawOval(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("FC"))
        {
            fillOval(line.substring(2, line.length()));
            return;
        }
        if (command.equals("DI"))
        {
            displayImage(line.substring(2, line.length()));
            return;
        }
        
        if (command.equals("SG"))
        {
               
            setGradient(line.substring(2, line.length()));
            return;
        }
         if (command.equals("CD"))
        {
           changeDimension(line.substring(2, line.length()));
            return;
        }
        
        
        throw new ParseException("Unknown drawing command");
    }
    
    
    
    private void drawLine(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        
       frame.drawLine(x1,y1,x2,y2);
    }
    
    private void drawRect(String args) throws ParseException
    {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        StringTokenizer tokenizer = new StringTokenizer(args);
        
        x1 = getInteger(tokenizer);
        y1 = getInteger(tokenizer);
        x2 = getInteger(tokenizer);
        y2 = getInteger(tokenizer);
        
       frame.drawRect(x1,y1,x2,y2);
    }
    
      private void fillRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    frame.fillRect(x1, y1, x2, y2);
  }
      
      private void fillOval(String args) throws ParseException
  {
   double x = 0.0;
    double y = 0.0;
    double w = 0.0;
    double h = 0.0;
    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getDouble(tokenizer);
    y = getDouble(tokenizer);
    w = getDouble(tokenizer);
     h = getDouble(tokenizer);
   frame.fillOval(x,y, w,h);
  }

  private void drawArc(String args) throws ParseException
  {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int startAngle = 0;
    int arcAngle = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    startAngle = getInteger(tokenizer);
    arcAngle = getInteger(tokenizer);
    frame.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  private void drawOval(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int width = 0;
    int height = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    frame.drawOval(x1, y1, width, height);
  }

  private void drawString(String args) throws ParseException
  {
    int x = 0;
    int y = 0 ;
    String s = "";
    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    int position = args.indexOf("@");
    if (position == -1) throw new ParseException("DrawString string is missing");
    s = args.substring(position+1,args.length());
    frame.strokeText(x,y,s);
  }
  
  private void setGradient(String args) throws ParseException{
   
     String colour1="";
     String colour2="";
     StringTokenizer tokenizer=new StringTokenizer(args);
     colour1=getString(tokenizer);
     colour2=getString(tokenizer);
    frame.setGradient(getColour(colour1),getColour(colour2));     
   }
  
  private void displayImage(String args) throws ParseException{
  String url="";
  int x=0;
  int y=0;
  int width=0;
  int height=0;
  StringTokenizer tokenizer=new StringTokenizer(args);
  url=getString(tokenizer);
  x=getInteger(tokenizer);
  y=getInteger(tokenizer);
  width=getInteger(tokenizer);
  height=getInteger(tokenizer);
  frame.displayImage(url, x, y, width, height);
  }
    private Color getColour(String colourName) throws ParseException{
        switch (colourName){
        case ("black"): return (Color.BLACK);
        case ("blue"): return (Color.BLUE);
        case ("cyan"): return (Color.CYAN);
        case("darkgray"): return (Color.DARKGRAY);
        case("gray"): return (Color.GRAY);
        case("green"): return Color.GREEN;
        case("lightgray"): return Color.LIGHTGRAY;
        case("magenta"): return Color.MAGENTA;
        case("orange"): return Color.ORANGE;
        case("pink"): return Color.PINK;
        case("red"): return Color.RED;
        case("white"): return Color.WHITE;
        case("yellow"): return Color.YELLOW;
        default:throw new ParseException("Invalid colour name in getColour");
        }
    }
    
    private void changeDimension(String args) throws ParseException{
    int width=-1;
    int height=-1;
 StringTokenizer tokenizer=new StringTokenizer(args);
 width=getInteger(tokenizer);
 height=getInteger(tokenizer);
   if((width<0)||(height<0)) {
            throw new ParseException ("Invalid width or height.");
        }
  
    frame.changeDimensions(width,height);   
    
    } 
  public void setPosition(String args) throws ParseException{
  int x=0;
  int y=0;
  StringTokenizer tokenizer=new StringTokenizer(args);  
  x=getInteger(tokenizer);
  y=getInteger(tokenizer);
  frame.getTurtle().setPosition(x, y);
  
    }
     public void turnLeft(String args) throws ParseException{
        int angle=0;
          StringTokenizer tokenizer=new StringTokenizer(args);  
angle=getInteger(tokenizer);
   frame.getTurtle().left(angle);
    }
      public void turnRight(String args) throws ParseException{
          int angle=0;
          StringTokenizer tokenizer=new StringTokenizer(args);  
angle=getInteger(tokenizer);
   frame.getTurtle().right(angle);
          
    }
    public void moveForward(String args) throws ParseException{
  int distance=0;
  StringTokenizer tokenizer=new StringTokenizer(args);  
  distance=getInteger(tokenizer);
   frame.getTurtle().moveForward(distance);
      }
   
    
    
    private void setColour(String colourName) throws ParseException
{
 
    
if(colourName.equals("black"))
{
frame.setColour(Color.BLACK);
return;
}

if(colourName.equals("blue"))
{
frame.setColour(Color.BLUE);
return;
}

if(colourName.equals("cyan"))
{
frame.setColour(Color.CYAN);
return;
}

if(colourName.equals("darkgray"))
{
frame.setColour(Color.DARKGRAY);
return;
}

if(colourName.equals("gray"))
{
frame.setColour(Color.GRAY);
return;
}

if(colourName.equals("green"))
{
frame.setColour(Color.GREEN);
return;
}

if(colourName.equals("lightgray"))
{
frame.setColour(Color.LIGHTGRAY);
return;
}

if(colourName.equals("magenta"))
{
frame.setColour(Color.MAGENTA);
return;
}

if(colourName.equals("orange"))
{
frame.setColour(Color.ORANGE);
return;
}

if(colourName.equals("pink"))
{
frame.setColour(Color.PINK);
return;
}

if(colourName.equals("red"))
{
frame.setColour(Color.RED);
return;
}

if(colourName.equals("white"))
{
frame.setColour(Color.WHITE);
return;
}

if(colourName.equals("yellow"))
{
frame.setColour(Color.YELLOW);
return;
}

throw new ParseException("Invalid colour name in setColour");
}
     
    private int getInteger(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens()) {
            return Integer.parseInt(tokenizer.nextToken());
        }
        else {
            throw new ParseException("Missing Integer value");
        }
  }
    
      private Double getDouble(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens()){
           
        return Double.parseDouble(tokenizer.nextToken());}
        else {
            throw new ParseException("Missing Double value");
        }
  }
    
    private String getString(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens()) {
            return tokenizer.nextToken();
        }
        else {
            throw new ParseException("Missing String value");
        }
  }
    
}
