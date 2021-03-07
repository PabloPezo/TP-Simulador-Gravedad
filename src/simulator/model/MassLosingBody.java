package simulator.model;

import simulator.misc.Vector2D;

public class MassLosingBody extends Body{

    private double lossFactor; 	// Factor perdida de masa
    private double lossFrequency; // Intervalo despues del cual pierde masa
    private double accumulatedTime;

    public MassLosingBody(String id, Vector2D v, Vector2D p, double m, double lossFactor, double lossFrequency) {
        super(id, v, p, m);
        this.lossFactor=lossFactor;
        this.lossFrequency=lossFrequency;
        accumulatedTime=0.0;
    }

    void move(double t)
    {
        super.move(t);
        
        // Pierde masa si se mueve
        
        accumulatedTime += t;
        
        if (accumulatedTime >= lossFrequency)
        {
            accumulatedTime = 0.0;
            mass *= 1 - lossFactor;
        }
    }
}