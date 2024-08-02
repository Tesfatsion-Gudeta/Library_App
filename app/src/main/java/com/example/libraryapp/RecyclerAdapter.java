package com.example.libraryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<BooksModel> books=new ArrayList<>();
    private Context context;

    public void setBooks(ArrayList<BooksModel> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bookName.setText(books.get(position).getBookName());
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImgUrl())
                .into(holder.bookImg);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BooksDetailActivity.class);
                intent.putExtra("bookId",books.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });

        holder.authorName.setText(books.get(position).getAuthorName());
        holder.shortDescription.setText(books.get(position).getShortDesc());

        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.cardView);
            holder.relativeLayout3.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

        }else{
            TransitionManager.beginDelayedTransition(holder.cardView);
            holder.relativeLayout3.setVisibility(View.GONE);
            holder.upArrow.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView bookImg,upArrow,downArrow;
        private TextView bookName,authorName,shortDescription;
        private RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.cardviewbook);
            bookImg=itemView.findViewById(R.id.bookimg);
            bookName=itemView.findViewById(R.id.bookName);

            relativeLayout1=itemView.findViewById(R.id.fulldesc);
            relativeLayout2=itemView.findViewById(R.id.shortdesc);
            relativeLayout3=itemView.findViewById(R.id.collapsed);
            upArrow=itemView.findViewById(R.id.uparrow);
            downArrow=itemView.findViewById(R.id.downarrow);
            authorName=itemView.findViewById(R.id.author);
            shortDescription=itemView.findViewById(R.id.desc);


            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BooksModel booksModel=books.get(getAdapterPosition());
                    booksModel.setExpanded(!booksModel.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BooksModel booksModel=books.get(getAdapterPosition());
                    booksModel.setExpanded(!booksModel.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });


        }
    }
}
