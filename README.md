# COVID-19 track and trace system

This project is a COVID-19 track and trace system with a command line interface. The program enables the recording and management of data about Events, Establishments and Users. The data is recorded for the purpose of tracing and alerting people who have been in close contact with a person who tested positive for COVID-19 [^1].

It was created as part of the *Introduction to Software Development* module of the MSc Computer Science course at Newcastle University in November 2020 but has been revised and updated in September 2022.

## Tools and technologies used

- Programming language: **Java**
- Build tool: **Maven**
- Unit testing framework: **JUnit**

## Java library packages and classes used

- `File` (io package) -> to retrieve the CSV file containing establishments to import
- `Scanner` (util package) -> for recording user input via the command line interface and reading a CSV file
- `ArrayList` (util package) -> to store Events and Establishments
- `DateTimeFormatter`, `LocalDate`, `LocalTime`, and `Period` (time package) -> for creating date and time objects and calculating a user’s age

The code has been commented to further explain the purpose of classes and methods.

[^1]: Disclaimer: All user data is fictional, no personal information is used in the program.
