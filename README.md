# SimThingy
SimThingy is a point-and-spring 2d physics modeler that could iterate over physical models to determine best range.  
So with a config file you can specify a 2d machine (pullies, struts, winches, platforms, etc) that undergoes forces like gravity and springs to produce motion and for my specific goals to find range of a thrown pumpkin.
It is one of the first programs I did so the Java is a little primitive but the math still holds up. 
The algorithm becomes slightly unstable when the time step to maximum force becomes too high, so a small time step is advised.  
I should have used more lagrangian mechanics for constrained dynamics, just to make it faster, but this way I get to find the trouble spots where too much force is applied.

I should probably redo this for javascript just because it is flashy and fun to play with in a graphical sense.
