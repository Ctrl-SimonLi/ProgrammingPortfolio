class Button {
  boolean hover, isNumber;
  int x, y, w, h;
  String val;
  color c1, c2, c3, c4; 
  Button(int tempX, int tempY, int tempW, int tempH, String tempTXT, boolean isNumber) {
    x = tempX;
    y = tempY;
    w = tempW;
    h = tempH;
    val = tempTXT;
    c1 = #000000;
    c2 = #313131;
    c3 = #FF811A;
    c4 = #CE6D1D;
    hover = false;
    this.isNumber = isNumber;
  }
  void display() {
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
  void hover() {
    hover = mouseX > x && mouseX < x+w && mouseY > y && mouseY < y+h;
  }
}
