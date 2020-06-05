package test;

import java.util.ArrayList;
import java.util.Arrays;

import model.AdjacencyMatrix;
import model.MatrixException;

public class TestAdjacencyMatrix 
{
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		System.out.println("test with flawed matrices:");
		ArrayList<int[][]> flawedMatrices = TestMatrices.flawedMatrices();
		int count = 0;
		for (int[][] fMatrix: flawedMatrices)
		{
			try {AdjacencyMatrix matrix = new AdjacencyMatrix(fMatrix);}         	
			catch (MatrixException e){System.out.println(e.getMessage());count++;}
		}
		if(count==flawedMatrices.size())
			System.out.println("all errors detected");
		else
			System.out.println("Errors not detected: "+(count-flawedMatrices.size()));
		
		System.out.println("test calculateDistanceMatrix()");		
		try{
			AdjacencyMatrix aM = new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component());
			boolean equals=true;
			for (int length=0; length<aM.getMatrix().length; length++)
				if(Arrays.equals(aM.calculateDistanceMatrix()[length],TestMatrices.matrix_4x4_1Component_DistanceMatrix()[length]))
					System.out.println("matrix_4x4_1Component Line "+length+": ok");
				else
					System.out.println("matrix_4x4_1Component Line "+length+": notOk");
		}
		catch (MatrixException e){System.out.println(e.getMessage());}
		try{
			System.out.println("||||||||||||||||||||||||||||||||||");
			toStringCsv(TestMatrices.matrix_8x8_3Component());
			AdjacencyMatrix aM = new AdjacencyMatrix(TestMatrices.matrix_8x8_3Component());
			System.out.println("------------------");
			toStringCsv(aM.calculateDistanceMatrix());
		}catch (MatrixException e){System.out.println(e.getMessage());}
		try{
		System.out.println("||||||||||||||||||||||||||||||||||");
		AdjacencyMatrix aM = new AdjacencyMatrix(new int[6][6]);
		System.out.println("------------------");
		toStringCsv(aM.calculateDistanceMatrix());
		}catch (MatrixException e){System.out.println(e.getMessage());}
		try{
			System.out.println("||||||||||||||||||||||||||||||||||");
			AdjacencyMatrix aM = new AdjacencyMatrix(TestMatrices.matrix_random_line(6));
			System.out.println(aM.toStringCsv());
			System.out.println("------------------");
			System.out.println(toStringCsv(aM.calculateDistanceMatrix()));			
		}catch (MatrixException e){System.out.println(e.getMessage());}
		
		try{
			System.out.println("||||||||||||||||||||||||||||||||||");
			AdjacencyMatrix aM = new AdjacencyMatrix(TestMatrices.matrix_random_line(6));
			System.out.println(aM.toStringCsv());
			System.out.println("------------------");

			for (int i=1; i<=aM.getMatrix().length;i++)
				System.out.println((i)+" = "+aM.toStringEdgesOfVortexTo(i));			
		}catch (MatrixException e){System.out.println(e.getMessage());}


	}
    public static String toStringCsv(int[][] matrix)
    {
    	int length = matrix.length;
    	int digitsLength=length<10?1:length<100?2:3;
    	StringBuilder sb = new StringBuilder((length*length)+length);
		for (int line=0; line<length;line++)
		{
			String prefix = "";
			sb.append(String.format("%-"+digitsLength+"d:",line+1));
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
