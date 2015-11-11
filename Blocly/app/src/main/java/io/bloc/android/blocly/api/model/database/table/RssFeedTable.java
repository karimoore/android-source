package io.bloc.android.blocly.api.model.database.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kari on 11/9/2015.
 */
public class RssFeedTable extends Table {

    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FEED_URL = "feed_url";


    @Override
    public String getName() {
        return "rss_feeds";
    }

    @Override
    public String getCreateStatement() {
        return "CREATE TABLE " + getName() + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_LINK + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FEED_URL + " TEXT)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase writableDatabase, int oldVersion, int newVersion) {
        super.onUpgrade(writableDatabase, oldVersion, newVersion);
    }

    public String getInsertOrReplaceStatement() {
        return "INSERT OR REPLACE INTO " + getName() + " ("
                + COLUMN_ID + ", "
                + COLUMN_LINK + ", "
                + COLUMN_TITLE + ", "
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_FEED_URL + ") VALUES (";


    }

}
