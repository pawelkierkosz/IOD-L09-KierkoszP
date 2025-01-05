Paweł Kierkosz 155995
2.1
Żeby zweryfikować, czy metoda loadExpenses prawidłowo współdziała z testowym obiektem bazy danych, można wykorzystać bibliotekę Mockito. Najpierw tworzy się obiekt pełniący rolę zamiennika bazy i określa sposób jego działania, wskazując, jakie wywołania metod są dozwolone i jakie wartości powinny być zwracane. Następnie, po wywołaniu metody loadExpenses, można użyć narzędzia InOrder z Mockito, aby skontrolować, czy metody connect, queryAll oraz close zostały wywołane w odpowiedniej kolejności.

5.1
Korzystając z biblioteki Mockito, należy zwracać szczególną uwagę na kolejność oczekiwań definiowanych dla tej samej metody przyjmującej różne argumenty. Mockito sprawdza wywołania metod dokładnie w takiej kolejności, w jakiej zostały one określone w teście. Dlatego kluczowe jest zachowanie właściwej sekwencji deklaracji, aby uniknąć błędów i zapewnić prawidłowe działanie testów. Zmiana układu oczekiwań może prowadzić do nieoczekiwanych rezultatów.
