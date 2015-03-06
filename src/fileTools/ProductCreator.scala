package fileTools

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import java.io._
import myUtils.WalkFileTree


object ProductCreatorRun extends App {
  
  val sourceDir = "C:/Julian/git/scalaTools"
  val dataFile ="C:/Julian/git/scalaTools/data/ProductCreatorData.csv"
  
  val x = new ProductCreator(sourceDir, dataFile)
  
 println( x.createProduct("SF345", ".wav"))
  
}


//TO DO - Fix -> mixing java and scala io here.
class ProductCreator(val sourceDir: File) {
  
  val albumMap = Map[String,Album]()
  
  def this(sourceDirName: String, dataFileName: String) = {
    this(new File (sourceDirName))
    val dataFile = scala.io.Source.fromFile(dataFileName)
    
  
    for (lines <- dataFile.getLines()) {
       // Get values from line
       val line = lines.split(",") 

       val albumid:String = line(0)
       val sfid:String = line(1) //need better of handling this
       val fileName:String = line(2)
   
       
       // Check if album already in map and add if not
       val result = albumMap.get(albumid)
       result match{
         case None => albumMap.put(albumid, new Album(albumid))        
         case Some(x) =>
       }
       

       albumMap.get(albumid).get.addTrack(sfid, fileName)
    } 
    
  }//constructor end
  
    def createProduct(albumid: String, format: String) = {
      val result = albumMap.get(albumid)
      
      result match{
         case None => println("Product code " + albumid + " doesn't exist.")
         case Some(album) => 
           for ( (songid, fileName) <- album.trackList) 
             WalkFileTree.walkTree(sourceDir)
             //println(songid)
       }
    }
    
    
    
}






