package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Graph 
{
	private AdjacencyMatrix adjacencyMatrix;
	private DistanceMatrix distanceMatrix;
	private ReachAbilityMatrix reachabilityMatrix;
	private String name;
	private int[] cutVertices;
	private int[][] bridges;
	
	public Graph(String name) 
	{
		setName(name);
	}
	public Graph() 
	{
		this("name");		
	}
	
//	-------------------------getter-----------------------
	public String getName() 
	{
		return name;
	}
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
		return distanceMatrix;
	}
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
	public int[] getCutVertices()
	{
		return cutVertices;
	}
	public int[][] getBridges()
	{
		return bridges;
	}
//	-------------------------setter-----------------------
	public void setName(String name) 
	{
		this.name = name;
	}
	public void setAdjacencyMatrix(AdjacencyMatrix adjacencyMatrix) 
	{
		this.adjacencyMatrix = adjacencyMatrix;
	}
	public void setAdjacencyMatrix(AdjacencyMatrix adjacencyMatrix, boolean setAll) throws MatrixException, GraphException 
	{
		this.adjacencyMatrix = adjacencyMatrix;
		if (setAll)
		{
			setDistanztrix();
			setReachAbilityMatrix();
			setCutVertices();
			setBridges();
		}
	}
	public void setDistanztrix() throws MatrixException 
	{
		distanceMatrix = new DistanceMatrix(adjacencyMatrix);
	}
	public void setReachAbilityMatrix() throws MatrixException 
	{
		reachabilityMatrix = new ReachAbilityMatrix(distanceMatrix);
	}
	public void setCutVertices() throws GraphException, MatrixException
	{
		int length = reachabilityMatrix.getMatrix().length;
		int[] isCutVertice = new int[length];
		for (int vortex=0; vortex<length; vortex++)
		{
			if(new ReachAbilityMatrix(new DistanceMatrix(new AdjacencyMatrix(adjacencyMatrix.removeVortex(vortex)))).getComponents()[length-1]>reachabilityMatrix.getComponents()[length])
				isCutVertice[vortex]=vortex;
			else
				isCutVertice[vortex]=0;
		}
		int count=0;
		for (int vortex=0; vortex<isCutVertice.length;vortex++)
			if(isCutVertice[vortex]!=0)
				count++;
		cutVertices=new int[count];
		count=0;
		for (int vortex=0; vortex<isCutVertice.length;vortex++)
			if(isCutVertice[vortex]!=0)
			{
				cutVertices[count]=isCutVertice[vortex];
				count++;
			}
	}
	public void setBridges() throws GraphException, MatrixException
	{
		int length = cutVertices.length;
		ArrayList<int[]>arrayBridges = new ArrayList<int[]>();
		for (int x=0; x<length; x++)
			for (int y=x+1; y<length; y++)
				if(adjacencyMatrix.getMatrix()[cutVertices[x]][cutVertices[y]]==1)
				{
					arrayBridges.add(new int[]{cutVertices[x],cutVertices[y]});
				}
		length=adjacencyMatrix.getMatrix().length;
		int count;
		int vortexY=-1;
		for (int vortexX=0; vortexX<length;vortexX++)
		{
			count=0;
			for (int column=0; column<length;column++)
			{
				if(adjacencyMatrix.getMatrix()[vortexX][column]==1)
				{
					count++;
					vortexY=column;
				}
			}
			if(count==1)
			{
				boolean iscutVertex = false;
//			    for (int i : cutVertices) 			  
//			        if (i == vortexX) 
//			        	iscutVertex = true;
				// if(components==1)
			    for (int i : cutVertices) 			  
			        if (i == vortexY) 
			        	iscutVertex = true;
			    if(iscutVertex)
			    {
					if(vortexX>vortexY)
						arrayBridges.add(new int[]{vortexY,vortexX});
					else
						arrayBridges.add(new int[]{vortexX,vortexY});
			    }
			    else
			    	if(vortexX<vortexY)
			    		arrayBridges.add(new int[]{vortexX,vortexY});
			    
			}
			
		}		
		bridges = new int[arrayBridges.size()][];
		for(int bridge=0; bridge<arrayBridges.size();bridge++)
			bridges[bridge]=arrayBridges.get(bridge).clone();
		Arrays.sort(bridges, Comparator.comparingInt(o -> o[0]));
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
	public void importMatrixCsv(String filename, String delimiter, boolean setAll) throws GraphException, MatrixException
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
			setAdjacencyMatrix(new AdjacencyMatrix(aMatrix), setAll);
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
		int countComponents = components[components.length-1]; 
		StringBuilder sbComponents = new StringBuilder();
		sbComponents.append(countComponents+" Component"+(countComponents==1?"":'s')+":\n");
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
			sbComponents.append("C"+component+"=({").append(sbVortex.toString()).append("}, {").append(sbEdges.toString()).append("})\n");
		}
		return sbComponents.toString();
	}
	public String toStringRadius()
	{
		if(reachabilityMatrix.getComponents()[reachabilityMatrix.getComponents().length-1]!=1)
			return new String("radius = "+"non sequitur"+'\n');
		return new String("radius = "+getRadius()+'\n');
	}
	public String toStringDiameter()
	{
		if(reachabilityMatrix.getComponents()[reachabilityMatrix.getComponents().length-1]!=1)
			return new String("diameter = "+"non sequitur"+'\n');
		return new String("diameter = "+getDiameter()+'\n');
	}
	public String toStringCentre()
	{
		if(reachabilityMatrix.getComponents()[reachabilityMatrix.getComponents().length-1]!=1)
			return new String("centre = "+"non sequitur"+'\n');
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
		for (int vortex:cutVertices)
		{
			sb.append(prefix).append(vortex+1);
			prefix=",";
		}
		sb.append("}\n");
		return sb.toString();
	}
	public String toStringBridges()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Brücken = {");
		String prefix="";
		for (int[] bridge:bridges)
		{
			sb.append(prefix).append(String.format("[%d,%d]",bridge[0]+1,bridge[1]+1));
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
