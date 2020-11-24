package org.d3if4070.minakitaapp.activities;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.d3if4070.minakitaapp.R;
import org.d3if4070.minakitaapp.adapter.CommentAdapter;
import org.d3if4070.minakitaapp.adapter.CommentDanaAdapter;
import org.d3if4070.minakitaapp.model.Comment;
import org.d3if4070.minakitaapp.model.CommentDana;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostDetailDanaActivity extends AppCompatActivity {

    ImageView imgPost,imgUserPost,imgCurrentUser;
    TextView txtPostDesc,txtPostDateName,txtPostTitle, txtPostDana;
    EditText editTextCommentDana;
    Button btnAddCommentDana;
    String PostKey;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    RecyclerView RvCommentDana;
    CommentDanaAdapter commentDanaAdapter;
    List<CommentDana> listCommentDana;
    static String COMMENT_KEY = "CommentDana" ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail_dana);


        // let's set the statue bar to transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar();

        // ini Views
        RvCommentDana = findViewById(R.id.rv_comment_dana);
        imgPost =findViewById(R.id.post_detail_dana_img);
        imgUserPost = findViewById(R.id.post_detail_user_img_dana);
        imgCurrentUser = findViewById(R.id.post_detail_currentuser_img_dana);

        txtPostTitle = findViewById(R.id.post_detail_title_dana);
        txtPostDesc = findViewById(R.id.post_detail_desc_dana);
        txtPostDateName = findViewById(R.id.post_detail_date_name_dana);
        txtPostDana = findViewById(R.id.post_detail_dana_dana);

        editTextCommentDana = findViewById(R.id.post_detail_comment_dana);
        btnAddCommentDana = findViewById(R.id.post_detail_add_comment_btn_dana);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        // add Comment button click listner

        btnAddCommentDana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnAddCommentDana.setVisibility(View.INVISIBLE);
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey).push();
                String comment_content = editTextCommentDana.getText().toString();
                String uid = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();
                String uimg = firebaseUser.getPhotoUrl().toString();
                CommentDana comment = new CommentDana(comment_content,uid,uimg,uname);

                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("comment added");
                        editTextCommentDana.setText("");
                        btnAddCommentDana.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("fail to add comment : "+e.getMessage());
                    }
                });



            }
        });


        // now we need to bind all data into those views
        // firt we need to get post data
        // we need to send post detail data to this activity first ...
        // now we can get post data

        String postImage = getIntent().getExtras().getString("postImageDana") ;
        Glide.with(this).load(postImage).into(imgPost);

        String postTitle = getIntent().getExtras().getString("titleDana");
        txtPostTitle.setText(postTitle);

        String userpostImage = getIntent().getExtras().getString("userPhotoDana");
        Glide.with(this).load(userpostImage).into(imgUserPost);

        String postDescription = getIntent().getExtras().getString("descriptionDana");
        txtPostDesc.setText(postDescription);

        String postDana = getIntent().getExtras().getString("danaDana");
        txtPostDana.setText(postDana);

        // setcomment user image

        Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imgCurrentUser);
        // get post id
        PostKey = getIntent().getExtras().getString("postKeyDana");

        String date = timestampToString(getIntent().getExtras().getLong("postDateDana"));
        txtPostDateName.setText(date);


        // ini Recyclerview Comment
        iniRvCommentDana();


    }

    private void iniRvCommentDana() {

        RvCommentDana.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listCommentDana = new ArrayList<>();
                for (DataSnapshot snap:dataSnapshot.getChildren()) {

                    CommentDana comment = snap.getValue(CommentDana.class);
                    listCommentDana.add(comment) ;

                }

                commentDanaAdapter = new CommentDanaAdapter(getApplicationContext(),listCommentDana);
                RvCommentDana.setAdapter(commentDanaAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }


    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
        return date;


    }


}
