package simulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import org.json.JSONObject;

public class LawsTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private List<LawsInfo> infoLaws;
	
	private final String[] columnNames = {"Key", "Value", "Description"};
	
	public LawsTableModel(JSONObject law) 
	{
		setInfo(law);
	}
	
	public void setInfo(JSONObject ley)
	{
		infoLaws = new ArrayList<LawsInfo>();
		
		JSONObject j = ley.getJSONObject("data");
		
		Set<String> aux = j.keySet();
		
		for(String s: aux)
		{ 
			infoLaws.add(new LawsInfo(s, "", j.getString(s)));
		}
		fireTableStructureChanged();
	}

	public boolean isCellEditable(int row, int col)		//Hace que solo se pueda editar la columna Values
	{
		return (col == 1);
	}

	public void setValueAt(Object object, int row, int col)		//Añades el valor en una casilla
	{
		infoLaws.get(row).setValue((String) object);
		fireTableCellUpdated(row, col);
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount() 
	{
		if (infoLaws != null)
		{
			return infoLaws.size();
		}
		else
		{
			return 0;
		}
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		String str = "";
		
		switch(col)
		{
			case 0:
				str = infoLaws.get(row).getKey();
				break;
				
			case 1:
				str = String.valueOf(infoLaws.get(row).getValue());	
				break;
				
			case 2:
				str = String.valueOf(infoLaws.get(row).getDesc());
				break;
		}
		return str;
	}

	@Override
	public String getColumnName(int column) 
	{
		return columnNames[column].toString();
	}
}