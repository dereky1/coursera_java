/**
 * Write a description of Part4 here.
 * 
 * @derek
 * @1.0.0
 */
import edu.duke.URLResource;

public class Part4 {
    public void findYoutube(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String s : ur.lines()){
            int startLink = s.indexOf("\"http");
            int isYoutube = s.toLowerCase().indexOf("youtube.com");
            int endLink = s.lastIndexOf("\"");
            if(isYoutube != -1){
                System.out.println(s.substring(startLink+1, endLink));
            }
        }
    }
}
