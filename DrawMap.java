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
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class DrawMap extends JFrame {
	
	int mapHeight;
	int mapWidth; 
	ArrayList<ArrayList<Node>> paths;

	public DrawMap(ArrayList<ArrayList<Node>> p) {
		if (StreetMap.file.equals("ur.txt")) {
			mapHeight = 550;
			mapWidth = 580;
		}
		else if (StreetMap.file.equals("monroe.txt")) {
			mapHeight = 800;
			mapWidth = 600;
		}
		else {
			mapHeight = 910;
			mapWidth = 600;
		}
		paths = p;
		this.setSize(mapHeight, mapWidth);
		this.setVisible(true);
		JPanel panel = new JPanel();
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void paint(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		g.setColor(Color.BLACK);
		g.setStroke(new java.awt.BasicStroke(1));
		double smallestLat = findSmallestLat();
		double latRange = findLatRange();
		double biggestLon = findBiggestLon();
		
		for (Node n : StreetMap.gph.graph.keySet()) {
			double x = (((n.lat - smallestLat) * 500)/latRange);
			double y = (((n.lon - biggestLon) * 500 * -1)/latRange);
			double xx = 500 - y;
			double yy = -x + 500;
			for (String s : StreetMap.points) {
				if (n.name.equals(s)) {
					g.setColor(new Color(0xCE3050));
					if (StreetMap.file.equals("ur.txt")) {
						g.drawOval((int) xx + 33, (int) yy + 43, 14, 14);
						g.fillOval((int) xx + 33, (int) yy + 43, 14, 14);
					}
					else if (StreetMap.file.equals("monroe.txt")) {
						g.drawOval((int) xx + 243, (int) yy + 43, 3, 3);
						g.fillOval((int) xx + 243, (int) yy + 43, 3, 3);
					}
					else {
						g.drawOval((int) xx + 393, (int) yy + 43, 3, 3);
						g.fillOval((int) xx + 393, (int) yy + 43, 3, 3);
					}
				}
			}
				//System.out.println(StreetMap.center.name);
				if(StreetMap.center!=null && n.name.equals(StreetMap.center.name)) {
					System.out.println(StreetMap.radius);
					g.setColor(Color.BLUE);
					xx = xx-(StreetMap.radius);
					yy = yy-(StreetMap.radius);
					if (StreetMap.file.equals("ur.txt")) {
						g.drawOval((int) xx + 33, (int) yy + 43, (int) StreetMap.radius*2, (int) StreetMap.radius*2);
						//g.fillOval((int) xx + 33, (int) yy + 43,(int) StreetMap.radius, (int) StreetMap.radius);
					}
					else if (StreetMap.file.equals("monroe.txt")) {
						g.drawOval((int) xx + 243, (int) yy + 43, (int) StreetMap.radius*2, (int) StreetMap.radius*2);
						//g.fillOval((int) xx + 243, (int) yy + 43,(int) StreetMap.radius, (int) StreetMap.radius);
					}
					else {
						g.drawOval((int) xx + 393, (int) yy + 43, (int)((StreetMap.radius*2)), (int)((StreetMap.radius*2)));
					}
				}
			g.setColor(Color.BLACK);
			for (Node no : StreetMap.gph.graph.get(n)) {
				double x2 = ((no.lat - smallestLat) * 500) / latRange;
				double y2 = ((no.lon - biggestLon) * 500 * -1) / latRange;
				double xx2 = 500 - y2;
				double yy2 = -x2+500;
				if (StreetMap.file.equals("ur.txt")) {
					g.drawLine((int) xx + 40, (int) yy + 50, (int) xx2 + 40, (int) yy2 + 50);
				}
				else if (StreetMap.file.equals("monroe.txt")) {
					g.drawLine((int) xx + 250, (int) yy + 50, (int) xx2 + 250, (int) yy2 + 50);
				}
				else {
					g.drawLine((int) xx + 400, (int) yy + 50, (int) xx2 + 400, (int) yy2 + 50);
				}
				g.setColor(Color.BLACK);
				g.setStroke(new java.awt.BasicStroke(1));
			}
		}
		for(ArrayList<Node> path : paths) {
		for(int i=0;i<path.size()-1; i++){
			g.setColor(new Color(0xCE3050));
			g.setStroke(new java.awt.BasicStroke(3));
			double x = (((path.get(i).lat - smallestLat) * 500)/latRange);
			double y = (((path.get(i).lon - biggestLon) * 500 * -1)/latRange);
			double xx = 500 - y;
			double yy = -x+500;
			double x2 = ((path.get(i+1).lat - smallestLat) * 500) / latRange;
			double y2 = ((path.get(i+1).lon - biggestLon) * 500 * -1) / latRange;
			double xx2 = 500 - y2;
			double yy2 = -x2+500;
			if (StreetMap.file.equals("ur.txt")) {
				g.drawLine((int) xx + 40, (int) yy + 50, (int) xx2 + 40, (int) yy2 + 50);
			}
			else if (StreetMap.file.equals("monroe.txt")) {
				g.drawLine((int) xx + 250, (int) yy + 50, (int) xx2 + 250, (int) yy2 + 50);
			}
			else {
				g.drawLine((int) xx + 400, (int) yy + 50, (int) xx2 + 400, (int) yy2 + 50);
			}
		}
		}
		
	}
	

	public double findSmallestLat() {
		Node small = (Node) (StreetMap.gph.graph.keySet().toArray())[0];
		double smallest = small.lat;
		for (Node n : StreetMap.gph.graph.keySet()) {
			if (n.lat < smallest) {
				smallest = n.lat;
			}
		}
		return smallest;
	}

	public double findLatRange() {
		double smallest = findSmallestLat();
		Node big = (Node) (StreetMap.gph.graph.keySet().toArray())[0];
		double biggest = big.lat;
		for (Node n : StreetMap.gph.graph.keySet()) {
			if (n.lat > biggest) {
				biggest = n.lat;
			}
		}
		double range = biggest - smallest;
		return range;
	}

	public double findBiggestLon() {
		Node big = (Node) (StreetMap.gph.graph.keySet().toArray())[0];
		double biggest = big.lon;
		for (Node n : StreetMap.gph.graph.keySet()) {
			if (n.lon > biggest) {
				biggest = n.lon;
			}
		}
		return biggest;
	}
}