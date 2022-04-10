# clojure-heap
Heap for Clojure
uses two different ways

## 1. heap_from_java
Functions in this package implies heap based on java *PriorityQueue* library. 

Function features are as follows:

#### 1) init

`(init)  (init comparater)`

Heap initialize function. Return an empty heap which is initialized with the comparater. 

One optinal argument *comparater* gives the priority determine function for the heap. For example, if comparater is `>`, it will return an maximum heap. 
By default, the priority is determined by objects’ natural ordering. 

e.g. `(init <)` will return an empty heap in which the priority is give by <. 

#### 2) push

`(push heap element)`

Push function. Insert one element into the heap. Return an updated heap. 

e.g. `(push heap0 3)` will push 3 into heap0 and sort the heap again. 

#### 3) get-root

`(get-root heap)`

Return the current root of heap. 
This operation will not change the heap structure itself. 

e.g. Suppose heap1 is a min heap as follows:

![image](https://user-images.githubusercontent.com/55539684/162600574-9c3d7b6e-e2df-40aa-8073-2c1a8e0ca109.png)

`(get-root heap1)` will return 3. And heap1 still look like above. 

#### 4) pop

`(pop heap)`

Pop out and return the root of heap. 
This operation will return the root of heap and then remove it. After that, heap will also be reordered. 

e.g. Suppose heap1 is a heap mentioned above. `(pop heap1)` will return 3, but heap1 will now turn into:

![image](https://user-images.githubusercontent.com/55539684/162600695-9ae820f3-75c8-42f3-9f95-d6a77068601a.png)

which is already updated. 

#### 5) size

`(size heap)`

Return the number of elements inside the heap. 

e.g. Suppose heap2 is the new heap after the popping above. `(size heap2)` will return 4. 


## 2. heap_from_clj

Functions in this package is a pure Clojure implement of heap. 

Function features are as follows:

#### 0) initialize heap node

`(Heapnode. [data] [left-child-node] [right-child-node])`

All the three elements can be `nil` if do not have value yet. 

#### 1) make-heaptree 

`(make-heaptree & root order)`

Return an initialized heap. `root` can be a single root with nil left child and right child, or the root of an existing tree structure.  `order` can be either `"ASC"` or `"DESC"`, refers to in order and reverse order accordingly. Default root is empty, and default order is ASC. 

One thing should be noticed is that, if the root is from an existing tree structure, this construction function will **NOT** check the order. 

e.g. `(make-heaptree (Heapnode. 7 nil nil) "DESC")` will return a heap with root equals to 7, reverse priority. 

#### 2) heap-compare

`(heap-compare x1 x2)`

Return whether x1 should be a parent node of x2 or not. Users can modify this functions to build different comparaters. 

e.g. if `(heap-compare a b)` returns `True`, then the node with value a will have higher priority than node with value b in an ASC heap. 

#### 3) heap-sort

`(heap-sort heap)`

Return a sorted heap. Private. Sort to put root to the right place

#### 4) find-leave 

`(find-leave heap)`

Return `[[leaf-value] [new-heap]]`. Private. Find a leaf node to replace root while doing pop. 

#### 5) heap-push

`(heap-push heap data)`

Push data into the heap and sort. Return the new heap after pushing. 

The user **SHOULD** assign the return value again to get the heap updated. 

e.g. `(def heap0 (heap-push heap0 8))` is to update heap0 by pushing 8 into it. 

#### 6) heap-pop

`(heap-pop heap)`

Pop the heap root and sort. Return a two-element vector. The first element is the data, and second one is the heap after pop operation. 

The user **SHOULD** assign the second return value to the origional heap to get it updated. 

e.g. `(def tree (do (let [ret (heap-pop tree)] (println (first ret)) (second ret))))` will print out the root and get tree updated. 

#### 7) heap-get-root

`(heap-get-root heap)`

Return the root value of the heap. This operation will not make any change to the heap itself. 
