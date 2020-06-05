package test;

import java.util.ArrayList;
import java.util.Arrays;

import model.*;

@SuppressWarnings("unused")
public class TestReachAbilityMatrix 
{
	public static void main(String[] args) 
	{
//		System.out.println("test with flawed matrices:");
//		ArrayList<int[][]> flawedMatrices = TestMatrices.flawedMatrices();
//		int count = 0;
//		for (int[][] fMatrix: flawedMatrices)
//		{
//			try {DistanceMatrix  matrix = new DistanceMatrix (fMatrix);}         	
//			catch (MatrixException e){System.out.println(e.getMessage());count++;}
//		}
//		if(count==flawedMatrices.size()-1)
//			System.out.println("all errors detected");
//		else
//			System.out.println("Errors not detected: "+(flawedMatrices.size()-count+1));
		
		System.out.println("test calculateDistanceMatrix()");	
		try
		{
			System.out.println("TestMatrices.matrix_4x4_1Component()");
			ReachAbilityMatrix rm = new ReachAbilityMatrix(new DistanceMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component()).calculateDistanceMatrix()));
			boolean equals=true;
			for (int length=0; length<rm.getMatrix().length; length++)
				if(Arrays.equals(rm.getMatrix()[length],TestMatrices.matrix_4x4_1Component_ReachAbilityMatrix()[length]))
					System.out.println("matrix_4x4_1Component Line "+length+": ok");
				else
					System.out.println("matrix_4x4_1Component Line "+length+": notOk");
			System.out.print("components   : ");
			for (int i=0; i<rm.getComponents().length; i++)
				System.out.print(rm.getComponents()[i]+" ");
			System.out.println('\n');
		}catch (MatrixException e){System.out.println(e.getMessage());}
		System.out.println("test calculateDistanceMatrix()");	
		try
		{
			System.out.println("TestMatrices.matrix_4x4_3Component()");
			ReachAbilityMatrix rm = new ReachAbilityMatrix(new DistanceMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_3Component()).calculateDistanceMatrix()));
			System.out.println("components : "+rm.getComponents()[rm.getComponents().length-1]);
			for (int i=0; i<rm.getComponents().length; i++)
				System.out.print(rm.getComponents()[i]+" ");
			System.out.println('\n');
		}catch (MatrixException e){System.out.println(e.getMessage());}


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
