package uk.sky;

import uk.sky.model.LogModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class DataFilterer {


    private static final String COMMA_DELIMITER = ",";

    /*
    * filterByCountry does filter the logs by country code.
    * @param source file
    * @param country string value used for filter the collection of logs
    * @return A collection of logs
    * */
    public static Collection<?> filterByCountry(Reader source, String country) {

        Collection<LogModel> collection = readLogs(source);

        return collection
                .stream()
                .filter(logModel -> logModel.getCountryCode().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    /*
     * filterByCountryWithResponseTimeAboveLimit does filter the logs by country and ResponseTime limit.
     * @param source file
     * @param country string value used for filter the collection of logs
     * @param limit long value that used to second condition for filter the collection of logs
     * @return A collection of logs
     * */
    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {

        Collection<LogModel> collection = readLogs(source);

        return collection
                .stream()
                .filter(logModel -> logModel.getCountryCode().equalsIgnoreCase(country) && logModel.getResponseTime() > limit)
                .collect(Collectors.toList());

    }

    /*
     * filterByCountry does filter the logs by country code.
     * @param source file
     * @return A collection of logs
     * */
    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {

        Collection<LogModel> logs = readLogs(source);

        if(logs.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        /*
        * Let's calculate the average response time of logs
        * later filter the collection of logs.
        * */
        Double avgResponseTime = logs
                .stream()
                .mapToDouble(LogModel::getResponseTime)
                .average().orElse(0);

        return logs
                .stream()
                .filter(logModel -> logModel.getResponseTime() > avgResponseTime)
                .collect(Collectors.toList());
    }


    /*
    * readsLogs does read all the rows from given source then
    * it splits the columns value by comma and added into a
    * list except [0]th row of this source file.
    * */
    private static Collection readLogs(Reader source) {
        Collection<LogModel> logs = new ArrayList<>();
        BufferedReader inBufferReader = new BufferedReader(source);
        String rows;
        int rowNumber = 0;

        try {
            while ((rows = inBufferReader.readLine()) != null) {

                rowNumber++;

                //Started from line 1, to exclude header section
                if (rowNumber == 1) {
                    continue;
                }

                String[] logAttributes = rows.split(COMMA_DELIMITER);

                //We can use LogModel::set... to set the values, but applied parameterised constructor for making simple code logic.
                logs.add(new LogModel(Long.parseLong(logAttributes[0]), logAttributes[1], Long.parseLong(logAttributes[2])));
            }
            return logs;

        } catch (IOException e) {

            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}