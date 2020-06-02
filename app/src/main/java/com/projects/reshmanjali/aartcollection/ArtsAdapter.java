package com.projects.reshmanjali.aartcollection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ArtsAdapter extends RecyclerView.Adapter<ArtsAdapter.MyViewHolder> {

    ListPojo validObjectsListPojo;
    int[] objectIds;
    Context context;
    MetMuseumService metMuseumService;//= RetrofitInstance.getRetrofitInstance().create(MetMuseumService.class);

    public ArtsAdapter(ListPojo responseListPojo, Context context) {
        this.context = context;
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
                ArtObjectPojo body = response.body();
                myViewHolder.artName.setText(body.getTitle());
                myViewHolder.artCulture.setText("Culture: "+ body.getCulture());
                myViewHolder.artCreditLine.append("Credit Line "+ body.getCreditLine());
                if(body.getPrimaryImage() != "")
                    Picasso.with(context).load(body.getPrimaryImage()).into(myViewHolder.artImg);
                else
                    Picasso.with(context).load(R.drawable.no_img_placeholder).into(myViewHolder.artImg);
            }

            @Override
            public void onFailure(Call<ArtObjectPojo> call, Throwable t) {
                myViewHolder.artName.setText("Uff Sorry");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView artName;
        ImageView artImg;
        TextView artCulture;
        TextView artCreditLine;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            artName = itemView.findViewById(R.id.id_art_name_tv);
            artImg = itemView.findViewById(R.id.id_art_img_iv);
            artCulture = itemView.findViewById(R.id.id_art_culture_tv);
            artCreditLine = itemView.findViewById(R.id.id_art_cLine_tv);
        }
    }
}
