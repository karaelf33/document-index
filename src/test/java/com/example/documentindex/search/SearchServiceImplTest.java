package com.example.documentindex.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @BeforeEach
    public void beforeEach(){
        searchService=new SearchServiceImpl();
    }

    // WARNING: content can't be shorter then query
    public static Stream<Arguments> query_requests() {
        return Stream.of(
                Arguments.of("abc adb","xxx abc adb yyy",100.00),
                Arguments.of("xx yy zz xx yy tt","aa bb cc dd xx yy zz xx yy zz xx yy tt",100.0),
                Arguments.of("This search query","This is the content of my dddasdsfsfd file.\n" +
                        "This is the content of my dddasdsfsfd file.\n" +
                        "This is the content of my dddasdsfsfd file.\n" +
                        "This is the content of my dddasdsfsfd file.\n" +
                        "This is the content of my dddasdsfsfd file.\n" +
                        "another.\n" +
                        "another\n" +
                        "is\n",33.3),

                Arguments.of("I am going to school adb","You am going to school xx yy tt",0.00),
                Arguments.of("I am going to school adb","I am going to school hospital tt ll ww al",83.3),
                Arguments.of("I am going to school","I am going hospital not going to school",60.00),
                Arguments.of("This search query","This search is the content of my dddasdsfsfd file.\\n\" +\n" +
                        "                        \"This is the content of my dddasdsfsfd file.\\n\" +\n" +
                        "                        \"This is the content of my dddasdsfsfd file.\\n\" +\n" +
                        "                        \"This is the content of my dddasdsfsfd file.\\n\" +\n" +
                        "                        \"another.\\n\" +\n" +
                        "                        \"another\\n\" +\n" +
                        "                        \"is\\n\"",66.6)
        );
    }

    @ParameterizedTest
    @MethodSource("query_requests")
    public void it_should_return_correct_match_score(String query,String content,double expectedMatchScore){

        // when
        double queryMatchScoreInContent = searchService.getQueryMatchScoreInContent(query, content);
        double differenceCanBe=0.99999999;

        //then
        assertEquals(expectedMatchScore,queryMatchScoreInContent,differenceCanBe);



    }
}