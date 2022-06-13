package com.example.gestimali.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gestimali.envelope.Envelope
import com.example.gestimali.envelope.EnvelopeDao
import com.example.gestimali.envelope_tags.EnvelopeTags
import com.example.gestimali.envelope_tags.EnvelopeTagsDao
import com.example.gestimali.fix_expense_tags.FixedExpenseTags
import com.example.gestimali.fix_expense_tags.FixedExpenseTagsDao
import com.example.gestimali.fixexpense.FixedExpense
import com.example.gestimali.fixexpense.FixedExpenseDao
import com.example.gestimali.income.Income
import com.example.gestimali.income.IncomeDao
import com.example.gestimali.income_tags.IncomeTags
import com.example.gestimali.income_tags.IncomeTagsDao
import com.example.gestimali.moneysaved.MoneySaved
import com.example.gestimali.moneysaved.MoneySavedDao
import com.example.gestimali.tag.Tag
import com.example.gestimali.tag.TagDao
import com.example.gestimali.transaction.Transaction
import com.example.gestimali.transaction.TransactionDao
import com.example.gestimali.wish.Wish
import com.example.gestimali.wish.WishDao

@Database(entities = [Envelope::class,FixedExpense::class, Income::class, MoneySaved::class,Tag::class,Transaction::class,Wish::class,EnvelopeTags::class,FixedExpenseTags::class,IncomeTags::class],version = 1, exportSchema = false)
abstract class BudgetDatabase : RoomDatabase() {

    abstract fun envelopeDao() : EnvelopeDao
    abstract fun envelopeTagsDao() : EnvelopeTagsDao
    abstract fun fixedExpenseTagsDao() : FixedExpenseTagsDao
    abstract fun fixedExpenseDao() : FixedExpenseDao
    abstract fun incomeDao() : IncomeDao
    abstract fun incomeTagsDao() : IncomeTagsDao
    abstract fun moneySavedDao() : MoneySavedDao
    abstract fun tagDao() : TagDao
    abstract fun transactionDao() : TransactionDao
    abstract fun wishDao() : WishDao

    companion object {
        @Volatile
        private var INSTANCE : BudgetDatabase? = null

        fun getDatabase(context : Context) :BudgetDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}