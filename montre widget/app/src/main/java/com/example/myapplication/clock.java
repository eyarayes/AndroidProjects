package fr.koor.clocksample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

public class Clock extends View {

    private Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG );
    private boolean isRunning = false;

    private float centerX;
    private float centerY;
    private float radius;


    public Clock( Context context ) {
        super( context );
    }

    public Clock(Context context, AttributeSet attrs) {
        super( context, attrs );
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        centerX = width/2;
        centerY = height/2;
        radius = Math.min( width*0.45f, height*0.45f );
    }

    public void onResume() {
        isRunning = true;
        new Thread() {
            @Override
            public void run() {
                while( isRunning ) {
                    try {
                        sleep( 990 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }.start();
    }

    public void onPause() {
        isRunning = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // --- Outer disk ---

        LinearGradient linearGradient = new LinearGradient(
                0, centerY-radius, 0, centerY+radius,
                new int[] { 0xffe0e0e0, 0xff6e7774, 0xff0a0e0a, 0xff0a0809 },
                new float[] { 0, 0.49f, 0.50f, 1 },
                Shader.TileMode.REPEAT
        );
        paint.setShader( linearGradient );
        canvas.drawCircle( centerX, centerY, radius, paint );

        paint.setShader( null );
        paint.setColor( 0xff212121 );
        canvas.drawCircle( centerX, centerY, radius*0.95f, paint );

        // --- Graduations ---
        paint.setColor( 0xFFFFFFFF );
        paint.setStrokeWidth( 3f );
        paint.setTextSize( radius/8f );

        int hour = 12;
        for( float angle = 0; angle<=360; angle += 6f ) {

            float radianAngle = (float) -(Math.PI/2 - (2 * Math.PI * angle/360));

            if ( angle % 30 != 0 ) {
                canvas.drawLine(
                        (float) (centerX + Math.cos(radianAngle) * radius * 0.85),
                        (float) (centerY + Math.sin(radianAngle) * radius * 0.85),
                        (float) (centerX + Math.cos(radianAngle) * radius * 0.90),
                        (float) (centerY + Math.sin(radianAngle) * radius * 0.90),
                        paint
                );
            } else {
                Path path = new Path();
                path.moveTo(
                        (float) (centerX + Math.cos(radianAngle-0.01) * radius * 0.80),
                        (float) (centerY + Math.sin(radianAngle-0.01) * radius * 0.80)
                );
                path.lineTo(
                        (float) (centerX + Math.cos(radianAngle-0.02) * radius * 0.90),
                        (float) (centerY + Math.sin(radianAngle-0.02) * radius * 0.90)
                );
                path.lineTo(
                        (float) (centerX + Math.cos(radianAngle+0.02) * radius * 0.90),
                        (float) (centerY + Math.sin(radianAngle+0.02) * radius * 0.90)
                );
                path.lineTo(
                        (float) (centerX + Math.cos(radianAngle+0.01) * radius * 0.80),
                        (float) (centerY + Math.sin(radianAngle+0.01) * radius * 0.80)
                );
                path.lineTo(
                        (float) (centerX + Math.cos(radianAngle-0.01) * radius * 0.80),
                        (float) (centerY + Math.sin(radianAngle-0.01) * radius * 0.80)
                );
                canvas.drawPath( path, paint );

                if ( hour > 0 ) {
                    String strHour = "" + hour;
                    Rect textBounds = new Rect();
                    paint.getTextBounds( strHour, 0, strHour.length(), textBounds);
                    canvas.drawText(
                            strHour,
                            (float) (centerX + Math.cos( radianAngle ) * radius * 0.70 - textBounds.width()/2 ),
                            (float) (centerY + Math.sin( radianAngle ) * radius * 0.70 + textBounds.height()/3 ),
                            paint
                    );
                }
                hour = ++hour % 12;
            }

        }

        // --- Clockwise ---

        Calendar now = Calendar.getInstance();
        now.setTime( new Date() );

        float hourAngle = ((float) (Math.PI * 2))
                * ( now.get( Calendar.HOUR ) % 12 + now.get( Calendar.MINUTE ) / 60f ) / 12f;
        hourAngle -= (float) Math.PI/2;
        paint.setColor( 0x99FF0000 );

        Path path = new Path();
        path.moveTo(
                (float) (centerX + Math.cos( hourAngle ) * radius * 0.60),
                (float) (centerY + Math.sin( hourAngle ) * radius * 0.60)
        );
        path.lineTo(
                (float) (centerX + Math.cos( hourAngle-0.4 ) * radius * 0.10),
                (float) (centerY + Math.sin( hourAngle-0.4 ) * radius * 0.10)
        );
        path.lineTo(
                (float) (centerX + Math.cos( hourAngle+0.4 ) * radius * 0.10),
                (float) (centerY + Math.sin( hourAngle+0.4 ) * radius * 0.10)
        );
        path.lineTo(
                (float) (centerX + Math.cos( hourAngle ) * radius * 0.60),
                (float) (centerY + Math.sin( hourAngle ) * radius * 0.60)
        );
        canvas.drawPath( path, paint );

        paint.setColor( 0xFFFF0000 );
        canvas.drawLine(
                (float) (centerX + Math.cos( hourAngle ) * radius * 0.60),
                (float) (centerY + Math.sin( hourAngle ) * radius * 0.60),
                (float) (centerX + Math.cos( hourAngle-0.4 ) * radius * 0.10),
                (float) (centerY + Math.sin( hourAngle-0.4 ) * radius * 0.10),
                paint
        );
        canvas.drawLine(
                (float) (centerX + Math.cos( hourAngle ) * radius * 0.60),
                (float) (centerY + Math.sin( hourAngle ) * radius * 0.60),
                (float) (centerX + Math.cos( hourAngle+0.4 ) * radius * 0.10),
                (float) (centerY + Math.sin( hourAngle+0.4 ) * radius * 0.10),
                paint
        );

        float minuteAngle = ((float) (Math.PI * 2))
                * ( now.get( Calendar.MINUTE ) / 60f + now.get( Calendar.SECOND ) / 3600f );
        minuteAngle -= (float) Math.PI/2;
        paint.setColor( 0x99FF0000 );

        path.reset();
        path.moveTo(
                (float) (centerX + Math.cos( minuteAngle ) * radius * 0.93),
                (float) (centerY + Math.sin( minuteAngle ) * radius * 0.93)
        );
        path.lineTo(
                (float) (centerX + Math.cos( minuteAngle-0.3 ) * radius * 0.10),
                (float) (centerY + Math.sin( minuteAngle-0.3 ) * radius * 0.10)
        );
        path.lineTo(
                (float) (centerX + Math.cos( minuteAngle+0.3 ) * radius * 0.10),
                (float) (centerY + Math.sin( minuteAngle+0.3 ) * radius * 0.10)
        );
        path.lineTo(
                (float) (centerX + Math.cos( minuteAngle ) * radius * 0.93),
                (float) (centerY + Math.sin( minuteAngle ) * radius * 0.93)
        );
        canvas.drawPath( path, paint );

        paint.setColor( 0xFFFF0000 );
        canvas.drawLine(
                (float) (centerX + Math.cos( minuteAngle ) * radius * 0.93),
                (float) (centerY + Math.sin( minuteAngle ) * radius * 0.93),
                (float) (centerX + Math.cos( minuteAngle-0.3 ) * radius * 0.10),
                (float) (centerY + Math.sin( minuteAngle-0.3 ) * radius * 0.10),
                paint
        );
        canvas.drawLine(
                (float) (centerX + Math.cos( minuteAngle ) * radius * 0.93),
                (float) (centerY + Math.sin( minuteAngle ) * radius * 0.93),
                (float) (centerX + Math.cos( minuteAngle+0.3 ) * radius * 0.10),
                (float) (centerY + Math.sin( minuteAngle+0.3 ) * radius * 0.10),
                paint
        );

        paint.setColor( 0x99FF0000 );
        paint.setStrokeWidth( 8f );
        float secondAngle = ((float) (Math.PI * 2))  * now.get( Calendar.SECOND ) / 60f;
        secondAngle -= (float) Math.PI/2;

        canvas.drawLine(
                (float) (centerX + Math.cos( secondAngle ) * radius * 0.94),
                (float) (centerY + Math.sin( secondAngle ) * radius * 0.94),
                (float) (centerX + Math.cos( secondAngle ) * radius * 0.10),
                (float) (centerY + Math.sin( secondAngle ) * radius * 0.10),
                paint
        );

        // --- Inner disk ---
        paint.setColor( 0xff000000 );

        linearGradient = new LinearGradient(
                0, centerY-radius*0.2f, 0, centerY+radius*0.2f,
                new int[] { 0xffe0e0e0, 0xff6e7774, 0xff0a0e0a, 0xff0a0809 },
                new float[] { 0, 0.49f, 0.50f, 1 },
                Shader.TileMode.REPEAT
        );
        paint.setShader( linearGradient );
        canvas.drawCircle( centerX, centerY, radius*0.18f, paint );

        paint.setShader( null );
        paint.setColor( 0xff212121 );
        canvas.drawCircle( centerX, centerY, radius*0.15f, paint );

    }
}