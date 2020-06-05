package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph 
{
	private AdjacencyMatrix adjacencyMatrix;
	private DistanceMatrix distanceMatrix;
	private ReachAbilityMatrix reachabilityMatrix;
	private String name;
	private boolean[] cutVertices;
	
	public Graph(String name) 
	{
		setName(name);
	}
	public Graph() 
	{
		this("name");		
	}
	
//	-------------------------getter-----------------------

	public AdjacencyMatrix getAdjazensmatirx()
	{
//		if (adjacencyMatrix==null)
//			throw new GraphException("no adjacency Matrix available!");
		return adjacencyMatrix;
	}
	public ReachAbilityMatrix getReachabilityMatrix() 
	{
//		if (reachabilityMatrix==null)
//			throw new GraphException("no reachability Matrix available!");
		return reachabilityMatrix;
	}
	public DistanceMatrix getDistanceMatrix()
	{
//		if (distanceMatrix==null)
//			throw new GraphException("no distance Matrix available!");
		return distanceMatrix;
	}
	public String getName() 
	{
		return name;
	}
	public boolean[] getCutVertices()
	{
		return cutVertices;
	}

//	-------------------------setter-----------------------
	public void setAdjacencyMatrix(AdjacencyMatrix adjacencyMatrix) 
	{
		this.adjacencyMatrix = adjacencyMatrix;
	}
	public void setDistanztrix() throws MatrixException 
	{
		distanceMatrix = new DistanceMatrix(adjacencyMatrix);
	}
	public void setWegmatrix() throws MatrixException 
	{
		reachabilityMatrix = new ReachAbilityMatrix(distanceMatrix);
	}	
	public void setName(String name) 
	{
		this.name = name;
	}
//	------------------------- calculations -----------------------	
	public int getRadius()
	{
		return Arrays.stream(distanceMatrix.getSummary()).summaryStatistics().getMin();
	}
	public int getDiameter()
	{
		return Arrays.stream(distanceMatrix.getSummary()).summaryStatistics().getMax();
	}
	public int[] getCentre()
	{
		ArrayList<Integer> centre = new ArrayList<Integer>();
		for (int i=0;i<distanceMatrix.getSummary().length;i++)
			if (distanceMatrix.getSummary()[i]==getRadius())
			{
				centre.add(i+1);
			}
		return centre.stream().mapToInt(i->i).toArray();
		//Info: compiler does: mapToInt( (Integer i) -> i.intValue() ) 
	}

	public void calculateCutVertices() throws GraphException, MatrixException
	{
		int length = reachabilityMatrix.getMatrix().length;
		boolean[] isCutVertice = new boolean[length];
		for (int vortex=0; vortex<length; vortex++)
		{
			if(new ReachAbilityMatrix(new DistanceMatrix(new AdjacencyMatrix(adjacencyMatrix.removeVortex(vortex)))).getComponents()[length-1]>reachabilityMatrix.getComponents()[length])
				isCutVertice[vortex]=true;
			else
				isCutVertice[vortex]=false;
		}
//		for (int count=0; )
		cutVertices = isCutVertice;
////		-----------------
//		int[] cutVertices = new int[4];
//		for (int vortex=0; vortex<cutVertices.length;vortex++)
//			if(cutVertices[vortex])
//			{
//				sb.append(prefix).append(vortex+1);
//				prefix=",";
//			}
//		sb.append("}\n");
//		return sb.toString();
	}
//	//work in progress!!!!!!!!!!!!!!!!!!!!!
//	public void calculateBridges() throws GraphException, MatrixException
//	{
//		int length = reachabilityMatrix.getMatrix().length;
//		ArrayList<int[]>bridges = new ArrayList<int[]>();
//		for (int vortexX=0; vortexX<length; vortexX++)
//			for (int vortexY=0; vortexY<length; vortexY++)
//				if(new ReachAbilityMatrix(new DistanceMatrix(new AdjacencyMatrix(adjacencyMatrix.removeEdge(vortexX, vortexY)))).getComponents()[length-1]>reachabilityMatrix.getComponents()[length])
//					bridges.add(new int[]{vortexX,vortexY});
//	}
	
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
//	---------------------------   toString   -----------------------------------	
	public String toStringComponents() throws MatrixException
	{
		int[] components = reachabilityMatrix.getComponents();
		StringBuilder sbComponents = new StringBuilder();
		StringBuilder sbVortex;
		StringBuilder sbEdges;
		String prefix;
		for (int component=1; component<=components[components.length-1];component++)
		{
			sbVortex = new StringBuilder();
			sbEdges = new StringBuilder();
			prefix="";
			for(int vortex=0; vortex<components.length-1; vortex++)
			{
				if (components[vortex]==component)
				{
					sbVortex.append(prefix).append((vortex+1));
					String edges= adjacencyMatrix.toStringEdgesOfVortexTo(vortex+1);
					if(!edges.equals(""))
						sbEdges.append(prefix).append(edges);
					prefix=",";
				}
				
			}
			sbComponents.append("K"+component+"= ({").append(sbVortex.toString()).append("},{").append(sbEdges.toString()).append("})\n");
		}
		return sbComponents.toString();
	}
	public String radiusToString()
	{
		return new String("radius = "+getRadius()+'\n');
	}
	public String diameterToString()
	{
		return new String("diameter = "+getDiameter()+'\n');
	}
	public String centreToString()
	{
		int radius = getRadius();
		StringBuilder sb = new StringBuilder();
		sb.append("centre = {");
		String prefix = "";
		for (int vortex=0; vortex<distanceMatrix.getSummary().length;vortex++)
		{
			if(distanceMatrix.getSummary()[vortex]==radius)
			{
				sb.append(String.format("%s%d",prefix, vortex+1));
				prefix=",";
			}
		}
		sb.append("}\n");
		return sb.toString();
	}
	public String toStringCutVertices()
	{
		StringBuilder sb = new StringBuilder();
		String prefix="";
		sb.append("Artikulationen = {");
		for (int vortex=0; vortex<cutVertices.length;vortex++)
			if(cutVertices[vortex])
			{
				sb.append(prefix).append(vortex+1);
				prefix=",";
			}
		sb.append("}\n");
		return sb.toString();
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
