{:title "Vorlesung: Relationen (German)"
 :layout :page
 :page-index 101
 :navbar? false
 :section "B Material"
 :toc true}

# Relationen in B

Relationen sind ein wichtiger Bestandteil der B Sprache.
Sie bauen auf Paaren auf.
```B
:language event_b
```
## Paare

Geordnete Paare können in B mit dem "Maplet" Operator ↦ (ASCII |->) erstellt werden:
```B
{TRUE}↦1+1
```
Paare sind geordnet:
```B
1↦2 = 2↦1
```
Paare können verschachtelt werden:
```B
1↦2↦3 = (1↦2)↦3
```
## Kartesisches Produkt (Kreuzprodukt)

Wir können das Kreuzprodukt zweier Mengen als "Set Comprehension" schreiben:
```{a↦b | a:BOOL & b:1..2}``` (in klassischem B muss hier das Komma anstatt ↦ verwendet werden)
```B
{a↦b | a:BOOL & b:1..2}
```
```B
{p | ∃a,b. a:BOOL ∧ b:1..2 ∧ p=a↦b}
```
```B
 BOOL × (1..2)
```
## Relationen

Eine binäre Relation zwischen A und B ist eine Untermenge des Kreuzprodukts A × B.
Das Kreuzprodukt selber ist auch eine Relation, die jedes Element von A mit einem Element aus B in Relation setzt:
```B
:language classical_b
```
```B
:dot expr_as_graph ("rel",(1..3) × (1..3))
```
```B
:dot expr_as_graph ("rel",(1..3) × (1..3))
```
```B
:dot expr_as_graph ("rel",{1↦3,3↦2, 2↦3})
```
```B
(1..3)×(1..3)
```
```B
:table POW((1..2)×(1..2))
```
```B
:table (BOOL<->(1..2))
```
```B
{1↦3,3↦2, 3↦3} /\  id(1..3)
```
```B
:dot expr_as_graph ("rel",id(1..3))
```
```B
:language event_b
```
## Operatoren auf Relationen

In B gibt es folgende Operatoren auf Relationen:

Relational Image:
```B
{1↦3,3↦2, 3↦3} [ {3} ]
```
Das relational image kann man auch so ausdrücken:
```B
r={1↦3,3↦2, 3↦3} & img3 = {y | 3↦y : r}
```
Wenn man das Paar umdreht kann man von der Zielmenge rückwärts mögliche Eingaben bestimmen:
```B
r={1↦3,3↦2, 3↦3} & img3 = {y | y↦3 : r}
```
Bequemer geht dies mit dem relationalen Abbild und dem Umkehroperator ```~```
```B
{1↦3,3↦2, 3↦3}~[{3}]
```
Mit dom und ran kann man die Domäne und den Wertebereich einer Relation berrechnen:
```B
dom({1↦3,3↦2, 3↦4})
```
```B
ran({1↦3,3↦2, 3↦4})
```
Man kann Relationen mit dem ```;``` Operator verketten:
```B
({1↦3,3↦2, 3↦4} ; {1↦3,3↦2, 3↦44})
```
Mit dem Override Operator ```<+``` kann man eine Relation an bestimmten Stellen überschreiben:
```B
{11 ↦ TRUE, 22 ↦ FALSE,22 ↦ TRUE}  <+ {22↦TRUE,33↦FALSE}
```
Mit dem Domain Restriction Operator ```<|``` kann man eine Relation auf eine Domäne einschränken. Es gibt insgesamt vier Operatoren um Relationen einzuschränken ```<|```, ```<<|```, ```|>```, ```|>>```.
```B
 {22} <| {11 ↦ TRUE, 22 ↦ FALSE,22 ↦ TRUE, 33 ↦ FALSE} 
```
```B
{11 ↦ TRUE, 22 ↦ FALSE,22 ↦ TRUE, 33 ↦ FALSE} [{22}]
```
```B
{11} <| {11 ↦ TRUE, 22 ↦ FALSE,22 ↦ TRUE, 33 ↦ FALSE} |> {TRUE}
```
## Spezialfälle von Relationen
```B
id(2..3)
```
```B
:table BOOL <-> 1..2
```
```B
:table BOOL --> 1..2
```
```B
{11↦2800, 22↦3500}[{11}]
```
```B
({11↦2800,22↦3500}(11))
```
Mit dem Lambda Operator ```%``` kann man Funktionen erstellen:
```B
(%x.x:0..9|(x+1) mod 10)
```
Dies ist äquivalent zu:
```B
{x↦result | x:0..9 & result = (x+1) mod 10}
```
```B
:table BOOL --> 1..2
```
```B
:table BOOL -->> 1..2
```
```B
:table BOOL >-> 1..2
```
```B
:table BOOL +-> 1..2
```
```B
{f| f:BOOL +-> 1..2 & dom(f)=BOOL & ran(f)=1..2}
```
```B
{1|->22, 2|->33, 3|->22}
```
```B
{1|->22, 2|->33, 3|->22} (2)
```
```B
{1|->22, 2|->33, 3|->22}~[{22}]
```
```B
card({1|->22, 2|->33, 3|->22})
```
```B
ran({1|->22, 2|->33, 3|->22})
```
