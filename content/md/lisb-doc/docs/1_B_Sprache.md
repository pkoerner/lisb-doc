{:title "Vorlesung: B Sprache (German)"
 :layout :page
 :page-index 100
 :navbar? false
 :section "B Material"
 :toc true}

# Vorlesung: B Sprache
## Struktur von Event-B Modellen

Ein Event-B System besteht aus
* statischen Teilen: die Kontexte
- definieren Konstanten, Basismengen, Axiome, Theoreme
* dynamischen Teilen: Maschinen
- definieren Variablen, Ereignisse, Invarianten

* Kontexte können von Maschinen gesehen werden
* Kontexte und Maschinen können verfeinert werden
## Syntaktische Klassen
Es gibt drei Arten von Formeln in B:
- **Ausdrücke** (Expressions): haben einen Wert, verändern den Zustand einer B Maschine *nicht*
- **Prädikate** (Predicates): sind wahr oder falsch, haben *keinen* Wert und verändern den Zustand der Maschine *nicht*
- **Anweisungen** (Substitutions, Statements): haben keinen Wert, können aber den Zustand einer Maschine verändern

Anweisungen sind zB `x := x+1` und können nur in Maschinen, nicht in Kontexten verwendet werden.

Dies ist ein Ausdruck in B:
```B
2+3
```
Dies ist ein Prädikat in B:
```B
2>3
```
B unterscheidet streng zwischen den Bool'schen Werten TRUE und FALSE und zwischen Prädikaten die wahr und falsch sind. Folgendes ist ein valider Ausdruck in B und beschreibt die Menge bestehend aus dem Bool'schen Wert `FALSE`:
```B
{FALSE}
```
Folgendes ist **kein** valider Ausdruck in B:
```B
{bool(2>3)}
```
## Syntax

Die meisten Operatoren in B können entweder als ASCII Zeichenkette oder als Unicode Symbol eingegeben werden.
Hier verwenden wir zum Beispiel die Unicode Version von ```/``` für die Division:
```B
10 ÷ 2
```

Die Syntax von Event-B und klassischem B unterscheidet sich leicht.
In Rodin muss man Event-B Syntax verwenden; Jupyter verwendet standardmäßig klassisches B.

Zum Beispiel benutzt Rodin `^` zum Potenzieren, während man im klassische B `**` verwendet:
```B
2**100
```
In Rodin wird `**` für das kartesische Produkt verwendet, im klassischen B verwendet man dafür `*`.
Wir laden jetzt ein leeres Event-B Modell um den Parser in den Event-B Modus zu wechseln.
Man kann auch das Kommando ```:language event_b``` verwenden um den Parser umzustellen.
Mit ```:language classical_b``` kann man zurück zum normalen B Parser wechseln.
Wenn man eine B Maschine oder einen Kontext lädt wechselt der Parser in den richtigen Modus.
Hier laden wir einen leeren Event-B Kontext; dadurch wechselt der Parser in den Event-B Modus:
```B
:load models/empty_ctx.eventb
```
```B
2^100
```
## Semantische Grundlage von B

* Arithmetik (ganze Zahlen)  ÷   +   −   ≤
* Prädikatenlogik  ⇒   ∀
* Mengentheorie  ∩   ∪   ⊆   ∈
* Paare
* Relationen,Funktionen, Sequenzen/Folgen, Bäume
## Aussagenlogik

B unterstützt folgende Junktoren der Aussagenlogik:

Junktor | ASCII | Unicode
-------|-------|-----
Konjunktion | & | ∧
Disjunktion | or | ∨
Negation | not | ¬
Implikation | => | ⇒
Äquivalenz | <=> | ⇔

Mit `:prettyprint` kann man sich die Unicode Version einer Formel ausgeben lassen:
```B
:prettyprint not(2>3 or 4>2) <=> 5>2
```
Man kann eine Aussage natürlich auch auswerten:
```B
not(2>3 or 4>2) ⇔ 5>2
```
```B
2>3 or 4>2
```
```B
not(2>3 or 4>2)
```
```B
5>2
```
### Prioritäten

* höchste Priorität: ¬
* danach ∧ , ∨
* danach ⇒ , ⇔

```B
2>0 or 3>4 ⇒ 4>5
```
```B
2>0 or (3>4 ⇒ 4>5)
```
```B
(2>0 or 3>4) ⇒ 4>5
```
```B
(1=1 => 2=2) => 3=1
```
*Achtung:* 
* in Rodin/Event-B haben Konjunktion und Disjunktion die gleiche Priorität und dürfen nicht ohne Klammerung gemischt werden. In klassischem B schon.
* Das gleiche gilt für => und <=>.
* In Rodin ist auch keine Assoziativität für => oder <=> definiert worden. Man darf diese Operatoren also auch nicht untereinander ohne Klammern mischen.
```B
2>1 or 2>3 & 3>4
```
## Prädikate

In Event-B gibt es die Aussagen true (⊤) und false (⊥)  (nicht verwechseln mit den Bool'schen Datenwerten TRUE und FALSE). Im klassichen B gibt es diese beiden Aussagen nicht (wobei ProB `btrue` und `bfalse` akzeptiert).


```B
true or false
```
```B
⊤ or ⊥
```

Wahrheitswerte können über Prädikate hergestellt werden:
* ein n-äres Pradikat bekommt n Datenwerte und bildet diese auf einen Wahrheitswert ab

Für alle Datentypen gibt es die binären Pradikate = und ≠ (wobei gilt x ≠ y ⇔ ¬(x=y)).
Für Zahlen gibt es die mathematischen Vergleichsoperatoren. Für Mengen werden noch weitere Prädikate hinzukommen.
```B
2 = 3-1
```
```B
3 ≠ 3+1
```
```B
4 >= 2+2
```
## Quantoren

In B gibt es zwei Quantoren die neue Variablen einführen:
* den Existenzquantor (mindestens eins)
 - #x.(P) als ASCII
 - ∃x.(P) als Unicode
* den Allquantor/Universalquantor (alle / jeder)
 - !x.(P => Q) als ASCII
 - ∀(x).(P ⇒ Q) als Unicode
 
 Der Rumpf eines Allquantors muss also immer eine Implikation auf oberster Ebene verwenden.
```B
∃x .(  x>2 )
```
```B
∀x .( x>2 ⇒ x>3 )
```
```B
∀x .( x>2 & x < 100 ⇒  ∃y.(x=y+y))
```
```B
∀x .( x>2 & x < 100 ⇒  ∃y.(x=y+y) or ∃y.(x=y+y+1))
```
### Typisierung bei Quantoren

B basiert auf typisierter Logik
* Bei Quantoren, zum Beispiel, müssen die neuen Variablen typisiert werden
 - ∃x.(2>3)  ist nicht erlaubt

Generell: für   ∃x.(P)  und  ∀(x).(P ⇒ Q)  muss P den Typen von x bestimmbar machen.
Deshalb ist der Rumpf eines Allquantors auch immer eine Implikation.
 Beim Existenzquantor wird oft eine Konjunktion verwendet.
 Warum macht eine Implikation beim Existenzquantor fast nie Sinn?
```B
∃x.(x>-100000 => 22=1)
```
## Arithmetik

B bietet Arithmetik über *ganzen* Zahlen an.

Folgende Zahlenmengen sind vordefiniert:
* INT, ℤ
* NAT = {x|x≥0}, ℕ
* NAT1= {x|x≥1}, ℕ1

In Atelier-B (klassisches B für die Softwareentwicklung, nicht in Rodin) wird zwischen mathematischen und implementierbaren Zahlen unterschieden:
* Mathematische ganze Zahlen: INTEGER, NATURAL, NATURAL1
* Implementierbare ganze Zahlen: INT, NAT, NAT1
* MININT, MAXINT

NATURAL aus dem klassischem B entspricht also NAT in Rodin.
NAT aus dem klassischen B entspricht 0..MAXINT in Rodin, wobei MAXINT als Konstante definiert werden muss.

```B
0:ℕ
```
```B
0:NAT1
```
### Divsion

B benutzt ganzzahlige Division und es gilt zB das Gesetz
*  b≠0 ⇒ a ÷ b = (−a) ÷ (−b)
Man hat auch:
```B
3/2
```
```B
-3 / 2
```
In Python bekommt man
```
>>> (-3)//2
-2
```
Division durch 0 und modulo durch negative Zahlen ist nicht definiert.
```B
100 mod -2
```
## Mengen

Mengen sind ein fundamentales Konzept in B.
In eine Mengen darf man nur Werten eines gleichen Typs packen.
Mengen dürfen aber verschachtelt werden.

Die einfachste Menge ist die leere Menge:
```B
{}
```
Man kann Mengen durch **explizite Aufzählung** (Enumeration, set extension auf Englisch) definieren:
```B
{1,1+1,2,2+2,3,4}
```
Mengen können auch mit Hilfe eines Prädikats definiert werden (set comprehension auf Englisch). Die Syntaxform ist `{x | P}`.
```B
{x | x>0 & x<5}
```
```B
{x|x>2+2}
```
Eine Menge an Zahlen kann auch mit der Intervallnotation ``a..b`` definiert werden:
```B
1..4
```
Die Kardinalität (Mächtigkeit) einer endlichen Menge kann mit dem card Operator bestimmt werden:
```B
card({1,1+1,2,2+2,3,4})
```
```B
card({x|x>2})
```
Mengen können andere Mengen beinhalten:
```B
{ 1..4 , {1,2,3,4,2+2}, {}}
```
```B
card({ 1..4 , {1,2,3,4,2+2}, {}})
```
Was ist der Untschied zwischen der leeren Menge ∅ und { ∅ }?
```B
∅ = {∅}
```
```B
card(∅)
```
```B
card({∅})
```
```B
∅ : {∅}
```
Die Notation `{x | P}` wobei eine Menge per Prädikat definiert wird ist die mächtigste Notation.
```B
{x | x>1 & x<1  }
```
```B
 {x | x=1 or x=2 }
```
```B
{x | x>=1 & x<=4 }
```
```B
NAT = {x | x>=0 }
```
In Event-B gibt es auch eine angepasste Variante der Notation `{x . P | E}`, wo man einen Ausdruck `E` angibt der in die generierte Menge eingefügt wird:
```B
{x.x:1..10|x*x}
```
## Prädikate über Mengen

Es gibt die Standardprädikate der Mathematik:
* ∈
* ∉
* ⊆
* ⊂

```B
:prettyprint not({1,2,3} /<<: {1,2,3})
```
Zusätzlich hat Event-B das partition Prädikat, welches wir weiter unter erklären.
## Operatoren über Mengen

* Vereinigung         \/   ∪
* Schnittmenge      /\   ∩
* Differenzmenge    \
* union(S), inter(S) für Menge von Mengen
* POW(S) (Potenzmenge: Untermengen von S)
* POW1(S) (nicht leeren Untermengen von S)

Übung: diese Operatoren durch comprehension sets definieren
```B
a = {1,2,4} & b = {2,3,4} & aub1 = a \/ b & aub2 = {x | x:a or x:b}
a = {1,2,4} & b = {2,3,4} & aib1 = a/\b & aib2 = {x | x:a & x:b}
a = {1,2,4} & b = {2,3,4} & aib1 = a \ b & aib2 = {x | x:a & x/:b}
{x.x:1..10|x*x}
a = {1,2,4} & b = {2,3,4} & c = {4,6} & abc = union({a,b,c})
POW(1..2)
POW(BOOL)
POW(POW(BOOL))
card(POW(POW(POW(BOOL))))
```
## Übung: Send More Money

Man soll 8 Ziffern finden so dass folgende Summe aufgeht

|   |   |   |   |   |   |
|---|---|---|---|---|---|
|   |   | S | E | N | D |
|   | + | M | O | R | E |
| = | M | O | N | E | Y |

Wir brauchen acht Ziffern:
```B
{S,E,N,D,M,O,R,Y}<:0..9
```
Diese Ziffern sollen alle unterschiedlich sein:
```B
{S,E,N,D,M,O,R,Y}⊆0..9 & card({S,E,N,D,M,O,R,Y}) = 8
```
Die Ziffern S und M dürfen nicht 0 sein:
```B
{S,E,N,D,M,O,R,Y}⊆0..9 & card({S,E,N,D,M,O,R,Y}) = 8 & S /= 0 & M /= 0
```
Und jetzt muss noch die Summe stimmen:
```B
{S,E,N,D,M,O,R,Y}<:0..9 & card({S,E,N,D,M,O,R,Y}) = 8 & S /= 0 & M /= 0 & S*1000 + E*100 + N*10 + D + M*1000 + O*100 + R*10 + E = M*10000 + O*1000 + N*100 + E*10 + Y
```
Wir können auch die Menge aller Lösungen bestimmen:
```B
{S|->E|->N|->D|->M|->O|->R|->Y | {S,E,N,D,M,O,R,Y}⊆0..9 & card({S,E,N,D,M,O,R,Y}) = 8 & S /= 0 & M /= 0 & S*1000 + E*100 + N*10 + D + M*1000 + O*100 + R*10 + E = M*10000 + O*1000 + N*100 + E*10 + Y }
```
## Das Partition Prädikat

Dieses Prädikat gibt es nur in Event-B und es ist syntaktischer Zucker für einen grösseren äquivalenten Ausdruck:
```
 partition(S,S1,S2,...,Sn)
 ```
 steht für
 ```
   S = S1 \/ S2 \/ ... Sn &
   S1 /\ S2 = {} & ... S1 /\ Sn = {} &
   S2 /\ S3 = {} & ... S2 /\ Sn = {}
   ...
   Sn-1 /\ Sn = {}
   ```
```B
:language event_b
```
```B
partition(1..3,{1},{2},{3})
```
```B
:table {a|->b| partition(1..2,a,b)}
```
## Neue Basismengen

Vom Benutzer können in Event-B neue Basismengen eingeführt werden

* werden in der `sets` Sektion von Kontexten eingeführt
* jede Benutzermenge ist sein eigener Typ!

Man darf unterschiedliche Mengen bzw. Elemente aus unterschiedlichen Basismengen nicht mischen.

Es gibt zwei Möglichkeiten:
1) Enumerated Set: man gibt explizit alle Elemente der neuen Basismenge an
S = {a,b,c} in klassischem B oder partition(S,{a},{b},{c}).
2) Deferred Set: man lässt die Menge für Erweiterungen offen

In Rodin gibt es zwei Wizards zum einführen von enumerated bzw. deferred "carrier" sets.
## Typen in B

* es gibt einen Typ für alle Zahlen: INT (ℤ)
* es gibt den Typ BOOL für die Bool'schen Werte
* für jede Basismenge gibt es einen eigenen Typen
* ist t ein Typ, dann ist POW(t) auch ein Typ
* nächste Woche werden wir noch ein weitere Typkonstruktion sehen

Jeder Ausdruck und damit auch jede Variable muss genau einen Typen haben. Die Typen müssen mit den Signaturen der B Operatoren kompatibel sein.

* man kann explizit Typen angeben, mit einem Prädikat `VARIABLE ∈ TYP`, zum Beispiel: `a∈ℤ`
* oder auf die Typ-Inferenz von Atelier-B, Rodin, ProB hoffen (Atelier-B am schwächsten; ProB am mächtigsten)

Beispiele:
```B
:type 1
```
```B
1 ∈ ℤ
```
```B
:type {1}
```
```B
{1} ∈ POW(INT)
```
```B
NAT : POW(INT)
```
```B
:type {}
```
Are the following two expressions well-typed? What are the type inference rules?
```B
1+card({TRUE})
```
```B
{TRUE} ∪ {1}
```
### Inferenzregeln für Typen

Folgende Regeln sind relevant um die Typen der beiden Ausdrücke oben zu prüfen:

* type(1) = INTEGER
* type(TRUE) = BOOL
* type({ x }) = POW(XT)  where type(x) = XT
* type(a+b) = INTEGER if type(a)=type(b)=INTEGER
* type(a ∪ b) = POW(T)  if type(a) = type(b) = POW(T)


# Kleine Beispiele
```B
1+x=3
```
Wenn man die Menge der Primzahlen so definiert, behält ProB diese als symbolische Menge:
```B
Primzahlen = {x|x>1 ∧ ∀y.(y∈2..(x-1) ⇒ x mod y > 0)}
```
Man kann aber zum Beispiel eine Primzahl generieren:
```B
x∈Primzahlen ∧ Primzahlen = {x|x>1 ∧ ∀y.(y∈2..(x-1) ⇒ x mod y > 0)}
```
Man kann die Menge der Primzahlen auch mit 1..100 schneiden um die Primzahlen bis 100 zu bekommen:
```B
Primzahlen = {x|x>1 ∧ ∀y.(y∈2..(x-1) ⇒ x mod y > 0)} ∧ res = Primzahlen ∩ 1..100
```
Hier rechnen wir jetzt die Anzahl der Primzahle bis 1000 aus:
```B
Primzahlen = {x|x>1 ∧ ∀y.(y∈2..(x-1) ⇒ x mod y > 0)} ∧ res = card(Primzahlen ∩ 1..1000)
```
