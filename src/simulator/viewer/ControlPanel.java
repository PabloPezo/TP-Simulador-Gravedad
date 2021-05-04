package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver 
{
	private static final long serialVersionUID = 1L;
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
		JFrame mainFrame = new JFrame("PhysicsSimulator");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		JToolBar toolBar = new JToolBar();
		mainPanel.add(toolBar, BorderLayout.PAGE_START);


		toolBar.add (createButton(new ImageIcon("resources/icons/open.png"), "Archivos", "Carga el fichero seleccionado"));
		toolBar.addSeparator();
		toolBar.add (createButton(new ImageIcon("resources/icons/physics.png"), "Physics", "Cambia las leyes de fuerza"));
		toolBar.addSeparator();
		toolBar.add (createButton(new ImageIcon("resources/icons/run.png"), "Run", "Inicia la simulación"));
		toolBar.add (createButton(new ImageIcon("resources/icons/stop.png"), "Stop", "Detiene la simulación"));

		toolBar.add(new JLabel(" Steps: "));
		JSpinner steps = new JSpinner();
		steps.setSize(1, 1);
		toolBar.add(steps);
		
		toolBar.add(new JLabel(" Delta-Time: "));
		JTextField time = new JTextField();
		toolBar.add(time);
		
	
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add (createButton(new ImageIcon("resources/icons/exit.png"), "Exit", "Cierra la simulación"));
		
		mainFrame.add(mainPanel);
		mainFrame.setBounds(400, 300, 800, 90);
		
		
		mainFrame.setVisible(true);
		mainPanel.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	private void run_sim(int n) 
	{
		if ( n>0 && !_stopped ) 
		{
			try {
				//_ctrl.run(1); // Modificar o hace run override
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
		} 
		else 
		{
			_stopped = true;
			// TODO enable all buttons
		}
	}
	
	public JButton createButton(Icon route, String caption, String toolTip) 
	{

	    EventoEleccion eventoBotones = new EventoEleccion(this);
		JButton button  = new JButton();
		button.setIcon(route);
		button.setToolTipText(toolTip);
		button.addActionListener(eventoBotones);
		return button;
		
	}
	
	// Cositas que me pone solo
	
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
	
	public static void main(String[] args)
	{
		ControlPanel pepe = new ControlPanel(new Controller(null, null));
		pepe.initGUI();
	}
}

class EventoEleccion implements ActionListener
{
	private ControlPanel eleccion;
	private JButton boton1 = new JButton("Archivos");
	private JButton boton2 = new JButton("Physics");
	private JButton boton3 = new JButton("Run");
	private JButton boton4 = new JButton("Stop");
	private JButton boton5 = new JButton("Exit");

	public EventoEleccion(ControlPanel eleccion) 
	{
		this.eleccion = eleccion;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{   
		JButton source = (JButton)e.getSource();

		if (boton1 == source  )
		{
		}
		else if (boton2 == source )
		{

		}
		else if (boton3 == source )
		{

		}		
		else if (boton4 == source)
		{

		}
		else// if (boton5 == source)
		{
			System.out.println("funciona");
		}
	//	https://es.stackoverflow.com/questions/130285/cambiar-tama%C3%B1o-o-estilo-de-botones-java
	}

}