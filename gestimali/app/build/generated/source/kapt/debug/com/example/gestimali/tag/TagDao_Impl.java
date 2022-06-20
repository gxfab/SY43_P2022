package com.example.gestimali.tag;

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
public final class TagDao_Impl implements TagDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Tag> __insertionAdapterOfTag;

  public TagDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTag = new EntityInsertionAdapter<Tag>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `T_tag` (`tag_id`,`tag_name`,`tag_desc`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tag value) {
        stmt.bindLong(1, value.getTag_id());
        if (value.getTag_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTag_name());
        }
        if (value.getTag_desc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTag_desc());
        }
      }
    };
  }

  @Override
  public void addTag(final Tag tag) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTag.insert(tag);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Tag>> readAllData() {
    final String _sql = "SELECT * FROM T_tag ORDER BY tag_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"T_tag"}, false, new Callable<List<Tag>>() {
      @Override
      public List<Tag> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTagId = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_id");
          final int _cursorIndexOfTagName = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_name");
          final int _cursorIndexOfTagDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_desc");
          final List<Tag> _result = new ArrayList<Tag>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Tag _item;
            final int _tmpTag_id;
            _tmpTag_id = _cursor.getInt(_cursorIndexOfTagId);
            final String _tmpTag_name;
            _tmpTag_name = _cursor.getString(_cursorIndexOfTagName);
            final String _tmpTag_desc;
            _tmpTag_desc = _cursor.getString(_cursorIndexOfTagDesc);
            _item = new Tag(_tmpTag_id,_tmpTag_name,_tmpTag_desc);
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
