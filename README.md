# README.md

## Przewodnik dotyczący uruchamiania projektu

### Wymagania wstępne

Przed rozpoczęciem, należy upewnić się, że w systemie są zainstalowane następujące oprogramowania:

- **Java Development Kit** (JDK): 17 lub nowsza
- **Gradle**: 7.5.1 lub nowszy
- **Baza danych PostgreSQL**: 14.12 lub nowsza

Podczas budowania projektu, dostęp do Internetu może być pożądany ze względu na konieczność pobierania bibliotek frontendowych. Zaleca się posiadanie aktywnego połączenie z Internetem.

### Struktura projektu

Projekt jest aplikacją Spring Boot i używa Gradle jako narzędzia do budowania. Kod źródłowy zawiera wszystkie niezbędne komponenty do uruchomienia aplikacji.

### Pierwsze kroki

#### 1. Konfiguracja bazy danych

Należy upewnić się, że PostgreSQL działa na komputerze lub jest dostępny zdalnie.

Należy utworzyć nową bazę danych dla projektu.

Należy zmodyfikować plik `application.properties` znajdujący się w `src/main/resources` ustawiając dane do połączenia z bazą danych:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/twoja-baza-danych
spring.datasource.username=twoj-username
spring.datasource.password=twoje-haslo
spring.jpa.hibernate.ddl-auto=update
```

#### 2. Budowanie projektu

Przejść do głównego katalogu projektu (zawiera plik `build.gradle`) i zbudować go za pomocą polecenia:

```sh
./gradlew build
```

#### 3. Uruchomienie aplikacji

Po pomyślnym zbudowaniu projektu, uruchomić aplikację Spring Boot za pomocą polecenia:

```sh
./gradlew bootRun
```

Aplikacja powinna teraz działać pod adresem `http://localhost:8080`.

### Testowanie

W celu uruchomienia testów aplikacji należy wykonać polecenie:

```sh
./gradlew test
```

Testy zostaną uruchomione przy użyciu platformy JUnit.

### Dodatkowe informacje

Więcej szczegółów na temat konfiguracji i używania Spring Boot, Gradle oraz powiązanych technologii można znaleźć w oficjalnej dokumentacji:

- [Dokumentacja Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Dokumentacja Gradle](https://docs.gradle.org/current/userguide/userguide.html)
- [Dokumentacja JUnit 5](https://junit.org/junit5/docs/current/user-guide/)