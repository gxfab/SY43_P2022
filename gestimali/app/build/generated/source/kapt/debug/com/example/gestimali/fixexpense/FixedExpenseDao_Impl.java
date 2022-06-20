package com.example.gestimali.fixexpense;

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
public final class FixedExpenseDao_Impl implements FixedExpenseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FixedExpense> __insertionAdapterOfFixedExpense;

  public FixedExpenseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFixedExpense = new EntityInsertionAdapter<FixedExpense>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_fixed_expense` (`exp_id`,`exp_year`,`exp_month`,`exp_name`,`exp_planned_amount`,`exp_real_amount`,`exp_day_transaction`,`exp_mensually_fix`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FixedExpense value) {
        stmt.bindLong(1, value.getExp_id());
        stmt.bindLong(2, value.getExp_year());
        stmt.bindLong(3, value.getExp_month());
        if (value.getExp_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExp_name());
        }
        stmt.bindDouble(5, value.getExp_planned_amount());
        stmt.bindDouble(6, value.getExp_real_amount());
        stmt.bindLong(7, value.getExp_day_transaction());
        final int _tmp;
        _tmp = value.getExp_mensually_fix() ? 1 : 0;
        stmt.bindLong(8, _tmp);
      }
    };
  }

  @Override
  public void addFixedExpense(final FixedExpense fixedExpense) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFixedExpense.insert(fixedExpense);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<FixedExpense>> readAllData() {
    final String _sql = "SELECT * FROM T_fixed_expense ORDER BY exp_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_fixed_expense"}, false, new Callable<List<FixedExpense>>() {
      @Override
      public List<FixedExpense> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfExpId = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_id");
          final int _cursorIndexOfExpYear = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_year");
          final int _cursorIndexOfExpMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_month");
          final int _cursorIndexOfExpName = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_name");
          final int _cursorIndexOfExpPlannedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_planned_amount");
          final int _cursorIndexOfExpRealAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_real_amount");
          final int _cursorIndexOfExpDayTransaction = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_day_transaction");
          final int _cursorIndexOfExpMensuallyFix = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_mensually_fix");
          final List<FixedExpense> _result = new ArrayList<FixedExpense>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FixedExpense _item;
            final int _tmpExp_id;
            _tmpExp_id = _cursor.getInt(_cursorIndexOfExpId);
            final int _tmpExp_year;
            _tmpExp_year = _cursor.getInt(_cursorIndexOfExpYear);
            final int _tmpExp_month;
            _tmpExp_month = _cursor.getInt(_cursorIndexOfExpMonth);
            final String _tmpExp_name;
            _tmpExp_name = _cursor.getString(_cursorIndexOfExpName);
            final float _tmpExp_planned_amount;
            _tmpExp_planned_amount = _cursor.getFloat(_cursorIndexOfExpPlannedAmount);
            final float _tmpExp_real_amount;
            _tmpExp_real_amount = _cursor.getFloat(_cursorIndexOfExpRealAmount);
            final int _tmpExp_day_transaction;
            _tmpExp_day_transaction = _cursor.getInt(_cursorIndexOfExpDayTransaction);
            final boolean _tmpExp_mensually_fix;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfExpMensuallyFix);
            _tmpExp_mensually_fix = _tmp != 0;
            _item = new FixedExpense(_tmpExp_id,_tmpExp_year,_tmpExp_month,_tmpExp_name,_tmpExp_planned_amount,_tmpExp_real_amount,_tmpExp_day_transaction,_tmpExp_mensually_fix);
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
