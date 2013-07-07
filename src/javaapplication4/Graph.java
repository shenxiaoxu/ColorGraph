package javaapplication4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
/**
* Implements an undirected, unweighted graph backed by an ArrayList (growable random-access array).
* @author Ari Trachtenberg
*/

public class Graph {
        // CONSTRUCTOR
        /**
        * Constructs a directed, unweighted graph with the given number of vertices.
        * @param vertices The number of vertices in the initial graph.
        */
        public Graph(int vertices) {
                numVertices=vertices;
                edgeList = new ArrayList<edge>();
                adjacencyList = new HashMap<Integer, Vector<edge>>();
                color = new ArrayList<Integer>(numVertices);
                for (int ii=0; ii<numVertices; ii++) // color all vertices -1
                color.add(-1);
        }
        
        // CLASSES
        
        /**  An edge in the graph. */
        public class edge {
                /**
                * Creates an edge from  to 
                * @param from The originating vertex for the edge (between 0 and numVertices-1 inclusive).
                * @param to The concluding vertex for the edge (between 0 and numVertices-1 inclusive).
                * @require wt >=0
                */
                public edge(int from, int to) { fromVertex = from; toVertex=to;}
                
                public int fromVertex; /** This is the vertex from which the edge originates. */
                public int toVertex;   /** This is the vertex to which the edge connects. */
        }
        
        // METHODS
        
        // ... QUERY
        /**
        * @return true iff there is an edge from vertexA to vertexB
        */
        /*public boolean adj(int vertexA, int vertexB) {
                boolean result = false;
                for (int ii=0; ii<edgeList.size(); ii++)
                if (edgeList.get(ii).fromVertex == vertexA &&
                edgeList.get(ii).toVertex == vertexB)
                result = true;
                return result;
        }*/
        public boolean adj(int vertexA, int vertexB){
            if(adjacencyList.containsKey(vertexA)){                
		for (int ii=0; ii< adjacencyList.get(vertexA).size(); ii++)
			if (adjacencyList.get(vertexA).get(ii).fromVertex == vertexA &&
			adjacencyList.get(vertexA).get(ii).toVertex == vertexB)
				return true;                
                return false;
            }else{
                return false;
            }            
        }
        
        /**
        * @return the number of vertices currently in the graph.
        */
        public int verts() {
                return numVertices;
        }
        
        /**
        * @param theVertex
        * @return The color assigned to the given vertex, or -1 if none have been assigned
        */
        public int getColor(int theVertex) {
                return color.get(theVertex);
        }
        
        
        /**
        * @return A human-readable version of the graph
        */
        public String print() {
                String result = "Vertex (color): neighbors\n-------------------------\n";
                for (int ii=0; ii<numVertices; ii++) {
                        result += ii + "(" + getColor(ii) + "): ";
                        for (int jj=0; jj<numVertices; jj++)
                        if (adj(ii,jj))
                        result += jj + " ";
                        result+="\n";
                }
                return result;
        }
        
        // ... MANIPULATION
        /**
        * Adds to the graph a directed edge from  to  with weight .
        * @param fromVertex The originating vertex for the edge.
        * @param toVertex The concluding vertex for the edge.
        */
        /*public void addEdge(int fromVertex, int toVertex) {
                edgeList.add(new edge(fromVertex, toVertex));
                edgeList.add(new edge(toVertex, fromVertex));
        }*/
        public void addEdge(int fromVertex, int toVertex){
            if(adjacencyList.containsKey(fromVertex)){
                Vector vector = adjacencyList.get(fromVertex);
                adjacencyList.remove(fromVertex);
                vector.add(new edge(fromVertex, toVertex));
                vector.add(new edge(toVertex, fromVertex));
                adjacencyList.put(fromVertex, vector);
            }else{
                Vector vector = new Vector<edge>();
                vector.add(new edge(fromVertex, toVertex));
                vector.add(new edge(toVertex, fromVertex));
                adjacencyList.put(fromVertex, vector);
            }
        }
        
        /**
        * Sets the color of a given vertex, unless a neighboring vertex already
        * has this color, in which case false is returned instead.
        * @param theVertex The vertex to color.
        * @param theColor The color to give the vertex.
        * @return true iff setting the vertex to this color does not violate the graph coloring.
        */
        public boolean setColor(int theVertex, int theColor) {
                // check for violation of coloring constraint
                boolean violation = false;
                for (int ii=0; ii<numVertices; ii++)
                if (theVertex!=ii && adj(theVertex,ii) && getColor(ii)==theColor)
                violation = true;
                
                if (!violation) {
                        color.set(theVertex, theColor);
                        return true;
                }
                else return false;
        }
        
        // DATA FIELDS
        protected int numVertices;            /** The number of vertices in the graph. */
        protected ArrayList<edge> edgeList;   /** The list of edges for the graph. */
        protected Map<Integer, Vector<edge>> adjacencyList;
        protected ArrayList<Integer> color;   /** The colors of the vertices. */
}