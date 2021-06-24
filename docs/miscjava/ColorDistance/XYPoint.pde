

class XYPoint
  {
  double x,y;
  double xdrift, ydrift;
  color pointColor;
  
  XYPoint(int ix, int iy, color ipointColor)
    {
    x = ix;
    y = iy;
    pointColor = ipointColor;
    xdrift = 1;
    ydrift = 1;
    }
  
  double getX() {return x;}
  double getY() {return y;}
  color getColor() {return pointColor;}
  
  void randomizeDrift()
    {
    xdrift = (double)random(-1.0*(max(width,height)/100.0),max(width,height)/100.0);
    ydrift = (double)random(-1.0*(max(width,height)/100.0),max(width,height)/100.0);
    }
  
  void doDrift()
    {
    x = x+xdrift;
    y = y+ydrift;
    if(x >= width || x <= 0) xdrift = -1.0*xdrift;
    if(y >= height || y <= 0) ydrift = -1.0*ydrift;
    }
  
  }
