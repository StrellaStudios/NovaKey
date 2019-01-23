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

package viviano.cantu.novakey.controller.actions.input;

import viviano.cantu.novakey.NovaKey;
import viviano.cantu.novakey.controller.Controller;
import viviano.cantu.novakey.controller.actions.Action;
import viviano.cantu.novakey.model.Model;
/**
 * Universal input action
 * Can be given '\n', Keyboard.KEYCODE_SHIFT
 * or similar inputs and will perform the desired actions
 *
 * Created by Viviano on 6/14/2016.
 */
public class InputAction implements Action<Void> {

    private final String mText;
    private final int mCursorPos;

    public InputAction(String text) {
        this(text, true);
    }

    public InputAction(String text, boolean beforeCursor) {
        mCursorPos = beforeCursor ? 0 : 1;
        mText = text;
    }

    /**
     * Called when the action is triggered
     * Actual logic for the action goes here
     * @param ime
     * @param control
     * @param model
     */
    @Override
    public Void trigger(NovaKey ime, Controller control, Model model) {
        if (mText != null) {
            model.getInputState().inputText(mText, mCursorPos);
        }
        return null;
    }
}
