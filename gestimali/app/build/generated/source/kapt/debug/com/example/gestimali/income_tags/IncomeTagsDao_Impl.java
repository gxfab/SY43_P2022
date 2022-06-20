package com.example.gestimali.income_tags;

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
public final class IncomeTagsDao_Impl implements IncomeTagsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IncomeTags> __insertionAdapterOfIncomeTags;

  public IncomeTagsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIncomeTags = new EntityInsertionAdapter<IncomeTags>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_income_tags` (`inc_id`,`tag_id`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncomeTags value) {
        stmt.bindLong(1, value.getInc_id());
        stmt.bindLong(2, value.getTag_id());
      }
    };
  }

  @Override
  public void addIncomeTag(final IncomeTags incTags) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncomeTags.insert(incTags);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<IncomeTags>> readAllData() {
    final String _sql = "SELECT * FROM T_income_tags ORDER BY inc_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_income_tags"}, false, new Callable<List<IncomeTags>>() {
      @Override
      public List<IncomeTags> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIncId = CursorUtil.getColumnIndexOrThrow(_cursor, "inc_id");
          final int _cursorIndexOfTagId = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_id");
          final List<IncomeTags> _result = new ArrayList<IncomeTags>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final IncomeTags _item;
            final int _tmpInc_id;
            _tmpInc_id = _cursor.getInt(_cursorIndexOfIncId);
            final int _tmpTag_id;
            _tmpTag_id = _cursor.getInt(_cursorIndexOfTagId);
            _item = new IncomeTags(_tmpInc_id,_tmpTag_id);
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
