+++
banner = "images/banner.png"
categories = []
date = "2009-12-17"
description = ""
images = []
menu = ""
tags = ["Programming", "Class"]
title = "CSE60416 System Interface Design, Microsoft Surface"
nodateline = true
+++

I did this project with Mike Olson, a Notre Dame graduate student. (He's narrating the video.)

We studied the proper design of user interfaces in this class. Although we mostly focused on graphical interfaces, we also learned how to interface with motion-based systems such as the Nintendo Wii remote and Balance Board.

For our final project, we wanted to design an interactive browser for the Microsoft Surface, a multitouch based coffeetable like device. Since we have one of these units sitting in the department office, we thought it would be nice if people visiting could sit down while waiting for appointments and quickly see the research currently being done in the department. To this end, visitors can simply touch a researcher's picture on the left, and a card detailing their research projects appears. The research project pages can include pictures as well as video. Cards are animated in and out in several interesting ways; each animation is also accompanied by a swooshing sound. These details make the browser actually quite fun to play with.

{{< youtube 0_soz_eSgcQ >}}

All the researcher data for this application isn't in fact stored on the Surface unit itself: a Drupal based web site serves as the backend datastore. This allows for easy administration and content management.
