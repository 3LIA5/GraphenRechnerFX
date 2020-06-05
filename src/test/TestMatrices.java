package test;

import java.util.ArrayList;

public class TestMatrices 
{

	public TestMatrices() 
	{}
	public static ArrayList<int[][]> flawedMatrices()
	{
		ArrayList<int[][]> flawedMatrices=new ArrayList<int[][]>();
		flawedMatrices.add(matrixFlawed_null());
		flawedMatrices.add(matrixFlawed_4xnull());
		flawedMatrices.add(matrixFlawed_4xXY());
		flawedMatrices.add(matrixFlawed_11is1());
		flawedMatrices.add(matrixFlawed_44is1());
		flawedMatrices.add(matrixFlawed_undirected());
		flawedMatrices.add(matrixFlawed_edgesBigger1() );
		flawedMatrices.add(matrixFlawed_edgesSmaler0() );
		return flawedMatrices;
	}
	public static int[][] matrixFlawed_null()
	{ 
		return null;
	}
	public static int[][] matrixFlawed_4xnull()
	{ 
		return 
			new int[][] 
				{
					null,
					null,
					null,
					null
				};
	}
	public static int[][] matrixFlawed_4xXY()
	{ 
		return 
			new int[][] 
				{
					{0,0,1,0},
					{0,0,1,1,0},
					{1,1,0},
					{0,1}
				};
	}
	public static int[][] matrixFlawed_11is1()
	{ 
		return 
			new int[][] 
				{
					{0,0,1,0},
					{0,1,1,1},
					{1,1,0,0},
					{0,1,0,0}
				};
	}
	public static int[][] matrixFlawed_44is1()
	{ 
		return 
			new int[][] 
				{
					{0,0,1,0},
					{0,0,1,1},
					{1,1,0,0},
					{0,1,0,1}
				};
	}
	public static int[][] matrixFlawed_undirected()
	{ 
		return 
			new int[][] 
				{
					{0,0,1,0},
					{1,0,1,1},
					{0,1,0,0},
					{0,0,1,1}
				};
	}
	public static int[][] matrixFlawed_edgesBigger1()
	{ 
		return 
			new int[][] 
				{
					{0,0,1,0},
					{0,0,2,1},
					{1,2,0,0},
					{0,1,0,0}
				};
	}
	public static int[][] matrixFlawed_edgesSmaler0()
	{ 
		return 
			new int[][] 
				{
					{0,0,1,0},
					{0,0,-1,1},
					{1,-1,0,0},
					{0,1,0,0}
				};
	}
	public static int[][] matrix_4x4_1Component()
	{ 
		return 
			new int[][] 
				{
					{0,0,1,0},
					{0,0,1,1},
					{1,1,0,0},
					{0,1,0,0}
				};
	}
	public static int[][] matrix_4x4_1Component_DistanceMatrix()
	{ 
		return 
			new int[][] 
				{
					{0,2,1,3},
					{2,0,1,1},
					{1,1,0,2},
					{3,1,2,0}
				};
	}
	public static int[][] matrix_4x4_1Component_ReachAbilityMatrix()
	{ 
		return 
			new int[][] 
				{
					{1,1,1,1},
					{1,1,1,1},
					{1,1,1,1},
					{1,1,1,1}
				};
	}
	public static String matrix_4x4_1Component_durchmesser()
	{
		return new String("diameter = 3\n");
	}
	public static String matrix_4x4_1Component_radius()
	{
		return new String("radius = 2\n");
	}
	public static String matrix_4x4_1Component_Zentrum()
	{
		return new String("centre = {2,3}\n");
	}
	public static String matrix_4x4_1Component_Bruecken()
	{
		return new String("Brücken = {[1,3],[2,3],[2,4]}\n") ;
	}
	public static String matrix_4x4_1Component_Artikulationen()
	{
		return new String("Artikulationen = {2,3}\n") ;
	}
				
	public static int[][] matrix_4x4_3Component()
	{ 
		return
			new int[][] 
			{
				{0,0,0,0},
				{0,0,1,0},
				{0,1,0,0},
				{0,0,0,0}
			};	
	}
	public static int[][] matrix_8x8_1Component()
	{ 
		return
			new int[][] 
			{
				{0,1,0,1,0,1,0,0},
				{1,0,1,0,0,1,1,0},
				{0,1,0,0,0,0,0,0},
				{1,0,0,0,1,0,0,0},
				{0,0,0,1,0,1,0,0},
				{1,1,0,0,1,0,0,1},
				{0,1,0,0,0,0,0,1},
				{0,0,0,0,0,1,1,0}
			};
	}
	public static int[][] matrix_8x8_3Component()
	{ 
		return 
			new int[][] 
			{	
				{0,1,0,1,0,0,0,0},
				{1,0,0,0,1,0,0,0},
				{0,0,0,0,0,1,0,0},
				{1,0,0,0,1,0,0,0},
				{0,1,0,1,0,0,0,0},
				{0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,1},
				{0,0,0,0,0,0,1,0}
			};
	}
	public static int[][] matrix_8x8_4Component()
	{ 
		return 
			new int[][] 
			{	
				{0,1,0,1,0,0,0,0},
				{1,0,0,1,0,0,0,0},
				{0,0,0,0,0,1,0,0},
				{1,1,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,1,0,0,0,0,0},
				{0,0,0,0,0,0,0,1},
				{0,0,0,0,0,0,1,0}
			};
	}
	public static int[][] matrix_8x8b()
	{ 
		return
			new int[][] 
			{
				{0,1,1,1,0,0,0,0},
				{1,0,0,0,1,0,0,0},
				{1,0,0,0,0,1,0,0},
				{1,0,0,0,1,1,1,0},
				{0,1,0,1,0,0,1,1},
				{0,0,1,1,0,0,1,1},
				{0,0,0,1,1,1,0,1},
				{0,0,0,0,1,1,1,0}
			};
	}
	public static int[][] matrix_15x15_A()
	{ 
		return 
			new int[][] 
			{
				/*   {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O}*/
				/*A*/{0,1,0,0,0,1,1,0,0,1,0,0,0,0,0},
				/*B*/{1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
				/*C*/{0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
				/*D*/{0,0,0,0,1,0,1,0,0,0,0,0,0,0,0},
				/*E*/{0,0,0,1,0,0,0,0,1,0,0,0,0,0,0},
				/*F*/{1,0,0,0,0,0,1,0,0,0,1,1,0,0,0},
				/*G*/{1,0,0,1,0,1,0,0,0,0,1,0,1,0,1},
				/*H*/{0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
				/*I*/{0,0,1,0,1,0,0,1,0,0,0,0,1,0,0},
				/*J*/{1,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
				/*K*/{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
				/*L*/{0,0,0,0,0,1,0,0,0,0,0,0,1,0,0},
				/*M*/{0,0,0,0,0,0,1,0,1,0,0,1,0,1,0},
				/*N*/{0,0,0,0,0,0,0,0,0,1,0,0,1,0,0},
				/*O*/{0,0,0,0,0,0,1,1,0,0,0,0,0,0,0}
			};
	}
	public static int[][] matrix_25x25_B()
	{ 
		return 
			new int[][] 
			{
				/*   {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y}*/
				/*A*/{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*B*/{1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*C*/{0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*D*/{1,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*E*/{0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*F*/{0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*G*/{0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0},
				/*H*/{0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				/*I*/{0,0,0,1,1,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0},
				/*J*/{0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
				/*K*/{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
				/*L*/{0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
				/*M*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
				/*N*/{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
				/*O*/{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
				/*P*/{0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
				/*Q*/{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0},
				/*R*/{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0},
				/*S*/{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
				/*T*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0},
				/*U*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,0},
				/*V*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},
				/*W*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1},
				/*X*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
				/*Y*/{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0}
				/*   {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y}*/
			};
	}
	public static String matrix_25x25_B_durchmesser()
	{
		return new String("diameter = 7\n");
	}
	public static String matrix_25x25_B_radius()
	{
		return new String("radius = 4\n");
	}
	public static String matrix_25x25_B_Zentrum()
	{
		return new String("centre = {9,18}\n");
	}
	public static String matrix_25x25_B_Bruecken()
	{
		return new String("Brücken = {}\n") ;
	}
	public static String matrix_25x25_B_Artikulationen()
	{
		return new String("Artikulationen = {9,23}\n") ;
	}
	public static int[][] matrix_26x26_C()
	{ 
		return 
			new int[][] 
			{
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
			};
	}
	public static int[][] matrix_line(int size)
	{ 
		int[][] matrix_line = new int[size][size];
		for (int i=0;i<matrix_line.length-1;i++)
		{
			matrix_line[i][i+1]=1;
			matrix_line[i+1][i]=1;
		}
		return matrix_line;
	}
	public static int[][] matrix_200x200_random_A()
	{
		int[][] matrix_200x200_random = new int[200][200];
		for (int z=0;z<matrix_200x200_random.length;z++)
			for(int s=z;s<matrix_200x200_random.length;s++)
				matrix_200x200_random[z][s]=(int) Math.round( Math.random());
		for (int z=1;z<matrix_200x200_random.length;z++)
			for(int s=0;s<z;s++)
				matrix_200x200_random[z][s]=matrix_200x200_random[s][z];
		for (int z=0;z<matrix_200x200_random.length;z++)
			matrix_200x200_random[z][z]=0;
		return matrix_200x200_random;
	}
	public static int[][] matrix_random_line(int size)
	{
		int[][] matrix_random_line = new int[size][size];
		for (int i=0;i<size-1;i++)
		{
			matrix_random_line[i][i+1]=(int) Math.round( Math.random());
			matrix_random_line[i+1][i]=matrix_random_line[i][i+1];
		}
		for (int i=0;i<size-2;i++)
		{
			matrix_random_line[i][i+2]=(int) Math.round( Math.random());
			matrix_random_line[i+2][i]=matrix_random_line[i][i+2];
		}
		return matrix_random_line;
	}
	public static String toStringMatrix(int[][] matrix)
	{
    	int length = matrix.length;
    	int digitsLength=length<10?1:length<100?2:3;
    	StringBuilder sb = new StringBuilder(((length+3)*(length+1))+length);
		for (int line=0; line<length;line++)
		{
			String prefix = "";
			sb.append(String.format("%-"+digitsLength+"d:",line+1));
			for (int column=0; column<length;column++)
			{
				sb.append(String.format("%s%d",prefix, matrix[line][column]));
				prefix=",";
			}
			if(line!=length-1)
				sb.append('\n');
		}
    	return sb.toString();
	}

}
