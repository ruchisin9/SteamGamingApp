package com.assignedpro.nj.steam.SqliteHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by logimetrix on 1/8/16.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME= Environment.getExternalStorageDirectory()+"";// put db name here
    private static final int DATABASE_VERSION=0;

    //private Dao<ScurvesTable, Long> scurvesTableLongDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //TableUtils.createTable(connectionSource,ScurvesTable.class);

        }catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //TableUtils.dropTable(connectionSource,ScurvesTable.class,true);

            onCreate(database,connectionSource);

            /*getTableRecentActivityDao().executeRaw("ALTER TABLE `issue_collection` ADD COLUMN age TEXT;");*/
        }catch(Exception e){e.printStackTrace();}
    }


//    public Dao<ProjectListData,Long> getProjectListDataDao() throws SQLException {
//        try {
//            if (projectListDataDao == null)
//                projectListDataDao = getDao(ProjectListData.class);
//            return projectListDataDao;
//        }catch(Exception e){e.printStackTrace();}
//        return projectListDataDao;
//    }



}
