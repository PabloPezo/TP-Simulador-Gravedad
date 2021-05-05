package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver, ActionListener
{
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private boolean _stopped;

	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;

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

		boton1 = createButton(new ImageIcon("resources/icons/open.png"), "Carga el fichero seleccionado");
		toolBar.add(boton1);

		toolBar.addSeparator();

		boton2 = createButton(new ImageIcon("resources/icons/physics.png"), "Cambia las leyes de fuerza");
		toolBar.add(boton2);

		toolBar.addSeparator();

		boton3 = createButton(new ImageIcon("resources/icons/run.png"), "Inicia la simulación");
		toolBar.add(boton3);

		boton4 = createButton(new ImageIcon("resources/icons/stop.png"), "Detiene la simulación");
		toolBar.add(boton4);



		//	 	toolBar.add (createButton(new ImageIcon("resources/icons/open.png"), "Archivos", "Carga el fichero seleccionado"));
		//		toolBar.addSeparator();
		//		toolBar.add (createButton(new ImageIcon("resources/icons/physics.png"), "Physics", "Cambia las leyes de fuerza"));
		//		toolBar.addSeparator();
		//		toolBar.add (createButton(new ImageIcon("resources/icons/run.png"), "Run", "Inicia la simulación"));
		//		toolBar.add (createButton(new ImageIcon("resources/icons/stop.png"), "Stop", "Detiene la simulación"));
		//toolBar.add (createButton(new ImageIcon("resources/icons/exit.png"), "Exit", "Cierra la simulación"));

		toolBar.add(new JLabel(" Steps: "));
		JSpinner steps = new JSpinner();
		steps.setSize(1, 1);
		toolBar.add(steps);

		toolBar.add(new JLabel(" Delta-Time: "));
		JTextField time = new JTextField();
		toolBar.add(time);


		toolBar.add(Box.createHorizontalGlue());

		boton5 = createButton(new ImageIcon("resources/icons/exit.png"), "Cierra la simulación");
		toolBar.add(boton5);

		mainFrame.add(mainPanel);
		mainFrame.setBounds(400, 300, 800, 90);
		
		
		mainFrame.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) { quit(); }

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		mainFrame.setVisible(true);
		mainPanel.setVisible(true);
	}

	@SuppressWarnings("unused")
	private void run_sim(int n) 
	{
		if ( n>0 && !_stopped ) 
		{
			try 
			{
				//_ctrl.run(1); // Modificar o hace run override
			} 
			catch (Exception e) 
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

	public JButton createButton(Icon route, String toolTip) 
	{
		JButton button  = new JButton();
		button.setIcon(route);
		button.setToolTipText(toolTip);
		button.addActionListener(this);
		return button;
	}

	private void quit()
	{
		int n = JOptionPane.showOptionDialog(null, "Are you sure to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {System.exit(0);}
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == boton1)
		{
			JFileChooser fileChooser = new JFileChooser();
			int seleccion = fileChooser.showOpenDialog(getParent());
			_ctrl.reset();	
			//_ctrl.loadBodies(seleccion);
		}
		else if (e.getSource() == boton2)
		{
			System.out.println("Physics");
		}
		else if (e.getSource() == boton3)
		{
			System.out.println("Run");
		}
		else if (e.getSource() == boton4)
		{
			_stopped = true;
		}
		else if(e.getSource() == boton5)
		{
			quit();
		}
	}
}

//https://es.stackoverflow.com/questions/130285/cambiar-tama%C3%B1o-o-estilo-de-botones-java
