# MST
>A program to compute MST of a graph
## Background:
This program is designed to use Tarjan and Cheriton's round-robin algorithm to compute a minimun spannning tree of a given undirected graph with positive edge weights. 
This is my favorite projecct I have completed to date.
## Resources:
The input graphs are stored in two plain text documents: graph1.txt and graph2.txt

The input files are organized such that the first line has some integer N which corresponds to the number of vertices. The next N lines are the names of the vertices.
All of the remaining lines represent edges. The edges are formatted so that the letters represent vertices and the integers are the edge weight of that specific edge.

graph1.txt:
```
9
A
B
C
D
E
F
G
H
I
A B 4
A H 8
B C 8
B H 11
C D 7
C F 4
C I 2
D E 9
D F 14
E F 10
F G 2
G H 1
G I 6
H I 7

```
Corresponding Graph created by graph1.txt:

![alt text](https://github.com/Bundar/MST/blob/master/graph1.png "Graph 1")
## Usage:
The MST class has a main method which serves as a driver to run the algorithm. When ran the main method prints out the edges that are part of the minimum spanning tree.
## Example Run:
Output of Run on graph 1:
```
9
A
B
C
D
E
F
G
H
I
A H 8
A B 4
B H 11
B C 8
B A 4
C I 2
C F 4
C D 7
C B 8
D F 14
D E 9
D C 7
E F 10
E D 9
F G 2
F E 10
F D 14
F C 4
G I 6
G H 1
G F 2
H I 7
H G 1
H B 11
H A 8
I H 7
I G 6
I C 2
(A B 4)
(C I 2)
(D C 7)
(E D 9)
(F G 2)
(H G 1)
(B C 8)
(F C 4)

```

The first part is just the graph being printed. The edges in parenthesis at the bottom are the edges that comprise the MST.

Here is the MST computed above visualized over the actual graph:

![alt text](https://github.com/Bundar/MST/blob/master/graph1MST.png "Graph 1 MST")
### Algorithm Steps:
1. First an empty list of partial trees is created.
2. Then, for each vertex in the graph, a partial tree is created and all of the edges connected to that vertex are stored in a MinHeap of type Arc. Each PartialTree is added to the PartialTreeList.
3. The first PartialTree is removed from the PartialTreeList and the highest priority arc (lowest edge weight) is removed from that PartialTree.
4. If the edge that was removed connects to a vertex that is already in the partial tree choose one that doesnt and proceed to next step.
5. The edge removed is part of the MST.
6. Next find the PartialTree containing the vertex that the arc chosen connects to, and combine it with the previous PartialTree. Append this new merged PartialTree to the end of the PartialTreeList and go back to step 3 until there is only one PartialTree left in the list.

## License:
(c) Dunbar Paul Birnie IV 2017
