package model;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Matrix implements Serializable
{
	protected int[][] matrix;
	
	public Matrix(int[][] matrix) throws MatrixException 
	{
		setMatrix(matrix);
	}
//	--------------------------- getter/setter ---------------------------------------
	public void setMatrix(int[][] matrix) throws MatrixException
	{
		if(isMatrixValid(matrix))
			this.matrix=matrix;
		else
			throw new MatrixException("Matrix nicht zulässig!");
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
	public int[][] removeEdge(int vortexX, int vortexY)
	{
		int length = matrix.length;
		int[][] nMatrix = new int[length][length];
		for(int line=0; line<matrix.length; line++)
			nMatrix[line]=matrix[line].clone();
		nMatrix[vortexX][vortexY]=0;
		return nMatrix;
	}
	public abstract boolean isMatrixValid(int[][] matrix);
//	---------------------------- toString  ------------------------------------------	
	public String toString()
	{
    	return toString(2);
	}
	public String toString(int spacing)
	{
		if(spacing<=0)
			spacing=2;
    	int length = matrix.length;
    	int digitsLength=length<10?1:length<100?2:3;
    	StringBuilder sb = new StringBuilder(((length+3)*(length+1))+length);
    	sb.append(getClass().getSimpleName()).append('\n');
		for (int line=0; line<length;line++)
		{
			sb.append(String.format("%"+digitsLength+"d:",line+1));
			for (int column=0; column<length;column++)
			{
				sb.append(String.format("%"+spacing+"d",matrix[line][column]));
			}
			if(line!=length-1)
				sb.append('\n');
		}
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
				prefix=";";
			}
			if(line!=length-1)
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
