package simulator.view;

import java.awt.Dimension;
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
		ctrl.addObserver(this);
	}

	private void initGUI() 
	{
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));

		_currTime = new JLabel("Time: ");
		this.add(_currTime);
		_currTime.setPreferredSize(new Dimension (150, 15));

		_numOfBodies = new JLabel("| Bodies:  ");
		this.add(_numOfBodies);
		_numOfBodies.setPreferredSize(new Dimension (120, 15));

		_currLaws = new JLabel("| Laws:  ");
		this.add(_currLaws);
		_currLaws.setPreferredSize(new Dimension (530, 15));	// 150 + 120 + 530 = 800 (Dimensión de la ventana)

	}

	private void drawForce(String fLawsDesc)
	{
		if(fLawsDesc == null)
		{
			this._currLaws.setText("| Laws: None selected");
		}
		else
		{
			this._currLaws.setText("| Laws: " + fLawsDesc);
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc)
	{
		this._numOfBodies.setText("| Bodies: " + String.valueOf(bodies.size()));
		this._currTime.setText("Time: " + String.valueOf(time));
		drawForce(fLawsDesc);
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc)
	{
		this._numOfBodies.setText("| Bodies: " + String.valueOf(bodies.size()));
		this._currTime.setText("Time: " + String.valueOf(time));
		drawForce(fLawsDesc);
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) 
	{
		this._numOfBodies.setText("| Bodies: " + String.valueOf(bodies.size()));
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc)
	{
		drawForce(fLawsDesc);
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) 
	{
		this._currTime.setText("Time: " + String.valueOf(time));
	}
	
	@Override
	public void onDeltaTimeChanged(double dt)
	{	}

}