# What is it

Ordering of the vertices in a directed acyclic graph (DAG) such that for every directed edge `(u -> v)`,
vertex `u` comes before `v` in the ordering.

I.e. given graph `{(u -> v), (u -> t), (t -> k)}` then the sort could output `[u, v, t, k]`.

# When to Use

* Scheduling tasks with dependencies.
* Resolving symbol dependencies in linkers.
* Determining the order of compilation tasks.
* Detect if a DAG contains cycle. Basically, problems like [CourseSchedule](../src/main/java/net/sr89/CourseSchedule.java).

# Basic algorithm

1. Calculate in-degree for each node.
2. Enqueue all nodes with in-degree 0.
3. While the queue is not empty:
   a. Dequeue a node, add it to the result.
   b. For each neighbor of the node:
      i. Reduce its in-degree by 1.
      ii. If in-degree becomes 0, enqueue it.
4. If the result contains all nodes, return the result. Otherwise, there is a cycle.
