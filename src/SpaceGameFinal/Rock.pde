class Rock {
  int speed, x, y, rad, health; 
  color c;
  PImage img, img2, img3;

  Rock(int x, int y) {
    rad = 50;
    this.x = x;
    this.y = y;
    health = int(random(50, 75));
    speed = 4; 
    img = loadImage("rock1.png");
    img2 = loadImage("rock2.png");
    img3 = loadImage("rock3.png");
  }

  void move(int speed) {
    y += speed;
  }
  
  boolean bulletIntersect(Bullet bullet) {
    float distance = dist(x, y, bullet.x, bullet.y); 
    if (distance < rad + bullet.rad) { 
      return true;
    } else {
      return false;
    }
  }

  boolean reachedBottom() {
    if (y > height + rad*4) { 
      return true;
    } else {
      return false;
    }
  }

  void display() {
    fill(c);
    noStroke();
    if (health < 25){
       image(img3, x-51, y-35);
    }
    if (health >25){
    image( img2, x-51, y-35);
    }
    if (health >50){
    image( img, x-51, y-35);
    }
    fill(255);
  }
}
