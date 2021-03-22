int x, y;
void setup() {
  size(400, 400);
  frameRate(10);
  strokeWeight(2);
  // Set start coords
  x = 0;
  y = 0;
}
void draw() {
  fill(255);
  //keyPressed();
  //noLoop();
}

// Algorithm for your first name
void drawName() {
}

// Method to draw right line
void moveRight(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x+i, y);
  }
  x=x+(10*rep);
}
void moveDown(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x, y+i);
  }
  y=y+(10*rep);
}
void moveLeft(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x-i, y);
  }
  x=x-(10*rep);
}
void moveUp(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x, y-i);
  }
  y=y-(10*rep);
}
void moveRightDown(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x+i, y+i);
  }
  x=x+(10*rep);
  y=y+(10*rep);
}
void moveRightUp(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x+i, y-i);
  }
  x=x+(10*rep);
  y=y-(10*rep);
}
void moveLeftDown(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x-i, y+i);
  }
  x=x-(10*rep);
  y=y+(10*rep);
}
void moveLeftUp(int rep) {
  for (int i=0; i<rep*10; i++) {
    point(x-i, y-i);
  }
  x=x-(10*rep);
  y=y-(10*rep);
}
void keyPressed() {
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
