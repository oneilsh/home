<html>
	<head>
		<title>
			Queens Two
		</title>
		<link type="text/css" rel="stylesheet" href="../../style1.css">
	</head>
	<body>
		<?php include("../../ProgramMenu.htm"); ?>
		<div class="title">
			<h1>
				Queens Two
			</h1>
		</div>
		<p class="applet">
			<applet code="PanelApplet.class" width=400 height=300></applet>
		</p>
		<div class="box">
			<p class="text">
				This program finds solutions to the queens problem as well as catorizes the results.
				Because many of the solutions are merely reflections or rotations of each other, it is possible to filter
				out similar results. Pressing the black <B>A</B> button will show all results. Pressing the green <B>U</B> button will
				show only unique results, with all reflections and rotations of those shown removed. Pressing the blue <B>S</B> button
				will show only unique symetric results, or results that have a 180 degree similarity with itself. Pressing the red <B>U</b>
				button will show only unique "Elegant" results, or results that have a 90 degree similarity with itself. These results
				are kept in four seperate linked list data structures all referring to the same data.
			</p>
		</div>
	</body>
</html>
