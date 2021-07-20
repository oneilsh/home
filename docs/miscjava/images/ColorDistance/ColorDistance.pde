

  color backColor;
  Button closex;
  Handle[] handles;
  PFont fontA;
  
  ColorField field;
  boolean drawControls;
  boolean drifting;
  boolean skip1FieldRedraw;

    
  void setup()
   {
   fontA = loadFont("AndaleMono-48.vlw");
   textFont(fontA,14);
   
   handles = new Handle[2];     
   handles[0] = new Handle(0, 13, (int)Math.ceil(((Math.log(4.5)*0.75*(double)width)/(Math.log(100.0)))), 10, handles);
   handles[1] = new Handle(0, 28, (int)Math.ceil(((Math.log(2.5)*0.75*(double)width)/(Math.log(100.0)))), 10, handles);
   field = new ColorField(2,4); // initial lnorm,initial pval
   closex = new Button(10,10,25,25,color(200), color(50), false, "X");
  
   skip1FieldRedraw = false;
   drawControls = true;
   int pxsize = (int)Math.pow((width*height)/3600,0.5);
   field.setPxSize(pxsize);
   loop();
   drifting = false;
     
   size(400,400,P3D);
   background(255);
   //smooth();
   frameRate(20);
   //noLoop();
   } 
   
  void draw()
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
      double base = Math.pow(Math.E,Math.log(100.0)/(0.75*(double)width));
      double pval = Math.pow(base,(double)(handles[0].getLength()))-0.5;
      handles[0].setDisplayText("PVAL: " + ((int)(pval*100.0))/100.0);
      double lnorm = Math.pow(base,(double)(handles[1].getLength()))-0.5;
      handles[1].setDisplayText("LNORM: " + ((int)(lnorm*100.0))/100.0);
      field.setPVal(pval);
      field.setLNorm(lnorm);
      if(drifting) field.doDrifts();
      }
    }
 
  void mousePressed()
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
    
    
  void keyPressed()
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
        int pxsize = (int)Math.pow((width*height)/6400,0.5);
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


  void mouseReleased() 
    {
    handles[0].release();
    handles[1].release();
    }


  boolean isOverAnyButton()
    {
    if(!drawControls) return false;
    return(closex.over() || handles[0].isMouseOver() || handles[1].isMouseOver());
    }





