
/**
 * Write a description of Part1 here.
 * 
 * @derek
 * @1.0.0
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int index_start = dna.indexOf("ATG");
        if(index_start == -1)
            return "";
        int index_stop = dna.indexOf("TAA", index_start+3);
        if(index_stop == -1)
            return "";
        String substr = dna.substring(index_start, index_stop);
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
        System.out.println("DNA-1: " + dna1 + "\nResults: " + findSimpleGene(dna1));
        System.out.println("DNA-2: " + dna2 + "\nResults: " + findSimpleGene(dna2));
        System.out.println("DNA-3: " + dna3 + "\nResults: " + findSimpleGene(dna3));
        System.out.println("DNA-4: " + dna4 + "\nResults: " + findSimpleGene(dna4));
        System.out.println("DNA-5: " + dna5 + "\nResults: " + findSimpleGene(dna5));
    }
}
