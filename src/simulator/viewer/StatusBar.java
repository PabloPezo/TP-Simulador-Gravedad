package simulator.viewer;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver 
{
	private static final long serialVersionUID = 1L;
	private JLabel _currTime;
	private JLabel _currLaws; 
	private JLabel _numOfBodies; 

	StatusBar(Controller ctrl) 
	{
		initGUI();
		//ctrl.addObserver(this);
	}

	private void initGUI() // He arreglado cositas
	{
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));

		_currTime = new JLabel("Time: ");
		this.add(_currTime);
		this.setAlignmentX(567);

		_numOfBodies = new JLabel("Bodies:  ");
		this.add(_numOfBodies);


		_currLaws = new JLabel("Laws:  ");
		this.add(_currLaws);

	}


	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc)
	{
		this._numOfBodies.setText("Bodies: " + String.valueOf(bodies.size()));
		this._currLaws.setText("Laws: " + fLawsDesc);
		this._currTime.setText("Time: " + String.valueOf(time));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc)
	{
		this._numOfBodies.setText("Bodies: " + String.valueOf(bodies.size()));
		this._currLaws.setText("Laws: " + fLawsDesc);
		this._currTime.setText("Time: " + String.valueOf(time));
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) 
	{
		this._numOfBodies.setText("Bodies: " + String.valueOf(bodies.size()));

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