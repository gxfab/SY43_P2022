package fr.sy43.studzero.vue.layout;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Budget;
import fr.sy43.studzero.sqlite.model.Category;
import fr.sy43.studzero.sqlite.model.CategoryType;
import fr.sy43.studzero.sqlite.model.User;
import fr.sy43.studzero.vue.view.CategoryGraph;
import fr.sy43.studzero.vue.view.CategoryProgressBar;

/**
 * This class is used to show the stats of a previous budget
 * ListPaymentLayout inherits from LinearLayout
 */
public class PreviousBudgetStatsLayout extends LinearLayout {

    /**
     * Constructor of the class.
     * The constructor adds all the necessary views for the stats of a previous budget
     * @param context
     * @param idBudget id of the preivous budget
     */
    public PreviousBudgetStatsLayout(Context context, int idBudget) {
        super(context);
        setOrientation(VERTICAL);
        // Parameters of the layout
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        params.topMargin = convertDpToPx(this.getContext(), 20);
        params.leftMargin = convertDpToPx(this.getContext(), 20);
        params.rightMargin = convertDpToPx(this.getContext(), 20);
        setLayoutParams(params);

        // Retrieve data from database
        DatabaseHelper db = new DatabaseHelper(getContext());
        Budget budget = db.getBudget(idBudget);
        List<Category> categories = db.getAllCategories(budget.getIdBudget());
        List<CategoryType> categoryTypes = db.getAllCategoriesTypesOfBudget(budget.getIdBudget());
        String[] categoryNames = new String[categoryTypes.size()];
        for(int i = 0; i < categoryTypes.size(); ++i) {
            categoryNames[i] = categoryTypes.get(i).getNameCategory();
        }
        db.closeDB();

        // Estimated total amount view
        LinearLayout estimatedTotal = createTotalMoneyLayout(getResources().getString(R.string.EstimatedTotal), budget.getBudgetAmount());
        addView(estimatedTotal);

        // Real total amount view
        float sumRealAmountBudget = 0f;
        for(int i = 0; i < categories.size(); ++i) {
            sumRealAmountBudget += categories.get(i).getRealAmount();
        }
        LinearLayout realTotal = createTotalMoneyLayout(getResources().getString(R.string.RealTotal), sumRealAmountBudget);
        addView(realTotal);

        // Add progress bars for every category of the budget
        params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = convertDpToPx(getContext(), 40);
        params.bottomMargin = convertDpToPx(getContext(), 30);

        for(int i = 0; i < categories.size(); ++i) {
            CategoryProgressBar categoryProgressBar = new CategoryProgressBar(this.getContext(), null, categories.get(i));
            categoryProgressBar.setLayoutParams(params);
            categoryProgressBar.setBackgroundColor(getResources().getColor(R.color.BG_Objet));
            addView(categoryProgressBar);
        }

        // TextView for R.string.SelectCategory
        TextView selectCategoryTextView = new TextView(getContext());
        params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;
        params.bottomMargin = convertDpToPx(getContext(), 10);
        selectCategoryTextView.setPadding(convertDpToPx(getContext(), 10.0f), convertDpToPx(getContext(), 10.0f), 0, convertDpToPx(getContext(), 10.0f));
        selectCategoryTextView.setLayoutParams(params);
        selectCategoryTextView.setTextSize(convertSpToPx(getContext(), 8.0f));
        selectCategoryTextView.setText(getResources().getString(R.string.SelectCategory));
        selectCategoryTextView.setTextColor(getResources().getColor(R.color.white));
        addView(selectCategoryTextView);

        // Add a spinner to select a category for the afterwards graph
        Spinner spinner = new Spinner(getContext());
        params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.bottomMargin = convertDpToPx(getContext(), 10);
        spinner.setLayoutParams(params);
        spinner.setPadding(convertDpToPx(getContext(), 10.0f), convertDpToPx(getContext(), 10.0f), 0, convertDpToPx(getContext(), 10.0f));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.liste_deroulante_selected_item, categoryNames);
        adapter.setDropDownViewResource(R.layout.liste_deroulante);
        spinner.setAdapter(adapter);
        spinner.setBackgroundColor(getResources().getColor(R.color.BG_Objet));
        addView(spinner);

        // TextView for R.string.EvolutionPayments
        TextView evolutionPayments = new TextView(getContext());
        params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;
        params.bottomMargin = convertDpToPx(getContext(), 10);
        evolutionPayments.setPadding(convertDpToPx(getContext(), 10.0f), convertDpToPx(getContext(), 10.0f), 0, convertDpToPx(getContext(), 10.0f));
        evolutionPayments.setLayoutParams(params);
        evolutionPayments.setTextSize(convertSpToPx(getContext(), 8.0f));
        evolutionPayments.setText(getResources().getString(R.string.EvolutionPayments));
        evolutionPayments.setTextColor(getResources().getColor(R.color.white));
        evolutionPayments.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        addView(evolutionPayments);

        // Add a category graph to the layout
        CategoryGraph categoryGraph = new CategoryGraph(getContext(), db.getCategoryOfCategoryType(db.getCurrentBudget().getIdBudget(), categoryNames[0]));
        categoryGraph.setBackgroundColor(getResources().getColor(R.color.BG_Objet));
        params = generateDefaultLayoutParams();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = convertDpToPx(getContext(), 400);
        params.bottomMargin = convertDpToPx(getContext(), 30);
        categoryGraph.setLayoutParams(params);
        addView(categoryGraph);

        // Add listener to spinner to update the graph depending on the selected category
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Update the selected type of category and show the category of the selected category
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DatabaseHelper db = new DatabaseHelper(getContext());
                categoryGraph.setNewCategory(db.getCategoryOfCategoryType(db.getBudget(idBudget).getIdBudget(), categoryNames[position]));
                db.getCurrentBudget();
            }
            /**
             * If no category is selected : do nothing
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Creates the layout to show the estimated and the real total of a budget
     * @return LinearLayout
     */
    private LinearLayout createTotalMoneyLayout(String titleName, float amount) {
        LinearLayout layout = new LinearLayout(this.getContext());
        // Parameters of the new layout
        layout.setOrientation(HORIZONTAL);
        LayoutParams params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.bottomMargin = convertDpToPx(this.getContext(), 30);
        layout.setLayoutParams(params);
        layout.setBackgroundColor(getResources().getColor(R.color.BG_Objet));

        // Parameters for the text views
        params = generateDefaultLayoutParams();
        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1f;

        // Left textview of the layout that contains the title of the layout
        TextView titleText = new TextView(layout.getContext());
        titleText.setPadding(convertDpToPx(layout.getContext(), 10.0f), convertDpToPx(layout.getContext(), 10.0f), 0, convertDpToPx(layout.getContext(), 10.0f));
        titleText.setLayoutParams(params);
        titleText.setTextSize(convertSpToPx(layout.getContext(), 10.0f));
        titleText.setText(titleName);
        titleText.setTextColor(getResources().getColor(R.color.white));
        layout.addView(titleText);

        // Right textview of the layout that contains the amount of the layout
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
