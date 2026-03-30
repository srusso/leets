# What is it

Ordering of the vertices in a directed acyclic graph (DAG) such that for every directed edge `(u -> v)`,
vertex `u` comes before `v` in the ordering.

I.e. given graph `{(u -> v), (u -> t), (t -> k)}` then the sort could output `[u, v, t, k]`.

# When to Use

* Scheduling tasks with dependencies.
* Resolving symbol dependencies in linkers.
* Determining the order of compilation tasks.
* Detect if a DAG contains cycle. Basically, problems like [CourseSchedule](../src/main/java/net/sr89/CourseSchedule.java).