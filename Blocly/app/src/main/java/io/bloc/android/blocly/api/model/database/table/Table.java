package io.bloc.android.blocly.api.model.database.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kari on 11/9/2015.
 */
public abstract class Table {
    protected static final String COLUMN_ID = "id";
    public abstract String getName();
    public abstract String getCreateStatement();

    public void onUpgrade(SQLiteDatabase writableDatabase, int oldVersion, int newVersion){

    }
}
