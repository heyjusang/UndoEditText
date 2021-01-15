package hey.jusang.undoedittext

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import hey.jusang.undoedittext.memento.CareTaker
import hey.jusang.undoedittext.memento.Memento

class UndoEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {
    private val careTaker: CareTaker = CareTaker()
    private val textChangeListener: TextChangeListener = TextChangeListener()

    init {
        addTextChangedListener(textChangeListener)
    }

    fun canUndo(): Boolean {
        return careTaker.canUndo()
    }

    fun undo() {
        val memento: Memento = careTaker.undo() ?: return
        val start: Int = memento.start
        val end: Int = start + (if (memento.after != null) memento.after.length else 0)

        replace(start, end, memento.before)
    }

    fun redo() {
        val memento: Memento = careTaker.redo() ?: return
        var start: Int = memento.start
        var end: Int = start + (if (memento.before != null) memento.before.length else 0)

        replace(start, end, memento.after)
    }

    fun canRedo(): Boolean {
        return careTaker.canRedo()
    }

    fun clearHistory() {
        careTaker.clear()
    }

    fun setMaxHistorySize(size: Int) {
        careTaker.maxSize = size
    }

    private fun replace(start: Int, end: Int, s: CharSequence?) {
        textChangeListener.enabled = false
        editableText.replace(start, end, s)
        textChangeListener.enabled = true
    }

    private inner class TextChangeListener : TextWatcher {
        private var beforeText: CharSequence? = null
        private var afterText: CharSequence? = null
        var enabled: Boolean = true

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (!enabled) {
                return
            }

            beforeText = s?.subSequence(start, start + count)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!enabled) {
                return
            }

            afterText = s?.subSequence(start, start + count)
            careTaker.add(Memento(start, beforeText, afterText))
        }

        override fun afterTextChanged(s: Editable?) {}
    }
}