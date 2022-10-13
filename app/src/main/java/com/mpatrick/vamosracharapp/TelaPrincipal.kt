package com.mpatrick.vamosracharapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

// mascarando campos:
// https://www.youtube.com/watch?v=4bbF4I_ZaG4&t=256s


class TelaPrincipal : AppCompatActivity() {

            override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

            /*
                   val  btnVolume: Button = super.findViewById(R.id.volumeIcon);
                   val btnShare: Button = super.findViewById(R.id.shareIcon);

                    btnVolume.setOnClickListener(){
                    }
             */

                val  campoValorTotal :EditText = super.findViewById(R.id.inputValor);

                campoValorTotal.addTextChangedListener {

                        Toast.makeText( this, "Campo alterado!!!", Toast.LENGTH_SHORT).show();
                }



                val  campoNrachadores :EditText = super.findViewById(R.id.inputNumRachadores);

                campoNrachadores.addTextChangedListener {

                         if( campoValorTotal.text.equals("0,00") || campoValorTotal.text.equals("") ) {
                                 Toast.makeText(this,"Por favor, digite o valor total!!", Toast.LENGTH_SHORT).show();
                         }else {
                                 var valorDividido = campoValorTotal.text.toString().toDouble()  / campoNrachadores.text.toString().toInt() ;
                                 Toast.makeText( this,"O valor total é: ${valorDividido}",  Toast.LENGTH_SHORT).show();
                         }
                }




                /*
                    .addTextChangedListener(  obj: TextWatcher {
                });  */
            }



}