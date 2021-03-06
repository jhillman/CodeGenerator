package com.example.model;   
 
import android.content.ContentValues;
import android.database.Cursor;
 
import android.os.Parcel;
import android.os.Parcelable;
    
import java.util.ArrayList;
import java.util.List;
  
import com.example.database.table.PersonTable; 
 
import java.util.Set;
  
public class Person implements Parcelable {
    private transient long mRowId;
    private String mName;  
    private int mAge;  
    private boolean mAlive;  
    private double mBodyFat;  
    private long mIdPlace;  
  
    private transient ContentValues mValues = new ContentValues(); 
    public Person() {}  
  
    public Person(final Cursor cursor) {
        this(cursor, false, null);
    }
 
    public Person(final Cursor cursor, Set<String> fields) {
        this(cursor, false, fields);
    }
 
    public Person(final Cursor cursor, boolean prependTableName, Set<String> fields) {
        String prefix = prependTableName ? PersonTable.TABLE_NAME.replace("`", "") + "_" : "";
 
        if (shouldSet(cursor, fields, prefix + PersonTable._ID)) { 
            setRowId(cursor.getLong(cursor.getColumnIndex(prefix + PersonTable._ID)));
        } 
        if (shouldSet(cursor, fields, prefix + PersonTable.NAME)) {
            setName(cursor.getString(cursor.getColumnIndex(prefix + PersonTable.NAME))); 
        } 
        if (shouldSet(cursor, fields, prefix + PersonTable.AGE)) {
            setAge(cursor.getInt(cursor.getColumnIndex(prefix + PersonTable.AGE))); 
        } 
        if (shouldSet(cursor, fields, prefix + PersonTable.ALIVE)) {
            setAlive(cursor.getInt(cursor.getColumnIndex(prefix + PersonTable.ALIVE)) != 0); 
        } 
        if (shouldSet(cursor, fields, prefix + PersonTable.BODY_FAT)) {
            setBodyFat(cursor.getDouble(cursor.getColumnIndex(prefix + PersonTable.BODY_FAT))); 
        } 
        if (shouldSet(cursor, fields, prefix + PersonTable.ID_PLACE)) {
            setIdPlace(cursor.getLong(cursor.getColumnIndex(prefix + PersonTable.ID_PLACE))); 
        } 
    }
    
    private boolean shouldSet(Cursor cursor, Set<String> fields, String field) {
        return cursor.getColumnIndex(field) != -1 && !cursor.isNull(cursor.getColumnIndex(field)) && (fields == null || fields.contains(field));
    }
 
    public Person(Parcel parcel) {
        mRowId = parcel.readLong();
 
        setName(parcel.readString()); 
 
        setAge(parcel.readInt()); 
 
        setAlive(parcel.readInt() == 1); 
 
        setBodyFat(parcel.readDouble()); 
 
        setIdPlace(parcel.readLong()); 
    }
 
    @Override
    public int describeContents() {
        return 0;
    }
 
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(mRowId);
 
        parcel.writeString(getName()); 
 
        parcel.writeInt(getAge()); 
 
        parcel.writeInt(getAlive() ? 1 : 0); 
 
        parcel.writeDouble(getBodyFat()); 
 
        parcel.writeLong(getIdPlace()); 
    }
 
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }
 
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
  
    public final void setRowId(long _id) {
        mRowId = _id;
        mValues.put(PersonTable._ID, _id);
    }
 
    public final void setName(String name) {
        mName = name;
        mValues.put(PersonTable.NAME, name); 
    }
 
    public final void setAge(int age) {
        mAge = age;
        mValues.put(PersonTable.AGE, age); 
    }
 
    public final void setAlive(boolean alive) {
        mAlive = alive;
        mValues.put(PersonTable.ALIVE, alive); 
    }
 
    public final void setBodyFat(double bodyFat) {
        mBodyFat = bodyFat;
        mValues.put(PersonTable.BODY_FAT, bodyFat); 
    }
 
    public final void setIdPlace(long idPlace) {
        mIdPlace = idPlace;
        mValues.put(PersonTable.ID_PLACE, idPlace); 
    }
  
    public long getRowId() {
        return mRowId;
    }
 
    public String getName() {
        return mName;
    }
 
    public int getAge() {
        return mAge;
    }
 
    public boolean getAlive() {
        return mAlive;
    }
 
    public double getBodyFat() {
        return mBodyFat;
    }
 
    public long getIdPlace() {
        return mIdPlace;
    }
  
    public ContentValues getContentValues() {
        return mValues;
    }
  
    public static List<Person> listFromCursor(Cursor cursor, Set<String> fields) {
        List<Person> list = new ArrayList<Person>();
 
        if (cursor != null && cursor.moveToFirst()) {
            do {
                list.add(new Person(cursor, fields));
            } while (cursor.moveToNext());
        }
 
        return list;
    }
  
    public static List<Person> listFromCursor(Cursor cursor) {
        return listFromCursor(cursor, null);
    }
 
    // BEGIN PERSISTED SECTION - put custom methods here

    // END PERSISTED SECTION 
}