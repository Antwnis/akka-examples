Design
======

One `master` actor with a set of `workers` actors. Round-robin allocate tasks to workers.
Results returned to `master` to be aggregated.

Every worker receives a Double adds 10 and returns a message back.

Messages
========
* Work - Let a worker know that there is work to be done
* Result - Let the master know that i've done my work
* CompleteResult - The final aggregated result including how long it took to compute

What is interesting
===================
When defining messages for a particular Akka application - define a trait, that all messages extends.
Thus we can avoid mix and match of Messages from other systems.
