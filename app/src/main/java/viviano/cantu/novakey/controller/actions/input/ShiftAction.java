package viviano.cantu.novakey.controller.actions.input;

import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;

import viviano.cantu.novakey.NovaKey;
import viviano.cantu.novakey.controller.Controller;
import viviano.cantu.novakey.controller.actions.Action;
import viviano.cantu.novakey.controller.actions.SetKeyboardAction;
import viviano.cantu.novakey.controller.actions.SetShiftStateAction;
import viviano.cantu.novakey.model.elements.keyboards.Keyboards;
import viviano.cantu.novakey.model.Model;
import viviano.cantu.novakey.model.InputState;
import viviano.cantu.novakey.model.ShiftState;
import viviano.cantu.novakey.utils.Util;

/**
 * Performs the desired action of the user performing
 * the shift gesture. Which is, switch ShiftState & UserState
 * accordingly, and update the selected text
 *
 * Created by Viviano on 6/15/2016.
 */
public class ShiftAction implements Action<Void> {

    /**
     * Called when the action is triggered
     * Actual logic for the action goes here
     * @param ime
     * @param control
     * @param model
     */
    @Override
    public Void trigger(NovaKey ime, Controller control, Model model) {
        InputState state = model.getInputState();

        String selectedText = model.getInputState().getSelectedText();
        boolean shiftText = selectedText.length() > 0;
        int s = 0, e = 0;
        if (shiftText) {
            ExtractedText et = ime.getCurrentInputConnection()
                    .getExtractedText(new ExtractedTextRequest(), 0);
            s = et.selectionStart;
            e = et.selectionEnd;
        }

        switch (model.getKeyboardCode()) {
            case Keyboards.PUNCTUATION:
                control.fire(new SetKeyboardAction(Keyboards.SYMBOLS));
                break;
            case Keyboards.SYMBOLS:
                control.fire(new SetKeyboardAction(Keyboards.PUNCTUATION));
                break;
            default:
                switch (model.getShiftState()) {
                    case LOWERCASE:
                        control.fire(new SetShiftStateAction(ShiftState.UPPERCASE));
                        if (shiftText) {//uppercase each word
                            control.fire(new InputAction(Util.uppercaseFirst(selectedText)));
                            state.setSelection(s, e);
                        }
                        break;
                    case UPPERCASE:
                        control.fire(new SetShiftStateAction(ShiftState.CAPS_LOCKED));
                        if (shiftText) {//caps everything
                            control.fire(new InputAction(selectedText.toUpperCase()));
                            state.setSelection(s, e);
                        }
                        break;
                    case CAPS_LOCKED:
                        control.fire(new SetShiftStateAction(ShiftState.LOWERCASE));
                        if (shiftText) {//lowercase everything
                            control.fire(new InputAction(selectedText.toLowerCase()));
                            state.setSelection(s, e);
                        }
                        break;
                }
        }
        return null;
    }
}