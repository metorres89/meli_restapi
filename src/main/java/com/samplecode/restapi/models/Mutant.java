package com.samplecode.restapi.models;

public class Mutant {
    
    private boolean isMutant;

    public Mutant()
    {
        isMutant = false; //by default
    }

    public Mutant(boolean value)
    {
        isMutant = value;
    }

    public boolean getIsMutant()
    {
        return isMutant;
    }

    public boolean setIsMutant(boolean value)
    {
        isMutant = value;
        return isMutant;
    }
}
