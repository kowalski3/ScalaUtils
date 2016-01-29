package xmlUtils
import scala.io.Source

/*
 * Takes a directory of xml files, extracts all text elements from each one and writes contents to a new txt file 
 * with the same filename
 */

object RunLyricsToText {
  
  def run = {//Program starts here.  Enter the directory name below of the source directory
    val directory = ".//files"
    val x = new java.io.File(directory)
    println(x.isDirectory());
    LyricsToText.extractLyrics(directory)
  }
}

object LyricsToText  {
  import java.io.File
  import scala.collection.JavaConversions._
  
  
  def extractLyrics(directory: String) =
   
    getFileTree(new File(directory)).filter(_.getName.endsWith(".xml"))
                                    .foreach(xmlToTextFile)
  
  
  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree) 
        else Stream.empty)
          
             
  def xmlToTextFile(file:File) {
    import java.io._   
    import scala.xml.XML._

    val xmlFileName:String = file.toString().replaceAllLiterally("""\""", "/")
    val writer = new PrintWriter(new File(xmlFileName.substring(0,xmlFileName.length-4) + ".txt"))
    
    if(xmlFileName.endsWith(".xml"))
      (loadFile(xmlFileName) \\ "Text").foreach {Text => writer.write(Text.text.toString + "\n")}
    writer.close

     }
  
}

