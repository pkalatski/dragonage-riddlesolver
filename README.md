dragonage-riddlesolver
======================

Riddle solver for DragonAge: Inquisition

# Requirements 
* SBT (http://www.scala-sbt.org/). 
* Java 8.

# Instructions

* Check out the project using git.
* Draw the required riddle image on a paper or print out its screenshot (better to invert colors).
* Mark all points with unique numbers, order doesn't matter.
* Edit riddle.txt file, add all lines between points there. Every row in that file should have exactly one line separated by dash. Order does not matter. Example: 
<pre>
<code>
1-2
2-3
3-1
</code>
</pre> 
* Make sure that all lines from drawing are specified.
* Open the console, navigate to the project root directory (which has riddle.txt file) and execute <code>sbt run</code>.
* Draw lines in game according to instructions.
