#!/usr/bin/env python

## TimelapseThing version 1.21

#Copyright (c) 2013, Shawn T. O'Neil
#All rights reserved.

#Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

#1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
#2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

#THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 


from Tkinter import *
import tkFileDialog
import tkMessageBox
import os;
import datetime;
import shutil;
import subprocess;
import re;
import sys;
import math;
import io;

## for py2app to find the included convert, ffmpeg - didn't work :(
#import Foundation;



## This script is monolithic and poorly documented.
## Monolithic for (relative) ease of install and use by non-computer people (esp on OSX)
## Poorly documented because it's a weekend project, and ain't nobody got time for that ;)

class Utility:
	def __init__(self):
		self.applocation = os.path.dirname(sys.argv[0]);
		#os.environ["MAGICK_HOME"] = self.applocation + "/ImageMagick-6.8.5";
		#os.environ["DYLD_LIBRARY_PATH"] = self.applocation + "/ImageMagick-6.8.5/lib/";
		self.log_handle = open("timelapsething_log.txt", "w");
		#self.log_message("MAGICK_HOME: " + os.environ["MAGICK_HOME"]);
		#self.log_message("DYLD_LIBRARY_PATH" + os.environ["DYLD_LIBRARY_PATH"]);
		#self.log_message("Is this thing on??");
		self.log_message(subprocess.check_output("convert -list configure", shell = True));
		self.last_dir_accessed = os.path.dirname(sys.argv[0]);

	def log_message(self, message):
		self.log_handle.write("#########\n");
		self.log_handle.write(message);
		self.log_handle.write("\n\n");
		sys.stdout.write("#########\n");
		sys.stdout.write(message);
		sys.stdout.write("\n\n");
		self.log_handle.flush();
		os.fsync(self.log_handle.fileno());

	def get_file_to_save(self, defaultextn):
		file_to_save = tkFileDialog.asksaveasfilename(defaultextension = defaultextn, initialdir = self.last_dir_accessed, title = "File to write results to.");
		self.last_dir_accessed = os.path.dirname(file_to_save);
		return(file_to_save);


	def select_input_dir_with_numnames(self, label_to_update):
		toRet = "";
		chosendir = tkFileDialog.askdirectory(initialdir = self.last_dir_accessed, title="Select folder containing images.", mustexist = True);
		if(chosendir != ""):
			fileslist = os.listdir(chosendir);
			ok = True;
			
			names_ints = list();
			match_names = list();
			for filename in fileslist:
				if(re.match(r"\d{6,6}\.jpg", filename)):
					match_names.append(filename);
					theint = int(filename.split(".")[0]);
					names_ints.append(theint);
			if(len(match_names) < 1):
				ok = False;
			names_ints.sort();
			for i in range(0,len(names_ints)):
				print("is is: " + str(i+1) + " name is: " + str(names_ints[i]));
				if(i+1 != names_ints[i]):
					ok = False;
					
			if(ok):
				match_names.sort();
				firstname = match_names[0];
				lastname = match_names[len(match_names)-1];
				label_to_update["text"] = firstname + " ... " + lastname;
				toRet = chosendir;
				self.last_dir_accessed = chosendir;
			else:
				tkMessageBox.showerror("Oops.", "The directory you select must contain at sequence of files starting at 000001.jpg.");
		toRet = chosendir;
		return(toRet);

	def select_output_dir_for_names(self,names_dict):
		dir_to_save = tkFileDialog.askdirectory(initialdir = self.last_dir_accessed, title = "Select output directory.");
		
		existing_files = list();
		for name in names_dict.keys():
			fullpath = os.path.join(dir_to_save, name);
			if(os.path.isfile(fullpath)):
				existing_files.append(name);
				
		for name in names_dict.values():
			fullpath = os.path.join(dir_to_save, name);
			if(os.path.isfile(fullpath)):
				existing_files.append(name);
				
		existing_files.sort();
		if(len(existing_files) > 0):
			firstname = os.path.basename(existing_files[0]);
			lastname = os.path.basename(existing_files[len(existing_files)-1]);
			result = tkMessageBox.askquestion("Overwrite?","Warning: AT LEAST one of the output files to be created, " + firstname + " ... " + lastname + ",\nalready exists and will be overwritten if you continue and execute. Continue?", icon = "warning");
			if(result == "yes"):
				self.last_dir_accessed = dir_to_save;
			else:
				dir_to_save = "";
		else:
			self.last_dir_accessed = dir_to_save;
		return(dir_to_save);

	def move_files(self, from_to_dict, base_path):
		for from_file in from_to_dict:
			if(os.path.isfile(os.path.join(base_path,from_file))):
				shutil.move(os.path.join(base_path,from_file), os.path.join(base_path,from_to_dict[from_file]));

	def get_file_names_list(self, start, count):
		ret_dict = dict();
		for i in range(start,start+count):
			base = ("{0:06d}").format(i); 
			ret_dict["tmp" + str(base) + ".jpg"] = str(base) + ".jpg"; 
		return(ret_dict);


	def select_input_files(self, min_number, label_to_update):
		chosenfiles = tkFileDialog.askopenfilename(initialdir = self.last_dir_accessed, multiple = True, title="Select input images/videos.");
		toRet = list();
		if(len(chosenfiles) > min_number):
			firstname = os.path.basename(chosenfiles[0]);
			lastname = os.path.basename(chosenfiles[len(chosenfiles)-1]);
			label_to_update["text"] = firstname + " ... " + lastname
			self.last_dir_accessed = os.path.dirname(chosenfiles[0]);
			toRet = chosenfiles;
		else:
			tkMessageBox.showerror("Oops.","Please select at least " + str(min_number) + " jpg files (or videos).");
		return(toRet);

	def select_input_file(self, label_to_update):
		chosenfile = tkFileDialog.askopenfilename(initialdir = self.last_dir_accessed, multiple = False, title="Select input image/video.");
		toRet = "";
		if(chosenfile != ""):
			firstname = os.path.basename(chosenfile);
			label_to_update["text"] = firstname
			self.last_dir_accessed = os.path.dirname(chosenfile);
			toRet = chosenfile;
		else:
			tkMessageBox.showerror("Oops.","Please select one jpg file (or video).");
		return(toRet);


	def execute_command_series(self, command_list, label_to_update, window_to_update):
		count = 1;
		totalCount = len(command_list);
		lasttime = datetime.datetime.now();
		etastr = "?";
		running_mean_secs = 1;

		result_list = list();		

		for command in command_list:
			label_to_update["text"] = "Running; step " + str(count) + "/" + str(totalCount) + "    Eta: " + str(etastr);
			currenttime = datetime.datetime.now();				
			window_to_update.update();
			
			
			try:
				#command2 = self.convert_command_for_bundle(command) + "; exit 0";
				#self.log_message("Executing: " + command2);
				#result = subprocess.check_output(command2, stderr=subprocess.STDOUT, shell = True);
				self.log_message("Executing: " + command);
				result = subprocess.check_output(command, stderr=subprocess.STDOUT, shell = True).strip();
				result_list.append(result);
				self.log_message("Result: " + result);
			except Exception as e:
				self.log_message(str(e));
				tkMessageBox.showerror("Oops.", "Guru meditation error: " + str(e));
				quit();

			secs_delta = (currenttime-lasttime).total_seconds();	
			running_mean_secs = (running_mean_secs*count + secs_delta)/float(count + 1);
			eta = running_mean_secs*(totalCount - count);
			etastr = str(datetime.timedelta(seconds=int(eta)));
			lasttime = currenttime;

			count = count + 1;
		
		#self.log_handle.close();
		label_to_update["text"] = "Done!";
		window_to_update.update();
		return(result_list);
	## For OSX - this was a fail because I couldn't get convert to package into a nice binary
	#def convert_command_for_bundle(self, command):
	#	return(self.applocation + "/ImageMagick-6.8.5/bin/" + command);
	#	newCommand = command;
	#	try:
	#		bundle = Foundation.NSBundle.mainBundle();
	#		command_list = re.split(r"\s+",command);
	#		bundle_command_path = bundle.pathForResource_ofType_(command_list[0], None);
	#		if(bundle_command_path != None):
	#			command_list[0] = bundle_command_path;
	#			newCommand = " ".join(command_list);
	#	except Exception as e:
	#		self.log_message(e.message);
	#		tkMessageBox.showerror("Oops.", "Guru meditation error: " + e.message);
	#		
	#	return(newCommand);
		





class MainRoot:
	def __init__(self):
		self.rootWindow = Tk();
		self.rootWindow.wm_title("TimelapseThing");
		self.setup_gui();
		self.utility = Utility();				
		self.rootWindow.mainloop();

	def setup_gui(self):
		self.add_row("Crop/Scale", "Simple crop and scale images.", self.crop_scale, 1);
		self.add_row("Deflicker", "Remove small variances in exposure for a series of images.", self.deflicker, 2);
		self.add_row("Concatenate Videos", "Concatenate multiple videos (as output from a GoPro, for example). [beta]", self.concat_videos, 3);
		self.add_row("Video to Timelapse", "Turn a video into a series of images (so you can remerge them into a video! :-P) [beta]", self.video_to_images, 4);
		self.add_row("Simulate Shutter Angle", "Shutter angle too narrow? Shoot more frequently, and use this to average X frames, drop Y frames, average X, drop Y... [beta]", self.shutter_merge, 5);
		self.add_row("Window Merge", "Uses a sliding window, computing median (artifact removal?), lighten, darken, or mean over each window.", self.window_merge, 6);
		self.add_row("Expand", "Use a sliding gaussian interpolation to expand a set of images into a larger (smaller) set of images.", self.expand, 7);
		self.add_row("Create Video", "Create a video from individual frames named 000001.jpg, 000002.jpg, etc. (Requires ffmpeg)", self.create_video, 8);
		self.add_row("Lighten/Darken Merge", "Merge a series of JPGs into a single JPG based on layer lightening, darkening, etc.", self.lighten_darken_merge, 9);
		self.add_row("Rename Files", "Copy/Rename files like IMG2301.JPG IMG2302.JPG etc. to 000001.jpg 000002.jpg etc.", self.rename, 10);
		
		
	def add_row(self, button_name, label_text, func_to_call, rownum):
		
		frame1 = Frame(self.rootWindow);
		#frame1.grid(row=0, column = 0);
		fr1_button = Button(self.rootWindow, text = button_name, command = func_to_call);
		fr1_button.grid(row = rownum, column = 0, sticky = E+W);
		fr1_label = Label(self.rootWindow, text = label_text);
		fr1_label.grid(row = rownum, column = 1, sticky = W);

	def concat_videos(self):
		subwindow = ConcatVideos(self.utility);

	def video_to_images(self):
		subwindow = VideoToImages(self.utility);

	def lighten_darken_merge(self):
		subwindow = LightenDarken(self.utility);
		
	def shutter_merge(self):
		subwindow = ShutterMerge(self.utility);

	def rename(self):
		subwindow = Rename(self.utility);

	def create_video(self):
		subwindow = CreateVideo(self.utility);

	def window_merge(self):
		subwindow = WindowMerge(self.utility);

	def expand(self):
		subwindow = Expand(self.utility);

	def deflicker(self):
		subwindow = Deflicker(self.utility);

	def crop_scale(self):
		subwindow = CropScale(self.utility);


class CropScale:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Crop/Scale");
		self.width = StringVar(self.rootWindow);
		self.width.set("1280");
		self.height = StringVar(self.rootWindow);
		self.height.set("720");
		self.gravity = StringVar(self.rootWindow);
		self.gravity.set("Center");
		
		self.inputfiles = list();		
		self.dir_to_save = "";
		self.setup_gui();
		self.rootWindow.mainloop();
		
	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, columnspan = 2, sticky = E+W);
		
		files_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		files_selected_label.grid(row = 1, column = 0, sticky = W);
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 1, column = 1, columnspan = 3, sticky = W);
		
		width_label = Label(self.rootWindow, text = "Width: ");
		width_label.grid(row = 2, column = 0, sticky = W);
		width = Entry(self.rootWindow, textvariable = self.width);
		width.grid(row = 2, column = 1, sticky=W);

		height_label = Label(self.rootWindow, text = "Height: ");
		height_label.grid(row = 3, column = 0, sticky = W);
		height = Entry(self.rootWindow, textvariable = self.height);
		height.grid(row = 3, column = 1, sticky=W);

		options_label = Label(self.rootWindow, text = "Gravity: ");
		options_label.grid(row = 4, column = 0, sticky = W);
		options_dropdown = OptionMenu(self.rootWindow, self.gravity, "Center", "West", "East", "North", "South");
		options_dropdown.grid(row = 4, column = 1, sticky = E+W);

		go_button = Button(self.rootWindow, text = "Select Output Dir & Execute", command = self.execute);
		go_button.grid(row=5, column = 0, columnspan = 2, sticky = E+W);

		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 6, column = 0, sticky = W);
		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 6, column = 1, columnspan = 3, sticky = W);



	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(1,self.files_selected);
		
	def execute(self):
		self.status["text"] = "Waiting";
		input_count = len(self.inputfiles);
		width = int(self.width.get());
		height = int(self.height.get());
		gravity = self.gravity.get();

		new_names = self.utility.get_file_names_list(1,input_count);
		self.dir_to_save = self.utility.select_output_dir_for_names(new_names);
		if(self.dir_to_save != "" and len(self.inputfiles) > 0):
			commands_list = list();
			temp_names = new_names.keys();
			temp_names.sort();
			
			for i in range(0,input_count):
				cmd = "convert " + self.inputfiles[i] + " -scale " + "'" + str(width) + "x" + str(height) + "^' -gravity " + gravity + " -extent " + str(width) + "x" + str(height) + " " + os.path.join(self.dir_to_save,temp_names[i]); 
				commands_list.append(cmd);
			
			self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
			self.utility.move_files(new_names, self.dir_to_save);
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output directory selected.");
			

class Deflicker:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Deflicker");
		self.window_width = StringVar(self.rootWindow);
		self.window_width.set("10");
		self.ev_adjust_start = StringVar(self.rootWindow);
		self.ev_adjust_start.set("+0.0");
		self.ev_adjust_end = StringVar(self.rootWindow);
		self.ev_adjust_end.set("+0.0");
		self.interpolation_method = StringVar(self.rootWindow);
		self.interpolation_method.set("Original");
		self.exposure_method = StringVar(self.rootWindow);
		self.exposure_method.set("Mean Pixel");
		self.inputfiles = list();		
		self.dir_to_save = "";
		self.setup_gui();
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, columnspan = 3, sticky = E+W);
		
		files_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		files_selected_label.grid(row = 1, column = 0, sticky = W);
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 1, column = 1, columnspan = 2, sticky = W);

		exposure_method_label = Label(self.rootWindow, text = "Exposure Test: ");
		exposure_method_label.grid(row = 2, column = 0, sticky = W);
		exposure_dropdown = OptionMenu(self.rootWindow, self.exposure_method, "Mean Pixel", "Median Pixel (slow)");
		exposure_dropdown.grid(row = 2, column = 1, columnspan = 2, sticky = E+W);

		interpolation_method_label = Label(self.rootWindow, text = "Exposure Trend: ");
		interpolation_method_label.grid(row = 3, column = 0, sticky = W);
		interpolation_dropdown = OptionMenu(self.rootWindow, self.interpolation_method, "Original", "Linear Fit", "Flat", "None");
		interpolation_dropdown.grid(row = 3, column = 1, columnspan = 2, sticky = E+W);

		window_width_label = Label(self.rootWindow, text = "Smooth Width: ");
		window_width_label.grid(row = 4, column = 0, sticky = W);
		window_width = Entry(self.rootWindow, textvariable = self.window_width);
		window_width.grid(row = 4, column = 1, columnspan = 2, sticky=W);

		ev_adjust_label = Label(self.rootWindow, text = "EV adjust (start, end): ");
		ev_adjust_label.grid(row = 5, column = 0, sticky = W);
		ev_adjust_start = Entry(self.rootWindow, textvariable = self.ev_adjust_start);
		ev_adjust_start.grid(row = 5, column = 1, sticky=W);
		ev_adjust_end = Entry(self.rootWindow, textvariable = self.ev_adjust_end);
		ev_adjust_end.grid(row = 5, column = 2, sticky=W);
		

		go_button = Button(self.rootWindow, text = "Select Output File & Execute", command = self.execute);
		go_button.grid(row=6, column = 0, columnspan = 3, sticky = E+W);
				
		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 7, column = 0, sticky = W);		
		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 7, column = 1, columnspan = 2, sticky = W);

	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(int(self.window_width.get()),self.files_selected);

	## given a list of histogram strings from convert, 
	## get the median number listed from column 2
	## after splitting on commas
	def extract_medians(self, thelist):
		toRet = list();
		for histoString in thelist:
			histo_nums = list();
			histo_lines = histoString.split("\n");
			for histo_line in histo_lines:
				line_list = histo_line.split(",");
				histo_nums.append(int(line_list[1]));
			toRet.append(self.compute_median(histo_nums));
		return(toRet);

	def compute_median(self, thelist):
		thelist.sort();
		return(thelist[int(len(thelist)*0.5)]);

	def execute(self):
		self.status["text"] = "Waiting";
		input_count = len(self.inputfiles);
		width = int(self.window_width.get());
		ev_start = float(self.ev_adjust_start.get());
		ev_end = float(self.ev_adjust_end.get());
		interpolation_method = re.split(r"\s+", self.interpolation_method.get())[0];
		exposure_method = re.split(r"\s+", self.exposure_method.get())[0];

		new_names = self.utility.get_file_names_list(1,input_count);
		self.dir_to_save = self.utility.select_output_dir_for_names(new_names);
		if(self.dir_to_save != "" and len(self.inputfiles) > 0):
			commands_list = list();
			for file in self.inputfiles:
				cmd = "";
				if(exposure_method == "Median"):
					cmd = "convert " + file + " -type Grayscale -scale 500x500 -format %c histogram:info:"
				else:
					cmd = "convert " + file + ' -format "%[mean]" info:';
				commands_list.append(cmd);

			exposures = self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
			if(exposure_method == "Median"):
				exposures = self.extract_medians(exposures);
			
			## get the exposures as relative EV values from the first N
			adjusted = self.exposure_adjusts(exposures, width);
			
			## fix the exposures; the deflickering part
			relexposures = list();
			if(interpolation_method == "Original"):
				means = adjusted;
				for i in range(0,6): # smooth it a few times
					means = self.mean_window(means, width);
				relexposures = self.subtract_vals(adjusted,means);
			elif(interpolation_method == "Linear"):
				relexposures = self.subtract_vals(adjusted,self.linear_fit(adjusted));
			elif(interpolation_method == "Flat"):
				relexposures = adjusted;
			else: # handles "none" case
				for i in range(0,len(adjusted)):
					relexposures.append(0.0);
			
			## adjust EVs based on ev start and ev end
			ev_adjustments = self.linear_trend(len(adjusted), ev_start, ev_end);
			for i in range(0,len(adjusted)):
				relexposures[i] = relexposures[i] + ev_adjustments[i];
				
			#f_handle = open(os.path.join(self.dir_to_save,"exposures.txt"), "w");
			#for i in range(0,len(exposures)):
			#	f_handle.write(str(exposures[i]) + "\t" + str(adjusted[i]) + "\t" + str(relexposures[i]) + "\n");
			#f_handle.close();

			temp_names = new_names.keys();
			temp_names.sort();
			commands_list = list();
			for i in range(0,input_count):
				old_name = self.inputfiles[i];
				new_name = temp_names[i];
				ratio = 2**relexposures[i];
				# simple multiply is probably most accurate; but could blow highlights
				cmd = "convert " + old_name + " -evaluate multiply " + str(ratio) + " " + os.path.join(self.dir_to_save,temp_names[i]);
				# gamma adjustment is much stronger in a low-end, so dark pictures are opposite flickered :(
				#cmd = "convert " + old_name + " -gamma " + str(ratio) + " " + os.path.join(self.dir_to_save,new_name);
				#cmd = "convert " + old_name + " -sigmoidal-contrast 4," + str(50.0*(1.0/ratio**2)) + "% " +  os.path.join(self.dir_to_save, new_name);
				commands_list.append(cmd);

			result = self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
			self.utility.move_files(new_names, self.dir_to_save);


	# given a list of "exposures" (mean image intensities)
	# returns them as exposures relative to the first N
	def exposure_adjusts(self,thelist, window_size):
		window = thelist[0:window_size];
		first = self.get_mean(window);
		toRet = list();
		for el in thelist:
			elflt = float(el);
			toRet.append(-1.0*math.log(elflt/first)/math.log(2.0));
		return(toRet);
			
	## given a cound and two numbers, returns a list of the same length
	## with a simple trend between start and end of that count
	## count MUST be greater than 0, else crash
	def linear_trend(self, count, start, end):
		delta = (float(end) - float(start))/float(count-1);
		toRet = list();
		for i in range(0,count):
			toRet.append(i*delta+start);
		return(toRet);
		
	## given two equal sized vectors, returns lista-listb	
	def subtract_vals(self,lista, listb):
		toRet = list();
		for i in range(0,len(lista)):
			toRet.append(float(lista[i])-float(listb[i]));
		
		return(toRet);
	
	## given a list of numbers (possibly as strings), compute a linear fit
	def linear_fit(self, thelist):
		meanx = 0.0;
		meany = 0.0;
		for y in thelist:
			meany = meany + float(y);
		meany = meany/float(len(thelist));
		for x in range(0,len(thelist)):
			meanx = meanx + x;
		meanx = meanx/float(len(thelist));
	
		numerator = 0.0;
		for x in range(0,len(thelist)):
			y = float(thelist[x]);
			numerator = numerator + (x - meanx)*(y - meany);
		
		denominator = 0.0;
		for x in range(0,len(thelist)):
			denominator = denominator + (x-meanx)**2;
		
		beta = numerator/denominator;
		alpha = meany - beta*meanx;
		
		toRet = list();
		for x in range(0,len(thelist)):
			y = alpha + beta*x;
			toRet.append(y);
		return(toRet);		
				
	## given a list of numbers (possibly as strings), compute a sliding window mean
	def mean_window(self, thelist, window_size):
		means = list();
		midwindow = int(window_size/2.0);
		
		firstwindow = thelist[0:window_size];
		for index in range(0,midwindow+1):
			mean = self.get_mean(firstwindow);
			means.append(mean);
		
		for windowstart in range(1,len(thelist)-window_size):
			window = thelist[windowstart:windowstart+window_size];
			mean = self.get_mean(window);
			means.append(mean);
			
		lastwindow = thelist[len(thelist)-window_size+midwindow:len(thelist)];
		for index in range(len(thelist)-window_size+midwindow,len(thelist)):
			mean = self.get_mean(lastwindow);
			means.append(mean);
			
		return(means);
		
	def get_mean(self, thelist):
		sum = 0.0;
		for el in thelist:
			sum = sum + float(el);
		return(sum/float(len(thelist)));


class VideoToImages:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Lighten/Darken Merge");
		self.merge_type = StringVar(self.rootWindow);
		self.merge_type.set("Mean");		

		self.seconds_on = StringVar(self.rootWindow);
		self.seconds_on.set("10");
		self.seconds_off = StringVar(self.rootWindow);
		self.seconds_off.set("10");

		self.inputfile = "None";		
		self.dir_to_save = "";
		self.setup_gui();
		self.merge_type_dict = dict();
		self.merge_type_dict["Median"] = "Median";
		self.merge_type_dict["Mean"] = "Mean";
		self.merge_type_dict["Lighten"] = "Max";
		self.merge_type_dict["Darken"] = "Min";
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Video", command = self.select_file);
		select_button.grid(row=0, column = 0, columnspan = 2, sticky = E+W);
		
		options_dropdown_label = Label(self.rootWindow, text = "Merge Type: ");
		options_dropdown_label.grid(row = 1, column = 0, sticky = W);
		options_dropdown = OptionMenu(self.rootWindow, self.merge_type, "Lighten", "Darken", "Mean", "Median (slow)");
		options_dropdown.grid(row = 1, column = 1, sticky = E+W);
		
		seconds_on_label = Label(self.rootWindow, text = "Seconds Open Shutter: ");
		seconds_on_label.grid(row = 2, column = 0, sticky = W);
		seconds_on = Entry(self.rootWindow, textvariable = self.seconds_on);
		seconds_on.grid(row = 2, column = 1, sticky=W);

		seconds_off_label = Label(self.rootWindow, text = "Seconds Closed Shutter: ");
		seconds_off_label.grid(row = 3, column = 0, sticky = W);
		seconds_off = Entry(self.rootWindow, textvariable = self.seconds_off);
		seconds_off.grid(row = 3, column = 1, sticky=W);

		files_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		files_selected_label.grid(row = 4, column = 0, sticky = W);		
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 4, column = 1, sticky = W);

		go_button = Button(self.rootWindow, text = "Select Output Dir & Execute", command = self.execute);
		go_button.grid(row=5, column = 0, columnspan = 2, sticky = E+W);

		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 6, column = 0, sticky = W);
		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 6, column = 1, sticky = W);

	def select_file(self):
		self.status["text"] = "Waiting";
		self.inputfile = self.utility.select_input_file(self.files_selected);
		
	def execute(self):
		self.status["text"] = "Waiting";
		seconds_on = float(self.seconds_on.get());
		seconds_off = float(self.seconds_off.get());
		merge_type = re.split(r"\s+", self.merge_type.get())[0];
		merge_type = self.merge_type_dict[merge_type];
		duration = 0.0;
	
		if(self.inputfile != ""):
			cmd = "ffprobe -i " + self.inputfile + " -show_format -v quiet"
			duration_out = subprocess.check_output(cmd, shell = True);
			duration_list = duration_out.split("\n");
			for tag in duration_list:
				if(re.match("duration=", tag)):
					dur_tag_list = tag.split("=")
					duration = float(dur_tag_list[1])
			
			output_count = int((duration - seconds_on)/(seconds_on + seconds_off)) + 1;
			
			new_names = self.utility.get_file_names_list(1,output_count);
			self.dir_to_save = self.utility.select_output_dir_for_names(new_names);
			temp_names = new_names.keys();
			temp_names.sort();
			
			if(self.dir_to_save != ""):
				commands_list = list();
				
				counter = 0;
				for temp_name in temp_names:
					startpos = counter*(seconds_on + seconds_off);
					# ffmpeg -ss 00:10:00 -i GP020462.MP4 -t 00:00:05 -sameq -f image2pipe -vcodec ppm - | convert - -evaluate-sequence mean test.jpg
					cmd = "ffmpeg -ss " + str(startpos) + " -i " + self.inputfile + " -t " + str(seconds_on) + " -qscale 0 -f image2pipe -vcodec ppm - | convert - -evaluate-sequence " + merge_type + " " + os.path.join(self.dir_to_save,temp_name);
					commands_list.append(cmd);					
					
					counter = counter + 1;
			
				self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
				self.utility.move_files(new_names, self.dir_to_save);


class WindowMerge:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Lighten/Darken Merge");
		self.merge_type = StringVar(self.rootWindow);
		self.merge_type.set("Lighten");		
		self.window_width = StringVar(self.rootWindow);
		self.window_width.set("10");
		self.inputfiles = list();		
		self.dir_to_save = "";
		self.setup_gui();
		self.merge_type_dict = dict();
		self.merge_type_dict["Median"] = "Median";
		self.merge_type_dict["Mean"] = "Mean";
		self.merge_type_dict["Lighten"] = "Max";
		self.merge_type_dict["Darken"] = "Min";
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, columnspan = 2, sticky = E+W);
		
		options_dropdown_label = Label(self.rootWindow, text = "Merge Type: ");
		options_dropdown_label.grid(row = 1, column = 0, sticky = W);
		options_dropdown = OptionMenu(self.rootWindow, self.merge_type, "Lighten", "Darken", "Mean", "Median (slow)");
		options_dropdown.grid(row = 1, column = 1, sticky = E+W);
		
		window_width_label = Label(self.rootWindow, text = "Window Width: ");
		window_width_label.grid(row = 2, column = 0, sticky = W);
		window_width = Entry(self.rootWindow, textvariable = self.window_width);
		window_width.grid(row = 2, column = 1, sticky=W);

		files_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		files_selected_label.grid(row = 3, column = 0, sticky = W);		
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 3, column = 1, sticky = W);

		go_button = Button(self.rootWindow, text = "Select Output Dir & Execute", command = self.execute);
		go_button.grid(row=4, column = 0, columnspan = 2, sticky = E+W);

		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 5, column = 0, sticky = W);
		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 5, column = 1, sticky = W);
				

	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(int(self.window_width.get()),self.files_selected);
		
	
	def execute(self):
		self.status["text"] = "Waiting";
		input_count = len(self.inputfiles);
		width = int(self.window_width.get());
		output_count = input_count - width + 1;
		merge_type = re.split(r"\s+", self.merge_type.get())[0];
		merge_type = self.merge_type_dict[merge_type];
		
		new_names = self.utility.get_file_names_list(1,output_count);
		self.dir_to_save = self.utility.select_output_dir_for_names(new_names);
		if(self.dir_to_save != "" and len(self.inputfiles) > 0):
			commands_list = list();
			temp_names = new_names.keys();
			temp_names.sort();
			for index in range(0,output_count):
				files = list();
				for file_index in range(index,index+width):
					files.append(self.inputfiles[file_index]);
				
				magic_cmd_list = ["convert "];
				for file in files:
					magic_cmd_list.append(file + " ");
				magic_cmd_list.append(" -evaluate-sequence " + merge_type);
				magic_cmd_list.append(" " + os.path.join(self.dir_to_save,temp_names[index]));
				
				
				magic_cmd = "".join(magic_cmd_list);
				commands_list.append(magic_cmd);
			
			self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
			self.utility.move_files(new_names, self.dir_to_save);
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output directory selected.");



class ShutterMerge:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Shutter Merge");
		self.frames_on = StringVar(self.rootWindow);
		self.frames_on.set("3");
		self.frames_off = StringVar(self.rootWindow);
		self.frames_off.set("3");
		self.motion_threshold = StringVar(self.rootWindow);
		self.motion_threshold.set("10");
		self.motion_buffer_smooth = StringVar(self.rootWindow);
		self.motion_buffer_smooth.set("15");
		self.blur_amount = StringVar(self.rootWindow);
		self.blur_amount.set("15");
		self.blur_angle = StringVar(self.rootWindow);
		self.blur_angle.set("0");
				
		self.inputfiles = list();
		self.dir_to_save = "";

		self.setup_gui();
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, sticky = E+W);
		go_button = Button(self.rootWindow, text = "Select Output Directory & Execute", command = self.execute);
		go_button.grid(row=0, column = 1, sticky = E+W);
		
		frames_on_label = Label(self.rootWindow, text = "Frames On: ");
		frames_on_label.grid(row = 1, column = 0, sticky = W);
		frames_on = Entry(self.rootWindow, textvariable = self.frames_on);
		frames_on.grid(row = 1, column = 1, sticky=W);

		frames_off_label = Label(self.rootWindow, text = "Frames Off: ");
		frames_off_label.grid(row = 2, column = 0, sticky = W);
		frames_off = Entry(self.rootWindow, textvariable = self.frames_off);
		frames_off.grid(row = 2, column = 1, sticky=W);

		motion_threshold_label = Label(self.rootWindow, text = "Motion Threshold (in %): ");
		motion_threshold_label.grid(row = 3, column = 0, sticky = W);
		motion_threshold = Entry(self.rootWindow, textvariable = self.motion_threshold);
		motion_threshold.grid(row = 3, column = 1, sticky=W);

		motion_buffer_smooth_label = Label(self.rootWindow, text = "Motion Area Buffer Smooth (px): ");
		motion_buffer_smooth_label.grid(row = 4, column = 0, sticky = W);
		motion_buffer_smooth = Entry(self.rootWindow, textvariable = self.motion_buffer_smooth);
		motion_buffer_smooth.grid(row = 4, column = 1, sticky=W);

		blur_amount_label = Label(self.rootWindow, text = "Blur Amount (px): ");
		blur_amount_label.grid(row = 5, column = 0, sticky = W);
		blur_amount = Entry(self.rootWindow, textvariable = self.blur_amount);
		blur_amount.grid(row = 5, column = 1, sticky=W);

		blur_angle_label = Label(self.rootWindow, text = "Blur Angle (0 to 180): ");
		blur_angle_label.grid(row = 6, column = 0, sticky = W);
		blur_angle = Entry(self.rootWindow, textvariable = self.blur_angle);
		blur_angle.grid(row = 6, column = 1, sticky=W);


		dir_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		dir_selected_label.grid(row = 7, column = 0, sticky = W);
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 7, column = 1, columnspan = 2, sticky = W);

		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 8, column = 0, sticky = W);		
		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 8, column = 1, columnspan = 2, sticky = W);

	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(int(self.frames_on.get()),self.files_selected);
		

	def execute(self):
		self.status["text"] = "Waiting";
		input_count = len(self.inputfiles);
		frames_on = int(self.frames_on.get());
		frames_off = int(self.frames_off.get());
		motion_threshold = int(self.motion_threshold.get());
		motion_buffer_smooth = int(self.motion_buffer_smooth.get());
		blur_amount = int(self.blur_amount.get());
		blur_angle = int(self.blur_angle.get());
		
		output_count = int(input_count/(frames_on + frames_off));
		output_count = min(output_count, len(self.inputfiles) - frames_on);

		new_names = self.utility.get_file_names_list(1,output_count);
		self.dir_to_save = self.utility.select_output_dir_for_names(new_names);
		if(self.dir_to_save != "" and len(self.inputfiles) > 0):
			commands_list = list();
			temp_names = new_names.keys();
			temp_names.sort();
			for index in range(0,output_count):
				
				files = list();
				for file_index in range(index*(frames_on + frames_off),index*(frames_on+frames_off) + frames_on):
					print("file_index is " + str(file_index) + " out of " + str(len(self.inputfiles)))
					files.append(self.inputfiles[file_index]);
				
				magic_cmd_list = ["convert \\( "];
				for file in files:
					magic_cmd_list.append(file + " ");
				magic_cmd_list.append(" -evaluate-sequence mean \\) \\( \\( ");
				for file in files:
					magic_cmd_list.append(file + " ");
				magic_cmd_list.append(" -evaluate-sequence max \\) \\( ");
				for file in files:
					magic_cmd_list.append(file + " ");
				magic_cmd_list.append(" -evaluate-sequence min \\) ");
				magic_cmd_list.append(" -compose difference -composite -threshold " + str(motion_threshold) + "% -blur 0x"+ str(motion_buffer_smooth) + " \\) ");
				magic_cmd_list.append(" -compose blur -define compose:args=" + str(blur_amount) + "x0+" + str(blur_angle) + " -composite " );
				magic_cmd_list.append(" " + os.path.join(self.dir_to_save,temp_names[index]));
				
				
				magic_cmd = "".join(magic_cmd_list);
				commands_list.append(magic_cmd);
			
			self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
			self.utility.move_files(new_names, self.dir_to_save);
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output directory selected.");
		
				
class Expand:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Expand Series");
		self.window_width = StringVar(self.rootWindow);
		self.window_width.set("9");
		self.window_stdev = StringVar(self.rootWindow);
		self.window_stdev.set("1");
		self.new_count = StringVar(self.rootWindow);
		self.new_count.set("000100.jpg");
				
		self.inputfiles = list();
		self.dir_to_save = "";

		self.setup_gui();
		self.rootWindow.mainloop();
		

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, sticky = E+W);
		go_button = Button(self.rootWindow, text = "Select Output Directory & Execute", command = self.execute);
		go_button.grid(row=0, column = 1, sticky = E+W);
		
		window_width_label = Label(self.rootWindow, text = "Window Width: ");
		window_width_label.grid(row = 1, column = 0, sticky = W);
		window_width = Entry(self.rootWindow, textvariable = self.window_width);
		window_width.grid(row = 1, column = 1, sticky=W);

		window_stdev_label = Label(self.rootWindow, text = "Window StDev: ");
		window_stdev_label.grid(row = 2, column = 0, sticky = W);
		window_stdev = Entry(self.rootWindow, textvariable = self.window_stdev);
		window_stdev.grid(row = 2, column = 1, sticky=W);

		new_count_label = Label(self.rootWindow, text = "Output: 000001.jpg to: ");
		new_count_label.grid(row = 3, column = 0, sticky = W);
		new_count = Entry(self.rootWindow, textvariable = self.new_count);
		new_count.grid(row = 3, column = 1, sticky = W);
		
	
		dir_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		dir_selected_label.grid(row = 4, column = 0, sticky = W);
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 4, column = 1, columnspan = 2, sticky = W);

		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 5, column = 0, sticky = W);		
		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 5, column = 1, columnspan = 2, sticky = W);

	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(int(self.window_width.get()),self.files_selected);
		
	def execute(self):
		self.status["text"] = "Waiting";
		new_count = int(self.new_count.get().split(".")[0]);
		width = int(self.window_width.get());
		stdev = float(self.window_stdev.get());
		orig_count = len(self.inputfiles);
		new_names = self.utility.get_file_names_list(1,new_count);

		self.dir_to_save = self.utility.select_output_dir_for_names(new_names);
		if(self.dir_to_save != "" and len(self.inputfiles) > 0):
			hwidth = width/2.0; # plus or minus on either side of the meanloc (int'ed)
			delta = float(orig_count-1)/float(new_count-1);
			meanloc = 0.0;
			commands_list = list();
			temp_names = new_names.keys();
			temp_names.sort();
			for index in range(0,new_count):
				#print("index is: " + str(index) + " meanloc is: " + str(meanloc));
				orig_min_index = int(meanloc - hwidth)+1;
				orig_max_index = int(meanloc + hwidth);
				if(orig_min_index < 0):
					orig_min_index = 0;
				if(orig_max_index > orig_count - 1):
					orig_max_index = orig_count - 1;
				#print("... orig_min_index is: " + str(orig_min_index) + " orig_max_index is: " + str(orig_max_index));
				weights = list();
				for orig_index in range(orig_min_index,orig_max_index + 1): # want to do this for the whole window
					weights.append(self.get_norm_pdf(meanloc, stdev, orig_index));
				weights_norm = self.normalize_weights(weights);
				
				magic_cmd = self.get_magic_command(orig_min_index, orig_max_index, weights_norm, os.path.join(self.dir_to_save,temp_names[index]));
				commands_list.append(magic_cmd);
				meanloc = meanloc + delta;
			
			self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
			self.utility.move_files(new_names, self.dir_to_save);
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output directory selected.");
		

	def get_magic_command(self, orig_min_index, orig_max_index, weights_norm, outputname):
		magic_cmd_list = ["convert "];
		for i in range(orig_min_index, orig_max_index + 1):
			magic_cmd_list.append(self.inputfiles[i] + " ");
		magic_cmd_list.append(" -poly '");
		for weight in weights_norm:
			magic_cmd_list.append(str(weight) + ",1 ")
		magic_cmd_list.append("'");
		magic_cmd_list.append(" " + outputname);
		return("".join(magic_cmd_list));


	def normalize_weights(self, weights_list):
		sum = 0.0;
		for weight in weights_list:
			sum = sum + weight;
		new_weights = list();
		#print("... sum is: " + str(sum));
		## I did not realize that infinite sum_i of this function actually approaches the integral
		## of this function from -inf to inf = 1. Cool!!
		for weight in weights_list:
			new_weights.append(weight/sum);
		return(new_weights);


	def get_norm_pdf(self, mean, stdev, x):
		first = 1.0/(stdev*math.sqrt(2*math.pi));
		second = math.exp(-1*(x - mean)**2.0/(2.0*stdev**2.0));
		return(first*second);




		

class Rename:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Copy/Rename Files");
		self.start_index = StringVar(self.rootWindow);
		self.start_index.set("000001.jpg");		
		self.inputfiles = list();
		self.dir_to_save = "";

		self.setup_gui();
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, sticky = E+W);
		
		start_number_label = Label(self.rootWindow, text = "Start Index: ");
		start_number_label.grid(row = 1, column = 0, sticky = W);

		start_number = Entry(self.rootWindow, textvariable = self.start_index);
		start_number.grid(row = 1, column = 1, sticky=W);
		
		go_button = Button(self.rootWindow, text = "Select Output Directory & Execute", command = self.execute);
		go_button.grid(row=0, column = 1, sticky = E+W);
		
		dir_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		dir_selected_label.grid(row = 2, column = 0, sticky = W);
		
		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 3, column = 0, sticky = W);
		
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 2, column = 1, columnspan = 2, sticky = W);

		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 3, column = 1, columnspan = 2, sticky = W);

	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(1,self.files_selected);
	
	def execute(self):
		self.status["text"] = "Waiting";
		new_names_list = self.utility.get_file_names_list(int(self.start_index.get().split(".")[0]), len(self.inputfiles));
		self.dir_to_save = self.utility.select_output_dir_for_names(new_names_list);
		new_names_list = new_names_list.values();
		new_names_list.sort();
		if(self.dir_to_save != "" and len(self.inputfiles) > 0):
			if(self.dir_to_save != ""):
				for index in range(0,len(self.inputfiles)):
					oldfile = self.inputfiles[index];
					newfile_end = new_names_list[index];
					newfile = os.path.join(self.dir_to_save,newfile_end);
					shutil.copy2(oldfile, newfile);
			
				self.status["text"] = "Done!";
				self.rootWindow.update();
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output directory selected.");
			
	
	

class CreateVideo:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Create Video from Frames");
		self.frame_rate = StringVar(self.rootWindow);
		self.frame_rate.set("24");		
		self.height = StringVar(self.rootWindow);
		self.height.set("720");		
		
		self.inputdir = "None";
		self.file_to_save = "";

		self.setup_gui();
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Folder", command = self.select_dir);
		select_button.grid(row=0, column = 0, columnspan = 2, sticky = E+W);

		files_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		files_selected_label.grid(row = 1, column = 0, sticky = W);
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 1, column = 1, columnspan = 2, sticky = W);
		
		frame_rate_label = Label(self.rootWindow, text = "Frame Rate: ");
		frame_rate_label.grid(row = 2, column = 0, sticky = W);
		frame_rate_box = Entry(self.rootWindow, textvariable = self.frame_rate);
		frame_rate_box.grid(row = 2, column = 1, sticky=W);

		height_label = Label(self.rootWindow, text = "Video Height: ");
		height_label.grid(row = 3, column = 0, sticky = W);
		height_box = Entry(self.rootWindow, textvariable = self.height);
		height_box.grid(row = 3, column = 1, sticky=W);		
		
		go_button = Button(self.rootWindow, text = "Select Output File & Execute", command = self.execute);
		go_button.grid(row=4, column = 0, columnspan = 2, sticky = E+W);
		
		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 5, column = 0, sticky = W);
		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 5, column = 1, columnspan = 2, sticky = W);
		


	def select_dir(self):
		self.status["text"] = "Waiting";
		self.inputdir = self.utility.select_input_dir_with_numnames(self.files_selected);

	def execute(self):
		self.status["text"] = "Waiting";
		self.file_to_save = tkFileDialog.asksaveasfilename(defaultextension = ".mp4", initialdir = self.inputdir, title = "Video file to write to (default extn: mp4).");
		if(self.file_to_save != "" and self.inputdir != ""):
			self.status["text"] = "Running; unknown ETA... this dialog should update when finished (look for 'ffmpeg' in system status)";
			self.rootWindow.update();
			framerate = self.frame_rate.get();
			height = self.height.get();
			cmd = "ffmpeg -y -r " + framerate + " -i " + os.path.join(self.inputdir,"%06d.jpg") + " -b:v 12000k" + " -filter:v scale='trunc(oh*a*2)/2:" + height + "' "  + self.file_to_save;
			print("cmd: " + cmd)
			subprocess.check_output(cmd, shell = True);
						
			self.status["text"] = "Done!";
			self.rootWindow.update();
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output directory selected.");
		
class ConcatVideos:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Concatenate Videos");
		self.inputfiles = list();
		self.file_to_save = "";

		self.setup_gui();
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, sticky = E+W);
		
		go_button = Button(self.rootWindow, text = "Select Output File & Execute", command = self.execute);
		go_button.grid(row=0, column = 3, sticky = E+W);
		
		dir_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		dir_selected_label.grid(row = 1, column = 0, sticky = W);
		
		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 2, column = 0, sticky = W);
		
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 1, column = 1, columnspan = 3, sticky = W);

		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 2, column = 1, columnspan = 3, sticky = W);

	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(2,self.files_selected);
			
	def execute(self):
		self.status["text"] = "Waiting";
		self.file_to_save = self.utility.get_file_to_save(".mp4");
		if(self.file_to_save != "" and len(self.inputfiles) > 0):

			self.input_list_filename = self.file_to_save + ".txt";
			list_handle = io.open(self.input_list_filename, "wb");
			for inputfile in self.inputfiles:
				list_handle.write("file '" + inputfile + "'" + "\n");
			list_handle.close()

			self.status["text"] = "Running; unknown ETA... this dialog should update when finished (look for 'ffmpeg' in system status)";
			self.rootWindow.update();

			cmd = "ffmpeg -f concat -i " + self.input_list_filename + " -c copy " + self.file_to_save;
			subprocess.check_output(cmd, shell = True);			

			self.status["text"] = "Done!";
			self.rootWindow.update();

			os.remove(self.input_list_filename);
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output file selected.");


class LightenDarken:
	def __init__(self, utility):
		self.utility = utility;
		self.rootWindow = Tk();
		self.rootWindow.wm_title("Lighten/Darken Merge");
		self.merge_type = StringVar(self.rootWindow);
		self.merge_type.set("Lighten");		
		self.inputfiles = list();
		self.file_to_save = "";

		self.setup_gui();
		self.rootWindow.mainloop();

	def setup_gui(self):
		select_button = Button(self.rootWindow, text = "Select Input Files", command = self.select_files);
		select_button.grid(row=0, column = 0, sticky = E+W);
		
		options_dropdown = OptionMenu(self.rootWindow, self.merge_type, "Lighten", "Darken", "Lighten_Intensity", "Darken_Intensity");
		options_dropdown.grid(row = 0, column = 1, sticky = E+W);
		
		go_button = Button(self.rootWindow, text = "Select Output File & Execute", command = self.execute);
		go_button.grid(row=0, column = 3, sticky = E+W);
		
		dir_selected_label = Label(self.rootWindow, text = "Files Selected: ");
		dir_selected_label.grid(row = 1, column = 0, sticky = W);
		
		status_label = Label(self.rootWindow, text = "Status: ");
		status_label.grid(row = 2, column = 0, sticky = W);
		
		self.files_selected = Label(self.rootWindow, text = "None           ");
		self.files_selected.grid(row = 1, column = 1, columnspan = 3, sticky = W);

		self.status = Label(self.rootWindow, text = "Waiting");
		self.status.grid(row = 2, column = 1, columnspan = 3, sticky = W);

	def select_files(self):
		self.status["text"] = "Waiting";
		self.inputfiles = self.utility.select_input_files(2,self.files_selected);
			
	def execute(self):
		self.status["text"] = "Waiting";
		self.file_to_save = self.utility.get_file_to_save(".jpg");
		if(self.file_to_save != "" and len(self.inputfiles) > 0):
			mergetype = self.merge_type.get();
			## make a copy of the first one, for use in the loop
			shutil.copy2(self.inputfiles[0], self.file_to_save);	
			commands_list = list();
			for filename in self.inputfiles:
				
				cmd = "convert " + filename + " " + self.file_to_save + " -compose " + mergetype + " -composite " + self.file_to_save;
				commands_list.append(cmd);

			self.utility.execute_command_series(commands_list, self.status, self.rootWindow);
						
			## display it for the user's review
			Temp_image_window(self.file_to_save);
		else:
			tkMessageBox.showerror("Oops.", "Either no input files selected, or no output file selected.");
		

class Temp_image_window:
	def __init__(self, filename):
		## make a copy of it in png format so we can display it with tkinter without PIL
		gifname = filename + ".gif";
		cmd = "convert " + filename + " -resize 800x600 " + gifname;
		result = subprocess.check_output(cmd, shell = True);
		
		## display it			
		self.rootWindow = Tk();
		self.rootWindow.wm_title(filename);		
		img = PhotoImage(file = gifname, master = self.rootWindow);
		label = Label(self.rootWindow, image=img);
		label.grid();
		
		self.rootWindow.mainloop();
		
		## remove it
		os.remove(gifname);
		


main_window = MainRoot();
