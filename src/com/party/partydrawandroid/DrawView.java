package com.party.partydrawandroid;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;


public class DrawView extends View {
	
	private Path drawPath;
	private Paint drawPaint, canvasPaint;
	private int paintColor = 0xFF660000;
	private Canvas drawCanvas;
	private Bitmap canvasBitmap;
	private float brushSize, lastBrushSize;
	private boolean erase = false;
	
	
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
	}

	public void setupDrawing() {
		
		brushSize = getResources().getInteger(R.integer.small_size);
		lastBrushSize = brushSize;
		
		drawPath = new Path();
		drawPaint = new Paint();
		
		drawPaint.setColor(paintColor);
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		
	}
	
	public void startNew() {
	    drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
	    invalidate();
	}
	
	public void loadSaved(Bitmap bmp) {
		
		invalidate();
		canvasBitmap = bmp;
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		drawCanvas = new Canvas();
		drawCanvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		
	}
	
	public void setBrushSize(float newSize) {
		
		float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				newSize, getResources().getDisplayMetrics());
		brushSize = pixelAmount;
		drawPaint.setStrokeWidth(brushSize);
		
	}
	
	public void setLastBrushSize(float lastSize) {
		lastBrushSize = lastSize;
	}
	
	public float getLastBrushSize() {
		return lastBrushSize;
	}
	
	public void setErase(boolean isErase) {
		erase = isErase;
		if (erase) {
			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		}
		else {
			drawPaint.setXfermode(null);
		}
	}
	
	public void setColor(String newColor) {
		
		invalidate();
		
		paintColor = Color.parseColor(newColor);
		drawPaint.setColor(paintColor);

		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		float touchX = event.getX();
		float touchY = event.getY();
		
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
			drawPath.moveTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_MOVE:
			drawPath.lineTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_UP:
			drawCanvas.drawPath(drawPath, drawPaint);
			drawPath.reset();
			break;
		default:
			return false;
		}
		
		invalidate();
		return true;
	}

}
