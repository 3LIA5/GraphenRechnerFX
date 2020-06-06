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
		

		try {System.out.println(                                              "\n--TestMatrices.matrix_4x4_3Component()--");
			Graph gr = new Graph(                                                              "matrix_4x4_3Component");
			gr.setAdjacencyMatrix(                             new AdjacencyMatrix(TestMatrices.matrix_4x4_3Component()));
			gr.setDistanztrix();gr.setReachAbilityMatrix();
			gr.setCutVertices();gr.setBridges();
			System.out.println("diameter       :"+(gr.toStringDiameter().equals   (TestMatrices.matrix_4x4_3Component_durchmesser())?"ok":"NOT OK!!"));
			System.out.println("radius         :"+(gr.toStringRadius().equals     (TestMatrices.matrix_4x4_3Component_radius())?"ok":"NOT OK!!"));
			System.out.println("centre         :"+(gr.toStringCentre().equals     (TestMatrices.matrix_4x4_3Component_Zentrum())?"ok":"NOT OK!!"));
			System.out.println("Artikulationen :"+(gr.toStringCutVertices().equals(TestMatrices.matrix_4x4_3Component_Artikulationen())?"ok":"NOT OK!!"));
			System.out.println("Brücken        :"+(gr.toStringBridges().equals    (TestMatrices.matrix_4x4_3Component_Bruecken())?"ok":"NOT OK!!"));
			System.out.print(gr.getReachabilityMatrix().getComponents()[gr.getReachabilityMatrix().getMatrix().length]+" Components: \n"+gr.toStringComponents());
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+gr.getName()+".csv");
		} catch (Exception e){System.out.println(e.getMessage());}
		
		try {System.out.println(                                              "\n--TestMatrices.matrix_8x8_3Component()--");
			Graph gr = new Graph(                                                              "matrix_8x8_3Component");
			gr.setAdjacencyMatrix(                             new AdjacencyMatrix(TestMatrices.matrix_8x8_3Component()));
			gr.setDistanztrix();gr.setReachAbilityMatrix();
			gr.setCutVertices();gr.setBridges();
			System.out.println("      diameter :"+(gr.toStringDiameter().equals   (TestMatrices.matrix_8x8_3Component_durchmesser())?"ok":"NOT OK!!"));
			System.out.println("        radius :"+(gr.toStringRadius().equals     (TestMatrices.matrix_8x8_3Component_radius())?"ok":"NOT OK!!"));
			System.out.println("        centre :"+(gr.toStringCentre().equals     (TestMatrices.matrix_8x8_3Component_Zentrum())?"ok":"NOT OK!!"));
			System.out.println("Artikulationen :"+(gr.toStringCutVertices().equals(TestMatrices.matrix_8x8_3Component_Artikulationen())?"ok":"NOT OK!!"));
			System.out.println("       Brücken :"+(gr.toStringBridges().equals    (TestMatrices.matrix_8x8_3Component_Bruecken())?"ok":"NOT OK!!"));
			System.out.print(gr.getReachabilityMatrix().getComponents()[gr.getReachabilityMatrix().getMatrix().length]+" Components: \n"+gr.toStringComponents());
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+gr.getName()+".csv");
		} catch (Exception e){System.out.println(e.getMessage());}
		
		try {System.out.println(                                              "\n--TestMatrices.matrix_4x4_1Component()--");
			Graph gr = new Graph(                                                              "matrix_4x4_1Component");
			gr.setAdjacencyMatrix(                             new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component()));
			gr.setDistanztrix();gr.setReachAbilityMatrix();
			gr.setCutVertices();gr.setBridges();
			System.out.println("diameter       :"+(gr.toStringDiameter().equals   (TestMatrices.matrix_4x4_1Component_durchmesser())?"ok":"NOT OK!!"));
			System.out.println("radius         :"+(gr.toStringRadius().equals     (TestMatrices.matrix_4x4_1Component_radius())?"ok":"NOT OK!!"));
			System.out.println("centre         :"+(gr.toStringCentre().equals     (TestMatrices.matrix_4x4_1Component_Zentrum())?"ok":"NOT OK!!"));
			System.out.println("Artikulationen :"+(gr.toStringCutVertices().equals(TestMatrices.matrix_4x4_1Component_Artikulationen())?"ok":"NOT OK!!"));
			System.out.println("Brücken        :"+(gr.toStringBridges().equals    (TestMatrices.matrix_4x4_1Component_Bruecken())?"ok":"NOT OK!!"));
			System.out.print(gr.getReachabilityMatrix().getComponents()[gr.getReachabilityMatrix().getMatrix().length]+" Components: \n"+gr.toStringComponents());
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+gr.getName()+".csv");
		} catch (Exception e){System.out.println(e.getMessage());}
		
		try {System.out.println(                                              "\n--TestMatrices.matrix_8x8_1Component()--");
			Graph gr = new Graph(                                                              "matrix_8x8_1Component");
			gr.setAdjacencyMatrix(                             new AdjacencyMatrix(TestMatrices.matrix_8x8_1Component()));
			gr.setDistanztrix();gr.setReachAbilityMatrix();
			gr.setCutVertices();gr.setBridges();
			System.out.println("      diameter :"+(gr.toStringDiameter().equals   (TestMatrices.matrix_8x8_1Component_durchmesser())?"ok":"NOT OK!!"));
			System.out.println("        radius :"+(gr.toStringRadius().equals     (TestMatrices.matrix_8x8_1Component_radius())?"ok":"NOT OK!!"));
			System.out.println("        centre :"+(gr.toStringCentre().equals     (TestMatrices.matrix_8x8_1Component_Zentrum())?"ok":"NOT OK!!"));
			System.out.println("Artikulationen :"+(gr.toStringCutVertices().equals(TestMatrices.matrix_8x8_1Component_Artikulationen())?"ok":"NOT OK!!"));
			System.out.println("       Brücken :"+(gr.toStringBridges().equals    (TestMatrices.matrix_8x8_1Component_Bruecken())?"ok":"NOT OK!!"));
			System.out.print(gr.getReachabilityMatrix().getComponents()[gr.getReachabilityMatrix().getMatrix().length]+" Components: \n"+gr.toStringComponents());
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+gr.getName()+".csv");
		} catch (Exception e){System.out.println(e.getMessage());}
		
		try {System.out.println(                                              "\n--TestMatrices.matrix_25x25_B()--");
			Graph gr = new Graph(                                                              "matrix_25x25_B");
			gr.setAdjacencyMatrix(                             new AdjacencyMatrix(TestMatrices.matrix_25x25_B()));
			gr.setDistanztrix();gr.setReachAbilityMatrix();
			gr.setCutVertices();gr.setBridges();
			System.out.println("      diameter :"+(gr.toStringDiameter().equals   (TestMatrices.matrix_25x25_B_durchmesser())?"ok":"NOT OK!!"));
			System.out.println("        radius :"+(gr.toStringRadius().equals     (TestMatrices.matrix_25x25_B_radius())?"ok":"NOT OK!!"));
			System.out.println("        centre :"+(gr.toStringCentre().equals     (TestMatrices.matrix_25x25_B_Zentrum())?"ok":"NOT OK!!"));
			System.out.println("Artikulationen :"+(gr.toStringCutVertices().equals(TestMatrices.matrix_25x25_B_Artikulationen())?"ok":"NOT OK!!"));
			System.out.println("       Brücken :"+(gr.toStringBridges().equals    (TestMatrices.matrix_25x25_B_Bruecken())?"ok":"NOT OK!!"));
			System.out.print(gr.getReachabilityMatrix().getComponents()[gr.getReachabilityMatrix().getMatrix().length]+" Components: \n"+gr.toStringComponents());
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+gr.getName()+".csv");
		} catch (Exception e){System.out.println(e.getMessage());}
		
		try {System.out.println(                                              "\n--TestMatrices.matrix_26x26_C()--");
			Graph gr = new Graph(                                                              "matrix_26x26_C");
			gr.setAdjacencyMatrix(                             new AdjacencyMatrix(TestMatrices.matrix_26x26_C()));
			gr.setDistanztrix();gr.setReachAbilityMatrix();
			gr.setCutVertices();gr.setBridges();
			System.out.println("      diameter :"+(gr.toStringDiameter().equals   (TestMatrices.matrix_26x26_C_durchmesser())?"ok":"NOT OK!!"));
			System.out.println("        radius :"+(gr.toStringRadius().equals     (TestMatrices.matrix_26x26_C_radius())?"ok":"NOT OK!!"));
			System.out.println("        centre :"+(gr.toStringCentre().equals     (TestMatrices.matrix_26x26_C_Zentrum())?"ok":"NOT OK!!"));
			System.out.println("Artikulationen :"+(gr.toStringCutVertices().equals(TestMatrices.matrix_26x26_C_Artikulationen())?"ok":"NOT OK!!"));
			System.out.println("       Brücken :"+(gr.toStringBridges().equals    (TestMatrices.matrix_26x26_C_Bruecken())?"ok":"NOT OK!!"));
			System.out.print(gr.getReachabilityMatrix().getComponents()[gr.getReachabilityMatrix().getMatrix().length]+" Components: \n"+gr.toStringComponents());
//			gr.exportAdjazensmatirxCsv("c:\\scratch\\"+gr.getName()+".csv");
		} catch (Exception e){System.out.println(e.getMessage());}
//		try 
//		{
//			System.out.println("\n   matrix_random_line(10)");
//			Graph gr = new Graph();
//			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_random_line(10)));
//			System.out.println(gr.getAdjazensmatirx().toString());
//			gr.setDistanztrix();
//			System.out.println(gr.getDistanceMatrix());			
//			gr.setReachAbilityMatrix();
//			System.out.println(gr.getReachabilityMatrix());
//			gr.calculateCutVertices();
//			System.out.print(gr.toStringCutVertices());			
//			System.out.print(gr.getReachabilityMatrix().getComponents()[10]+" Components: \n"+gr.toStringComponents());
//			gr.calculateBridges();
//			System.out.println(gr.toStringBridges());
//			
//		} 
//		catch (Exception e){System.out.println(e.getMessage());	}
//
//		try 
//		{
//			System.out.println("\n   TestMatrices.matrix_line(10)");
//			Graph gr = new Graph();
//			gr.setAdjacencyMatrix(new AdjacencyMatrix(TestMatrices.matrix_line(10)));
//			System.out.println(gr.getAdjazensmatirx().toString());
//			gr.setDistanztrix();
//			System.out.println(gr.getDistanceMatrix());			
//			gr.setReachAbilityMatrix();
//			System.out.println(gr.getReachabilityMatrix());
//			gr.calculateCutVertices();
//			System.out.print(gr.toStringCutVertices());			
//			System.out.print(gr.getReachabilityMatrix().getComponents()[10]+" Components: \n"+gr.toStringComponents());
//			gr.calculateBridges();
//			System.out.println(gr.toStringBridges());
//		} 
//		catch (Exception e){System.out.println(e.getMessage());	} 
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


