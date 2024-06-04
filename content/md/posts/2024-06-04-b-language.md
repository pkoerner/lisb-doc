{:title "Vorlesung: B Sprache"
 :layout :post
 :toc true
 :tags  ["B language","tutorial"]}


# Struktur von Event-B Modellen

Ein Event-B System besteht aus 

- statischen Teilen: die Kontexte definieren
  - Konstanten,
  - Basismengen, 
  - Axiome, 
  - Theoreme

- dynamischen Teilen: Maschinen definieren 
  - Variablen, 
  - Ereignisse, 
  - Invarianten

Kontexte können von Maschinen gesehen werden. Kontexte und Maschinen können verfeinert werden.

# Syntaktische Klassen

Es gibt drei Arten von Formeln in B:

- Ausdrücke (Expressions): haben einen Wert, verändern den Zustand einer B Maschine nicht
- Prädikate (Predicates): sind wahr oder falsch, haben keinen Wert und verändern den Zustand der Maschine nicht
- Anweisungen (Substitutions, Statements): haben keinen Wert, können aber den Zustand einer Maschine verändern Anweisungen sind z.B. `x := x+1` und können nur in Maschinen, nicht in Kontexten verwendet werden. 

Dies ist ein Ausdruck in B:

```lisb
(+ 2 3)
```

Dies ist ein Prädikat in B:

```lisb
(> 2 3)
```

B unterscheidet streng zwischen den Bool'schen Werten TRUE und FALSE und zwischen Prädikaten die wahr und falsch sind.
Folgendes ist ein valider Ausdruck in B und beschreibt die Menge bestehend aus dem Bool'schen Wert FALSE:

```lisb
#{false}
```

Folgendes ist kein valider Ausdruck in B:

```lisb
#{(pred->bool (> 2 3))}
```


# Syntax

Die meisten Operatoren in B können entweder als ASCII Zeichenkette oder als Unicode Symbol eingegeben werden.
Hier verwenden wir zum Beispiel die Unicode Version von / für die Division:

```b-expr
10 ÷ 2
```

