package com.example.gestimali.envelope;

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
public final class EnvelopeDao_Impl implements EnvelopeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Envelope> __insertionAdapterOfEnvelope;

  public EnvelopeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEnvelope = new EntityInsertionAdapter<Envelope>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `T_envelope` (`env_id`,`env_year`,`env_month`,`env_name`,`env_planned_amount`,`env_money_used`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Envelope value) {
        stmt.bindLong(1, value.getEnv_id());
        stmt.bindLong(2, value.getEnv_year());
        stmt.bindLong(3, value.getEnv_month());
        if (value.getEnv_name() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEnv_name());
        }
        stmt.bindDouble(5, value.getEnv_planned_amount());
        stmt.bindDouble(6, value.getEnv_money_used());
      }
    };
  }

  @Override
  public void addEnvelope(final Envelope envelope) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEnvelope.insert(envelope);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Envelope>> readAllData() {
    final String _sql = "SELECT * FROM T_envelope ORDER BY env_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_envelope"}, false, new Callable<List<Envelope>>() {
      @Override
      public List<Envelope> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEnvId = CursorUtil.getColumnIndexOrThrow(_cursor, "env_id");
          final int _cursorIndexOfEnvYear = CursorUtil.getColumnIndexOrThrow(_cursor, "env_year");
          final int _cursorIndexOfEnvMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "env_month");
          final int _cursorIndexOfEnvName = CursorUtil.getColumnIndexOrThrow(_cursor, "env_name");
          final int _cursorIndexOfEnvPlannedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "env_planned_amount");
          final int _cursorIndexOfEnvMoneyUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "env_money_used");
          final List<Envelope> _result = new ArrayList<Envelope>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Envelope _item;
            final int _tmpEnv_id;
            _tmpEnv_id = _cursor.getInt(_cursorIndexOfEnvId);
            final int _tmpEnv_year;
            _tmpEnv_year = _cursor.getInt(_cursorIndexOfEnvYear);
            final int _tmpEnv_month;
            _tmpEnv_month = _cursor.getInt(_cursorIndexOfEnvMonth);
            final String _tmpEnv_name;
            _tmpEnv_name = _cursor.getString(_cursorIndexOfEnvName);
            final float _tmpEnv_planned_amount;
            _tmpEnv_planned_amount = _cursor.getFloat(_cursorIndexOfEnvPlannedAmount);
            final float _tmpEnv_money_used;
            _tmpEnv_money_used = _cursor.getFloat(_cursorIndexOfEnvMoneyUsed);
            _item = new Envelope(_tmpEnv_id,_tmpEnv_year,_tmpEnv_month,_tmpEnv_name,_tmpEnv_planned_amount,_tmpEnv_money_used);
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
