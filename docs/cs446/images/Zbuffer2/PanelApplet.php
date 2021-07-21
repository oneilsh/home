<html>
	<head>
		<title>
			Zbuffer Demo
		</title>
		<link type="text/css" rel="stylesheet" href="../../style1.css">
	</head>
	<body>
		<?php include("../../ProgramMenu.htm"); ?>
		<div class="title">
			<h1>
				Zbuffer Demo
			</h1>
		</div>
		<p class="applet">
			<applet code="PanelApplet.class" width=400 height=400></applet>
		</p>
		<div class="box">
			<p class="text">
				That is beautiful isn't it? The zbuffer algorith places objects up, and changes an array that contains
				a bunch of "highest points" and then it goes to color a pixel when its adding an object, it only colors
				it if the pixel being added would be "higher" than whats already there. The fractal is my own work too,
				the math and algorithm are really quite easy, check out the source.
			</p>
			<p class="text">
				Also, according to my Prof. this is the algorithm used in videogames and such today, as it can look good
				with a lot of specialized tricks, and its much faster than raytracing.
			</p>
		</div>
	</body>
</html>
