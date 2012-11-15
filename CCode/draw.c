#include <stdio.h>
#include "graphics.h"

int main(void){

//changeDimensions(400,400);
setColour(yellow);
fillOval(0.0,0.0,100.0,100.0);
setGradient("white","darkgray");
fillRect(100,0,200,265);

setGradient("cyan","green");
fillOval(350.0,200.0,80.0,80.0);
fillOval(400.0,200.0,80.0,80.0);
setGradient("orange","red");
fillRect(385,280,10,30);
fillRect(435,280,10,30);

setColour(black);
int i,a;
int y=10;
for (a=0;a<6;a++){
int x=110;
for (i=0;i<7;i++){
fillRect(x,y,20,30);
x=x+25;

}
y=y+40;
}
displayImage("http://blog.ivman.com/wp-content/Travelers.jpg",0,230,90,75);

}
