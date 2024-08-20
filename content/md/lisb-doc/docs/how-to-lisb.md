{:title "lisb Tutorial"
 :layout :page
 :page-index 0}


## Important Disclaimer

What was shown in snippets prefixed by `lisb=>` is not accurate.
These were given in order to explain the *semantics* of the language.
This was a lie for the sake of didactics.

In reality, lisb formulas generate an *intermediate representation* (IR)
that is just nested data.
Below, we will show the *real* Clojure calls that happen.


```clj
(b (+ 1 2))
(b (= :x (+ 42)))
```

## IR Structure

As you can see, operators in the IR are nested maps.
All maps share the key `:tag`, under which the operator is associated.
The other keys then depend on the operators and for what types it is defined.

Logical variables, which are keyword in the internal DSL, are keywords in the IR as well.
Same goes for numbers, strings and sets.

## TODO: Tuples, Sequences, Records and Structs


# lisb Interface

The `lisb.translations.util` namespace offers:

- All relevant symbols from `lisb.translation.lisb2ir`.
  These are the functions that generate the IR as well as the `b` macro.
- Functions to switch between representations, i.e.,
  the internal DSL, the IR, ProB's AST and plain B strings.

## Changing Representations


### From B Strings to the lisb DSL

Unfortunately, the B parser has several entry points depending on the B type of the string.

```clj
(use 'lisb.translation.util)
(b-expression->lisb "1 + 2")
(b-predicate->lisb "1 = 2")
```

The following functions also exist:

- `b->lisb` for entire machines
- `b-formula->lisb` should work on *both* predicates and expressions (unverified, judging from the parser's grammar)
- `b-substitution->lisb` for a single substitution
- `b-operation->lisb` for an entire operation (potentially containing several substitutions)
- `b-machine-clause->lisb` for any B machine clause (e.g., `VARIABLES`, `INVARIANT` but also `OPERATIONS` which may contain several operations)


### From the lisb DSL to the IR

```clj
(lisb->ir '(+ 1 2))
```

### From the IR to ProB's AST

```clj
(ir->ast {:tag :add, :nums [1 2]})
```

### From the AST to B

```clj
(ast->b (ir->ast {:tag :add, :nums [1 2]}))
```

### From the AST to the lisb DSL

```clj
(ast->lisb (ir->ast {:tag :add, :nums [1 2]}))
```

### From X to Y

The util namespace also provides the composition of the functions above.
Their naming scheme is `X->Y`, where `X` may be a string representation (`X` = `b` or `b-expression` or `b-predicate` or ..., see above),
`lisb`, `ir` or `ast`. `Y` can be `lisb`, `ir`, `ast` or `b`.


```clj
(lisb->ast '(+ 1 2))
(lisb->b '(+ 1 2))
(b-expression->ir "1 + 2")
```

## Evaluation

lisb gets much more useful as we can evaluate formulas using ProB.

```clj
(use 'lisb.core)
(eval-ir-formula' (b (+ 1 2)))
```

If the formula is an expression, we get the value it evaluates to.
There might also be open variables in predicates.
Then, we get a map from variables to values that make a solution that satisfies the formula.

```clj
(eval-ir-formula' (b (and (= :x 42) (< :x :y))))
```

