package com.samplecode.restapi;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.samplecode.restapi.dataaccess.dynamodb.DynamoDBConfig;
import com.samplecode.restapi.dataaccess.dynamodb.models.DNAInfo;
import com.samplecode.restapi.dataaccess.dynamodb.repositories.DNAInfoRepository;
import com.samplecode.restapi.utils.DNAHasher;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ApplicationContext.class})
@ContextConfiguration(classes = {DynamoDBConfig.class})
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { 
  "amazon.dynamodb.endpoint=http://localhost:8000/", 
  "amazon.aws.accesskey=test1", 
  "amazon.aws.secretkey=test231" })
public class DNAInfoRepositoryIntegrationTest 
{
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    DNAInfoRepository repository;

    @BeforeEach
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        if(!amazonDynamoDB.listTables().getTableNames().contains("dna_t1"))
        {
          CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(DNAInfo.class);
          tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
          amazonDynamoDB.createTable(tableRequest);
          //...
          dynamoDBMapper.batchDelete((List<DNAInfo>)repository.findAll());
        }
    }

    @Test
    public void testStoringDNASuccess() {

        DNAHasher hasher = new DNAHasher();

        List<String> dnaSeq = Arrays.asList(
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        );
        int id = hasher.getScalarFromDNAList(dnaSeq);

        DNAInfo DNAInfo = new DNAInfo(id, dnaSeq, true);
        repository.save(DNAInfo);

        //List<DNAInfo> result = (List<DNAInfo>) repository.findAll();

        Optional<DNAInfo> result = repository.findById(id);

        Assert.isTrue(result.isPresent(), "There should be at least one DNA Sequence stored!");
        Assert.isTrue(result.get().getSequence().get(0).equals("ATGCGA"), "Stored sequence must start with ATGCGA!");
    }
}