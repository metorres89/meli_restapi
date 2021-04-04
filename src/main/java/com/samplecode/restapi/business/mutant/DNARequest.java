package com.samplecode.restapi.business.mutant;

import java.util.List;

public class DNARequest {
    private List<String> dnaList;
    private boolean isMutant = false;

    public DNARequest(List<String> dnaListValue, boolean isMutantValue)
    {
        dnaList = dnaListValue;
        isMutant = isMutantValue;
    }

    public List<String> getDNAList()
    {
        return dnaList;
    }

    public boolean getIsMutant()
    {
        return isMutant;
    }

    public List<String> setDNAList(List<String> dnaListValue)
    {
        dnaList = dnaListValue;
        return dnaList;
    }

    public boolean setIsMutant(boolean isMutantValue)
    {
        isMutant = isMutantValue;
        return isMutant;
    }
}
