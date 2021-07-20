+++
banner = "images/ColorDistance/banner.png"
categories = []
date = "2010-09-21"
description = ""
images = []
menu = ""
tags = ["Programming"]
title = "Misc. Small Java"
nodateline = true
+++

A few fun things I don't want to lose.

<!--more-->

### Color Mixing in L-norms

Ever see a Voronoi diagram by Manhattan Distance? 


 {{< fluid_imgs 
  "pure-u-1-2|images/ColorDistance/screenshot.png" 
>}}

By clicking inside the applet, you set the color of that point to be a random color. The color of all the other points are computed based on [inverse distance weighting](https://en.wikipedia.org/wiki/Inverse_distance_weighting). Note the top slider labeled "PVAL." As per the wiki, inverse distance weighting uses a P parameter to determine how much each value "mixes up." (My terminology, not theirs!) If we change this P value to be quite large, we see that the picture begins to resemble a [Voronoi diagram](https://en.wikipedia.org/wiki/Voronoi_diagram).

Of course, inverse distance weighting also uses a distance metric to determine the distance between two points. Usually, we use the "Euclidean distance" formula: ((x1-x2)^2 + (y1-y2)^2)^(1/2). Notice the constant 2 there: this is also known as the L2-Norm. There are other norms we can use to compute distance, using any value of l we like: ((x1-x2)^l + (y1-y2)^l)^(1/l). The L1-Norm, which is also called the Manhattan distance between two points, computes distance "around the block" from point to point. As we use larger values of l and approach the L-Infinity Norm, the distance between two points gets closer and closer to max( |x1-x2| , |y1-y2| ).

So, after turning the P value up to get a Voronoi diagram, play with the LNORM slider to see Voronoi diagrams according to that distance metric!

Other Controls: Pressing 'R' will cause the applet to render a high res version of the current scene. It takes a minute, so be patient. Pressing 'R' again returns to the live view. 'C' at any time clears the view of points. When in live view, pressing 'D' will toggle drifting mode, in which the points slowly bounce around the screen. Leaving P and L to the defaults of 4 and 2 respectively, using drift mode makes a nice lava lamp with 3 or 4 points.

Made with [processing.org](https://processing.org/).


Jar file: [ColorDistance.jar](images/ColorDistance/ColorDistance.jar)




### Mystic Spiral

I'm thinking of [changing the name](http://daria.wikia.com/wiki/Mystik_Spiral).

 {{< fluid_imgs 
  "pure-u-1-2|images/circle/screenshot.png" 
>}}


Made with [processing.org](https://processing.org/).


Jar file: [circle.jar](images/circle/circle.jar)


### Hungarian Algorithm

In Java. In case you need it (I did).

{{< expand "Hungarian.java" >}}
{{< highlight java >}}
{{< readfile file="post/miscjava/images/Hungarian.java" >}}
{{< /highlight >}}
{{< /expand >}}

