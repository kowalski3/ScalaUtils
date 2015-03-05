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

       val albumid:String = line(0)
       val discNo:String = line(1) //need better of handling this
       val albumTrackNo:String = line(2)
       val sfid:String = line(3) 
       val artistTrackName =line(4)
       
       // Check if album already in map and add if not
       val result = albumMap.get(albumid)
       result match{
         case None => if(discNo.length == 0) 
                         albumMap.put(albumid, new AlbumNoDisc(albumid))
                       else
                         albumMap.put(albumid, new AlbumWithDisc(albumid))
         case Some(x) =>
       }

       albumMap.get(albumid).get.addTrack(albumTrackNo, sfid, discNo, artistTrackName)
         
    }

    //constructor ends here
  
 }
}


trait Album{
  def addTrack(albumTrackNo:String, sfid:String, discNo: String, artistTrackName:String)
  
}


class AlbumNoDisc(val albumid: String) extends Album{  
  private val trackList = ListBuffer[Track]()
  
 @Override 
 def addTrack(albumTrackNo:String, sfid:String, discNo: String, artistTrackName:String) = {
    trackList += new Track(albumTrackNo, sfid, artistTrackName)
  }
}

class AlbumWithDisc(albumid: String) extends Album{
  private val discMap = Map[String,ListBuffer[Track]]() //map from discno to tracklist
  
  @Override 
  def addTrack(albumTrackNo:String, sfid:String, discNo: String, artistTrackName:String) = {
    val result = discMap.get(discNo)
    result match{
      case None =>   discMap.put(discNo, new ListBuffer[Track]())
      case Some(x) =>
     }
    
    
    discMap.get(discNo).get.+=(new Track(albumTrackNo, sfid, artistTrackName))
    
  }
}
           
  
class Track(val albumTrackNo:String, 
            val sfid: String, 
            val artistTrackName:String)


