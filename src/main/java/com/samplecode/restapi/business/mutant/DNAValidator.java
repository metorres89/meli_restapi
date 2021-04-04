package com.samplecode.restapi.business.mutant;

import java.util.List;

import com.samplecode.restapi.controllers.exceptions.ForbiddenException;

public class DNAValidator extends DNABaseHandler {
    
    public DNAValidator(DNAHandler otherHandler) 
    {
        super(otherHandler);
    }

    public void handle(DNARequest request)
    {
        if(this.isValid(request.getDNAList()))
        {
            if(this.nextHandler != null)
                this.nextHandler.handle(request);
        }
        else
        {
            throw new ForbiddenException();
        }
    }

    private boolean isValid(List<String> dnaList)
    {
        //ensures that is not empty array
        if(dnaList.size() == 0)
            return false;
        
        //at least the first element length must be equal to the amount of rows
        if(dnaList.size() != dnaList.get(0).length())
            return false;

        //ensures that it's a square matrix
        if(!dnaList.stream().allMatch(dna -> dna.length() == dnaList.get(0).length()))
            return false;
        
        return true;
    }
}
