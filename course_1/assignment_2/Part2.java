
/**
 * Write a description of Part2 here.
 * 
 * @derek
 * @1.0.0
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon){
        boolean isLower = false;
        if(dna.startsWith("t") || dna.startsWith("g") || dna.startsWith("a") ||dna.startsWith("c")){
            isLower = true;
        }
        if(startCodon == -1){
            if(isLower){
                startCodon = dna.indexOf("atg");
                if(startCodon == -1)
                    return "";
            }
            else
                return "";
        }
        if(stopCodon == -1){
            if(isLower){
                stopCodon = dna.indexOf("taa", startCodon+3);
                if(stopCodon == -1)
                    return "";
            }
            else
                return "";
        }
        String substr = dna.substring(startCodon, stopCodon);
        if(substr.length()%3!=0)
            return "";
        return substr;
    }
    
    public void testSimpleGene(){
        String dna1 = "ATCCAATTTTGGGGTTAG";
        String dna2 = "GGTTAGATGAATGGGCCCGCGTCGGC";
        String dna3 = "TCGTCGTCGTCGTCGTCGTCGTCGTCG";
        String dna4 = "CCGGAATGACGTACGTAGCTGACTGATAAATTG";
        String dna5 = "CCGGGCGATGACGACGACAGTTAAGGGG";
        System.out.println("DNA-1: " + dna1 + "\nResults: " + findSimpleGene(dna1,dna1.indexOf("ATG"),dna1.indexOf("TAA",dna1.indexOf("ATG")+3)));
        System.out.println("DNA-2: " + dna2 + "\nResults: " + findSimpleGene(dna2,dna2.indexOf("ATG"),dna2.indexOf("TAA",dna2.indexOf("ATG")+3)));
        System.out.println("DNA-3: " + dna3 + "\nResults: " + findSimpleGene(dna3,dna3.indexOf("ATG"),dna3.indexOf("TAA",dna3.indexOf("ATG")+3)));
        System.out.println("DNA-4: " + dna4 + "\nResults: " + findSimpleGene(dna4,dna4.indexOf("ATG"),dna4.indexOf("TAA",dna4.indexOf("ATG")+3)));
        System.out.println("DNA-5: " + dna5 + "\nResults: " + findSimpleGene(dna5,dna5.indexOf("ATG"),dna5.indexOf("TAA",dna5.indexOf("ATG")+3)));
    }
}
