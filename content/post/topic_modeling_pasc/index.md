---
banner: "images/fig4_banner.png"
title: "Finding Long-COVID: Temporal Topic Modeling EHR Data"
author: Shawn T. O'Neil
date: '2023-09-12'
categories: []
tags:
  - research
  - data
  - biomedical
---

In this study, currently available as a [preprint](https://www.medrxiv.org/content/10.1101/2023.09.11.23295259v1) awaiting peer-review, I apply an unsupervised learning technique known as Latent Dirichlet Allocation (LDA) to identify condition clusters across millions of patients in the [N3C](/n3c) database. Such clusters are not guaranteed to be related to COVID-19, however. To identify those that are, we look at how patients are assigned to clusters before and after COVID-19 infection, integrating the probabilistic nature of LDA and supervised models (repeated-measures logistic regression) to predict how patients will migrate toward or away from a cluster post-infection, moderated by demographic factors such as age, sex, and wave of infection.

A primary goal here was to apply a data-first approach to identifying Long COVID. As of October 2021 there is an ICD-10 diagnosis code for Long COVID, U09.9. However, it is now generally understood that Long COVID (also known as PASC, for Post-Acute Sequelae of SARS-CoV-2 infection) is a complex set of conditions with varying presentations. Might our shifting understanding of this disease be causing us to miss important sub-types? To check, we looked for conditions increased in patients with Long-COVID diagnoses compared to COVID-naïve Control patients over a similar time frame. We also looked at COVID-positive patients without a Long-COVID diagnosis, finding that there are indeed clusters of conditions prevalent post-infection but not associated with U09.9 diagnoses. Fortunately, these seem to fall into two general categories: clusters of generic conditions indicative of unknown symptoms (such as "Ill-defined disease"), and long-term acute illness (such as persistent pneumonia). Interestingly, we find a signal of reduced healthcare access through the pandemic, which reveals itself as decreased rates of conditions commonly discovered during routine care.

{{< figure src="images/past_volcano_plot_svg.png" alt="Conditions increased for COVID-19 patients post-infection, for those with a Long-COVID diagnosis (right) and those without (left)" width="100%" >}}

Some of my favorite features of this work are provided by the flexibility gained from patient/cluster assignment provided by the LDA model, typically used to identify word clusters (called topics) in collections of documents like journal articles or web pages. LDA assigns each patient (in this case) a probability distribution over clusters, where each cluster is a probability distribution over conditions. We can do this for patient records over defined periods of time as well, allowing a creative methodological innovation of treating each patient/cluster assignment as a binomial trial, which we model by repeated-measures logistic regression. This allows us to see how cluster assignment is influenced by factors such as patient demographics, COVID infection status, and Long-COVID diagnosis.

Beyond modeling, we can see how patient/cluster assignment trends across other factors, like which site patient data are drawn from. The figure below illustrates that some clusters are not used uniformly by various healthcare organizations. Although we do not know which specific organization each patient comes from (for patient privacy), we do have hints, for example OMOP (PEDSNET) sites in the figure below display distinct trends in cluster usage relating to their pediatric focus.

{{< figure src="images/topic_data_partner_usage.png" alt="Relative condition cluster usage across data providers." width="100%" >}}

Finally, it is worth noting the value of large datasets in unsupervised learning. While most applications of LDA to structured health data to date have found anywhere from 6 to 60 coherent clusters, our modeling of millions of patients and hundreds of millions condition records reveals hundreds of clinically-relevant clusters, including rare diseases and their corresponding symptoms.