package fr.sy43.studzero.vue.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.helper.DatabaseHelper;
import fr.sy43.studzero.sqlite.model.Category;

/**
 * This class paints a progress bar for a category
 */
public class CategoryProgressBar extends View {
    /**
     * Variable that stores the resources of the app
     */
    private Resources res;
    /**
     * Variable that is used to paint the progress bar
     */
    private Paint paint;
    /**
     * Name of the categeory
     */
    private String categoryName;
    /**
     * Text that stores the real amount and the theoretical amount of money of a a category
     */
    private String textToDraw;
    /**
     * Ratio between the theoretical amount and the real amount of money of a category
     */
    private float ratioBar;

    /**
     * Constructor of the class
     * @param context
     * @param attrs
     * @param category used to paint the progress bar
     */
    public CategoryProgressBar(Context context, AttributeSet attrs, Category category) {
        super(context, attrs);
        this.res = this.getResources();
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        DatabaseHelper db = new DatabaseHelper(context);
        categoryName = db.getTypeCategory(category.getType()).getNameCategory();
        db.closeDB();
        textToDraw = "" + String.format("%.2f", category.getRealAmount()) + "/" + String.format("%.2f", category.getTheoreticalAmount());
        if(category.getRealAmount() / category.getTheoreticalAmount() > 1) {
            ratioBar = 1f;
        } else {
          ratioBar = category.getRealAmount() / category.getTheoreticalAmount();
        }
    }

    /**
     * Draw the progress bar component
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the name of the category
        this.paint.setTextSize(40f);
        this.paint.setStrokeWidth(0.5f);
        this.paint.setColor(res.getColor(R.color.white));
        canvas.drawText(categoryName, this.getWidth()*0.02f, getHeight()*0.35f, paint);
        // Draw the text that stores the real amount and the theoretical amount of money of a a category
        this.paint.setTextSize(40f);
        float widthText = this.getWidth() - this.paint.measureText(textToDraw);
        Rect bounds = new Rect();
        this.paint.getTextBounds(this.textToDraw, 0, this.textToDraw.length(), bounds);
        int heightText = bounds.height();
        canvas.drawText(textToDraw, widthText-this.getWidth()*0.02f, this.getHeight() * 0.8f, paint);
        // Draw the progress bar
        this.paint.setStrokeWidth(5f);
        float startX = this.getWidth() * 0.02f;
        float y = this.getHeight() * 0.9f - heightText/2;
        float stopX = widthText - this.getWidth()*0.1f;
        this.paint.setColor(res.getColor(R.color.purple_200));
        canvas.drawLine(startX, y, stopX, y, paint);
        this.paint.setColor(res.getColor(R.color.purple_500));
        canvas.drawLine(startX, y, stopX * ratioBar, y, paint);
        canvas.drawCircle(stopX * ratioBar, y, 15f, paint);
    }
}
