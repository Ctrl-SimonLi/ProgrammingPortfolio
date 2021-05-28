import processing.sound.*;
SoundFile bsound, rsound, pusound;
ArrayList<Bullet> bullets = new ArrayList<Bullet>();
ArrayList<Rock> rocks = new ArrayList<Rock>();
ArrayList<Bonus> bonuses = new ArrayList<Bonus>();
SpaceShip ship;
Timer rockT, bonusT;
int upg, rockR, rockC;
boolean play;
Star[] stars = new Star[100];
void setup() {
  size(600, 800);
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
void draw() {
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
      rocks.add(new Rock(int(random(width)), -40));
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
      bonuses.add(new Bonus(int(random(width)), -20));
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
          bonus.bonusf = int(random(1, 3));
        } else if (bonus.bonusf == 2) { //Adds health
          ship.health+=100;
          bonus.bonusf = int(random(1, 3));
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
void mousePressed() {
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
  void startScreen() {
    background(0);
    textMode(CENTER);
    text("SpaceGame: The Game", width/2-50, (height/2)-100);
    text("Click to Play", width/2-20, (height/2)-10);
    if (mousePressed) {
      play = true;
    }
  }
 //Start Screen
  void GMScreen() {
    background(0);
    textMode(CENTER);
    fill(255);
    text("GAMEOVER", width/2-50, (height/2)-40);
    text("Your final UPG/Score was "+ upg, width/2-120, (height/2)-10);
  }
//Infopanel
 void infopanel() {
    fill(255);
    textMode(LEFT);
    textSize(20);
    text("Health:"+ ship.health, 30, 50);
    text("Ammo:"+ ship.ammo, 150, 50);
    text("UPGStatus:"+ upg, 260, 50);
  }
