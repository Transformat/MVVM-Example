package bridgingtech.denefits.modules.baseModule

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import self.mvvmexample.R
import self.mvvmexample.utils.FontUtils

abstract class BaseActivity : AppCompatActivity() {

    private var baseView: View? = null

    private var currentClass: Class<*>? = null

    var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)
        baseView = this.findViewById(android.R.id.content)
        overrideFonts(this, baseView)


    }

    override fun onResume() {
        currentClass = this.javaClass
        super.onResume()
        val bundle = Bundle()
    }

    fun setupSnackBar(view: View) {
        snackBar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
        val sbView = snackBar?.view
        val tv = sbView?.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        tv.typeface = FontUtils.getFontBold(this)
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.no_internet_background))
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun adjustFontScale(configuration: Configuration) {
        configuration.fontScale = 1.toFloat()
        val metrics = resources.displayMetrics
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }


    companion object {

        fun overrideFonts(context: Context, v: View?) {
            try {
                if (v is ViewGroup) {
                    val vg = v as ViewGroup?
                    for (i in 0 until vg!!.childCount) {
                        val child = vg.getChildAt(i)
                        overrideFonts(context, child)
                    }
                }
                if (v!!.javaClass == TextView::class.java) {
                    if (v.tag != null && v.tag.toString().equals("bold", ignoreCase = true)) {
                        (v as TextView).typeface = FontUtils.getFontBold(context)
                    } else {
                        (v as TextView).typeface = FontUtils.getFont(context)
                    }
                }

                if (v.javaClass == AppCompatTextView::class.java) {
                    if (v.tag != null && v.tag.toString().equals("bold", ignoreCase = true)) {
                        (v as TextView).typeface = FontUtils.getFontBold(context)
                    } else {
                        (v as TextView).typeface = FontUtils.getFont(context)
                    }
                }

                if (v.javaClass == Button::class.java) {
                    if (v.tag != null && v.tag.toString().equals("button_shadow", ignoreCase = true)) {

                    }
                }
                if (v.javaClass == Button::class.java) {
                    v.setOnTouchListener { view, motionEvent ->
                        when (motionEvent.action) {
                            MotionEvent.ACTION_DOWN -> view.animate().scaleX(0.97f).scaleY(0.97f).setDuration(200).start()
                            MotionEvent.ACTION_UP -> view.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                        }

                        false
                    }
                    (v as Button).typeface = FontUtils.getFontBold(context)
                }
                if (v.javaClass == CheckBox::class.java) {
                    (v as CheckBox).typeface = FontUtils.getFontBold(context)
                }
                if (v.javaClass == RadioButton::class.java) {
                    (v as RadioButton).typeface = FontUtils.getFontBold(context)
                }
                if (v.javaClass == AppCompatButton::class.java) {
                    (v as Button).typeface = FontUtils.getFontBold(context)
                }

                if (v.javaClass == TextInputLayout::class.java) {
                    (v as TextInputLayout).setTypeface(FontUtils.getFontBold(context))
                }
                if (v.javaClass == TextInputEditText::class.java) {
                    (v as TextInputEditText).typeface = FontUtils.getFont(context)
                }
                if (v.javaClass == AppCompatEditText::class.java) {
                    (v as AppCompatEditText).typeface = FontUtils.getFont(context)
                }
                if (v.javaClass == EditText::class.java) {
                    if (v.tag != null && v.tag.toString().equals("bold", ignoreCase = true)) {
                        (v as EditText).typeface = FontUtils.getFontBold(context)
                    } else {
                        (v as EditText).typeface = FontUtils.getFont(context)
                    }
                }
            } catch (e: Exception) {
                Log.e("Font Exception", e.toString())
            }

        }

        fun changeTabsFont(tabLayout: TabLayout, context: Context) {

            val vg = tabLayout.getChildAt(0) as ViewGroup
            val tabsCount = vg.childCount
            for (j in 0 until tabsCount) {
                val vgTab = vg.getChildAt(j) as ViewGroup
                val tabChildCount = vgTab.childCount
                for (i in 0 until tabChildCount) {
                    val tabViewChild = vgTab.getChildAt(i)
                    if (tabViewChild is TextView) {
                        tabViewChild.typeface = FontUtils.getFontBold(context)
                    }
                }
            }
        }
    }


}

