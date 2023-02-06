package hr.algebra.baza

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME =   "TasksDatabase"
const val DATABASE_VERSION = 1

const val TABLE_NAME = "TASK"
const val COLUMN_ID = "_ID"
const val COLUMN_NAME = "Name"
const val COLUMN_DESCRIPTION = "Description"

class BazaZadataka( context : Context ) :
    SQLiteOpenHelper( context, DATABASE_NAME, null, DATABASE_VERSION ) {

    override fun onCreate( db : SQLiteDatabase? ) {
        val SQL_CREATE = """CREATE TABLE "$TABLE_NAME" (
                            "$COLUMN_ID" INTEGER,
                            "$COLUMN_NAME" TEXT,
                            "$COLUMN_DESCRIPTION" TEXT,
                            PRIMARY KEY("$COLUMN_ID" AUTOINCREMENT)
                        );"""
        db?.execSQL( SQL_CREATE )

    }

    override fun onUpgrade( db: SQLiteDatabase?, oldVersion : Int, newVersion: Int ) {
        db?.execSQL( "DROP TABLE IF EXISTS $TABLE_NAME;" )
        onCreate( db )
    }

    fun insertTask( task : Task ) {
        val db = writableDatabase
        val contentValues = ContentValues( )
        contentValues.put( COLUMN_NAME, task.name )
        contentValues.put( COLUMN_DESCRIPTION, task.description )

        db.insert( TABLE_NAME, null, contentValues )

        db.close( )
    }

    fun deleteTask( task : Task ) {
        val db = writableDatabase

        db.delete( TABLE_NAME, "$COLUMN_ID=${ task.id }", null )
        // db.delete( TABLE_NAME, "1=1", null ) -> briše sve retke tablice
        // db.delete( TABLE_NAME, null, null ) -> briše sve retke tablice

        db.close( )
    }

    @SuppressLint("Range")
    fun getTasks( ) : List< Task > {
        val list = ArrayList< Task >( )
        val select = "SELECT $COLUMN_ID,$COLUMN_NAME,$COLUMN_DESCRIPTION FROM $TABLE_NAME"

        val db = readableDatabase

        val cursor = db.rawQuery( select, null )
        if( cursor.moveToFirst( ) ) {
            do {
/*
                list.add(
                    Task(   cursor.getInt( 1 ),    //ID
                        cursor.getString( 2 ),     //Name
                        cursor.getString( 3 )      //Description
                    )
                )

 */
                list.add(
                    Task(   cursor.getInt( cursor.getColumnIndex( COLUMN_ID ) ),           //ID
                            cursor.getString( cursor.getColumnIndex( COLUMN_NAME ) ),      //Name
                            cursor.getString( cursor.getColumnIndex( COLUMN_DESCRIPTION ) ) //Description
                    )
                )
            } while ( cursor.moveToNext( ) )
        }
        db.close( )
        return list
    }
}