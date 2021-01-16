package hey.jusang.undoedittextsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hey.jusang.undoedittext.UndoEditText
import hey.jusang.undoedittext.UndoStatusListener
import hey.jusang.undoedittextsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUndoRedoBtn()
        setUndoRedoStatusListener()

        binding.undoEditText.setMaxHistorySize(UndoEditText.HISTORY_INFINITE)
    }

    private fun setUndoRedoBtn() {
        binding.undoBtn.setOnClickListener {
            if (binding.undoEditText.canUndo()) {
                binding.undoEditText.undo()
            }
        }

        binding.redoBtn.setOnClickListener {
            if (binding.undoEditText.canRedo()) {
                binding.undoEditText.redo()
            }
        }
    }

    private fun setUndoRedoStatusListener() {
        binding.undoBtn.isEnabled = false
        binding.redoBtn.isEnabled = false

        binding.undoEditText.setUndoStatusListener(object: UndoStatusListener {
            override fun onUndoStatusChanged(canUndo: Boolean) {
                binding.undoBtn.isEnabled = canUndo
            }

            override fun onRedoStatusChanged(canRedo: Boolean) {
                binding.redoBtn.isEnabled = canRedo
            }
        })
    }
}