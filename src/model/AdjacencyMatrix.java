package model;

@SuppressWarnings("serial")
public class AdjacencyMatrix extends Matrix 
{

	public AdjacencyMatrix(int[][] matrix) throws MatrixException 
	{
		super(matrix);
	}
//	--------------------------- getter/setter ---------------------------------------
	public void setMatrix(int[][] matrix) throws MatrixException 
	{
		super.setMatrix(matrix);
	}
//	---------------------------- calculate ------------------------------------------
	public int[][] calculateDistanceMatrix() /* multiplies one time then it needs ... !*/
	{
		int length = matrix.length;
		int[][] distanceMatrix = new int[length][length];
		int[][][] potenzMatrizen = new int[length][length][length];
				
		boolean didchange = false;
		for (int i=0; i<length;i++)
		{
			distanceMatrix[i]=matrix[i].clone();
			potenzMatrizen[1][i]=matrix[i].clone();
		}
		for (int square=2; square<length; square++)
		{
			didchange=false;
			for (int ze=0; ze<length; ze++)
			{
				for (int sp=ze; sp<length; sp++)
				{
					int vortex = 0;
					for (int spZe=0;spZe<sp;spZe++)
						vortex += potenzMatrizen[square-1][spZe][sp]*matrix[ze][spZe];
					for (int spSp=sp; spSp<length; spSp++)
						vortex += potenzMatrizen[square-1][sp][spSp]*matrix[ze][spSp];
					potenzMatrizen[square][ze][sp]=vortex;
					if(distanceMatrix[ze][sp]==0 && vortex!=0 && ze!=sp)
					{
						distanceMatrix[ze][sp]=square;
						distanceMatrix[sp][ze]=square;
						didchange=true;
					}
				}
			}
			if (!didchange)
				square=length;
		}
		
		return distanceMatrix;
	}
//	public int[][] calculateDistanceMatrix_v2() /* multiplies one time then it needs ... !*/
//	{
//		int length = matrix.length;
//		int[][] distanceMatrix = new int[length][];
//		int[][][] potenzMatrizen = new int[length][length][length];
//				
//		boolean didchange = false;
//		for (int line=0; line<length;line++)
//		{
//			for (int column=0; column<line+1; column++)
//			{
//				distanceMatrix[line][column]=matrix[line][column];
//				potenzMatrizen[1][line][column]=matrix[line][column];
//			}
//		}
//		for (int square=2; square<length; square++)
//		{
//			didchange=false;
//			for (int line=0; line<length; line++)
//			{
//				for (int column=line; column<length; column++)
//				{
//					int vortex = 0;
//					for (int spZe=0;spZe<column;spZe++)
//						vortex += potenzMatrizen[square-1][spZe][column]*matrix[line][spZe];
//					for (int spSp=column; spSp<length; spSp++)
//						vortex += potenzMatrizen[square-1][column][spSp]*matrix[line][spSp];
//					potenzMatrizen[square][line][column]=vortex;
//					if(distanceMatrix[line][column]==0 && vortex!=0 && line!=column)
//					{
//						distanceMatrix[line][column]=square;
//						distanceMatrix[column][line]=square;
//						didchange=true;
//					}
//				}
//			}
//			if (!didchange)
//				square=length;
//		}
//		
//		return distanceMatrix;
//	}
//	------------------------------- other  ------------------------------------------
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
				if(matrix[line][column]<0||matrix[line][column]>1)
					return false;				
			}
		return true;
	}
//	---------------------------- toString  ------------------------------------------
	public String toStringEdgesOfVortexTo(int vortex) throws MatrixException
	{
		if (vortex<1 || vortex>matrix.length)
			throw new MatrixException("Fehler in toStringEdgesOfVortexTo(int vortex): vortex must be greater then 0 and smaler then :"+matrix.length);
		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for(int vortexTo=vortex;vortexTo<matrix.length;vortexTo++)
			if(matrix[vortex-1][vortexTo]==1) 
			{
				sb.append(prefix+"["+(vortex)+","+(vortexTo+1)+"]");
				prefix = ",";
			}
		return sb.toString();
	}
}
