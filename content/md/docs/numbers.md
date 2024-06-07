{:title "Numbers"
 :layout :page
 :page-index 5
 :navbar? false
 :section "B Syntax Overview"
 :toc true}

## Literals

```lisb
42
```

Hexadecimal

```b-expr
0xdeadbeef
```

## Implementable Integer Bounds

```lisb
min-int
max-int
```

## Set Constants

```lisb
int-set
nat-set
nat1-set
```

```lisb
integer-set
natural-set
natural1-set
```


## Interval

```lisb
(interval 1 10)
```

## Comparison

```lisb
(< 1 2)
(<= 1 2)
(> 42 0)
(>= 42 0)
```

## Arithmetics

```lisb
(+ 1 2)
(+ 1 2 3 4)
(- 1 2)
(- 1 2 3 4)
(* 1 2)
(* 1 2 3 4)
(/ 5 2)
(/ 100 2 5)
(** 2 5) 
(** 2 2 5) 
(mod 5 2)
```

## Minimum / Maximum of Set

```lisb
(min #{1 2 3})
(max #{1 2 3})
```

## Successor / Predecessor

```lisb
(predecessor 42)
(successor 42)
(dec 42)
(inc 42)
```


## Sum / Product over Set

```lisb
(sigma [:x] (member? :x (range 1 10)) (* :x :x))
(pi [:x] (member? :x (range 1 10)) (* :x :x))
```

