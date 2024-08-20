{:title "Predicates"
 :layout :page
 :page-index 1
 :navbar? false
 :section "B Syntax Overview"
 :toc true}

## Equality

```lisb
(= 1 1)
(not= 1 1)
```

## Logical Connectives

### Negation

```lisb
(not (= 1 2))
```

### Binary operators

```lisb
(and (= 1 1) (= 2 2))
(or (= 1 2) (= 2 2))
```

The implication and equivalence have an alias each:

```lisb
(=> (= 1 2) (= 1 1))
(implication (= 1 2) (= 1 1))
(<=> (= 1 2) (= 1 1))
(equivalence (= 1 2) (= 1 1))
```

### Quantifiers

In B, the universal quantification requires an implication in the body.
In lisb, you can keep this style, or just use two arguments for the premise and the conclusion.
It is automatically re-written.

```lisb
(for-all [:x] (=> (member? :x nat1-set) (< 0 :x)))
(for-all [:x] (member? :x nat1-set) (< 0 :x))
(exists [:x] (< :x 10))
(exists [:x :y] (< :x 10 :y))
```

## Conversion to Boolean

```lisb
(pred->bool (= 1 1))
(pred->bool (= 1 2))
```
