/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

/**
 *
 * @author XiaoxuShen
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph graph = new Graph(200);
        for(int ii = 1; ii <= 200; ii++)
            for(int jj = 1; jj <= 200; jj++){
                if(ii % 5 != 0){
                    graph.addEdge(ii, jj);
                }
            }
        System.out.println(colorGraph(graph));
        System.out.println(graph.print());        
    }
    static int colorGraph(Graph G) {
        int maxColor=0;                      // the maximum number of colors used so far
        for (int ii=0; ii<G.verts(); ii++) { // color each node
                int color=0;
                while (!G.setColor(ii, color))   // try every color
                if (++color > maxColor)        // note if I have used more colors than before
                maxColor=color;
        }
        return maxColor+1;
    }
}
