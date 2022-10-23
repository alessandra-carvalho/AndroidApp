package com.mobile.androidapp.view;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobile.androidapp.R;
import com.mobile.androidapp.model.comments.Comments;
import com.mobile.androidapp.repository.AlbumsRepository;
import com.mobile.androidapp.repository.CommentsRepository;
import com.mobile.androidapp.repository.PhotosRepository;
import com.mobile.androidapp.repository.PostsRepository;
import com.mobile.androidapp.repository.TodosRepository;
import com.mobile.androidapp.repository.UserRepository;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // chama activity main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", "onCreate: antes do getInstance");

        //UserRepository.getInstance(this);
        //Log.e("TAG", "onCreate: depois do getInstance " + UserRepository.getInstance(this).getUsers().size());

        //PostsRepository.getInstance(this);
        //Log.e("TAG", "onCreate: depois do getInstance " + PostsRepository.getInstance(this).getPosts().size());

        //CommentsRepository.getInstance(this);
        //Log.e("TAG", "onCreate: depois do getInstance " + CommentsRepository.getInstance(this).getComments().size());

        //AlbumsRepository.getInstance(this);
        //Log.e("TAG", "onCreate: depois do getInstance " + AlbumsRepository.getInstance(this).getAlbums().size());

        //PhotosRepository.getInstance(this);
        //Log.e("TAG", "onCreate: depois do getInstance " + PhotosRepository.getInstance(this).getPhotos().size());

        TodosRepository.getInstance(this);
        Log.e("TAG", "onCreate: depois do getInstance " + TodosRepository.getInstance(this).getTodos().size());

        setContentView(R.layout.activity_main);
    }
}