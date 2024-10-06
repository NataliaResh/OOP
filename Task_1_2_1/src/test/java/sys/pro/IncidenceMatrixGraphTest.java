package sys.pro;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing IncidenceMatrixGraph.
 */
public class IncidenceMatrixGraphTest {
    @Test
    public void buildFromFileTest() {
        try {
            IncidenceMatrixGraph matrix = new IncidenceMatrixGraph(
                    "build/resources/test/tests/1.in");
            Integer[] result = {2, 6};
            Assert.assertEquals(matrix.getNeighbours(3), result);
            Assert.assertNull(matrix.getNeighbours(10));
        } catch (IncorrectFormatException | IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void topSortTest() {
        try {
            IncidenceMatrixGraph matrix = new IncidenceMatrixGraph(
                    "build/resources/test/tests/1.in");
            Integer[] topSortMatrix = TopologicalSort.topSort(matrix);
            Integer[] ans = {5, 4, 3, 6, 2, 1};
            Assert.assertEquals(topSortMatrix, ans);
        } catch (IncorrectFormatException | IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void toStringTest() {
        try {
            IncidenceMatrixGraph matrix = new IncidenceMatrixGraph(
                    "build/resources/test/tests/1.in");
            String result = matrix.toString();
            String ans = "1: \n2: 1\n3: 2, 6\n4: 1\n5: 4\n6: \n";
            Assert.assertEquals(result, ans);
        } catch (IncorrectFormatException | IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void buildTest() {
        IncidenceMatrixGraph matrix = new IncidenceMatrixGraph();
        matrix.addNode(4);
        matrix.addNode(2);
        matrix.addEdge(2, 1);
        matrix.addEdge(4, 1);
        matrix.addEdge(5, 4);
        matrix.addEdge(3, 2);
        matrix.addNode(6);
        matrix.addEdge(3, 6);
        try {
            IncidenceMatrixGraph result = new IncidenceMatrixGraph(
                    "build/resources/test/tests/1.in");
            Assert.assertTrue(matrix.isEqual(result));
        } catch (IncorrectFormatException | IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void resizeTest() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addNode(1000);
        Assert.assertEquals(graph.getNodesCapacity(), 1024);
        Assert.assertTrue(graph.isConsistNode(1000));
    }

    @Test
    public void removeNodeTest1() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        int nodeCount = 500;
        for (int i = 0; i < nodeCount; i++) {
            graph.addNode(i);
            Assert.assertEquals(graph.getNodesCount(), i + 1);
        }
        for (int i = 0; i < nodeCount / 2; i++) {
            Assert.assertTrue(graph.removeNode(i));
        }
        Assert.assertEquals(graph.getNodesCount(), nodeCount / 2);
        for (int i = 0; i < nodeCount / 2; i++) {
            Assert.assertFalse(graph.isConsistNode(i));
        }
        for (int i = nodeCount / 2; i < nodeCount; i++) {
            Assert.assertTrue(graph.isConsistNode(i));
        }
    }

    @Test
    public void removeNodeTest2() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        Assert.assertFalse(graph.removeNode(4));
        Assert.assertTrue(graph.removeNode(1));
        Assert.assertFalse(graph.isConsistNode(1));
        Assert.assertTrue(graph.isConsistNode(2));
        Assert.assertTrue(graph.isConsistNode(3));
        Assert.assertTrue(graph.removeEdge(2, 3));
    }

    @Test
    public void removeEdgeTest() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        int nodeCount = 500;
        for (int i = 0; i < nodeCount; i++) {
            graph.addEdge(i, i + 1);
        }
        Assert.assertEquals(graph.getNodesCount(), nodeCount + 1);
        for (int i = 0; i < nodeCount / 2; i++) {
            Assert.assertFalse(graph.removeEdge(i + nodeCount, i + 1));
            Assert.assertFalse(graph.removeEdge(i, i + 1 + nodeCount));
            Assert.assertFalse(graph.removeEdge(i, i + 2));
            Assert.assertTrue(graph.removeEdge(i, i + 1));
        }
        Assert.assertEquals(graph.getNodesCount(), nodeCount + 1);
    }

    @Test
    public void noEqualTest1() {
        IncidenceMatrixGraph graph1 = new IncidenceMatrixGraph();
        IncidenceMatrixGraph graph2 = new IncidenceMatrixGraph();
        int nodeCount = 20;
        for (int i = 0; i < nodeCount; i++) {
            graph1.addEdge(i, i + 1);
            graph2.addEdge(i + 1, i + 2);
        }
        Assert.assertFalse(graph1.isEqual(graph2));
    }

    @Test
    public void noEqualTest2() {
        IncidenceMatrixGraph graph1 = new IncidenceMatrixGraph();
        IncidenceMatrixGraph graph2 = new IncidenceMatrixGraph();
        int nodeCount = 20;
        for (int i = 0; i < nodeCount; i++) {
            graph1.addEdge(i, i + 1);
            graph2.addEdge(i + 1, i + 2);
            graph2.addEdge(i + 1 + nodeCount, i + 2);
        }
        Assert.assertFalse(graph1.isEqual(graph2));
    }

    @Test
    public void noEqualTest3() {
        IncidenceMatrixGraph graph1 = new IncidenceMatrixGraph();
        IncidenceMatrixGraph graph2 = new IncidenceMatrixGraph();
        int nodeCount = 20;
        for (int i = 0; i < nodeCount; i++) {
            graph1.addEdge(0, i);
            graph2.addEdge(0, i + 1);
        }
        graph1.addNode(nodeCount);
        Assert.assertFalse(graph1.isEqual(graph2));
    }
}
