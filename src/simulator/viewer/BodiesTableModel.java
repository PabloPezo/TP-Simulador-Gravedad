package simulator.viewer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver 
{
	private static final long serialVersionUID = 1L;
	private List<Body> _bodies;
	private final String[] names = {"id", "mass", "position", "force"};
	private String[] column;
	
	BodiesTableModel(Controller ctrl) 
	{
		_bodies = new ArrayList<>();
		//ctrl.addObserver(this);
		this.column = new String[names.length];
	}

	@Override
	public int getColumnCount()
	{
		return names.length;
	}

	@Override
	public int getRowCount() 
	{
		return _bodies.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		this.column[0] = _bodies.get(rowIndex).getId().toString();
		this.column[1] = String.valueOf(_bodies.get(rowIndex).getMass());
		this.column[2] = String.valueOf(_bodies.get(rowIndex).getVelocity());
		this.column[3] = String.valueOf(_bodies.get(rowIndex).getPosition());
		this.column[4] = String.valueOf(_bodies.get(rowIndex).getForce());
		return this.column[columnIndex];
	}


	@Override
	public String getColumnName(int column) 
	{
		return names[column].toString();
	}

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