package fr.sy43.studzero.vue.layout;

import android.content.Context;
import android.text.PrecomputedText;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Payment;

/**
 * This class is used the show a list of payments.
 * ListPaymentLayout inherits from LinearLayout
 */
public class ListPaymentLayout extends LinearLayout {
    /**
     * Constructor of the class
     * @param context
     */
    public ListPaymentLayout(Context context) {
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
     * Add a payment to the layout
     * @param payment payment to add to the layout
     */
    public void addPayment(Payment payment) {
        LinearLayout layout = createPaymentLayout();
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;

        TextView category = new TextView(layout.getContext());
        category.setPadding(convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), 0, convertDpToPx(layout.getContext(), 10.0f));
        category.setLayoutParams(params);
        category.setTextSize(convertSpToPx(layout.getContext(), 10.0f));
        DatabaseHelper dbHelper = new DatabaseHelper(category.getContext());
        category.setText(""+dbHelper.getTypeCategory(payment.getCategory()).getNameCategory());
        dbHelper.closeDB();
        category.setTextColor(getResources().getColor(R.color.white));
        layout.addView(category);

        TextView amount = new TextView(layout.getContext());
        amount.setPadding(0, convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f));
        amount.setLayoutParams(params);
        amount.setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
        amount.setText(String.format("%.2f", payment.getAmount())+"€");
        amount.setTextColor(getResources().getColor(R.color.white));
        amount.setTextSize(convertSpToPx(layout.getContext(), 10.0f));

        layout.addView(amount);
        addView(layout);
    }

    /**
     * Creates the layout for a payment
     * @return LinearLayout
     */
    private LinearLayout createPaymentLayout() {
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
