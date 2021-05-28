import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SpaceGameFinal extends PApplet {


SoundFile bsound, rsound, pusound;
ArrayList<Bullet> bullets = new ArrayList<Bullet>();
ArrayList<Rock> rocks = new ArrayList<Rock>();
ArrayList<Bonus> bonuses = new ArrayList<Bonus>();
SpaceShip ship;
Timer rockT, bonusT;
int upg, rockR, rockC;
boolean play;
Star[] stars = new Star[100];
public void setup() {
  
  bsound = new SoundFile(this, "bsoundsg.wav");
  rsound = new SoundFile(this, "rockhitsg.wav");
  pusound = new SoundFile(this, "pusoundsg.wav");
  bullets = new ArrayList();
  rocks = new ArrayList();  
  bonuses = new ArrayList();
  rockT = new Timer(rockR);
  bonusT = new Timer(2000);
  rockC = 0;
  rockR = 1000;
  upg = 0;
  for (int i=0; i<stars.length; i++) {
    stars[i] = new Star();
  }
  ship = new SpaceShip();
}
public void draw() {
  noCursor();
  if (!play) {
    startScreen();
  } else {
    if (ship.health<0){
      GMScreen();
    }else{
    background(0);
    if (upg<99){
      frameRate(60);
    }
    if (upg>99){
      frameRate(80);
    }
    if (upg>399){
      frameRate(100);
    }
    if (upg>999){
      frameRate(120);
    }
    //Bullet Logic
        for (int i = 0; i < bullets.size(); i++) {
      Bullet bullet = bullets.get(i);
      bullet.display(bullet.x);
      if (upg > 999) {
        bullet.fire(17);
      }
      if (upg > 399) {
        bullet.fire(10);
      }
      if (upg < 399) {
        bullet.fire(bullet.speed);
      }
      if (bullet.reachedTop()) {
        bullets.remove(bullet);
      }
      for (int j = 0; j<rocks.size(); j++) {
        Rock rock = rocks.get(j);
        if (rock.bulletIntersect(bullet)) {
          bullets.remove(bullet);
          rock.health-=10;
          if (rock.health<1) {
            rocks.remove(rock);
            upg+=10;
          }
        }
      }
    }
    ship.display(mouseX, mouseY);
      infopanel();
    //Background Logic
    for (int i=0; i<stars.length; i++) {
      stars[i].display();
      stars[i].drive();
    }
    //rockT
     if (rockT.isFinished()) {
      rocks.add(new Rock(PApplet.parseInt(random(width)), -40));
      rockC++;
      if (rockR > 200){
      rockR -= 8;
      }
      rockT = new Timer(rockR);
      rockT.start();
    }

    //Rock Logic
    for (int i = 0; i<rocks.size(); i++) {
      Rock rock = rocks.get(i);
      if (upg > 999) {
        rock.move(7);
      }
      if (upg > 99) {
        rock.move(6);
      }
      if (upg < 99) {
        rock.move(rock.speed);
      }
      rock.display();
     
      if (ship.rockIntersect(rock)) {
        ship.health-=rock.health;
        rocks.remove(rock);
        rsound.play();
        if (rock.health<1) {
          rock.health-=1;
          rocks.remove(rock);
        }
      }

      if (rock.reachedBottom()) {
        rsound.play();
        rocks.remove(rock);
        ship.health-=rock.health;
      }
    }
    //BonusTimer
    if (bonusT.isFinished()) {
      bonuses.add(new Bonus(PApplet.parseInt(random(width)), -20));
      bonusT.start();
    }
    //Bonus Logic
      for (int o = 0; o<bonuses.size(); o++) {
      Bonus bonus = bonuses.get(o);
      bonus.move();
      bonus.display();
      if (ship.bIntersect(bonus)) {
        pusound.play();
        if (bonus.bonusf == 1) { // Adds ammo
          ship.ammo+=50;
          bonus.bonusf = PApplet.parseInt(random(1, 3));
        } else if (bonus.bonusf == 2) { //Adds health
          ship.health+=100;
          bonus.bonusf = PApplet.parseInt(random(1, 3));
        }
        bonuses.remove(bonus);
      }
      if (bonus.reachedBottom()) {
        bonuses.remove(bonus);
      }
    }
  }
  }
}
//MouseInput
public void mousePressed() {
  bsound.play();
    if (ship.ammo > 0) {
      if (upg >999) {
        bullets.add(new Bullet(ship.x-20, ship.y));
        bullets.add(new Bullet(ship.x+20, ship.y));
        bullets.add(new Bullet(ship.x-10, ship.y));
        bullets.add(new Bullet(ship.x+10, ship.y));
        bullets.add(new Bullet(ship.x-30, ship.y));
        bullets.add(new Bullet(ship.x+30, ship.y));
        bullets.add(new Bullet(ship.x, ship.y));
      }
      if (upg >399) {
        bullets.add(new Bullet(ship.x-20, ship.y));
        bullets.add(new Bullet(ship.x+20, ship.y));
        bullets.add(new Bullet(ship.x-10, ship.y));
        bullets.add(new Bullet(ship.x+10, ship.y));
      }
      if (upg >99) {
        bullets.add(new Bullet(ship.x-20, ship.y));
        bullets.add(new Bullet(ship.x+20, ship.y));
      }
      if (upg <100) {
        bullets.add(new Bullet(ship.x, ship.y));
      }
      ship.ammo--;
      ship.sp++;
    }
  }
//Start Screen
  public void startScreen() {
    background(0);
    textMode(CENTER);
    text("SpaceGame: The Game", width/2-50, (height/2)-100);
    text("Click to Play", width/2-20, (height/2)-10);
    if (mousePressed) {
      play = true;
    }
  }
 //Start Screen
  public void GMScreen() {
    background(0);
    textMode(CENTER);
    fill(255);
    text("GAMEOVER", width/2-50, (height/2)-40);
    text("Your final UPG/Score was "+ upg, width/2-120, (height/2)-10);
  }
//Infopanel
 public void infopanel() {
    fill(255);
    textMode(LEFT);
    textSize(20);
    text("Health:"+ ship.health, 30, 50);
    text("Ammo:"+ ship.ammo, 150, 50);
    text("UPGStatus:"+ upg, 260, 50);
  }
class Bonus {
  int x, y, speed, rad, bonusf;
  int c;
  PImage img, img2;
  

  Bonus(int x, int y) {
    rad = 30; 
    this.x = x;
    bonusf = PApplet.parseInt(random(1,3));
    this.y = y;
    speed = 4;    
    img = loadImage("ammorefill.png");
    img2 = loadImage("healthrefill.png");
  }

  public void move() {
    y += speed;
  }
  
  public boolean bulletIntersect(Bullet bullet) {
    float distance = dist(x, y, bullet.x, bullet.y); 
    if (distance < rad + bullet.rad) { 
      return true;
    } else {
      return false;
    }
  }

  public boolean reachedBottom() {
    if (y > height + rad*4) { 
      return true;
    } else {
      return false;
    }
  }

  public void display() {
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
 class Bullet {
  int x, y, z, speed, rad, power;
  int c;
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
  public void fire(int speed) {
    y-= speed;
  }
  public boolean reachedTop() {
    if (y<-5) {
      return true;
    } else {
      return false;
    }
  }
  public void display(int x1) {
    image(img, x1-48, y);
    ;
  }
}
class Rock {
  int speed, x, y, rad, health; 
  int c;
  PImage img, img2, img3;

  Rock(int x, int y) {
    rad = 50;
    this.x = x;
    this.y = y;
    health = PApplet.parseInt(random(50, 75));
    speed = 4; 
    img = loadImage("rock1.png");
    img2 = loadImage("rock2.png");
    img3 = loadImage("rock3.png");
  }

  public void move(int speed) {
    y += speed;
  }
  
  public boolean bulletIntersect(Bullet bullet) {
    float distance = dist(x, y, bullet.x, bullet.y); 
    if (distance < rad + bullet.rad) { 
      return true;
    } else {
      return false;
    }
  }

  public boolean reachedBottom() {
    if (y > height + rad*4) { 
      return true;
    } else {
      return false;
    }
  }

  public void display() {
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
  public boolean rockIntersect(Rock rock) {
    float distance = dist(x, y, rock.x, rock.y);
    if (distance <rad + rock.rad) {
      return true;
    } else {
      return false;
    }
  }
  public boolean bIntersect(Bonus bonus) {
    float distance = dist(x, y, bonus.x, bonus.y); 
    if (distance < rad + bonus.rad) { 
      return true;
    } else {
      return false;
    }
  }
  public void display(int x, int y) {
    this.x = x;
    this.y = y;
    image(images[ssindex], x-100, y-50);
    if (frameCount % 8 == 0){
      ssindex = (ssindex+1) % images.length;
    }
  }
}
class Star {
  float xpos, ypos, yspeed;
  Star() {
    xpos = random(width);
    ypos = random(height);
    yspeed = random(1, 3);
  }
  public void display() {
    rectMode(CENTER);
    fill(255);
    noStroke();
    ellipse(xpos, ypos, random(1, 2), random(1, 2));
  }
  public void drive() {
      ypos -= -yspeed;
      if (ypos > height-5) {
        ypos = 0;
        xpos = random(width);
        yspeed = random(1, 4);
      }
    }
  }
// Example 10-5: Object-oriented timer
// By Daniel Shifman | http://learningprocessing.com/examples/chp10/example-10-10-rain-catcher-game

class Timer {

  int savedTime; // When Timer started
  int totalTime; // How long Timer should last

  Timer(int tempTotalTime) {
    totalTime = tempTotalTime;
  }

  // Starting the timer
  public void start() {
    // When the timer starts it stores the current time in milliseconds.
    savedTime = millis();
  }

  // The function isFinished() returns true if 5,000 ms have passed. 
  // The work of the timer is farmed out to this method.
  public boolean isFinished() { 
    // Check how much time has passed
    int passedTime = millis()- savedTime;
    if (passedTime > totalTime) {
      return true;
    } else {
      return false;
    }
  }
}
  public void settings() {  size(600, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SpaceGameFinal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
