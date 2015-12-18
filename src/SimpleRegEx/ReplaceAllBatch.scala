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
  val directoryInName = "C:/Julian/txtIn"
  val directoryOutName = "C:/Julian/txtOut/"
  val sourceInDirectory = new File(directoryInName)
  
  //SETUP words
  
  var words = new ListBuffer[(String,String)]()
  val wordCsv = "C:/Julian/git/scalaTools/data/wordConversion.csv"
  //Load words for conversion into words list
  for (line <- Source.fromFile(wordCsv).getLines()){
    val x = line.split(",")
    if(x(1).length() > 0) {
      words += ((x(0), x(1)))
      if(! Character.isUpperCase(x(0).charAt(0))) 
      words += ((capitaliseFirstLetter(x(0)), capitaliseFirstLetter(x(1))))
      
      def capitaliseFirstLetter(word:String):String = word(0).toUpper + word.substring(1, word.size -1)    
    
  } 
  
  
  }

  // RegEx's
  //finds words certain characters in the middle
  val midWordBad = "[a-z][^a-zA-Z '-*-θφόίδ.,ιρακϊ?’ηνσ?γ][a-z]+".r 
  //finds words with question marks in the middle
  val questionMarkPattern = "[a-z][?][a-z]+".r 
 // val questionMarkNotAtEnd = "\?\w+".r 
  
  
  //PROCESS
  
  for(file <- sourceInDirectory.listFiles() ) {
    
  
    var lines = scala.io.Source.fromFile(file).mkString
    
 
    
    for(word <- words){
      lines = lines.replaceAllLiterally(word._1, word._2)
    }

    new PrintWriter("C:/Julian/txtOut/" + file.getName) { write(lines); close }
  }
     

}