package com.example.libraryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    private ArrayList<BooksModel> books = new ArrayList<>();
    private final Context context;
    private final String parentActivity;

    public RecyclerAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    public void setBooks(ArrayList<BooksModel> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcardview, parent, false);
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
                Intent intent = new Intent(context, BooksDetailActivity.class);
                intent.putExtra("bookId", books.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });

        holder.authorName.setText(books.get(position).getAuthorName());
        holder.shortDescription.setText(books.get(position).getShortDesc());

        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.cardView);
            holder.relativeLayout3.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (parentActivity.equals("reading")) {
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(holder.getAdapterPosition()).getBookName() + " from currently reading books list?");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.getSingletonInstance(context).removeFromReading(books.get(holder.getAdapterPosition()));
                                Toast.makeText(context, "book successfully deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();

                            }
                        });
                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            } else if (parentActivity.equals("wishlist")) {
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(holder.getAdapterPosition()).getBookName() + " from wishlist?");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.getSingletonInstance(context).removeFromWishList(books.get(holder.getAdapterPosition()));
                                Toast.makeText(context, "book successfully deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();

                            }
                        });
                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            } else if (parentActivity.equals("read")) {
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(holder.getAdapterPosition()).getBookName() + " from already read books list?");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.getSingletonInstance(context).removeFromAlreadyRead(books.get(holder.getAdapterPosition()));
                                Toast.makeText(context, "book successfully deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();

                            }
                        });
                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            } else if (parentActivity.equals("favourite")) {
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(holder.getAdapterPosition()).getBookName() + " from favourite books list?");
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.getSingletonInstance(context).removeFromFavourite(books.get(holder.getAdapterPosition()));
                                Toast.makeText(context, "book successfully deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();

                            }
                        });
                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }

        } else {
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

        private final CardView cardView;
        private final ImageView bookImg;
        private final ImageView upArrow;
        private final ImageView downArrow;
        private final TextView bookName;
        private final TextView authorName;
        private final TextView shortDescription;
        private final TextView deleteBtn;
        private final RelativeLayout relativeLayout1;
        private final RelativeLayout relativeLayout2;
        private final RelativeLayout relativeLayout3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardviewbook);
            bookImg = itemView.findViewById(R.id.bookimg);
            bookName = itemView.findViewById(R.id.bookName);

            relativeLayout1 = itemView.findViewById(R.id.fulldesc);
            relativeLayout2 = itemView.findViewById(R.id.shortdesc);
            relativeLayout3 = itemView.findViewById(R.id.collapsed);
            upArrow = itemView.findViewById(R.id.uparrow);
            downArrow = itemView.findViewById(R.id.downarrow);
            authorName = itemView.findViewById(R.id.author);
            shortDescription = itemView.findViewById(R.id.desc);
            deleteBtn = itemView.findViewById(R.id.deletebtn);


            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BooksModel booksModel = books.get(getAdapterPosition());
                    booksModel.setExpanded(!booksModel.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BooksModel booksModel = books.get(getAdapterPosition());
                    booksModel.setExpanded(!booksModel.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });


        }
    }
}
