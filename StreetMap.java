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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;


public class StreetMap {
	static Graph gph = new Graph();
	static String file;
	static ArrayList<String> points = new ArrayList<String>();
	static double radius = -1;
	static Node center;
	static ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();
	static boolean shortest = true;
	
	public static Node convert(String s) {
		return gph.vNames.get(s);
	}
	
	public static ArrayList<Node> findShortestPath(Node start, Node end) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		ArrayList<Node> retList = new ArrayList<Node>();
		start.distance = 0;
		pq.add(start);
		start.visited = true;
		while(!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visited = true;
			for(Node adj : gph.graph.get(minDis)) { 
				if(!adj.visited) {
				double dis2 = distance(minDis,adj) + minDis.distance;
				if(!(minDis.distance==-1) && minDis.distance < dis2) {
					pq.remove(adj);
					adj.distance = dis2;
					adj.prev = minDis;
				}
				else if(minDis.distance == -1) {
					adj.distance = distance(minDis,adj);
					adj.prev = minDis;
				}
				pq.add(adj);
				adj.visited = true;
			}
			}
		}
		Node curr = end;
		if(curr.prev != null) {
			while(curr.prev!=null) {
					retList.add(0,curr);
					curr = curr.prev;
			}
			retList.add(0,curr);
		}
		return retList;	
	}
	public static ArrayList<Node> findLongestPath(Node start, Node end) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		ArrayList<Node> retList = new ArrayList<Node>();
		start.distance = 0;
		pq.add(start);
		start.visited = true;
		while(!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visited = true;
			for(Node adj : gph.graph.get(minDis)) { 
				if(!adj.visited) {
				double dis2 = distance(minDis,adj) + minDis.distance;
				if(!(minDis.distance==-1) && minDis.distance > dis2) {
					pq.remove(adj);
					adj.distance = dis2;
					adj.prev = minDis;
				}
				else if(minDis.distance == -1) {
					adj.distance = distance(minDis,adj);
					adj.prev = minDis;
				}
				pq.add(adj);
				adj.visited = true;
			}
			}
		}
		Node curr = end;
		if(curr.prev != null) {
			while(curr.prev!=null) {
					retList.add(0,curr);
					curr = curr.prev;
			}
			retList.add(0,curr);
		}
		return retList;	
	}
	public static void findPoints(Double r, Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		ArrayList<Node> retList = new ArrayList<Node>();
		start.distance = 0;
		pq.add(start);
		start.visited = true;
		while(!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visited = true;
			for(Node adj : gph.graph.get(minDis)) { 
				if(!adj.visited) {
				double dis2 = distance(minDis,adj) + minDis.distance;
				if(!(minDis.distance==-1) && minDis.distance < dis2) {
					pq.remove(adj);
					adj.distance = dis2;
					adj.prev = minDis;
				}
				else if(minDis.distance == -1) {
					adj.distance = distance(minDis,adj);
					adj.prev = minDis;
				}
				pq.add(adj);
				adj.visited = true;
			}
			}
		}
		for(Node no: gph.graph.keySet()) {
			//System.out.println(no.distance);
			if (no.distance *69 <= radius && no.distance >=0) {
				points.add(no.name);
			}
	}
	}
	public static void findPaths(Double r, Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		//ArrayList<Node> retList = new ArrayList<Node>();
		start.distance = 0;
		pq.add(start);
		start.visited = true;
		while(!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visited = true;
			for(Node adj : gph.graph.get(minDis)) { 
				if(!adj.visited) {
				double dis2 = distance(minDis,adj) + minDis.distance;
				if(!(minDis.distance==-1) && minDis.distance < dis2) {
					pq.remove(adj);
					adj.distance = dis2;
					adj.prev = minDis;
				}
				else if(minDis.distance == -1) {
					adj.distance = distance(minDis,adj);
					adj.prev = minDis;
				}
				pq.add(adj);
				adj.visited = true;
			}
			}
		}
		Node curr;
		for(Node no: gph.graph.keySet()) {
			if (no.distance *69 <= radius && no.distance >=0) {
				ArrayList<Node> p = new ArrayList<Node>();
				 curr = no;
				 p.add(0,no);
				 while(curr.prev!=null) {
					 curr=curr.prev;
					 p.add(0,curr);
				 }
				 paths.add(p);
			}
	}
	}
	/* public static void findMSPPaths(Double r, Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		//ArrayList<Node> retList = new ArrayList<Node>();
		start.distance = 0;
		pq.add(start);
		start.visited = true;
		while(!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visited = true;
			for(Node adj : gph.graph.get(minDis)) { 
				if(!adj.visited) {
				double dis2 = distance(minDis,adj) + minDis.distance;
				if(!(minDis.distance==-1) && minDis.distance < dis2) {
					pq.remove(adj);
					adj.distance = dis2;
					adj.prev = minDis;
				}
				else if(minDis.distance == -1) {
					adj.distance = distance(minDis,adj);
					adj.prev = minDis;
				}
				pq.add(adj);
				adj.visited = true;
			}
			}
		}
		Node curr;
		for(Node no: gph.graph.keySet()) {
			if (distance(center,no)*69 <= r && no.distance>=0) {
				ArrayList<Node> p = new ArrayList<Node>();
				 curr = no;
				 p.add(0,no);
				 while(curr.prev!=null) {
					 curr=curr.prev;
					 p.add(0,curr);
				 }
				 paths.add(p);
			}
	}
	}*/
	
	public static double milesTraveled(ArrayList<Node> path) {
		double distance = 0.0;
		for(int i=0;i<path.size()-1;i++) {
			distance+= 69* distance(path.get(i),path.get(i+1));
		}
		return distance;
	}
	
	public static double distance(Node n1, Node n2) {
		double a = n1.lat - n2.lat;
		double b = n1.lon - n2.lon;
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)); 
	}
	
	public static String time(double n) {
		int hours = 0;
		if(n<60) {
			//n = (int) n;
			String num = String.valueOf(n);
			if(num.length()<2) {
			return num.substring(0, 1) + " minutes";
			}
			return num.substring(0, 2) + " minutes";
		}
		else {
			while(n>=60) {
				hours++;
				n=n-60;
			}
			return hours + " hours " + time(n);
		}
	}
	/*public static void addPoints(double r, Node n) {
		for(: distances.keySet) {
			if(milesTraveled(findShortestPath(n,no)) <= r) {
				points.add(no.name);
			}
		}
	}*/
	public static void main(String[] args) throws FileNotFoundException {
		file = args[0];
		
		ArrayList<Node> l = new ArrayList<Node>();
		Scanner sc = new Scanner(new File(file));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.equals("i")) {
				gph.addNode(sc.next(), Double.parseDouble(sc.next()), Double.parseDouble(sc.next()));
			}
			if (s.equals("r")) {
				gph.addEdge(sc.next(), sc.next(), sc.next());
			}
		}

		if(args[1].equals("--show")) {
			if(args.length > 2) {
			if(args[2].equals("--directions")) {
				if(args[3].equals("--time")) {
					Node p1= gph.vNames.get(args[4]);
					Node p2= gph.vNames.get(args[5]);
					Double mph = Double.parseDouble(args[6]);
					l = findShortestPath(convert(args[4]),convert(args[5]));
					System.out.println("path: ");
					for(Node n: l) {
						System.out.println(n.name + " ");
					}
					System.out.println("you will get from: " + p1.name + " to: "+ p2.name + " in: " +  time(milesTraveled(l)/mph*60));
				}
				else {
				l = findShortestPath(convert(args[3]),convert(args[4]));
				System.out.println("path: ");
				for(Node n: l) {
					System.out.println(n.name + " ");
				}
				}
				
				System.out.println("Miles traveled: " + milesTraveled(l));
			}
			else if(args[2].equals("--miles")) {
				if(args[3].equals("--paths")) {
					radius = Double.parseDouble(args[4]);
					Node p1 = gph.vNames.get(args[5]);
					findPaths(radius, p1);
					System.out.println(paths.size() + " paths in " + radius + " miles from " + p1.name);
				}
				else {
				radius = Double.parseDouble(args[3]);
				//System.out.println(radius);
				Node p1 = gph.vNames.get(args[4]);
				findPoints(radius, p1);
				System.out.println("Points within " + radius + " miles: ");
				for(String p : points) {
					System.out.println(p);
				}
			}
			}
			else if(args[2].equals("--time")) {
				Node p1= gph.vNames.get(args[3]);
				Node p2= gph.vNames.get(args[4]);
				Double mph = Double.parseDouble(args[5]);
				l = findShortestPath(convert(args[3]),convert(args[4]));
				System.out.println("you will get from: " + p1.name + " to: "+ p2.name + " in: " +  time(milesTraveled(l)/mph*60));
				System.out.println("Miles traveled: " + milesTraveled(l));
			}
			/* else if(args[2].equals("--msp")) {
				radius = Double.parseDouble(args[3]);
				center = gph.vNames.get(args[4]);
				findMSPPaths(radius,center);
			} */
			else {
				for (int i = 2; i < args.length; i++) {
					points.add(args[i]);
				}
			}
			}
			paths.add(l);
			new DrawMap(paths);
		}
		if(args[1].equals("--directions")) {
			if(args[2].equals("--time")) {
				Node p1= gph.vNames.get(args[3]);
				Node p2= gph.vNames.get(args[4]);
				Double mph = Double.parseDouble(args[5]);
				l = findShortestPath(convert(args[3]),convert(args[4]));
				System.out.println("path: ");
				for(Node n: l) {
					System.out.println(n.name + " ");
				}
				System.out.println("you will get from: " + p1.name + " to: "+ p2.name + " in: " +  time(milesTraveled(l)/mph*60));
			}
			else {
			l = findShortestPath(convert(args[2]),convert(args[3]));
			}
			
			System.out.println("Miles traveled: " + milesTraveled(l));
		}
		
		/*
		l = findShortestPath(convert("GOERGEN-ATHLETIC"), convert("CSB"));
		System.out.println("Shortest Path:");
		for (Node n : l) {
			System.out.println(n.name);
		}
		System.out.println("Miles traveled: " + milesTraveled(l));*/
		
		//new DrawMap(l);
	}
}



