package com.example.sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.FacultyViewer> {

    List<POJO> frDeltails;
    Context context;
    OpenHelper dbhelper;
    SQLiteDatabase sqLiteDatabase;

    public RecycleAdapter(List<POJO> frDeltails) {
        this.frDeltails = frDeltails;
    }

    @NonNull
    @Override
    public FacultyViewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.view, parent, false);
        FacultyViewer viewHolder = new FacultyViewer(iteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.FacultyViewer holder, final int position) {
        final POJO pojo = frDeltails.get(position);

        holder.pID.setText("ID: " + pojo.getP_id());
        holder.pIdno.setText("ID number: " + pojo.getP_idno());
        holder.pName.setText("Name: " + pojo.getP_name());
        holder.pAddress.setText("Address: " + pojo.getP_address());
        holder.pDegree.setText("Highest Degree Attained:" + pojo.getP_degree());
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int pId = pojo.getP_id();
                dbhelper = new OpenHelper(context);
                sqLiteDatabase = dbhelper.getWritableDatabase();
                sqLiteDatabase.delete(DBInfo.TABLE_NAME, DBInfo._ID+ " = " + pId,null);
                notifyItemRangeChanged(position,frDeltails.size());
                frDeltails.remove(position);
                notifyItemRemoved(position);
                sqLiteDatabase.close();
            }
        });

        holder.txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("stuId", pojo.getP_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return frDeltails.size();
    }

    public class FacultyViewer extends RecyclerView.ViewHolder {
        TextView pID;
        TextView pIdno;
        TextView pName;
        TextView pDegree;
        TextView pAddress;
        TextView txtDelete;
        TextView txtUpdate;

        public FacultyViewer(View itemView) {
            super(itemView);
            pID = itemView.findViewById(R.id.txtid);
            pIdno = itemView.findViewById(R.id.txtidno);
            pName = itemView.findViewById(R.id.txtname);
            pAddress = itemView.findViewById(R.id.txtaddress);
            pDegree = itemView.findViewById(R.id.txtDegree);
            txtDelete = itemView.findViewById(R.id.txtDelete);
            txtUpdate = itemView.findViewById(R.id.txtUpdate);
        }
    }
    public void deleteFaculty() {

    }
}
