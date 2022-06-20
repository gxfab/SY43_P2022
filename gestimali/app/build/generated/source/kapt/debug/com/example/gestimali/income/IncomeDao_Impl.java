package com.example.gestimali.income;

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
public final class IncomeDao_Impl implements IncomeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Income> __insertionAdapterOfIncome;

  public IncomeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIncome = new EntityInsertionAdapter<Income>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_income` (`inc_id`,`inc_year`,`inc_month`,`inc_name`,`inc_planned_amount`,`inc_real_amount`,`inc_day_transaction`,`inc_mensually_fixed`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Income value) {
        stmt.bindLong(1, value.getInc_id());
        stmt.bindLong(2, value.getInc_year());
        stmt.bindLong(3, value.getInc_month());
        if (value.getInc_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInc_name());
        }
        stmt.bindDouble(5, value.getInc_planned_amount());
        stmt.bindDouble(6, value.getInc_real_amount());
        stmt.bindLong(7, value.getInc_day_transaction());
        final int _tmp;
        _tmp = value.getInc_mensually_fixed() ? 1 : 0;
        stmt.bindLong(8, _tmp);
      }
    };
  }

  @Override
  public void addIncome(final Income income) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncome.insert(income);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Income>> readAllData() {
    final String _sql = "SELECT * FROM T_income ORDER BY inc_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_income"}, false, new Callable<List<Income>>() {
      @Override
      public List<Income> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIncId = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_id");
          final int _cursorIndexOfIncYear = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_year");
          final int _cursorIndexOfIncMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_month");
          final int _cursorIndexOfIncName = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_name");
          final int _cursorIndexOfIncPlannedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_planned_amount");
          final int _cursorIndexOfIncRealAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_real_amount");
          final int _cursorIndexOfIncDayTransaction = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_day_transaction");
          final int _cursorIndexOfIncMensuallyFixed = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_mensually_fixed");
          final List<Income> _result = new ArrayList<Income>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Income _item;
            final int _tmpInc_id;
            _tmpInc_id = _cursor.getInt(_cursorIndexOfIncId);
            final int _tmpInc_year;
            _tmpInc_year = _cursor.getInt(_cursorIndexOfIncYear);
            final int _tmpInc_month;
            _tmpInc_month = _cursor.getInt(_cursorIndexOfIncMonth);
            final String _tmpInc_name;
            _tmpInc_name = _cursor.getString(_cursorIndexOfIncName);
            final float _tmpInc_planned_amount;
            _tmpInc_planned_amount = _cursor.getFloat(_cursorIndexOfIncPlannedAmount);
            final float _tmpInc_real_amount;
            _tmpInc_real_amount = _cursor.getFloat(_cursorIndexOfIncRealAmount);
            final int _tmpInc_day_transaction;
            _tmpInc_day_transaction = _cursor.getInt(_cursorIndexOfIncDayTransaction);
            final boolean _tmpInc_mensually_fixed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIncMensuallyFixed);
            _tmpInc_mensually_fixed = _tmp != 0;
            _item = new Income(_tmpInc_id,_tmpInc_year,_tmpInc_month,_tmpInc_name,_tmpInc_planned_amount,_tmpInc_real_amount,_tmpInc_day_transaction,_tmpInc_mensually_fixed);
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

  @Override
  public LiveData<List<Income>> readAllDataFromMonth(final int month) {
    final String _sql = "SELECT * FROM T_income WHERE inc_month=? ORDER BY inc_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, month);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_income"}, false, new Callable<List<Income>>() {
      @Override
      public List<Income> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIncId = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_id");
          final int _cursorIndexOfIncYear = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_year");
          final int _cursorIndexOfIncMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_month");
          final int _cursorIndexOfIncName = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_name");
          final int _cursorIndexOfIncPlannedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_planned_amount");
          final int _cursorIndexOfIncRealAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_real_amount");
          final int _cursorIndexOfIncDayTransaction = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_day_transaction");
          final int _cursorIndexOfIncMensuallyFixed = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_mensually_fixed");
          final List<Income> _result = new ArrayList<Income>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Income _item;
            final int _tmpInc_id;
            _tmpInc_id = _cursor.getInt(_cursorIndexOfIncId);
            final int _tmpInc_year;
            _tmpInc_year = _cursor.getInt(_cursorIndexOfIncYear);
            final int _tmpInc_month;
            _tmpInc_month = _cursor.getInt(_cursorIndexOfIncMonth);
            final String _tmpInc_name;
            _tmpInc_name = _cursor.getString(_cursorIndexOfIncName);
            final float _tmpInc_planned_amount;
            _tmpInc_planned_amount = _cursor.getFloat(_cursorIndexOfIncPlannedAmount);
            final float _tmpInc_real_amount;
            _tmpInc_real_amount = _cursor.getFloat(_cursorIndexOfIncRealAmount);
            final int _tmpInc_day_transaction;
            _tmpInc_day_transaction = _cursor.getInt(_cursorIndexOfIncDayTransaction);
            final boolean _tmpInc_mensually_fixed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIncMensuallyFixed);
            _tmpInc_mensually_fixed = _tmp != 0;
            _item = new Income(_tmpInc_id,_tmpInc_year,_tmpInc_month,_tmpInc_name,_tmpInc_planned_amount,_tmpInc_real_amount,_tmpInc_day_transaction,_tmpInc_mensually_fixed);
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
