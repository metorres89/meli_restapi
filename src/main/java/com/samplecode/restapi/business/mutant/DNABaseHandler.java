package com.samplecode.restapi.business.mutant;

public abstract class DNABaseHandler implements DNAHandler{
    
    protected DNAHandler nextHandler;
    
    public DNABaseHandler()
    {
        nextHandler = null;
    }

    public DNABaseHandler(DNAHandler otherHandler)
    {
        nextHandler = otherHandler;
    }

    public void setNext(DNAHandler otherHandler)
    {
        nextHandler = otherHandler;
    }
}
