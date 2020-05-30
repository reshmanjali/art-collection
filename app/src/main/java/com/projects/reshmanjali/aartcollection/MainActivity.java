package com.projects.reshmanjali.aartcollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.id_tv);
        recyclerView = findViewById(R.id.id_rv);
        final ListPojo responseListPojo = new ListPojo();

        MetMuseumService metMuseumService = RetrofitInstance.getRetrofitInstance().create(MetMuseumService.class);
        Call<ListPojo> validObjectListCall = metMuseumService.getValidObjectList();
        validObjectListCall.enqueue(new Callback<ListPojo>() {
            @Override
            public void onResponse(Call<ListPojo> call, Response<ListPojo> response) {
                responseListPojo.setObjectIds(response.body().getObjectIds());
                responseListPojo.setTotal(response.body().total);
            }
            @Override
            public void onFailure(Call<ListPojo> call, Throwable t) {
                textView.setText("Sorry");
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new ArtsAdapter());
    }
}
