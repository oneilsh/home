---
banner: "images/banner.png"
title: DataScience@OSU
author: Shawn T. O'Neil
date: '2020-08-10'
categories: []
tags:
  - infrastructure
---

DS@OSU was a project I had the priveledge of co-leading (along with Dr. Robin Pappas) in collaboration with Oregon State UIT. Driven by a broadly recognized need to enable accessible and scalable infrastructure for data science (and related) instruction, we embarked on campus-wide needs assessments, a steering committee, technical and faculty advisory committees, fact-finding sessions with peer institutions, and eventually platform implemention.


Based on the [Zero to JupyterHub](https://zero-to-jupyterhub.readthedocs.io/en/stable/) kubernetes-based deployment pattern, DS@OSU provides a number of additional custom features:

* Cloud-based, autoscaling access to JupyterLab, RStudio, command-line, and related data science 
tools for classes and similar workgroups.

* Data for each "Hub" (e.g. for a single class) lives in a hub-unique space, with two levels of permissions: 
  admins (e.g. instructors and TAs) have read/write access nearly everywhere in the class data space, including 
  inside users (e.g. students) home folders, while users have read/write access in their own home folders and designated
  shared-data folders. 

* Login from Canvas, with admin/user roles determined by Canvas roles (instructors and TAs are admins, students are 
  regular users). Login with [NativeAuthenticator](https://native-authenticator.readthedocs.io/en/latest/) is also supported to independent user management (sans Canvas).

* Admins/Instructors can easily add hub-wide Python packages, R packages, and command-line utilities/scripts via the permissioned shared data space. (Individual users can also 
  install packages for their own use.)

* Admins/Instructors can customize user environments with login and startup scripts, including for Python notebooks, bash, and R/Rstudio. 

* Optional computational profiles for different compute needs, including GPU-compute. Adjustable auto-renewing quotas encourage judicious use and control costs. 

[Technical documentation](https://datasci-osu.github.io/dsosuk8s/docsify/#/)


{{< fluid_imgs 
  "pure-u-1-2|images/lab_initial_login.png" 
  "pure-u-1-2|images/terminal_permissions.png" 
  "pure-u-1-2|images/dshub_profiles.png" 
  "pure-u-1-2|images/dshub_profiles_popup.png" 

>}}

