package com.example.fpv

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import kotlin.properties.Delegates

class DonneesGyroscope : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    private lateinit var textView_xvalue: TextView
    private lateinit var textView_yvalue: TextView
    private lateinit var textView_zvalue: TextView
    private var minValue by Delegates.notNull<Double>()

    //private val socket = Socket("192.168.0.10", 8080) // création du socket
    //private val inputStream = DataInputStream(socket.getInputStream()) // ouverture des entrée
    //private val outputStream = DataOutputStream(socket.getOutputStream()) // ouverture des sorties

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donnees_gyroscope)

        textView_xvalue = findViewById(R.id.textView_xvalue)
        textView_yvalue = findViewById(R.id.textView_yvalue)
        textView_zvalue = findViewById(R.id.textView_zvalue)
        minValue = 0.01

        // Obtenir une référence au SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Obtenir une référence au gyroscope
        val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        // Enregistrer un écouteur pour recevoir des mises à jour de la part du gyroscope
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {

        // Vérifier si l'événement provient du gyroscope
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {

            // Récupérer les valeurs de l'accélération en x, y et z
            var x = event.values[0]
            var y = event.values[1]
            var z = event.values[2]

            // Utiliser les valeurs de l'accélération

            val arrondi = CArrondi()
            x = arrondi.arrondir(x, 2)
            y = arrondi.arrondir(y, 2) // on arrondi chaque valeurs avec la classe arrondir
            z = arrondi.arrondir(z, 2)

            textView_xvalue.text = x.toString()
            textView_yvalue.text = y.toString() // on affiche toutes les valeurs du gyroscope à l'écran
            textView_zvalue.text = z.toString()

            //outputStream.writeFloat(x)
            //outputStream.writeFloat(y) // on envoie chaque variables
            //outputStream.writeFloat(z)
            //outputStream.flush() // on s'assure d'avoir envoyer toute les infos de la sortie et on vide le cache

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onStop() { // lorsque l'acivité s'arrète complétement
        super.onStop()

        //inputStream.close()
        //outputStream.close() // on ferme toutes les entrées/sorties et le socket
        //socket.close()
    }

}