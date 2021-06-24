+++
banner = "/learning_ai/banner.jpg"
categories = []
date = "2006-10-06"
description = ""
images = []
menu = ""
tags = ["Class", "Data", "Programming"]
title = "CCSE60171 Artificial Intelligence, Learning to Play Games"
nodateline = true
+++


I did this project with Allison Regier. It's not too difficult to write a program which uses an exhaustive search to play a game. What this requires, though, is some way to evaluate one board against another to tell which moves to make. Usually such a comparison function is hand written.

We asked whether it was possible to write a generic board evaluation function which would learn a preference over board states by playing many games, either against itself or a hand coded evaluation function. After a game is finished, if the learner lost, it works backwards through the board states visited reducing the preferences by decreasing amounts. Boards are recorded in terms of "features," so the learner is generalizable over different board games. As you can see from the presentation, we met with some success.



Files: [Proposal](/learning_ai/proposal.pdf) (pdf) [Presentation](/learning_ai/pres.pdf) (pdf).