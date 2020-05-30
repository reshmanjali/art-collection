package com.projects.reshmanjali.aartcollection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MetMuseumService {

    @GET("v1/objects?departmentIds=18")
    Call<ListPojo> getValidObjectList();


}
