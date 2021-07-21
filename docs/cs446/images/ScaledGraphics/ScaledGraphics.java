import java.awt.*;
import java.awt.event.*;

public class ScaledGraphics
{
	private Graphics g;
	private double wxl, wxh, wyl, wyh, vxl, vxh, vyl, vyh, xShift, yShift, xRatio, yRatio;
	private int width;

	private double[][] transform;

	public ScaledGraphics(Graphics gg, int widthfrom)
		{
		g = gg;
		width = widthfrom;
		wxl = 0;
		wyl = 0;
		vxl = 0;
		vyl = 0;
		wxh = 1;
		wyh = 1;
		vxh = 1;
		vyh = 1;
		calcRatios();

		transform = Transform.identity();
		//transform = Transform.translate(0,-10);
		//transform = Transform.stretch(.5,1);
		//transform = Transform.times(Transform.rotate(45), transform);
		//transform = Transform.times(Transform.translate(0,10), transform);
		}

	public void calcRatios()
		{
		xRatio = ((vxh-vxl)/(wxh-wxl)*(width-1));
		yRatio = ((vyh-vyl)/(wyh-wyl)*(width-1));
		xShift = vxl*width - wxl*xRatio;
		yShift = vyl*width - wyl*yRatio;
		}

	public void drawLine(double x1, double y1, double x2, double y2)
		{
		double vector[] = Transform.times(transform, new double[] {x1,y1,1});
		x1 = vector[0];
		y1 = vector[1];
		vector = Transform.times(transform, new double[] {x2,y2,1});
		x2 = vector[0];
		y2 = vector[1];
		g.drawLine((int)((x1*xRatio + xShift)), (int)(width-(y1*yRatio + yShift)), (int)(x2*xRatio + xShift), (int)(width-(y2*yRatio + yShift)));
		}

	public void fillRect(double x1, double y1, double x2, double y2)
		{
		g.fillRect((int)((x1*xRatio + xShift)), (int)(width-(y1*yRatio + yShift)), (int)(x2*xRatio + xShift), (int)(width-(y2*yRatio + yShift)));
		}

	public void setWindow(double xl, double xh, double yl, double yh)
		{
		wxl = xl;
		wxh = xh;
		wyl = yl;
		wyh = yh;
		calcRatios();
		}

	public void setViewPort(double xl, double xh, double yl, double yh)
		{
		vxl = xl;
		vxh = xh;
		vyl = yl;
		vyh = yh;
		calcRatios();
		}

	public void setColor(Color color)
		{
		g.setColor(color);
		}
}