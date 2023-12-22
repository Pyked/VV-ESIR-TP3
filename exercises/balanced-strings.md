# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer
    Partitionnement de l'espace d'entrée :

        Caractères valides : Les tests utilisent uniquement les caractères suivants : '{', '}', '[', ']', '(', ')'.

        Présence d'autres caractères : Les tests couvrent des cas où d'autres caractères sont présents dans la chaîne.

        Position des symboles : Certains tests, comme "testSymboleFermetureRedondant" et "testSymboleFermetureNonApparie", vérifient la position des symboles.

    Évaluation de la couverture de déclaration :

        Cas 1 : testChaineVide : La chaîne est vide. Le test couvre une chaîne vide, devrait renvoyer true.

        Cas 2 et 3 : testSymbolesEquilibres et testSymbolesDesequilibres : Ces tests couvrent des chaînes avec des symboles équilibrés et déséquilibrés, respectivement. Ils devraient renvoyer les résultats attendus.

        Cas 4 : testAutresCaracteres : Vérifie que d'autres caractères, tels que des lettres et des chiffres, n'affectent pas l'équilibre. Devrait renvoyer true.

        Cas 5 et 6 : testSymboleFermetureRedondant et testSymboleFermetureNonApparie : Ces tests vérifient les cas où les symboles de fermeture sont redondants ou non appariés. Devraient renvoyer false.

    Évaluation de la couverture des prédicats et Base Choice Coverage :
        Aucune évaluation n'est nécessaire ici car le code ne semble pas contenir de prédicats complexes avec plus de deux opérateurs booléens.

        
Implémentation:

```java
public static boolean isBalanced(String str) {
    int balance_indicator = 0;  // Initialize balance_indicator to 0

    for (int i = 0; i < str.length(); i++) {
        char currentChar = str.charAt(i);

        if (currentChar == '{') {
            balance_indicator += 1;
        } else if (currentChar == '[') {
            balance_indicator += 2;
        } else if (currentChar == '(') {
            balance_indicator += 3;
        } else if (currentChar == '}') {
            balance_indicator -= 1;
        } else if (currentChar == ']') {
            balance_indicator -= 2;
        } else if (currentChar == ')') {
            balance_indicator -= 3;
        }

        // Check if the balance_indicator goes negative at any point
        if (balance_indicator < 0) {
            return false;
        }
    }

    // Check if the balance_indicator is zero after processing the entire string
    return balance_indicator == 0;
}
```

