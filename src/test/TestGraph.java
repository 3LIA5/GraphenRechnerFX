package test;

import model.*;

public class TestGraph 
{
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		AdjacencyMatrix ma;
		try 
		{
			Graph gr = new Graph("test");
			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component()));
			System.out.println(gr.getAdjazensmatirx());
			gr.setDistanztrix();
//			System.out.println(new DistanceMatrix(TestMatrices.matrix_4x4_1Component_DistanceMatrix()));
			System.out.println(gr.getDistanzMatrix());
			gr.setWegmatrix();
//			System.out.println(new ReachAbilityMatrix(TestMatrices.matrix_4x4_1Component_ReachAbilityMatrix()));
			System.out.println(gr.getWegmatrix());
		} 
		catch (MatrixException e) 
		{
			System.out.println(e.getMessage());
		}

		
////		----------------------Test export/import csv ---------------------------
//		try 
//		{
//			Graph gr = new Graph("test");
//			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component()));
//			System.out.println("Test: exp_csv");
//			System.out.println(System.getProperty("java.io.tmpdir")+"matrix_8x8_4Component"+".matrix");
////			gr.exportAdjazensmatirxCsv(System.getProperty("java.io.tmpdir")+"matrix_200x200_200components"+".csv");
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+"matrix_200x200_1Component"+".csv");
//			System.out.println("Test: imp_csv");
////			gr.importMatrixCsv(System.getProperty("java.io.tmpdir")+"matrix_8x8_4Component"+".csv", ",");
//			gr.importMatrixCsv("C:\\scratch\\"+"matrix_200x200_1Component"+".csv", ",");
//			System.out.println(gr.getAdjazensmatirx());
//		} 
//		catch (GraphenRechnerException e) 
//		{
//			System.out.println(e.getMessage());
//		}	
	}


}


