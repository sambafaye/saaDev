echo MAVEN BUILD
mvn clean eclipse:clean eclipse:eclipse -DskipTests install
echo SUPPRESSION des fichiers application.xml
del ord-ear\target\eclipseEar\META-INF\application.xml
del ord-ear\target\ord-wsserveur\META-INF\application.xml
