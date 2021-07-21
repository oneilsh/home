;; Given a tree written in preorder (I think), tell me the "skew"
;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Main method, just opens the files and gives the buffers to the recursion
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun skew ()
	(with-open-file (skew-out "skew.out" :direction :output)
	(with-open-file (skew-in "skew.in" :direction :input)
		(docase skew-in skew-out 0)
	))	
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Gets each case, and if it was an empty case it ends
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun docase(skew-in skew-out counter)
	(let ((tosplit (skew2 () skew-in skew-out))(counter (+ counter 1)))
		(if (not (null tosplit))
			(progn
				(list 'hello (input tosplit))
				(format skew-out "Case ~D: The skew factor is ~D~%" counter (input tosplit))
				(docase skew-in skew-out counter)
			)
		)
	)
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;   Read the input until you get to a blank line
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun skew2 (y skew-in skew-out)
	(setf word (read-line skew-in))
	;(print y)
	(if (equal word "")
		;if the word read was "" return
		y
		;else return
		(let
			((y (append y (list word))))
			(skew2 y skew-in skew-out)
		)
	)
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  Takes an input and gives it to listsplit, using the first entry as
;;  where to split and the rest as what to split
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(DEFUN input (x)
	;(print x)
	(let
		((justsplit (listsplit () (rest x) (first x))))
		(+	
			(computeskew (length (first justsplit)) (length (second justsplit)))
			(if (equal (first justsplit) ())
				0; (length (second justsplit))
				(input (first justsplit))
			)
			(if (equal (second justsplit) ())
				0; (length (first justsplit))
				(input (second justsplit))
			)
		)
	)
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Compute the skew of a node given number of nodes on each side
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(DEFUN computeskew (x y)
	(let ((   y (abs (- x y))    ))
		(if (<= y 1)
			0
			(- y 1)
		)
	)
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;;  takes an empty, a string, and where to break, and makes two lists out of it
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(DEFUN LISTSPLIT (y x where)
	(if (string> where (first x))
			(if (equal (first x) ())		;take an element off of the second list and add it to the first
				(list y x)								;if the second list is null, just return the two
				(listsplit (append y (list (first x))) (rest x) where)	;else actually take one off the second and add it to the first
			)
			(list y x)							;return both lists now that they are finished
	)
)

