package dataParse

import scala.collection.mutable.Map
import java.io._

object run extends App{
  
  val writer = new PrintWriter(new File("C:/Julian/git/scalaTools/data/test.txt" ))
  
  val x = new DataMap("C:/Julian/git/scalaTools/data/spotifyData.csv")
  x.scan
  for ((key, value) <- x.albums) writer.write(value.toString)
  
  writer.close
}



class DataMap (val fileName: String){
 
  val albums = Map[String,Album]()
  val file = scala.io.Source.fromFile(fileName)
 
  

  //this method is bad, needs refactoring!
 def scan{
   for (lines <- file.getLines()) {
     
     //parse line
     val line = lines.split(",")
     val albumTitle:String = line(0)
     val trackTitle:String = line(1)
     val total:Double = line(2).toDouble
     val streams:Int =line(3).toInt
     
     //checks if album already in map and add it not
     val result = albums.get(albumTitle)
     result match{
       case None => albums.put(albumTitle, new Album(albumTitle))
       case Some(x) =>
     }
     

       albums.get(albumTitle).get.addTrack(trackTitle, total, streams)

    }
   
   for ((key, value) <- albums) value.calculateAlbumData
  
}
  
 
  
}







class Album (val name:String){
  var totals:Double = 0
  var streams = 0
  val tracks = Map[String,Track]()
    
  def addTrack (trackTitle:String, total:Double, streams:Int ) {
    val result = tracks.get(trackTitle)
      result match{
       case None => tracks.put(trackTitle, new Track(trackTitle, total, streams))
       case Some(x) => tracks.get(trackTitle).get.updateValues(total,streams)
     }
    
    
  }
   
  
  def calculateAlbumData {
   for ((key, value) <- tracks){
     totals += value.total
     streams += value.streams
   }
    
  }
  
  override def toString: String = {
    var text = "" 
    
    for ((key, value) <- tracks) 
      text += name + ","  + value.toString  
    
      text  + "TOTAL ," + name + "," + totals + " , " + streams + "\n\n"
  }
   
}
  


class Track(val name:String,
            var total:Double,
            var streams:Int)
            {
  
  def updateValues(total:Double, streams:Int){
    this.total += total
    this.streams += streams
   }
  
  override def toString: String = {
   name + " , " + total + " , " + streams + "\n"
  }
   
}
            