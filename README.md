# Document-Index

Document-Index is a search application for indexing and retrieving documents efficiently. It provides a convenient way to search for documents based on a query and obtain relevant results quickly.



## Installation
To build and run the Document-Index project, please follow these instructions:

## Prerequisites
#### Java 17:Ensure that you have Java Development Kit (JDK) 17 installed on your system.
#### Maven: Make sure you have Apache Maven installed to handle project dependencies and build process.
#### Steps
1.)Clone the repository: git clone https://github.com/karaelf33/document-index.git 

2.) Navigate to the project directory:cd Document-Index

3.)Build the project using Maven: mvn clean install

4.) Run the application: mvn spring-boot:run

5.) Access the application: Open a web browser and go to http://localhost:8080 to access the Document-Index user interface.


### Task Effort
I spent approximately  2 hours research, 3 hours coding

## Caveats and Improvements 
### If I were to start again, I would consider the following improvements:

1:)Pay attention more for Flows: Currently, when an exception occurs during the processing of a faulty file,
it throws custom exceptions and the flow stop of the application.
Instead of completely stopping the flow while searching/saving files, 
it would be more effective to just log the errors and return meaningful status codes to the client and continue the flow for other files.
For instance, in the method [getDocumentFactory()](/src/main/java/com/example/documentindex/documents/factory/DocumentFactoryManagerImpl.java#L47) method.

2:) Test-Driven Development (TDD): Especially Applying Test-Driven Development to the
[calculateQueryMatchScoreInContent()](/src/main/java/com/example/documentindex/search/SearchServiceImpl.java#L11)
method could have significantly reduced development effort. Writing tests before implementing the code helps clarify requirements, 
reduces debugging time, and ensures a more robust implementation. Consider adopting this approach for future development.
If I were start again, I would follow the TDD especially for here.

3:) Factory Design Pattern: It would have been beneficial to spend more time considering the Factory design pattern. 
Returning a dynamic DocumentFactory is a good idea, but it posed challenges when writing tests.
If I were to start again, I would spend more time thinking and implementing the Factory design pattern.
As Uncle Bob suggests, if testing going to be hard often indicates that there might be something wrong.

4:)Util Package: The util package has started to become a bit bothersome. It could be worth considering removing it to clutter the codebase.

4:) Logging in [searchQueryInDocuments()](/src/main/java/com/example/documentindex/service/impl/DocumentServiceImpl.java#L26)and
[saveDocumentWithContent()](/src/main/java/com/example/documentindex/service/impl/DocumentServiceImpl.java#L39) 
Methods: It can be discussed whether to add logs to monitor these methods. 
If monitoring is not crucial, I prefer not to include them, as it can significantly increase the log volume.

5:)Naming Conventions: I made an effort to keep the naming as descriptive and simple as possible. 
However, the naming of Document operations and Search operations seems to be nested, making it appear confusing. 
If I were to start again, I would pay more attention to the naming and separation of responsibilities in these services. 
I would spend more time refactoring the names to ensure clarity and better separate the responsibilities.

6:) Commit Practices: It would have been beneficial to make smaller commits and follow a standard commit message convention. 
By making smaller commits, it becomes easier to track changes and understand the progression of the codebase. Additionally, adhering to a commit message standard provides clarity and context to each commit,
making it easier for team members to understand the changes being made. If I was to start again I would pay attention more to that

7:)Increased Test Coverage: Although the project has a decent test coverage, there is still room for improvement. If I had more time, I would aim to increase the test coverage further, particularly in critical areas and edge cases. Having comprehensive tests helps ensure the reliability and stability of the codebase, catching potential bugs and issues early on. It also provides confidence when making changes or refactoring existing code. In future iterations, 
I would prioritize expanding the test suite to enhance the overall robustness of the application.
