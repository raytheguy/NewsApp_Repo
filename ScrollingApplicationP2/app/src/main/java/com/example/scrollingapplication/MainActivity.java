package com.example.scrollingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//removed some imports as they are now handled by the recycler
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ConstraintLayout top;

    //spinner
    public Spinner topSpin;

    //others from Adapter
    public ArrayList<Integer> imageIds = new ArrayList<>();
    public ArrayList<Integer> articleIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button to go to another activity
        top = findViewById(R.id.top);

        //Spinners test: The spinner does not do anything
        topSpin = findViewById(R.id.topSpin);
        //Create an ArrayAdapter using array in string resources
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.newsArray, android.R.layout.simple_spinner_item);
        //Layout to use when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
        topSpin.setAdapter(adapter);

        initArticleIds();
        initImageIds();
        initRecyclerViewer();

    }

    //method is to obtain the articleIds and place them into an arrayList
    public void initArticleIds() {
        articleIds.add(FakeDatabase.getArticleById(1).getArticleID());
        articleIds.add(FakeDatabase.getArticleById(2).getArticleID());
        articleIds.add(FakeDatabase.getArticleById(3).getArticleID());
        articleIds.add(FakeDatabase.getArticleById(4).getArticleID());
        articleIds.add(FakeDatabase.getArticleById(5).getArticleID());
        articleIds.add(FakeDatabase.getArticleById(6).getArticleID());
    }

    //method is to obtain the imageIds and place them into an arrayList
    public void initImageIds() {
        imageIds.add(R.drawable.centralafrica);
        imageIds.add(R.drawable.bidens);
        imageIds.add(R.drawable.francemourn);
        imageIds.add(R.drawable.inmates);
        imageIds.add(R.drawable.northpole);
        imageIds.add(R.drawable.posthappy);
    }

    //method is to initialise the recyclerViewer
    public void initRecyclerViewer(){
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        //the arrayLists to put inside the RecycleView Adapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(imageIds, articleIds, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //references
    //https://stackoverflow.com/questions/37786796/how-to-display-an-arraylist-in-a-recyclerview
    //https://stackoverflow.com/questions/35817610/large-gap-forms-between-recyclerview-items-when-scrolling-down
    //https://stackoverflow.com/questions/51646852/recyclerview-slow-scrolling-performance-and-lag-halt-sometime-while-scrolling
    //https://stackoverflow.com/questions/28528009/start-new-intent-from-recyclerviewadapter
    //https://stackoverflow.com/questions/42468113/how-can-i-use-getresources-inside-of-onbindviewholder
    //https://developer.android.com/guide/topics/ui/controls/spinner
    //recycle view tutorial: https://www.youtube.com/watch?v=Vyqz_-sJGFk
}
