package uk.sky;

import org.junit.Test;
import uk.sky.model.LogModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static uk.sky.DataFilterer.*;

public class DataFiltererTest {

    private static final String EMPTY_LOG_FILE = "src/test/resources/empty";
    private static final String GB = "GB";
    private static final String US = "US";
    private static final String SINGLE_LINE_LOG_FILE = "src/test/resources/single-line";
    private static final String NO_LOG_FILE = "src/test/resources/single-lines";
    public static final String MULTI_LINES_LOG_FILE = "src/test/resources/multi-lines";

    @Test
    public void shouldReturnEmptyCollection_whenLOGFileEmpty() throws FileNotFoundException {

        assertTrue(filterByCountry(openFile(EMPTY_LOG_FILE), GB).isEmpty());
        assertTrue(filterByCountryWithResponseTimeAboveLimit(openFile(EMPTY_LOG_FILE), US, 100).isEmpty());
        assertTrue(filterByResponseTimeAboveAverage(openFile(EMPTY_LOG_FILE)).isEmpty());
    }

    @Test
    public void shouldReturnSingleLineCollection_whenFilterByCountry() throws FileNotFoundException {

        List<LogModel> logs = (List<LogModel>) filterByCountry(openFile(SINGLE_LINE_LOG_FILE), GB);

        assertThat(logs.size(), is(1));
        assertThat(logs.get(0).getCountryCode(), is(GB));
    }

    /*
    * Added this additional test scenario while TDD to make coverage of given data has more than one line of US
    * */
    @Test
    public void shouldReturnMultiLineCollection_whenFilterByCountry() throws FileNotFoundException {

        List<LogModel> logs = (List<LogModel>) filterByCountry(openFile(MULTI_LINES_LOG_FILE), US);

        assertThat(logs.size(), is(not(1)));
    }

    @Test
    public void shouldThrowException_WhenFilterByCountry() {
        try{
            assertTrue(filterByCountry(openFile(NO_LOG_FILE), GB).isEmpty());
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @Test
    public void shouldThrowException_WhenResponseTimeAboveLimit()  {

        try {
            assertTrue(filterByCountryWithResponseTimeAboveLimit(openFile(NO_LOG_FILE), GB, 100).isEmpty());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnEmptyCollection_WhenResponseTimeAboveLimit() throws FileNotFoundException {
        assertTrue(filterByCountryWithResponseTimeAboveLimit(openFile(SINGLE_LINE_LOG_FILE), GB, 200).isEmpty());
        assertTrue(filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LINES_LOG_FILE), US, 851).isEmpty());
    }

    @Test
    public void shouldReturnSingleLineCollection_WhenResponseTimeNotAboveLimit() throws FileNotFoundException {

        List<LogModel> singleLineLog = (List<LogModel>) filterByCountryWithResponseTimeAboveLimit(openFile(SINGLE_LINE_LOG_FILE), GB, 30);

        assertThat(singleLineLog.size(), is(1));
        assertTrue(singleLineLog.get(0).getResponseTime() > 30);
    }

    @Test
    public void shouldReturnMultiLineLogCollection_WhenResponseTimeNotAboveLimit() throws FileNotFoundException {

        List<LogModel> multiLineLog = (List<LogModel>) filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LINES_LOG_FILE), US, 400);

        assertThat(multiLineLog.size(), is(not(1)));
        assertTrue(multiLineLog.get(0).getResponseTime() > 400);
    }

    @Test
    public void shouldReturnMultiLineCollection_WhenResponseTimeAboveAverage() throws FileNotFoundException {

        /*
        * total responseTime / number of rows in the logs
        * hence average from multi line = 526 for given logs
        * */

        List<LogModel> multiLineLogsAboveAverage = (List<LogModel>) filterByResponseTimeAboveAverage(openFile(MULTI_LINES_LOG_FILE));

        assertTrue(multiLineLogsAboveAverage.size() > 1 );
        assertTrue(multiLineLogsAboveAverage.get(0).getResponseTime() > 526);

    }

    @Test
    public void shouldThrowException_WhenResponseTimeAboveAverage() {
        try {
            assertTrue(filterByResponseTimeAboveAverage(openFile(NO_LOG_FILE)).isEmpty());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
