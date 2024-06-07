{:title "Relations"
 :layout :page
 :page-index 15
 :navbar? false
 :section "B Syntax Overview"
 :toc true}


Relations over two sets are a subset of the cartesian product of two sets.
It contains only tuples with the first element in the first base set, and the second in the other.

## Maplet / Tuple

```lisb
(maplet 1 2)
(maplet 1 "foo")
(|-> 1 "foo")
```

```b-expr
1|->2
(1,2)
```

## Sets of Relations

The operators below describes the set of all possible relations (which are total, surjective or both total and surjective):

```lisb
(<-> #{1 2} #{4 5})
(relation #{1 2} #{4 5})
```

### Total Relation

```lisb
(<<-> #{1 2} #{4 5})
(total-relation #{1 2} #{4 5})
```

### Surjective Relation

```lisb
(<->> #{1 2} #{4 5})
(surjective-relation #{1 2} #{4 5})
```

### Total Surjective Relation

```lisb
(<<->> #{1 2} #{4 5})
(total-surjective-relation #{1 2} #{4 5})
```

## Domain and Range

```lisb
(dom #{(|-> 1 2) (|-> 1 3) (|-> 2 2)})
(ran #{(|-> 1 2) (|-> 1 3) (|-> 2 2)})
```

## Identity Relation

```lisb
(id #{1 2 3})
```


## Restrictions and Subtractions

```lisb
(<| #{1} #{(|-> 1 2) (|-> 1 3) (|-> 2 2)})
(domain-restriction #{(|-> 1 2) (|-> 1 3) (|-> 2 2)})
```

```lisb
(<<| #{1} #{(|-> 1 2) (|-> 1 3) (|-> 2 2)})
(domain-substraction #{(|-> 1 2) (|-> 1 3) (|-> 2 2)})
```

```lisb
(|> #{(|-> 1 2) (|-> 1 3) (|-> 2 2)} #{2})
(range-restriction  #{(|-> 1 2) (|-> 1 3) (|-> 2 2)} #{2})
```

```lisb
(|>> #{(|-> 1 2) (|-> 1 3) (|-> 2 2)} #{2})
(range-subtraction  #{(|-> 1 2) (|-> 1 3) (|-> 2 2)} #{2})
```

## Inverse

```lisb
(inverse #{(|-> 1 2) (|-> 1 3) (|-> 2 2)} #{2})
```

## Relational Image

```lisb
(image #{(|-> 1 2) (|-> 1 3) (|-> 2 2) (|-> 3 42)} #{1,2})
```

## Misc Operators

```lisb
(<+ #{(|-> 1 2) (|-> 1 3) (|-> 2 3)} #{(|-> 1 0) (|-> 4 0)})
(override #{(|-> 1 2) (|-> 1 3) (|-> 2 3)} #{(|-> 1 0) (|-> 4 0)})
```

```lisb
(>< #{(|-> 1 2) (|-> 2 3) (|-> 3 4)} #{(|-> 2 6) (|-> 4 4)})
(direct-product #{(|-> 1 2) (|-> 2 3) (|-> 3 4)} #{(|-> 2 6) (|-> 4 4)})
#{[:x :y :z] | (and (member? (|-> :x :y)  #{(|-> 1 2) (|-> 2 3) (|-> 3 4)}) (member? (|-> :x :z) #{(|-> 2 6) (|-> 4 4)}))} ;; according to wiki
```

```lisb
(composition #{(|-> 1 2) (|-> 2 3) (|-> 3 4)} #{(|-> 2 6) (|-> 4 4)})
#{[:x :y] | (exists [:z] (and (member? (|-> :x :z)  #{(|-> 1 2) (|-> 2 3) (|-> 3 4)}) (member? (|-> :z :y) #{(|-> 2 6) (|-> 4 4)})))} ;; according to wiki
```

```lisb
(parallel-product #{(|-> 1 2) (|-> 3 4)} #{(|-> 5 6) (|-> 7 8)})
#{[(|-> (|-> :x :v) (|-> :y :w))] | (and (member? (|-> :x :y) #{(|-> 1 2) (|-> 3 4)}) (member? (|-> :y :w) #{(|-> 5 6) (|-> 7 8)}))}
```

## Projection

The projection functions allow extracting the first or the second element of a tuple, respectively.
They must be parameterized with the correct sets.

```lisb
(prj1 nat-set nat-set)
(fn-call (prj1 nat-set nat-set) (bmaplet 1 2))
(fn-call (prj2 nat-set nat-set) (bmaplet 1 2))
(fn-call (prj2 string-set nat-set) (bmaplet 1 2))
```


## Closures

The closure operator seems to keep the set symbolic.

```lisb
(closure #{(|-> 1 2) (|-> 2 3) (|-> 3 4) (|-> 4 2)})
(closure1 #{(|-> 1 2) (|-> 2 3) (|-> 3 4) (|-> 4 2)})
(iterate #{(|-> 1 2) (|-> 2 3) (|-> 3 4) (|-> 4 2)} 1)
(iterate #{(|-> 1 2) (|-> 2 3) (|-> 3 4) (|-> 4 2)} 2)
(iterate #{(|-> 1 2) (|-> 2 3) (|-> 3 4) (|-> 4 2)} 3)
```

## Relation to Function, Function to Relation

```lisb
(fnc #{(|-> 1 2) (|-> 1 3) (|-> 2 4)})
(rel #{(|-> 1 #{1 2}) (|-> 2 #{4})})
```
