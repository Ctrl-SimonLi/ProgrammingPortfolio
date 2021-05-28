class SpaceShip {
  int x, y, z, health, ammo, lives, sp, rad;
  char displaymode;
  PImage[] images = new PImage[3];
  int ssindex= 0;

  SpaceShip() {
    x= 200;
    y= 600;
    z= 150;
    sp= 0;
    health = 500;
    ammo = 100;
    lives = 3;
    rad = 7;
    for (int i = 0; i < images.length; i ++ ) {
      images[i] = loadImage( "spaceship" + i + ".png" );
    }
  }
  boolean rockIntersect(Rock rock) {
    float distance = dist(x, y, rock.x, rock.y);
    if (distance <rad + rock.rad) {
      return true;
    } else {
      return false;
    }
  }
  boolean bIntersect(Bonus bonus) {
    float distance = dist(x, y, bonus.x, bonus.y); 
    if (distance < rad + bonus.rad) { 
      return true;
    } else {
      return false;
    }
  }
  void display(int x, int y) {
    this.x = x;
    this.y = y;
    image(images[ssindex], x-100, y-50);
    if (frameCount % 8 == 0){
      ssindex = (ssindex+1) % images.length;
    }
  }
}
