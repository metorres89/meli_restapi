package com.samplecode.restapi.utils;

public class DNAHelper {
    private static int mutantSeqLength = 4;

    public boolean isMutant(String[] dna)
    {
        //Check if there are repeated letters
        int sequencesCounter = 0;

        for(int ind = 0; ind < dna.length; ind++)
        {
            sequencesCounter += countSequences(dna, new Vec2d(0, ind), new Vec2d(1, 0), mutantSeqLength); // horizontal sequences
            if(sequencesCounter > 1) return true;
            
            sequencesCounter += countSequences(dna, new Vec2d(ind, 0), new Vec2d(0, 1), mutantSeqLength); // vertical sequences
            if(sequencesCounter > 1) return true;
        }
        
        sequencesCounter += countSequences(dna, new Vec2d(0, 0), new Vec2d(1,1), mutantSeqLength); //main diagonal (top, left) -> (bottom, right)
        if(sequencesCounter > 1) return true;
        
        sequencesCounter += countSequences(dna, new Vec2d(dna.length - 1, 0), new Vec2d(-1,1), mutantSeqLength); //secondary diagonal (top, right) -> (bottom, left)
        if(sequencesCounter > 1) return true;

        //the other diagonals that could contain mutant sequences
        for(int ind = 1; ind <= dna.length - mutantSeqLength; ind++)
        {
            sequencesCounter += countSequences(dna, new Vec2d(0, ind), new Vec2d(1,1), mutantSeqLength); // "\"
            if(sequencesCounter > 1) return true;

            sequencesCounter += countSequences(dna, new Vec2d(ind, 0), new Vec2d(1,1), mutantSeqLength); // "\"
            if(sequencesCounter > 1) return true;

            sequencesCounter += countSequences(dna, new Vec2d(dna.length - 1 - ind, 0), new Vec2d(-1,1), mutantSeqLength); // "/"
            if(sequencesCounter > 1) return true;

            sequencesCounter += countSequences(dna, new Vec2d(dna.length - 1, ind), new Vec2d(-1,1), mutantSeqLength); // "/"
            if(sequencesCounter > 1) return true;
        }

        return sequencesCounter > 1;
    }

    private int countSequences(String[] dna, Vec2d iniPos, Vec2d dir, int sequenceLength)
    {
        int occurrences = 0;
        int dirScalar = 0;
        Vec2d curPos = new Vec2d(iniPos);
        Vec2d endPos = curPos.UnmutableAdd(dir.UnmutableScalarProduct(sequenceLength - 1));
        
        while(isInsideMatrix(endPos, dna))
        {
            
            dirScalar = 0;
            boolean outOfBounds = false;
            while(!outOfBounds && dna[iniPos.y].charAt(iniPos.x) == dna[curPos.y].charAt(curPos.x))
            {
                dirScalar++;
                curPos.MutableAdd(dir);
                outOfBounds = !isInsideMatrix(curPos, dna);
            }
    
            occurrences += dirScalar / sequenceLength;
            
            if(outOfBounds)
                break;

            endPos = curPos.UnmutableAdd(dir.UnmutableScalarProduct(sequenceLength - 1));
            iniPos = new Vec2d(curPos);
        }

        return occurrences;
    }

    private boolean isInsideMatrix(Vec2d pos, String[] matrix)
    {
        return pos.x >= 0 && pos.y >= 0 && pos.x < matrix.length && pos.y < matrix.length;
    }
}
