package com.samplecode.restapi.utils;

import java.util.List;

public class DNAHasher {
    
    public int getScalarFromDNAList(List<String> dnaList)
    {
        int scalar = 0;
        for(int ind = 0; ind < dnaList.size(); ind++)
            scalar += (getScalarFromDNAString(dnaList.get(ind)) * (ind + 1)) % Integer.MAX_VALUE;
        return scalar;
    }

    private int getScalarFromDNAString(String dna)
    {
        int scalar = 0;
        for(int ind = 0; ind < dna.length(); ind++)
            scalar += (getScalarFromChar(dna.charAt(ind)) * Math.pow(5, ind)) % Integer.MAX_VALUE;
        return scalar;
    }

    private int getScalarFromChar(char value)
    {
        switch(value)
        {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: return 0;
        }
    }
}
