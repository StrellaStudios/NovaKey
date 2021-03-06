/*
 * NovaKey - An alternative touchscreen input method
 * Copyright (C) 2019  Viviano Cantu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>
 *
 * Any questions about the program or source may be directed to <strellastudios@gmail.com>
 */

package viviano.cantu.novakey.core.view.themes.board;

import android.graphics.Canvas;
import android.graphics.Paint;

import viviano.cantu.novakey.core.utils.drawing.Draw;
import viviano.cantu.novakey.core.utils.drawing.drawables.Drawable;
import viviano.cantu.novakey.core.view.themes.MasterTheme;

/**
 * Created by Viviano on 6/9/2015.
 */
public class BaseTheme implements BoardTheme {

    protected final Paint pB, pT;
    protected MasterTheme mParent;


    public BaseTheme() {
        pB = new Paint();
        pT = new Paint();

        pB.setFlags(Paint.ANTI_ALIAS_FLAG);//smooth edges and Never changes
        pT.setFlags(Paint.ANTI_ALIAS_FLAG);
    }


    /**
     * Draw the center of the board
     *
     * @param x      center X position
     * @param y      center Y position
     * @param r      radius of keyboard
     * @param sr
     * @param canvas canvas to draw on
     */
    @Override
    public void drawBoard(float x, float y, float r, float sr, Canvas canvas) {
        drawBoardBack(x, y, r, sr, canvas);
        drawLines(x, y, r, sr, canvas);
    }


    //Override to change drawing
    protected void drawBoardBack(float x, float y, float r, float sr, Canvas canvas) {
        //Does nothing
    }


    private void drawLines(float x, float y, float r, float sr, Canvas canvas) {
        drawLines(x, y, r, sr, 1 / 72f, canvas);
    }


    //override to change drawing
    protected void drawLines(float x, float y, float r, float sr, float w, Canvas canvas) {
        if (mParent.is3D()) {
            Draw.shadedLines(x, y + r / 72f, r, sr, 0x80000000, pB, canvas);
            pB.setShadowLayer(r / 72f / 2, 0, r / 72f, 0x80000000);
        }
        //draw lines and circle
        pB.setColor(mParent.getAccentColor());
        pB.setStyle(Paint.Style.STROKE);
        pB.setStrokeWidth(r * w);
        //draw circles & lines
        canvas.drawCircle(x, y, sr, pB);
        pB.clearShadowLayer();

        Draw.shadedLines(x, y, r, sr, mParent.getAccentColor(), pB, canvas);
    }


    /**
     * Draws an object, ensuring contrast, on top of the board.
     * Use this to draw thngs like letters, text, icons bmps and such
     *
     * @param drawable drawable object to draw
     * @param x        x position to draw
     * @param y        y position to draw
     * @param size     size of object to draw
     * @param canvas   canvas to draw on
     */
    @Override
    public void drawItem(Drawable drawable, float x, float y, float size, Canvas canvas) {
        if (mParent.is3D())
            pT.setShadowLayer(100, 0, 100, 0x80000000);//TODO: globalize shadow height
        pT.setStyle(Paint.Style.FILL);
        pT.setColor(mParent.getContrastColor());
        drawable.draw(x, y, size, pT, canvas);
        pT.clearShadowLayer();
    }


    /**
     * Sets this child's master theme for reference
     *
     * @param masterTheme this theme's parent
     */
    @Override
    public void setParent(MasterTheme masterTheme) {
        mParent = masterTheme;
    }


    /**
     * Draw method for the picker item
     *
     * @param x        center x position
     * @param y        center y position
     * @param dimen    dimension equivalent to the maximum height
     * @param selected whether it is selected
     * @param index    sub index of picker item
     * @param p        paint used
     * @param canvas   canvas to draw on
     */
    @Override
    public void drawPickerItem(float x, float y, float dimen, boolean selected,
                               int index, Paint p, Canvas canvas) {
        float r = dimen / 2 * .8f;
        float sr = (dimen / 2 * .8f) / 3;
        mParent.setColors(0xFFF0F0F0, 0xFF616161, 0xFF616161);
        drawBoardBack(x, y, r, sr, canvas);
        drawLines(x, y, r, sr, 1 / 30f, canvas);

        if (selected) {
            p.clearShadowLayer();
            p.setStyle(Paint.Style.STROKE);
            p.setColor(0xFF58ACFA);
            p.setStrokeWidth(dimen * .1f);
            canvas.drawCircle(x, y, r, p);
            p.setStrokeWidth(0);
            p.setStyle(Paint.Style.FILL);
        }
    }
}
