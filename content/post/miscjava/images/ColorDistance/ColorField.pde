import java.util.ArrayList;

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


  void setLNorm(double ilnorm) {lnorm = ilnorm;}
  void setPVal(double ipval) {pval = ipval;}
  void setPxSize(int ipxsize) {pxsize = ipxsize;}
  
  void resetList()
    {
    xyPointList = new ArrayList();
    }
  
  int getListSize()
    {
    return xyPointList.size();
    }
  
  void addPoint(int x, int y, color pointColor)
    {
    xyPointList.add(new XYPoint(x,y,pointColor));
    }
  
  void display()
    {
      for(int x = 0; x <= width; x = x+pxsize)
        for(int y = 0; y <= height; y = y+pxsize)
          {
          double totalDistance = 0.0;
          double r = (xyPointList.size() > 0) ? 0.0 : 255.0;
          double g = (xyPointList.size() > 0) ? 0.0 : 255.0;
          double b = (xyPointList.size() > 0) ? 0.0 : 255.0;
          for(int i = 0; i < xyPointList.size(); i++)
            {
            XYPoint thisPoint = new XYPoint(x,y,color(0));
            XYPoint listPoint = (XYPoint)xyPointList.get(i);
            double distance = getDistance(thisPoint, listPoint);
            r = r + (1.0/Math.pow(distance,pval))*(double)red(listPoint.getColor());
            g = g + (1.0/Math.pow(distance,pval))*(double)green(listPoint.getColor());  
            b = b + (1.0/Math.pow(distance,pval))*(double)blue(listPoint.getColor());
          
            totalDistance = totalDistance + 1.0/Math.pow(distance,pval);
            }
          double finalR = r/totalDistance;
          double finalG = g/totalDistance;
          double finalB = b/totalDistance;
          fill( (int)finalR, (int)finalG, (int)finalB );
          noStroke();
          rect(x-(int)(pxsize/2.0),y-(int)(pxsize/2.0),pxsize,pxsize);
          }
    }
    
  void randomizeDrifts()
    {
    for(int i = 0; i < xyPointList.size(); i++)
      {
      XYPoint xypoint = (XYPoint)xyPointList.get(i);
      xypoint.randomizeDrift(); 
      }
    }
    
  void doDrifts()
    {
    for(int i = 0; i < xyPointList.size(); i++)
      {
      XYPoint xypoint = (XYPoint)xyPointList.get(i);
      xypoint.doDrift(); 
      }
    }
    
  double getDistance(XYPoint one, XYPoint two)
    {
    double x1 = one.getX();
    double y1 = one.getY();
    double x2 = two.getX();
    double y2 = two.getY();
    double firstInside = Math.pow(Math.abs(x1-x2),lnorm);
    double secondInside = Math.pow(Math.abs(y1-y2),lnorm);
    double distance = Math.pow(firstInside+secondInside,1.0/lnorm);
    if(distance == 0.0) distance = 0.1;
    return distance;
    }
    
  }
