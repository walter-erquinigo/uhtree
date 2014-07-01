UHTree
======

The UHTree is a concurrent search tree based on the van Emde Boas tree which can store any kind of data.
It doesn't use any kind of tree rotations, which prevents the tree from having many thread locks, which is a disadvantage of many common search trees, such as Splay Trees, Treaps, etc.

The complexity of the operations for searching, deleting and inserting is O(log(log(n))). 

The memory complexity is O(n), although each node uses many variables internally. However, the speed efficiency worths it.

The tree is optimized for data access with Zipfian distribution. In this case, the most frequent elements achieve operation complexities with near O(1) time. 
For other data distributions or for less frequent elements, it's still O(log(log(n))). 

If you want to see how to test it, just read the ConcurrentTester class, which is the main tester for this tree.

This was part of a research I did in the Pontifical Catholic University of Pery during my undergrad. It was declared as the best undergrad research ever at my faculty :)
