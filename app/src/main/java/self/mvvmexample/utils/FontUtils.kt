package self.mvvmexample.utils

import android.content.Context
import android.graphics.Typeface


object FontUtils {

    var regular: Typeface? = null
    var bold: Typeface? = null

    fun getFont(appContext: Context): Typeface {
        if (regular == null) {
            regular = Typeface.createFromAsset(appContext.assets, "fonts/Proxima-Nova-Regular.ttf")
        }
        return regular!!
    }


    fun getFontBold(appContext: Context): Typeface {
        if (bold == null) {
            bold = Typeface.createFromAsset(appContext.assets, "fonts/Proxima-Nova-Semibold.ttf")
        }
        return bold!!
    }
}
