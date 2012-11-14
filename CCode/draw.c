#include <stdio.h>
#include "graphics.h"

int main(void){

/*
void drawLine(int,int,int,int);
void drawRect(int,int,int,int);
void drawOval(int,int,int,int);
void drawArc(int,int,int,int,int,int);
void fillRect(int,int,int,int);
void drawString(char*,int,int);
void setColour(colour);*/

setColour(gray);
fillRect(0,0,200,500);
setColour(black);
int i,a;

int y=10;
for (a=0;a<7;a++){
int x=10;
for (i=0;i<7;i++){
fillRect(x,y,20,30);
x=x+25;

}
y=y+40;
}
}
