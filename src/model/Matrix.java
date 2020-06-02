package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Matrix implements Serializable
{
	protected int[][] matrix;
	
	public Matrix(int[][] matrix) 
	{
		setMatrix(matrix);
	}
//	--------------------------- getter/setter ---------------------------------------
	public void setMatrix(int[][] matrix)
	{
		this.matrix=matrix;
	}
	public int[][] getMatrix() 
	{
		return matrix;
	}	
//	----------------------------- other  --------------------------------------------
	public int[][] removeVortex(int vortex)
	{
		int length = matrix.length;
		int skipColumn;
		int skipLine = 0;
		int[][] minorMatrix = new int [length-1][length-1];
		
		for (int line =0; line<length-1; line++)
		{
			if(line==vortex)skipLine=1;
			skipColumn=0;
			for (int column=0; column<length-1; column++)
			{
				if(column==vortex)skipColumn=1;
				minorMatrix[line][column]=matrix[line+skipLine][column+skipColumn];
			}
		}
		return minorMatrix;
	}
//	---------------------------- toString  ------------------------------------------	
	public String toString()
	{
		int length = matrix.length;
		StringBuilder sb = new StringBuilder();
		for (int line=0; line<length;line++)
		{
			for (int column=0; column<length;column++)
				sb.append(String.format("%5d", matrix[line][column]));
			sb.append("\n\n");
		}
		sb.append('\n');
		return sb.toString();
	}	
    public String toStringCsv()
    {
    	int length = matrix.length;
    	StringBuilder sb = new StringBuilder((length*length)+length);
		for (int line=0; line<length;line++)
		{
			String prefix = "";
			for (int column=0; column<length;column++)
			{
				sb.append(String.format("%s%d",prefix, matrix[line][column]));
				prefix=",";
			}
			sb.append('\n');
		}
    	return sb.toString();
    }
//	--------------------------- for testing -----------------------------------
	protected void matrixToString(int [][] matrix)
	{
		for(int[] l:matrix)
		{
			for(int c:l)
				System.out.print((String.format("%5d", c)));
			System.out.println("\n\n");
		}
	}
}
