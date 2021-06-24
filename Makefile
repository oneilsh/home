

html: 
	hugo

sync:
	rsync -aiuv --stats --progress public/ shawntoneil.com:~skeezene/public_html/shawntoneil/ 
	rsync -aiuv index.php/ shawntoneil.com:~skeezene/public_html/shawntoneil/
