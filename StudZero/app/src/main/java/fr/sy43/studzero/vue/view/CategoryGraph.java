package fr.sy43.studzero.vue.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Date;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Category;
import fr.sy43.studzero.sqlite.model.Payment;

/**
 * View that draws a graph for a category
 */
public class CategoryGraph extends View {
    /**
     * Variable that is used to paint the graph
     */
    private Paint paint;
    /**
     * Category used for the graph
     */
    private Category category;
    /**
     * Variable that stores the resources of the app
     */
    private Resources resources;
    /**
     * Hash map that links the sum of payments done by day x to an int that represents a day of the budget
     */
    private LinkedHashMap<Integer, Float> sumPaymentsPerDay;
    /**
     * Ration between the real amount and the theoretical amount of money spend on the category
     */
    private float ratioRealTheoreticalAmount;

    /**
     * True if the category used for the graph is not attached to the current budget
     */
    private boolean isNotCurrentBudget;

    /**
     * If no payments have been done for the category used by the graph,
     * the variable corresponds to the number of days that have passed since the start of the budget.
     * Otherwise = -1
     */
    private int daysWithoutPayment;

    /**
     * Constructor the view
     * @param context
     * @param category category used for the drawn graph
     */
    public CategoryGraph(Context context, Category category) {
        super(context);
        this.paint = new Paint();
        this.resources = this.getResources();
        DatabaseHelper db = new DatabaseHelper(getContext());
        this.isNotCurrentBudget = category.getBudget() != db.getCurrentBudget().getIdBudget();
        db.closeDB();
        setGraphData(category);
    }

    /**
     * Sets the value of attributes used for to draw the curves of the graph
     * @param category
     */
    private void setGraphData(Category category) {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<Payment>payments = db.getAllPaymentsOfCategory(category.getIdCategory());
        sumPaymentsPerDay = new LinkedHashMap<>();
        this.category = category;

        if(payments.size() > 0) {
            long diffBudgetStart =  payments.get(0).getDatePayment().getTime() - db.getBudget(category.getBudget()).getDateStart().getTime();
            int numberOfDaysBetweenBudgetStartAnd1stPayment =  1 + (int) TimeUnit.DAYS.convert(diffBudgetStart, TimeUnit.MILLISECONDS);
            for(int i = 0; i < numberOfDaysBetweenBudgetStartAnd1stPayment; ++i)  {
                sumPaymentsPerDay.put(i, 0f);
            }
            sumPaymentsPerDay.put(numberOfDaysBetweenBudgetStartAnd1stPayment, payments.get(0).getAmount());

            int day = 1;
            for(int i = 1; i < payments.size(); ++i) {
                if(!new String(DatabaseHelper.getStringWithoutTimeFromDate(payments.get(i - 1).getDatePayment())).equals(DatabaseHelper.getStringWithoutTimeFromDate(payments.get(i).getDatePayment()))) {
                    long diff = payments.get(i).getDatePayment().getTime() - payments.get(i - 1).getDatePayment().getTime();
                    int numberOfDaysBetween2Dates = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    for (int j = 1; j <= numberOfDaysBetween2Dates; ++j) {
                        day++;
                        sumPaymentsPerDay.put(day, sumPaymentsPerDay.get(day - 1));
                    }
                }
                sumPaymentsPerDay.put(day, sumPaymentsPerDay.get(day) + payments.get(i).getAmount());
            }
            if(day > 1) {
                sumPaymentsPerDay.put(day + 1, -1f);
            } else {
                sumPaymentsPerDay.put(numberOfDaysBetweenBudgetStartAnd1stPayment + 1, -1f);
            }

            if(category.getTheoreticalAmount() > category.getRealAmount()) {
                this.ratioRealTheoreticalAmount = 1;
            } else {
                this.ratioRealTheoreticalAmount = category.getTheoreticalAmount() / category.getRealAmount();
            }
            this.daysWithoutPayment = -1;
        } else {
            this.ratioRealTheoreticalAmount = 1;
            long diffBudgetStart =  (new Date().getTime()) - db.getBudget(category.getBudget()).getDateStart().getTime();
            this.daysWithoutPayment = (int) TimeUnit.DAYS.convert(diffBudgetStart, TimeUnit.MILLISECONDS);
        }

        db.closeDB();
    }

    /**
     * Set a new category to be used for the graph + draws the graph of the new category
     * @param category
     */
    public void setNewCategory(Category category) {
        setGraphData(category);
        this.invalidate();
    }

    /**
     * Draw the graph of a category
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(5f);

        // Draw the titles of the axes
        this.paint.setColor(this.resources.getColor(R.color.white));
        this.paint.setTextSize(40f);
        this.paint.setStrokeWidth(0.5f);
        float xGraphYTitle = 0.05f * this.getWidth();
        float yGraphYTitle = 0.1f * this.getHeight();
        float yGraphXTitle = this.getHeight() * 0.7f;
        canvas.drawText(getContext().getString(R.string.GraphYAxisTitle), xGraphYTitle, yGraphYTitle, this.paint);
        float widthText = this.getWidth() - this.paint.measureText(getContext().getString(R.string.GraphXAxisTitle));
        canvas.drawText(getContext().getString(R.string.GraphXAxisTitle), widthText-this.getWidth()*0.02f, yGraphXTitle, this.paint);

        // Coordinates of the x and y axes
        this.paint.setStrokeWidth(5f);
        float yStartYAxis = this.getHeight() * 0.15f;
        float xValueYAxis = this.getWidth() * 0.1f;
        float xEndXAxis = widthText - this.getWidth()*0.1f;
        // Draw the lines of the x and y axes
        // y axis
        canvas.drawLine( xValueYAxis,  yStartYAxis,  xValueYAxis,  yGraphXTitle, this.paint);
        // x axis
        canvas.drawLine( xValueYAxis,  yGraphXTitle,  xEndXAxis,  yGraphXTitle, this.paint);

        // Draw every 5 days on the x axis
        this.paint.setStrokeWidth(0.5f);
        for(int i = 0; i <= 30; i+=5) {
            float xDayI = xValueYAxis + (xEndXAxis - xValueYAxis) * i/30;
            canvas.drawText(""+i, xDayI, yGraphXTitle * 1.1f, paint);
        }

        // Draw the legends of the graph
        this.paint.setStrokeWidth(5f);
        this.paint.setColor(this.resources.getColor(R.color.teal_200));
        float graphLegendTheoreticalAmountXStart = this.getWidth() * 0.1f;
        float graphLegendTheoreticalAmountXEnd = this.getWidth() * 0.3f;
        float graphLegendTheoreticalAmountY = this.getHeight() * 0.85f;
        canvas.drawLine(graphLegendTheoreticalAmountXStart,  graphLegendTheoreticalAmountY,  graphLegendTheoreticalAmountXEnd,  graphLegendTheoreticalAmountY, this.paint);
        this.paint.setColor(this.resources.getColor(R.color.purple_200));
        float graphLegendRealAmountXStart = this.getWidth() * 0.1f;
        float graphLegendRealAmountXEnd = this.getWidth() * 0.3f;
        float graphLegendRealAmountY = this.getHeight() * 0.9f;
        canvas.drawLine( graphLegendRealAmountXStart,  graphLegendRealAmountY,  graphLegendRealAmountXEnd,  graphLegendRealAmountY, this.paint);
        this.paint.setColor(this.resources.getColor(R.color.white));
        this.paint.setTextSize(40f);
        this.paint.setStrokeWidth(0.5f);
        canvas.drawText(getContext().getString(R.string.TheoreticalAmountGraphicalLegend), graphLegendTheoreticalAmountXEnd * 1.1f, graphLegendTheoreticalAmountY, paint);
        canvas.drawText(getContext().getString(R.string.RealAmountGraphicalLegend), graphLegendRealAmountXEnd * 1.1f, graphLegendRealAmountY, paint);

        // Draw the theoretical amount curve
        this.paint.setStrokeWidth(5f);
        this.paint.setColor(this.resources.getColor(R.color.teal_200));
        float yTheoreticalAmount = yGraphXTitle - (yGraphXTitle - yStartYAxis) * ratioRealTheoreticalAmount;
        canvas.drawLine( xValueYAxis,  yTheoreticalAmount,  xEndXAxis,  yTheoreticalAmount, this.paint);

        // Draw the real amount curve
        this.paint.setColor(this.resources.getColor(R.color.purple_200));
        // If no payments have been done without payments, draw a line for the number that have passed at 0
        if(this.daysWithoutPayment != -1) {
            float xEndWithoutPayment = xValueYAxis + (xEndXAxis - xValueYAxis) * (this.daysWithoutPayment)/30;
            // If not current budget, draw until the end of the axis
            if(isNotCurrentBudget) {
                canvas.drawLine( xValueYAxis,  yGraphXTitle,  xEndXAxis,  yGraphXTitle, this.paint);
            } else {
                canvas.drawLine( xValueYAxis,  yGraphXTitle,  xEndWithoutPayment,  yGraphXTitle, this.paint);
            }
        } else {
            for(int i = 1; i <= 30 && sumPaymentsPerDay.size() > 0; ++i) {

                float xPreviousDay = xValueYAxis + (xEndXAxis - xValueYAxis) * (i-1)/30;
                float xDay = xValueYAxis + (xEndXAxis - xValueYAxis) * (i)/30;
                float yPreviousDay, yDay;
                if(category.getTheoreticalAmount() > category.getRealAmount()) {
                    yPreviousDay = yGraphXTitle - (yGraphXTitle - yStartYAxis) * sumPaymentsPerDay.get(i-1)/category.getTheoreticalAmount();
                    yDay = yGraphXTitle - (yGraphXTitle - yStartYAxis) * sumPaymentsPerDay.get(i)/category.getTheoreticalAmount();
                } else {
                    yPreviousDay = yGraphXTitle - (yGraphXTitle - yStartYAxis) * sumPaymentsPerDay.get(i-1)/category.getRealAmount();
                    yDay = yGraphXTitle - (yGraphXTitle - yStartYAxis) * sumPaymentsPerDay.get(i)/category.getRealAmount();
                }
                if(sumPaymentsPerDay.get(i) == -1f) {
                    if(isNotCurrentBudget) {
                        canvas.drawLine( xPreviousDay,  yPreviousDay,  xEndXAxis,  yPreviousDay, this.paint);
                    }
                    break;
                }
                canvas.drawLine( xPreviousDay,  yPreviousDay,  xDay,  yDay, this.paint);
            }
        }

    }
}
