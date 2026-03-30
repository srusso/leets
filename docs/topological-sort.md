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

1. Calculate in-degree for each node. In-degree of a node: how many nodes it depends on.
   - `in-degree = 0` means the node depends on nothing
2. Create a queue of all nodes which depend on nothing. Add to it all nodes with in-degree 0.
3. While the queue is not empty:
   - Dequeue a node, add it to the result.
   - For each neighbor of the node:
      - Reduce its in-degree by 1.
      - If in-degree becomes 0, enqueue it.
4. If the result contains all nodes, return the result. Otherwise, there is a cycle.
