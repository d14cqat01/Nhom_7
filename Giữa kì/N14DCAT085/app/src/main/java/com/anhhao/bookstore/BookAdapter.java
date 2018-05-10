package com.anhhao.bookstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huy on 4/15/2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final String TAG = "BookAdapter";
    private List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
        Log.d(TAG, "BookAdapter: " + books.size());
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Book book = books.get(position);
        holder.textDescription.setText(book.getDescription());
        holder.textAuthor.setText(book.getAuthor());
        holder.textName.setText(book.getName());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "Sửa");
                contextMenu.add(holder.getAdapterPosition(), 1, 0, "Xóa");
            }
        });
    }



    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDescription, textAuthor;

        public BookViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "BookViewHolder: ");
            textName = itemView.findViewById(R.id.tvNameItem);
            textAuthor = itemView.findViewById(R.id.tvAuthor);
            textDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}

