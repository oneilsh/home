import processing.core.*; import java.util.ArrayList; import java.applet.*; import java.awt.*; import java.awt.image.*; import java.awt.event.*; import java.io.*; import java.net.*; import java.text.*; import java.util.*; import java.util.zip.*; import javax.sound.midi.*; import javax.sound.midi.spi.*; import javax.sound.sampled.*; import javax.sound.sampled.spi.*; import java.util.regex.*; import javax.xml.parsers.*; import javax.xml.transform.*; import javax.xml.transform.dom.*; import javax.xml.transform.sax.*; import javax.xml.transform.stream.*; import org.xml.sax.*; import org.xml.sax.ext.*; import org.xml.sax.helpers.*; public class ColorDistance extends PApplet {

  int backColor;
  Button closex;
  Handle[] handles;
  PFont fontA;
  
  ColorField field;
  boolean drawControls;
  boolean drifting;
  boolean skip1FieldRedraw;

    
  public void setup()
   {
   fontA = loadFont("AndaleMono-48.vlw");
   textFont(fontA,14);
   
   handles = new Handle[2];     
   handles[0] = new Handle(0, 13, (int)Math.ceil(((Math.log(4.5f)*0.75f*(double)width)/(Math.log(100.0f)))), 10, handles);
   handles[1] = new Handle(0, 28, (int)Math.ceil(((Math.log(2.5f)*0.75f*(double)width)/(Math.log(100.0f)))), 10, handles);
   field = new ColorField(2,4); // initial lnorm,initial pval
   closex = new Button(10,10,25,25,color(200), color(50), false, "X");
  
   skip1FieldRedraw = false;
   drawControls = true;
   int pxsize = (int)Math.pow((width*height)/3600,0.5f);
   field.setPxSize(pxsize);
   loop();
   drifting = false;
     
   size(400,400,P3D);
   background(255);
   //smooth();
   frameRate(20);
   //noLoop();
   } 
   
  public void draw()
    {
    field.display(); 
    if(field.getListSize() == 0)
      {
      fill(0);
      textAlign(CENTER);
      text("Click to add points...", width/2,height/2);
      text("Nice Render On/Off:  R", width/2,height/2+20);
      text("Clear Points:        C", width/2,height/2+40);
      text("Drift:               D", width/2,height/2+60);
      }
    if(drawControls)
      {
      //closex.display();
      handles[0].update(); handles[0].display();
      handles[1].update(); handles[1].display();
      double base = Math.pow(Math.E,Math.log(100.0f)/(0.75f*(double)width));
      double pval = Math.pow(base,(double)(handles[0].getLength()))-0.5f;
      handles[0].setDisplayText("PVAL: " + ((int)(pval*100.0f))/100.0f);
      double lnorm = Math.pow(base,(double)(handles[1].getLength()))-0.5f;
      handles[1].setDisplayText("LNORM: " + ((int)(lnorm*100.0f))/100.0f);
      field.setPVal(pval);
      field.setLNorm(lnorm);
      if(drifting) field.doDrifts();
      }
    }
 
  public void mousePressed()
    {
    if(!isOverAnyButton())
      {
      int randR = (int)random(256);
      int randG = (int)random(256);
      int randB = (int)random(256);
      field.addPoint(mouseX,mouseY,color(randR,randG,randB));  
      }
        
    redraw();
    }
    
    
  public void keyPressed()
    {
    if(key == 'c' || key == 'C') // C is to clear the points added
      {
      field.resetList();
      background(255);
      }
    else if(key == 'd' || key == 'D' && drawControls) // D toggles drifting
      {
      drifting = !drifting;
      if(drifting) field.randomizeDrifts();
      }
    else if(key == 'r' || key == 'R') // Toggle render mode (hide controls, render fine, turn off drifting)
      {
      drawControls = !drawControls;
      if(drawControls)
        {
        int pxsize = (int)Math.pow((width*height)/6400,0.5f);
        field.setPxSize(pxsize);
        loop();
        }
      else
        {
        field.setPxSize(1);
        noLoop();
        skip1FieldRedraw = true;
        }
      }
    else if(key == 'q')
      {
      redraw();
      }
    field.display(); // Why do I have to call field.display instead of redraw() here?
    redraw(); // We need this for the R key to work apparenlty
    }


  public void mouseReleased() 
    {
    handles[0].release();
    handles[1].release();
    }


  public boolean isOverAnyButton()
    {
    if(!drawControls) return false;
    return(closex.over() || handles[0].isMouseOver() || handles[1].isMouseOver());
    }








class Button 
  {
  int buttonColor;
  int checkedColor;
  boolean isChecked;
  String buttonText;
  int x,y,dx,dy;
  
  Button(int ix, int iy, int idx, int idy, int ibuttonColor, int icheckedColor, boolean iisChecked, String ibuttonText)
    {
    x = ix; y = iy; dx = idx; dy = idy;
    buttonColor = ibuttonColor;
    checkedColor = icheckedColor;
    isChecked = iisChecked;
    buttonText = ibuttonText;
    }

  
  public void display()
    {
    stroke(0);
    strokeWeight(2);
    if(!isChecked) noStroke();
    //if(isChecked) //fill(checkedColor);s
    fill(buttonColor);
    rect(x,y,dx,dy);
    PFont fontA = loadFont("AndaleMono-14.vlw");
    fill(0);
    textAlign(CENTER);
    textFont(fontA,14);
    text(buttonText, x+(int)(dx/2.0f)+2,y+(int)(dy/2.0f)+6);
    }
  
  
  public void setIsChecked(boolean checked) { isChecked = checked; }
  
  public boolean over()
    {
    if(x <= mouseX && mouseX <= x+dx && y <= mouseY && mouseY <= y+dy) return true;
    return false;
    }
  
  
  }



class ColorField
  {
  ArrayList xyPointList;
  double lnorm;
  double pval;
  int pxsize;
  
  ColorField(double ilnorm, double ipval)
    {
    pxsize = 1;
    lnorm = ilnorm;
    pval = ipval;
    xyPointList = new ArrayList();
    }


  public void setLNorm(double ilnorm) {lnorm = ilnorm;}
  public void setPVal(double ipval) {pval = ipval;}
  public void setPxSize(int ipxsize) {pxsize = ipxsize;}
  
  public void resetList()
    {
    xyPointList = new ArrayList();
    }
  
  public int getListSize()
    {
    return xyPointList.size();
    }
  
  public void addPoint(int x, int y, int pointColor)
    {
    xyPointList.add(new XYPoint(x,y,pointColor));
    }
  
  public void display()
    {
      for(int x = 0; x <= width; x = x+pxsize)
        for(int y = 0; y <= height; y = y+pxsize)
          {
          double totalDistance = 0.0f;
          double r = (xyPointList.size() > 0) ? 0.0f : 255.0f;
          double g = (xyPointList.size() > 0) ? 0.0f : 255.0f;
          double b = (xyPointList.size() > 0) ? 0.0f : 255.0f;
          for(int i = 0; i < xyPointList.size(); i++)
            {
            XYPoint thisPoint = new XYPoint(x,y,color(0));
            XYPoint listPoint = (XYPoint)xyPointList.get(i);
            double distance = getDistance(thisPoint, listPoint);
            r = r + (1.0f/Math.pow(distance,pval))*(double)red(listPoint.getColor());
            g = g + (1.0f/Math.pow(distance,pval))*(double)green(listPoint.getColor());  
            b = b + (1.0f/Math.pow(distance,pval))*(double)blue(listPoint.getColor());
          
            totalDistance = totalDistance + 1.0f/Math.pow(distance,pval);
            }
          double finalR = r/totalDistance;
          double finalG = g/totalDistance;
          double finalB = b/totalDistance;
          fill( (int)finalR, (int)finalG, (int)finalB );
          noStroke();
          rect(x-(int)(pxsize/2.0f),y-(int)(pxsize/2.0f),pxsize,pxsize);
          }
    }
    
  public void randomizeDrifts()
    {
    for(int i = 0; i < xyPointList.size(); i++)
      {
      XYPoint xypoint = (XYPoint)xyPointList.get(i);
      xypoint.randomizeDrift(); 
      }
    }
    
  public void doDrifts()
    {
    for(int i = 0; i < xyPointList.size(); i++)
      {
      XYPoint xypoint = (XYPoint)xyPointList.get(i);
      xypoint.doDrift(); 
      }
    }
    
  public double getDistance(XYPoint one, XYPoint two)
    {
    double x1 = one.getX();
    double y1 = one.getY();
    double x2 = two.getX();
    double y2 = two.getY();
    double firstInside = Math.pow(Math.abs(x1-x2),lnorm);
    double secondInside = Math.pow(Math.abs(y1-y2),lnorm);
    double distance = Math.pow(firstInside+secondInside,1.0f/lnorm);
    if(distance == 0.0f) distance = 0.1f;
    return distance;
    }
    
  }


class Handle
{
  int x, y;
  int boxx, boxy;
  int hlength;
  int hsize;
  boolean over;
  boolean press;
  boolean locked = false;
  boolean otherslocked = false;
  String displayText;
  Handle[] others;
  
  Handle(int ix, int iy, int il, int is, Handle[] o)
  {
    displayText = "0";
    x = ix;
    y = iy;
    hlength = il;
    hsize = is;
    boxx = x+hlength - hsize/2;
    boxy = y - hsize/2;
    others = o;
  }
  
  public void update() 
  {
    boxx = x+hlength;
    boxy = y - hsize/2;
    
    for(int i=0; i<others.length; i++) {
      if(others[i].locked == true) {
        otherslocked = true;
        break;
      } else {
        otherslocked = false;
      }  
    }
    
    if(otherslocked == false) {
      over();
      press();
    }
    
    if(press) {
      hlength = lock(mouseX-hsize/2, 0, width-hsize);
    }
  }
  
  public void over()
  {
    if(overRect(boxx, boxy, hsize, hsize)) {
      over = true;
    } else {
      over = false;
    }
  }
  
  public void press()
  {
    if(over && mousePressed || locked) {
      press = true;
      locked = true;
    } else {
      press = false;
    }
  }
  
  public void release()
  {
    locked = false;
  }
  
  public void display() 
  {
      fill(0);
      textAlign(LEFT);
      text(displayText, x+hlength+hsize+2,y+hsize/2);
    stroke(0);
    line(x, y, x+hlength, y);
    fill(255);
    stroke(0);
    rect(boxx, boxy, hsize, hsize);
    if(over || press) {
      line(boxx, boxy, boxx+hsize, boxy+hsize);
      line(boxx, boxy+hsize, boxx+hsize, boxy);
    }

  }
  
  public boolean isMouseOver()
    {
    return overRect(boxx, boxy, hsize, hsize);
    }
  
  public int getLength()
    {
    return hlength;
    }

  public void setDisplayText(String text) 
    {
    displayText = text;
    }
    
}

public boolean overRect(int x, int y, int width, int height) 
{
  if (mouseX >= x && mouseX <= x+width && 
      mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}


public int lock(int val, int minv, int maxv) 
{ 
  return  min(max(val, minv), maxv); 
} 



class XYPoint
  {
  double x,y;
  double xdrift, ydrift;
  int pointColor;
  
  XYPoint(int ix, int iy, int ipointColor)
    {
    x = ix;
    y = iy;
    pointColor = ipointColor;
    xdrift = 1;
    ydrift = 1;
    }
  
  public double getX() {return x;}
  public double getY() {return y;}
  public int getColor() {return pointColor;}
  
  public void randomizeDrift()
    {
    xdrift = (double)random(-1.0f*(max(width,height)/100.0f),max(width,height)/100.0f);
    ydrift = (double)random(-1.0f*(max(width,height)/100.0f),max(width,height)/100.0f);
    }
  
  public void doDrift()
    {
    x = x+xdrift;
    y = y+ydrift;
    if(x >= width || x <= 0) xdrift = -1.0f*xdrift;
    if(y >= height || y <= 0) ydrift = -1.0f*ydrift;
    }
  
  }

  static public void main(String args[]) {     PApplet.main(new String[] { "ColorDistance" });  }}