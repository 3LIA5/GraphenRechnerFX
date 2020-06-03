package model;

@SuppressWarnings("serial")
public class ReachAbilityMatrix extends Matrix 
{
	private int[] components;
	public ReachAbilityMatrix(int[][] matrix) throws MatrixException 
	{
		super(matrix);
	}
//	--------------------------- getter/setter ---------------------------------------
	public void setMatrix(int[][] matrix) throws MatrixException 
	{
		super.setMatrix(matrix);
	}
	public int[] getComponents() 
	{
		return components;
	}
	public void setComponents() 
	{
		components = calculateComponents(matrix);
	}
	//	---------------------------- calculate ------------------------------------------
	public int[] calculateComponents(int[][] matrix)
	{
		int length = matrix.length;
		int[] components = new int [length+1];
		boolean changed =false;
		int nodes = length;
		int anz = 1;
		int line = 0;

		while(nodes>0)
		{
			if(changed)
			{
				anz++;
				changed=false;
			}
			for (int column =0; column < length; column++)
			{
				if (matrix[line][column]==1 && components[column]==0)
				{
					components[column]=anz;
					changed=true;
					nodes--;
				}
			}
			line++;
		}	
		components[length]=anz;
		return components;
	}
	
	public boolean[] calculateCutVertices() throws GraphException
	{
		int length = matrix.length;
		boolean[] isCutVertice = new boolean[length];
		for (int vortex=0; vortex<length; vortex++)
		{
			if(calculateComponents(removeVortex(vortex))[length]>components[length])
				isCutVertice[vortex]=true;
			else
				isCutVertice[vortex]=false;
		}
		return isCutVertice;
	}
//	---------------------------- other  ------------------------------------------
	public boolean isMatrixValid(int[][] matrix) 
	{

		return true;
	}
//	---------------------------- toString  ------------------------------------------	

}
