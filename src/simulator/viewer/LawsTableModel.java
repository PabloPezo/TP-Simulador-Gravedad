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
	private List<LawsInfo> info;
	
	private final String[] names = {"Key", "Value", "Description"};
	private String[] column;
	
	private final String nlug = "", g = "";
	private final Vector2D c = new Vector2D(0.0, 0.0);

	public LawsTableModel(JSONObject law) 
	{
		setInfo(law);
	}
	
	public void setInfo(JSONObject ley)
	{		
		info = new ArrayList<LawsInfo>();
		
		this.column = new String[names.length];
		
		String name = ley.getString("type");
		JSONObject j = new JSONObject();
		j = ley.getJSONObject("data");
		
		switch (name)
		{
			case "nlug":			
				info.add(new LawsInfo("G", nlug, j.getString("G")));
				break;
				
			case "mtfp":			
				info.add(new LawsInfo("g", g, j.getString("g")));
				info.add(new LawsInfo("c", c.toString(), j.getString("c")));
				break;
				
			case "nf":
				info = null;
				
			default:
				info = null;
		}
		fireTableStructureChanged();
	}
	
	public boolean isCellEditable(int row, int col)
	{
		return (col == 1);
	}

	public void setValueAt(Object object, int row, int col)
	{
		info.get(row).setValue((String) object);
		fireTableCellUpdated(row, col);
	}
	
	@Override
	public int getColumnCount()
	{
		return names.length;
	}

	@Override
	public int getRowCount() 
	{
		if (info != null)
		{
			return info.size();
		}
		else
		{
			return 0;
		}
	}
	
	public JSONObject selectedForce()
	{
		JSONObject o = new JSONObject();
		
		if (info != null)
		{
			for (int i = 0; i < info.size(); i++)
			{
				if (getValueAt(i, 0).equals("c"))
				{
					String c = (String) getValueAt(i, 1);
					
					System.out.println("parseC(c): " + parseC(c));
					
					o.put("c", parseC(c));
				}
				else
				{
					o.put((String) getValueAt(i, 0), getValueAt(i, 1));
				}
			}
		}		
		return o;
	}
	
	private JSONArray parseC(String c)
	{
		JSONArray j = new JSONArray();
		String aux = "";
		for (int i = 1; i < c.length(); i++)
		{
			if (c.charAt(i) != ',')
			{				
				aux = aux + c.charAt(i);
			}
			else
			{
				double d = Double.parseDouble(aux);
				
				j.put(d);
				aux = "";
			}
		}
		
		return j;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		this.column[0] = info.get(rowIndex).getKey();
		
		if (String.valueOf(info.get(rowIndex).getValue()) != "")
		{
			this.column[1] = String.valueOf(info.get(rowIndex).getValue());
		}
		else
		{
			this.column[1] = null;
		}
		
		this.column[2] = String.valueOf(info.get(rowIndex).getDesc());
		
		return this.column[columnIndex];
	}

	@Override
	public String getColumnName(int column) 
	{
		return names[column].toString();
	}
}