Design
======

One `master` actor with a set of `workers` actors. Round-robin allocate tasks to workers.
Results returned to `master` to be aggregated.

Once all results are aggregated, the final is reported to an `accountant` and the actor system is stopped.

Messages
========
* Work - Let a worker know that there is work to be done
* Result - Let the master know that i've done my work
* CompleteResult - The final aggregated result including how long it took to compute

What to take home from this exercise
====================================
1. How to enable logging with `log4j.properties`
2. When designing the `messages` always define a trait that all messages extend
  - Thus we can avoid mix and match of Messages from other systems.
3. When a particular message has no parameters, use an `object` instead of a case class