+++
banner = "bobcash/banner.png"
categories = []
date = "2007-10-20"
description = ""
images = []
menu = ""
tags = ["Programming", "Class"]
title = "CSE60622 Cryptography. BobCash"
nodateline = true
+++


This was a project I did for Cryptography, which implemented a digital cash scheme as a web service. The idea behind digital cash is that, well, it's digital cash. Which means, it's a digital object which has value and can be traded/spent, but is also anonymous. This is accomplished via digital signatures. Alice, who has money in the bank, sends the bank an string of random letters and an amount to withdraw. The bank then confirms that she has that much money in her account, removes the money from the account, and "signs" the string of letters; the signature is sent back to Alice. Using the signature, Alice can prove to anybody who cares to check that the string of letters really does represent money from the bank.

(This relates to Bitcoin and other modern digital currencies, except that in those systems the signature is not kept via a centralized bank, but rather on a "public ledger" called the blockchain.)

{{< figure src="bobcash/bobcash_web.jpg" width="50%" >}}

There's more to digital cash than that, of course, but that's the idea. See the paper for more details. Here, the string of letters and the signature is all wrapped up in a jpeg using steganography. This makes it easy to transfer digital cash between parties, as the image itself then "becomes" the cash. This project was an exercise in security for me: all connections are SSL enabled, including the connection to random.org from which true random data is drawn from. Strong password libraries are used, etc. The only weakness currently present is the use of only 20 random bits used in the blinded signature protocol. I also needed to implement RSA style blind signatures myself, as I didn't find any libraries for such purpose.

Files: [Proposal](/bobcash/proposal.pdf) (pdf), [Paper](/bobcash/paper.pdf) (pdf).