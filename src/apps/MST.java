package apps;

import structures.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import apps.PartialTree.Arc;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList L = new PartialTreeList();
		for(Vertex v : graph.vertices){
			PartialTree T = new PartialTree(v);
			MinHeap<Arc> P = new MinHeap<Arc>();
			Vertex.Neighbor tmp = v.neighbors;
			while(tmp != null){
				P.insert(new Arc(v, tmp.vertex, tmp.weight));
				tmp = tmp.next;
			}
			T.getArcs().merge(P);
			L.append(T);
		}
		return L;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		ArrayList<PartialTree.Arc> result = new ArrayList<PartialTree.Arc>();
		while(ptlist.size() != 1){	
			
//			Iterator<PartialTree> iter = ptlist.iterator();
//			while(iter.hasNext()){
//				System.out.println(iter.next());
//			}
//			System.out.println();
			
			//added is the current partial tree
			PartialTree added = ptlist.remove();
			
			//checks if arc a points to the same PTlist
			Arc a = null;
			for(int i=0;i<added.getArcs().size();i++){
				boolean cont = true;
				a = added.getArcs().getMin();
				added.getArcs().deleteMin();
				Iterator<PartialTree.Arc> arcIterator = added.getArcs().iterator();
				while(arcIterator.hasNext()){
					Arc b = arcIterator.next();
					if(a.v2.name.equals(b.v1.name)){
						cont = false;
						break;
					}
				}
				if(cont == true)
					break;
			}
			//adds a to results
			if(a == null){
				throw new NoSuchElementException("There are no arcs to this element. It cannot be part of the MST.");
			}
			result.add(a);
			PartialTree merged = ptlist.removeTreeContaining(a.v2);	
			added.merge(merged);
			ptlist.append(added);
		}
		return result;
	}
//	public static void main(String[] args) throws IOException{
//		Graph g = new Graph("graph2.txt");
//		g.print();
//		PartialTreeList ptList = initialize(g);
//		ArrayList<PartialTree.Arc> results = execute(ptList);
//		for(Arc a:results)
//			System.out.println(a);
//	}
}
