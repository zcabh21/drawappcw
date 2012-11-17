#include <stdio.h>
#include "graphics.h"


void setPosition(int x,int y){
printf("SP %i %i\n",x,y);
}
void moveForward(int angle){
printf("MF %i\n",angle);
}
void turnLeft(int angle){
printf("TL %i\n",angle);
}
void turnRight(int angle){
printf("TR %i\n",angle);
}

void changeDimension(int width,int height){
printf("CD %i %i\n",width,height);
}
void drawLine(int x1, int x2, int x3, int x4)
{
  printf("DL %i %i %i %i\n", x1, x2, x3, x4);
}

void drawRect(int x1, int x2, int x3, int x4)
{
  printf("DR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawOval(int x, int y, int width, int height)
{
  printf("DO %i %i %i %i\n",x,y,width,height);
}

void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
{
  printf("DA %i %i %i %i %i %i\n",x,y,width,height, startAngle, arcAngle);
}

void fillRect(int x1, int x2, int x3, int x4)
{
  printf("FR %i %i %i %i\n", x1, x2, x3, x4);
}

void fillOval(double x, double y, double w, double h)
{
  printf("FC %f %f %f %f\n", x, y, w, h);
}

void setGradient(char* c1, char* c2)
{
  printf("SG %s %s\n", c1,c2);
}

void displayImage(char* url, int x, int y, int width,int height)
{
  printf("DI %s %i %i %i %i\n", url,x,y, width, height);
}


void drawString(char* s, int x, int y)
{
  printf("DS %i %i @%s\n",x,y,s);
}

void setColour(colour c)
{
  char* colourName;
  switch(c)
  {
    case black : colourName = "black"; break;
    case blue : colourName = "blue"; break;
    case cyan : colourName = "cyan"; break;
    case darkgray : colourName = "darkgray"; break;
    case gray : colourName = "gray"; break;
    case green : colourName = "green"; break;
    case lightgray : colourName = "lightgray"; break;
    case magenta : colourName = "magenta"; break;
    case orange : colourName = "orange"; break;
    case pink : colourName = "pink"; break;
    case red : colourName = "red"; break;
    case white : colourName = "white"; break;
    case yellow : colourName = "yellow"; break;
  }
  printf("SC %s\n", colourName);
}
