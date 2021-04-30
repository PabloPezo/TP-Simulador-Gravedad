package simulator.view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;

public class ControlPanel extends JPanel implements SimulatorObserver
{
	// ...
	private Controller _ctrl;
	private boolean _stopped;
	ControlPanel(Controller ctrl)
	{
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	private void initGUI() 
	{
		// Crea todos los botones y esas cositas
	}
	// other private/protected methods
	// ...
	private void run_sim(int n)
	{
		if ( n>0 && !_stopped )
		{
			try 
			{
				//_ctrl.run(1);			// Hay que cambiar la función run o configurar un override
			} catch (Exception e)
			{
				// TODO show the error in a dialog box
				// TODO enable all buttons
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater( new Runnable() 
			{
				@Override
				public void run()
				{
					run_sim(n-1);
				}
			});
		} else 
		{
			_stopped = true;
			// TODO enable all buttons
		}
	}
	
	
	// ME LO HA GENERADO LA MAQUINITA
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
}

