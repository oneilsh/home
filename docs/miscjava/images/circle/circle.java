import processing.core.*; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; public class circle extends PApplet {



public void setup()
  {
  size(400, 400, P3D);
  background(0);
  noStroke();
  }
  
//I'm not sure why width and height aren't set at this point...
int width = 400; 
int height = 400;

float x = (width/2.0f);
float y = (height/2.0f);
float degrees = 0; 
float circlesize = width * .05f;
float rads = 0;
int blue = 255;
int red = 255;
int green = 255;

// jitter should range between 0 and about .3
// based on the y coordinate
float jitter = .15f;
// speed should range between about -3 and 3, based on the x coordinate
float speed = 1;
 
public void draw()
  {
  if(mousePressed)
    {
    drawDirections();
    if(mouseButton == LEFT)
      {
      speed = (mouseX-(width/2.0f))/66.0f;
      jitter = (mouseY*.00075f);
      }
    else
      {
      green = (int)(mouseX*(255.0f/width));
      red = (int)(mouseY*(255.0f/height));
      }
    }
    
  degrees = degrees + speed;
  if(degrees >= 360) degrees = 0;
  rads = radians(degrees); 
  
  noStroke();
  x = cos(rads) + random(-1*jitter,jitter);
  y = sin(rads) + random(-1*jitter,jitter);
  fill(0,0,0,1);
  rect(0,0,399,399);
  //background(0,0,0,50);
  fill(red,green,blue,100);
  ellipse(100*x+(width/2), 100*y+(width/2), circlesize, circlesize);
  
  
  }

public void drawDirections()
  {
  textAlign(LEFT);
  PFont font;
  font = loadFont("LucidaSans-12.vlw");
  textFont(font, 12); 
    
    
  stroke(100);
  fill(100);
  line(30, height-30, width-30, height-30);
  line(30, height-30,35,height-35);
  line(30, height-30,35,height-25);
  line(width-30, height-30,width-35,height-35);
  line(width-30, height-30,width-35,height-25);
  if(mouseButton == LEFT)
    text("DIRECTION/SPEED", width/2 - 45,width-15);
  else
    text("GREEN AMOUNT", width/2 - 35,width-15);
  
  line(width-30, 30, width-30, height-30);
  line(width-30, 30, width-35, 35);
  line(width-30, 30, width-25, 35);
  line(width-30, height-30, width-35, height-35);
  line(width-30, height-30, width-25, height-35);
  
  pushMatrix();
  translate(width-20, 0);
  rotateZ(PI/2.0f);

  if(mouseButton == LEFT)
    text("JITTER", width/2 - 20, 0);
  else
    text("RED AMOUNT", width/2 - 30, 0);
  popMatrix();
  }
static public void main(String args[]) {   PApplet.main(new String[] { "circle" });}}