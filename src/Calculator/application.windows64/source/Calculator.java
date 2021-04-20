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

public class Calculator extends PApplet {

Button[] numButtons = new Button[11];
Button[] opButtons = new Button[11];
String dVal = "";
String op = "";
boolean left = true;
float r = 0.0f;
float l = 0.0f;
float result = 0.0f;
int currentNum = 0;
public void setup() {
  
  numButtons[10] = new Button(101, 349, 48, 48, ".", true);
  numButtons[9] = new Button(101, 199, 48, 48, "9", true);
  numButtons[8] = new Button(51, 199, 48, 48, "8", true);
  numButtons[7] = new Button(1, 199, 48, 48, "7", true);
  numButtons[6] = new Button(101, 249, 48, 48, "6", true);
  numButtons[5] = new Button(51, 249, 48, 48, "5", true);
  numButtons[4] = new Button(1, 249, 48, 48, "4", true);
  numButtons[3] = new Button(101, 299, 48, 48, "3", true);
  numButtons[2] = new Button(51, 299, 48, 48, "2", true);
  numButtons[1] = new Button(1, 299, 48, 48, "1", true);
  numButtons[0] = new Button(1, 349, 98, 48, "0", true);
  opButtons[10] = new Button(101, 149, 48, 48, "1/x", false);
  opButtons[9] = new Button(51, 149, 48, 48, "π", false);
  opButtons[8] = new Button(1, 149, 48, 48, "C", false);
  opButtons[7] = new Button(101, 99, 48, 48, "√x", false);
  opButtons[6] = new Button(51, 99, 48, 48, "x²", false);
  opButtons[5] = new Button(1, 99, 48, 48, "+/-", false);
  opButtons[4] = new Button(151, 99, 47, 48, "-", false);
  opButtons[3] = new Button(151, 149, 47, 48, "/", false);
  opButtons[2] = new Button(151, 199, 47, 48, "+", false);
  opButtons[1] = new Button(151, 249, 47, 48, "x", false);
  opButtons[0] = new Button(151, 299, 47, 98, "=", false);
}
public void draw() {
  background(255);
  for (int i=0; i<numButtons.length; i++) {
    numButtons[i].display();
    numButtons[i].hover();
  }
  for (int i=0; i<opButtons.length; i++) {
    opButtons[i].display();
    opButtons[i].hover();
  }
  updateDisplay();
  textSize(27);
}
public void mouseReleased() {
  for (int i=0; i<numButtons.length; i++) {
    if (numButtons[i].hover && dVal.length()<18) {
      handleEvent(numButtons[i].val, true);
    }
  }

  for (int i=0; i<opButtons.length; i++) {
    if (opButtons[i].hover) {
      handleEvent(opButtons[i].val, false);
    }
  }
}
public void keyPressed() {
  println("KEY:" + key + " keyCode:" + keyCode);
  if (dVal.length()<18) {
    if (key == '0') {
      handleEvent("0", true);
    } else if (key == '1') {
      handleEvent("1", true);
    } else if (key == '2') {
      handleEvent("2", true);
    } else if (key == '3') {
      handleEvent("3", true);
    } else if (key == '4') {
      handleEvent("4", true);
    } else if (key == '5') {
      handleEvent("5", true);
    } else if (key == '6') {
      handleEvent("6", true);
    } else if (key == '7') {
      handleEvent("7", true);
    } else if (key == '8') {
      handleEvent("8", true);
    } else if (key == '9') {
      handleEvent("9", true);
    } else if (key == '*') {
      handleEvent("x", false);
    } else if (key == '/') {
      handleEvent("/", false);
    } else if (key == '+') {
      handleEvent("+", false);
    } else if (key == '-') {
      handleEvent("-", false);
    } else if (key == '.') {
      handleEvent(".", false);
    } else if (key == 10) {
      handleEvent("=", false);
    } else if (keyCode == 127) {
      handleEvent("C", false);
    } else if (keyCode == 8) {
      handleEvent("C", false);
    }
  }
}
public void updateDisplay() {
  fill(0);
  stroke(0);
  rect(1, 1, 197, 96);
  fill (255);
  textAlign(RIGHT);
  if (dVal.length()<10) {
    textSize(30);
  } else if (dVal.length()<12) {
    textSize(28);
  } else if (dVal.length()<13) {
    textSize(26);
  } else if (dVal.length()<14) {
    textSize(24);
  } else if (dVal.length()<15) {
    textSize(22);
  } else if (dVal.length()<16) {
    textSize(20);
  } else if (dVal.length()<17) {
    textSize(19);
  } else if (dVal.length()<18) {
    textSize(18);
  }
  text(dVal, width-5, 93);
}
public void performCalc() {
  if (op.equals("+")) {
    result = l + r;
  } else if (op.equals("-")) {
    result = l - r;
  } else if (op.equals("x")) {
    result = l * r;
  } else if (op.equals("/")) {
    result = l / r;
  }
  l = result;
  dVal = str(result);
  left = true;
}
public String handleEvent(String val, boolean num) {
  if (left && num) {
    if (dVal.equals("0") || result == l) {
      dVal = (val);
      l = PApplet.parseFloat(dVal);
    } else { 
      dVal += (val);
      l = PApplet.parseFloat(dVal);
    }
  } else if (!left && num) {
    if (dVal.equals("0") || result == l) {
      dVal = (val);
      r = PApplet.parseFloat(dVal);
    } else { 
      dVal += (val);
      r = PApplet.parseFloat(dVal);
    }
  } else if (val.equals("C")) {
    dVal = "0";
    result = 0.0f;
    left = true;
    r = 0.0f;
    l = 0.0f;
    op = "";
    if (left) {
      dVal=dVal.substring(0, dVal.length()-1);
    }
  } else if (val.equals("+")) {
    if (!left) {
      performCalc();
    } else {
      op = "+";
      left = false;
      dVal = "0";
    }
  } else if (val.equals("-")) {
    op = "-";
    left = false;
    dVal = "0";
  } else if (val.equals("x")) {
    op = "x";
    left = false;
    dVal = "0";
  } else if (val.equals("/")) {
    op = "/";
    left = false;
    dVal = "0";
  } else if (val.equals("+/-")) {
    if (left) {
      l *= -1;
      dVal = str(l);
    } else {
      r *= -1;
      dVal = str(r);
    }
  } else if (val.equals("x²")) {
    if (left) {
      l = sq(l);
      dVal = str(l);
    } else {
      r = sq(r);
      dVal = str(r);
    }
  } else if (val.equals("√x")) {
    if (left) {
      l = sqrt(l);
      dVal = str(l);
    } else {
      r = sqrt(r);
      dVal = str(r);
    }
  } else if (val.equals("1/x")) {
    if (left) {
      l = (1/l);
      dVal = str(l);
    } else {
      r = (1/r);
      dVal = str(r);
    }
  } else if (val.equals("π")) {
    if (left) {
      l = (3.1415f);
      dVal = str(l);
    } else {
      r = (3.1415f);
      dVal = str(r);
    }
  } else if (val.equals(".") && !dVal.contains(".")) {
    dVal += (val);
  } else if (val.equals("=")) {
    performCalc();
  }
  return val;
}
class Button {
  boolean hover, isNumber;
  int x, y, w, h;
  String val;
  int c1, c2, c3, c4; 
  Button(int tempX, int tempY, int tempW, int tempH, String tempTXT, boolean isNumber) {
    x = tempX;
    y = tempY;
    w = tempW;
    h = tempH;
    val = tempTXT;
    c1 = 0xff000000;
    c2 = 0xff313131;
    c3 = 0xffFF811A;
    c4 = 0xffCE6D1D;
    hover = false;
    this.isNumber = isNumber;
  }
  public void display() {
    if (isNumber == true) {
      if (hover) {
        fill(c2);
        stroke(c2);
      } else {
        fill(c1);
        stroke(c1);
      }
      rect(x, y, w, h);
      fill(255);
      textSize(15);
      textAlign(CENTER);
      text(val, x+w/2, y+5+h/2);
    }else{
      if (hover) {
        fill(c4);
        stroke(c4);
      } else {
        fill(c3);
        stroke(c3);
      }
      rect(x, y, w, h);
      fill(0);
      textSize(15);
      textAlign(CENTER);
      text(val, x+w/2, y+5+h/2);}
  }
  public void hover() {
    hover = mouseX > x && mouseX < x+w && mouseY > y && mouseY < y+h;
  }
}
  public void settings() {  size(200, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Calculator" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
