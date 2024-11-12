# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny. 

## Procesy biznesowe

---
<a id="bc1"></a>
### BC1: Sprzedaż aukcyjna

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego.

**Scenariusz główny:**
1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([UC2](#uc2), [BR1](#br1))
3. [Kupujący](#ac2) wygrywa aukcję ([BR2](#br2), [UC3](#uc3))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu. ([UC4](#uc4))
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu. ([UC5](#uc5))

**Scenariusze alternatywne:**
- 2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.
  * 2.A.1. Przejdź do kroku 2.

- 3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))
  * 3.A.1. Koniec przypadku użycia.

---

## Aktorzy

<a id="ac1"></a>
### AC1: Sprzedający

Osoba oferująca towar na aukcji.

<a id="ac2"></a>
### AC2: Kupujący

Osoba chcąca zakupić produkt na aukcji.

## Przypadki użycia poziomu użytkownika

### Aktorzy i ich cele

[Sprzedający](#ac1):
* [UC1](#uc1): Wystawienie produktu na aukcję
* [UC5](#uc5): Przekazanie produktu Kupującemu

[Kupujący](#ac2):
* [UC2](#uc2): Złożenie oferty zakupu
* [UC3](#uc3): Wygranie aukcji
* [UC4](#uc4): Przekazanie płatności Sprzedającemu

---

<a id="uc1"></a>
### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:** 

4.A. Podano niepoprawne lub niekompletne dane produktu.
* 4.A.1. System informuje o błędnie podanych danych.
* 4.A.2. Przejdź do kroku 2.

---

<a id="uc2"></a>
### UC2: Złożenie oferty zakupu

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) wybiera produkt, na który chce złożyć ofertę.
2. System pokazuje aktualnie najwyższą ofertę.
3. [Kupujący](#ac2) proponuje nową, wyższą kwotę zakupu.
4. System sprawdza, czy oferta jest wyższa od aktualnej najwyższej oferty. ([BR1](#br1))
5. System zapisuje nową ofertę i informuje Kupującego o pomyślnym złożeniu oferty.

**Scenariusze alternatywne:** 
- 4.A. Oferta nie jest wyższa od aktualnej najwyższej oferty.
  * 4.A.1. System informuje Kupującego o konieczności złożenia wyższej oferty.
  * 4.A.2. Przejdź do kroku 3.

---

<a id="uc3"></a>
### UC3: Wygranie aukcji

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. Czas aukcji upływa.
2. System weryfikuje, kto złożył najwyższą ofertę.
3. System informuje Kupującego o wygraniu aukcji, jeśli jego oferta była najwyższa. ([BR2](#br2))

**Scenariusze alternatywne:**
- 3.A. Kupujący nie posiada najwyższej oferty po zakończeniu aukcji.
  * 3.A.1. System informuje Kupującego o przegranej aukcji.

---

<a id="uc4"></a>
### UC4: Przekazanie płatności Sprzedającemu

**Aktorzy:** [Kupujący](#ac2), [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Kupujący](#ac2) inicjuje płatność za wygrany produkt.
2. System pobiera informacje dotyczące płatności i kwoty.
3. System potwierdza otrzymanie płatności i informuje Kupującego o pomyślnym zakończeniu transakcji.
4. System informuje Sprzedającego o przekazaniu należności.

**Scenariusze alternatywne:**
- 2.A. Płatność nie powiodła się.
  * 2.A.1. System informuje Kupującego o nieudanej płatności.
  * 2.A.2. Przejdź do kroku 1.

---

<a id="uc5"></a>
### UC5: Przekazanie produktu Kupującemu

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**
1. System informuje Sprzedającego o przekazaniu płatności.
2. [Sprzedający](#ac1) wysyła produkt do Kupującego.
3. System potwierdza dostarczenie produktu Kupującemu.

---

## Obiekty biznesowe (inaczej obiekty dziedzinowe lub informatyczne)

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu, natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty. Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta, produkt nabywa ten Kupujący, który zaproponował najwyższą kwotę. 

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

## Reguły biznesowe

<a id="br1"></a>
### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.

<a id="br2"></a>
### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujących](#ac2), który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

## Macierz CRUDL


| Przypadek użycia                  | Aukcja (BO1) | Produkt (BO2) | Płatność | Kupujący | Sprzedający |
|-----------------------------------|--------------|---------------|----------|----------|-------------|
| UC1: Wystawienie produktu         | C, R         | C, R          |          | R        | R           |
| UC2: Złożenie oferty zakupu       | R, U         | R             |          | C, R, U  | R           |
| UC3: Wygranie aukcji              | R            | R             |          | R, U     | R           |
| UC4: Przekazanie płatności        | R            | R             | C, R     | U        | R           |
| UC5: Przekazanie produktu         | R            | R, U          | R, U     | R        | R, U        |
