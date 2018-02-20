package edu.colostate.jaredboese.treetour;

import android.content.Context;

/**
 * Created by Jared Boese on 2/19/2018.
 */

public class Model implements AutoCloseable {
    public static class Lazy extends edu.colostate.jaredboese.treetour.Lazy < Model > {
        Lazy(Context context) {
            super(Model.class, context);
        }
    }

    Context mContext;
    Model(Context context) {
        mContext = context;
        mPrefs = new Prefs.Lazy(mContext);
    }

    Context context() { return mContext; }
    Prefs.Lazy mPrefs;
    Prefs prefs() { return mPrefs.self(); }



    @Override
    public void close() {
        mPrefs.close();
    }
}
