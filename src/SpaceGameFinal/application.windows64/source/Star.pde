class Star {
  float xpos, ypos, yspeed;
  Star() {
    xpos = random(width);
    ypos = random(height);
    yspeed = random(1, 3);
  }
  void display() {
    rectMode(CENTER);
    fill(255);
    noStroke();
    ellipse(xpos, ypos, random(1, 2), random(1, 2));
  }
  void drive() {
      ypos -= -yspeed;
      if (ypos > height-5) {
        ypos = 0;
        xpos = random(width);
        yspeed = random(1, 4);
      }
    }
  }
