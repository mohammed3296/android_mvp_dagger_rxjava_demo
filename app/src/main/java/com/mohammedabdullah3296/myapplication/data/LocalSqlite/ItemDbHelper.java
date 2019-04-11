package com.mohammedabdullah3296.myapplication.data.LocalSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.mohammedabdullah3296.myapplication.data.LocalSqlite.governors.GovernorContract;
import com.mohammedabdullah3296.myapplication.data.LocalSqlite.jobs.JobContract;
import com.mohammedabdullah3296.myapplication.data.LocalSqlite.positions.PositionsContract;
import com.mohammedabdullah3296.myapplication.data.models.getGovernors.Governor;
import com.mohammedabdullah3296.myapplication.data.models.jops.Job;
import com.mohammedabdullah3296.myapplication.data.models.positions.PositionResponseDetails;

import java.util.ArrayList;
import java.util.List;

import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.governors.GovernorContract.ItemEntry.GOVERNOR_TABLE_NAME;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.governors.GovernorContract.ItemEntry.GOV_ID;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.governors.GovernorContract.ItemEntry.GOV_NAME;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.jobs.JobContract.ItemEntry.JOB_ID;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.jobs.JobContract.ItemEntry.JOB_TABLE_NAME;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.jobs.JobContract.ItemEntry.OFFICE_ID;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.jobs.JobContract.ItemEntry.PROJECT_ID;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.jobs.JobContract.ItemEntry.USER_ID;
import static com.mohammedabdullah3296.myapplication.data.LocalSqlite.positions.PositionsContract.ItemEntry.POSITION_TABLE_NAME;


public class ItemDbHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SurveyDB.db";
    private static final int VERSION = 1;
    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_GOVERNOR_TABLE = "CREATE TABLE "  + GOVERNOR_TABLE_NAME + " (" +
                GovernorContract.ItemEntry._ID                + " INTEGER PRIMARY KEY, " +
                GOV_ID + " TEXT , " +
                GOV_NAME + " TEXT );" ;

        db.execSQL(CREATE_GOVERNOR_TABLE);

        final String CREATE_POSITION_TABLE = "CREATE TABLE " + POSITION_TABLE_NAME + " (" +
                PositionsContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                PositionsContract.ItemEntry.position_id + " TEXT , " +
                PositionsContract.ItemEntry.project_id + " TEXT , " +
                PositionsContract.ItemEntry.position_name + " TEXT );";

        db.execSQL(CREATE_POSITION_TABLE);

        final String CREATE_JOB_TABLE = "CREATE TABLE " + JOB_TABLE_NAME + " (" +
                JobContract.ItemEntry._ID + " INTEGER PRIMARY KEY, " +
                JOB_ID + " TEXT , " +
                JobContract.ItemEntry.JOB_NAME + " TEXT , " +
                JobContract.ItemEntry.COUNT + " TEXT , " +
                USER_ID + " TEXT , " +
                PROJECT_ID + " TEXT , " +
                OFFICE_ID + " TEXT , " +
                JobContract.ItemEntry.NOTE + " TEXT );";
        db.execSQL(CREATE_JOB_TABLE);

    }

    public List<Governor>  getGovs() {

        String[] columns={GOV_ID , GOV_NAME};
        List<Governor>  governors = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor =  db.query(GOVERNOR_TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);


        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                Governor  governor = new Governor();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                governor.setGov_id( retCursor.getString(retCursor.getColumnIndex(GOV_ID)));
                governor.setGov_name( retCursor.getString(retCursor.getColumnIndex(GOV_NAME)));
                governors.add(governor);
            }
        }

        return  governors;
    }


    public void addPositions(List<PositionResponseDetails> positionResponseDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(PositionsContract.ItemEntry.position_id, "0");
            values.put(PositionsContract.ItemEntry.project_id, "0");
            values.put(PositionsContract.ItemEntry.position_name, "اختر الوظيفة");
            db.insert(POSITION_TABLE_NAME, null, values);
            for (PositionResponseDetails positionResponseDetails1 : positionResponseDetails) {
                values.put(PositionsContract.ItemEntry.position_id, positionResponseDetails1.getPosition_id());
                values.put(PositionsContract.ItemEntry.project_id, positionResponseDetails1.getProject_id());
                values.put(PositionsContract.ItemEntry.position_name, positionResponseDetails1.getPosition_name());


                db.insert(POSITION_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void addPosition(PositionResponseDetails positionResponseDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
                values.put(PositionsContract.ItemEntry.position_id, positionResponseDetails.getPosition_id());
                values.put(PositionsContract.ItemEntry.project_id, positionResponseDetails.getProject_id());
                values.put(PositionsContract.ItemEntry.position_name, positionResponseDetails.getPosition_name());
                db.insert(POSITION_TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public List<PositionResponseDetails>  getPositions() {
        List<PositionResponseDetails>  positionResponses = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor =  db.query(POSITION_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        if(retCursor!=null){
            while (retCursor.moveToNext()) {
                PositionResponseDetails  positionResponseDetails = new PositionResponseDetails();
                positionResponseDetails.setPosition_id( retCursor.getString(retCursor.getColumnIndex(PositionsContract.ItemEntry.position_id)));
                positionResponseDetails.setProject_id( retCursor.getString(retCursor.getColumnIndex(PositionsContract.ItemEntry.project_id)));
                positionResponseDetails.setPosition_name( retCursor.getString(retCursor.getColumnIndex(PositionsContract.ItemEntry.position_name)));
                positionResponses.add(positionResponseDetails);
            }
        }

        return  positionResponses;
    }

    // TODO End Office Logic
    public void addGovernors(ArrayList<Governor> governors) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(GOV_ID, 0);
            values.put(GOV_NAME, "اختر المحافظة");
            db.insert(GOVERNOR_TABLE_NAME, null, values);
            for (Governor governor : governors) {
                values.put(GOV_ID, governor.getGov_id());
                values.put(GOV_NAME, governor.getGov_name());
                db.insert(GOVERNOR_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<Governor> getGovernors() {
        ArrayList<Governor> governors = new ArrayList<>();
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(GOVERNOR_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                Governor governor = new Governor();
                governor.setGov_id(retCursor.getString(retCursor.getColumnIndex(GOV_ID)));
                governor.setGov_name(retCursor.getString(retCursor.getColumnIndex(GOV_NAME)));
                governors.add(governor);
            }
        }

        return governors;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + GOVERNOR_TABLE_NAME);
        onCreate(db);
    }

    public void deleteSavedGovernors()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from  " + GOVERNOR_TABLE_NAME);

    }

      public void deleteSavedPositionTable() {
          SQLiteDatabase db = this.getWritableDatabase();
          db.execSQL("delete from  " + POSITION_TABLE_NAME);
      }

    // By Mohammed Elamary
    public ArrayList<PositionResponseDetails> getCertainPosition(String projectId) throws SQLException {
        ArrayList<PositionResponseDetails> positions = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(true, POSITION_TABLE_NAME,
                new String[]{PositionsContract.ItemEntry.project_id,
                        PositionsContract.ItemEntry.position_id,
                        PositionsContract.ItemEntry.position_name},
                PositionsContract.ItemEntry.project_id + "=" + "1" + " or " + PositionsContract.ItemEntry.project_id + "=1",
                null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                PositionResponseDetails positionResponseDetails = new PositionResponseDetails();
                positionResponseDetails.setPosition_id(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.position_id)));
                positionResponseDetails.setPosition_name(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.position_name)));
                positionResponseDetails.setProject_id(
                        mCursor.getString(mCursor.getColumnIndex(PositionsContract.ItemEntry.project_id)));

                positions.add(positionResponseDetails);
            }
        }

        return positions;
    }
    // By Mohammed Elamary
    public void addJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(JOB_ID, job.getJob_id());
            values.put(JobContract.ItemEntry.JOB_NAME, job.getJob_name());
            values.put(JobContract.ItemEntry.COUNT, job.getCount());
            values.put(JobContract.ItemEntry.NOTE, job.getNote());
            values.put(OFFICE_ID, job.getOffice_id());
            values.put(PROJECT_ID, job.getProject_id());
            values.put(USER_ID, job.getUser_id());
            db.insert(JOB_TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    //By Mohammed Elamary
    public ArrayList<Job> getJobs(String project_id , String office_id , String user_id) {
        ArrayList<Job> jobs = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(JOB_TABLE_NAME,
                null,
                        PROJECT_ID + "=" + project_id + " and " + OFFICE_ID + "=" + office_id
                        + " and " + USER_ID + "=" + user_id,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                Job job = new Job();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                job.setJob_id(retCursor.getString(retCursor.getColumnIndex(JOB_ID)));
                job.setJob_name(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.JOB_NAME)));
                job.setCount(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.COUNT)));
                job.setNote(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.NOTE)));
                job.setProject_id(retCursor.getString(retCursor.getColumnIndex(PROJECT_ID)));
                job.setUser_id(retCursor.getString(retCursor.getColumnIndex(USER_ID)));
                job.setOffice_id(retCursor.getString(retCursor.getColumnIndex(OFFICE_ID)));
                jobs.add(job);
            }
        }

        return jobs;
    }
    public ArrayList<Job> getJobsByUserId(String user_id , String officeId) {
        ArrayList<Job> jobs = new ArrayList<>();
        final SQLiteDatabase db = this.getReadableDatabase();

        Cursor retCursor;
        retCursor = db.query(JOB_TABLE_NAME,
                null,
                USER_ID + " = " + user_id + " and " +OFFICE_ID + " = " + officeId,
                null,
                null,
                null,
                null);


        if (retCursor != null) {
            while (retCursor.moveToNext()) {
                Job job = new Job();
                // governor.setId(retCursor.getInt(retCursor.getColumnIndex(ItemContract.ItemEntry._ID)));
                job.setJob_id(retCursor.getString(retCursor.getColumnIndex(JOB_ID)));
                job.setJob_name(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.JOB_NAME)));
                job.setCount(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.COUNT)));
                job.setNote(retCursor.getString(retCursor.getColumnIndex(JobContract.ItemEntry.NOTE)));
                job.setProject_id(retCursor.getString(retCursor.getColumnIndex(PROJECT_ID)));
                job.setUser_id(retCursor.getString(retCursor.getColumnIndex(USER_ID)));
                job.setOffice_id(retCursor.getString(retCursor.getColumnIndex(OFFICE_ID)));
                jobs.add(job);
            }
        }

        return jobs;
    }
    //By Mohammed Elamary
    public boolean updateJob(String jobId , Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put(JOB_ID, job.getJob_id());

        args.put(JobContract.ItemEntry.JOB_NAME, job.getJob_name());

        args.put(JobContract.ItemEntry.NOTE, job.getNote());

        args.put(JobContract.ItemEntry.COUNT, job.getCount());

        args.put(PROJECT_ID, job.getProject_id());

        args.put(USER_ID, job.getUser_id());

        args.put(OFFICE_ID, job.getOffice_id());

        return db.update(JOB_TABLE_NAME, args, JOB_ID + " = " + jobId + " and " +
                PROJECT_ID + " = " + job.getProject_id() + " and " + OFFICE_ID + " = " + job.getOffice_id()
                + " and " + USER_ID + " = " + job.getUser_id(), null) > 0;

    }

    // By Mohammed Elamary
    public boolean deleteJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(JOB_TABLE_NAME, JOB_ID + " = " + job.getJob_id() + " and " +
                PROJECT_ID + " = " + job.getProject_id() + " and " + OFFICE_ID + " = " + job.getOffice_id()
                + " and " + USER_ID + " = " + job.getUser_id(), null) > 0;
    }
    //By Mohammed Elamary
    public boolean getACertainJob(String jobID, String officeID , String projectId ,String userId) {
        final SQLiteDatabase db = this.getReadableDatabase();
        Cursor retCursor;
        retCursor = db.query(JOB_TABLE_NAME,
                null,
                JOB_ID + " = " + jobID + " and "+ OFFICE_ID + " = " + officeID+ " and "+ PROJECT_ID + " = " + projectId+ " and "+ USER_ID + " = " + userId ,
                null,
                null,
                null,
                null);

        if (retCursor.getCount() > 0) {

            return true;
        } else {

            return false;
        }
    }

}