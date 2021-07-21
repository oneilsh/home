import java.awt.*;
import java.awt.event.*;

public class ScaledGraphics
{
	private Graphics g;
	private double wxl, wxh, wyl, wyh, vxl, vxh, vyl, vyh, xShift, yShift, xRatio, yRatio;
	private int width;
	private TStack tstack;

	private double[][] trans;

	public ScaledGraphics(Graphics gg, int widthfrom)
		{
		tstack = new TStack();
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
		trans = Transform.identity();
		}

	public boolean isVisible(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3)
		{
		double prime1[] = Transform.times(tstack.peek(), new double[] {x1,y1,z1,1});
		double prime2[] = Transform.times(tstack.peek(), new double[] {x2,y2,z2,1});
		double prime3[] = Transform.times(tstack.peek(), new double[] {x3,y3,z3,1});
		double dx1 = prime2[0] - prime1[0];
		double dx2 = prime3[0] - prime2[0];
		double dy1 = prime2[1] - prime1[1];
		double dy2 = prime3[1] - prime2[1];
		return(dx1*dy2 - dx2*dy1 > 0);
		}

	public void calcRatios()
		{
		xRatio = ((vxh-vxl)/(wxh-wxl)*(width-1));
		yRatio = ((vyh-vyl)/(wyh-wyl)*(width-1));
		xShift = vxl*width - wxl*xRatio;
		yShift = vyl*width - wyl*yRatio;
		}

	public void pushIdentity()
		{
		tstack.push(Transform.identity());
		}

	public void pop()
		{
		tstack.pop();
		}

	public void setTrans()
		{
		trans = tstack.peek();
		}

	public void merge()
		{
		modify(tstack.data[tstack.top-1]);
		}

	public void clear()
		{
		tstack.clear();
		}

	public void modify(double matrix[][])
		{
		tstack.data[tstack.top] = Transform.times(matrix, tstack.data[tstack.top]);
		}

	public void drawLine(double x1, double y1, double x2, double y2)
		{
		double vector[] = Transform.times(tstack.peek(), new double[] {x1,y1,0,1});
		x1 = vector[0];
		y1 = vector[1];
		vector = Transform.times(tstack.peek(), new double[] {x2,y2,0,1});
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