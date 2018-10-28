package com.gomezrondon.demoelasticsearch.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "dataloadstat", type = "logstat")
public class LogStat {

    @Id
    private String id;

    private String dataLoadDate;
    private String dataFeedDate;
    private String tarFileName;
    private String tranCode;

    public LogStat() {
    }

    public LogStat(String id, String dataLoadDate, String dataFeedDate, String tarFileName, String tranCode) {
        this.id = id;
        this.dataLoadDate = dataLoadDate;
        this.dataFeedDate = dataFeedDate;
        this.tarFileName = tarFileName;
        this.tranCode = tranCode;
    }

    public static LogStat loadByString(String lineOfText, String strDivider){
        //19~2018-10-25T00:52:06~11630407~2018-10-24T17:28:44~ 7:23~20181024172844.tar~*
        String[] split = lineOfText.split(strDivider);
        String dataLoad = validateDateString(split[1]);
        String dataFeed = validateDateString(split[3]);
        LogStat logStat = new LogStat(split[2].trim(), dataLoad, dataFeed, split[5].trim(), split[2].trim());
        System.out.println("Reading: "+logStat);

        return logStat;
    }

    private static String validateDateString(String s) {
        String temp = s.trim();
        String dataLoad = s.trim();
        if(temp.length() != 19){
            dataLoad = temp+":00";

        }
        return dataLoad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataLoadDate() {
        return dataLoadDate;
    }

    public void setDataLoadDate(String dataLoadDate) {
        this.dataLoadDate = dataLoadDate;
    }

    public String getDataFeedDate() {
        return dataFeedDate;
    }

    public void setDataFeedDate(String dataFeedDate) {
        this.dataFeedDate = dataFeedDate;
    }

    public String getTarFileName() {
        return tarFileName;
    }

    public void setTarFileName(String tarFileName) {
        this.tarFileName = tarFileName;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    @Override
    public String toString() {
        return "LogStat{" +
                "id='" + id + '\'' +
                ", dataLoadDate='" + dataLoadDate + '\'' +
                ", dataFeedDate='" + dataFeedDate + '\'' +
                ", tarFileName='" + tarFileName + '\'' +
                ", tranCode='" + tranCode + '\'' +
                '}';
    }
}
