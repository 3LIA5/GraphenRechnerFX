package model;
import java.util.*;

public class Rechner 
{
	public int[][][] exponentialMatrices;

	public Rechner (int[][] matrix)
	{
		setMatrizen(matrix);
	}
	//-------------------------getter-----------------------
	public int[][][] getMatrizen() 
	{
		return exponentialMatrices;
	}
	//-------------------------setter-----------------------
	public void setMatrizen(int[][] matrix) 
	{
		if (matrix!=null)
		{
			int width=matrix.length;
			exponentialMatrices = new int[width][width][width];
			exponentialMatrices[0] = matrix;
		}
		else
			System.out.println("NULL Ref. für setMatrize(int[][] matrize)!!");
	}
	
	public static int[][] distanzMatrix(Graph graph)
	{
		int width = graph.getAdjazensmatirx().length;
		int[][][] potenzMatrizen = new int[width][width][width];		
		int[][] distanzMatrix = new int[width][width];
		for (int i=0; i<width;i++)
		{
			distanzMatrix[i]=graph.getAdjazensmatirx()[i].clone();
			potenzMatrizen[0][i]=graph.getAdjazensmatirx()[i].clone();
		}

		for (int square=0; square<width-2; square++)
		{
			for (int line=0; line<width;line++)
			{
				for (int column=0; column<width;column++)
				{
					int vortex = 0;
					for (int i=0; i<width; i++) 
					{			
						vortex += potenzMatrizen[square][line][i]*potenzMatrizen[0][i][column];
					}
					potenzMatrizen[square+1][line][column]=vortex;
					if(distanzMatrix[line][column]==0 && vortex!=0 && line!=column)
						distanzMatrix[line][column]=square+2;
				}			
			}	
		}
		return distanzMatrix;
	}
	public static int[][] wegMatrix(Graph graph)
	{
		int[][] distanzMatrix = graph.getDistanzMatrix();
		int lenght=distanzMatrix.length;
		int[][] wegMatrix = new int[lenght][lenght];
		for (int line=0; line<lenght; line++)
		{
			for (int column=0; column<lenght; column++)
			{
				if(distanzMatrix[line][column]!=0)
					wegMatrix[line][column]=1;
			}
			wegMatrix[line][line]=1;
		}
		return wegMatrix;
	}
	
	public String toStringAuswertung()
	{
		int[] stat = new int[exponentialMatrices.length];
		int min=0;
		int max=0;
		StringBuilder sb = new StringBuilder();
		sb.append("Exzentrizität(V):\n");
		for (int line=0; line<exponentialMatrices.length;line++)
		{
			stat[line]=Arrays.stream(exponentialMatrices[exponentialMatrices.length-1][line]).summaryStatistics().getMax();
			sb.append(String.format("%5s", (char)(65+line))).
			append(" : ").
			append(stat[line]).
			append("\n");
		}
		min =  Arrays.stream(stat).summaryStatistics().getMin();
		max = Arrays.stream(stat).summaryStatistics().getMax();
		sb.append('\n').
		append("Durchmesser: ").
		append(max).append('\n').
		append("Radius: ").
		append(min).append('\n').
		append("Zentrum = {");
		String prefix = "";
		for (int i=0;i<exponentialMatrices.length;i++)
			if (stat[i]==min)
			{
				sb.append(prefix).append((char)(65+i));
				prefix =",";
			}
		sb.append("}\n");
		return sb.toString();
	}
	
	public String toStringDistanzmatrix()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Distanzmatrix:\n");
		for (int line=0; line<exponentialMatrices.length;line++)
		{
			for (int column=0; column<exponentialMatrices[0][line].length;column++)
				sb.append(String.format("%5d", exponentialMatrices[exponentialMatrices.length-1][line][column]));
			sb.append(" -> ").
			append(Arrays.stream(exponentialMatrices[exponentialMatrices.length-1][line]).summaryStatistics().getMax()).
			append("\n\n");
		}
		sb.append('\n');
		
		return sb.toString();
	}
	public String toStringMatrix(String str, int pos)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(str);
//		int l=0,c=0;
		for (int line=0; line<exponentialMatrices.length;line++)
		{
			for (int column=0; column<exponentialMatrices[pos][line].length;column++)
				sb.append(String.format("%5d", exponentialMatrices[pos][line][column]));
			sb.append("\n\n");
		}
		sb.append('\n');
//		for (int line=0; line<matrizen.length;line++)
//		{
//			l=0;
//			c=line;
//			sb.append(String.format("%5d", matrizen[pos][l][c]));
//			while (l<c&&l<matrizen.length)
//			{
//				l++;
//				sb.append(String.format("%5d", matrizen[pos][l][c]));
//			}
//			while (c<matrizen.length-1)
//			{
//				c++;
//				sb.append(String.format("%5d", matrizen[pos][l][c]));
//			}
//			sb.append("\n\n");
//		}
//		sb.append('\n');
		
		return sb.toString();
	}
	public String toString()
	{
		return  toStringMatrix("Adjazenzmatrix:\n",0) 
				+toStringMatrix("Distanzmatrix:\n",(exponentialMatrices.length-1))		
				+toStringAuswertung();
	}
	
}
