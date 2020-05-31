package model;


public class Matrix 
{
	private boolean regulaer;
	private int[][] matrix;
	
	public Matrix(int[][] matrix) 
	{
		setMatrix(matrix);
		setRegulaer();
	}
	public Matrix(int length) 
	{
		setMatrix(new int[length][length]);
		setRegulaer();
	}
	public Matrix() 
	{
		this(
			new int[][] 
			{
				/*26x26 (Graph C)*/
				/*   {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z}*/
				/*A*/{0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*B*/{1,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*C*/{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*D*/{0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*E*/{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*F*/{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
				/*G*/{0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
				/*H*/{1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
				/*I*/{0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*J*/{0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*K*/{0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*L*/{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*M*/{0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,0},
				/*N*/{0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0},
				/*O*/{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
				/*P*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0},
				/*Q*/{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*R*/{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
				/*S*/{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
				/*T*/{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0},
				/*U*/{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0},
				/*V*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0},
				/*W*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
				/*X*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0},
				/*Y*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,1},
				/*Z*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0}
				/*   {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z}*/
			}
		);
	}

	public void setMatrix(int[][] matrix) 
	{
		this.matrix = matrix;
	}
	public void setRegulaer()
	{
		regulaer=true;
		for (int zeile=0; zeile<matrix.length; zeile++)
			for (int spalte=zeile; spalte<matrix.length; spalte++)
				if(matrix[zeile][spalte]!=matrix[spalte][zeile])
					regulaer=false;				
	}
	
	public int[][] getMatrix() 
	{
		return matrix;
	}
	public boolean isRegulaer()
	{
		return regulaer;
	}
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
}
