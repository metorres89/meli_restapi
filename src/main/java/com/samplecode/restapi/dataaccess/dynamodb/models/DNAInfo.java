package com.samplecode.restapi.dataaccess.dynamodb.models;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "dna_t1")
public class DNAInfo {
    private Integer id;
    private List<String> sequence;
    private boolean isMutant;

    @DynamoDBHashKey
    public Integer getId() {
        return id;
    }
    
    public Integer setId(Integer value)
    {
        id = value;
        return id;
    }

    @DynamoDBAttribute
    public List<String> getSequence() {
        return sequence;
    }

    public List<String> setSequence(List<String> value)
    {
        sequence = value;
        return sequence;
    }

    @DynamoDBAttribute
    public boolean getIsMutant() {
        return isMutant;
    }

    public boolean setIsMutant(boolean value)
    {
        isMutant = value;
        return isMutant;
    }

    public DNAInfo() {}

    public DNAInfo(Integer idValue, List<String> sequenceValue, boolean isMutantValue)
    {
        id = idValue;
        sequence = sequenceValue;
        isMutant = isMutantValue;
    }
}
