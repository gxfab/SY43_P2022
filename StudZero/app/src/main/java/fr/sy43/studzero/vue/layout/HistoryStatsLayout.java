package fr.sy43.studzero.vue.layout;

import android.content.Context;
import android.widget.LinearLayout;

public class HistoryStatsLayout extends LinearLayout {
    private int idBudget;

    public HistoryStatsLayout(Context context, int idBudget) {
        super(context);
        this.idBudget = idBudget;
    }
}
