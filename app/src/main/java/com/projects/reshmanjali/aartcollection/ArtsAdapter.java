package com.projects.reshmanjali.aartcollection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ArtsAdapter extends RecyclerView.Adapter<ArtsAdapter.MyViewHolder> {

    ListPojo validObjectsListPojo;
    int[] objectIds;
    MetMuseumService metMuseumService;//= RetrofitInstance.getRetrofitInstance().create(MetMuseumService.class);

    public ArtsAdapter(ListPojo responseListPojo) {
        validObjectsListPojo = responseListPojo;
        objectIds = validObjectsListPojo.getObjectIds();
        metMuseumService = RetrofitInstance.getRetrofitInstance().create(MetMuseumService.class);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_art, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        setDataAfterAServiceCall(myViewHolder,i);
    }

    void setDataAfterAServiceCall(final MyViewHolder myViewHolder, int i){
        Call<ArtObjectPojo> arObject = metMuseumService.getArObject(objectIds[i]);
        arObject.enqueue(new Callback<ArtObjectPojo>() {
            @Override
            public void onResponse(Call<ArtObjectPojo> call, Response<ArtObjectPojo> response) {
                myViewHolder.artName.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<ArtObjectPojo> call, Throwable t) {
                myViewHolder.artName.setText("Uff Sorry");
            }
        });
       // myViewHolder.artName.setText("hgyghb");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView artName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            artName = itemView.findViewById(R.id.id_art_name_tv);
        }
    }
}
