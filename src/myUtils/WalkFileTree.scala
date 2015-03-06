package myUtils

import java.io.File

/*
 * Recursively walks file tree
 * http://rosettacode.org/wiki/Walk_a_directory/Recursively#Scala
 */
object WalkFileTree { 
  def walkTree(file: File): Iterable[File] = {
    val children = new Iterable[File] {
      def iterator = if (file.isDirectory) file.listFiles.iterator else Iterator.empty
    }
    Seq(file) ++: children.flatMap(walkTree(_))
  }
}
      
//object Test extends App {
//  val dir = new File("C:/Julian/git/scalaTools")
//  for(f <- WalkFileTree.walkTree(dir) 
//      if f.getName.endsWith(".scala")) println(f)
//}
//      