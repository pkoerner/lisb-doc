{:title "B/lisb tutorial"
 :layout :post
 :toc true
 :tags  ["B language","tutorial"]}



# Literals

##  Scalar Values 

In B, there are only three kinds of scalar values: booleans, numbers and strings.

```lisb
42
3.14
true
false
"foo" 
```

## Compound Values

We have ... compound types: sets, tuples (or maplets) sequences (which are a special case of sets), and records.

### Sets
Sets are simply an (unordered) collection of items (from the same type).

```lisb
#{1 2 3}
```

### Tuples / Maplets

```lisb
(b (maplet 1 2))
[1 2] ;; not supported
```

### Sequences

```lisb
(b (sequence 1 2 3))
```

### Structs and Records
```lisb
(b (struct :x nat-set, :y int-set))
(b (record :x 42, :y 43))
```
                    
## Constants
Small number sets, size controlled by ProB's MININT and MAXINT constants:

```lisb
nat-set
nat1-set
int-set
```

Sets of numbers that have an infinite size (and thus cannot be calculated entirely):

```lisb
natural-set
natural1-set
integer-set
```

The set of all strings:

```lisb
string-set
```
# Arithmetics
Standard arithmetic operators on numbers are defined:

```lisb
(b (+ 1 2))
(b (- 1))
(b (- 1 2))
(b (* 1 2))
(b (/ 5 2))
```

lisb arithmetic operators take a variadic number of arguments 

```lisb
(b (+ 1))
(b (+ 1 2 3 4))
(b (- 1 2 3 4))
(b (* 3 4 5))
(b (/ 100 2 2 2))
```

# Set Operators

```lisb
(b (union #{1 2} #{1 3}))
(b (set- #{1 2 3} #{3 4}))
```

