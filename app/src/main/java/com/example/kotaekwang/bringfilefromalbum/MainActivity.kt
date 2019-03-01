package com.example.kotaekwang.bringfilefromalbum

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val Gallery = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set listener on button
        loadImage_button.setOnClickListener{ loadImage() }
    }

    private fun loadImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent,"Load Picture"), Gallery)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Gallery){
            if(resultCode == RESULT_OK){
                var dataUri = data?.data
                try{
                    var bitmap : Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,dataUri)
                    imageView.setImageBitmap(bitmap)
                }catch (e:Exception){
                    Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
                }
           }
            else{
                //something wrong
            }
        }
    }


}
