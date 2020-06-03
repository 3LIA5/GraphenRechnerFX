package test;

import java.util.ArrayList;
import java.util.Arrays;

import model.*;

public class TestDistanceMatrix 
{
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		System.out.println("test with flawed matrices:");
		ArrayList<int[][]> flawedMatrices = TestMatrices.flawedMatrices();
		int count = 0;
		for (int[][] fMatrix: flawedMatrices)
		{
			try {DistanceMatrix  matrix = new DistanceMatrix (fMatrix);}         	
			catch (MatrixException e){System.out.println(e.getMessage());count++;}
		}
		if(count==flawedMatrices.size()-1)
			System.out.println("all errors detected");
		else
			System.out.println("Errors not detected: "+(flawedMatrices.size()-count+1));
		
		System.out.println("test calculateDistanceMatrix()");	
		
		try{
			DistanceMatrix dm = new DistanceMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component()).calculateDistanceMatrix());
			boolean equals=true;
			for (int length=0; length<dm.getMatrix().length; length++)
				if(Arrays.equals(dm.calculateReachAbilityMatrix()[length],TestMatrices.matrix_4x4_1Component_ReachAbilityMatrix()[length]))
					System.out.println("matrix_4x4_1Component Line "+length+": ok");
				else
					System.out.println("matrix_4x4_1Component Line "+length+": notOk");
		}
		catch (MatrixException e){System.out.println(e.getMessage());}
//		try{
//			System.out.println("------------------");
//			SysMatrix(TestMatrices.matrix_8x8_3Component());
//			AdjacencyMatrix aM = new AdjacencyMatrix(TestMatrices.matrix_8x8_3Component());
//			System.out.println("------------------");
//			SysMatrix(aM.calculateDistanceMatrix());
//		}catch (MatrixException e){System.out.println(e.getMessage());}
//		try{
//			System.out.println("------------------");
//			SysMatrix(TestMatrices.matrix_line(8));
//			AdjacencyMatrix aM = new AdjacencyMatrix(TestMatrices.matrix_line(8));
//			System.out.println("------------------");
//			SysMatrix(aM.calculateDistanceMatrix());
//		}catch (MatrixException e){System.out.println(e.getMessage());}
//		try{
//			System.out.println("------------------");
//			AdjacencyMatrix aM = new AdjacencyMatrix(new int[6][6]);
//			System.out.println("------------------");
//			SysMatrix(aM.calculateDistanceMatrix());
//		}catch (MatrixException e){System.out.println(e.getMessage());}

	}
	public static void SysMatrix(int [][] matrix)
	{
		for(int[] l:matrix)
		{
			for(int c:l)
				System.out.print((String.format("%5d", c)));
			System.out.println("\n\n");
		}
	}
}