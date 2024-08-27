{:title "Improving Control Over Evaluation"
 :layout :post
 :tags  ["lisb" "0.0.6" "preview" "feature"]}

The next version (and current snapshot) features a new function to evaluate formulas.
In the same turn, I will deprecate the old `eval-ir-formula` fn.

## Rationale

The main idea is that lisb should offer more control about the results.
ProB's underlying internals look like this:

The Java API returns a formula string.
It also offers a value translator to directly obtain Java objects that represents values,
such as sets, strings or numbers.

However, this value translator quickly comes to its limit once ProB keeps sets symbolic.
This can happen if it is "hard" to calculate all elements or impossible because it is infinite.
Yet, we also want to represent these values in some way, and maybe re-use them in further calculations
or even transform them anyway!

Thus, I want to offer the following representations of results:

- a textual B representation (aka pretty print which you might prefer if you like infix operators)
- an intermediate representation of resulting formulas (which we prefer for transformations)
- a DSL representations of resulting formulas (which we prefer for iteratively building a model)
- a "preview" of the set (which we prefer for the rEPL)

## Usage

Loading stuff (`lisb.core` for the evaluation function, `lisb.translation.util` for easy access to the B macro and symbols):

```clj
(use 'lisb.core)
(use 'lisb.translation.util)
```

Let us try evaluating a formula, like what is the set of natural numbers:


```clj
(eval-ir-formula' (b natural-set)
                  {:val-output :bstr})
```

This gives us the formula string from ProB (even the unicode representation!).
We can probably read this nicely, but plugging the solution into another formula will be a pain.


```clj
(eval-ir-formula' (b natural-set)
                  {:val-output :lisb})
```

This yields a DSL representation (in this case, exactly what we put in).
Of course, this will be more useful if the formula is more complex.

```clj
(eval-ir-formula' (b natural-set)
                  {:val-output :ir})
```

This is nice if you want to build another constraint when the IR representation is required.

But, finally, the main useful feature for the REPL:

```clj
(eval-ir-formula' (b natural-set)
                  {:val-output :value})
```

This shows us some elements of the set (by default, 10).
We can fine-tune this if we want more (or less):

```clj
(eval-ir-formula' (b natural-set)
                  {:val-output :value
                   :val-aggression 50})
```

But what if we do not know how many elements we need?
We can just obtain a lazy sequence of all of them!

```clj
(take 15 (eval-ir-formula' (b natural-set)
                           {:val-output :value
                            :val-aggression :lazy}))
```

Be careful to actually only take the number of elements you need or to set the `*print-length*` binding.

## Notes

- It would probably be nice to print a `...` if the set might have more elements than the (default) `:val-aggression` value.
- Increasing the `:val-aggression` or lazily obtaining more elements does not scale linearly.
  This has to do with ProB's internas and that for each additional element, a new query is started excluding all elements that have already been seen.
  Using an external function, it is possible to retrieve a fixed number of elements more efficiently.
- You can pass a `:state-space` as part of the option map if you want to evaluate a formula in the context of a loaded model.
- The option map is open for extensions. In the future, I want to implement the option to override function how B types are converted to Clojure data.
  For example, maybe you want to use vectors for tuples or for sequences.
  Maybe you want to retain the sequence mapping (e.g., `1->"foo", 2->"bar"`) or are only interested in the values.
  This is not implemented yet.
