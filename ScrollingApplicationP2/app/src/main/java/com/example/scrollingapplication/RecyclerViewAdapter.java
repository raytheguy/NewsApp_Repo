package com.example.scrollingapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

//viewholder type
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    //for debugging
    private static final String TAG = "RecyclerViewAdapter";
    //ArrayList of Strings to Hold Imageids
    public ArrayList<Integer> imageIds = new ArrayList<>();
    public ArrayList<Integer> articleIds = new ArrayList<>();
    public Context mContext;

    public RecyclerViewAdapter(ArrayList<Integer> imageIds, ArrayList<Integer> articleIds, Context mContext) {
        this.imageIds = imageIds;
        this.articleIds = articleIds;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    //inflate the view; same as any recycle adapter, just need to change the id
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent, false);
        ViewHolder holder = new ViewHolder(myView);
        return holder;
    }

    //changes based on layout
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //log for debugging purposes
        //called when loaded
        Log.d(TAG, "Tester called");

        //set the headline and image
        //add 1 to position as article ids start from 1
        holder.textView.setText(FakeDatabase.getArticleById(position+1).getHeadline());
        holder.imageView.setImageResource(imageIds.get(position));

        //Ger resources which is 'by NY Times'
        final String res = holder.itemView.getContext().getResources().getString(R.string.NYTimes);

        //onClick Listener when the 'holder' or constraintLayout is clicked
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                Snackbar.make(myView, "Intent initiated and your article ID is " + position+1, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intentBtn = new Intent(myView.getContext(), DetailActivity.class);
                //pass article id number over to the detail activity
                //add one as the arrayLists start from '0' while the articles start from '1'
                intentBtn.putExtra("ArticleToPass", position+1);
                intentBtn.putExtra("ImageToPass", imageIds.get(position));
//                intentBtn.putExtra("ImageToPass", articleImgID);
                mContext.startActivity(intentBtn);
            }
        });

        //onClick Listening for the share button
        //onClick Listener when the 'holder' or constraintLayout is clicked
        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
                Snackbar.make(myView, "Intent initiated and your article ID is " + position+1, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //intent for the button send thing
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, FakeDatabase.getArticleById(position+1).getHeadline() + res);
                mContext.startActivity(textIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //get the amount of article Ids that exists
        return articleIds.size();
    }

    //purpose: holds widgets in memory
    //attach the widgets to its id in the news layout
    public class ViewHolder extends RecyclerView.ViewHolder {

        //declare everything the ViewHolder holds here
        //in news_layout, there is imageView
        ConstraintLayout layoutMain;
        ImageView imageView;
        TextView textView;
        View view;
        Button shareBtn;

        public ViewHolder(@NonNull View v) {
            //super to inherit the View class
            super(v);

            //attach the items to the id
            layoutMain = v.findViewById(R.id.layoutMain);
            imageView = v.findViewById(R.id.imageView);
            view = v.findViewById(R.id.imageView);
            textView = v.findViewById(R.id.textView);
            shareBtn = v.findViewById(R.id.shareBtn);

        }
    }
}
