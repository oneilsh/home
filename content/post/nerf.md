+++
banner = "nerf/shotsDotplotBlaster.png"
categories = []
date = "2012-08-06"
description = ""
images = []
menu = ""
tags = ["Data", "Nerf"]
title = "A Statistical Analysis of Nerf Blasters and Darts"
nodateline = true
+++

By Shawn O'Neil and Katie Drueen.

<!--more-->

### Introduction

Nerf [blasters](http://www.hasbro.com/nerf/en_US/) have been a favorite toy since their introduction in the late 1980s. Once simple toys, these machines have evolved into manual as well as battery-operated, accessory-laden, belt and clip-fed [behemoths](http://nerf.wikia.com/wiki/N-Strike). Communities have grown around [painting](http://meandmunch.deviantart.com/gallery/) and/or modifying ("modding") these blasters to shoot further or more [accurately](http://modworks.blogspot.com/), and it is now possible to purchase aftermarket internal parts as [well](http://www.orangemodworks.com/).


{{< figure src="nerf/vulcan_alpha.png" alt="Figure 1: The Nerf Vulcan." width="50%" >}}

The Nerf blaster lineup provides a variety of firing mechanisms, each presenting different characteristics for rate of fire, accuracy, distance, and modding potential. For example, the older [Longshot](http://nerf.wikia.com/wiki/Longshot_CS-6) used a "direct" plunger system which provided greater ranges than the "reverse" plunger found on recent blasters. Battery-operated flywheel-based blasters such as the [Rayven](http://nerf.wikia.com/wiki/Rayven_CS-18) provide for high rates of fire, but may suffer in accuracy and distance (unless modified by increasing the voltage used).

Presumably in an effort to please the more serious Nerfing community, Nerf has introduced a line of blasters labeled the "N-Strike Elite" [series](https://nerf.hasbro.com/en-us/toys-games/nerf:elite), which returns to the direct-plunger system and includes a new dart type (colored blue, unlike previous orange and white darts), sporting an advertised range of 75 feet. While several have speculated that the new dart design contributes to the increased ranges and anecdotal evidence supports this, it is difficult to find a true difference between the new and old dart types. Further, while many reviews of nerf blasters and modifications test range and accuracy, these tests frequently lack scientific rigor.

This study attempts to address these shortfalls by comparing both dart accuracy and range across modified and unmodified blasters. While the new Elite blasters have not yet been released in stores accessible to the authors of this work, we have secured an accessory pack of Elite darts for comparison to older dart types (orange and white). Further, we compare accuracy and distance for four firing/blaster types in a 3x4 design.

### Experimental Design

In order to test both accuracy and distance for blaster/dart combinations in an efficient, controlled, and repeatable manner, we designed a range in which darts could be fired at a sliding glass door marked with a grid annotating the x and y-coordinates of each hit. Before each round of firing, dart tips were marked with liquid eyeliner which reliably marked their hit location on the door. To ensure repeatability and comparability of shots between blasters, a small level was attached to a wall aligned with the door approximately 19 feet away. The level was placed at a height of 64 inches. During firing, each blaster's top rail was held flush against both the wall and the level.

{{< fluid_imgs 
  "pure-u-1-2|/nerf/grid.jpg|Figure 2: Experimental setup." 
  "pure-u-1-2|/nerf/level.jpg|Kate is taking a break to watch some TV on the futon. Science!" 

>}}

Each of the four blasters was fired 50 times using each of the three dart types. Because the blasters were fired at level from a known height, we can compare firing distance as drop in inches from the 64 inch point on the grid. Comparing accuracy of each shot required some adjustment: because of differences between blasters, we are interested in the repeatability of shots rather than their absolute location. Thus, for each blaster/dart combination, we considered the median x and y-location of the grouping to the be central location of the group, and computed the Euclidean distance from this group center for each individual shot. Thus, each shot was scored with a drop (in inches) and accuracy (in inches), which were to be minimized.

We tested four blasters: the battery-operated flywheel-based Nerf Rayven* modified to accept either 4 AA batteries or a single 9v battery, and the reverse-plunger Nerf Recon* in both stock and modified forms (sans barrel attachments). The modifications on the Recon (aside from painting) included a basic air-restrictor removal and plunger-spring stretch*. It should be noted that all darts were in nearly new or gently used state: blue Elite and older-style orange darts were new from packaging (but cycled through each blaster a few times to wear them in) and white darts were similarly well-cared for (but not purchased for the purposes of this experiment).


{{< figure src="nerf/guns.jpg" alt="Figure 3: Blasters and dart types tested." width="50%" >}}

### Results: Overview

Figure 4 shows the shot groupings organized by dart and blaster type. Red points indicate group centers, and transparent red circles enclose the 50% most accurate shots for each grouping. Immediately we see some interesting patterns: for example, blue darts do indeed seem to be suffering from less drop, particularly for the Recon and modified Recon.


{{< figure src="nerf/shotsDotplot.png" alt="Figure 4: Shot placements. Red dots indicate group centers, 50% of shots occur within red circles." width="80%" >}}

Figure 5 shows these same shots grouped by blaster, with points colored by dart type. The modified Recon appears to shoot the furthest, and is overall quite accurate.

{{< figure src="nerf/shotsDotplotBlaster.png" alt="Figure 4: Shot placements. Red dots indicate group centers, 50% of shots occur within red circles." width="100%" >}}

Figures 6 and 7 show distributions of drop and accuracy for dart and blaster types, smoothed with kernel density estimates. Again we see interesting differences between different darts and blasters; the statistical analyses of the next section will bear these out.



{{< fluid_imgs 
  "pure-u-1-1|/nerf/distsByDart.png|Figure 6." 
  "pure-u-1-1|/nerf/distsByBlaster.png|Figure 7." 

>}}

### Results: Statistical Analysis

We analyzed both the drop and accuracy data using a 2x3 Analysis of Variance approach. For drop, we found significant (p < 0.05) main effects in both blaster and dart type, as well as in the interaction. Tukey's post-hoc test revealed that the modified Recon suffered less drop than all three other blasters, and that the modified Rayven shot higher than the unmodified versions of both the Rayven and Recon; we also found that Elite darts shot higher than both orange and white darts (p < 0.05) though we did not see a difference between orange and white darts at a 0.05 level, possibly due to sample size (p = 0.08). Figure 8 is a traditional line plot showing average drop for each dart and blaster combination.


{{< figure src="nerf/dropLines.png" alt="Figure 8: Average dart drop by dart type and blaster." width="100%" >}}

Figure 9 shows a transitively-reduced graph for which blaster and dart combinations suffered significantly less drop than others in the post-hoc test (an arrow or path from combination A to combination B signifies A dropped less than B, p < 0.05). As we discovered by main effect, blue Elite darts were predominantly better: in no case was a blaster better with orange or white darts than Elite darts. Both the Recon and Rayven showed significant improvements in distance with modification.

{{< figure src="nerf/dropSigs.png" alt="Figure 9: Post-hoc significance results for distance. An arrow or path from combination A to B indicates that combination A dropped less than combination B (p < 0.05)." width="70%" >}}

Figures 10 and 11 show average accuracies by blaster and dart and a similar comparison of statistically significant combination differences (p < 0.05). Here we see that the modified Recon was easily the most accurate blaster; when shooting Elite darts it bested all other combinations except for itself with white or orange darts (which themselves bested the modified Rayven shooting Elite darts, the least accurate combination). ANOVA for this test revealed significant effects in the main effect of blaster only: accuracy differences between darts were not found.


{{< figure src="nerf/accuracyLines.png" alt="Figure 10: Average accuracy by dart type and blaster." width="100%" >}}

<br />

{{< figure src="nerf/accuracySigs.png" alt="Figure 11: Post-hoc significance results for accuracy. An arrow or path from combination A to B indicates that combination A was more accurate than combination B (p < 0.05)." width="85%" >}}





### Discussion


Our experimental design appears to have been more than adequate in revealing differences between dart types, blasters, and the effects of blaster modification on drop and accuracy. Our results indicate that, overall, Elite darts do afford less drop over the distances tested, but not necessarily more accuracy. In fact, the modified Rayven with Elite darts proved to be the most inaccurate combination. On the other hand, the modified Recon (the most accurate blaster overall by far) improved in accuracy with white darts and suffered less drop with Elite darts. Although we do not have an Elite blaster to test with currently, this bodes well for accuracy with that line (which presumably would be most similar to the modified Recon we tested with).

We also saw the greatest distance improvement with the modified and unmodified Recons using Elite darts (8 inches less drop on average, over a level shooting distance of 19 feet), while the Rayven showed modest improvement (3.5 inches). Both blasters showed a significant improvement in distance with modification, and presumably higher voltage modifications would result in continually lower drops but result in perhaps worse accuracy as well.

This study suggests several possibilities for future research. Obviously, it would be very interesting to consider the new Elite blasters themselves, both in stock and modified form. For these longer-range blasters, it may be necessary to increase the distance to the measuring grid to help with discriminating between blasters with minimal drop. Anecdotally, we believe that accuracy issues for the modified Rayven could be alleviated with the use a barrel attachment while suffering minimal loss in distance.

One variable we did not consider in these tests were misfires: firings where the dart would spin out of control or the blaster would jam. We felt that we observed differential rates of misfires for darts and blasters (for example, it appears that the modified Recon is more prone to jamming with Elite darts, and the modified Rayven is more prone to misfires overall), however we did not record these data. Finally, although we constructed an accurate and repeatable measure of distance (as inches in drop on the measurement grid), we do not know how these drop amounts relate to distance-on-the-ground due to the often erratic nature of nerf dart flight. Constructing a predictive model mapping drop inches to distance in feet might prove useful.


### Conclusion

When I was a child, I once bought a Nerf blaster that promptly broke. My parents urged me to contact Hasbro and complain. I did so, and the company was nice enough to compensate me with a box containing a half dozen or more Nerf blasters.

Main conclusion: Nerf is Awesome.



### Retaliator Update

Some time after the previous tests were ran, we obtained a nerf retaliator, one of the new direct-plunger nerf blasters from the "Elite" line:


{{< figure src="nerf/retaliator.jpg" alt="Figure 12: Nerf Retaliator." width="50%" >}}


While the nerf retaliator is a more powerful blaster designed to work with the blue elite darts, it also features something new to the nerf line: a "rifled" barrel attachment:

{{< fluid_imgs 
  "pure-u-1-3|/nerf/web-barrel_comparison.jpg|Retaliator and older Recon barrel attachment side by side." 
  "pure-u-1-3|/nerf/web-retalaiator_barrel.jpg|Retaliator barrel internal." 
  "pure-u-1-3|/nerf/web-recon_barrel.jpg|Recon barrel internal." 

>}}


While the retaliator barrel is not "rifled" in the traditional sense (where the rifling is in contact with the projectile throughout its journey down the barrel, imparting an unavoidable spin on the projectile), it stands to reason that the engineers at Hasbro would not add such a non-visible feature without reason. Still, it has been debated whether rifling of any kind is beneficial for the flight of nerf darts (see for example [Rifling: Helpful, harmful, or ineffective?](http://btrettel.nerfers.com/archives/14)).

Fortunately, our experimental design can answer this question emprically. We tested a stock Retaliator blaster body with elite darts, using three different barrel configurations: 1) Using the "rifled" retaliator barrel attachment, 2) using the non-rifled Recon barrel attachment, and 3) using no barrel attachment. Here are the raw shot-placements:


{{< figure src="nerf/shotsDotplotBarrel.png" alt="Figure 14: Shot placements by barrel configuration for Retaliator blaster, using Elite darts. 50% of shots occur within red circles; red points indicate group median position." width="100%" >}}

hese results are clear visually: the "rifled" Retaliator barrel is more accurate than the unrifled Recon barrel, which is in turn more accurate than the absense of a barrel attachment. Further, both barrel attachments showed less drop than the lack of an attachment, which may be considered a surprising result. These results are also supported statistically: Tukey's post-hoc analysis supports the accuracy differences for all three barrel types as well as the distance differences at a p < 0.01 level:

      Fit: aov(formula = drop ~ blaster, data = barreldata)
                                                   p adj
      retaliator_barrel-retaliator_reconbarrel   0.997789
      retaliator_nobarrel-retaliator_reconbarrel 0.000000
      retaliator_nobarrel-retaliator_barrel      0.000000
				
      Fit: aov(formula = accuracy_median ~ blaster, data = barreldata)
                                                   p adj
      retaliator_reconbarrel-retaliator_barrel   0.0093151
      retaliator_nobarrel-retaliator_barrel      0.0000000
      retaliator_nobarrel-retaliator_reconbarrel 0.0005961
      
So, why might the Retaliator barrel be more accurate than the Recon barrel? Although the manufacture of these barrel attachments appears to be very similar, we cannot rule out factors such as the type of plastic used or manufacturing tolerances. Both barrel attachments have an identical inner diamater of ~0.675 inches, much larger than the Elite darts 0.5 inches outer diameter, and both are ~8.75 inches in length. Based on our tests, we suspect that while the "rifled" barrel does not impart a true spin on the darts (though we would need a slow-motion video experiment to confirm this), these darts do have a tendancy to "fishtail," and the Retaliator barrel attachment simply causes this fishtail to "spiral," imparting a reduction in accuracy variance that the Recon barrel attachment does not. Similarly, it would appear that without a barrel attachment the retaliator has a tendency to direct darts slightly downward (or, if you will, higher variance that is not centered on level), and both types of barrel attachment redirect these shots to a more-level flight path.

Finally, the unmodified Retaliator is a more-than-capable blaster: we found it to be statistically indistinguishable from the modified Recon tested above in both accuracy and drop when using the blue elite darts (data not shown).

