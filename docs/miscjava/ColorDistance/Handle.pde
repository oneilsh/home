
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
  
  void update() 
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
  
  void over()
  {
    if(overRect(boxx, boxy, hsize, hsize)) {
      over = true;
    } else {
      over = false;
    }
  }
  
  void press()
  {
    if(over && mousePressed || locked) {
      press = true;
      locked = true;
    } else {
      press = false;
    }
  }
  
  void release()
  {
    locked = false;
  }
  
  void display() 
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
  
  boolean isMouseOver()
    {
    return overRect(boxx, boxy, hsize, hsize);
    }
  
  int getLength()
    {
    return hlength;
    }

  void setDisplayText(String text) 
    {
    displayText = text;
    }
    
}

boolean overRect(int x, int y, int width, int height) 
{
  if (mouseX >= x && mouseX <= x+width && 
      mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}


int lock(int val, int minv, int maxv) 
{ 
  return  min(max(val, minv), maxv); 
} 
