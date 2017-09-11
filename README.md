# android-8-demo

1.androd 8 画中画 通知点功能demo实现

2.项目中加入kotlin语言使用，简单通过xml以及kotlin代码实现viw的对比



# 真的可以实现秒开app么？

     背景:应用的启动时间是影响用户体验的重要一个方面。开发过程中，从点击app从桌面启动都有一个在桌面停留的时间，如果能节省这个时间，是不是可以优化用户体验呢，从这方面出发看能否节省启动时间。
     首先了解一下app启动的几个主要过程
     系统进程：load & lunch Application --> display window 
     应用进程：application onCreate --> Activity inite --> Activity onCreate --> inflate views
     主要回调函数：

          程序启动fork进程：Zygote Fork Proccess 
                              -> Application:attachBaseContext()
                              -> Application:onCreate() 
                              -> MainActiviity:onCreate()
                              
                              
    针对从程序启动到view创建过程中的这三个生命周期进行优化：
    
    1.生命周期内减少耗时操作：
      Application:onCreate()
      这个方法是需要重点优化的，因为大家的第三方插件初始化一般都会放在这里，在Application初始化做繁重的东西会严重阻塞app启动。
      针对于解决第三方插件初始化耗时方案一般是：
          1.SDK分优先级加载，非必要SDK由懒加载实现。
          2.可以多线程初始化的sdk由多线程方式来进行初始化。
      MainActiviity:onCreate()  activity的oncreate方法尽量不要在此布局做一些耗时的操作或者呈现一些过于复杂的布局
      
      
      2. 避免冷启动

        App启动方式一般有3种：

        · ColdStart ——冷启动：

        此种方式最为耗时，一般是因为进程被干掉，系统需要重新fork进程进行一系列初始化。

        ·  WarmStart ——暖启动

        比ColdStart稍快，因为app的所有Activities还常驻在内存中，并没有被杀掉，所做的只是把app从后台提到前台来展示，并不需要重走初始化一系列行为，减少了对象初始化、布局加载等工作。但其行为表现与冷启动一致，是会displays a blank screen直到App渲染activity。这个blank screen后面会解释。

        · LukeWarm Start——热启动

        启动方式最快，类似于返回键退出应用又立即进入的那种行为。

        优化方案：

        既然冷启动那么慢，我们就在非用户主动kill进程或系统通知kill进程的其他情况下不再主动退出进程。那答案很简单了，就是在位于Activity栈底activity中Hook其返回键行为，保证用户点击返回键后不再退出app。在我们App里位于我们栈底的一定是我们的MainActivity，因为一系统行为都是由其向下衍生的。
        所以，只需要在activity中重新onBackPress()方法，然后调用moveTaskToBack(true),moveTaskToBack：作用是不再Finish到此Activity，仅仅是把它放到后台隐藏。类似于用户主动触发系统Home键的效果。
        
       快速启动的猫腻---- WindowBackGround
       
       其实在创建App进程时，android系统会为你立即显示一个background window，然后再去创建app进程，当app完成first draw时，会立即由你的MainActivity（即默认启动的Activity）替换掉它。这里的background window就是上文WarmStart中提到的blank screen。谜底到此解开所谓的秒开原来就是视觉欺骗。。。所以说有人给你说他只是仅仅是优化生命周期内初始化代码达到秒开都是扯淡。但不得不承认这样用户体验大大的提升了，一点击launcher就渲染好一个背景图片，给用户一种已经启动的感觉，前面做的一系列优化，不过为了让用户少看一会儿系统给渲染的black window。
       
       所以，可以在启动时候自定义一个图片作为闪屏页
        <!--自定义启动闪屏页-->
    <style name="splashTheme" parent="AppTheme">
        <item name="android:windowFullscreen">true</item>
        <item name="android:windowBackground">@drawable/ic_autocomplete_logo_24dp</item>
        <item name="android:windowIsTranslucent">false</item>
    </style>
        然后，在mainactivity的oncreate方法中加入setTheme(R.style.AppTheme)，把主题再修改回来
        




