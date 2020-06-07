package application;

import java.io.File;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.*;

public class RootBorderPane extends BorderPane
{
	private MenuBar menuBar;
	private Menu mDatei, mBerechnen, mAnsicht, mMatrix, mEigenschaften, mHilfe;
	private MenuItem miNeu, miLaden, miSpeichern, miImportAdjazensmatrixCsv, miExportieren, miBeenden,
					 miAdjazensmatrixAnsicht, miDistanzmatrixAnsicht, miWegmatrixAnsicht,
					 miViewRadiusDiameterCentre, miViewCutVertices, miViewBridges, miViewComponents, miViewAll,
					 miDistanzmatrix, miWegmatrix, miArtikulationen, miBruecken, miKomponenten,  
					 miUeber;
	private Graph graph;
	private TextArea textAreaCenter;
	private Text viewStats;
	
	public RootBorderPane()
	{
		initComponents();
		addComponents();
		addHandlers();
		initModel();
		disableComponents(true);
	}
	private void initComponents()
	{
		menuBar = new MenuBar();
		
		mDatei = new Menu("Datei");
		mBerechnen = new Menu("Berechnungen");
			mBerechnen.setVisible(false);
		mAnsicht = new Menu("Ansicht");
		mHilfe = new Menu("Hilfe");
		
		miNeu = new MenuItem("Neu");
			miNeu.setDisable(true);
		miLaden = new MenuItem("Laden");
			miLaden.setDisable(true);
		miSpeichern = new MenuItem("Speichern");
			miSpeichern.setDisable(true);
		miImportAdjazensmatrixCsv = new MenuItem("csv Import");
		miExportieren = new MenuItem("csv Export");
		miBeenden = new MenuItem("Beenden");
		
		
		miDistanzmatrix = new MenuItem("Distanzmatrix");
		miWegmatrix = new MenuItem("Wegmatrix");
		miArtikulationen = new MenuItem("Artikulationen");
		miBruecken = new MenuItem("Bruecken");
		miKomponenten = new MenuItem("Komponenten");

		mMatrix  = new Menu("Matrix");
			miAdjazensmatrixAnsicht = new MenuItem("Adjazensmatrix");
			miDistanzmatrixAnsicht = new MenuItem("Distanzmatrix");
			miWegmatrixAnsicht = new MenuItem("Wegmatrix");
		mEigenschaften = new Menu("Eigenschaften");
			miViewRadiusDiameterCentre = new MenuItem("Radius, Durchmesser, Zentrum");
			miViewCutVertices = new MenuItem("Artikulationen");
			miViewBridges = new MenuItem("Brücken");
			miViewComponents = new MenuItem("Komponenten");
			miViewAll = new MenuItem("Alle");
		miUeber = new MenuItem("Über");
		
		textAreaCenter = new TextArea();
			textAreaCenter.setFont(Font.font("Consolas", 12));
			textAreaCenter.setEditable(false);
		viewStats = new Text();
			viewStats.setFont(Font.font("Consolas", 9));
//			viewStats.setEditable(false);
//			viewStats.setVisible(false);
//			viewStats.prefWidthProperty().bind(this.widthProperty());
//			viewStats.setMaxWidth(150);
	}
	private void initModel()
	{
		graph = new Graph();
	}
	private void addComponents()
	{
		setTop(menuBar);
		
		menuBar.getMenus().addAll(mDatei,mBerechnen, mAnsicht, mHilfe);
		
		mDatei.getItems().addAll(miNeu, miLaden, miSpeichern, new SeparatorMenuItem(), miImportAdjazensmatrixCsv, miExportieren, new SeparatorMenuItem(), miBeenden);
		mBerechnen.getItems().addAll(miDistanzmatrix, miWegmatrix, miArtikulationen, miBruecken, miKomponenten);
		mAnsicht.getItems().addAll(mMatrix, mEigenschaften);
			mMatrix.getItems().addAll(miAdjazensmatrixAnsicht, miDistanzmatrixAnsicht, miWegmatrixAnsicht);
			mEigenschaften.getItems().addAll(miViewRadiusDiameterCentre, miViewCutVertices, miViewBridges, miViewComponents, miViewAll);
		mHilfe.getItems().addAll(miUeber);
		
		setCenter(textAreaCenter);
		setBottom(viewStats);
	}
	private void addHandlers()
	{
		miImportAdjazensmatrixCsv.setOnAction(event -> importAdjazensmatrixCsv());
		miAdjazensmatrixAnsicht.setOnAction(event -> viewMatrix(graph.getAdjazensmatirx()));
		miDistanzmatrixAnsicht.setOnAction(event -> viewMatrix(graph.getDistanceMatrix()));
		miWegmatrixAnsicht.setOnAction(event -> viewMatrix(graph.getReachabilityMatrix()));
		miDistanzmatrix.setOnAction(event -> berechneDistanzmatrix());
		miWegmatrix.setOnAction(event -> berechneWegmatrix());
		miBeenden.setOnAction( event -> beenden() );
		miUeber.setOnAction(event -> ueber() );	
		miViewRadiusDiameterCentre.setOnAction(event -> viewRadiusDiameterCentre()); 
		miViewCutVertices.setOnAction(event -> viewCutVertices()); 
		miViewBridges.setOnAction(event -> viewBridges()); 
		miViewComponents.setOnAction(event -> viewComponents());
		miViewAll.setOnAction(event -> viewAll());
	}
	
	public void disableComponents(boolean disabled)
	{
		mMatrix.setDisable(disabled);
		mEigenschaften.setDisable(disabled);
		miExportieren.setDisable(disabled);		
	}
//-------------------------------------- Handler-Methoden ---------------------------	

	private void importAdjazensmatrixCsv()
	{
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(System.getProperty("java.io.tmpdir")));
		File selected = fc.showOpenDialog(null);
		if (selected != null)
		{
			try
			{
				long start = System.nanoTime();
				graph.importMatrixCsv(selected.getAbsolutePath(),",",true);
//				textAreaCenter.setText("** calculating distance matrix\n");
//				graph.setDistanztrix();
//				textAreaCenter.appendText("** calculating reachability matrix\n");
//				graph.setReachAbilityMatrix();
//				textAreaCenter.appendText("** calculating cut-vertices\n");
//				graph.setCutVertices();
//				textAreaCenter.appendText("** calculating bridges");
//				graph.setBridges();
				viewMatrix(graph.getAdjazensmatirx());
				disableComponents(false);
				long need = (System.nanoTime() - start) / 1000000;
				viewStats.setText(new String("time to calculate:" +need+"mS"));
				
			}
			catch (GraphException e)
			{
				Main.showAlert(AlertType.ERROR, e.getMessage()+"\n"+e.getClass().getSimpleName());
			}
			catch (MatrixException e) 
			{
				Main.showAlert(AlertType.ERROR, e.getMessage()+"\n"+e.getClass().getSimpleName());
			}
		}
		else
			Main.showAlert(AlertType.INFORMATION, "Benutzer-Abbruch");
	}

	private void viewMatrix(Matrix matrix)
	{
		if (matrix==null)
			textAreaCenter.setText("\n - matrix not available -");
		textAreaCenter.setText(matrix.toString());
	}
	private void viewRadiusDiameterCentre()
	{
		textAreaCenter.setText(graph.toStringRadius());
		textAreaCenter.appendText(graph.toStringDiameter());
		textAreaCenter.appendText(graph.toStringCentre());
	}
	private void viewCutVertices()
	{
		textAreaCenter.setText(graph.toStringCutVertices());
	}
	private void viewBridges()
	{
		textAreaCenter.setText(graph.toStringBridges());
	}
	private void viewComponents()
	{
		try
		{
			textAreaCenter.setText(graph.toStringComponents());
		} 
		catch (MatrixException e) 
		{
			Main.showAlert(AlertType.ERROR, e.getMessage()+"\n"+e.getClass().getSimpleName());
		}
	}
	private void viewAll()
	{
		try
		{
			textAreaCenter.setText(graph.toStringRadius());
			textAreaCenter.appendText(graph.toStringDiameter());
			textAreaCenter.appendText(graph.toStringCentre());
			textAreaCenter.appendText(graph.toStringCutVertices());
			textAreaCenter.appendText(graph.toStringBridges());
			textAreaCenter.appendText(graph.toStringComponents());
		} 		
		catch (MatrixException e) 
		{
			Main.showAlert(AlertType.ERROR, e.getMessage()+"\n"+e.getClass().getSimpleName());
		}
	}

	private void berechneDistanzmatrix()
	{
		try 
		{
			graph.setDistanztrix();
			viewMatrix(graph.getDistanceMatrix());
			textAreaCenter.appendText(graph.toStringRadius());
			textAreaCenter.appendText(graph.toStringDiameter());
			textAreaCenter.appendText(graph.toStringCentre());
		} 
		catch (MatrixException e) 
		{
			Main.showAlert(AlertType.ERROR, e.getMessage()+"\n"+e.getClass().getSimpleName());
		}
	}
	private void berechneWegmatrix()
	{
		try 
		{
			graph.setReachAbilityMatrix();
			viewMatrix(graph.getReachabilityMatrix());
			textAreaCenter.appendText(graph.toStringComponents());
		} 
		catch (MatrixException e) 
		{
			Main.showAlert(AlertType.ERROR, e.getMessage()+"\n"+e.getClass().getSimpleName());
		}
	}
	
	private void ueber()
	{
		Main.showAlert(AlertType.INFORMATION, "\nGraphenProgramm  Version: 2.0\n\nCopyright  Elias Gross 6ACIF  2020");
	}
	private void beenden()
	{
		Platform.exit();
	}
}
