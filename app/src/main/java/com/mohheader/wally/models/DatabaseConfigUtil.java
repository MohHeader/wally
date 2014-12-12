package com.mohheader.wally.models;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Created by thedreamer on 12/12/14.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}