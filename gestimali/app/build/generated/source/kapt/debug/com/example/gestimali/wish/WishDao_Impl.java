package com.example.gestimali.wish;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WishDao_Impl implements WishDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Wish> __insertionAdapterOfWish;

  public WishDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWish = new EntityInsertionAdapter<Wish>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_wish` (`wis_id`,`wis_name`,`wis_amount_needed`,`wis_amount_collected`,`wis_purchased`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Wish value) {
        stmt.bindLong(1, value.getWis_id());
        if (value.getWis_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getWis_name());
        }
        stmt.bindDouble(3, value.getWis_amount_needed());
        stmt.bindDouble(4, value.getWis_amount_collected());
        final int _tmp;
        _tmp = value.getWis_purchased() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
  }

  @Override
  public void addWish(final Wish wish) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWish.insert(wish);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Wish>> readAllData() {
    final String _sql = "SELECT * FROM T_wish ORDER BY wis_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_wish"}, false, new Callable<List<Wish>>() {
      @Override
      public List<Wish> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWisId = CursorUtil.getColumnIndexOrThrow(_cursor, "wis_id");
          final int _cursorIndexOfWisName = CursorUtil.getColumnIndexOrThrow(_cursor, "wis_name");
          final int _cursorIndexOfWisAmountNeeded = CursorUtil.getColumnIndexOrThrow(_cursor, "wis_amount_needed");
          final int _cursorIndexOfWisAmountCollected = CursorUtil.getColumnIndexOrThrow(_cursor, "wis_amount_collected");
          final int _cursorIndexOfWisPurchased = CursorUtil.getColumnIndexOrThrow(_cursor, "wis_purchased");
          final List<Wish> _result = new ArrayList<Wish>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Wish _item;
            final int _tmpWis_id;
            _tmpWis_id = _cursor.getInt(_cursorIndexOfWisId);
            final String _tmpWis_name;
            _tmpWis_name = _cursor.getString(_cursorIndexOfWisName);
            final float _tmpWis_amount_needed;
            _tmpWis_amount_needed = _cursor.getFloat(_cursorIndexOfWisAmountNeeded);
            final float _tmpWis_amount_collected;
            _tmpWis_amount_collected = _cursor.getFloat(_cursorIndexOfWisAmountCollected);
            final boolean _tmpWis_purchased;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfWisPurchased);
            _tmpWis_purchased = _tmp != 0;
            _item = new Wish(_tmpWis_id,_tmpWis_name,_tmpWis_amount_needed,_tmpWis_amount_collected,_tmpWis_purchased);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
