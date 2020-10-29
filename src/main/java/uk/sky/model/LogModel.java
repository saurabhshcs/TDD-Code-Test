package uk.sky.model;

/*
 * @created 28/10/2020 -00:07
 * @project coding-test
 * @author  saurabhshcs
 */

public class LogModel {

    private long requestTS;
    private String countryCode;
    private long responseTime;

    public LogModel() {
    }

    public LogModel(long requestTS, String countryCode, long responseTime) {
        this.requestTS = requestTS;
        this.countryCode = countryCode;
        this.responseTime = responseTime;
    }

    public long getRequestTS() {
        return requestTS;
    }

    public void setRequestTS(long requestTS) {
        this.requestTS = requestTS;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
