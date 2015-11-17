package io.bloc.android.blocly.api.model;

/**
 * Created by Kari on 11/16/2015.
 */
public abstract class Model {

    private final long rowId;

    public Model(long rowId) {
        this.rowId = rowId;
    }

    public long getRowId() {
        return rowId;
    }
}
