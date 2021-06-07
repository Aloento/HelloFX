# HelloFX
记录了我学习JavaFX的历程，也可以作为你学习的参考资料  
内有大量中文注释，尽可能做到每一行都有

## Maven
首先，这是一个简单的Maven项目  
在任何支持Maven的IDE中打开即可使用

## JavaFX
其次，这是一个简单的JavaFX项目  
在Java11中被移除，交社区维护，改名叫OpenJFX  

使用Java17，在JVM启动参数中添加  
` --module-path "你的OpenJFX目录\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml `  
即可正常启动程序  
或者使用Launcher（一个额外的启动类）来调用它以忽略启动时检查

## IDE
我使用Intellij IDEA
