package dataParse

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import java.io._

/**
 * @author julian
 */
class StreamingDataPrep(val sourceFileName:String) {
  val streamingPlaylistMap = Map[String, ListBuffer[String]]()
  val file = scala.io.Source.fromFile(sourceFileName)
  
  
    def prepData{
     for (lines <- file.getLines()) {
        val line = lines.split("\\,", -1); //makes sure empty fields are split too
        //println(line(1))
        val albumSku = line(0)
        val track = line(1)
        println(line(0));
        
        val result = streamingPlaylistMap.get(albumSku)
        result match{
          
           case None => streamingPlaylistMap.put(albumSku, new ListBuffer[String])
           case Some(x) =>
        }
        
        streamingPlaylistMap.get(albumSku).get.+=(track)
     }    
  } 
  
  
  
  def writeToFile(path: String, txt: String): Unit = {
     val pw = new PrintWriter(new File(path ))
     pw.write(txt)
     pw.close   
    }
  
  def listToString(list: ListBuffer[String]) ={
    list mkString "|"
  }
  
}


object StreamingDataPrepRunner extends App{
  
  val albumPrep = new StreamingDataPrep("C:/Julian/git/scalaTools/data/streamingImport.csv")
  albumPrep.prepData
  var str = new StringBuffer()
  for ((key, value) <- albumPrep.streamingPlaylistMap){
    str.append(key + "," + albumPrep.listToString(value) + "\n")
  }
  
  albumPrep.writeToFile("C:/Julian/git/scalaTools/output/streamingDataPrep.txt", str.toString())
  
  
}