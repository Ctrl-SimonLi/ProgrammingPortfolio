class Bonus {
  int x, y, speed, rad, bonusf;
  color c;
  PImage img, img2;
  

  Bonus(int x, int y) {
    rad = 30; 
    this.x = x;
    bonusf = int(random(1,3));
    this.y = y;
    speed = 4;    
    img = loadImage("ammorefill.png");
    img2 = loadImage("healthrefill.png");
  }

  void move() {
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
    if (bonusf ==1){
    //Extra Ammo
    image(img, x-51, y-35);
    }
    if (bonusf ==2){
    //Health Refill
    image(img2, x-51, y-35);
    }
  }
}
