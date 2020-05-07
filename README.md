# set-minimality-checking
This Java project aims to develop an efficient way of checking if a given test set is minimal w.r.t. a collection of sets, which is the case if the latter does not contain any subset of the tested set. Here, the general approach is to represent sets as some kind of sortable objects, thus enabling to iterate over all the elements that represent a suitable subset candidate. 
In the related implementation, this is realized by some abstract structures for iterating over objects that meet some matching conditions, as well as concrete implementations for set representations based on the idea of modeling a set as a bit vector where the positions of the entries are defined by the set's elements (e.g. using their hash code).

A performance comparison of the project is given by [setmincheck-experiment](https://github.com/M-Illich/setmincheck-experiment.git).
