package data.logit.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by AdminProgram on 5/10/2015.
 */
public class GameActivity extends AppCompatActivity {


    int frogSpot;
    int carSpot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(new MyView(this));

    }

    public class MyView extends View {
        int x, y;

        public MyView(Context context) {
            super(context);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
                final int xWidth = getWidth();
                final int yHeight = getHeight();
                int radius;
                radius = 100;


                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
                canvas.drawPaint(paint);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setColor(Color.GREEN);


                Bitmap carBM = BitmapFactory.decodeResource(getResources(), R.mipmap.car);
                Bitmap frogBM = BitmapFactory.decodeResource(getResources(), R.mipmap.frog);


                Rect safeField1 = new Rect(1, 1, xWidth, 1 + frogBM.getHeight() * 2);
                canvas.drawRect(safeField1, paint);
                Rect safeField2 = new Rect(1, yHeight - frogBM.getHeight(), xWidth, yHeight);
                canvas.drawRect(safeField2, paint);
                Rect safeField3 = new Rect(1, 1 + frogBM.getHeight() * 6, xWidth, 1 + frogBM.getHeight() * 8);
                canvas.drawRect(safeField3, paint);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);

                if(frogSpot == 0) {
                    frogSpot = 1 + frogBM.getHeight();
                } else {
                    if (carSpot == 0 - carBM.getWidth()) {
                        carSpot += 10;
                    } else if (carSpot >= xWidth) {
                        carSpot = 0 - carBM.getWidth() + 1;
                    } else {
                        if (y <= (yHeight / 2) && y != 0) {
                            frogSpot += frogBM.getHeight();
                            canvas.drawBitmap(frogBM, (xWidth / 2) - (frogBM.getWidth() / 2), frogSpot, paint);

                            x = 0;
                            y = 0;
                        } else if (y >= (yHeight / 2)) {
                            frogSpot -= frogBM.getHeight();
                            canvas.drawBitmap(frogBM, (xWidth / 2) - (frogBM.getWidth() / 2), frogSpot, paint);
                            x = 0;
                            y = 0;
                        } else if (y == 0) {

                        }
                    }
                }
                canvas.drawBitmap(frogBM, (xWidth / 2) - (frogBM.getHeight() / 2), frogSpot, paint);
                canvas.drawBitmap(carBM, carSpot, frogBM.getHeight() * 3, paint);
                Bitmap deadFrogger = BitmapFactory.decodeResource(getResources(), R.mipmap.deadfrogger);
                carSpot += 10;
                invalidate();
                Rect carRect = new Rect(carSpot, frogBM.getHeight() * 3, carSpot + carBM.getWidth(), frogBM.getHeight() * 3 + carBM.getHeight());
                Rect frogRect = new Rect((xWidth / 2) - (frogBM.getWidth() / 2), frogSpot, (xWidth / 2) - (frogBM.getWidth() / 2) + frogBM.getWidth(), frogSpot + frogBM.getHeight());
                if(carRect.intersect(frogRect)){
                    Vibrator v = (Vibrator) this.getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    canvas.drawBitmap(deadFrogger, (xWidth / 2) - (frogBM.getHeight() / 2), frogSpot, paint);
                    canvas.drawBitmap(carBM, carSpot, frogBM.getHeight() * 3, paint);
                    v.vibrate(500);
                    Intent gameOver = new Intent(GameActivity.this, GameOverActivity.class);
                    startActivity(gameOver);
                    GameActivity.this.finish();
                }
                //canvas.drawBitmap(frogBM, x - (frogWidth / 2), y - (frogHeight / 2), paint);

        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN :
                {
                    x = (int) event.getX();
                    y = (int) event.getY();
                    System.err.println("touched " + x + ", "+ y);
                    invalidate();
                    return true;
                }
                case MotionEvent.ACTION_UP:
                {
                    return true;
                }
                default:
                    return super.onTouchEvent(event);
            }
        }

    }


}
