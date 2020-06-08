package model;

@SuppressWarnings("serial")
public class ReachAbilityMatrix extends Matrix 
{
	private int[] components;
	public ReachAbilityMatrix(int[][] matrix) throws MatrixException 
	{
		super(matrix);
		setComponents();
	}
	public ReachAbilityMatrix(DistanceMatrix matrix) throws MatrixException 
	{
		this(matrix.calculateReachAbilityMatrix());
	}

//	--------------------------- getter/setter ---------------------------------------
	public void setMatrix(int[][] matrix) throws MatrixException 
	{
		super.setMatrix(matrix);
	}
	public void setComponents()
	{
		int length = matrix.length;
		components = new int [length+1];
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
				/*for testing Start*/
//				System.out.println(" line: "+line+" column: "+column+" nodes: "+nodes);
//				for(int i:components)System.out.print(" "+i);
//				System.out.print('\n');
				/*for testing End*/
			}
			line++;
		}	
		components[length]=anz;
	}
	public int[] getComponents() 
	{
		return components;
	}
	//	---------------------------- calculate ------------------------------------------
	//	---------------------------- other  ------------------------------------------
	public boolean isMatrixValid(int[][] matrix) 
	{
		return true;
	}
//	---------------------------- toString  ------------------------------------------	


}
