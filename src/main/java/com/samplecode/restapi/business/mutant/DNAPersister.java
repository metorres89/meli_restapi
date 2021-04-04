package com.samplecode.restapi.business.mutant;

import java.util.List;
import java.util.Optional;

import com.samplecode.restapi.dataaccess.dynamodb.models.DNAInfo;
import com.samplecode.restapi.dataaccess.dynamodb.repositories.DNAInfoRepository;
import com.samplecode.restapi.utils.DNAHasher;


public class DNAPersister extends DNABaseHandler {
    
    DNAInfoRepository repository;
    DNAHasher hasher;

    public DNAPersister()
    {
        super(null);
        hasher = new DNAHasher();
    }

    public DNAPersister(DNAHandler otherHandler) {
        super(otherHandler);
        hasher = new DNAHasher();
    }
    
    public DNAPersister(DNAHandler otherHandler, DNAInfoRepository repositoryRef)
    {
        super(otherHandler);
        repository = repositoryRef;
        hasher = new DNAHasher();
    }

    public void handle(DNARequest request)
    {
        this.PersistDNA(request.getDNAList(), request.getIsMutant());
        if(this.nextHandler != null)
            this.nextHandler.handle(request);
    }

    private void PersistDNA(List<String> dnaList, boolean isMutant)
    {
        int dnaId = hasher.getScalarFromDNAList(dnaList);
		Optional<DNAInfo> dnaInfo = repository.findById(dnaId);

		if(!dnaInfo.isPresent())
		{
			DNAInfo newDNAInfo = new DNAInfo(dnaId, dnaList, isMutant);
        	repository.save(newDNAInfo);
		}
    }
}
