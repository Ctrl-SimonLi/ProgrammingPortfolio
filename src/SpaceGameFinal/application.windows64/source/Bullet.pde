 class Bullet {
  int x, y, z, speed, rad, power;
  color c;
  boolean reachedTop;
  PImage img;
  Bullet(int x, int y) {
    this.x=x;
    this.y=y;
    z= 150;
    speed = 5;
    img = loadImage("bullet.png");
    rad = 1;
  }
  void fire(int speed) {
    y-= speed;
  }
  boolean reachedTop() {
    if (y<-5) {
      return true;
    } else {
      return false;
    }
  }
  void display(int x1) {
    image(img, x1-48, y);
    ;
  }
}
