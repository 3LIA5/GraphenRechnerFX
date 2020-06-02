package application;

import java.io.File;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.*;

public class RootBorderPane extends BorderPane
{
	private MenuBar menuBar;
	private Menu mDatei, mBerechnen, mAnsicht, mHilfe;
	private MenuItem miNeu, miLaden, miSpeichern, miImportieren, miExportieren, miBeenden,
					 miDistanzmatrix, miWegmatrix, miArtikulationen, miBruecken, miKomponenten,
					 miAdjazensmatrixAnsicht, miDistanzmatrixAnsicht, miWegmatrixAnsicht, miEigenschaften,
					 miUeber;
	
	private Graph graph;	
	private MatrixUebersicht uebersicht;
	
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
		miImportieren = new MenuItem("csv Importieren");
		miExportieren = new MenuItem("csv Exportieren");
		miBeenden = new MenuItem("Beenden");
		miNeu.setDisable(true);
		miLaden.setDisable(true);
		miSpeichern.setDisable(true);
		miImportieren.setDisable(false);
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
		
		uebersicht = new MatrixUebersicht();
	}
	private void initModel()
	{
		graph = new Graph();
	}
	private void addComponents()
	{
		setTop(menuBar);
		
		menuBar.getMenus().addAll(mDatei, mBerechnen, mAnsicht, mHilfe);
		
		mDatei.getItems().addAll(miNeu, miLaden, miSpeichern, new SeparatorMenuItem(), miImportieren, miExportieren, new SeparatorMenuItem(), miBeenden);
		mBerechnen.getItems().addAll(miDistanzmatrix, miWegmatrix, miArtikulationen, miBruecken, miKomponenten);
		mAnsicht.getItems().addAll(miAdjazensmatrixAnsicht, miDistanzmatrixAnsicht, miWegmatrixAnsicht, miEigenschaften);
		mHilfe.getItems().addAll(miUeber);
		
		setCenter(uebersicht);
	}
	private void addHandlers()
	{
		miImportieren.setOnAction(event -> importAdjazensmatrixCsv());
		miAdjazensmatrixAnsicht.setOnAction(event -> ansichtMatrix(graph.getAdjazensmatirx().getMatrix()));
		miDistanzmatrixAnsicht.setOnAction(event -> ansichtMatrix(graph.getDistanzMatrix().getMatrix()));
		miWegmatrixAnsicht.setOnAction(event -> ansichtMatrix(graph.getWegmatrix().getMatrix()));
		miDistanzmatrix.setOnAction(event -> berechneDistanzmatrix());
		miWegmatrix.setOnAction(event -> berechneWegmatrix());
		miBeenden.setOnAction( event -> beenden() );
		miUeber.setOnAction(event -> ueber() );	
	}
//-------------------------------------- Handler-Methoden ---------------------------	
	private void ansichtMatrix(int[][] matrix)
	{
		uebersicht.updateAndShow(matrix);
	}
	private void berechneWegmatrix()
	{
		try 
		{
			graph.setWegmatrix();
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
		} 
		catch (MatrixException e) 
		{
			Main.showAlert(AlertType.ERROR, e.getMessage()+"\n"+e.getClass().getSimpleName());
		}
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
				uebersicht.updateAndShow(graph.getAdjazensmatirx().getMatrix());
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
	
	private void ueber()
	{
		Main.showAlert(AlertType.INFORMATION, "\nGraphenProgramm  Version: 2.0\n\nCopyright  Elias Gross 6ACIF  2020");
	}
	private void beenden()
	{
		Platform.exit();
	}
}
