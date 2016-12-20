#finagle-example

* [http://eax.me/scalatest/](http://eax.me/scalatest/)
* jdk-1.7.0
* -Dsbt.version=0.13.9


>
1. В SBT-проекте должна присутствовать директория  ' **project** '  с конфиггурационным файлом, внутри нее  ' **plugins.sbt** '.
   А также в корне этого SBT-проекта должен присутствовать основноной конфиггурационный файл  ' **build.sbt** '.
2. Делаем **импорт** SBT-проекта, при этом выбираем:
   - основноной конфиггурационный файл  ' **build.sbt** ' из корня этого SBT-проекта [IntelliJIDEA/Getting+Started+with+SBT](https://confluence.jetbrains.com/display/IntelliJIDEA/Getting+Started+with+SBT);
   - (ошибка в IntelliJIDEA-14 [SBT-0.13.9 fail java.lang.ClassNotFoundException](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206633295-SBT-0-13-9-fail-java-lang-ClassNotFoundException-org-jetbrains-sbt-ReadProject-)) разварачиваем и настраиваем пункт **Global SBT settings**
     - в  ' **JVM** '  >>  ' **Custom** '  выбрать **jdk-1.6**;
     - добавляем параметр в  ' **JVM Options** ': **-Dsbt.version=0.13.9**;
     - предварительно в  ' **Launcher (sbt-launch.jar)** '  >>  ' **Custom** '  выбрать уже установленный ( **C:\Program Files (x86)\sbt\bin\sbt-launch.jar** ) и переключить на  ' **Bundled** ';
3. После **импорта** SBT-проекта нужно, в настройках проекта IntelliJIDEA, заменить с **jdk-1.6** на **jdk-1.7.0**.

