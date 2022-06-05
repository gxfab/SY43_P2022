package fr.sy43.studzero.vue.layout;

import android.content.Context;
import android.content.Intent;
import android.text.PrecomputedText;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.sy43.studzero.R;
import fr.sy43.studzero.activities.HistoryStats;
import fr.sy43.studzero.activities.New_Budget_1;
import fr.sy43.studzero.activities.New_Budget_3;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.Payment;

/**
 * This class is used to show the list of old budgets
 * ListPaymentLayout inherits from LinearLayout
 */
public class HistoryLayout extends LinearLayout {
    /**
     * Constructor of the class
     * @param context used by the parent of the classed
     */
    public HistoryLayout(Context context) {
        super(context);
        setOrientation(VERTICAL);
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.bottomMargin = convertDpToPx(this.getContext(), 60);
        params.topMargin = convertDpToPx(this.getContext(), 20);
        params.leftMargin = convertDpToPx(this.getContext(), 20);
        params.rightMargin = convertDpToPx(this.getContext(), 20);
        setLayoutParams(params);
    }

    /**
     * Add a budget to the layout
     * @param budget
     */
    public LinearLayout addBudget(Budget budget) {
        LinearLayout layout = createBudgetLayout();
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;

        TextView amount = new TextView(layout.getContext());
        amount.setPadding(0, convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f));
        amount.setLayoutParams(params);
        amount.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        String dates = DatabaseHelper.getStringWithoutTimeFromDate(budget.getDateStart()) + " - " + DatabaseHelper.getStringWithoutTimeFromDate(budget.getDateEnd());
        amount.setText(dates);
        amount.setTextColor(getResources().getColor(R.color.white));
        amount.setTextSize(convertSpToPx(layout.getContext(), 10.0f));
        layout.addView(amount);
        addView(layout);
        return layout;
    }

    /**
     * Creates the layout for a budget
     * @return LinearLayout
     */
    private LinearLayout createBudgetLayout() {
        LinearLayout layout = new LinearLayout(this.getContext());
        layout.setOrientation(HORIZONTAL);
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.bottomMargin = convertDpToPx(this.getContext(), 30);
        layout.setLayoutParams(params);
        layout.setBackgroundColor(getResources().getColor(R.color.BG_Objet));
        return layout;
    }

    /**
     * Converts dp to pixels
     * @param context
     * @param dp
     * @return pixels
     */
    private int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    /**
     * Converts sp to pixels
     * @param context
     * @param sp
     * @return pixels
     */
    private int convertSpToPx(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
