package com.samplecode.restapi.dataaccess.dynamodb.repositories;

import com.samplecode.restapi.dataaccess.dynamodb.models.DNAInfo;
import java.util.List;
import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

@EnableScan
@EnableScanCount
public interface DNAInfoRepository extends CrudRepository<DNAInfo, Integer> {
    
    Optional<DNAInfo> findById(Integer id);
    
    List<DNAInfo> findByIsMutantTrue();
    List<DNAInfo> findByIsMutantFalse();

    int countByIsMutantTrue();
    int countByIsMutantFalse();
}
