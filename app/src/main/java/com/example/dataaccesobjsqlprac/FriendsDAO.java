package com.example.dataaccesobjsqlprac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FriendsDAO {
    private DBHandler dBHandler;
    private SQLiteDatabase db;

    public FriendsDAO(Context context) {
        dBHandler = new DBHandler(context);
    }

    public void open() throws SQLException {
        db = dBHandler.getWritableDatabase();
    }

    public void close() {dBHandler.close();}

    public void deleteTable(){
        db.delete(DBHandler.TABLE_FRIENDS, null, null);
    }

    void addFriend(Friend friend) {
        ContentValues values = new ContentValues();
        values.put(DBHandler.KEY_FNAME,
                friend.getFirstName());
        values.put(DBHandler.KEY_LNAME,
                friend.getLastName());
        values.put(DBHandler.KEY_EMAIL,
                friend.getEmail());
        db.insert(DBHandler.TABLE_FRIENDS, null, values);
    }
    Friend getFriend(int id) {
        Cursor cursor = db.query(DBHandler.TABLE_FRIENDS,
                new String[] {DBHandler.KEY_ID,
                        DBHandler.KEY_FNAME, DBHandler.KEY_LNAME,
                        DBHandler.KEY_EMAIL },
                DBHandler.KEY_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        Friend friend = new Friend(Integer.parseInt(
                cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
        return friend;
    }
    public List<Friend> getAllFriends() {
        List<Friend> friendList = new ArrayList<Friend>();
        String selectQuery = "SELECT  * FROM " +
                DBHandler.TABLE_FRIENDS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Friend friend = new Friend();
                friend.setId(Integer.parseInt(cursor.getString(0)));
                friend.setFirstName(cursor.getString(1));
                friend.setLastName(cursor.getString(2));
                friend.setEmail(cursor.getString(3));
                friendList.add(friend);
            } while (cursor.moveToNext());
        }
        return friendList;
    }
    public int updateContact(Friend friend) {
        ContentValues values = new ContentValues();
        values.put(DBHandler.KEY_FNAME, friend.getFirstName());
        values.put(DBHandler.KEY_LNAME, friend.getLastName());
        values.put(DBHandler.KEY_EMAIL, friend.getEmail());
        return db.update(DBHandler.TABLE_FRIENDS, values,
                DBHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(friend.getId()) });
    }
    public void deleteContact(Friend friend) {
        db.delete(DBHandler.TABLE_FRIENDS, DBHandler.KEY_ID +
                "= ?", new String[] { String.valueOf(friend.getId()) });
    }
    public int getFriendsCount() {
        String countQuery = "SELECT  * FROM "
                + DBHandler.TABLE_FRIENDS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public void deleteFriends(){
        db.execSQL("delete from " +
                DBHandler.TABLE_FRIENDS);
    }
}