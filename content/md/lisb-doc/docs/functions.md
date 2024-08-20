{:title "Functions"
 :layout :page
 :page-index 20
 :navbar? false
 :section "B Syntax Overview"
 :toc true}

Functions are special relations, that map an element from its domain to at most one element of its range.


## Sets of Functions

```lisb
(+-> #{1 2} #{3 4})
(partial-function #{1 2} #{3 4})
```

```lisb
(--> #{1 2} #{3 4})
(total-function #{1 2} #{3 4})
```

```lisb
(+->> #{1 2} #{3 4})
(partial-surjection #{1 2} #{3 4})
```

```lisb
(-->> #{1 2} #{3 4})
(total-surjection #{1 2} #{3 4})
```

```lisb
(>+> #{1 2} #{3 4})
(partial-injection #{1 2} #{3 4})
```

```lisb
(>-> #{1 2} #{3 4})
(total-injection #{1 2} #{3 4})
```

```lisb
(>+>> #{1 2} #{3 4})
(partial-bijection #{1 2} #{3 4})
```

```lisb
(>->> #{1 2} #{3 4})
(total-bijection #{1 2} #{3 4})
```

## Lambda Expression

```lisb
(lambda [:x] (member? :x #{1 2}) (inc :x))
```

## Function Call

```lisb
(fn-call #{(|-> 1 2) (|-> 2 3)} 1)
(fn-call #{(|-> (|-> 1 2) 3)} 1 2)
```

Note that the association of the maplets is important:

```lisb
(fn-call #{(|-> 1 (|-> 2 3))} 1 2)
```

