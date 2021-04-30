package simulator.model;

import java.util.List;

public interface SimulatorObserver 
{
	// NOTAS:
	// --------------------------------------------
	// bodies: Lista de cuerpos actual
	// b cuerpo
	// time tiempo actual del simulador
	// dt tiempo por paso actual del simulador
	// fLawsDesc string que describe las leyes de fuerza actuales (toString() de la ley de fuerza actual)
	// --------------------------------------------
	
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc);
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc);
	public void onBodyAdded(List<Body> bodies, Body b);
	public void onAdvance(List<Body> bodies, double time);
	public void onDeltaTimeChanged(double dt);
	public void onForceLawsChanged(String fLawsDesc);

}
