{:title "Set Operators"
 :layout :page
 :page-index 10
 :navbar? false
 :section "B Syntax Overview"
 :toc true}



## Set Literals

### Empty Set 
The empty set is a literal:

```lisb
#{}
```

### Enumeration Set
Sets can contain one or more elements.
Note that in B, you may write duplicates that are removed (by putting them into a set).
lisb, on the other hand, uses Clojure sets to represent a set,
so duplicates yield an error in the reader.

```lisb
#{1,2,3}
```

### Comprehension Sets

Sets can also be created by providing a predicate that shall be fulfilled by the elements:


```lisb
(comprehension-set [:x] (and (< 0 :x 10) (= 0 (mod :x 2))))
#{[:x] | (and (< 0 :x 10) (= 0 (mod :x 2)))}
```



## Subset Operators

The powerset `POW` is the set of all subsets of the argument.
The `POW1` operator does not include the empty set.

```lisb
(pow #{1 2 3})
(pow1 #{1 2 3})
```

Similarly, all *finite* subsets are generated with the `FIN` operator,
while `FIN1` does also not include the empty set.
```lisb
(pow #{1 2 3})
(pow1 #{1 2 3})
```

## Cardinality / Size of Set

You can get the number of elements contained in a set by using `card`:

```lisb
(card #{1 2 3})
```

## Set Union, Intersection, Difference


```lisb
(union #{1 2} #{1 3})
(intersection #{1 2 3} #{3 4})
(set- #{1 2 3} #{3 4})
```

## Membership Test

```lisb
(member? 1 #{1 2 3})
(member? 0 #{1 2 3})
(contains? #{1 2 3} 1)
(contains? #{1 2 3} 1 2)
(contains? #{1 2 3} 1 2 3 4)
```

B also has an operator to check that an element is *not* contained set.
In lisb, you wrap the `member?` predicate in `(not ...)`.

```lisb
(not (member? 42 #{1 2 3}))
```

## Subset Test

```lisb
(subset? #{1 2} nat-set)
(subset? #{1 2 3 4 5} nat-set)
(not (subset? #{1 2 3 4 5} nat-set))
(superset? #{0 1 2 3 4 5} nat-set)
(strict-subset? #{0 1 2 3} nat-set)
```

## Generalised Operators

```lisb
(unite-sets #{#{1 2} #{3 4}})
(intersect-sets #{#{1 2 3} #{2 3 4}})
```

```lisb
(union-pe [:x] (subset? :x #{7 8 9}) #{(card :x)})
(intersection-pe [:x] (subset? :x #{7 8 9}) #{(card :x)})
```


## Cartesian Product

```lisb
(cartesian-product #{1 2} #{3 4})
```


## Interval

```lisb
(interval 1 5)
(range 1 5)
```
