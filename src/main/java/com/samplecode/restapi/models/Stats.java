package com.samplecode.restapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
    @JsonProperty("count_mutant_dna")
    private int mutantCounter;
    @JsonProperty("count_human_dna")
    private int humanCounter;
    @JsonProperty("ratio")
    private float ratio;

    public Stats()
    {
        mutantCounter = 0;
        humanCounter = 0;
        ratio = 0;
    }

    public Stats(int mutantCounterValue, int humanCounterValue)
    {
        mutantCounter = mutantCounterValue;
        humanCounter = humanCounterValue;
        ratio = humanCounter > 0 ? (float)mutantCounter / humanCounter : 0.0f;
    }

    public int getMutantCounter()
    {
        return mutantCounter;
    }

    public int setMutantCounter(int value)
    {
        mutantCounter = value;
        return mutantCounter;
    }

    public int getHumanCounter()
    {
        return humanCounter;
    }

    public int setHumanCounter(int value)
    {
        humanCounter = value;
        return humanCounter;
    }

    public float getRatio()
    {
        return ratio;
    }

    public float setRatio(float value)
    {
        ratio = value;
        return ratio;
    }
}
