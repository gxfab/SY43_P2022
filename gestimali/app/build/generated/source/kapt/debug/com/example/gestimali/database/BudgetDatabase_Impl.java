package com.example.gestimali.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.gestimali.envelope.EnvelopeDao;
import com.example.gestimali.envelope.EnvelopeDao_Impl;
import com.example.gestimali.envelope_tags.EnvelopeTagsDao;
import com.example.gestimali.envelope_tags.EnvelopeTagsDao_Impl;
import com.example.gestimali.fix_expense_tags.FixedExpenseTagsDao;
import com.example.gestimali.fix_expense_tags.FixedExpenseTagsDao_Impl;
import com.example.gestimali.fixexpense.FixedExpenseDao;
import com.example.gestimali.fixexpense.FixedExpenseDao_Impl;
import com.example.gestimali.income.IncomeDao;
import com.example.gestimali.income.IncomeDao_Impl;
import com.example.gestimali.income_tags.IncomeTagsDao;
import com.example.gestimali.income_tags.IncomeTagsDao_Impl;
import com.example.gestimali.moneysaved.MoneySavedDao;
import com.example.gestimali.moneysaved.MoneySavedDao_Impl;
import com.example.gestimali.tag.TagDao;
import com.example.gestimali.tag.TagDao_Impl;
import com.example.gestimali.transaction.TransactionDao;
import com.example.gestimali.transaction.TransactionDao_Impl;
import com.example.gestimali.wish.WishDao;
import com.example.gestimali.wish.WishDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BudgetDatabase_Impl extends BudgetDatabase {
  private volatile EnvelopeDao _envelopeDao;

  private volatile EnvelopeTagsDao _envelopeTagsDao;

  private volatile FixedExpenseTagsDao _fixedExpenseTagsDao;

  private volatile FixedExpenseDao _fixedExpenseDao;

  private volatile IncomeDao _incomeDao;

  private volatile IncomeTagsDao _incomeTagsDao;

  private volatile MoneySavedDao _moneySavedDao;

  private volatile TagDao _tagDao;

  private volatile TransactionDao _transactionDao;

  private volatile WishDao _wishDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_envelope` (`env_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `env_year` INTEGER NOT NULL, `env_month` INTEGER NOT NULL, `env_name` TEXT NOT NULL, `env_planned_amount` REAL NOT NULL, `env_money_used` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_fixed_expense` (`exp_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `exp_year` INTEGER NOT NULL, `exp_month` INTEGER NOT NULL, `exp_name` TEXT NOT NULL, `exp_planned_amount` REAL NOT NULL, `exp_real_amount` REAL NOT NULL, `exp_day_transaction` INTEGER NOT NULL, `exp_mensually_fix` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_income` (`inc_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `inc_year` INTEGER NOT NULL, `inc_month` INTEGER NOT NULL, `inc_name` TEXT NOT NULL, `inc_planned_amount` REAL NOT NULL, `inc_real_amount` REAL NOT NULL, `inc_day_transaction` INTEGER NOT NULL, `inc_mensually_fixed` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_money_saved` (`mon_year` INTEGER NOT NULL, `mon_month` INTEGER NOT NULL, `wis_id` INTEGER NOT NULL, `mon_amount` REAL NOT NULL, PRIMARY KEY(`mon_year`, `mon_month`, `wis_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_tag` (`tag_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tag_name` TEXT NOT NULL, `tag_desc` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_transaction` (`env_id` INTEGER NOT NULL, `tra_id` INTEGER NOT NULL, `tra_amount` REAL NOT NULL, `tra_date` INTEGER NOT NULL, PRIMARY KEY(`env_id`, `tra_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_wish` (`wis_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `wis_name` TEXT NOT NULL, `wis_amount_needed` REAL NOT NULL, `wis_amount_collected` REAL NOT NULL, `wis_purchased` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_enveloppe_tags` (`env_id` INTEGER NOT NULL, `tag_id` INTEGER NOT NULL, PRIMARY KEY(`env_id`, `tag_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_fixed_expense_tags` (`exp_id` INTEGER NOT NULL, `tag_id` INTEGER NOT NULL, PRIMARY KEY(`exp_id`, `tag_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `T_income_tags` (`inc_id` INTEGER NOT NULL, `tag_id` INTEGER NOT NULL, PRIMARY KEY(`inc_id`, `tag_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd38af3401ed276a6a7ebdcbddee1ad27')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `T_envelope`");
        _db.execSQL("DROP TABLE IF EXISTS `T_fixed_expense`");
        _db.execSQL("DROP TABLE IF EXISTS `T_income`");
        _db.execSQL("DROP TABLE IF EXISTS `T_money_saved`");
        _db.execSQL("DROP TABLE IF EXISTS `T_tag`");
        _db.execSQL("DROP TABLE IF EXISTS `T_transaction`");
        _db.execSQL("DROP TABLE IF EXISTS `T_wish`");
        _db.execSQL("DROP TABLE IF EXISTS `T_enveloppe_tags`");
        _db.execSQL("DROP TABLE IF EXISTS `T_fixed_expense_tags`");
        _db.execSQL("DROP TABLE IF EXISTS `T_income_tags`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTEnvelope = new HashMap<String, TableInfo.Column>(6);
        _columnsTEnvelope.put("env_id", new TableInfo.Column("env_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTEnvelope.put("env_year", new TableInfo.Column("env_year", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTEnvelope.put("env_month", new TableInfo.Column("env_month", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTEnvelope.put("env_name", new TableInfo.Column("env_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTEnvelope.put("env_planned_amount", new TableInfo.Column("env_planned_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTEnvelope.put("env_money_used", new TableInfo.Column("env_money_used", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTEnvelope = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTEnvelope = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTEnvelope = new TableInfo("T_envelope", _columnsTEnvelope, _foreignKeysTEnvelope, _indicesTEnvelope);
        final TableInfo _existingTEnvelope = TableInfo.read(_db, "T_envelope");
        if (! _infoTEnvelope.equals(_existingTEnvelope)) {
          return new RoomOpenHelper.ValidationResult(false, "T_envelope(com.example.gestimali.envelope.Envelope).\n"
                  + " Expected:\n" + _infoTEnvelope + "\n"
                  + " Found:\n" + _existingTEnvelope);
        }
        final HashMap<String, TableInfo.Column> _columnsTFixedExpense = new HashMap<String, TableInfo.Column>(8);
        _columnsTFixedExpense.put("exp_id", new TableInfo.Column("exp_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpense.put("exp_year", new TableInfo.Column("exp_year", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpense.put("exp_month", new TableInfo.Column("exp_month", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpense.put("exp_name", new TableInfo.Column("exp_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpense.put("exp_planned_amount", new TableInfo.Column("exp_planned_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpense.put("exp_real_amount", new TableInfo.Column("exp_real_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpense.put("exp_day_transaction", new TableInfo.Column("exp_day_transaction", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpense.put("exp_mensually_fix", new TableInfo.Column("exp_mensually_fix", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTFixedExpense = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTFixedExpense = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTFixedExpense = new TableInfo("T_fixed_expense", _columnsTFixedExpense, _foreignKeysTFixedExpense, _indicesTFixedExpense);
        final TableInfo _existingTFixedExpense = TableInfo.read(_db, "T_fixed_expense");
        if (! _infoTFixedExpense.equals(_existingTFixedExpense)) {
          return new RoomOpenHelper.ValidationResult(false, "T_fixed_expense(com.example.gestimali.fixexpense.FixedExpense).\n"
                  + " Expected:\n" + _infoTFixedExpense + "\n"
                  + " Found:\n" + _existingTFixedExpense);
        }
        final HashMap<String, TableInfo.Column> _columnsTIncome = new HashMap<String, TableInfo.Column>(8);
        _columnsTIncome.put("inc_id", new TableInfo.Column("inc_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncome.put("inc_year", new TableInfo.Column("inc_year", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncome.put("inc_month", new TableInfo.Column("inc_month", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncome.put("inc_name", new TableInfo.Column("inc_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncome.put("inc_planned_amount", new TableInfo.Column("inc_planned_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncome.put("inc_real_amount", new TableInfo.Column("inc_real_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncome.put("inc_day_transaction", new TableInfo.Column("inc_day_transaction", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncome.put("inc_mensually_fixed", new TableInfo.Column("inc_mensually_fixed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTIncome = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTIncome = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTIncome = new TableInfo("T_income", _columnsTIncome, _foreignKeysTIncome, _indicesTIncome);
        final TableInfo _existingTIncome = TableInfo.read(_db, "T_income");
        if (! _infoTIncome.equals(_existingTIncome)) {
          return new RoomOpenHelper.ValidationResult(false, "T_income(com.example.gestimali.income.Income).\n"
                  + " Expected:\n" + _infoTIncome + "\n"
                  + " Found:\n" + _existingTIncome);
        }
        final HashMap<String, TableInfo.Column> _columnsTMoneySaved = new HashMap<String, TableInfo.Column>(4);
        _columnsTMoneySaved.put("mon_year", new TableInfo.Column("mon_year", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTMoneySaved.put("mon_month", new TableInfo.Column("mon_month", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTMoneySaved.put("wis_id", new TableInfo.Column("wis_id", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTMoneySaved.put("mon_amount", new TableInfo.Column("mon_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTMoneySaved = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTMoneySaved = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTMoneySaved = new TableInfo("T_money_saved", _columnsTMoneySaved, _foreignKeysTMoneySaved, _indicesTMoneySaved);
        final TableInfo _existingTMoneySaved = TableInfo.read(_db, "T_money_saved");
        if (! _infoTMoneySaved.equals(_existingTMoneySaved)) {
          return new RoomOpenHelper.ValidationResult(false, "T_money_saved(com.example.gestimali.moneysaved.MoneySaved).\n"
                  + " Expected:\n" + _infoTMoneySaved + "\n"
                  + " Found:\n" + _existingTMoneySaved);
        }
        final HashMap<String, TableInfo.Column> _columnsTTag = new HashMap<String, TableInfo.Column>(3);
        _columnsTTag.put("tag_id", new TableInfo.Column("tag_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTTag.put("tag_name", new TableInfo.Column("tag_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTTag.put("tag_desc", new TableInfo.Column("tag_desc", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTTag = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTTag = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTTag = new TableInfo("T_tag", _columnsTTag, _foreignKeysTTag, _indicesTTag);
        final TableInfo _existingTTag = TableInfo.read(_db, "T_tag");
        if (! _infoTTag.equals(_existingTTag)) {
          return new RoomOpenHelper.ValidationResult(false, "T_tag(com.example.gestimali.tag.Tag).\n"
                  + " Expected:\n" + _infoTTag + "\n"
                  + " Found:\n" + _existingTTag);
        }
        final HashMap<String, TableInfo.Column> _columnsTTransaction = new HashMap<String, TableInfo.Column>(4);
        _columnsTTransaction.put("env_id", new TableInfo.Column("env_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTTransaction.put("tra_id", new TableInfo.Column("tra_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTTransaction.put("tra_amount", new TableInfo.Column("tra_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTTransaction.put("tra_date", new TableInfo.Column("tra_date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTTransaction = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTTransaction = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTTransaction = new TableInfo("T_transaction", _columnsTTransaction, _foreignKeysTTransaction, _indicesTTransaction);
        final TableInfo _existingTTransaction = TableInfo.read(_db, "T_transaction");
        if (! _infoTTransaction.equals(_existingTTransaction)) {
          return new RoomOpenHelper.ValidationResult(false, "T_transaction(com.example.gestimali.transaction.Transaction).\n"
                  + " Expected:\n" + _infoTTransaction + "\n"
                  + " Found:\n" + _existingTTransaction);
        }
        final HashMap<String, TableInfo.Column> _columnsTWish = new HashMap<String, TableInfo.Column>(5);
        _columnsTWish.put("wis_id", new TableInfo.Column("wis_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTWish.put("wis_name", new TableInfo.Column("wis_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTWish.put("wis_amount_needed", new TableInfo.Column("wis_amount_needed", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTWish.put("wis_amount_collected", new TableInfo.Column("wis_amount_collected", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTWish.put("wis_purchased", new TableInfo.Column("wis_purchased", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTWish = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTWish = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTWish = new TableInfo("T_wish", _columnsTWish, _foreignKeysTWish, _indicesTWish);
        final TableInfo _existingTWish = TableInfo.read(_db, "T_wish");
        if (! _infoTWish.equals(_existingTWish)) {
          return new RoomOpenHelper.ValidationResult(false, "T_wish(com.example.gestimali.wish.Wish).\n"
                  + " Expected:\n" + _infoTWish + "\n"
                  + " Found:\n" + _existingTWish);
        }
        final HashMap<String, TableInfo.Column> _columnsTEnveloppeTags = new HashMap<String, TableInfo.Column>(2);
        _columnsTEnveloppeTags.put("env_id", new TableInfo.Column("env_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTEnveloppeTags.put("tag_id", new TableInfo.Column("tag_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTEnveloppeTags = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTEnveloppeTags = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTEnveloppeTags = new TableInfo("T_enveloppe_tags", _columnsTEnveloppeTags, _foreignKeysTEnveloppeTags, _indicesTEnveloppeTags);
        final TableInfo _existingTEnveloppeTags = TableInfo.read(_db, "T_enveloppe_tags");
        if (! _infoTEnveloppeTags.equals(_existingTEnveloppeTags)) {
          return new RoomOpenHelper.ValidationResult(false, "T_enveloppe_tags(com.example.gestimali.envelope_tags.EnvelopeTags).\n"
                  + " Expected:\n" + _infoTEnveloppeTags + "\n"
                  + " Found:\n" + _existingTEnveloppeTags);
        }
        final HashMap<String, TableInfo.Column> _columnsTFixedExpenseTags = new HashMap<String, TableInfo.Column>(2);
        _columnsTFixedExpenseTags.put("exp_id", new TableInfo.Column("exp_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTFixedExpenseTags.put("tag_id", new TableInfo.Column("tag_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTFixedExpenseTags = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTFixedExpenseTags = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTFixedExpenseTags = new TableInfo("T_fixed_expense_tags", _columnsTFixedExpenseTags, _foreignKeysTFixedExpenseTags, _indicesTFixedExpenseTags);
        final TableInfo _existingTFixedExpenseTags = TableInfo.read(_db, "T_fixed_expense_tags");
        if (! _infoTFixedExpenseTags.equals(_existingTFixedExpenseTags)) {
          return new RoomOpenHelper.ValidationResult(false, "T_fixed_expense_tags(com.example.gestimali.fix_expense_tags.FixedExpenseTags).\n"
                  + " Expected:\n" + _infoTFixedExpenseTags + "\n"
                  + " Found:\n" + _existingTFixedExpenseTags);
        }
        final HashMap<String, TableInfo.Column> _columnsTIncomeTags = new HashMap<String, TableInfo.Column>(2);
        _columnsTIncomeTags.put("inc_id", new TableInfo.Column("inc_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTIncomeTags.put("tag_id", new TableInfo.Column("tag_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTIncomeTags = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTIncomeTags = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTIncomeTags = new TableInfo("T_income_tags", _columnsTIncomeTags, _foreignKeysTIncomeTags, _indicesTIncomeTags);
        final TableInfo _existingTIncomeTags = TableInfo.read(_db, "T_income_tags");
        if (! _infoTIncomeTags.equals(_existingTIncomeTags)) {
          return new RoomOpenHelper.ValidationResult(false, "T_income_tags(com.example.gestimali.income_tags.IncomeTags).\n"
                  + " Expected:\n" + _infoTIncomeTags + "\n"
                  + " Found:\n" + _existingTIncomeTags);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d38af3401ed276a6a7ebdcbddee1ad27", "73fa038b2c130088492386aa92514361");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "T_envelope","T_fixed_expense","T_income","T_money_saved","T_tag","T_transaction","T_wish","T_enveloppe_tags","T_fixed_expense_tags","T_income_tags");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `T_envelope`");
      _db.execSQL("DELETE FROM `T_fixed_expense`");
      _db.execSQL("DELETE FROM `T_income`");
      _db.execSQL("DELETE FROM `T_money_saved`");
      _db.execSQL("DELETE FROM `T_tag`");
      _db.execSQL("DELETE FROM `T_transaction`");
      _db.execSQL("DELETE FROM `T_wish`");
      _db.execSQL("DELETE FROM `T_enveloppe_tags`");
      _db.execSQL("DELETE FROM `T_fixed_expense_tags`");
      _db.execSQL("DELETE FROM `T_income_tags`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public EnvelopeDao envelopeDao() {
    if (_envelopeDao != null) {
      return _envelopeDao;
    } else {
      synchronized(this) {
        if(_envelopeDao == null) {
          _envelopeDao = new EnvelopeDao_Impl(this);
        }
        return _envelopeDao;
      }
    }
  }

  @Override
  public EnvelopeTagsDao envelopeTagsDao() {
    if (_envelopeTagsDao != null) {
      return _envelopeTagsDao;
    } else {
      synchronized(this) {
        if(_envelopeTagsDao == null) {
          _envelopeTagsDao = new EnvelopeTagsDao_Impl(this);
        }
        return _envelopeTagsDao;
      }
    }
  }

  @Override
  public FixedExpenseTagsDao fixedExpenseTagsDao() {
    if (_fixedExpenseTagsDao != null) {
      return _fixedExpenseTagsDao;
    } else {
      synchronized(this) {
        if(_fixedExpenseTagsDao == null) {
          _fixedExpenseTagsDao = new FixedExpenseTagsDao_Impl(this);
        }
        return _fixedExpenseTagsDao;
      }
    }
  }

  @Override
  public FixedExpenseDao fixedExpenseDao() {
    if (_fixedExpenseDao != null) {
      return _fixedExpenseDao;
    } else {
      synchronized(this) {
        if(_fixedExpenseDao == null) {
          _fixedExpenseDao = new FixedExpenseDao_Impl(this);
        }
        return _fixedExpenseDao;
      }
    }
  }

  @Override
  public IncomeDao incomeDao() {
    if (_incomeDao != null) {
      return _incomeDao;
    } else {
      synchronized(this) {
        if(_incomeDao == null) {
          _incomeDao = new IncomeDao_Impl(this);
        }
        return _incomeDao;
      }
    }
  }

  @Override
  public IncomeTagsDao incomeTagsDao() {
    if (_incomeTagsDao != null) {
      return _incomeTagsDao;
    } else {
      synchronized(this) {
        if(_incomeTagsDao == null) {
          _incomeTagsDao = new IncomeTagsDao_Impl(this);
        }
        return _incomeTagsDao;
      }
    }
  }

  @Override
  public MoneySavedDao moneySavedDao() {
    if (_moneySavedDao != null) {
      return _moneySavedDao;
    } else {
      synchronized(this) {
        if(_moneySavedDao == null) {
          _moneySavedDao = new MoneySavedDao_Impl(this);
        }
        return _moneySavedDao;
      }
    }
  }

  @Override
  public TagDao tagDao() {
    if (_tagDao != null) {
      return _tagDao;
    } else {
      synchronized(this) {
        if(_tagDao == null) {
          _tagDao = new TagDao_Impl(this);
        }
        return _tagDao;
      }
    }
  }

  @Override
  public TransactionDao transactionDao() {
    if (_transactionDao != null) {
      return _transactionDao;
    } else {
      synchronized(this) {
        if(_transactionDao == null) {
          _transactionDao = new TransactionDao_Impl(this);
        }
        return _transactionDao;
      }
    }
  }

  @Override
  public WishDao wishDao() {
    if (_wishDao != null) {
      return _wishDao;
    } else {
      synchronized(this) {
        if(_wishDao == null) {
          _wishDao = new WishDao_Impl(this);
        }
        return _wishDao;
      }
    }
  }
}
