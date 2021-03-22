#### 使用说明文档 
水波纹涟漪效果控件
  
- [x] 支持颜色、速度、圆数量、实心/空心圆、圆宽度配置
- [x] 支持Lifecycle绑定，自动暂停、恢复动画

#### 效果预览
<img src="https://raw.githubusercontent.com/Leo199206/RippleView/main/image/device-2021-03-22-132121.gif" width="300" heght="500" align=center />


#### 依赖
+ 添加maven仓库配置到项目根目录gradle文件下

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

+ 添加以下maven依赖配置到app模块，gradle文件下

```
implementation  'com.github.Leo199206:RippleView:1.0.0'
```

#### 添加到布局

```
    <com.ripple.view.RippleView
        android:id="@+id/ripple_fill"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:layout_gravity="center"
        android:layout_marginBottom="20dp"
        app:ripple_circle_color="@color/teal_700"
        app:ripple_circle_count="3"
        app:ripple_circle_min_radius="10dp"
        app:ripple_circle_style="FILL"
        app:ripple_speed="0.5" />

```

+ 代码配置

```
ripple_fill.onStart(this)

```


#### 已定义样式属性

| 属性  | 说明 |
| --- | --- |
| ripple_circle_color | 圆圈颜色 |
| ripple_circle_min_radius | 圆圈起始最小半径 |
| ripple_circle_count | 圆圈起始半径~圆圈最大半径间的圆圈数量 |
| ripple_circle_style | 圆圈风格 FILL/STROKE  |
| ripple_speed | 涟漪圆圈扩散速度，浮点数，默认0.5f | 
| ripple_circle_stroke_width | 圆圈线条宽度，STROKE先有效 | 


#### 支持
+ 使用过程中，如有问题或者建议，欢迎提出issue
+ 如遇issue解决不了的问题，可以邮件联系：850884963@qq.com
+ 开源维护不易，如果该库对你有帮助，请动动你的小手指，给个star🤩  

#### LICENSE
RippleView is under the Apache License Version 2.0. See the [LICENSE](https://raw.githubusercontent.com/Leo199206/RippleView/main/LICENSE) file for details.
