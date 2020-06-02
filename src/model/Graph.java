package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Graph 
{
	private AdjacencyMatrix adjacencyMatrix;
	private DistanceMatrix distanzMatrix;
	private ReachAbilityMatrix reachAbilityMatrix;
	private String name;
	
	public Graph(String name) 
	{
		setName(name);
	}
	public Graph() 
	{
		this("name");		
	}
	
//	-------------------------getter-----------------------

	public Matrix getAdjazensmatirx() 
	{
		return adjacencyMatrix;
	}
	public Matrix getWegmatrix() 
	{
		return reachAbilityMatrix;
	}
	public Matrix getDistanzMatrix() 
	{
		return distanzMatrix;
	}
	public String getName() 
	{
		return name;
	}

//	-------------------------setter-----------------------
	public void setAdjacencyMatrix(AdjacencyMatrix adjacencyMatrix) 
	{
		this.adjacencyMatrix = adjacencyMatrix;
	}
	public void setDistanztrix() throws MatrixException 
	{
		distanzMatrix = new DistanceMatrix(adjacencyMatrix.calculateDistanceMatrix());
	}
	public void setWegmatrix() throws MatrixException 
	{
		reachAbilityMatrix = new ReachAbilityMatrix(distanzMatrix.calculateReachAbilityMatrix());
	}	
	public void setName(String name) 
	{
		this.name = name;
	}
//	-------------------------other-----------------------
	public void exportAdjazensmatirxCsv(String filename) throws GraphException
	{
		if (filename != null)
		{
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
				bw.write(adjacencyMatrix.toStringCsv());
				bw.close();
			}
			catch (IOException e)
			{
				throw new GraphException("IOException beim Aufbau des FileWriter fuer "+filename);
			}
			
		}
		else
			throw new GraphException("null-Ref für loadMitarbeiter(String filename)");
	}
	public void importMatrixCsv(String filename, String delimiter) throws GraphException, MatrixException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filename));)
		{
			String line;			
			line = br.readLine();
			int count = 0;
			int countVortex = line.length()/2+1;
			String[] lines = new String[countVortex];
			while (line != null)
			{
				lines[count]=line;
				line = br.readLine();
				count++;
			}
//			for (String str: lines){System.out.println(' '+str);} //test lines
			int[][] aMatrix = new int [countVortex][countVortex];
			int l=0;
			int c=0;
			for (String vortex:lines)
			{
				c=0;
				for (String edge: vortex.split(delimiter))
				{	
//					System.out.println(edge+l+c); show edges
					aMatrix[l][c]=Integer.parseInt(edge);
					c++;
				}
				l++;
			}
			adjacencyMatrix = new AdjacencyMatrix(aMatrix);
		}
		catch (FileNotFoundException e)
		{
			throw new GraphException("FileNotFoundException beim Aufbau des FileReades fuer "+filename);
		}
		catch (IOException e)
		{
			throw new GraphException("IOException beim Aufbau des FIS fuer "+filename);
		}
	}
//	--------------------------- for testing -----------------------------------
	@SuppressWarnings("unused")
	private void matrixToString(int [][] matrix)
	{
		for(int[] i:matrix)
		{
			for(int a:i)
				System.out.print((String.format("%5d", a)));
			System.out.println("\n\n");
		}
	}
}
