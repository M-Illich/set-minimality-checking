# set-minimality-checking
This Java project aims to develop an efficient way of checking if a given test set is minimal w.r.t. a collection of sets, which is the case if the latter does not contain any subset of the tested set. Here, the general approach is to represent sets as some kind of sortable objects, thus enabling to iterate over all the elements that represent a suitable subset candidate. 
In the related implementation, this is realized by some abstract structures for iterating over objects that meet some matching conditions, as well as concrete implementations for set representations based on the idea of modeling a set as a bit vector where the positions of the entries are defined by the set's elements (e.g. using their hash code).

A performance comparison of the project is given by [setmincheck-experiment](https://github.com/M-Illich/setmincheck-experiment.git).

-------------------------------------------------------------------------
Copyright 2020 Moritz Illich

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
