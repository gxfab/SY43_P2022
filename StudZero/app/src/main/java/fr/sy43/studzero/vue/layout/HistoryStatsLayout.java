package fr.sy43.studzero.vue.layout;

import android.content.Context;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.User;

public class HistoryStatsLayout extends LinearLayout {
    private int idBudget;

    public HistoryStatsLayout(Context context, int idBudget) {
        super(context);
        setOrientation(VERTICAL);
        this.idBudget = idBudget;
        // Parameters of the layout
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.topMargin = convertDpToPx(this.getContext(), 20);
        params.leftMargin = convertDpToPx(this.getContext(), 20);
        params.rightMargin = convertDpToPx(this.getContext(), 20);
        setLayoutParams(params);
        DatabaseHelper db = new DatabaseHelper(getContext());
        Budget budget = db.getBudget(idBudget);
        db.closeDB();
        // Estimated total amount view
        LinearLayout estimatedTotal = createTotalMoneyLayout(getResources().getString(R.string.EstimatedTotal), budget.getBudgetAmount());
        // Real total amount view
        LinearLayout realTotal = createTotalMoneyLayout(getResources().getString(R.string.EstimatedTotal), budget.getBudgetAmount());

        addView(estimatedTotal);
        addView(realTotal);
    }

    /**
     * Creates the layout to show the estimated and the real total of a budget
     * @return LinearLayout
     */
    private LinearLayout createTotalMoneyLayout(String titleName, float amount) {
        LinearLayout layout = new LinearLayout(this.getContext());
        layout.setOrientation(HORIZONTAL);
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.bottomMargin = convertDpToPx(this.getContext(), 30);
        layout.setLayoutParams(params);
        layout.setBackgroundColor(getResources().getColor(R.color.BG_Objet));

        params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;
        TextView titleText = new TextView(layout.getContext());
        titleText.setPadding(convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), 0, convertDpToPx(layout.getContext(), 10.0f));
        titleText.setLayoutParams(params);
        titleText.setTextSize(convertSpToPx(layout.getContext(), 10.0f));
        titleText.setText(titleName);
        titleText.setTextColor(getResources().getColor(R.color.white));
        layout.addView(titleText);

        TextView amountText = new TextView(layout.getContext());
        amountText.setPadding(0, convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f));
        amountText.setLayoutParams(params);
        amountText.setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
        amountText.setText(String.format("%.2f", amount)+"€");
        amountText.setTextColor(getResources().getColor(R.color.white));
        amountText.setTextSize(convertSpToPx(layout.getContext(), 10.0f));
        layout.addView(amountText);

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
