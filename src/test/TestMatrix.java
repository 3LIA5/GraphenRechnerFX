package test;
import model.*;

@SuppressWarnings("unused")
public class TestMatrix 
{

	public static void main(String[] args) 
	{
		try	{
			Matrix ma = new AdjacencyMatrix(TestMatrices.matrix_4x4_3Component());
			System.out.println(ma);
			System.out.println('\n'+TestMatrices.toStringMatrix(TestMatrices.matrix_4x4_3Component()));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(0)));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(1)));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(2)));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(3)));
		} catch (MatrixException e)	{System.out.println(e.getMessage());}
		
		try	{
			Matrix ma = new AdjacencyMatrix(TestMatrices.matrix_4x4_1Component());
			System.out.println(ma);
			System.out.println('\n'+TestMatrices.toStringMatrix(TestMatrices.matrix_4x4_1Component()));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(0)));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(1)));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(2)));
			System.out.println('\n'+TestMatrices.toStringMatrix(ma.removeVortex(3)));
		} catch (MatrixException e)	{System.out.println(e.getMessage());}
	}
}


