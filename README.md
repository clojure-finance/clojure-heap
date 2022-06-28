# Clojure Heap

A pure Clojure implementation of heap. 

### Installation

Copy the source code in [`src/clojure/heap/core.clj`](src/clojure/heap/core.clj) to your project.

### Speed

Equal to the theoretical limit.

**Single** **`add`**: O(logn)

**Single** **`poll`**: O(logn)

**Space**: n

### APIs

#### `heap`

Create a heap with a comparator[ and an entry]. O(1)

| Argument     | Type     | Function                                         | Remarks                                                      |
| ------------ | -------- | ------------------------------------------------ | ------------------------------------------------------------ |
| `comparator` | Function | A comparator function, deciding a max / min heap | Two arguments. Return `true` on matching the condition, `false` otherwise |
| [`value`]    | Any      | The first entry of the heap                      |                                                              |

**Example**

```clojure
;; define a max heap containing maps
(def x (heap (fn [a b] (> (:id a) (:id b))) {:id 3}))
;; without initial value
(def x (heap (fn [a b] (> (:id a) (:id b)))))
```



#### `get-size`

Get the size (length) of heap. O(1)

| Argument | Type           | Function      | Remarks |
| -------- | -------------- | ------------- | ------- |
| `heap`   | heap.core.Heap | A heap object |         |

**Example**

```clojure
(def x (heap (fn [a b] (> (:id a) (:id b))) {:id 3}))
(get-size x)
;; return 1
```



#### `peek`

Get the top value of the heap. If it is a min heap, return the smallest value; otherwise return the largest value. Return nil if the heap is empty. O(1)

| Argument | Type           | Function      | Remarks |
| -------- | -------------- | ------------- | ------- |
| `heap`   | heap.core.Heap | A heap object |         |

**Example**

```clojure
(def x (heap (fn [a b] (> (:id a) (:id b))) {:id 3}))
(peek x)
;; return {:id 3}
```



#### `add`

Insert an entry to the heap. The heap will be reorganized to fit the new value. O(logn), n = size of the heap

| Argument | Type           | Function                             | Remarks                                                |
| -------- | -------------- | ------------------------------------ | ------------------------------------------------------ |
| `heap`   | heap.core.Heap | A heap object                        |                                                        |
| `value`  | Any            | The value to be inserted to the heap | Should be applicable as one argument of the comparator |

**Example**

```clojure
(def x (heap (fn [a b] (> (:id a) (:id b))) {:id 3}))
(push x {:id 4})
(get-size x)
;; return 2
```



#### `poll`

Delete and return the top value of the heap. If it is a min heap, return the smallest value; otherwise return the largest value. O(logn), n = size of the heap

| Argument | Type           | Function      | Remarks |
| -------- | -------------- | ------------- | ------- |
| `heap`   | heap.core.Heap | A heap object |         |

**Example**

```clojure
(def x (heap (fn [a b] (> (:id a) (:id b))) {:id 3}))
(pop x)
;; return {:id 3}
(pop x)
;; return nil
```

