package com.projects.reshmanjali.aartcollection;

import com.google.gson.annotations.SerializedName;

public class ListPojo {
    @SerializedName("total")
    int total;
    @SerializedName("objectIDs")
    int[] objectIds;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int[] getObjectIds() {
        return objectIds;
    }

    public void setObjectIds(int[] objectIds) {
        this.objectIds = objectIds;
    }
}
