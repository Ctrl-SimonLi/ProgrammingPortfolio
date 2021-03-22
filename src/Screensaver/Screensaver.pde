float x, y, strokew, pointCount, g1;

void setup() {
  size(1000, 1000);
  x=random(width);
  y=random(height);
  frameRate(500);
  background(0);
}
void draw() {
  strokew= random(1,3);
  pointCount= random(2, 15);
  if (x>width || y>height || x<0|| y<0) {
    x=random(width);
    y=random(height);
    stroke(random(180), random(180), random(255));
  } else {
    g1 = random(100);
    if (g1>75) {
      strokeWeight(strokew);
      moveRight(x, y, pointCount);
    } else if (g1>50) {
      strokeWeight(strokew);
      moveUp(x, y, pointCount);
    } else if (g1>25) {
      strokeWeight(strokew);
      moveDown(x, y, pointCount);
    } else if (g1>=0){
      strokeWeight(strokew);
      moveLeft(x, y, pointCount);
    }
  }
}
void moveRight(float startx, float starty, float moveCount) {
  for (float i=0; i<moveCount; i++) {
    
    point(startx+i, starty);
    x = startx+i;
    y = starty;
  }
}
void moveDown(float startx, float starty, float moveCount) {
  for (float i=0; i<moveCount; i++) {
    point(startx, starty+i);
    x = startx;
    y = starty+i;
  }
}
void moveUp(float startx, float starty, float moveCount) {
  for (float i=0; i<moveCount; i++) {
    point(startx, starty-i);
    x = startx;
    y = starty-i;
  }
}
void moveLeft(float startx, float starty, float moveCount) {
  for (float i=0; i<moveCount; i++) {
    point(startx-i, starty);
    x = startx-i;
    y = starty;
  }
}
