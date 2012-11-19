#include <stdio.h>
#include "graphics.h"

int main(void){

changeDimension(500,500);

//create birds using turtleMode
setPosition(375,100);
turnRight(135);
moveForward(50);
turnLeft(45);
moveForward(50);
turnRight(45);
turnLeft(135);

setPosition(425,70);
turnRight(135);
moveForward(30);
turnLeft(45);
moveForward(30);

setPosition(400,140);
turnRight(135);
moveForward(40);
turnLeft(45);
moveForward(40);

//create the sun
setColour(yellow);
fillOval(0.0,0.0,100.0,100.0);

//create the trees
setGradient("cyan","green");
fillOval(350.0,200.0,80.0,80.0);
fillOval(400.0,200.0,80.0,80.0);
setGradient("orange","red");
fillRect(385,280,10,30);
fillRect(435,280,10,30);

//create the hotel
setGradient("white","darkgray");
fillRect(100,0,200,265);
//create its windows
setColour(black);
int i,a;
int y=10;
for (a=0;a<6;a++){
int x=110;
for (i=0;i<7;i++){
fillRect(x,y,20,30);
x=x+25;}
y=y+40;}

//place the car image
displayImage("http://blog.ivman.com/wp-content/Travelers.jpg",0,230,90,75);
}
