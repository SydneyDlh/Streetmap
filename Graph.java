/*Contact information:
	Sydney Dlhopolsky
	sdlhopol
	118
	sdlhopol@u.rochester.edu
Partner information: 
	Liza Pressman 
	lpressma
	78
	lspressma@u.rochester.edu */
import java.applet.Applet;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph{
	HashMap<Node,LinkedList<Node>> graph;
	HashMap<String,Node> vNames;
	//HashMap<String, Double> roads;
	int mapWidth = 800;
	int mapHeight = 800;

	
	
	public Graph() {
		graph = new HashMap<Node,LinkedList<Node>>();
		vNames = new HashMap<String,Node>();
		//roads = new HashMap<String,Double>();
	}
	

	public void addNode(String n, double la, double lo) {
		Node no = new Node(n,la,lo);
		graph.put(no,new LinkedList<Node>());
		vNames.put(n, no);
	}

	public void addEdge(String n, String i, String j) {
		if (graph.containsKey(vNames.get(i)) && graph.containsKey(vNames.get(j))) {
		graph.get(vNames.get(i)).add(vNames.get(j));
		//roads.put(n,-1.0);
		graph.get(vNames.get(j)).add(vNames.get(i));
		//roads.put(n,-1.0);
		}
	}	



}
