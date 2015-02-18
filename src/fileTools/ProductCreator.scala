package fileTools

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import java.io._


object ProductCreatorRun extends App {
  val x = new ProductCreator("")
}



class ProductCreator {
  
  val albumMap = Map[String,Album]()
  
  //var productMap = 0
  
  def this(fileName: String) = {
    this()
    val file = scala.io.Source.fromFile(fileName)
    
  
    for (lines <- file.getLines()) {
       // Get values from line
       val line = lines.split(",") 
       val albumid:String = line(0) ;  val albumTrackNo:String = line(1) ; val sfid:String = line(2) ; val artistTrackName =line(3)
       
       // Check if album already in map and add if not
       val result = albumMap.get(albumid)
       result match{
         case None => albumMap.put(albumid, new Album(albumid))
         case Some(x) =>
       }
       //add album to map
       albumMap.get(albumid).get.trackList.+=(new Track(albumTrackNo, sfid, artistTrackName))
    }
     
    
   }//constructor ends here
  
 }









class Album(val albumid: String){  
  val trackList = ListBuffer[Track]()
  }
            
  
class Track(val albumTrackNo:String, 
            val sfid: String, 
            val artistTrackName:String)


