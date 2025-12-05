# Graphics-Rendering-Demo
A simple demonstration of the graphics rendering pipeline using Java and the Swing library.

When running the application, you may want to reposition the camera. The scene is composed of 4 objects:
* Chair 1
* Chair 2
* Triangle
* Camera

All four objects start at the origin, so the camera doesn't render anything interesting without any transformations.

Note that there is not a lot of error checking on inputs, so transforming with a singular matrix will cause problems! This is meant to be a demo program.

Do also note that although the camera defines a near and far plane, they are not used at the moment.
