<html>
	<head>
		<title>
			Cube
		</title>
		<link type="text/css" rel="stylesheet" href="../../style1.css">
	</head>
	<body>
		<?php include("../../ProgramMenu.htm"); ?>
		<div class="title">
			<h1>
				Cube
			</h1>
		</div>
		<p class="applet">
			<applet code="PanelApplet.class" width=400 height=400></applet>
		</p>
		<div class="box">
			<p class="text">
				This is my 3d cube program. Instead of putting the cube up
				in three differernt ways, I opted for a user interactive setup.
				Pushing the <B>Clear</B> button will toggle whether all or only
				the front sides show. Pushing the <B>xup</B> or <B>xdown</B>
				buttons will either increse or decrease the amount of rotate
				along the x-axis, and similarly for the other buttons. Also,
				clicking and dragging on the image itself will cause the cube to
				rotate about the x and/or y axis' (axes? axis's? axises?). Note,
				the amount of flicker is due to the multiple thousands of individual
				lines being drawn at avery frame. I could have made it runnable and
				used an off screen image buffer to solve the problem, but I fear I
				don't have the time or patience (yet).
			</p>
		</div>
	</body>
</html>
