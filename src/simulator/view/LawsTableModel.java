package simulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
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
	
	public JSONObject selectedForce()	//Devuelve la fuerza seleccionada según los parámetros de la tabla
	{
		JSONObject o = new JSONObject();
		
		if (infoLaws != null)
		{
			for (int i = 0; i < infoLaws.size(); i++)
			{
				if (getValueAt(i, 0).equals("c"))
				{
					System.out.println("getval");
					
					if(getValueAt(i,1) != null)
					{
						System.out.println("getvalue del null = " + getValueAt(i,1));
						
						System.out.println("ola");
						
						String olo = (String) getValueAt(i,0);
						
						o.put(olo, stringToVector((String) getValueAt(i, 1)));
					}
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
	public Object getValueAt(int row, int col)
	{
		Object obj = null;
		
		switch(col)
		{
			case 0:
				obj = infoLaws.get(row).getKey();
				break;
				
			case 1:
				if (String.valueOf(infoLaws.get(row).getValue()) != "")	//Comprobar si está vacío para que entonces te ponga el valor por defecto en el builder
				{
					obj = String.valueOf(infoLaws.get(row).getValue());
				}
				else
				{
					obj = null;
				}			
				break;
				
			case 2:
				obj = String.valueOf(infoLaws.get(row).getDesc());
				break;
		}
		return obj;
	}

	@Override
	public String getColumnName(int column) 
	{
		return columnNames[column].toString();
	}
}