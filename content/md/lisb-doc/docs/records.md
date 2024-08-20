{:title "Records"
 :layout :page
 :page-index 30
 :navbar? false
 :section "B Syntax Overview"
 :toc true}

## Records

```b-expr
struct(foo:NAT, bar:NAT)
rec(foo:1, bar:2)
rec(foo:1, bar:2)'foo
```

```lisb
(struct :foo nat-set, :bar nat-set)
(record :foo 1, :bar 2)
(record-get (record :foo 1, :bar 2) :foo)
```

