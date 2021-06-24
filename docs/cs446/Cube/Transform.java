import java.awt.*;
import java.awt.event.*;

public class Transform
	{
	public static double[][] identity()
		{
		double m[][] = new double[4][4];
		for(int x = 0; x < 4; x++)
			{
			m[x][x] = 1;
			}
		return m;
		}

	public static double[][] zRotate(double angle)
		{
		double m[][] = identity();
		double c = Math.cos(angle*Math.PI/180);
		double s = Math.sin(angle*Math.PI/180);
		m[0][0] = m[1][1] = c;
		m[1][0] = s;
		m[0][1] = -s;
		return m;
		}

	public static double[][] xRotate(double angle)
		{
		double m[][] = identity();
		double c = Math.cos(angle*Math.PI/180);
		double s = Math.sin(angle*Math.PI/180);
		m[1][1] = m[2][2] = c;
		m[2][1] = -s;
		m[1][2] = s;
		return m;
		}

	public static double[][] yRotate(double angle)
		{
		double m[][] = identity();
		double c = Math.cos(angle*Math.PI/180);
		double s = Math.sin(angle*Math.PI/180);
		m[0][0] = m[2][2] = c;
		m[2][0] = s;
		m[0][2] = -s;
		return m;
		}

	public static double[][] translate(double dx, double dy, double dz)
		{
		double m[][] = identity();
		m[0][3] = dx;
		m[1][3] = dy;
		m[2][3] = dz;
		return m;
		}

	public static double[][] stretch(double rx, double ry, double rz)
		{
		double m[][] = identity();
		m[0][0] = rx;
		m[1][1] = ry;
		m[2][2] = rz;
		return m;
		}

	public static double[] times(double matrix[][], double vector[])
		{
		double answer[] = new double[4];
		answer[0] = matrix[0][0]*vector[0] + matrix[0][1]* vector[1] + matrix[0][2]*vector[2] + matrix[0][3]*vector[3];
		answer[1] = matrix[1][0]*vector[0] + matrix[1][1]* vector[1] + matrix[1][2]*vector[2] + matrix[1][3]*vector[3];
		answer[2] = matrix[2][0]*vector[0] + matrix[2][1]* vector[1] + matrix[2][2]*vector[2] + matrix[2][3]*vector[3];
		answer[3] = 1;
		return answer;
		}

	public static double[][] times(double m[][], double n[][])
		{
		double a[][] = new double[4][4];
		for(int i = 0; i < 4; i++)
			for(int k = 0; k < 4; k++)
				{
				a[i][k] = 0;
				for(int j = 0; j< 4; j++)
					{
					a[i][k] += m[i][j]*n[j][k];
					}
				}
		return a;
		}

	public static String toString(double m[][])
		{
		String s = "";
		for (int i=0; i<m.length; i++)
			{
			for (int j=0; j<m[i].length; j++)
				s += m[i][j] + " ";
			s += "\n";
			}
		return s;
		}

	}