package model;

import java.util.Arrays;

@SuppressWarnings("serial")
public class DistanceMatrix extends Matrix 
{
	private int[] summary;
	public DistanceMatrix(int[][] matrix) throws MatrixException 
	{
		super(matrix);
		calculateSummary();
	}
	public DistanceMatrix(AdjacencyMatrix matrix) throws MatrixException 
	{
		super(matrix.calculateDistanceMatrix());
		calculateSummary();
	}
//	public DistanceMatrix(AdjacencyMatrix matrix,boolean simple) throws MatrixException 
//	{
//		super(matrix.calculateDistanceMatrix());
//		if (!simple)
//			calculateSummary();
//	}
//	--------------------------- getter/setter ---------------------------------------
	public void setMatrix(int[][] matrix) throws MatrixException 
	{
		super.setMatrix(matrix);
	}
	public int[] getSummary()
	{
		return summary;
	}
//	---------------------------- calculate ------------------------------------------
	public void calculateSummary()
	{
		summary = new int[matrix.length];
		for (int vortex=0; vortex<matrix.length;vortex++)
		{
			summary[vortex]=Arrays.stream(matrix[vortex]).summaryStatistics().getMax();
		}
	}
	public int[][] calculateReachAbilityMatrix()
	{
		int lenght=matrix.length;
		int[][] reachAbilityMatrix = new int[lenght][lenght];
		for (int line=0; line<lenght; line++)
		{
			for (int column=0; column<lenght; column++)
//			for (int column=line+1; column<lenght; column++)
			{
				if(matrix[line][column]!=0)
				{
					reachAbilityMatrix[line][column]=1;
//					reachAbilityMatrix[column][line]=1;
				}
			}
			reachAbilityMatrix[line][line]=1;
		}
		return reachAbilityMatrix;
	}
//	---------------------------- other  ------------------------------------------
	public boolean isMatrixValid(int[][] matrix) 
	{
		if(matrix==null)
			return false;
		int length = matrix.length;
		for (int line=0;line<length;line++)
		{
			if(matrix[line]==null)
				return false;
			if(length!=matrix[line].length)
				return false;
			if(matrix[line][line]!=0)
				return false;
		}
		for (int line=1;line<length;line++)
			for(int column=0;column<line;column++)
			{
				if(matrix[line][column]!=matrix[column][line])
					return false;
				if(matrix[line][column]<0)
					return false;				
			}
		return true;
	}
//	---------------------------- toString  ------------------------------------------
	public String toString()
	{
		int diameter=Arrays.stream(summary).summaryStatistics().getMax();
	    int spacer=diameter<10?2:diameter<100?3:4;
		return super.toString(spacer);
	}
}
