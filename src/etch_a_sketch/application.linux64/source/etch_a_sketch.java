import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class etch_a_sketch extends PApplet {

int x, y;
public void setup() {
  
  frameRate(10);
  strokeWeight(2);
  // Set start coords
  x = 0;
  y = 0;
}
public void draw() {
  fill(255);
  //keyPressed();
  //noLoop();
}

// Algorithm for your first name
public void drawName() {
}

// Method to draw right line
public void moveRight(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x+i, y);
  }
  x=x+(10*rep);
}
public void moveDown(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x, y+i);
  }
  y=y+(10*rep);
}
public void moveLeft(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x-i, y);
  }
  x=x-(10*rep);
}
public void moveUp(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x, y-i);
  }
  y=y-(10*rep);
}
public void moveRightDown(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x+i, y+i);
  }
  x=x+(10*rep);
  y=y+(10*rep);
}
public void moveRightUp(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x+i, y-i);
  }
  x=x+(10*rep);
  y=y-(10*rep);
}
public void moveLeftDown(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x-i, y+i);
  }
  x=x-(10*rep);
  y=y+(10*rep);
}
public void moveLeftUp(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x-i, y-i);
  }
  x=x-(10*rep);
  y=y-(10*rep);
}
public void keyPressed() {
  if (key == '2') {
    moveDown(2);
  } else if (key == '4') {
    moveLeft(2);
  } else if (key == '6') {
    moveRight(2);
  } else if (key == '8') {
    moveUp(2);
  } else if (key == '1') {
    moveLeftDown(2);
  } else if (key == '3') {
    moveRightDown(2);
  } else if (key == '7') {
    moveLeftUp(2);
  } else if (key == '9') {
    moveRightUp(2);
  } else if (key == 'c') {
    background(200);
  }else if (key == 's'){
    saveFrame("line-######.png");
  }
}
  public void settings() {  size(400, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "etch_a_sketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
