package fr.sy43.studzero.vue.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
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

public class CategoryGraph extends View {
    private Paint paint;
    private Category category;
    private Resources resources;
    private LinkedHashMap<Integer, Float> sumPaymentsPerDay;
    private float ratioRealTheoreticalAmount;

    public CategoryGraph(Context context, Category category) {
        super(context);
        this.paint = new Paint();
        this.category = category;
        this.resources = this.getResources();
        setGraphData(category);
    }

    private void setGraphData(Category category) {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<Payment>payments = db.getAllPaymentsOfCategory(category.getIdCategory());
        sumPaymentsPerDay = new LinkedHashMap<>();

        if(payments.size() > 0) {
            long diffBudgetStart = db.getBudget(category.getBudget()).getDateStart().getTime() - payments.get(0).getDatePayment().getTime();
            db.closeDB();
            int numberOfDaysBetweenBudgetStartAnd1stPayment =  1 + (int) TimeUnit.DAYS.convert(diffBudgetStart, TimeUnit.MILLISECONDS);
            for(int i = 0; i < numberOfDaysBetweenBudgetStartAnd1stPayment; ++i)  {
                sumPaymentsPerDay.put(i, 0f);
            }
            sumPaymentsPerDay.put(numberOfDaysBetweenBudgetStartAnd1stPayment, payments.get(0).getAmount());
            for(int i = numberOfDaysBetweenBudgetStartAnd1stPayment + 1; i <= 30; ++i) {
                sumPaymentsPerDay.put(i, -1f);
            }
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

            if(category.getTheoreticalAmount() > category.getRealAmount()) {
                this.ratioRealTheoreticalAmount = 1;
            } else {
                this.ratioRealTheoreticalAmount = category.getTheoreticalAmount() / category.getRealAmount();
            }
        } else {
            this.ratioRealTheoreticalAmount = 1;
        }

    }

    public void setNewCategory(Category category) {
        setGraphData(category);
        this.invalidate();
    }

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
        for(int i = 1; i <= 30 && sumPaymentsPerDay.size() > 0; ++i) {
            if(sumPaymentsPerDay.get(i) == -1f) {
                break;
            }
            float xPreviousDay = xValueYAxis + (xEndXAxis - xValueYAxis) * (i-1)/30;
            float yPreviousDay = yGraphXTitle - (yGraphXTitle - yStartYAxis) * sumPaymentsPerDay.get(i-1)/category.getRealAmount();
            float xDay = xValueYAxis + (xEndXAxis - xValueYAxis) * (i)/30;
            float yDay;
            if(category.getTheoreticalAmount() > category.getRealAmount()) {
                yDay = yGraphXTitle - (yGraphXTitle - yStartYAxis) * sumPaymentsPerDay.get(i)/category.getTheoreticalAmount();
            } else {
                yDay = yGraphXTitle - (yGraphXTitle - yStartYAxis) * sumPaymentsPerDay.get(i)/category.getRealAmount();
            }

            canvas.drawLine( xPreviousDay,  yPreviousDay,  xDay,  yDay, this.paint);
        }
    }
}
