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

package viviano.cantu.novakey.core.view.themes.button;

import android.graphics.Canvas;

import viviano.cantu.novakey.core.utils.drawing.drawables.Drawable;
import viviano.cantu.novakey.core.utils.drawing.shapes.Shape;
import viviano.cantu.novakey.core.view.themes.ChildTheme;

/**
 * Created by Viviano on 8/14/2016.
 */
public interface ButtonTheme extends ChildTheme {

    /**
     * Draws an object, ensuring contrast, on top of the board.
     * Use this to draw thngs like letters, text, icons bmps and such
     *
     * @param shape  drawable object to draw
     * @param x      x position to draw
     * @param y      y position to draw
     * @param size   size of object to draw
     * @param canvas canvas to draw on
     */
    void drawBack(Shape shape, float x, float y, float size, Canvas canvas);


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
    void drawIcon(Drawable drawable, float x, float y, float size, Canvas canvas);
}
