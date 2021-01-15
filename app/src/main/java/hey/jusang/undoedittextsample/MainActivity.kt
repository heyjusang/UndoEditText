package hey.jusang.undoedittextsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hey.jusang.undoedittext.UndoEditText
import hey.jusang.undoedittextsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}