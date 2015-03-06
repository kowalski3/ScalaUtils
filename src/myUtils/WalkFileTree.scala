package myUtils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
/*
 * Recursively walks file tree
 * http://rosettacode.org/wiki/Walk_a_directory/Recursively#Scala
 */
object MyFileUtils { 
  def walkTree(file: File): Iterable[File] = {
    val children = new Iterable[File] {
      def iterator = if (file.isDirectory) file.listFiles.iterator else Iterator.empty
    }
    Seq(file) ++: children.flatMap(walkTree(_))
  }
  
  
  def copyFile(src: File, dest: File){
    
    new FileOutputStream(dest) getChannel() transferFrom(
       new FileInputStream(src) getChannel, 0, Long.MaxValue)
  }
  
  
}
      
//object Test extends App {
//  val dir = new File("C:/Julian/git/scalaTools")
//  for(f <- WalkFileTree.walkTree(dir) 
//      if f.getName.endsWith(".scala")) println(f)
//}
//      