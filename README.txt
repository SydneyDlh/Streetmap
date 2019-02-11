Contact information:
	Sydney Dlhopolsky
	sdlhopol
	118
	sdlhopol@u.rochester.edu
Partner information: 
	Liza Pressman 
	lpressma
	78
	lspressma@u.rochester.edu
	
CS 172 Project 4 Street Mapping:
Synopsis: 
	Our code has all the functionality: it draws a map for the three text files and can find the shortest path between two given points. 
	In addition it draw a red line to show the shortest path on the drawn map. 
Structure: 
	Our main structure for the graph is in the Graph class. We have two hashmaps a hashmap with a Node and a LinkedList of nodes with all its adjacenys
	this is basically our adjaceny matrix. Because of this matrix we don't really use our edge class. We have a roads hashmap commented out just in case 
	we want to be able to access the road names for extra features. Our second hashmap has a string that is the name of the node and then the node itself
	so we can access the nodes from their names.
	The graph is made up of Nodes. The Node class constructs these. It gives them a distance of -1 to show that it hasnt been visited, also has a visited 
	attribute and name attribute and a prev attribute to help with our find shortest past algo. 
	The DrawMap class draws the map from the adjaceny matrix and then adds the red line from teh shortest path that the shortestpath algo returns. 
	StreetMap class has the shortest path method and calls drawmap. 
Obstacles:	
	The biggest obstacles we faced were dealing with nodes that had 
	already been visited and make sure they aren't repeated. We also had some trouble with some typos where we accidently changed the latitude and 
	didn't realize. 
Workload Distrubution:
	We worked on pretty much every part of the code together in pair programming paradigm. This way we both had equal share of the work and split time typing
	code and following along. 
Runtime:
	Drawing map -- O(N^2) this is because the time-heaviest method is a nested for loop. All helper functions have a runtime of N and aren't called in 
	a loop. To draw the shortest path it takes another N runtime. 
	Shortest Path -- O(ELogV) because for each vertex taken out of the priority queue (represented by logV-- because the priority queue makes this quicker) 
	we check every edge connected to that vertex through the priority queue hense why we multiply by the number of edges. 
EXTRA CREDIT STUFF YAY:
	For extra credit we implemented a few things:
		(1) TIME: You can type --show --directions --time place1 place2 mph (with or without show) and it will tell you how long it will take you to
		 get there in an easy to read format. This is really useful if you wanna know what time you have to leave one place on campus to get to another.
		  It also works for monroe county and NY state which is great aswell. 
		(2) POINTS IN M MILES: You can type --show --miles miles startPoint and it will show you all the points that you can get to by driving, or walking
		or biking or scootering that many miles.
		(3) PATHS IN M MILES: you can type --show --miles --paths and it will show you all the paths that you can get to by or walking
		or biking or scootering that many miles.
	