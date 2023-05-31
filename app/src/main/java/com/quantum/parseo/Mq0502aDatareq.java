package com.quantum.parseo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mq0502aDatareq {
    @SerializedName("tableId")
    @Expose
    private String tableId;
    @SerializedName("rowset")
    @Expose
    private List<Rowset2> rowset = null;
    @SerializedName("records")
    @Expose
    private Integer records;
    @SerializedName("moreRecords")
    @Expose
    private Boolean moreRecords;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }


    public List<Rowset2> getRowset() {
        return rowset;
    }

    public void setRowset(List<Rowset2> rowset) {
        this.rowset = rowset;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Boolean getMoreRecords() {
        return moreRecords;
    }

    public void setMoreRecords(Boolean moreRecords) {
        this.moreRecords = moreRecords;
    }

}