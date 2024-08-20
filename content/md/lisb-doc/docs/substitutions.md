{:title "Substitutions"
 :layout :page
 :page-index 50
 :navbar? false
 :section "B Syntax Overview"
 :toc true}

Substitutions change the program state.
As they do not have a value, we cannot execute them.
Instead, we provide some examples and give the B representation instead.

## Empty Assignment

```pplisb
skip
```

## Assignments

```pplisb
(assign :x 42)
(assign :x 42 :y 1337)
```

```pplisb
(becomes-element-of [:x] nat-set))
(becomes-member [:x :y] (cartesian-product nat-set (range 1 10)))
```

```pplisb
(becomes-such [:x :y] (= :x (inc :y)))
```

```pplisb
(<-- [:x :y] (op-call :foo :a 42))
```

## Composition

```pplisb
(|| (assign :x 42) (assign :y 43) (assign :z 1337))
(parallel-sub (assign :x 42) (assign :y 43) (assign :z 1337))
(sequential-sub (assign :x 42) (assign :y 43) (assign :z 1337))
```

## Choices, Bindings, ...

```pplisb
(any [:x :y] (< min-int :x :y max-int) (assign :a :x :b :y))
(let-sub [:x 42 :y 1337] (assign :a :x :y 1337))
(var-sub [:x] (assign :x 42) (assign :a :x))
(pre (= :x 42) (assign :y 42))
(assert (= :x 42) (assign :y 42))
(choice (assign :x 42) (assign :y 42))
(if-sub (= :x 0) (assign :x 100))
(if-sub (= :x 0) (assign :x 100) (assign :x (dec :x)))
(cond (= :x 0) (assign :x 1) (= :y 0) (assign :y 1))
(cond (= :x 0) (assign :x 1) (= :y 0) (assign :y 1) (assign :v "default"))
(select (= :x 0) (assign :x 1) (= :y 0) (assign :y 1))
(select (= :x 0) (assign :x 1) (= :y 0) (assign :y 1) (assign :v "default"))
(case :x 0 (assign :x 1) 1 (assign :y 1))
(case :x 0 (assign :x 1) 1 (assign :y 1) (assign :v "default"))
```
