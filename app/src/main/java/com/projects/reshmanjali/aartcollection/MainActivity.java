package com.projects.reshmanjali.aartcollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.id_rv);
        progressBar = findViewById(R.id.id_pro_bar);
        final ListPojo responseListPojo = new ListPojo();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        MetMuseumService metMuseumService = RetrofitInstance.getRetrofitInstance().create(MetMuseumService.class);
        Call<ListPojo> validObjectListCall = metMuseumService.getValidObjectList();
        validObjectListCall.enqueue(new Callback<ListPojo>() {
            @Override
            public void onResponse(Call<ListPojo> call, Response<ListPojo> response) {
                progressBar.setVisibility(View.GONE);
                responseListPojo.setObjectIds(response.body().getObjectIds());
                responseListPojo.setTotal(response.body().total);
                recyclerView.setAdapter(new ArtsAdapter(responseListPojo,getApplicationContext()));
            }
            @Override
            public void onFailure(Call<ListPojo> call, Throwable t) {
               // textView.setText("Sorry");
            }
        });

    }
}
