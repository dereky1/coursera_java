/**
 * Write a description of Part1 here.
 * 
 * @derek
 * @1.0.0
 */
import edu.duke.*;

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
    public StorageResource getAllGenes(String dna){
        StorageResource bin = new StorageResource();
        int dnaSize = dna.length();
        int atgCodon, taaCodon, tagCodon;
        int smallerCodon = 0;
        while(true){
            atgCodon = dna.indexOf("ATG",smallerCodon);
            if(atgCodon == -1)
                return bin;
            taaCodon = findStopCodon(dna, atgCodon, "TAA"); 
            tagCodon = findStopCodon(dna, atgCodon, "TAG");
            if(tagCodon == dnaSize && taaCodon == dnaSize)
                break;
            if(taaCodon < tagCodon){
                bin.add(dna.substring(atgCodon,taaCodon+3));
                smallerCodon = taaCodon + 3;
            }
            else if(tagCodon < taaCodon){
                bin.add(dna.substring(atgCodon,tagCodon+3));
                smallerCodon = tagCodon + 3;
            }
        }
        return bin;
    }
    public void testGetAllGenes(){
        for(String s : getAllGenes("ATGABCGCDTAAATGBCDRETRREERETAGATGATGATGTAA").data())
            System.out.println(s);
    }
    public double cgRatio(String dna){
        char[] chars = dna.toCharArray();
        int dnaSize = dna.length();
        double count = 0;
        for(int i=0; i<dnaSize; ++i){
            if(chars[i]=='C' || chars[i]=='G')
                count++;
        }
        return count/dnaSize;
    }
    public int countCTG(String dna){
        int count = 0;
        int index = dna.indexOf("CTG");
        while(index != -1){
            count++;
            index = dna.indexOf("CTG",index+3);
        }
        return count;
    }
    public void processGenes(StorageResource sr){
        int stringCount = 0, ratioCount = 0, longest = 0, count = 0;
        for(String s : sr.data()){
            count++;
            int sSize = s.length();
            if(sSize > 60 ){
                stringCount++;
                System.out.println(s);
            }
            if(cgRatio(s) > 0.35 ){
                ratioCount++;
                System.out.println(s);
            }
            if(sSize > longest)
                longest=sSize;
        }
        System.out.println("Gene sequence that are longer than 9 characters: " + stringCount);
        System.out.println("Gene sequence that have a cqration over 0.35: " + stringCount);
        System.out.println("Longest gene sequence length: " + longest);
        System.out.println("Number of gene sequences: " + count);
    }
    public void testProcessGenes(){
        StorageResource bin;
        /*String one = "ATCGGGGGGGGGTAAATGTAAATGGGGGGGTAGATGTAG";
        String two = "ATGTAA";
        String three = "ATGGCCTAG";
        String four = "ATGAAATAA";
        String five = "ATGakjnalkdsnaslkdasTAAATGGCCGCCGCCGCCTAGATGATGATGATGATGTAG";
        bin.add(one);
        bin.add(two);
        bin.add(three);
        bin.add(four);
        bin.add(five);*/
        FileResource fr = new FileResource("brca1line.fa");
        bin = getAllGenes(fr.asString().toUpperCase());
        processGenes(bin);
        
    }
}
