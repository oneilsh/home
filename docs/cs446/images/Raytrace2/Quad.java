import java.awt.*;
import java.awt.event.*;

public class Quad
	{
	private double A, B, C, D, E, F, G, H, I, J;
	private Color brightColor, shadeColor;
	private boolean sunspot;
	private double reflect;

	public Quad(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j)
		{
		reflect = 0;
		sunspot = false;
		A = a;
		B = b;
		C = c;
		D = d;
		E = e;
		F = f;
		G = g;
		H = h;
		I = i;
		J = j;
		}

	public double getSmallT(double eye[], double dir[])
		{
		//System.out.println(""+eye[0]+" "+eye[1]+" "+eye[2]);
		//System.out.println(""+dir[0]+" "+dir[1]+" "+dir[2]);
		double a = A*dir[0]*dir[0] + B*dir[1]*dir[1]+C*dir[2]*dir[2] +D*dir[1]*dir[2]+E*dir[0]*dir[2] +F*dir[0]*dir[1];
		double b = 2*(A*eye[0]*dir[0] +B*eye[1]*dir[1] +C*eye[2]*dir[2])+ D*(eye[1]*dir[2] + eye[2]*dir[1]) +E*(eye[0]*dir[2] +eye[2]*dir[0]) + F*(eye[0]*dir[1] + eye[1]*dir[0]) + G*dir[0] + H*dir[1] + I*dir[2];
		double c = A*eye[0]*eye[0] + B*eye[1]*eye[1] +C*eye[2]*eye[2] +D*eye[1]*eye[2] +E*eye[0]*eye[2] +F*eye[0]*eye[1] + G*eye[0] + H*eye[1] + I*eye[2] + J;
		double disc = (b*b) - (4*a*c);
		//System.out.println(disc);//""+a+" "+b+" "+c);
		if(disc < 0)
			return -1;
		double t1 = (-b - Math.sqrt(disc))/(2*a);
		double t2 = (-b + Math.sqrt(disc))/(2*a);
		if(t1 <= t2)
			return t1;
		return t2;
		}

	public double[] getNormal(double t, double dir[], double eye[])
		{
		double point[] = new double[3];
		point[0] = eye[0] +t*dir[0];
		point[1] = eye[1] +t*dir[1];
		point[2] = eye[2] +t*dir[2];
		double normal[] = new double[3];
		normal[0] = 2*A*point[0] + E*point[2] +F*point[1] +G;
		normal[1] = 2*B*point[1] + D*point[2] +F*point[0] + H;
		normal[2] = 2*C*point[2] + D*point[1] +E*point[0] + I;
		double dot = normal[0]*dir[0] + normal[1]*dir[1] + normal[2]*dir[2];
		if(dot < 0)
			{
			normal[0] = - normal[0];
			normal[1] = - normal[1];
			normal[2] = - normal[2];
			}
		double magnitude = Math.sqrt(normal[0]*normal[0] + normal[1]*normal[1] + normal[2]*normal[2]);
		normal[0] = normal[0]/magnitude;
		normal[1] = normal[1]/magnitude;
		normal[2] = normal[2]/magnitude;
		return normal;
		}

	public void translate(double dx, double dy, double dz)
		{
		J = J + (dx*dx)*A + (dy*dy)*B + (dz*dz)*C + dy*dz*D + dx*dz*E + dx*dy*F - dx*G - dy*H - dz*I;
		G = G - 2*dx*A - dz*E - dy*F;
		H = H - 2*dy*B - dz*D - dx*F;
		I = I - 2*dz*C - dy*D - dx*E;
		}

	public void rotateZ(double angle)
		{
		double a = A;
		double b = B;
		double c = C;
		double d = D;
		double e = E;
		double f = F;
		double g = G;
		double h = H;
		double i = I;
		double j = J;
		double cos = Math.cos(angle/180);
		double sin = Math.sin(angle/180);
		A = a*cos*cos + b*sin*sin + f*sin*cos;
		B = a*sin*sin + b*cos*cos - f*sin*cos;
		D = d*cos - e*sin;
		E = d*sin + e*cos;
		F = 2*f*(cos*cos - sin*sin);
		G = g*cos + h*sin;
		H = -g*sin + h*cos;
		}

	public void rotateX(double angle)
		{
		double a = A;
		double b = B;
		double c = C;
		double d = D;
		double e = E;
		double f = F;
		double g = G;
		double h = H;
		double i = I;
		double j = J;
		double cos = Math.cos(angle/180);
		double sin = Math.sin(angle/180);
		B = b*cos*cos + c*sin*sin - d*sin*cos;
		C = b*sin*sin + c*cos*cos + d*sin*cos;
		E = e*cos + f*sin;
		F = f*cos - e*sin;
		D = d*(cos*cos - sin*sin) + 2*sin*cos*(b-c);
		H = h*cos - i*sin;
		I = i*cos + h*sin;
		}

	public void rotateY(double angle)
		{
		double a = A;
		double b = B;
		double c = C;
		double d = D;
		double e = E;
		double f = F;
		double g = G;
		double h = H;
		double i = I;
		double j = J;
		double cos = Math.cos(angle/180);
		double sin = Math.sin(angle/180);
		C = c*cos*cos + a*sin*sin - e*sin*cos;
		A = c*sin*sin + a*cos*cos + e*sin*cos;
		F = f*cos + d*sin;
		D = d*cos - f*sin;
		E = e*(cos*cos - sin*sin) + 2*sin*cos*(c-a);
		I = i*cos - g*sin;
		G = g*cos + i*sin;
		}

	public void expand(double x, double y, double z)
		{
		A /= x*x;
		B /= y*y;
		C /= z*z;
		D /= y*z;
		E /= x*z;
		F /= x*y;
		G /= x;
		H /= y;
		I /= z;
		}

	public void setSunSpot(boolean value)
		{
		sunspot = value;
		}

	public boolean getSunSpot()
		{
		return sunspot;
		}

	public void setReflect(double value)
		{
		reflect = value;
		}

	public double getReflect()
		{
		return reflect;
		}

	public void setBrightColor(Color bright)
		{
		brightColor = bright;
		}

	public void setShadeColor(Color shade)
		{
		shadeColor = shade;
		}

	public Color getBrightColor()
		{
		return brightColor;
		}

	public Color getShadeColor()
		{
		return shadeColor;
		}

	private int convert(String s)
			{
			try
				{
				return (new Integer(s)).intValue();
				}
			catch (Throwable t)
				{
				return 50;
				}
			}

	private double convertdouble(String s)
			{
			try
				{
				return (new Double(s)).doubleValue();
				}
			catch (Throwable t)
				{
				return .7;
				}
			}
	}