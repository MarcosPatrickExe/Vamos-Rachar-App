package com.mpatrick.vamosracharapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import java.util.*

// mascarando campos:
// https://www.youtube.com/watch?v=4bbF4I_ZaG4&t=256s




class TelaPrincipal : AppCompatActivity(), TextToSpeech.OnInitListener {

            private var tts :TextToSpeech? = null;


            override fun onCreate( savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState);
                    super.setContentView(R.layout.activity_main);

                    super.getSupportActionBar()?.hide();

                    val  inputValorConta :EditText = super.findViewById(R.id.inputValor);
                    val  inputNumRachadores :EditText = super.findViewById(R.id.inputNumRachadores);
                    val volumeIcon  = super.findViewById(R.id.volumeIcon) as ImageView;
                    val shareIcon  = super.findViewById(R.id.shareIcon) as ImageView;


                    inputValorConta.addTextChangedListener {
                                  //Toast.makeText( this, "Campo alterado!!!", Toast.LENGTH_SHORT).show();
                                this.validar(inputValorConta, inputNumRachadores);
                    }

                    inputNumRachadores.addTextChangedListener {
                                this.validar( inputValorConta, inputNumRachadores);
                    }

                    // inicializando TTS
                    this!!.tts = TextToSpeech(this, this);

                    // BOTÃO DE VOLUME (TTS):
                    volumeIcon.setOnClickListener{
                                 this.falar(
                                        super .findViewById<TextView>(R.id.valorTxt).text .toString()
                                 );
                    }

                    // BOTÃO DE COMPARTILHAMENTO:
                    shareIcon.setOnClickListener {
                                var shareIntent = Intent();
                                shareIntent.setAction( Intent.ACTION_SEND );
                                shareIntent.putExtra( Intent.EXTRA_TEXT,  super .findViewById<TextView>(R.id.valorTxt).text .toString());
                                shareIntent.setType("text/plain");
                                super.startActivity( shareIntent );
                    }
            }


            private fun falar( valor: String){
                                Log.d("out", "Falando o texto recebido: ${valor}");
                                this.tts!!.speak( valor, TextToSpeech.QUEUE_FLUSH, null, null )
            }


            // MÉTODO DA INTERFACE OnInitListener
            override fun onInit( status: Int){

                                if( status !=  TextToSpeech.ERROR){
                                        this.tts!!.language= Locale.ENGLISH;
                                }
            }



            fun validar(  valorConta: TextView, nRachadores: TextView){

                        if( valorConta.text.toString().equals("0.00") || valorConta.text.toString().equals("") || nRachadores.text.toString().equals("0") || nRachadores.text.toString().equals("") ) {

                                        Toast.makeText(this,"Nenhum campo pode ficar vazio ou ter valor zero!!", Toast.LENGTH_SHORT).show();
                                        super .findViewById<TextView>(R.id.valorTxt) .text = "R$ 0.00";

                        }else if(  valorConta.text.toString().toDouble() > 0 &&  nRachadores.text.toString().toDouble() > 0  ){

                                        var valorDividido :Double=   valorConta.text.toString().toDouble()  / nRachadores.text.toString().toDouble() ;
                                       // Toast.makeText( this,"O valor que fica para cada um é: ${String.format("%.2f", valorDividido)}",  Toast.LENGTH_SHORT).show();
                                        super .findViewById<TextView>(R.id.valorTxt) .text = ("R$ "+String.format("%.2f", valorDividido) );

                        }else if(valorConta.text.toString().toDouble() == 0.00  || nRachadores.text.toString().toDouble() == 0.0){
                                        Toast.makeText(this, "O valor da conta e o número de rachadores não podem ser zero!!", Toast.LENGTH_SHORT)
                                       super .findViewById<TextView>(R.id.valorTxt) .text = "R$ 0.00";
                        }
            }



}