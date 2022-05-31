package fr.sy43.studzero.vue.layout;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Category;
import fr.sy43.studzero.vue.view.CategoryProgressBar;

/**
 * Layout used for the home page view
 */
public class HomeLayout extends LinearLayout {
    /**
     * Constructor of the layout
     * @param context used by the parent class
     */
    public HomeLayout(Context context) {
        super(context);
        DatabaseHelper db = new DatabaseHelper(context);
        // Set basics parameters of the layout
        setOrientation(VERTICAL);
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.bottomMargin = convertDpToPx(this.getContext(), 60);
        params.topMargin = convertDpToPx(this.getContext(), 20);
        params.leftMargin = convertDpToPx(this.getContext(), 20);
        params.rightMargin = convertDpToPx(this.getContext(), 20);
        setLayoutParams(params);


        // Add text at the top of le layout
        // Remaining balance text
        params.height = LayoutParams.WRAP_CONTENT;
        params.bottomMargin = convertDpToPx(this.getContext(), 10);
        params.topMargin = convertDpToPx(this.getContext(), 10);
        params.leftMargin = 0;
        params.rightMargin = 0;
        TextView remainingBalance = new TextView(this.getContext());
        remainingBalance.setLayoutParams(params);
        remainingBalance.setText(context.getString(R.string.RemainingBalance));
        remainingBalance.setTextColor(getResources().getColor(R.color.white));
        remainingBalance.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        remainingBalance.setTextSize(convertSpToPx(this.getContext(), 10.0f));


        // Remaining balance amount text
        params.bottomMargin = convertDpToPx(this.getContext(), 0);
        params.topMargin = convertDpToPx(this.getContext(), 10);
        TextView remainingBalanceAmount = new TextView(this.getContext());
        remainingBalanceAmount.setLayoutParams(params);
        float remainingBalanceValue = db.getRemainingAmountOfCurrentBudget();
        remainingBalanceAmount.setText(String.format("%.2f", remainingBalanceValue)+"€");
        if(remainingBalanceValue < 0.f) {
            remainingBalanceAmount.setTextColor(getResources().getColor(R.color.red));
        } else {
            remainingBalanceAmount.setTextColor(getResources().getColor(R.color.white));
        }
        remainingBalanceAmount.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        remainingBalanceAmount.setTextSize(convertSpToPx(this.getContext(), 10.0f));

        // Remaining days text
        params.topMargin = convertDpToPx(this.getContext(), 0);
        params.bottomMargin = convertDpToPx(this.getContext(), 0);
        TextView remainingDays = new TextView(this.getContext());
        remainingDays.setLayoutParams(params);
        remainingDays.setText(context.getString(R.string.RemainingDays));
        remainingDays.setTextColor(getResources().getColor(R.color.white));
        remainingDays.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        remainingDays.setTextSize(convertSpToPx(this.getContext(), 10.0f));

        // Remaining days amount text
        params.bottomMargin = convertDpToPx(this.getContext(), 40);
        TextView numberRemainingDays = new TextView(this.getContext());
        numberRemainingDays.setLayoutParams(params);
        numberRemainingDays.setText(""+db.getRemainingDaysOfCurrentBudget());
        numberRemainingDays.setTextColor(getResources().getColor(R.color.white));
        numberRemainingDays.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        numberRemainingDays.setTextSize(convertSpToPx(this.getContext(), 10.0f));

        // Add views to the layout
        addView(remainingBalance);
        addView(remainingBalanceAmount);
        addView(remainingDays);
        addView(numberRemainingDays);

        db.closeDB();
    }

    /**
     * Add a category to the layout
     * @param category
     */
    public void addCategory(Category category) {
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = convertDpToPx(getContext(), 40);
        params.leftMargin = convertDpToPx(getContext(), 20);
        params.rightMargin = convertDpToPx(getContext(), 20);
        params.bottomMargin = convertDpToPx(getContext(), 30);
        CategoryProgressBar categoryProgressBar = new CategoryProgressBar(this.getContext(), null, category);
        categoryProgressBar.setLayoutParams(params);
        categoryProgressBar.setBackgroundColor(getResources().getColor(R.color.BG_Objet));
        addView(categoryProgressBar);
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
