package fr.sy43.studzero.vue.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

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

    }
}
