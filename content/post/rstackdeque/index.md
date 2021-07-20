+++
banner = "images/quicksort_vis.png"
categories = []
date = "2015-07-12"
description = ""
images = []
menu = ""
tags = ["Theory", "Programming", "Research"]
title = "Rstackdeque"
nodateline = false 
+++

I'm (usually) a fan of the R programming language, though in a few ways it lacks features computer scientists expect. For example, R lacks many data structures provided by high-level languages, such as trees, queues, and stacks. This is somewhat complicated by R's functional nature--R programmers expect data structures to be "persistent," such that previous versions of the structure are available even after insertions or deletions. Fortunately, data structures in purely-functional languages (generally not R) is a topic of past and ongoing research, spurred initially by Chris Okasaki's excellent [Purely Functional Data Structures](https://www.amazon.com/Purely-Functional-Structures-Chris-Okasaki/dp/0521663504). THis implements purely functional stack and queue data structures as described by Okasaki and others, and gives examples of their use, in R. The package, `rstackdeque`, is available on CRAN, and described in the [R Journal](https://journal.r-project.org/archive/2015-1/oneil.pdf).


{{< figure src="images/lazy_queue.png" alt="O(1) purely functional queue via lazy evaluation." width="80%" >}}

Combined with `ggplot2`, it provides a nice way to visualize recursive processes.

{{< figure src="images/quicksort_vis.png" alt="Illustration of quicksort." width="100%" >}}

{{< vimeo 266983537 >}}

{{< vimeo 266984392 >}}

