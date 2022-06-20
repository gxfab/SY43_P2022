package com.example.gestimali.fix_expense_tags;

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
public final class FixedExpenseTagsDao_Impl implements FixedExpenseTagsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FixedExpenseTags> __insertionAdapterOfFixedExpenseTags;

  public FixedExpenseTagsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFixedExpenseTags = new EntityInsertionAdapter<FixedExpenseTags>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_fixed_expense_tags` (`exp_id`,`tag_id`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FixedExpenseTags value) {
        stmt.bindLong(1, value.getExp_id());
        stmt.bindLong(2, value.getTag_id());
      }
    };
  }

  @Override
  public void addFixedExpenseTag(final FixedExpenseTags expTag) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFixedExpenseTags.insert(expTag);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<FixedExpenseTags>> readAllData() {
    final String _sql = "SELECT * FROM T_fixed_expense_tags ORDER BY exp_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_fixed_expense_tags"}, false, new Callable<List<FixedExpenseTags>>() {
      @Override
      public List<FixedExpenseTags> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfExpId = CursorUtil.getColumnIndexOrThrow(_cursor, "exp_id");
          final int _cursorIndexOfTagId = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_id");
          final List<FixedExpenseTags> _result = new ArrayList<FixedExpenseTags>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FixedExpenseTags _item;
            final int _tmpExp_id;
            _tmpExp_id = _cursor.getInt(_cursorIndexOfExpId);
            final int _tmpTag_id;
            _tmpTag_id = _cursor.getInt(_cursorIndexOfTagId);
            _item = new FixedExpenseTags(_tmpExp_id,_tmpTag_id);
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
