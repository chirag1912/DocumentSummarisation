import java.io.IOException;

import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.stream.file.FileSinkImages.LayoutPolicy;
import org.graphstream.stream.file.FileSinkImages.OutputType;
import org.graphstream.stream.file.FileSinkImages.Resolutions;

public class GraphTest {
     public static void main(String args[]) throws IOException
     {
    	 
    	 DefaultGraph g = new DefaultGraph("my beautiful graph");
    	 FileSinkImages pic = new FileSinkImages(OutputType.PNG, Resolutions.VGA);
    	 
    	 pic.setLayoutPolicy(LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
    	 	
    	 g.addNode("A");
    	 g.addNode("B");
    	 g.addNode("C");
    	 
    	 g.addEdge("AB", "A", "B");
    	 g.addEdge("AC", "A", "C");
    	 g.addEdge("BC", "B", "C");
    	 g.display();
    	 pic.writeAll(g, "sample.png");
    	 
     }
}
