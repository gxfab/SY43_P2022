package fr.sy43.studzero.vue.layout;

import android.content.Context;
import android.text.PrecomputedText;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.model.Payment;

public class ListPaymentLayout extends LinearLayout {
    public ListPaymentLayout(Context context) {
        super(context);
        setOrientation(VERTICAL);
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.bottomMargin = 45;
        params.topMargin = 20;
        params.leftMargin = 20;
        params.rightMargin = 20;
        // setBackgroundColor(getResources().getColor(R.color.white));
        setLayoutParams(params);
    }

    public void addPayment(Payment payment) {
        LinearLayout layout = createPaymentLayout();
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;

        TextView category = new TextView(layout.getContext());

        category.setLayoutParams(params);
        category.setTextSize(30);
        category.setText(""+payment.getCategory());
        category.setTextColor(getResources().getColor(R.color.white));
        layout.addView(category);

        TextView amount = new TextView(layout.getContext());
        amount.setLayoutParams(params);
        amount.setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
        amount.setText(""+payment.getAmount());
        amount.setTextColor(getResources().getColor(R.color.white));
        amount.setTextSize(30);


        layout.addView(amount);
        addView(layout);
    }

    private LinearLayout createPaymentLayout() {
        LinearLayout layout = new LinearLayout(this.getContext());
        layout.setOrientation(HORIZONTAL);
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.bottomMargin = 20;
        layout.setLayoutParams(params);
        layout.setBackgroundColor(getResources().getColor(R.color.BG_Objet));
        return layout;
    }
}
