package xml

/*
 * Takes an xml file, extracts all text elements and writes contents to txt file with same filename
 */

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

object RunLyricsToText extends App{
  val directory = "C:/Users/Julian.SUNFLYKARAOKE/Desktop/K Chan Swear Check/NEED CHECKING"
  LyricsToText.extractLyrics(directory)
}
 
   
