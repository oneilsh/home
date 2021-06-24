

class Button 
  {
  int buttonColor;
  int checkedColor;
  boolean isChecked;
  String buttonText;
  int x,y,dx,dy;
  
  Button(int ix, int iy, int idx, int idy, color ibuttonColor, color icheckedColor, boolean iisChecked, String ibuttonText)
    {
    x = ix; y = iy; dx = idx; dy = idy;
    buttonColor = ibuttonColor;
    checkedColor = icheckedColor;
    isChecked = iisChecked;
    buttonText = ibuttonText;
    }

  
  void display()
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
    text(buttonText, x+(int)(dx/2.0)+2,y+(int)(dy/2.0)+6);
    }
  
  
  void setIsChecked(boolean checked) { isChecked = checked; }
  
  boolean over()
    {
    if(x <= mouseX && mouseX <= x+dx && y <= mouseY && mouseY <= y+dy) return true;
    return false;
    }
  
  
  }
