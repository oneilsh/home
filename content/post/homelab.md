+++
banner = "homelab/banner.png"
categories = []
date = "2019-05-18"
description = ""
images = []
menu = ""
tags = ["diy", "infrastructure"]
title = "Homelab"
nodateline = false
+++

Sometimes you just get tired of paying AWS & Digital Ocean. A quick sketch of my homelab, inspired by the good folks at [r/homelab](http://reddit.com/r/homelab).

The main goal of the project was to enable the quick creation of Virtual Machines (or containers), accessible by subdomain such as project1.mydomain.net, project2.mydomain.net, etc., all under a single dynamic IP address. I managed to make it work pretty well, with a little 
help from NGINX for reverse proxying, pfsense for local DNS and routing, ddclient for dynamic DNS, Proxmox for hypervisor management, and freeNAS for shared storage.

<iframe src="https://a0f5e397-e761-40ed-b772-7483ae90b62e.htmlpasta.com/" style="width: 100%; height=1000px; "></iframe>

Note that the diagram is interactive (click for full-page version), and enabling or disabling certain layers may help in browsing. Configuration notes are included. 

<!-- <a href="https://a0f5e397-e761-40ed-b772-7483ae90b62e.htmlpasta.com/"><img src="homelab/homelab_screenshot.png"></img></a> -->

