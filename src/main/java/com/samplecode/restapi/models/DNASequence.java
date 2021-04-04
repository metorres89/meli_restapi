package com.samplecode.restapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DNASequence {

    @JsonProperty("dna")
    private List<String> DNA;

    public DNASequence()
    {
    }

    public DNASequence(List<String> value)
    {
        DNA = value;
    }

    public List<String> getDNA()
    {
        return DNA;
    }

    public List<String> setDNA(List<String> value)
    {
        DNA = value;
        return DNA;
    }
}
