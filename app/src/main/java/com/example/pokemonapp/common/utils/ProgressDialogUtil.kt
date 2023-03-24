package com.example.pokemonapp.common.utils


import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.graphics.drawable.toDrawable
import com.example.pokemonapp.R


object ProgressDialogUtil {

    lateinit var progressDialog: Dialog
    fun showLoadingProgress(context: Context): Dialog {

        progressDialog = Dialog(context)
        progressDialog.let {
            //it.show()
            it.window!!.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog_view)
            it.setCancelable(true)
            it.setCanceledOnTouchOutside(true)

            return it
        }
    }

    fun start(){
        progressDialog.show()
    }
    fun stop(){
        progressDialog.cancel()
        progressDialog.dismiss()
    }

}


/*
class ProgressDialogUtil(val context: Context) {

    companion object {

      @Volatile private var instance: ProgressDialogUtil? = null

        fun getInstance(context: Context) : ProgressDialogUtil{

            if(instance == null)
                instance = ProgressDialogUtil(context = context)

            return instance!!
        }
    }

    private lateinit var dialog: CustomDialog
    val inflater = LayoutInflater.from(context)
    val view = inflater.inflate(R.layout.progress_dialog_view, null)
    init {
        // Custom Dialog initialization
    }

    var title:String = ""
    fun start(title: String = "") {
        this.title = title

        dialog = CustomDialog(context)
        dialog.setContentView(view)

        dialog.show()

    }

    fun stop() {

            dialog.cancel()
            dialog.dismiss()

    }

    class CustomDialog(context: Context) : Dialog(context) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            //window?.decorView?.rootView?.setBackgroundResource(R.color.black)
            window!!.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            //window?.decorView?.rootView?.setBackgroundResource(R.color.transparent)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}
*/