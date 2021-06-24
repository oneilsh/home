+++
banner = "research_transcriptomics/ohr.png"
categories = []
date = "2013-02-04"
description = ""
images = []
menu = ""
tags = ["Research", "Programming"]
title = "Transcriptomics"
nodateline = false 
+++


Assessing the quality and completeness of transcriptome assemblies is a challenge. For this problem, we developed a measure of gene assembly known as the "ortholog hit ratio." First we associate each assembled gene with its closest match in a related organism. Then we compare the length of the matching region to the total length of the related gene. (Comparing the length of only the matching region ignores untranslated regions on the ends that are not considered part of the gene.) When this ratio is near 1, the sequence is likely to be completely assembled. This measure has since been adopted by [other research groups](http://scholar.google.com/scholar?q=%22ortholog+hit+ratio%22&hl=en&btnG=Search&as_sdt=1%2C15&as_sdtp=on). ([Paper](http://www.biomedcentral.com/1471-2164/11/310).)


{{< figure src="research_transcriptomics/ohr.png" alt="Distribution of ortholog hit ratios for a transcriptome assembly." width="80%" >}}


While assembly metrics for whole-genome assembly are straightfoward, those for transcriptome assembly (such as OHR above) often are not and thus it is more difficult to determine their relevance and accuracy. We simulated transcriptome data from a well-characterized species (Drosophila melanogaster) and produced both "perfect" and "realistic" assemblies, empirically testing a variety of metrics to see if they consistenty reflected the higher quality of perfect assemblies and increased dataset size. Amongst a large cache of results, we found that errors in the OHR metric are largely determined by relative expansion/contraction of transcripts in the evolutionary history of the species compared against. ([Paper](http://www.biomedcentral.com/content/pdf/1471-2164-14-465.pdf).)


{{< figure src="research_transcriptomics/assembly_metrics.png" alt="Top: Distributions of ratios of ortholog hit ratios (OHR Error). Bottom: Distributions of ratios of the ratios of ortholog hit ratios to the ratio of amino-acid lengths. (Well, when you write it all out like that...)" width="70%" >}}
