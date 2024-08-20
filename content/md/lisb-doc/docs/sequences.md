{:title "Sequences"
 :layout :page
 :page-index 25
 :navbar? false
 :section "B Syntax Overview"
 :toc true}


Sequences are special functions that map the indices 1 to n to values.

## Literals

The empty sequence can be written in B as follows:
```b-expr
<>
[]
```

A sequence containing elements looks like:

```b-expr
[42, 1337, 97]
```

In lisb style:


```lisb
(sequence)
(sequence 42 1337 97)
```


## Sets of Sequences

Note that the following expressions construct *all* possible sequences mapping to the values of the set.
Thus, there are infinitely many.


```lisb
(seq #{42 1337})
(seq1 #{42 1337})
```

```lisb
(and (subset? :x (seq #{42 1337})) (= (card :x) 5))
(and (subset? :x (seq1 #{42 1337})) (= (card :x) 5))
```

### Injective Sequences

```lisb
(iseq #{42 1337})
(iseq1 #{42 1337})
```

### Bijective Sequences (Permutations)

```lisb
(perm #{42 1337 97})
```

## Size of Sequence

```lisb
(size (sequence 1 2 3 4))
```

## Getting Longer

```lisb
(concat (sequence 4 3 2) (sequence 6 7 8))
(prepend 0 (sequence 6 7 8))
(append (sequence 6 7 8) 0)
(conc (sequence (sequence 42 1337) (sequence 1 2) (sequence 9 9)))
```

## Reversing a Sequence
```lisb
(reverse (sequence 1 2 3))
```

## Getting Shorter
```lisb
(front (sequence 1 2 3))
(drop-last (sequence 1 2 3))
(tail (sequence 1 2 3))
(rest (sequence 1 2 3))
```

## Accessing

```lisb
(first (sequence 1 2 3))
(last (sequence 1 2 3))
```
