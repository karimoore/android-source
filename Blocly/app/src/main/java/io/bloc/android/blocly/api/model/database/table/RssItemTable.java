package io.bloc.android.blocly.api.model.database.table;

/**
 * Created by Kari on 11/9/2015.
 */
public class RssItemTable extends Table {
    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_GUID = "guid";
    private static final String COLUMN_PUB_DATE = "pub_date";
    private static final String COLUMN_ENCLOSURE = "enclosure";
    private static final String COLUMN_MIME_TYPE = "mime_type";
    private static final String COLUMN_RSS_FEED = "rss_feed";
    private static final String COLUMN_FAVORITE = "is_favorite";
    private static final String COLUMN_ARCHIVED = "is_archived";

    public String getInsertOrReplaceStatement() {
        return "INSERT OR REPLACE INTO " +getName() + " ("
                + COLUMN_ID + ", "
                + COLUMN_LINK + " ,"
                + COLUMN_TITLE + ", "
                + COLUMN_DESCRIPTION + " ,"
                + COLUMN_GUID + ", "
                + COLUMN_PUB_DATE + ", "
                + COLUMN_ENCLOSURE + ", "
                + COLUMN_MIME_TYPE + ", "
                + COLUMN_RSS_FEED + ", "
                + COLUMN_FAVORITE + ", "
                + COLUMN_ARCHIVED + ") VALUES (";

    }


    @Override
    public String getName() {
        return "rss_items";
    }

    @Override
    public String getCreateStatement() {
        return "CREATE TABLE " +getName() + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_LINK + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_GUID + " TEXT,"
                + COLUMN_PUB_DATE + " TEXT,"
                + COLUMN_ENCLOSURE + " TEXT,"
                + COLUMN_MIME_TYPE + " TEXT,"
                + COLUMN_RSS_FEED + " INTEGER,"
                + COLUMN_FAVORITE + " INTEGER DEFAULT 0,"
                + COLUMN_ARCHIVED + " INTEGER DEFAULT 1)";

    }
}
