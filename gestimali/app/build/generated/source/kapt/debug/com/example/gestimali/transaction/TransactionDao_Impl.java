package com.example.gestimali.transaction;

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
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  public TransactionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_transaction` (`env_id`,`tra_id`,`tra_amount`,`tra_date`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Transaction value) {
        stmt.bindLong(1, value.getEnv_id());
        stmt.bindLong(2, value.getTra_id());
        stmt.bindDouble(3, value.getTra_amount());
        stmt.bindLong(4, value.getTra_date());
      }
    };
  }

  @Override
  public void addTransaction(final Transaction transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransaction.insert(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Transaction>> readAllData() {
    final String _sql = "SELECT * FROM T_transaction ORDER BY tra_id,env_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_transaction"}, false, new Callable<List<Transaction>>() {
      @Override
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEnvId = CursorUtil.getColumnIndexOrThrow(_cursor, "env_id");
          final int _cursorIndexOfTraId = CursorUtil.getColumnIndexOrThrow(_cursor, "tra_id");
          final int _cursorIndexOfTraAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "tra_amount");
          final int _cursorIndexOfTraDate = CursorUtil.getColumnIndexOrThrow(_cursor, "tra_date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpEnv_id;
            _tmpEnv_id = _cursor.getInt(_cursorIndexOfEnvId);
            final int _tmpTra_id;
            _tmpTra_id = _cursor.getInt(_cursorIndexOfTraId);
            final float _tmpTra_amount;
            _tmpTra_amount = _cursor.getFloat(_cursorIndexOfTraAmount);
            final int _tmpTra_date;
            _tmpTra_date = _cursor.getInt(_cursorIndexOfTraDate);
            _item = new Transaction(_tmpEnv_id,_tmpTra_id,_tmpTra_amount,_tmpTra_date);
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
