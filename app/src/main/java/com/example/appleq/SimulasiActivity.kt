package com.example.appleq

import android.content.res.AssetManager
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SimulasiActivity : AppCompatActivity() {
    private lateinit var interpreter: Interpreter
    private val mModelPath = "apple.tflite"

    private lateinit var resultText: TextView
    private lateinit var Size: EditText
    private lateinit var Weight: EditText
    private lateinit var Sweetness: EditText
    private lateinit var Crunchiness: EditText
    private lateinit var Juiciness: EditText
    private lateinit var Ripeness: EditText
    private lateinit var Acdity: EditText
    private lateinit var checkButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulasi)

        resultText = findViewById(R.id.tvHasil)
        Size = findViewById(R.id.etSize)
        Weight = findViewById(R.id.etWeight)
        Sweetness = findViewById(R.id.etSweet)
        Crunchiness = findViewById(R.id.etCrunch)
        Juiciness = findViewById(R.id.etJuice)
        Ripeness = findViewById(R.id.etRipe)
        Acdity = findViewById(R.id.etAcid)
        checkButton = findViewById(R.id.btnPredict)

        checkButton.setOnClickListener {
            var result = doInference(
                Size.text.toString(),
                Weight.text.toString(),
                Sweetness.text.toString(),
                Crunchiness.text.toString(),
                Juiciness.text.toString(),
                Ripeness.text.toString(),
                Acdity.text.toString())
            runOnUiThread {
                resultText.text = if (result == 0) "Bad Quality" else "Good Quality"
            }
        }
        initInterpreter()
    }

    private fun initInterpreter() {
        val options = org.tensorflow.lite.Interpreter.Options()
        options.setNumThreads(4)
        options.setUseNNAPI(true)
        interpreter = org.tensorflow.lite.Interpreter(loadModelFile(assets, mModelPath), options)
    }

    private fun doInference(input1: String, input2: String, input3: String, input4: String, input5: String, input6: String, input7: String): Int{
        val inputVal = FloatArray(7)
        inputVal[0] = input1.toFloat()
        inputVal[1] = input2.toFloat()
        inputVal[2] = input3.toFloat()
        inputVal[3] = input4.toFloat()
        inputVal[4] = input5.toFloat()
        inputVal[5] = input6.toFloat()
        inputVal[6] = input7.toFloat()
        val output = Array(1) { FloatArray(1) }
        interpreter.run(inputVal, output)

        Log.e("result", (output[0].toList()+" ").toString())

        return if (output[0][0] >= 0.5f) 1 else 0
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}