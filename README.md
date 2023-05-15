
//what can be better.

throw exceptionlar akisi bozuyor eger bir data
bozuk ise akis duruyor.
Bunun yerine sadece log basip akisi durduran sebepleri 
clinete anlamli status code olarak donmek daha iyi olurdu.example :getDocumentFactory()

//Butun caseler cover edilmedi (zaman kisitindan dolayi)
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
