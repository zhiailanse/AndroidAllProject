package com.zmm.androidallproject.four_big_component.DBandCP;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.PetType;
import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.Pets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class PetTrackerListActivity extends PetTrackerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpets);

        // Fill ListView from database
        fillPetList();

        // Handle Go enter more pets button
        final Button gotoEntry = (Button) findViewById(R.id.ButtonEnterMorePets);
        gotoEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // We're done here. Finish and return to the entry screen
                finish();
            }
        });
    }

    public void fillPetList() {
        // SQL Query
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(Pets.Pets_Table_Name + ", "
                + PetType.PetType_Table_Name);
        queryBuilder.appendWhere(Pets.Pets_Table_Name + "." + Pets.Pet_Type_Id
                + "=" + PetType.PetType_Table_Name + "." + PetType._ID);

        // Get the database and run the query
        String asColumnsToReturn[] = {
                Pets.Pets_Table_Name + "." + Pets.Pet_Name,
                Pets.Pets_Table_Name + "." + Pets._ID,
                PetType.PetType_Table_Name + "." + PetType.Pet_Type_Name };
        mCursor = queryBuilder.query(mDB, asColumnsToReturn, null, null, null,
                null, Pets.Default_Sort_Order);

        System.out.println("++++++++"+mCursor.getCount());
        // Use an adapter to bind the data to a ListView, where each item is
        // shown as a pet_item layout
        startManagingCursor(mCursor);
        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.pet_item,
                mCursor, new String[] { Pets.Pet_Name, PetType.Pet_Type_Name },
                new int[] { R.id.TextView_PetName, R.id.TextView_PetType });

        ListView av = (ListView) findViewById(R.id.petList);
        av.setAdapter(adapter);

        av.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // Check for delete button
                final long deletePetId = id;

                RelativeLayout item = (RelativeLayout) view;
                TextView nameView = (TextView) item
                        .findViewById(R.id.TextView_PetName);
                String name = nameView.getText().toString();
                // Use an Alert dialog to confirm delete operation
                new AlertDialog.Builder(PetTrackerListActivity.this)
                        .setMessage("Delete Pet Record for " + name + "?")
                        .setPositiveButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                            int which) {

                                        // Delete that pet
                                        deletePet(deletePetId);

                                        // Refresh the data in our cursor and
                                        // therefore our List
                                        mCursor.requery();
                                    }
                                }).show();
            }
        });
    }

    public void deletePet(Long id) {
        String astrArgs[] = { id.toString() };
        mDB.delete(Pets.Pets_Table_Name, Pets._ID + "=?", astrArgs);
    }

}
