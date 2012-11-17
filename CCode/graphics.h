enum colour {black,brown, blue,cyan,darkgray,gray,green,lightgray,magenta,orange,pink,red,white,yellow};
typedef enum colour colour;

void drawLine(int,int,int,int);
void drawRect(int,int,int,int);
void drawOval(int,int,int,int);
void drawArc(int,int,int,int,int,int);
void fillRect(int,int,int,int);
void drawString(char*,int,int);
void displayImage(char*,int,int,int,int);
void fillOval(double,double,double,double);
void setGradient(char*,char*);
void setColour(colour);
void changeDimension(int,int);
void setPosition(int,int);
void moveForward(int);
void turnLeft(int);
void turnRight(int);
