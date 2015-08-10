package dataParse

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import java.io._

/**
 * Imports a CSV of album data and returns the CSV ready for import into CMS
 * 
 * @author julian
 * 
 */
class AlbumDataPrep(val sourceFileName:String) {
  val albumMap = Map[String, SFAlbum]()
  val file = scala.io.Source.fromFile(sourceFileName)
  
  
  def writeToFile(path: String, txt: String): Unit = {
     val pw = new PrintWriter(new File(path ))
     pw.write(txt)
     pw.close   
    }
  
  
  def prepData{
     for (lines <- file.getLines()) {
        val line = lines.split("\\,", -1); //makes sure empty fields are split too
        //println(line(1))
        val albumSku_0 = line(0)
       println(line(0));
           if(line(2).isEmpty()){ //i.e column C "Track No is empty" then this is album row
       
             val range_5 = line(5)
             val subRange_6 = line(6)
             val format_7 = line(7)
             val compStatus_8 = line(8)
             val upc_9 = line(9)
             val notes_10 = line(10)
             val stockLevel_11 = line(11)
             val repress_12 = line(12)
             val price_13 = line(13)
             val weight_14 = line(14)
             val tracks = new ListBuffer[SFAlbumTrack]
           
            // println(albumSku_0)
             val album = new SFAlbum(albumSku_0,
                                     range_5, 
                                     subRange_6, 
                                     format_7, 
                                     compStatus_8, 
                                     upc_9, 
                                     notes_10, 
                                     stockLevel_11, 
                                     repress_12,
                                     price_13,
                                     weight_14,
                                     tracks)
             albumMap.put(albumSku_0, album) 
            
           } else{ //is a track line 
             var discNo_1 = -1 
             if(! line(1).isEmpty()) discNo_1 = line(1).toInt
             val trackNo_2 = line(2).toInt 
             val productCode3 = line(3)
             val trackSku_4 = line(4)
             val sfAlbumTrack = new SFAlbumTrack(discNo_1, trackNo_2, productCode3, trackSku_4)
             val x = albumMap.get(albumSku_0).get.getTracks += sfAlbumTrack
           }  
     }    
  } 
}


class SFAlbum(val albumSku_0:String,
              val range_5:String,
              val subRange_6:String,
              val format_7:String,
              val compStatus_8:String,
              val upc_9:String,
              val notes_10:String,
              val stockLevel_11:String,
              val repress_12:String,
              val price_13:String,
              val weight_14:String,
              val tracks:ListBuffer[SFAlbumTrack]
              ){

  def getTracks: ListBuffer[SFAlbumTrack] = {
    tracks
  }
  
  def getAlbumData:String = {
    return albumSku_0 + "," +
    tracks.foldLeft(new StringBuffer()){ (sb, s) => sb append s.discNo_1.toString() + "|"}.toString() + "," + //discNos
    tracks.foldLeft(new StringBuffer()){ (sb, s) => sb append s.trackNo_2.toString()  + "|"}.toString() + "," + //trackNos
    tracks.foldLeft(new StringBuffer()){ (sb, s) => sb append s.productCode3.toString()  + "|"}.toString() + "," + //productCodes
    tracks.foldLeft(new StringBuffer()){ (sb, s) => sb append s.trackSku_4.toString()  + "|"}.toString() + "," +  //trackSku
    range_5 + "," +
    subRange_6 + "," +
    format_7 + "," +
    compStatus_8 + "," +
    upc_9 + "," +
    notes_10 + "," +
    stockLevel_11 + "," +
    repress_12 + "," +
    price_13 + "," +
    weight_14 + "\n"
  }
}


class SFAlbumTrack(val discNo_1: Int,
                   val trackNo_2: Int,
                   val productCode3: String,
                   val trackSku_4:String){
  
  def getTrackData:String = {
    discNo_1.toString() + "," +
    trackNo_2.toString() + "," +
    productCode3.toString() + "," +
    "SF" + trackSku_4.toString()
    
  }
  
  
  
  
}
   
                   
object AlbumPrepRunner extends App{
  
  val albumPrep = new AlbumDataPrep("C:/Julian/git/scalaTools/data/albumDataPrep.csv")
  albumPrep.prepData
  
  var str = new StringBuffer()
  for ((key, value) <- albumPrep.albumMap){
   
    str.append(value.getAlbumData)
  }
  
  albumPrep.writeToFile("C:/Julian/git/scalaTools/output/albumPrep.txt", str.toString())
  
  
}