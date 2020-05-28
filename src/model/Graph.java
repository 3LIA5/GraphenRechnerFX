package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph 
{
	private Matrix adjazensmatrix,wegMatrix,distanzMatrix;
	private String name;
	int radius, durchmesser, artikulationen, brueken, komponenten;
	private ArrayList<Integer> zentrum = new ArrayList<Integer>();
	
	public Graph(String name) 
	{
		setName(name);
	}
	public Graph() 
	{
		this("name");
		int[][] matrix = 
			new int[][] 
			{
				{0,1,0,1,0,1,0,0},
				{1,0,1,0,0,1,1,0},
				{0,1,0,0,0,0,0,0},
				{1,0,0,0,1,0,0,0},
				{0,0,0,1,0,1,0,0},
				{1,1,0,0,1,0,0,1},
				{0,1,0,0,0,0,0,1},
				{0,0,0,0,0,1,1,0}
			};
		setAdjazensmatirx(new Matrix(matrix));			
	}
	
	//-------------------------getter-----------------------

	public Matrix getAdjazensmatirx() 
	{
		return adjazensmatrix;
	}
	public Matrix getWegmatrix() 
	{
		return wegMatrix;
	}
	public Matrix getDistanzMatrix() 
	{
		return distanzMatrix;
	}
	public String getName() 
	{
		return name;
	}
	//-------------------------setter-----------------------
	public void setAdjazensmatirx(Matrix adjazensmatirx) 
	{
		this.adjazensmatrix = adjazensmatirx;
	}
	public void setWegmatrix(Matrix wegMatrix) 
	{
		this.wegMatrix = wegMatrix;
	}
	public void setDistanztrix(Matrix distanzMatrix) 
	{
		this.distanzMatrix = distanzMatrix;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	//-------------------------other-----------------------
	
	public void berechneDistanzmatrix() /* multipl. einmal zu oft !*/
	{
		int length = adjazensmatrix.getMatrix().length;
		distanzMatrix = new Matrix(length);
		int[][][] potenzMatrizen = new int[length][length][length];
		boolean didchange = false;
		for (int i=0; i<length;i++)
		{
			distanzMatrix.getMatrix()[i]=adjazensmatrix.getMatrix()[i].clone();
			potenzMatrizen[1][i]=adjazensmatrix.getMatrix()[i].clone();
		}
		for (int square=2; square<length; square++)
		{
			didchange=false;
			for (int ze=0; ze<length; ze++)
			{
				for (int sp=ze; sp<length; sp++)
				{
					int vortex = 0;
					for (int spZe=0;spZe<sp;spZe++)
						vortex += potenzMatrizen[square-1][spZe][sp]*adjazensmatrix.getMatrix()[ze][spZe];
					for (int spSp=sp; spSp<length; spSp++)
						vortex += potenzMatrizen[square-1][sp][spSp]*adjazensmatrix.getMatrix()[ze][spSp];
					potenzMatrizen[square][ze][sp]=vortex;
					if(distanzMatrix.getMatrix()[ze][sp]==0 && vortex!=0 && ze!=sp)
					{
						distanzMatrix.getMatrix()[ze][sp]=square;
						distanzMatrix.getMatrix()[sp][ze]=square;
						didchange=true;
					}
				}
			}
//			System.out.println("Potenz: "+square);
//			System.out.println(new Matrix(potenzMatrizen[square]).toString());
//			System.out.println(distanzMatrix.toString());
//			System.out.println("Potenz: "+square);
			if (!didchange)
				square=length;
		}
	}
	public void berechneWegmatrix()
	{
		int lenght=distanzMatrix.getMatrix().length;
		int[][] wMatrix = new int[lenght][lenght];
		for (int line=0; line<lenght; line++)
		{
			for (int column=0; column<lenght; column++)
			{
				if(distanzMatrix.getMatrix()[line][column]!=0)
					wMatrix[line][column]=1;
			}
			wMatrix[line][line]=1;
		}
		wegMatrix = new Matrix(wMatrix);
	}
	
	public void berechneDurchmesserRadiusZentrum()
	{
		int[] stat = new int[distanzMatrix.getMatrix().length];
		for (int vortex=0; vortex<distanzMatrix.getMatrix().length;vortex++)
		{
			stat[vortex]=Arrays.stream(distanzMatrix.getMatrix()[vortex]).summaryStatistics().getMax();
		}
		radius =  Arrays.stream(stat).summaryStatistics().getMin();
		durchmesser = Arrays.stream(stat).summaryStatistics().getMax();
		for (int i=0;i<stat.length;i++)
			if (stat[i]==radius)
			{
				zentrum.add(stat[i]);
			}
	}
	
	public void exportAdjazensmatirxCsv(String filename) throws GraphenRechnerException
	{
		if (filename != null)
		{
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
				bw.write(adjazensmatrix.toStringCsv());
				bw.close();
			}
			catch (IOException e)
			{
				throw new GraphenRechnerException("IOException beim Aufbau des FileWriter fuer "+filename);
			}
			
		}
		else
			throw new GraphenRechnerException("null-Ref für loadMitarbeiter(String filename)");
	}
}
