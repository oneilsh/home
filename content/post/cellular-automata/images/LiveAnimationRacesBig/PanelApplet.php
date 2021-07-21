<html>
	<head>
		<title>
			Live Animation Races Big
		</title>
		<link type="text/css" rel="stylesheet" href="../../style1.css">
	</head>
	<body>
		<?php include("../../ProgramMenu.htm"); ?>
		<div class="title">
			<h1>
				Live Animation Races Big
			</h1>
		</div>
		<p class="applet">
			<applet code="PanelApplet.class" width=400 height=400></applet>
		</p>
		<div class="box">
			<p class="text">
				This is yet another version of Life, an update on the previous one with two "races" of cells. This one
				implements a size adjustment, with numbered buttons. Neat things can be done here, at first it was a bug,
				but I decided to leave it and call it a feature. Go to size 3, then randomize. Then go to size 2, and clear,
				then size one and randomize. Then go back to size 3. This must all be done quickly though. Neat huh?<BR><BR>
			</p>
			<p class="text">
				In this version, in order for a cell to survive, it must have more
				neighbors of its own color, and the total number of neighbors must be less
				than crowd number of its color. Use the dialog boxes to change this. For example,
				putting a 5 in the red crowd dialog box means that a red cell will survive if it has less
				than five neighbors and it has more red neighbors than green.<BR><BR>
			</p>
			<p class="text">
				You can add either color of cell or remove a cell by clicking in the simulation area. The type added
				can be changed by clickin the <B>Add Color</B> button. Also note the probability field is the probability is that
				a cell will be changed to either a random color or a blank in the viewing window (even though this won't actually do much :-). <BR><BR>
			</p>
			<p class="text">
				In order for a cell to be created in a blank spot, it must have exactly 3 neighbors of its
				color, and it must have more of its own color neighbors than the opposite plus its racist modifyer.
				So if there is a 1 in the red race box, a red cell will only be created if it is surrounded by 3 red cells
				and one green cell near it. Not that entering in a 3 or higher in the race box will result in no new cells
				being added, as the number will never be low enough. Negatives work too.<BR><BR>
			</p>
			<p class="text">
				It's actually kind of difficult to explain, especially after all the fuddling I've done with it. Play with
				it, and have fun. Also, I plan to work on this project just a little bit more in the days to come. Adusting
				the numbers by only one or two will give noticable results, so dont go crazy. Try leaving the racist adjustment alone,
				and upping the crowdability, 6 is a good number here for both. Put one high and one low, then when one color is just about
				done, put that one better.
			</p>
		</div>
	</body>
</html>
