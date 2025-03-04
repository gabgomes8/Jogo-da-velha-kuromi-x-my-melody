package com.example.jogodavelha

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    //Vetor bidimensional que representará o tabuleiro de jogo
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    //Qual o Jogador está jogando
    var jogadorAtual = "My Melody"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    //Função associada com todos os botões @param view é o botão clicado
    fun buttonClick(view: View) {
        //O botão clicado é associado com uma constante
        val buttonSelecionado = view as Button

        //De acordo com o botão clicado, a posição da matriz receberá o Jogador
        when(buttonSelecionado.id){
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }

        buttonSelecionado.setBackgroundResource(R.drawable.melody)
        buttonSelecionado.isEnabled=false

        //Recebe o jogador vencedor através da função verificaTabuleiro. @param tabuleito
        var vencedor = verificaVencedor(tabuleiro)

        if(!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        var rX = Random.nextInt(0, 3)
        var rY = Random.nextInt(0, 3)
var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)

            if (tabuleiro[rX][rY] != "My Melody" && tabuleiro[rX][rY] != "Kuromi") {
                break
            }

            i++
        }

        tabuleiro[rX][rY]="Kuromi"

        val posicao = rX*3 + rY

        when(posicao){
            0 -> {
                binding.buttonZero.setBackgroundResource(R.drawable.kuromi)
                binding.buttonZero.isEnabled = false
            }
            1 -> {
                binding.buttonUm.setBackgroundResource(R.drawable.kuromi)
                binding.buttonUm.isEnabled = false
            }
            2 -> {
                binding.buttonDois.setBackgroundResource(R.drawable.kuromi)
                binding.buttonDois.isEnabled = false
            }
            3 -> {
                binding.buttonTres.setBackgroundResource(R.drawable.kuromi)
                binding.buttonTres.isEnabled = false
            }
            4 -> {
                binding.buttonQuatro.setBackgroundResource(R.drawable.kuromi)
                binding.buttonQuatro.isEnabled = false
            }
            5 -> {
                binding.buttonCinco.setBackgroundResource(R.drawable.kuromi)
                binding.buttonCinco.isEnabled = false
            }
            6 -> {
                binding.buttonSeis.setBackgroundResource(R.drawable.kuromi)
                binding.buttonSeis.isEnabled = false
            }
            7 -> {
                binding.buttonSete.setBackgroundResource(R.drawable.kuromi)
                binding.buttonSete.isEnabled = false
            }
            8 -> {
                binding.buttonOito.setBackgroundResource(R.drawable.kuromi)
                binding.buttonOito.isEnabled = false
            }
        }

        vencedor = verificaVencedor(tabuleiro)

        if(!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {

        // Verifica linhas e colunas
        for (i in 0 until 3) {
            //Verifica se há três itens iguais na linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            //Verifica se há três itens iguais na coluna
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }
        //Verifica a quantidade de jogadores 
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if(valor.equals("My Melody")||valor.equals("Kuromi")){
                    empate++
                }
            }
        }
        //Se existem 9 jogadas e não há três letras iguais, houve um empate
        if(empate == 9){
            return "Empate"
        }
        // Nenhum vencedor
        return null
    }
}