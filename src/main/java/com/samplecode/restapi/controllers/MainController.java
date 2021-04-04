package com.samplecode.restapi.controllers;

import com.samplecode.restapi.business.mutant.*;
import com.samplecode.restapi.controllers.exceptions.ForbiddenException;
import com.samplecode.restapi.dataaccess.dynamodb.repositories.DNAInfoRepository;
import com.samplecode.restapi.models.DNASequence;
import com.samplecode.restapi.models.Mutant;
import com.samplecode.restapi.models.Stats;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
    DNAInfoRepository dnaRepository;

	DNABaseHandler handlerChain;
	DNAMutantChecker mutantChecker;
	DNAPersister persister;

	@PostConstruct
    public void init(){
        // initialize your monitor here, instance of someService is already injected by this time.
		
		handlerChain = new DNAValidator(null); //1st handler -> DNAValidator: validates that the matrix is square.
		DNAMutantChecker mutantChecker = new DNAMutantChecker(null); //2nd handler -> DNAMutantChecker: checks if the dna is human or mutant.
		persister = new DNAPersister(null, dnaRepository); //3rd handler -> DNAPersister: saves the dna into DB.
		
		//creates handler chain
		handlerChain.setNext(mutantChecker);
		mutantChecker.setNext(persister);
    }

    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mutant greeting(@RequestBody DNASequence sequence) {
		DNARequest request = new DNARequest(sequence.getDNA(), false);
		handlerChain.handle(request);
		if(request.getIsMutant())
			return new Mutant(request.getIsMutant());
		else
			throw new ForbiddenException();
	}

	@GetMapping("/stats")
	public Stats greeting() {
		return new Stats(dnaRepository.countByIsMutantTrue(), dnaRepository.countByIsMutantFalse());
	}

}
