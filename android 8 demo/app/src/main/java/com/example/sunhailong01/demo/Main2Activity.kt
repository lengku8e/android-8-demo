package com.example.sunhailong01.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.*

class Main2Activity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val User = User("xml实现", 11)

    relativeLayout {
        var textview = textView{
            id = TEXT_VIEW
            text  = "helloworld"
            textSize = 30.0f
            textColor = R.color.abc_btn_colored_borderless_text_material
            lparams {
                width = matchParent
                height = wrapContent
                margin = dip(8)
            }
        }
        var one = button {
            id = ONE_BUTTON
            text = "ONE_BUTTON"
            lparams {
                width = matchParent
                height = wrapContent
                margin = dip(8)
                below(TEXT_VIEW)
            }
            onClick {
                reformat(textview, textview.text.toString(), normalizeCase = true, upperCaseFirstLetter = true,
                        divideByCamelHumps = false, wordSeparator = '_')
                context.longToast("helloworld")

            }
        }.setBackgroundColor(R.color.abc_btn_colored_borderless_text_material)
        var two = button {
            id= TWO_BUTTON
            text="TWO_BUTTON"
            lparams {
                width = matchParent
                height = wrapContent
                margin = dip(8)
                below(ONE_BUTTON)
            }
            onClick {
                textview.text = ""
                gone()

            }
        }
        var three = button {
            id= THREE_BUTTON
            text="THREE_BUTTON"

            lparams {
                width = matchParent
                height = wrapContent
                margin = dip(8)
                below(TWO_BUTTON)
            }
        }

    }
//
//        setContentView(R.layout.activity_main2)
//
//        kotlin_textview.text = "代码实现view"
//        kotlin_textview.textSize = 20.0f
//
//        one.setOnClickListener {
//            reformat(kotlin_textview, User.name, normalizeCase = true, upperCaseFirstLetter = true,
//                    divideByCamelHumps = false, wordSeparator = '_')
//        }

    }

    fun reformat(obj : TextView ?, str: String, normalizeCase: Boolean = true, upperCaseFirstLetter: Boolean = true,
                 divideByCamelHumps: Boolean = false,
                 wordSeparator: Char = ' ') {
        if (obj == null) {
            return
        }
        obj.text = (str + normalizeCase + upperCaseFirstLetter + divideByCamelHumps + wordSeparator );
    }

    companion object static {
        val ONE_BUTTON: Int = 1
        val TWO_BUTTON: Int = 2
        val THREE_BUTTON: Int = 3
        val TEXT_VIEW : Int = 4
    }








}
