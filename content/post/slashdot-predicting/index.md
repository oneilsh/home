+++
banner = "images/fractile.png"
categories = []
date = "2008-04-05"
description = ""
images = []
menu = ""
tags = ["Class", "Data", "Research"]
title = "CSE60647 Data Mining, Predicting on Slashdot"
nodateline = true
+++


Much of the research I performed during my Master's was on the newsvendor problem, which deals with attempting to guess the demand for short-lived products so as to maximize profits. Unfortunately, data about newspaper sales is tough to come by. So, why not use an online equivalent? This project aims at predicting the number of comments a Slashdot story will generate given text which appears in the summary.

However, since we are interested in looking at the number of comments as a demand for a product to be sold, we don't stop there. Using cost-sensitive data mining approaches, we interpret probabilistic predictions as a sort of "customized distribution" and order the correct amount in terms of likely profit maximization.

{{< figure src="images/comments_dist.png" width="60%" >}}

As a bonus, be sure to check out the paper for some interesting statistics about Slashdot summaries from 2002 to 2007.



Files: [Proposal](images/proposal.pdf) (pdf), [Paper](images/final.pdf) (pdf), [Presentation](images/pres.pdf) (pdf).