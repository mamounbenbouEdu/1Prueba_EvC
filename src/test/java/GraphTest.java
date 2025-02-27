import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.example.practica3.Graph;

public class GraphTest {

    @Test
    public void onePathFindsAPath() {
        System.out.println("\nTest onePathFindsAPath");
        System.out.println("----------------------");


        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);

        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);


        assertEquals(expectedPath, g.onePath(1, 4));
    }
}
