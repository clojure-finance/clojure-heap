# clojure-heap
Heap for Clojure
uses two different ways

## 1. heap_from_java
Functions in this package implies heap based on 
java *PriorityQueue* library. 

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
