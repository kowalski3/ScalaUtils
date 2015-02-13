package xml

/*
 * https://bcomposes.wordpress.com/2012/05/04/basic-xml-processing-with-scala/
 */

class XmlParser(fileName:String) {
  
  //val pageList = List(1,2,3)
  
  
  
  val file = scala.xml.XML.loadFile(fileName) 
  
  
  def printXML = (file \ "Pages").foreach { Pages =>
   //  println("Pages")
     (Pages \ "Page").foreach { Page =>  
       println("<PAGE>") 
       (Page \ "Paragraphs").foreach { Paragraphs =>  
         println("    <PARAGRAPHS>") 
         (Paragraphs \ "Block").foreach { Block =>  
           println("      <BLOCK>")
           (Block \ "Lines").foreach { Lines =>  
             println("      <LINES>")
             (Lines \ "Line").foreach { Line =>  
               println("        <LINE>")
               (Line \ "Text").foreach { Text => 
                println("           <TEXT>" + Text.text + "</TEXT>")
               }
             }
           }
         }
       }
     }
   }
  
}

object Test extends App {
   
  val sfParser = new XmlParser("C:/Julian/git/scalaTools/data/sfXmlSample1.xml")
  
  sfParser.printXML
   
                 
   
 
}
