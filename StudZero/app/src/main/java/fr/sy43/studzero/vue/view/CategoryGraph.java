package fr.sy43.studzero.vue.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import fr.sy43.studzero.R;
import fr.sy43.studzero.sqlite.model.Category;

public class CategoryGraph extends View {
    private Paint paint;
    private Category category;
    private Resources resources;

    public CategoryGraph(Context context, Category category) {
        super(context);
        this.paint = new Paint();
        this.category = category;
        this.resources = this.getResources();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth(5f);
        this.paint.setColor(this.resources.getColor(R.color.white));
        float yStartYAxis = this.getHeight() * 0.05f;
        float YEndYAxis = this.getHeight() * 0.7f;
        float xValueYAxis = this.getWidth() * 0.1f;
        float xEndXAxis = this.getWidth() * 0.9f;
        canvas.drawLine( xValueYAxis,  yStartYAxis,  this.getWidth() * 0.1f,  YEndYAxis, this.paint);
        canvas.drawLine( xValueYAxis,  YEndYAxis,  xEndXAxis,  YEndYAxis, this.paint);
        // canvas.drawText(""+category.getRealAmount(), this.getWidth()*0.02f, getHeight()*0.35f, paint);

    }
}
