package com.example.gestimali.envelope_tags;

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
public final class EnvelopeTagsDao_Impl implements EnvelopeTagsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EnvelopeTags> __insertionAdapterOfEnvelopeTags;

  public EnvelopeTagsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEnvelopeTags = new EntityInsertionAdapter<EnvelopeTags>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_enveloppe_tags` (`env_id`,`tag_id`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EnvelopeTags value) {
        stmt.bindLong(1, value.getEnv_id());
        stmt.bindLong(2, value.getTag_id());
      }
    };
  }

  @Override
  public void addEnvelopeTags(final EnvelopeTags envTags) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEnvelopeTags.insert(envTags);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<EnvelopeTags>> readAllData() {
    final String _sql = "SELECT * FROM T_enveloppe_tags ORDER BY env_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_enveloppe_tags"}, false, new Callable<List<EnvelopeTags>>() {
      @Override
      public List<EnvelopeTags> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEnvId = CursorUtil.getColumnIndexOrThrow(_cursor, "env_id");
          final int _cursorIndexOfTagId = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_id");
          final List<EnvelopeTags> _result = new ArrayList<EnvelopeTags>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EnvelopeTags _item;
            final int _tmpEnv_id;
            _tmpEnv_id = _cursor.getInt(_cursorIndexOfEnvId);
            final int _tmpTag_id;
            _tmpTag_id = _cursor.getInt(_cursorIndexOfTagId);
            _item = new EnvelopeTags(_tmpEnv_id,_tmpTag_id);
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
