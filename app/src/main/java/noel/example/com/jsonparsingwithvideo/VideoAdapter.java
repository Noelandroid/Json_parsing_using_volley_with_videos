package noel.example.com.jsonparsingwithvideo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {




    List<Video> videos;
    Context mcontext;

    public VideoAdapter(Context ctx, List<Video> videoList) {

        this.videos = videoList;
        this.mcontext=ctx;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videoholder, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(videos.get(position).getTitle());
        holder.text2.setText(videos.get(position).getDescription());
        holder.text3.setText(videos.get(position).getAuthor());
        Picasso.with(mcontext).load(videos.get(position).getImageurl()).into(holder.videoimage);
        //Glide.with(mcontext).load(videos.get(position).getImageurl()).into(holder.videoimage);
        holder.vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b= new Bundle();
                b.putSerializable("videodata",videos.get(position));
                Intent intent=new Intent(mcontext,Player.class);
                intent.putExtras(b);
                view.getContext().startActivity(intent);
            }
        });









    }

    @Override
    public int getItemCount() {

        return videos.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,text2,text3,text4;
        public ImageView videoimage;
        View vv;






        public ViewHolder(View itemView) {

            super(itemView);

            videoimage = (ImageView) itemView.findViewById(R.id.videothumbnail);

            title = (TextView) itemView.findViewById(R.id.videotitle);
            text2 = (TextView) itemView.findViewById(R.id.textview2);
            text3 = (TextView) itemView.findViewById(R.id.textview3);
            text4 = (TextView) itemView.findViewById(R.id.textview4);

           vv=itemView;










        }


    }



}
