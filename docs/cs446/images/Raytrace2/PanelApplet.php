<html>
	<head>
		<title>
			RayTracer Demo
		</title>
		<link type="text/css" rel="stylesheet" href="../../style1.css">
	</head>
	<body>
		<?php include("../../ProgramMenu.htm"); ?>
		<div class="title">
			<h1>
				RayTracer Demo
			</h1>
		</div>
		<p class="applet">
			<applet code="PanelApplet.class" width=400 height=400></applet>
		</p>
		<div class="box">
			<p class="text">
				The raytracer. It works by "sending out" rays from eye point, determining what it hit by searching
				through the list of objects, and then determining the color to put to the screen by checking to see
				what color the object is, if its shiny, if it should be in shadow, etc. Note that this algorithm must
				do a lot of checks for every object in the list for each pixel it prints, so it gets slow fast if there
				are a lot of objects on the screen.
			</p>
		</div>
	</body>
</html>
