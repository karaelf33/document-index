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
If I were to start again, I would consider the following improvements:

1:)Currently, when an exception occurs during the processing of a faulty file,
it throws custom exceptions and the flow stop of the application.
Instead of completely stopping the flow while searching/saving files, 
it would be more effective to log the errors and return meaningful status codes to the client and continue the flow for other files.
For instance, in the method [getDocumentFactory()](\src\main\java\com\example\documentindex\documents\factory\DocumentFactoryManagerImpl.java#LXX) method.


//Butun caseler cover edilmedi (zaman kisitindan dolayi)`
.Eger ekleseydim daha fazla if-else statement 
eklenemek zorunda kalirdim.Butun case,leri en basta
dusunup gereksiz if else statmentten kurtulmak icin ona gore daha 
iyi bir desing yapilarbilirdi.
--->file,query icin validtor desing edilebilir 
//calculateQueryMatchScoreInContent() methodu icin TDD uygulamayak
kodlarken ki efforu cok dusurudu.
Clintten. istek atarak test etmek kod yazma suresini attirdi.

// factory desing patternin uzerinde biraz daha fazla dusunmek 
daha iyi olurdu.Dinamik bir documentFactory dondundermek mantkli
fakat test yazarken bir hayli zor.
Cunku test codelari yazarken zorlandim.
Bir seylerin yanlis gittige dair en buyuk isaret(Uncle bob)

// util package gozumu rahatsiz etmeye basladi.
Silinmesi icin uzerinde dusurebilir

//searchQueryInDocuments,saveDocumentWithContent
methodlarina log koymak uzerine tartisalabilir 
munlari monitor etmek onemini icin.
Eger montirin yapilmasi icin cok onemli degilse 
koyulmamasini tercih ederim.
Koyuldugu takdirde log hacmi cok artabilir. 

// isimlendirmeler olabildiginde aciklayici ve basit
olmasina ozen gosterdim.Fakat refactor edildiginde
daha iyi isimlendirmeler her zaman bulunabilir.

// daha kucuk commitler atilabilir ve commit mesajlarinin
standarti olabilirdi.
//
