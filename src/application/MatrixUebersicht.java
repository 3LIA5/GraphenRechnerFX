package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MatrixUebersicht extends TableView<int[]> 
{
	public void updateAndShow(int[][] matrizen)
	{
		getColumns().clear();
		getItems().clear();
		setFixedCellSize(19.0);
	    int length = matrizen.length;

	    for (int i = 0 ; i < length ; i++) 
	    {
	        TableColumn<int[], Number> vortex = new TableColumn<>(Character.toString(((char)(65+i))));
	        final int columnIndex = i ;
	        vortex.setCellValueFactory(cellData -> 
											        {
											            int[] row = cellData.getValue();
											            return new SimpleIntegerProperty(row[columnIndex]);
											        });
	        vortex.setPrefWidth(19d);
	        vortex.setStyle("-fx-alignment: CENTER");
	        getColumns().add(vortex);
	    }
	    for (int i = 0 ; i < length ; i++) 
	    {
	        getItems().add(matrizen[i]);
	    }
	}
}
