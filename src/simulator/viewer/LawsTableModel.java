package simulator.viewer;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.misc.Vector2D;

public class LawsTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private List<LawsInfo> infoLaws;
	
	private final String[] columnNames = {"Key", "Value", "Description"};
	private String[] column;
	
	private final String G = "";
	private final String g = "";
	private final Vector2D c = new Vector2D(0.0, 0.0);

	public LawsTableModel(JSONObject law) 
	{
		setInfo(law);
	}

	public void setInfo(JSONObject ley)
	{
		infoLaws = new ArrayList<LawsInfo>();
		this.column = new String[columnNames.length];
		String key;
		
		JSONObject j = new JSONObject();
		j = ley.getJSONObject("data");
		String type = ley.getString("type");
		
		switch (type)	//Dependiendo de la fuerza seleccionada, cambia los datos en la tabla
		{
			case "nlug":
				key = "G";
				infoLaws.add(new LawsInfo(key, G, j.getString(key)));
			break;
				
			case "mtfp":
				key = "g";
				infoLaws.add(new LawsInfo(key, g, j.getString(key)));
				
				key = "c";
				infoLaws.add(new LawsInfo(key, c.toString(), j.getString(key)));
			break;
				
			case "nf":
				infoLaws = null;
			break;
				
			default:
				infoLaws = null;
			break;
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
	
	public JSONObject selectedForce()	//Devuelve la fuerza seleccionada según los parámetros de la tabla
	{
		JSONObject o = new JSONObject();
		
		if (infoLaws != null)
		{
			for (int i = 0; i < infoLaws.size(); i++)
			{
				if (getValueAt(i, 0).equals("c"))
				{
					o.put("c", stringToVector((String) getValueAt(i, 1)));
				}
				else
				{
					o.put((String) getValueAt(i, 0), getValueAt(i, 1));
				}
			}
		}		
		return o;
	}
	
	private JSONArray stringToVector(String c)		//Lee un array de tipo [0.0,0.0] y lo pasa a un JSONArray
	{
		JSONArray j = new JSONArray();
		String aux = "";
		
		int i = 1;
		while (c.charAt(i) != ',')
		{
			aux += c.charAt(i);
			i++;
		}
		
		j.put(Double.parseDouble(aux));
		aux = "";
		i++;
		
		while (c.charAt(i) != ']')
		{
			aux += c.charAt(i);
			i++;
		}

		j.put(Double.parseDouble(aux));
		return j;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		this.column[0] = infoLaws.get(rowIndex).getKey();
		
		if (String.valueOf(infoLaws.get(rowIndex).getValue()) != "")	//Comprobar si está vacío para que entonces te ponga el valor por defecto en el builder
		{
			this.column[1] = String.valueOf(infoLaws.get(rowIndex).getValue());
		}
		else
		{
			this.column[1] = null;
		}
		this.column[2] = String.valueOf(infoLaws.get(rowIndex).getDesc());

		return this.column[columnIndex];
	}

	@Override
	public String getColumnName(int column) 
	{
		return columnNames[column].toString();
	}
}