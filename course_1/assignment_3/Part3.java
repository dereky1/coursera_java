
/**
 * Write a description of Part3 here.
 * 
 * @derek
 * @1.0.0
 *         String two = "ATGGGGCTAATAATCTAAATGATGTAAATGTAGTAG";
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int dnaSize = dna.length();
        int stopIndex = 0;
        int index = startIndex;
        
        while(stopIndex != -1){
            stopIndex = dna.indexOf(stopCodon, index+3);
            if(stopIndex == -1)
                break;
            if (dna.substring(startIndex, stopIndex+3).length() % 3 == 0)
                return stopIndex;
            index = stopIndex;
        }
        
        return dnaSize;
    }
    
    public void printAllGenes(String dna){
        int dnaSize = dna.length();
        int atgCodon = dna.indexOf("ATG");
        if(atgCodon == -1)
            return;
        int taaCodon = findStopCodon(dna, atgCodon, "TAA");   
        int tagCodon = findStopCodon(dna, atgCodon, "TAG");
        while(true){
            if(tagCodon == dnaSize && taaCodon == dnaSize)
                break;
            if(taaCodon != dnaSize){
                System.out.println(dna.substring(atgCodon,taaCodon+3));
                taaCodon = findStopCodon(dna, taaCodon+3, "TAA");  
            }
            if(tagCodon != dnaSize){
                System.out.println(dna.substring(atgCodon,tagCodon+3));
                tagCodon = findStopCodon(dna, tagCodon+3, "TAG");
            }
        }
    }
    
    public int countGenes(String dna){
        int count = 0;
        int dnaSize = dna.length();
        int atgCodon, taaCodon, tagCodon;
        int smallerCodon = 0;
        while(true){
            atgCodon = dna.indexOf("ATG",smallerCodon);
            if(atgCodon == -1)
                return count;
            taaCodon = findStopCodon(dna, atgCodon, "TAA"); 
            tagCodon = findStopCodon(dna, atgCodon, "TAG");
            if(tagCodon == dnaSize && taaCodon == dnaSize)
                break;
            if(taaCodon < tagCodon){
                count++;
                smallerCodon = taaCodon + 3;
            }
            else if(tagCodon < taaCodon){
                count++;
                smallerCodon = tagCodon + 3;
            }
        }
        return count;
    }
    
    public void testCountGenes(){
        String one = "ATGTAAGATGCCCTAGT";
        String two = "ATGGGGCTAATAATCTAAATGATGTAAATGTAGTAG";
        
        System.out.println(one + ":\n" + countGenes(one));
        System.out.println(two + ":\n" + countGenes(two));
    }
}
