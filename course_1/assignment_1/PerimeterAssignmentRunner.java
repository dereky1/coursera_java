import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
;        int sum = 0;
        // Put code here
        for (Point p : s.getPoints()){
           sum++;
        }
        return sum;
    }

    public double getAverageLength(Shape s) {
        double sum = 0.0;
        Point prev = s.getLastPoint();
        // Put code here
        for (Point p : s.getPoints()){
            sum += p.distance(prev);
            prev = p;
        }
        return sum/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double lrg = 0.0;
        Point prev = s.getLastPoint();
        // Put code here
        for (Point p : s.getPoints()){
            if(p.distance(prev) > lrg)
                lrg = p.distance(prev);
            prev = p;
        }
        return lrg;
    }

    public double getLargestX(Shape s) {
        double lrg = 0.0;
        // Put code here
        for (Point p : s.getPoints()){
            if(p.getX() > lrg)
                lrg = p.getX();
        }
        return lrg;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double lrg = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > lrg)
                lrg = getPerimeter(s);
        }
        return lrg;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double lrg = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > lrg){
                lrg = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + getNumPoints(s));
        System.out.println("average length of sides = " + getAverageLength(s));
        System.out.println("largest side legnth = " + getLargestSide(s));
        System.out.println("largest X = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("largest perimeter = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("file with largest perimeter = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
