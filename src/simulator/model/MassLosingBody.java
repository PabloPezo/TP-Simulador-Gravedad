package simulator.model;

import simulator.misc.Vector2D;

public class MassLosingBody extends Body
{
    private double lossFactor; 	// Factor perdida de masa
    private double lossFrequency; // Intervalo despues del cual pierde masa
    private double cont;

    public MassLosingBody(String id, Vector2D v, Vector2D p, double m, double lossFactor, double lossFrequency) 
    {
        super(id, v, p, m);
        this.lossFactor = lossFactor;
        this.lossFrequency = lossFrequency;
        cont = 0.0;
    }

    void move(double t)
    {
        super.move(t);

        cont += t;
        
        if (cont >= lossFrequency)
        {
            mass *= 1 - lossFactor;
        	cont = 0.0;
        }
    }
}