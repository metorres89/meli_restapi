package com.samplecode.restapi.business.mutant;

import com.samplecode.restapi.utils.DNAHelper;

public class DNAMutantChecker extends DNABaseHandler {
    
    DNAHelper helper;

    public DNAMutantChecker(DNAHandler otherHandler) 
    {
        super(otherHandler);
        helper = new DNAHelper();
    }

    public void handle(DNARequest request)
    {
        String[] dnaArray = {};
		if(request.getDNAList() != null)
			dnaArray = request.getDNAList().toArray(new String[request.getDNAList().size()]);
        
        request.setIsMutant(helper.isMutant(dnaArray));

        if(this.nextHandler != null)
            this.nextHandler.handle(request);
    }
}
