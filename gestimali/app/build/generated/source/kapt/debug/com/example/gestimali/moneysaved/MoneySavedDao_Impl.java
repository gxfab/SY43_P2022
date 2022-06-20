package com.example.gestimali.moneysaved;

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
public final class MoneySavedDao_Impl implements MoneySavedDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MoneySaved> __insertionAdapterOfMoneySaved;

  public MoneySavedDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMoneySaved = new EntityInsertionAdapter<MoneySaved>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_money_saved` (`mon_year`,`mon_month`,`wis_id`,`mon_amount`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MoneySaved value) {
        stmt.bindLong(1, value.getMon_year());
        stmt.bindLong(2, value.getMon_month());
        stmt.bindLong(3, value.getWis_id());
        stmt.bindDouble(4, value.getMon_amount());
      }
    };
  }

  @Override
  public void addMoneySaved(final MoneySaved monSaved) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMoneySaved.insert(monSaved);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<MoneySaved>> readAllData() {
    final String _sql = "SELECT * FROM T_money_saved ORDER BY mon_year, mon_month,wis_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_money_saved"}, false, new Callable<List<MoneySaved>>() {
      @Override
      public List<MoneySaved> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMonYear = CursorUtil.getColumnIndexOrThrow(_cursor, "mon_year");
          final int _cursorIndexOfMonMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "mon_month");
          final int _cursorIndexOfWisId = CursorUtil.getColumnIndexOrThrow(_cursor, "wis_id");
          final int _cursorIndexOfMonAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "mon_amount");
          final List<MoneySaved> _result = new ArrayList<MoneySaved>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MoneySaved _item;
            final int _tmpMon_year;
            _tmpMon_year = _cursor.getInt(_cursorIndexOfMonYear);
            final int _tmpMon_month;
            _tmpMon_month = _cursor.getInt(_cursorIndexOfMonMonth);
            final int _tmpWis_id;
            _tmpWis_id = _cursor.getInt(_cursorIndexOfWisId);
            final float _tmpMon_amount;
            _tmpMon_amount = _cursor.getFloat(_cursorIndexOfMonAmount);
            _item = new MoneySaved(_tmpMon_year,_tmpMon_month,_tmpWis_id,_tmpMon_amount);
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
