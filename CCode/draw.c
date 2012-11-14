#include <stdio.h>
#include "graphics.h"

int main(void){
setColour(green);
fillOval(250.0,150.0,60.0,60.0);
setGradient("gray","darkgray");
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

displayImage("http://profile.ak.fbcdn.net/hprofile-ak-prn1/71059_294960127900_6746764_n.jpg",150,150,70,70);

}
