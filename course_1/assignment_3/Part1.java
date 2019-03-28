
/**
 * Write a description of Part1 here.
 * 
 * @derek 
 * @1.0.0
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int dnaSize = dna.length();
        int stopIndex = 0;
        int index = startIndex;
        
        while(stopIndex != -1){
            stopIndex = dna.indexOf(stopCodon, index+3);
            if(stopIndex == -1)
                break;
            if (dna.substring(startIndex, stopIndex).length() % 3 == 0)
                return stopIndex;
            index = stopIndex;
        }
        
        return dnaSize;
    }
    
    public String findGene(String dna){
        int atgCodon = dna.indexOf("ATG");
        if(atgCodon == -1)
            return "";
        int taaCodon = findStopCodon(dna, atgCodon, "TAA");   
        int tagCodon = findStopCodon(dna, atgCodon, "TAG");
        return (tagCodon == dna.length() && taaCodon == dna.length()) ? "" : dna.substring(atgCodon, Math.min(taaCodon,tagCodon)+3);
    }
    
    public void testFindStopCodon(){
        String one = "TATGTTTTTTTTTAATTAAT";
        String two = "GATGAAATATAA";
        String three = "ATGTAAATGATGATGATGTAAGTAGTAGTATAA";
        
        System.out.println(one + ":\n" + one.substring(1,findStopCodon(one,1,"TAA")+3));
        System.out.println(findStopCodon(one,findStopCodon(one,1,"TAA"),"TAA"));
        System.out.println(two + ":\n" + findStopCodon(two,1,"TAA"));
        System.out.println(three + ":\n" + three.substring(0,findStopCodon(three,0,"TAA")+3));
        System.out.println(three.substring(0,findStopCodon(three,findStopCodon(one,0,"TAA"),"TAA")+3));
        System.out.println(three.substring(0,findStopCodon(three,findStopCodon(three,findStopCodon(one,0,"TAA"),"TAA"),"TAA")+3));
    }
    
    public void testFindGene(){
        String one = "TAATAATAATAATAATAA";
        String two = "ATGGGGGGGGGGTATAGGTAG";
        String three = "ATGGCGTAATAG";
        String four = "ATGGGGGGGGGGGG";
        
        System.out.println(one + ":\n" + findGene(one));
        System.out.println(two + ":\n" + findGene(two));
        System.out.println(three + ":\n" + findGene(three));
        System.out.println(four + ":\n" + findGene(four));
    }
    
    public void printAllGenes(String dna){
        int dnaSize = dna.length();
        int atgCodon, taaCodon, tagCodon;
        int smallerCodon = 0;
        while(true){
            atgCodon = dna.indexOf("ATG",smallerCodon);
            if(atgCodon == -1)
                return;
            taaCodon = findStopCodon(dna, atgCodon, "TAA"); 
            tagCodon = findStopCodon(dna, atgCodon, "TAG");
            if(tagCodon == dnaSize && taaCodon == dnaSize)
                break;
            if(taaCodon < tagCodon){
                System.out.println(dna.substring(atgCodon,taaCodon+3));
                smallerCodon = taaCodon + 3;
            }
            else if(tagCodon < taaCodon){
                System.out.println(dna.substring(atgCodon,tagCodon+3));
                smallerCodon = tagCodon + 3;
            }
        }
		return;
    }
}
