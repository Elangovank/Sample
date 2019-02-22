package com.i.sample.app;


import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;

import com.i.sample.database.AppDatabase;


public class AppController extends Application {

   public static final String TAG = AppController.class.getSimpleName();
   private static AppController myInstance;
   private AppDatabase db;


   public static synchronized AppController getInstance() {
      return myInstance;
   }



   @Override
   public void onCreate() {
      super.onCreate();
      myInstance = this;


      Room.databaseBuilder( this, AppDatabase.class, "sample_db" )
              .fallbackToDestructiveMigration()
              .build();
   }




   static final Migration FROM_1_TO_2 = new Migration( 1, 2 ) {
      @Override
      public void migrate( final SupportSQLiteDatabase database ) {
         //database.execSQL( "ALTER TABLE Repo ADD COLUMN createdAt TEXT" );
      }
   };
}