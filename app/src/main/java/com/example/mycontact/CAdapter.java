package com.example.mycontact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CAdapter extends RecyclerView.Adapter<CAdapter.ViewHolder> {
    ArrayList<Modelclass> arrayList;
    Modelclass modal;
    DBHelper dbHelper;
    Context context;
    AlertDialog.Builder builder;




    public CAdapter(ArrayList<Modelclass> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Modelclass modelclass= arrayList.get(position);
        holder.name1.setText(modelclass.getName());
        holder.contact1.setText(modelclass.getContact());
        holder.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an alert builder
                Intent i = new Intent(context, UpdateActivity.class);
                // below we are passing all our values.
                i.putExtra("name", modelclass.getName());
                i.putExtra("contact", modelclass.getContact());
                // starting our activity.
                context.startActivity(i);
           /*         AlertDialog.Builder builder
                            = new AlertDialog.Builder(context);
                    builder.setTitle("Contact Update");

                    // set the custom layout
                    final View customLayout = LayoutInflater.from(context).inflate(R.layout.updateiteam,null);
                    builder.setView(customLayout);
                    // add a button
                    builder.setPositiveButton(
                                    "Update", new DialogInterface.OnClickListener()
                                    {@Override public void onClick(DialogInterface dialog, int which)
                                        {
                                            // send data from the
                                            // AlertDialog to the Activity
                                            EditText name= customLayout.findViewById(R.id.name);
                                            EditText contact =customLayout.findViewById(R.id.contact);
                                            dbHelper = new DBHelper(context) ;
                                            String updatename = name.getText().toString();
                                            String updateContact = contact.getText().toString();
                                            Boolean updateData = dbHelper.Updatedata(updatename,updateContact);
                                            if (updateData==true){
                                                Toast.makeText(context, "Update Sucessfull", Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(context, "Update Unsucessfull", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                    AlertDialog dialog
                            = builder.create();
                    dialog.show();*/
            }

        });



       /* holder.name.setText(arrayList.get(position).getName());
        holder.contact.setText(arrayList.get(position).getContact());*/

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name1,contact1;
        ImageView delete, updatebtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.name);
            contact1 = itemView.findViewById(R.id.contact);
            delete = itemView.findViewById(R.id.delete);
            updatebtn = itemView.findViewById(R.id.update);
            itemView.setClickable(true);
            builder = new AlertDialog.Builder(context);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.setMessage("Are you want to delete contact") .setTitle("Delete Contact");
                    builder.setMessage("Do you want to delete contact ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dbHelper = new DBHelper(context) ;
                                  /*  dbHelper.Deletedata(id);
                                    arrayList.remove(id);
                                    notifyItemRemoved(id);
                                    notifyDataSetChanged();*/

                                    String nametxt = name1.getText().toString();
                                    Boolean deleteData = dbHelper.Deletedata(nametxt);
                                    if (deleteData==true){
                                        Toast.makeText(context, "Contact Deleted Succesfull", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(context,MainActivity.class);
                                        context.startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(context, "Contact not Delete", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(context,"cancel",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Delete Contact");
                    alert.show();

                }
            });
        }
    }
}
