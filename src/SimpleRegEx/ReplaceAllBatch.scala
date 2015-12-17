package SimpleRegEx
import java.io._
import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
 * Takes a list of words you can to convert from and next to them, the word to convert to.
 * Processes a directory of text files based on this
 */

object ReplaceAllBatch extends App {
  //Source file directory
  val directoryName = "C:/Julian/txt1"
  val sourceDirectory = new File(directoryName)
  
  //SETUP words
  
  var words = new ListBuffer[(String,String)]()
  val wordCsv = "C:/Julian/git/scalaTools/data/wordConversion.csv"
  //Load words for conversion into words list
  for (line <- Source.fromFile(wordCsv).getLines()){
    val x = line.split(",")
    if(x(1).length() > 0) {
      words += ((x(0), x(1)))
    }
  } 

  // RegEx's
  //finds words certain characters in the middle
  val midWordBad = "[a-z][^a-zA-Z '-*-θφόίδ.,ιρακϊ?’ηνσ?γ][a-z]+".r 
  //finds words with question marks in the middle
  val questionMarkPattern = "[a-z][?][a-z]+".r 
  
  //PROCESS
  
  for(file <- sourceDirectory.listFiles() ) {
    
  
    var lines = scala.io.Source.fromFile(file).mkString
 
    
    for(word <- words){
      lines.replaceAllLiterally(word._1, word._2)
    }

    new PrintWriter("C:/Julian/txt1/" + file.getName+"FIXED"+".txt") { write(lines); close }
  }
     

}