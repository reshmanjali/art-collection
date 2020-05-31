package com.projects.reshmanjali.aartcollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MetMuseumService {

    @GET("v1/objects?departmentIds=18")
    Call<ListPojo> getValidObjectList();

    @GET("v1/objects/{objId}")
    Call<ArtObjectPojo> getArObject(@Path("objId") int id);
}
