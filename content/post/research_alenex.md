+++
banner = "research_alenex/banner.png"
categories = []
date = "2008-01-19"
description = ""
images = []
menu = ""
tags = ["Research", "Theory"]
title = "Online Learning and the Newsvendor Problem"
nodateline = false 
+++


Machine learning methods and inventory management were a focus in my Master's work. In the newsvendor problem, an amount of product to order must be decided upon periodically for reselling. Ordering too much results in losses due to overstock which must be discarded; too little results in losses due to lost sales. 

Traditional approaches experience a tradeoff in that some methods perform better in some situations, while our machine learning approach performs well consistently, particularly in situations where the demand suddenly increases or decreases. We were also able to prove lower bounds on the loss for our method:


{{< figure src="research_alenex/regret_bound.png" alt=" " width="50%" >}}

This was my very first research presentation, at ALENEX 2008. One of the highlights of the conference for me was seeing Donald Knuth give the keynote lecture!

[Paper](http://epubs.siam.org/doi/abs/10.1137/1.9781611972887.5).

We later published a more complete version of this research in the journal
Decision Sciences, where we more thoroughly explored the robustness of our machine-learning approach WMNS in volatile markets:

{{< figure src="research_alenex/decision_science.png" alt=" " width="100%" >}}

[Paper](https://onlinelibrary.wiley.com/doi/pdf/10.1111/deci.12187).
