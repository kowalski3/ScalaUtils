package dataParse

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

/**
 * Imports a CSV of album data and returns the CSV ready for import into CMS
 * 
 * @author julian
 * 
 */
class AlbumDataPrep(val sourceFileName:String) {
  val albumMap = Map[String, ListBuffer[SFAlbumTrack]]()
  val file = scala.io.Source.fromFile(sourceFileName)
  
  def prepData{
     for (lines <- file.getLines()) {
       val line = lines.split(",")
       val albumSku = line(0)
           if(line(2).isEmpty()){ //i.e column C "Track No is empty" then this is album row
             val albumSku = line(0)
             val range = line(5)
             val subRange = line(6)
             val format = line(7)
             val compStatus = line(8)
             val upc = line(9)
             val notes = line(10)
             val stockLevel = line(11)
             val repress = line(12)
             val tracks = new ListBuffer[SFAlbumTrack]
             
             val album = new SFAlbum(albumSku, 
                                     range, 
                                     subRange, 
                                     format, 
                                     compStatus, 
                                     upc, 
                                     notes, 
                                     stockLevel, 
                                     repress,
                                     tracks) 
             albumMap.put(albumSku, tracks)
            
              
           } else{ //is a track line
             val discNo = line(1).toInt  
             val trackNo = line(2).toInt 
             val trackSku = line(4)
             val SFAlbumTrack = new SFAlbumTrack(discNo, trackNo, trackSku)
             
             albumMap.get(albumSku).get+=(SFAlbumTrack)
            
           }
       
     }
     
    
    
  }
  
  
}


class SFAlbum(val albumSku:String,
              val range:String,
              val subRange:String,
              val format:String,
              val compStatus:String,
              val upc:String,
              val notes:String,
              val stockLevel:String,
              val repress:String,
              val tracks:ListBuffer[SFAlbumTrack])


class SFAlbumTrack(val discNo: Int,
                   val trackNo: Int,
                   val trackSku:String)
   
                   
object AlbumPrepRunner extends App{
  
  val albumPrep = new AlbumDataPrep("C:/Julian/git/scalaTools/data/spotifyData.csv")
  
  
}