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

public class ListPaymentLayout extends LinearLayout {
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

    public void addPayment(Payment payment) {
        LinearLayout layout = createPaymentLayout();
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;

        TextView category = new TextView(layout.getContext());
        category.setPadding(convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), 0, convertDpToPx(layout.getContext(), 10.0f));
        category.setLayoutParams(params);
        category.setTextSize(35);
        DatabaseHelper dbHelper = new DatabaseHelper(category.getContext());
        category.setText(""+dbHelper.getTypeCategory(payment.getCategory()).getNameCategory());
        dbHelper.closeDB();
        category.setTextColor(getResources().getColor(R.color.white));
        layout.addView(category);

        TextView amount = new TextView(layout.getContext());
        amount.setPadding(0, convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f));
        amount.setLayoutParams(params);
        amount.setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
        amount.setText(""+String.format("%.2f", payment.getAmount())+"€");
        amount.setTextColor(getResources().getColor(R.color.white));
        amount.setTextSize(35);

        layout.addView(amount);
        addView(layout);
    }

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

    private int convertDpToPx(Context context, float dp) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}
