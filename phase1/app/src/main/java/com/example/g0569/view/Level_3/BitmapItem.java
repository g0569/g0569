//package com.example.g0569.view.Level_3;
//
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Rect;
//
//public abstract class BitmapItem extends ItemInPlatform {
//
//  public int x;
//  public int y;
//  public Rect src_rect;
//  public Rect dest_rect;
//  public int size;
//  public int dx;
//  public int dy;
//  public Bitmap appearance;
//
//  @Override
//  public void draw(Canvas canvas, Paint paint) {
//    canvas.drawBitmap(appearance, src_rect, dest_rect, paint);
//  }
//
//  public void basicMove(){
//      x += dx;
//      y += dy;
//      dest_rect.set(x, y, x + size, y + size);
//  }
//}
