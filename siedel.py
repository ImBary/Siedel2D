from scipy.optimize import linprog
import numpy as np

def is_unbounded(input_file):
    # Wczytanie danych z pliku
    with open(input_file, 'r') as file:
        lines = file.readlines()

    m = int(lines[0].strip())  # Liczba ograniczeń
    constraints = []

    #mapujemy warunki
    for i in range(1, m + 1):
        a, b, c = map(float, lines[i].strip().split())
        constraints.append((a, b, c))

    #zgarniamy ax + bx <= c gdzie b<0, bo ograniczaja wzorst y
    limiting_constraints = [constraint for constraint in constraints if constraint[1] < 0]

    #jak nie ma ograniczeń b<0 to y moze rosnac w nieskończoność
    if not limiting_constraints:
        return "TAK"

    
    A = []#a,b
    b = []#c
    for a, b_coef, c in limiting_constraints:
        A.append([a, b_coef])
        b.append(c)

    A = np.array(A)#lp potrzebuje numpy.array
    b = np.array(b)

    # Sprawdzenie, czy istnieje punkt spełniający wszystkie ograniczenia jednocześnie
    # "c" współczynnik f.celu [0,1] bo maks y
    # bounds[none,none] zmienne x i y moga przyjmowac dowolne wartosci 
    # highs metoda rozwiazywania lp
    result = linprog(c=[0, 1], A_ub=A, b_ub=b, bounds=(None, None), method='highs')

    # Jeśli istnieje wspólne rozwiązanie, to program liniowy jest ograniczony
    if result.success:
        return "NIE"
    else:
        return "TAK"

# Test funkcji
input_file = 'input.txt'
print("CZY OGRANICZONY")
print(is_unbounded(input_file))
