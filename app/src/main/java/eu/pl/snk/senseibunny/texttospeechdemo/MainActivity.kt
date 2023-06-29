package eu.pl.snk.senseibunny.texttospeechdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.UserDictionary.Words.LOCALE
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import eu.pl.snk.senseibunny.texttospeechdemo.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech?= null
    private var binding: ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        tts= TextToSpeech(this, this)

        binding?.buttonText?.setOnClickListener{ view->
            Log.e("Init Failed1","1")
            if(binding?.etText?.text!!.isEmpty()){
                Toast.makeText(this@MainActivity, "Enter some text bruh", Toast.LENGTH_LONG).show()
                Log.e("Dziala","dziala poprawnie")
            }
            else{
                //speak text

                speakout(binding?.etText?.text.toString())
            }
        }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if(tts!=null){
            tts?.stop()
            tts?.shutdown()
        }

        binding=null
    }

    private fun speakout(text: String){
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }
}