package DrawApptesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;
import javafx.scene.paint.Color;

public class Parser 
{
    private BufferedReader reader; 

    private MainWindow frame;
    
    public Parser(Reader reader, MainWindow frame)
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

		/*String[] commands = {"DR 0 0 100 50"};

		for(String s : commands) {
			parseLine(s);
		}*/
           
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
        frame.postMessage("Drawing completed in parse");
    }
    
    
    public int step_by_step(){
      try
        {
            System.out.println("I enter the try");
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
        frame.postMessage("Drawing completed in step");
       return 0;
    
    
}
    
    private void parseLine(String line) throws ParseException
    {
        if (line.length() < 2) return;
        
        String command = line.substring(0, 2);
        
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
                 
      throw new ParseException("Invalid colour name");
  }
     
    private int getInteger(StringTokenizer tokenizer) throws ParseException
    {
        if (tokenizer.hasMoreTokens())
            return Integer.parseInt(tokenizer.nextToken());
        else
            throw new ParseException("Missing Integer value");
  }
    
}
