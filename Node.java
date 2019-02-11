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
public class Node implements Comparable<Node> {
	String name; 
	boolean visited;
	Node prev;
	double lon;
	double lat; 
	double x;
	double y;
	double distance;
	boolean color;
	
 public Node(String n, double la, double lo) {
	 distance = -1;
	 name = n;
	 lon = lo;
	 lat = la;
	 x = convertLo(lo);
	 y = convertLa(la);
	 visited = false;
	 prev = null;
	 color = false;
 }
 
 public double convertLo(double lo) { //from stackoverflow https://stackoverflow.com/questions/14329691/convert-latitude-longitude-point-to-a-pixels-x-y-on-mercator-projection
	int mapWidth    = 600;
	//int mapHeight   = 600;
	return (lo+180)*(mapWidth/360);
 }
 
 public double convertLa(double la) {
	 int mapWidth    = 600;
	 int mapHeight   = 600;
	 double latRad = lat*Math.PI/180;
	 double mercN = Math.log((Math.tan(((Math.PI/4)+(latRad/2)))));
	 return (mapHeight/2)-(mapWidth*mercN/(2*Math.PI));
 }

@Override
public int compareTo(Node o) {
	if(StreetMap.shortest == true) {
	if (o.distance < this.distance) {
		return 1;
	}
	return -1;
}
	else {
		if (o.distance > this.distance) {
			return 1;
		}
		return -1;
	}
	
}
}

