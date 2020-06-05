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
import javafx.stage.FileChooser;
import model.*;

public class RootBorderPane extends BorderPane
{
	private MenuBar menuBar;
	private Menu mDatei, mBerechnen, mAnsicht, mHilfe;
	private MenuItem miNeu, miLaden, miSpeichern, miImportAdjazensmatrixCsv, miExportieren, miBeenden,
					 miDistanzmatrix, miWegmatrix, miArtikulationen, miBruecken, miKomponenten,
					 miAdjazensmatrixAnsicht, miDistanzmatrixAnsicht, miWegmatrixAnsicht, miEigenschaften,
					 miUeber;
	private Graph graph;
	private TextArea viewMatrix;
	private TextArea viewStats;
	
	public RootBorderPane()
	{
		initComponents();
		addComponents();
		addHandlers();
		initModel();
	}
	private void initComponents()
	{
		menuBar = new MenuBar();
		
		mDatei = new Menu("Datei");
		mBerechnen = new Menu("Berechnungen");
		mAnsicht = new Menu("Ansicht");
		mHilfe = new Menu("Hilfe");
		
		miNeu = new MenuItem("Neu");
		miLaden = new MenuItem("Laden");
		miSpeichern = new MenuItem("Speichern");
		miImportAdjazensmatrixCsv = new MenuItem("csv Import");
		miExportieren = new MenuItem("csv Export");
		miBeenden = new MenuItem("Beenden");
		miNeu.setDisable(true);
		miLaden.setDisable(true);
		miSpeichern.setDisable(true);
		miImportAdjazensmatrixCsv.setDisable(false);
		miExportieren.setDisable(true);
		miBeenden.setDisable(false);
		
		miDistanzmatrix = new MenuItem("Distanzmatrix");
		miWegmatrix = new MenuItem("Wegmatrix");
		miArtikulationen = new MenuItem("Artikulationen");
		miBruecken = new MenuItem("Bruecken");
		miKomponenten = new MenuItem("Komponenten");
		miDistanzmatrix.setDisable(false);
		miWegmatrix.setDisable(false);
		miArtikulationen.setDisable(true);
		miBruecken.setDisable(true);
		miKomponenten.setDisable(true);
		
		miAdjazensmatrixAnsicht = new MenuItem("Adjazensmatrix");
		miDistanzmatrixAnsicht = new MenuItem("Distanzmatrix");
		miWegmatrixAnsicht = new MenuItem("Wegmatrix");
		miEigenschaften = new MenuItem("Eigenschaften");
		miAdjazensmatrixAnsicht.setDisable(false);
		miDistanzmatrixAnsicht.setDisable(false);
		miWegmatrixAnsicht.setDisable(false);
		miEigenschaften.setDisable(true);
		
		miUeber = new MenuItem("Über");
		
//		uebersicht = new MatrixUebersicht();
		viewMatrix = new TextArea();
			viewMatrix.setFont(Font.font("Consolas", 12));
			viewMatrix.setEditable(false);
		viewStats = new TextArea();
			viewStats.setFont(Font.font("Consolas", 12));
			viewStats.setEditable(false);
//			viewStats.prefWidthProperty().bind(this.widthProperty());
			viewStats.setMaxWidth(150);
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
		mAnsicht.getItems().addAll(miAdjazensmatrixAnsicht, miDistanzmatrixAnsicht, miWegmatrixAnsicht, miEigenschaften);
		mHilfe.getItems().addAll(miUeber);
		
		setCenter(viewMatrix);
		setLeft(viewStats);
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
		
	}
//-------------------------------------- Handler-Methoden ---------------------------	
	private void viewMatrix(Matrix matrix)
	{
		if (matrix==null)
			Main.showAlert(AlertType.ERROR, "Null Ref. : no Matrix available!");
		viewMatrix.setText(matrix.toString());
	}
	private void importAdjazensmatrixCsv()
	{
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(System.getProperty("java.io.tmpdir")));
		File selected = fc.showOpenDialog(null);  // modal
		if (selected != null)
		{
			try
			{
				graph.importMatrixCsv(selected.getAbsolutePath(),",");
//				ansichtMatrix(graph.getAdjazensmatirx());
//				disableComponents(false);
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
	private void berechneDistanzmatrix()
	{
		try 
		{
			graph.setDistanztrix();
			viewMatrix(graph.getDistanceMatrix());
			viewStats.appendText(graph.radiusToString());
			viewStats.appendText(graph.diameterToString());
			viewStats.appendText(graph.centreToString());
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
			graph.setWegmatrix();
			viewMatrix(graph.getReachabilityMatrix());
			viewStats.appendText(graph.toStringComponents());
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
