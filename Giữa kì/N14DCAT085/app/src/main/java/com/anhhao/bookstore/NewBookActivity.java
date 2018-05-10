package com.anhhao.bookstore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewBookActivity extends AppCompatActivity {
    EditText etName;
    EditText etDescription;
    EditText etAuthor;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        setControl();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference bookRef = database.getReference("books");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if (intent.hasExtra("book")) {
            final Book book = (Book) intent.getSerializableExtra("book");
            etAuthor.setText(book.getAuthor());
            etName.setText(book.getName());
            etDescription.setText(book.getDescription());
            btnAdd.setText("Lưu");
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    book.setAuthor(etAuthor.getText().toString());
                    book.setDescription(etDescription.getText().toString());
                    book.setName(etName.getText().toString());
                    Map<String, Object> bookValue = book.toMap();
                    Map<String, Object> newBook = new HashMap<>();
                    newBook.put(book.getBookId(), bookValue);
                    bookRef.updateChildren(newBook);
                    Toast.makeText(NewBookActivity.this, "Successful", Toast.LENGTH_LONG).show();
                }
            });
        } else {
//            Push
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = new Book();
                    String bookId = bookRef.push().getKey();
                    book.setBookId(bookId);
                    book.setAuthor(etAuthor.getText().toString());
                    book.setDescription(etDescription.getText().toString());
                    book.setName(etName.getText().toString());
                    bookRef.child(bookId).setValue(book);
                    Toast.makeText(NewBookActivity.this, "Successful", Toast.LENGTH_LONG).show();
                }
            });
        }


    }
    public void setControl(){
        etName = findViewById(R.id.input_name);
        etDescription = findViewById(R.id.input_description);
        etAuthor = findViewById(R.id.input_author);
        btnAdd = findViewById(R.id.btnAddBook);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
