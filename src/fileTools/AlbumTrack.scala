package fileTools

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

/*
 * ALBUM CLASSES
 */
case class Album(val albumid: String) {  
  val trackList = Map[String, String]() //map from songid to filename
  

 def addTrack(sfid: String, fileName:String) = {
    trackList.put(sfid, fileName)
  }
 
}
