import java.awt.*;
import java.awt.event.*;

public class Transform
	{
	public static double[][] identity()
		{
		double m[][] = new double[3][3];
		for(int x = 0; x < 3; x++)
			{
			m[x][x] = 1;
			}
		return m;
		}

	public static double[][] rotate(double angle)
		{
		double m[][] = identity();
		double c = Math.cos(angle*Math.PI/180);
		double s = Math.sin(angle*Math.PI/180);
		m[0][0] = m[1][1] = c;
		m[1][0] = s;
		m[0][1] = -s;
		return m;
		}

	public static double[][] translate(double dx, double dy)
		{
		double m[][] = identity();
		m[1][2] = dx;
		m[2][2] = dy;
		return m;
		}

	public static double[][] stretch(double rx, double ry)
		{
		double m[][] = identity();
		m[0][0] = rx;
		m[1][1] = ry;
		return m;
		}

	public static double[] times(double matrix[][], double vector[])
		{
		double answer[] = new double[3];
		answer[0] = matrix[0][0]*vector[0] + matrix[0][1]* vector[1] + matrix[0][2]*vector[2];
		answer[1] = matrix[1][0]*vector[0] + matrix[1][1]* vector[1] + matrix[1][2]*vector[2];
		answer[2] = 1;
		return answer;
		}

	public static double[][] times(double m[][], double n[][])
		{
		double a[][] = new double[3][3];
		for(int i = 0; i < 3; i++)
			for(int k = 0; k < 3; k++)
				{
				a[i][k] = 0;
				for(int j = 0; j< 3; j++)
					{
					a[i][k] += m[i][j]*n[j][k];
					}
				}
		return a;
		}

	}