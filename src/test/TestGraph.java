package test;

import model.*;

public class TestGraph 
{
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
//	//	----------------------Test export/import csv ---------------------------
//		try 
//		{
//			Graph gr = new Graph("test");
//			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component()));
//			System.out.println("Test: exp_csv");
//			System.out.println(System.getProperty("java.io.tmpdir")+"matrix_8x8_4Component"+".matrix");
//	//		gr.exportAdjazensmatirxCsv(System.getProperty("java.io.tmpdir")+"matrix_200x200_200components"+".csv");
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+"matrix_200x200_1Component"+".csv");
//			System.out.println("Test: imp_csv");
//	//		gr.importMatrixCsv(System.getProperty("java.io.tmpdir")+"matrix_8x8_4Component"+".csv", ",");
//			gr.importMatrixCsv("C:\\scratch\\"+"matrix_200x200_1Component"+".csv", ",");
//			System.out.println(gr.getAdjazensmatirx());
//		} 
//		catch (Exception e)	{System.out.println(e.getMessage());}
		
		try 
		{
			System.out.println("\n   TestMatrices.matrix_4x4_1Component()");
			Graph gr = new Graph();
			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component()));
			System.out.println(gr.getAdjazensmatirx().toString());
			gr.setDistanztrix();
			System.out.println(gr.getDistanceMatrix());			
			if(gr.radiusToString().equals(TestMatrices.matrix_4x4_1Component_radius()))
				System.out.println("Radius ok");
			else
				System.out.println("Radius not ok");
			if(gr.diameterToString().equals(TestMatrices.matrix_4x4_1Component_durchmesser()))
				System.out.println("Durchmesser ok");
			else
				System.out.println("Durchmesser not ok");
			if(gr.centreToString().equals(TestMatrices.matrix_4x4_1Component_Zentrum()))
				System.out.println("Zentrum ok");
			else
				System.out.println("Zentrum not ok");
			gr.setWegmatrix();
			gr.calculateCutVertices();
			if(gr.toStringCutVertices().equals(TestMatrices.matrix_4x4_1Component_Artikulationen()))
				System.out.println("Artikulationen ok");
			else
				System.out.println("Artikulationen not ok");
			System.out.println(gr.toStringCutVertices());
			System.out.println(TestMatrices.matrix_4x4_1Component_Artikulationen());
			System.out.println("Components: \n"+gr.toStringComponents());
		} 
		catch (Exception e){System.out.println(e.getMessage());	}

		try 
		{
			System.out.println("\n   TestMatrices.matrix_4x4_3Component()");
			Graph gr = new Graph();
			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_4x4_3Component()));
			System.out.println(gr.getAdjazensmatirx().toString());
			gr.setDistanztrix();
			System.out.println(gr.getDistanceMatrix());			
			gr.setWegmatrix();
			System.out.println(gr.getReachabilityMatrix());
			gr.calculateCutVertices();
			System.out.print(gr.toStringCutVertices());			
			System.out.print(gr.getReachabilityMatrix().getComponents()[4]+" Components: \n"+gr.toStringComponents());
		} 
		catch (Exception e){System.out.println(e.getMessage());	}
		
		try 
		{
			System.out.println("\n   matrix_8x8_3Component()");
			Graph gr = new Graph();
			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_8x8_3Component()));
			System.out.println(gr.getAdjazensmatirx().toString());
			gr.setDistanztrix();
			System.out.println(gr.getDistanceMatrix());			
			gr.setWegmatrix();
			System.out.println(gr.getReachabilityMatrix());
			gr.calculateCutVertices();
			System.out.print(gr.toStringCutVertices());			
			System.out.print(gr.getReachabilityMatrix().getComponents()[8]+" Components: \n"+gr.toStringComponents());
		} 
		catch (Exception e){System.out.println(e.getMessage());	}
		
		try 
		{
			System.out.println("\n   matrix_random_line(100)");
			Graph gr = new Graph();
			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_random_line(100)));
			System.out.println(gr.getAdjazensmatirx().toString());
			gr.setDistanztrix();
			System.out.println(gr.getDistanceMatrix());			
			gr.setWegmatrix();
			System.out.println(gr.getReachabilityMatrix());
			gr.calculateCutVertices();
			System.out.print(gr.toStringCutVertices());			
			System.out.print(gr.getReachabilityMatrix().getComponents()[100]+" Components: \n"+gr.toStringComponents());
		} 
		catch (Exception e){System.out.println(e.getMessage());	}

//		try
//		{
//			System.out.println("\n   TestMatrices.matrix_25x25_B()");
//			Graph gr = new Graph();
//			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_25x25_B()));
//			System.out.println(gr.getAdjazensmatirx().toString());
//			gr.setDistanztrix();
//			System.out.println(gr.getDistanceMatrix());			
//			if(gr.radiusToString().equals(TestMatrices.matrix_25x25_B_radius()))
//				System.out.println("Radius ok");
//			else
//				System.out.println("Radius not ok");
//			if(gr.diameterToString().equals(TestMatrices.matrix_25x25_B_durchmesser()))
//				System.out.println("Durchmesser ok");
//			else
//				System.out.println("Durchmesser not ok");
//			if(gr.centreToString().equals(TestMatrices.matrix_25x25_B_Zentrum()))
//				System.out.println("Zentrum ok");
//			else
//				System.out.println("Zentrum not ok");
//		}catch (MatrixException e){System.out.println(e.getMessage());}
	}


}


