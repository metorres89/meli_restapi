package com.samplecode.restapi.business.mutant;

public interface DNAHandler 
{
    public void setNext(DNAHandler otherHandler);
    public void handle(DNARequest request);
}
