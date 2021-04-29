package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver 
{
	private List<Body> _bodies;
	
	BodiesTableModel(Controller ctrl)
	{
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
	}
	
	@Override
	public int getRowCount()
	{
		// TODO complete
		return 0;
	}
	@Override
	public int getColumnCount() 
	{
		// TODO complete
		return 0;
	}
	@Override
	public String getColumnName(int column)
	{
		// TODO complete
		return "";
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		// TODO complete
		return 0;
	}
	// SimulatorObserver methods

	
	// TODO ESTO ME LO HA GENERADO LA MAQUINITA
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDeltaTimeChanged(double dt) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) 
	{
		// TODO Auto-generated method stub
		
	}
}
